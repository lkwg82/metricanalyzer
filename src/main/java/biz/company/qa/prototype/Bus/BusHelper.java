package biz.company.qa.prototype.Bus;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class BusHelper
{

  private BusService bus;

  public BusHelper(final BusService bus)
  {
    this.bus = bus;
  }

  /**
   * sends a message, in case the destination is synchronuous it returns a message
   * 
   * @param destination
   * @param message
   * @return
   * @throws Exception
   */
  public Message sendMessage(final DESTINATION destination, final Message message) throws Exception
  {
    Endpoint endpoint = bus.getEndpoint(destination);

    Exchange exchange = endpoint.createExchange();
    exchange.setIn(message);
    endpoint.createProducer().process(exchange);

    if (destination.isSync())
    {
      return exchange.getOut();
    }
    else
    {
      return null;
    }
  }
}
