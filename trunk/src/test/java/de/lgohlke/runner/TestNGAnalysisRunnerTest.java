package de.lgohlke.runner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import org.testng.IAnnotationTransformer;
import org.testng.TestNG;
import org.testng.annotations.ITestAnnotation;

import com.google.common.io.Files;

public class TestNGAnalysisRunnerTest
{
  private File                   outputDir;
  @SuppressWarnings("rawtypes")
  private IAnnotationTransformer annotationTransformer = new IAnnotationTransformer()
  {

    @Override
    public void transform(final ITestAnnotation annotation, final Class testClass,
        final Constructor testConstructor, final Method testMethod)
    {
      if (testMethod.getName().startsWith("testNG"))
      {
        annotation.setEnabled(true);
      }

    }
  };

  @Before
  public void before()
  {
    outputDir = Files.createTempDir();
  }

  @After
  public void after() throws IOException
  {
    FileUtils.deleteDirectory(outputDir);
  }

  @Test
  public void test() throws IOException
  {
    TestNGAnalysisRunner runner = new TestNGAnalysisRunner();
    runner.setOutputDir(outputDir);
    TestNG testng = new TestNG();
    testng.setTestClasses(new Class[] { TestNGAnalysisRunnerTest.class });
    testng.addListener(runner);
    testng.setAnnotationTransformer(annotationTransformer);
    testng.run();

    Assert.assertEquals(1, new File(outputDir.getAbsolutePath() + "/classes").list().length);
    Assert.assertEquals(runner.getFailedTests().size(), new File(outputDir.getAbsolutePath() + "/testmethodsSource").list().length);
    Assert.assertEquals(runner.getFailedTests().size(), new File(outputDir.getAbsolutePath() + "/testmethods").list().length);
  }

  @org.testng.annotations.Test(enabled=false)
  public void testNGTest1()
  {
    System.out.println();
    Assert.fail("fail is ok");
  }

  @org.testng.annotations.Test(enabled = false)
  public void testNGTest2()
  {
    Assert.fail("fail is ok");
  }
}
