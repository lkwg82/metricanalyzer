package de.lgohlke.runner;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.OutputStream;

import joptsimple.OptionException;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.lgohlke.MetricAnalyzer;
import de.lgohlke.io.bo.TEST_TYPE;

public class CLIRunnerTest
{

  private CLIRunner wrapper;

  @Before
  public void before()
  {
    wrapper = new CLIRunner();
    wrapper.setOut(new OutputStream()
    {
      private final StringBuilder string = new StringBuilder();

      @Override
      public void write(final int b) throws IOException
      {
        this.string.append((char) b);
      }

      @Override
      public String toString()
      {
        return this.string.toString();
      }
    });
  }

  @Test(expected=ConfigurationException.class)
  public void emptyArguments() throws Exception
  {
    wrapper.parseCLIandCreateInstance(new String[] {});
  }

  @Test(expected = ConfigurationException.class)
  public void nullArguments() throws Exception
  {
    wrapper.parseCLIandCreateInstance(null).analyze();
  }

  @Test
  public void chooseTestTypeUpperCaseJunit() throws Exception
  {
    MetricAnalyzer analyzer = wrapper.parseCLIandCreateInstance(new String[] { "-m", ".", "-t", "JUNIT" });

    Assert.assertTrue(wrapper.getOut().toString().length() == 0);
    Assert.assertEquals(TEST_TYPE.JUNIT, analyzer.getAnalysisTestFilter().getType());
  }

  @Test
  public void chooseTestTypeLowerCaseJunit() throws Exception
  {
    try
    {
      wrapper.parseCLIandCreateInstance(new String[] { "-m", ".", "-t", "junit" });
      fail("should fail");
    }
    catch (OptionException e)
    {
      Assert.assertTrue(wrapper.getOut().toString().startsWith("Cannot convert argument"));
    }
  }

  @Test
  public void chooseTestTypeTestNG() throws Exception
  {
    MetricAnalyzer analyzer = wrapper.parseCLIandCreateInstance(new String[] { "-m", ".", "-t", "TESTNG" });

    Assert.assertTrue(wrapper.getOut().toString().length() == 0);
    Assert.assertEquals(TEST_TYPE.TESTNG, analyzer.getAnalysisTestFilter().getType());
  }

  @Test
  public void mavenEnvironment() throws Exception
  {
    MetricAnalyzer instance = wrapper.parseCLIandCreateInstance(new String[] { "--maven-project-path=." });

    Assert.assertTrue(wrapper.getOut().toString().length() == 0);
    Assert.assertNotNull(instance);
  }

  @Test(expected=ConfigurationException.class)
  public void mavenEnvironmentNonExistingDirectory() throws Exception
  {
    String[] params = new String[] { "--maven-project-path=asdasdasdasd" };
    wrapper.parseCLIandCreateInstance(params);
  }

  /*
   * these both tests are only for testing failed tests
   */

  @Test
  @Ignore
  public void fails()
  {
    fail("ok");
  }

  @Test
  @Ignore
  public void fails2()
  {
    fail("ok");
  }
}
