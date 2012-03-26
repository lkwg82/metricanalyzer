package biz.company.qa.prototype.Messages;


public final class NewUserMessage extends ApiMessageAbstract
{
  private NewUserMessage()
  {
    // ok
  }

  public static NewUserMessage create(final String user, final String pass)
  {
    NewUserMessage message = new NewUserMessage();
    message.setHeader("user", user);
    message.setHeader("pass", pass);
    return message;
  }

  public String getUser()
  {
    return getHeader("user", String.class);
  }

  public String getPass()
  {
    return getHeader("pass", String.class);
  }
}
