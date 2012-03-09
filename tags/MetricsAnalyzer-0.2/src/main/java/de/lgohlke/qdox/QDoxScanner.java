package de.lgohlke.qdox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaSource;

import de.lgohlke.io.bo.TEST_TYPE;
import de.lgohlke.io.bo.TestClass;
import de.lgohlke.io.bo.TestMethod;

public class QDoxScanner
{
  private final List<TestClass> results = new ArrayList<TestClass>();
  private final File            file;

  public QDoxScanner(final File file)
  {
    this.file = file;

  }

  public void scan() throws IOException
  {
    JavaDocBuilder builder = new JavaDocBuilder();
    JavaSource source = builder.addSource(file);

    boolean foundTestImport = false;
    for (String _import : source.getImports())
    {
      if (!foundTestImport && TEST_TYPE.isTestImport(_import))
      {
        foundTestImport = true;
      }
    }

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
            testClazz = new TestClass(clazz, file);
          }

          testClazz.getTests().add(testMethod);
        }
      }

      if (testClazz != null)
      {
        results.add(testClazz);
      }
    }

  }

  public List<TestClass> getClasses()
  {

    return results;
  }

}
