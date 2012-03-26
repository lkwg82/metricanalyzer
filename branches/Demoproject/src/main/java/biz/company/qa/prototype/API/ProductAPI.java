package biz.company.qa.prototype.API;

import biz.company.qa.prototype.Product;
import biz.company.qa.prototype.Bus.BusConnector;
import biz.company.qa.prototype.Messages.ProductCreationMessage;
import biz.company.qa.prototype.Messages.ResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductAPI
{
  private final static Logger log = LoggerFactory.getLogger(ProductAPI.class);
  private final BusConnector  connector;

  public ProductAPI(final BusConnector connector)
  {
    this.connector = connector;
  }

  private void logWhenNotOk(final ResponseMessage response)
  {
    if (!response.isOk())
    {
      log.warn("status " + response.getStatus() + " " + response.getStatusMessage());
    }
  }

  public boolean createProduct(final String token, final Product product) throws Exception
  {
    ProductCreationMessage message = ProductCreationMessage.create(token, product);
    ResponseMessage response = connector.send(message);

    logWhenNotOk(response);
    return response.isOk();
  }
}
