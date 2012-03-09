package de.lgohlke.io.bo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.thoughtworks.qdox.model.JavaClass;

/**
 * <p>
 * TestClass class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
@RequiredArgsConstructor
@Data
public class TestClass
{
  private final JavaClass        clazz;
  private final List<TestMethod> tests = new ArrayList<TestMethod>();
  private final File             file;

  /** {@inheritDoc} */
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
