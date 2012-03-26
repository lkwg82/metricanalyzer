package biz.company.qa.mail;

import java.util.List;

import javax.mail.MessagingException;

import junit.framework.Assert;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultCamelContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MailboxTest
{

  private final String        user     = "vmmail";
  private final String        host     = "vmmail.virtual4";
  private final String        password = "company";
  private final String        folder   = "vm04";

  private final String        sendTo   = folder + "@qa.company.info";

  private DefaultCamelContext camel;
  private Endpoint            destination;
  private Mailbox             mailbox;
  private Producer            producer;

  @BeforeTest
  public void before() throws Exception
  {
    camel = new DefaultCamelContext();
    camel.start();

    // sending
    String uri = String.format("smtp://%s@%s?password=%s", user, host, password);
    destination = camel.getEndpoint(uri);

    // receiving
    mailbox = new Mailbox(host, user, password, folder);

    // create a producer that can produce the exchange (= send the mail)
    producer = destination.createProducer();
    // start the producer
    producer.start();
  }

  @AfterTest
  public void after() throws Exception
  {
    camel.stop();
  }

  /**
   * send -> receive -> compare
   */
  @Test(expectedExceptions = MessagingException.class)
  public void simpleRoundTrip() throws Exception
  {

    Exchange exchange = destination.createExchange();
    Message in = exchange.getIn();
    in.setHeader("To", sendTo);
    in.setHeader("From", sendTo);
    in.setHeader("Subject", "Test");
    in.setBody("Hello World");

    // and let it go (processes the exchange by sending the email)
    producer.process(exchange);

    List<Email> mails = mailbox.wait(10 * 1000).forResults(new OnlineFilter().processOnlyLast(100).bodyTermForPlainText("Hello World"));

    Assert.assertTrue(mails.size() > 1);
    System.out.println(mails.get(1).getPlaintext());
    Assert.assertTrue(mails.get(1).getPlaintext().startsWith("Hello World"));
  }
}
