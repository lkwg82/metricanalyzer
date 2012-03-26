package biz.company.qa.prototype.Messages;

public final class RemoveUserMessage extends ApiMessageAbstract
{
  private RemoveUserMessage()
  {
    // ok
  }

  public static RemoveUserMessage create(final String user)
  {
    RemoveUserMessage message = new RemoveUserMessage();
    message.setHeader("user", user);
    return message;
  }

  public String getUser()
  {
    return getHeader("user", String.class);
  }
}
