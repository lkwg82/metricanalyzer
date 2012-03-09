package de.lgohlke.io.bo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.qdox.model.JavaClass;

public class TestClass
{
  private final static Logger    log   = LoggerFactory.getLogger(TestClass.class);
  private final JavaClass        clazz;
  private final List<TestMethod> tests = new ArrayList<TestMethod>();
  private final File             file;

  public TestClass(final JavaClass clazz, final File file)
  {
    this.file = file;
    this.clazz = clazz;
  }

  public List<TestMethod> getTests()
  {
    return tests;
  }

  public JavaClass getClazz()
  {
    return clazz;
  }

  @Override
  public String toString()
  {
    StringBuffer buffer = new StringBuffer();

    try
    {
      buffer.append(clazz.getName() + "(" + file.getCanonicalPath() + ")");
    }
    catch (IOException e)
    {
      log.error(e.getMessage(), e);
    }

    buffer.append("\n");
    for (TestMethod m : getTests())
    {
      buffer.append("\t");
      buffer.append(m);
      buffer.append("\n");
    }

    return buffer.toString();
  }

}
