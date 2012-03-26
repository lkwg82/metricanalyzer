package biz.company.qa.prototype.Bus;

import biz.company.qa.prototype.Messages.ResponseMessage;

import org.apache.camel.Message;

public interface Connector
{
  ResponseMessage send(final Message message) throws Exception;
}
