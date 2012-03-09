package de.lgohlke.failedTestsfilter;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.lgohlke.failedTestsfilter.junit.JunitFailedTestFilter;
import de.lgohlke.failedTestsfilter.testng.TestngFailedTestFilter;

public class FailedTestFilterTest
{

  @Test
  public void findFailedTestNGTests() throws IOException
  {
    TestngFailedTestFilter filter = new TestngFailedTestFilter("src/test/resources/testng-failed-tests");
    testIt(filter);
  }

  @Test
  public void findFailedJunitTests() throws IOException
  {
    JunitFailedTestFilter filter = new JunitFailedTestFilter("src/test/resources/junit-failed-tests");
    testIt(filter);
  }

  private void testIt(final IFailedTestFilter filter) throws IOException
  {
    List<FailedTest> failedTests = filter.findFailedTests();

    Assert.assertEquals(2, failedTests.size());
    Assert.assertEquals("fails", failedTests.get(0).getMethod());
    Assert.assertEquals("fails2", failedTests.get(1).getMethod());
  }
}
