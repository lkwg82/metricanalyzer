package de.lgohlke;

import org.apache.commons.configuration.ConfigurationException;

import de.lgohlke.failedTestsfilter.IFailedTestFilter;
import de.lgohlke.failedTestsfilter.junit.JunitFailedTestFilter;
import de.lgohlke.failedTestsfilter.testng.TestngFailedTestFilter;
import de.lgohlke.io.bo.TEST_TYPE;

public class FailedTestFilterFactory
{
  public static IFailedTestFilter create(final TEST_TYPE type) throws ConfigurationException
  {
    switch (type)
    {
      case JUNIT:
        return new JunitFailedTestFilter("target/test-classes/junit-failed-tests");
      case TESTNG:
        return new TestngFailedTestFilter("target/test-classes/testng-failed-tests/");
      default:
        throw new ConfigurationException("this type  has no filter : " + type);
    }
  }
}
