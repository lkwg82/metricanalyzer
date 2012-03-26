package biz.company.qa.prototype;

import org.apache.camel.Message;

public interface MessageListener
{
  void onMessage(Message message) throws Exception;
}
