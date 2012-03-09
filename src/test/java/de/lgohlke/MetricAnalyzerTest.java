package de.lgohlke;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import com.google.common.io.Files;

public class MetricAnalyzerTest
{
  @Test (timeout = 60000)
  public void testAll() throws Exception
  {

    File outputDir = Files.createTempDir();
    try
    {
      File directoryToAnalyse = new File(System.getProperty("user.dir"));

      new MetricAnalyzer().addDirectoryForAnalysis(directoryToAnalyse).writeTo(outputDir).analyze();

      Assert.assertTrue(outputDir + " is missing ", outputDir.exists());
    }
    finally
    {
      Files.deleteRecursively(outputDir);
    }
  }

  @Test (timeout = 60000)
  public void testRelativSourceDirectory() throws Exception
  {

    File outputDir = Files.createTempDir();
    try
    {
      File directoryToAnalyse = new File("src");

      new MetricAnalyzer().addDirectoryForAnalysis(directoryToAnalyse).writeTo(outputDir).analyze();

      Assert.assertTrue(outputDir + " is missing ", outputDir.exists());
    }
    finally
    {
      Files.deleteRecursively(outputDir);
    }
  }
}
