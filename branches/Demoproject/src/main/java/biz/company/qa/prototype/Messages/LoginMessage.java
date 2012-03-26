package biz.company.qa.prototype.Messages;


public final class LoginMessage extends ApiMessageAbstract
{
  private LoginMessage()
  {
    // ok
  }

  public static LoginMessage create(final String user, final String pass)
  {
    LoginMessage message = new LoginMessage();
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
