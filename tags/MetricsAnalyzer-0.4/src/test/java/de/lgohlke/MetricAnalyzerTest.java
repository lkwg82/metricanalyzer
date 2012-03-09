package de.lgohlke;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.Files;

import de.lgohlke.failedTestsfilter.junit.JunitFailedTestFilter;
import de.lgohlke.io.bo.TEST_TYPE;

public class MetricAnalyzerTest
{
  private File outputDir;

  @Before
  public void before()
  {
    outputDir = Files.createTempDir();
  }

  @After
  public void after() throws IOException
  {
    Files.deleteRecursively(outputDir);
  }

  @Test(timeout = 60000)
  public void testAll() throws Exception
  {
    File directoryToAnalyse = new File(System.getProperty("user.dir"));

    new MetricAnalyzer().addDirectoryForAnalysis(directoryToAnalyse).writeTo(outputDir).analyze();

    Assert.assertTrue(outputDir + " is missing ", outputDir.exists());
  }

  @Test(timeout = 60000)
  public void testRelativSourceDirectory() throws Exception
  {
    File directoryToAnalyse = new File("src");

    new MetricAnalyzer().addDirectoryForAnalysis(directoryToAnalyse).writeTo(outputDir).analyze();

    Assert.assertTrue(outputDir + " is missing ", outputDir.exists());
  }

  @Test
  // (timeout = 60000)
  public void testFailedTestsFilter() throws Exception
  {
    File directoryToAnalyse = new File(System.getProperty("user.dir"));
    AnalysisTestFilter analysisTestFilter = new AnalysisTestFilter(TEST_TYPE.JUNIT);
    JunitFailedTestFilter filter = new JunitFailedTestFilter("src/test/resources/junit-failed-tests");
    // TestngFailedTestFilter filter = new TestngFailedTestFilter("src/test/resources/testng-failed-tests");
    analysisTestFilter.setFilter(filter);

    System.out.println("found failed tests: " + filter.getFailedTests().size());

    new MetricAnalyzer().addDirectoryForAnalysis(directoryToAnalyse).writeTo(outputDir).filter(analysisTestFilter).analyze();

    Assert.assertTrue(outputDir + " is missing ", outputDir.exists());
    File classDir = new File(outputDir.getAbsolutePath() + "/classes");
    System.out.println(classDir);
    Assert.assertEquals(1, classDir.list().length);
    Assert.assertEquals(filter.getFailedTests().size(), new File(outputDir.getAbsolutePath() + "/testmethodsSource").list().length);
    Assert.assertEquals(filter.getFailedTests().size(), new File(outputDir.getAbsolutePath() + "/testmethods").list().length);
  }
}
