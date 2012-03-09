package de.lgohlke.runner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import de.lgohlke.AnalysisTestFilter;
import de.lgohlke.MetricAnalyzer;
import de.lgohlke.failedTestsfilter.FailedTest;
import de.lgohlke.failedTestsfilter.IFailedTestFilter;
import de.lgohlke.io.bo.TEST_TYPE;

public class TestNGAnalysisRunner implements ITestListener, IFailedTestFilter
{
  @Setter
  private File                   outputDir   = new File("target/analysis");
  private final List<FailedTest> failedTests = new ArrayList<FailedTest>();

  @Override
  public void onTestStart(final ITestResult result)
  {
    // ok
  }

  @Override
  public void onTestSuccess(final ITestResult result)
  {
    // ok
  }

  @Override
  public void onTestFailure(final ITestResult result)
  {
    // ok
  }

  @Override
  public void onTestSkipped(final ITestResult result)
  {
    // ok
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(final ITestResult result)
  {
    // ok
  }

  @Override
  public void onStart(final ITestContext context)
  {
    // ok
  }

  @Override
  public void onFinish(final ITestContext context)
  {
    for (ITestNGMethod m : context.getFailedTests().getAllMethods())
    {
      failedTests.add(FailedTest.createRawFailedTest(m.getRealClass().getCanonicalName(), m.getMethodName()));
    }
    runPostTestAnalysis();
  }

  /**
   * 
   */
  private void runPostTestAnalysis()
  {
    File directoryToAnalyse = new File(System.getProperty("user.dir"));

    AnalysisTestFilter analysisTestFilter = new AnalysisTestFilter(TEST_TYPE.TESTNG);
    try
    {
      analysisTestFilter.setFilter(this);

      new MetricAnalyzer().//
      addDirectoryForAnalysis(directoryToAnalyse).//
      writeTo(outputDir).//
      filter(analysisTestFilter).//
      analyze();
    }
    catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public List<FailedTest> findFailedTests() throws IOException
  {
    return failedTests;
  }

  @Override
  public List<FailedTest> getFailedTests() throws IOException
  {
    return failedTests;
  }

  @Override
  public TEST_TYPE getType()
  {
    return TEST_TYPE.TESTNG;
  }

}
