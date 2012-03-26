package de.lgohlke;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.configuration.ConfigurationException;

public class AnalyzerFactory
{
  /**
   * @param projectdir
   *          - project directory
   * @return
   * @throws ConfigurationException
   */
  public MetricAnalyzer createInstanceForMaven(final File projectdir) throws ConfigurationException
  {
    File outputFile = new File(projectdir.getAbsolutePath() + "/target/analysis");
    return createInstanceForMavenWithCustomOutput(projectdir, outputFile);
  }

  public MetricAnalyzer createInstanceWithCustomSettings(final File sourceFile, final File outputFile)
  {
    return new MetricAnalyzer().addDirectoryForAnalysis(sourceFile).writeTo(outputFile);
  }

  public MetricAnalyzer createInstanceForMavenWithCustomOutput(final File projectdir, final File outputFile) throws ConfigurationException
  {
    FileFilter pomFileFilter = new FileFilter()
    {
      @Override
      public boolean accept(final File f)
      {
        return f.isFile() && f.getName().equals("pom.xml");
      }
    };

    if (projectdir.isDirectory() && (projectdir.listFiles(pomFileFilter).length == 1))
    {
      File sourceFile = new File(projectdir.getAbsolutePath() + "/src");

      return createInstanceWithCustomSettings(sourceFile, outputFile);
    }
    else
    {
      throw new ConfigurationException("could not configure with maven, set directory to pom.xml : " + projectdir.getAbsolutePath());
    }
  }

}
