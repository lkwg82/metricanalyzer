package biz.company.qa.prototype;

import biz.company.qa.prototype.SystemContext;

import org.apache.camel.CamelException;
import org.testng.annotations.Test;

public class SystemContextTest
{
  @Test
  public void startStop() throws Exception
  {
    SystemContext context = new SystemContext();
    context.start();
    context.stop();
  }

  @Test(expectedExceptions = CamelException.class)
  public void startStopStop() throws Exception
  {
    SystemContext context = new SystemContext();
    context.start();
    context.stop();
    context.stop();
  }

  @Test(expectedExceptions = CamelException.class)
  public void startStartStop() throws Exception
  {
    SystemContext context = new SystemContext();
    context.start();
    context.start();
    context.stop();
  }
}
