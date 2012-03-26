package biz.company.qa.prototype.API;

import javax.naming.ServiceUnavailableException;

import biz.company.qa.prototype.Service;
import biz.company.qa.prototype.Bus.BusConnector;
import biz.company.qa.prototype.Bus.BusService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIService implements Service
{
  private static Logger log = LoggerFactory.getLogger(APIService.class);

  private boolean       started;
  private BusConnector  connector;
  private UserAPI       userApi;
  private ProductAPI    productApi;

  @Override
  public void start() throws Exception
  {
    log.info("starting api");
    started = true;
    userApi = new UserAPI(connector);
    productApi = new ProductAPI(connector);
  }

  @Override
  public void stop() throws Exception
  {
    log.info("stopping api");
    started = false;
    userApi = null;
    productApi = null;
  }

  @Override
  public boolean isStarted()
  {
    return started;
  }

  public void connectTo(final BusService bus)
  {
    connector = new BusConnector(bus);
  }

  void checkServiceStarted() throws ServiceUnavailableException
  {
    if (!isStarted())
    {
      throw new ServiceUnavailableException("service is not started");
    }
  }

  public UserAPI user() throws Exception
  {
    checkServiceStarted();
    return userApi;
  }

  public ProductAPI product() throws Exception
  {
    checkServiceStarted();
    return productApi;
  }
}
