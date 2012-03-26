package biz.company.qa.prototype.Bus;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import biz.company.qa.prototype.MessageListener;
import biz.company.qa.prototype.Service;

import org.apache.camel.CamelContext;
import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class BusService implements Service
{
  class ListenerEndpoint
  {
    private final MessageListener listener;
    private final DESTINATION     destination;

    public ListenerEndpoint(final MessageListener listener, final DESTINATION destination)
    {
      this.listener = listener;
      this.destination = destination;
    }

    public DESTINATION getDestination()
    {
      return destination;
    }

    public MessageListener getListener()
    {
      return listener;
    }
  }

  private CamelContext                    context;
  private boolean                         started;

  private Map<ListenerEndpoint, Consumer> consumers = new HashMap<ListenerEndpoint, Consumer>();

  private void init() throws Exception
  {
    RouteBuilder builder = new RouteBuilder()
    {
      @Override
      public void configure()
      {
        from(DESTINATION.API_SYNC.getURI()).to(DESTINATION.DB_SYNC.getURI());
      }
    };

    context = new DefaultCamelContext();
    context.addRoutes(builder);
  }

  @Override
  public void start() throws Exception
  {
    init();
    context.start();
    for (Entry<ListenerEndpoint, Consumer> e : consumers.entrySet())
    {
      if (e.getValue() == null)
      {
        e.setValue(createConsumer(e.getKey()));
      }
      e.getValue().start();
    }
    started = true;
  }

  @Override
  public void stop() throws Exception
  {
    for (Entry<ListenerEndpoint, Consumer> e : consumers.entrySet())
    {
      e.getValue().stop();
      e.setValue(null);
    }
    context.stop();
    context = null;
    started = false;
  }

  @Override
  public boolean isStarted()
  {
    return started;
  }

  public void addListener(final MessageListener listener, final DESTINATION destination) throws Exception
  {
    ListenerEndpoint listenerEndpoint = new ListenerEndpoint(listener, destination);

    if (started)
    {
      Consumer consumer = createConsumer(listenerEndpoint);
      consumers.put(listenerEndpoint, consumer);
      consumer.start();
    }
    else
    {
      consumers.put(listenerEndpoint, null);
    }
  }

  private Consumer createConsumer(final ListenerEndpoint listenerEndpoint) throws Exception
  {
    DESTINATION destination = listenerEndpoint.getDestination();
    Consumer consumer = getEndpoint(destination).createConsumer(new Processor()
    {
      @Override
      public void process(final Exchange exchange) throws Exception
      {
        listenerEndpoint.getListener().onMessage(exchange.getIn());
      }
    });
    return consumer;
  }

  public Endpoint getEndpoint(final DESTINATION ep)
  {
    // for easy load testing
    // String uri = ep.getURI() + new Random().nextInt();
    String uri = ep.getURI();
    return context.getEndpoint(uri);
  }

  public void removeListener(final MessageListener listener)
  {
    consumers.remove(listener);
  }
}
