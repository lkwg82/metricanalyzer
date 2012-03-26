package biz.company.qa.prototype.Messages;

import biz.company.qa.prototype.STATUS;

import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;

public class ApiMessageAbstract extends DefaultMessage implements Message
{
  protected ApiMessageAbstract()
  {
    setHeader("messageType", getClass().getSimpleName());
  }

  public String getType()
  {
    return getHeader("messageType", String.class);
  }

  public STATUS getStatus()
  {
    return STATUS.parse(getHeader("status", Integer.class));
  }

  public void setStatus(final STATUS status)
  {
    setHeader("status", status.getCode());
  }

  public void setStatus(final String status)
  {
    setHeader("status", status);
  }

  public void setStatusMessage(final String statusMessage)
  {
    setHeader("statusMsg", statusMessage);
  }

  public String getStatusMessage()
  {
    return getHeader("statusMsg", String.class);
  }
}
