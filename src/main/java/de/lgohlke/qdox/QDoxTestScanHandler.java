package de.lgohlke.qdox;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaSource;

import de.lgohlke.io.bo.TEST_TYPE;
import de.lgohlke.io.bo.TestClass;
import de.lgohlke.io.bo.TestMethod;

@Slf4j
@Data
public class QDoxTestScanHandler implements IQDoxScanHandler
{

  private final List<TestClass> testClasses = new ArrayList<TestClass>();
  private final List<JavaClass> classList   = new ArrayList<JavaClass>();

  private void scanAllNestedClasses(final JavaClass[] classes)
  {
    if ((classes != null) && (classes.length > 0))
    {
      classList.addAll(Arrays.asList(classes));
      for (JavaClass clazz : classes)
      {
        if (clazz.isEnum())
        {
          for (JavaField jf : clazz.getFields())
          {
            // log.debug(jf + " type:" + jf.getType());

            if (jf.getType().getJavaClass().equals(clazz))
            {
              log.debug("enum issue : http://jira.codehaus.org/browse/QDOX-226");
            }

          }
        }
        scanAllNestedClasses(clazz.getNestedClasses());
      }
    }
  }

  private void findTestMethods(final JavaSource source)
  {
    for (JavaClass clazz : source.getClasses())
    {
      TestClass testClazz = null;

      for (JavaMethod method : clazz.getMethods())
      {
        TestMethod testMethod = null;

        // System.out.println(m);
        for (Annotation a : method.getAnnotations())
        {
          if (testMethod == null)
          {
            String type = a.getType().getFullyQualifiedName();
            if (TEST_TYPE.isTestImport(type))
            {
              testMethod = new TestMethod(method);
              testMethod.setType(TEST_TYPE.getType(type));
            }
          }
        }

        if (testMethod != null)
        {
          if (testClazz == null)
          {
            testClazz = new TestClass(clazz, new File(source.getURL().getFile()));
          }

          testClazz.getTests().add(testMethod);
        }
      }

      if (testClazz != null)
      {
        testClasses.add(testClazz);
      }
    }

  }

  private TEST_TYPE detectTestTypeByScanningImports(final JavaSource source)
  {
    boolean foundTestImport = false;
    TEST_TYPE type = TEST_TYPE.NONE;
    for (String _import : source.getImports())
    {
      if (!foundTestImport && TEST_TYPE.isTestImport(_import))
      {
        foundTestImport = true;
        type = TEST_TYPE.detectType(_import);
      }
    }

    return type;
  }

  @Override
  public void scanSource(final JavaSource source)
  {
    if (source.getClasses().length == 0)
    {
      if (log.isDebugEnabled())
      {
        log.debug("no classes in " + source.getURL());
      }
    }
    else
    {
      TEST_TYPE type = detectTestTypeByScanningImports(source);
      if (log.isDebugEnabled())
      {
        log.debug(" scanned class : " + source.getClasses()[0].getFullyQualifiedName() + " [type:" + type + "]");
      }
      // log.debug(" in file " + new File(source.getURL().getFile()));

      if (type != TEST_TYPE.NONE)
      {
        findTestMethods(source);
      }

      scanAllNestedClasses(source.getClasses());
    }
  }
}
