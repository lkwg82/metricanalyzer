package de.lgohlke.failedTestsfilter.testng;

import java.io.File;
import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import de.lgohlke.failedTestsfilter.FailedTest;
import de.lgohlke.failedTestsfilter.IFailedTestFilter;
import de.lgohlke.io.bo.TEST_TYPE;
import de.lgohlke.serializer.SerializeHelper;

@Slf4j
@RequiredArgsConstructor
public class TestngFailedTestFilter implements IFailedTestFilter
{
  private final static String failedTestsXml = "testng-failed.xml";
  private final String        failedTestsDir;
  private List<FailedTest>    results;

  @Override
  public List<FailedTest> findFailedTests() throws IOException
  {

    File file = new File(failedTestsDir + "/" + failedTestsXml);

    log.info("parsing " + file.getName());

    TestngTestSuite testsuite = SerializeHelper.fromXML(file, TestngTestSuite.class);
    return testsuite.getFailedTests();
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
    return TEST_TYPE.TESTNG;
  }

}
