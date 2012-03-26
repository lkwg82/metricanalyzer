package biz.company.qa.prototype.API;

import javax.mail.MessagingException;

import biz.company.qa.prototype.Bus.BusConnector;
import biz.company.qa.prototype.Messages.LoginMessage;
import biz.company.qa.prototype.Messages.NewUserMessage;
import biz.company.qa.prototype.Messages.RemoveUserMessage;
import biz.company.qa.prototype.Messages.ResponseMessage;
import biz.company.qa.prototype.Messages.TokenMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAPI
{
  private final static Logger log = LoggerFactory.getLogger(UserAPI.class);
  private BusConnector        connector;

  public UserAPI(final BusConnector connector)
  {
    this.connector = connector;
  }

  /**
   * retrieves a token
   * 
   * @param user
   * @param pass
   * @return
   * @throws Exception
   */
  public String login(final String user, final String pass) throws Exception
  {
    log.info("trying to login as " + user);
    LoginMessage message = LoginMessage.create(user, pass);
    ResponseMessage response = connector.send(message);
    log.info("logged in as " + user);
    logWhenNotOk(response);
    if (!response.isOk())
    {
      throw new MessagingException("creating new user failed : " + response.getStatusMessage());
    }
    return response.getBody(String.class);
  }

  public void createNewUser(final String login, final String pass) throws Exception
  {
    NewUserMessage message = NewUserMessage.create(login, pass);
    ResponseMessage response = connector.send(message);

    logWhenNotOk(response);
    if (!response.isOk())
    {
      throw new MessagingException("creating new user failed : " + response.getStatusMessage());
    }
  }

  public void removeUser(final String login) throws Exception
  {
    RemoveUserMessage message = RemoveUserMessage.create(login);
    ResponseMessage response = connector.send(message);

    logWhenNotOk(response);
    if (!response.isOk())
    {
      throw new MessagingException("removing user " + login + " failed : " + response.getStatusMessage());
    }

  }

  private void logWhenNotOk(final ResponseMessage response)
  {
    if (!response.isOk())
    {
      log.warn("status " + response.getStatus() + " " + response.getStatusMessage());
    }
  }

  public boolean existUser(final String login) throws Exception
  {
    LoginMessage message = LoginMessage.create(login, null);
    ResponseMessage response = connector.send(message);

    logWhenNotOk(response);
    return response.isOk();
  }

  public boolean validateToken(final String token) throws Exception
  {
    TokenMessage message = TokenMessage.create(token, false);
    ResponseMessage response = connector.send(message);

    logWhenNotOk(response);
    return response.isOk();
  }

  public boolean invalidateToken(final String token) throws Exception
  {
    TokenMessage message = TokenMessage.create(token, true);
    ResponseMessage response = connector.send(message);

    logWhenNotOk(response);
    return response.isOk();
  }

}
