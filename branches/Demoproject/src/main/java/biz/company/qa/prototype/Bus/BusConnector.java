package biz.company.qa.prototype.Bus;

import biz.company.qa.prototype.STATUS;
import biz.company.qa.prototype.Messages.ApiMessageAbstract;
import biz.company.qa.prototype.Messages.ResponseMessage;

import org.apache.camel.Message;

public class BusConnector implements Connector
{
  private BusHelper helper;

  public BusConnector(final BusService bus)
  {
    this.helper = new BusHelper(bus);
  }

  @Override
  public ResponseMessage send(final Message message) throws Exception
  {
    Message responseMessage = helper.sendMessage(DESTINATION.API_SYNC, message);
    if (responseMessage == null)
    {
      return ResponseMessage.create(STATUS.UNDEF);
    }
    else
    {
      ApiMessageAbstract apiMessage = (ApiMessageAbstract) message;
      ResponseMessage response = ResponseMessage.create(apiMessage.getStatus(), apiMessage.getStatusMessage());
      response.setBody(message.getBody());
      return response;
    }
  }
}
