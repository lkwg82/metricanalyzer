package biz.company.qa.prototype.Bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;
import biz.company.qa.pageobjects.utils.ICondition;
import biz.company.qa.pageobjects.utils.Waiter;
import biz.company.qa.prototype.MessageListener;

import org.apache.camel.Endpoint;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BusServiceTest
{
  private BusService bus;

  @BeforeMethod
  public void init()
  {
    bus = new BusService();
  }

  @AfterMethod
  public void cleanup() throws Exception
  {
    if (bus.isStarted())
    {
      bus.stop();
    }
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void smokeTest() throws Exception
  {
    bus.start();

    Endpoint endpoint = bus.getEndpoint(DESTINATION.API_SYNC);

    endpoint.createProducer().process(null);

  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void listenersInitializedCorrectlyAfterStart() throws Exception
  {
    bus.start();

    bus.addListener(new MessageListener()
    {

      @Override
      public void onMessage(final Message message) throws Exception
      {
        // ok

      }
    }, DESTINATION.API_SYNC);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void listenersInitializedCorrectlyBeforeStart() throws Exception
  {

    bus.addListener(new MessageListener()
    {

      @Override
      public void onMessage(final Message message) throws Exception
      {
        // ok

      }
    }, DESTINATION.API_SYNC);
    bus.start();
  }

  @Test
  public void roundtrip() throws Exception
  {

    class MyListener implements MessageListener
    {
      private List<Message> messages = new ArrayList<Message>();

      @Override
      public void onMessage(final Message message)
      {
        messages.add(message);
      }

      public List<Message> getMessages()
      {
        return messages;
      }

      public void waitForSomeTimeForReceivingAMessage()
      {
        try
        {
          new Waiter(5, TimeUnit.SECONDS).forCondition(new ICondition()
          {
            @Override
            public String getErrorMessage()
            {
              return "timedout";
            }

            @Override
            public boolean check()
            {
              return getMessages().size() == 1;
            }
          });

        }
        catch (TimeoutException e)
        {
          // ok
        }

      }
    }
    BusHelper helper = new BusHelper(bus);

    final MyListener myListener = new MyListener();

    bus.addListener(myListener, DESTINATION.DB_SYNC);
    bus.start();

    DefaultMessage message = new DefaultMessage();
    message.setBody(new Random().nextDouble());

    helper.sendMessage(DESTINATION.API_SYNC, message);

    myListener.waitForSomeTimeForReceivingAMessage();
    Assert.assertEquals(1, myListener.getMessages().size());
  }

}
