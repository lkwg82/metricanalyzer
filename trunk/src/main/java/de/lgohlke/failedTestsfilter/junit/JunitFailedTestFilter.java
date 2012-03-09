package de.lgohlke.failedTestsfilter.junit;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.thoughtworks.xstream.mapper.CannotResolveClassException;

import de.lgohlke.failedTestsfilter.FailedTest;
import de.lgohlke.failedTestsfilter.IFailedTestFilter;
import de.lgohlke.io.bo.TEST_TYPE;
import de.lgohlke.serializer.SerializeHelper;

@Slf4j
@RequiredArgsConstructor
public class JunitFailedTestFilter implements IFailedTestFilter
{
  private List<FailedTest> results;
  private final String     failedTestsDir;

  @Override
  public List<FailedTest> findFailedTests() throws IOException
  {
    List<FailedTest> result = new ArrayList<FailedTest>();
    File[] list = new File(failedTestsDir).listFiles(new FileFilter()
    {
      @Override
      public boolean accept(final File f)
      {
        return f.isFile() && f.getName().endsWith(".xml");
      }
    });

    if (list != null)
    {
      for (File f : list)
      {
        log.info("parsing " + f.getName());

        try
        {
          JunitTestSuite testsuite = SerializeHelper.fromXML(f, JunitTestSuite.class);
          result.addAll(testsuite.getFailedTests());
        }
        catch (CannotResolveClassException e)
        {
          throw new IOException("file  " + f.getAbsolutePath() + "  does not exist or with wrong format");
        }

      }
    }
    return result;

  }

  @Override
  public List<FailedTest> getFailedTests() throws IOException
  {
    if (results == null)
    {
      results = findFailedTests();
    }
    return results;
  }

  @Override
  public TEST_TYPE getType()
  {
    return TEST_TYPE.JUNIT;
  }

}
