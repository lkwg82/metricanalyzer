package biz.company.qa.prototype.Messages;

public final class TokenMessage extends TokenMessageAbstract
{
  private TokenMessage(final String token)
  {
    super(token);
  }

  public static TokenMessage create(final String token, final boolean remove)
  {
    TokenMessage message = new TokenMessage(token);
    message.setHeader("removeToken", remove);
    return message;
  }

  public boolean isRemoveToken()
  {
    return getHeader("removeToken", boolean.class);
  }
}
