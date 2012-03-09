package de.lgohlke.io;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.thoughtworks.qdox.model.JavaMethod;

import de.lgohlke.AST.Location;
import de.lgohlke.io.bo.TestMethod;
import de.lgohlke.qdox.JavaMethodHashed;

/**
 * <p>
 * RelatedTestsFinderResultQuery class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@RequiredArgsConstructor
@Slf4j
public class TestmethodContextFactory
{
  /**
   * <p>
   * findByKey.
   * </p>
   * 
   * @param key
   *          a {@link java.lang.String} object.
   * @return a {@link de.lgohlke.io.TestmethodContext} object.
   */
  public static TestmethodContext createByKey(final RelatedTestsFinder finder, final String key)
  {
    // lazyFind(finder);
    JavaMethod method = findJavaMethodByLocationKey(finder, key);
    TestMethod testMethod = findTestMethodByJavaMethod(finder, method);

    return createByTest(finder, testMethod);
  }

  private static JavaMethod findJavaMethodByLocationKey(final RelatedTestsFinder finder, final String key)
  {
    for (Entry<Location, JavaMethod> entry : finder.getResult().getLocationJavaMethodMap().entrySet())
    {
      if (key.equals(entry.getKey().getKey()))
      {
        return entry.getValue();
      }
    }
    return null;
  }

  private static TestMethod findTestMethodByJavaMethod(final RelatedTestsFinder finder, final JavaMethod method)
  {
    for (Entry<TestMethod, Set<JavaMethodHashed>> entry : finder.getResult().getTestCodeMap().entrySet())
    {
      JavaMethod methodToCompare = entry.getKey().getMethod();
      if (sameJavaMethods(method, methodToCompare))
      {
        return entry.getKey();
      }
    }
    return null;
  }

  private static boolean sameJavaMethods(final JavaMethod method, final JavaMethod methodToCompare)
  {
    return (method.getLineNumber() == methodToCompare.getLineNumber())
        && method.getDeclarationSignature(true).equals(methodToCompare.getDeclarationSignature(true))
        && method.getParentClass().getFullyQualifiedName().equals(methodToCompare.getParentClass().getFullyQualifiedName());
  }

  /**
   * <p>
   * findByTest.
   * </p>
   * 
   * @param finder
   * @param testMethod
   *          a {@link de.lgohlke.io.bo.TestMethod} object.
   * @return a {@link de.lgohlke.io.TestmethodContext} object.
   */
  public static TestmethodContext createByTest(final RelatedTestsFinder finder, final TestMethod testMethod)
  {
    // lazyFind(finder);

    TestmethodContext result = new TestmethodContext();

    result.setTestMethod(testMethod);
    RelatedTestsFinderResult result2 = finder.getResult();
    Map<TestMethod, Set<JavaMethodHashed>> testCodeMap = result2.getTestCodeMap();
    result.setRelatedMethods(testCodeMap.get(testMethod));

    Map<JavaMethodHashed, Location> locationMap = new HashMap<JavaMethodHashed, Location>();
    {
      for (JavaMethodHashed relatedMethod : result.getRelatedMethods())
      {
        addMatchingLocationForMethod(finder, relatedMethod, locationMap);
      }

      addMatchingLocationForMethod(finder, new JavaMethodHashed(testMethod.getMethod()), locationMap);
    }
    result.setLocationMap(locationMap);

    return result;
  }

  // private static synchronized void lazyFind(final RelatedTestsFinder finder)
  // {
  // if (finder.getResult() == null)
  // {
  // log.debug("starting finder - not ran yet");
  // finder.find();
  // }
  // }

  private static void addMatchingLocationForMethod(final RelatedTestsFinder finder, final JavaMethodHashed method,
      final Map<JavaMethodHashed, Location> locationMap)
  {
    for (Entry<Location, JavaMethod> entry : finder.getResult().getLocationJavaMethodMap().entrySet())
    {
      JavaMethod methodToCompare = entry.getValue();
      if (sameJavaMethods(method.getMethod(), methodToCompare))
      {
        locationMap.put(new JavaMethodHashed(entry.getValue()), entry.getKey());
        return;// we got what we want, so why continue looping
      }
    }
  }
}
