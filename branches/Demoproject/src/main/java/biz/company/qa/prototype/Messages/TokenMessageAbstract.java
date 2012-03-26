package biz.company.qa.prototype.Messages;

public class TokenMessageAbstract extends ApiMessageAbstract
{

  public TokenMessageAbstract(final String token)
  {
    setHeader("token", token);
  }

  public String getToken()
  {
    return getHeader("token", String.class);
  }

}
