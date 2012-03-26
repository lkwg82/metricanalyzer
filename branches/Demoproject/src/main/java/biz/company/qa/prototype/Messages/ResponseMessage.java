package biz.company.qa.prototype.Messages;

import biz.company.qa.prototype.STATUS;

public class ResponseMessage extends ApiMessageAbstract
{
  public static ResponseMessage create(final STATUS status)
  {
    ResponseMessage message = new ResponseMessage();
    message.setHeader("status", status.getCode());
    return message;
  }

  public static ResponseMessage create(final STATUS status, final String statusMessage)
  {
    ResponseMessage message = new ResponseMessage();
    message.setHeader("status", status.getCode());
    message.setHeader("statusMsg", statusMessage);
    return message;
  }

  public boolean isOk()
  {
    return getStatus() == STATUS.OK;
  }

  public boolean isNotOk()
  {
    return getStatus() == STATUS.NOT_OK;
  }
}
