package biz.company.qa.prototype;

import java.util.HashSet;
import java.util.Set;

import org.apache.camel.CamelException;

public class SystemContext implements Service
{
  private boolean      started  = false;
  private Set<Service> services = new HashSet<Service>();

  @Override
  public void start() throws Exception
  {
    if (isStarted())
    {
      throw new CamelException("already started");
    }
    else
    {
      started = true;

      for (Service service : services)
      {
        service.start();
      }
    }
  }

  @Override
  public void stop() throws Exception
  {
    if (isStarted())
    {
      started = false;

      for (Service service : services)
      {
        service.stop();
      }
    }
    else
    {
      throw new CamelException("not started");
    }
  }

  public SystemContext addService(final Service service) throws Exception
  {
    if (services.add(service))
    {
      if (started)
      {
        service.start();
      }
      else
      {
        // do nothing
      }
    }
    else
    {
      // TODO duplicate
    }
    return this;
  }

  @Override
  public boolean isStarted()
  {
    return started;
  }

  public Set<Service> getServices()
  {
    return services;
  }
}
