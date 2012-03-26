package biz.company.qa.prototype.DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class UserDB
{
  private final Map<String, APIToken> tokenTable = new HashMap<String, APIToken>();
  private final Map<String, String>   userTable  = new HashMap<String, String>();

  public boolean addNewUser(final String user, final String pass)
  {
    if (getUserTable().containsKey(user))
    {
      return false;
    }
    else
    {
      getUserTable().put(user, pass);
      return true;
    }
  }

  public boolean checkLogin(final String user, final String pass)
  {
    boolean s = true || ((new Random().nextInt() % 2) != 0);
    return s && getUserTable().containsKey(user) && getUserTable().get(user).equals(pass);
  }

  public boolean removeUser(final String user)
  {
    if (getUserTable().containsKey(user))
    {
      getUserTable().remove(user);
      return true;
    }
    else
    {
      return false;
    }
  }

  public String getTokenForUser(final String user)
  {
    APIToken token = tokenTable.get(user);
    if ((token == null) || token.isExpired())
    {
      token = new APIToken(500);
      tokenTable.put(user, token);
    }
    else
    {
      token.update();
    }
    return token.getToken();
  }

  public Map<String, String> getUserTable()
  {
    return userTable;
  }

  public boolean removeToken(final String token)
  {
    cleanupTokens();
    String user = findUserByToken(token);
    if (user == null)
    {
      return false;
    }
    else
    {
      tokenTable.remove(user);
      return true;
    }
  }

  public boolean validateToken(final String token)
  {
    cleanupTokens();
    String user = findUserByToken(token);
    if (user == null)
    {
      return false;
    }
    else
    {
      tokenTable.get(user).update();
      return true;
    }
  }

  private void cleanupTokens()
  {
    List<String> removeList = new ArrayList<String>();
    for (Entry<String, APIToken> entry : tokenTable.entrySet())
    {
      if (entry.getValue().isExpired())
      {
        removeList.add(entry.getKey());
      }
    }

    for (String user : removeList)
    {
      tokenTable.remove(user);
    }
  }

  public String findUserByToken(final String token)
  {
    for (Entry<String, APIToken> entry : tokenTable.entrySet())
    {
      APIToken _token = entry.getValue();

      if (_token.getToken().equals(token))
      {
        return entry.getKey();
      }
    }
    return null;
  }
}
