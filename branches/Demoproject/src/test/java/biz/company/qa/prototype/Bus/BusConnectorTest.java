package biz.company.qa.prototype.Bus;

import junit.framework.Assert;

import biz.company.qa.prototype.MessageListener;
import biz.company.qa.prototype.STATUS;
import biz.company.qa.prototype.Bus.BusConnector;
import biz.company.qa.prototype.Bus.BusService;
import biz.company.qa.prototype.Bus.DESTINATION;
import biz.company.qa.prototype.Messages.LoginMessage;
import biz.company.qa.prototype.Messages.ResponseMessage;

import org.apache.camel.Message;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BusConnectorTest
{
  private BusService          bus;
  private BusConnector connector;

  @BeforeMethod
  public void before() throws Exception
  {

    bus = new BusService();
    bus.start();
    connector = new BusConnector(bus);
  }

  @AfterMethod
  public void after() throws Exception
  {
    bus.stop();
  }

  @Test
  public void test() throws Exception
  {
    // mock DB-endpoint
    bus.addListener(new MessageListener()
    {

      @Override
      public void onMessage(final Message message)
      {
        message.setHeader("status", -1);
      }
    }, DESTINATION.DB_SYNC);

    ResponseMessage response = connector.send(LoginMessage.create("s", "<"));

    Assert.assertEquals(STATUS.ERROR, response.getStatus());
  }
}
