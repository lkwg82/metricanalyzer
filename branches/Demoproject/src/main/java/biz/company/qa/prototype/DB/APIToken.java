package biz.company.qa.prototype.DB;

import java.util.UUID;

public class APIToken
{
  @Override
  public String toString()
  {
    return "APIToken [token=" + token + ", expiresRange=" + expiresRange + ", expires=" + expires + "]";
  }

  private final String token = UUID.randomUUID().toString();
  private long         expiresRange;
  private long         expires;

  public APIToken(final long expires)
  {
    this.expiresRange = expires;
    update();
  }

  public void update()
  {
    expires = System.currentTimeMillis() + expiresRange;
  }

  public boolean isExpired()
  {
    return System.currentTimeMillis() >= expires;
  }

  public String getToken()
  {
    return token;
  }
}
