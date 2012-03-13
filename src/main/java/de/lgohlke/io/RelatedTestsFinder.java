package de.lgohlke.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.picocontainer.MutablePicoContainer;

import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

import de.lgohlke.CommonStore;
import de.lgohlke.AST.Location;
import de.lgohlke.AST.Registry;
import de.lgohlke.concurrent.ThreadPool;
import de.lgohlke.io.bo.TEST_TYPE;
import de.lgohlke.io.bo.TestClass;
import de.lgohlke.io.bo.TestMethod;
import de.lgohlke.qdox.JavaMethodHashed;

/**
 * <p>
 * RelatedTestsFinder class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
@RequiredArgsConstructor
public class RelatedTestsFinder
{
  private final MutablePicoContainer pico;
  private final List<Location>       locationList;
  private final Registry             registry;

  @Getter
  private RelatedTestsFinderResult   result;

  // private Map<JavaClass, JavaClass> parentClassMap;

  private Map<Location, JavaMethod>  locationMethodMap;

  private volatile int               count = 0;
  private volatile int               step  = 0;

  /**
   * <p>
   * find.
   * </p>
   */
  public void find()
  {
    Map<Location, JavaMethod> locationJavaMethodMap = new HashMap<Location, JavaMethod>();
    final Map<TestMethod, Set<JavaMethodHashed>> testCodeMap = new ConcurrentHashMap<TestMethod, Set<JavaMethodHashed>>();

    initQdoxSearchBase();

    for (Location loc : locationList)
    {
      JavaMethod method = findQDoxMethod(loc);
      if (method == null)
      {
        if (loc.getClazz().startsWith("anonymousInner#"))
        {
          // ok, we would like to dismiss this
          if (log.isDebugEnabled())
          {
            log.debug("dismiss anonymousInner#");
          }
        }
        else if (loc.getKey().matches(".*\\$\\d+#.*"))
        {
          // is enum with methods
          if (log.isDebugEnabled())
          {
            log.debug("is enum method : " + loc.getKey());
          }
        }
        else
        {
          if (registry.getQdoxErrorParsedFiles().contains(loc.getFile()))
          {
            if (log.isDebugEnabled())
            {
              log.debug("is an qdox error parsed file");
            }
          }
          else
          {
            log.warn("did not find : " + loc.getKey() + "\n" + loc.getFile());
          }
        }
      }
      else
      {
        locationJavaMethodMap.put(loc, method);
      }
    }

    ThreadPool pool = ThreadPool.getInstance();
    /**
     * alle method finden, die für einen Tests relevant sind
     */
    final int size = pico.getComponent(CommonStore.class).getTestClasses().size();
    count = 0;
    for (final TestClass testClass : pico.getComponent(CommonStore.class).getTestClasses())
    {
      pool.submit(new Runnable()
      {
        @Override
        public void run()
        {
          count++;
          int progress = (count * 100) / size;
          if (((progress % 10) == 0) && (step != (progress / 10)))
          {
            step = progress / 10;
            log.debug(count + "/" + size + " " + progress + "% testclass: " + testClass.getClazz().getName());
          }

          HashMap<TestMethod, Set<JavaMethodHashed>> tempTestCodeMap = new HashMap<TestMethod, Set<JavaMethodHashed>>();

          for (TestMethod method : testClass.getTests())
          {
            // log.debug("   testmethod: " + method);
            // log.debug(" hash " + method.hashCode());
            findConnectedMethods(method, tempTestCodeMap);
          }

          testCodeMap.putAll(tempTestCodeMap);
        }
      });
    }

    pool.waitForShutdown();

    result = new RelatedTestsFinderResult(testCodeMap, locationJavaMethodMap);
  }

  private void findConnectedMethods(final TestMethod method, final Map<TestMethod, Set<JavaMethodHashed>> testCodeMap)
  {
    findConnectedMethods(method, testCodeMap, method.getMethod().getParentClass());
  }

  /**
   * @param method
   *          to seek for
   * @param testCodeMap
   *          - map to save the results
   */
  private void findConnectedMethods(final TestMethod method, final Map<TestMethod, Set<JavaMethodHashed>> testCodeMap, final JavaClass clazz)
  {
    if (!testCodeMap.containsKey(method))
    {
      testCodeMap.put(method, new HashSet<JavaMethodHashed>());
    }

    log.debug("seeking connected methods for " + method);
    TEST_TYPE type = method.getType();
    for (JavaMethod m : clazz.getMethods(false))
    {
      if (!m.equals(method.getMethod()))
      {
        /**
         * test-spezifische Annotationen enthalten?
         */
        // System.out.println(m);
        boolean lifecycleAnnotationForThisTypeFound = false;
        for (Annotation a : m.getAnnotations())
        {
          if (!lifecycleAnnotationForThisTypeFound)
          {
            String annotationType = a.getType().getFullyQualifiedName();
            // System.out.println("@" + annotationType);

            if (type.getLifeCycleAnnotations().contains(annotationType))
            {
              log.debug("     method is connected : " + m.getName() + " " + a);
              lifecycleAnnotationForThisTypeFound = true;
              testCodeMap.get(method).add(new JavaMethodHashed(m));
            }
          }
        }
      }
    }
    JavaClass superClazz = clazz.getSuperJavaClass();
    // log.debug("super class of " + clazz + " is " + superClazz);
    // JavaClass superClazz = bugWorkAroundFindingCorrectSuperclass(clazz);
    /**
     * calls recursivly with parent class
     */
    if (!((superClazz == null) || "java.lang.Object".equals(superClazz.getFullyQualifiedName())))
    {

      // log.debug("current           : " + clazz);
      // log.debug("super             : " + clazz.getSuperJavaClass());
      // log.debug("super-methods (1) : " + clazz.getSuperJavaClass().getMethods().length);
      // log.debug("super-methods (2) : " + superClazz.getMethods().length);
      // log.debug("super-super       : " + clazz.getSuperJavaClass().getSuperJavaClass());
      findConnectedMethods(method, testCodeMap, superClazz);
    }
  }

  private JavaMethod findQDoxMethod(final Location loc)
  {
    if (locationMethodMap.containsKey(loc))
    {
      return locationMethodMap.get(loc);
    }
    else
    {
      return null;
    }
  }

  private void initQdoxSearchBase()
  {
    if (locationMethodMap == null)
    {
      log.debug("init QDoxMethod map  ");
      locationMethodMap = new ConcurrentHashMap<Location, JavaMethod>(locationList.size());

      final CommonStore store = pico.getComponent(CommonStore.class);
      final JavaClass[] javaClassArray = store.getJavaClasses().toArray(new JavaClass[store.getJavaClasses().size()]);
      List<JavaMethod> methods = new ArrayList<JavaMethod>();

      @Data
      class Range
      {
        private final int start;
        private final int end;

        public Range[] init(final int max, final int count)
        {
          // init ranges
          Range[] ranges = new Range[max];
          for (int i = 0; i < ranges.length; i++)
          {
            if (i == 0)
            {
              ranges[0] = new Range(0, count / ranges.length);
            }
            else
            {
              ranges[i] = new Range(ranges[i - 1].getEnd() + 1, Math.min(ranges[i - 1].getEnd() + 1 + (count / ranges.length), count - 1));
            }
          }
          return ranges;
        }
      }

      final ThreadPool pool = ThreadPool.getNaturalInstance();

      if (log.isDebugEnabled())
      {
        log.debug("listing methods");
      }

      for (JavaClass clazz : javaClassArray)
      {
        for (JavaMethod method : clazz.getMethods())
        {
          methods.add(method);
        }
      }

      final JavaMethod[] methodsArray = methods.toArray(new JavaMethod[methods.size()]);

      // FIXME: ranges falsch, zu klein
      Range[] ranges = new Range(0, 0).init(100, methodsArray.length);

      if (log.isDebugEnabled())
      {
        log.debug("finding locations");
      }
      for (int i = 0; i < ranges.length; i++)
      {
        final Range currentRange = ranges[i];
        pool.submit(new Runnable()
        {
          @Override
          public void run()
          {
            for (Location location : locationList)
            {
              for (int index = currentRange.getStart(); index <= currentRange.getEnd(); index++)
              {
                JavaMethod method = methodsArray[index];
                if ((method.getLineNumber() >= location.getLineStart()) && (method.getLineNumber() <= location.getLineEnd()))
                {
                  String file = method.getSource().getURL().getFile();

                  if (file.equals(location.getFile()))
                  {
                    locationMethodMap.put(location, methodsArray[index]);
                    break;
                  }
                }
              }
            }
          }
        });
      }

      pool.waitForShutdown();

      log.debug("end QDoxMethodmap init");
    }
  }
}
