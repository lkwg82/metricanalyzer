package de.lgohlke.runner;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang.StringUtils;

import de.lgohlke.AnalysisTestFilter;
import de.lgohlke.AnalyzerFactory;
import de.lgohlke.MetricAnalyzer;
import de.lgohlke.io.bo.TEST_TYPE;

@Slf4j
public class CLIRunner
{
  private OutputStream out = System.out;

  public static void main(final String[] args)
  {
    try
    {
      new CLIRunner().parseCLIandCreateInstance(args).analyze();
    }
    catch (ConfigurationException e)
    {
      // ok
    }
    catch (OptionException e)
    {
      // ok
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public MetricAnalyzer parseCLIandCreateInstance(final String[] args) throws IOException, ConfigurationException
  {
    String[] _args;
    if (args == null)
    {
      _args = new String[] {};
    }
    else
    {
      _args = args;
    }

    OptionParser parser = new OptionParser();

    ArgumentAcceptingOptionSpec<File> mavenOptSpec = parser
        .acceptsAll(asList("m", "maven-project-path"), "analyze a maven project and give path to directory the pom.xml sits in").//
        withRequiredArg().ofType(File.class);
    ArgumentAcceptingOptionSpec<File> sourceOptSpec = parser.acceptsAll(asList("s", "source-dir"), "directory of sources, could be a tree").withRequiredArg()
        .ofType(File.class);
    ArgumentAcceptingOptionSpec<File> outputOptSpec = parser.acceptsAll(asList("o", "output-dir"), "directory for output").withRequiredArg().ofType(File.class);

    ArgumentAcceptingOptionSpec<String> helpOptSpec = parser.acceptsAll(asList("h", "?", "help"), "show help").withOptionalArg();
    // ArgumentAcceptingOptionSpec<String> verboseOptSpec = parser.acceptsAll(asList("v", "verbose"),
    // "be more verbose").withOptionalArg();

    ArgumentAcceptingOptionSpec<TEST_TYPE> testTypeOptSpec = parser.acceptsAll(asList("t", "test-type"), "type of failed tests to analyze").withRequiredArg()
        .ofType(TEST_TYPE.class);

    MetricAnalyzer analyzer = null;
    try
    {
      log.debug("arguments : " + StringUtils.join(_args, " "));
      OptionSet options = parser.parse(_args);

      if (options.has(helpOptSpec))
      {
        parser.printHelpOn(getOut());
      }
      else if (options.has(mavenOptSpec) && options.has(outputOptSpec))
      {
        analyzer = new AnalyzerFactory().createInstanceForMavenWithCustomOutput(mavenOptSpec.value(options), outputOptSpec.value(options));
      }
      else if (options.has(mavenOptSpec))
      {
        analyzer = new AnalyzerFactory().createInstanceForMaven(mavenOptSpec.value(options));
      }
      else if (options.has(sourceOptSpec) && options.has(outputOptSpec))
      {
        analyzer = new AnalyzerFactory().createInstanceWithCustomSettings(sourceOptSpec.value(options), outputOptSpec.value(options));
      }
      else
      {
        throw new ConfigurationException("please add at least -m or -s and -o");
      }

      /*
       * some optional
       */if (analyzer != null)
       {
         if (options.has(testTypeOptSpec))
         {

           TEST_TYPE type = testTypeOptSpec.value(options);
           log.info("adding filter for failed tests of type : " + type);

           analyzer.filter(new AnalysisTestFilter(type));
         }
       }
       return analyzer;
    }
    catch (ConfigurationException e)
    {
      out.write((e.getMessage() + "\n").getBytes());
      parser.printHelpOn(out);
      throw e;
    }
    catch (OptionException e)
    {
      out.write((e.getMessage() + "\n").getBytes());
      parser.printHelpOn(out);
      throw e;
    }
  }

  public OutputStream getOut()
  {
    return out;
  }

  public void setOut(final OutputStream out)
  {
    this.out = out;
  }
}
