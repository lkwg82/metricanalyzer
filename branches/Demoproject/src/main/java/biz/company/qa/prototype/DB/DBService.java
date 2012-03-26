package biz.company.qa.prototype.DB;

import javax.naming.ServiceUnavailableException;

import biz.company.qa.prototype.MessageListener;
import biz.company.qa.prototype.STATUS;
import biz.company.qa.prototype.Service;
import biz.company.qa.prototype.Bus.BusService;
import biz.company.qa.prototype.Bus.DESTINATION;
import biz.company.qa.prototype.Messages.ApiMessageAbstract;
import biz.company.qa.prototype.Messages.LoginMessage;
import biz.company.qa.prototype.Messages.NewUserMessage;
import biz.company.qa.prototype.Messages.ProductCreationMessage;
import biz.company.qa.prototype.Messages.RemoveUserMessage;
import biz.company.qa.prototype.Messages.TokenMessage;

import org.apache.camel.CamelException;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBService implements Service, MessageListener
{
  private static Logger   log       = LoggerFactory.getLogger(DBService.class);
  private final UserDB    userDB    = new UserDB();
  private final ProductDB productDB = new ProductDB();

  private boolean         started;
  private BusService      bus;

  @Override
  public void start() throws Exception
  {
    started = true;
  }

  @Override
  public void stop() throws Exception
  {
    started = false;
    bus.removeListener(this);
  }

  @Override
  public boolean isStarted()
  {
    return started;
  }

  public void connectTo(final BusService bus) throws Exception
  {
    this.bus = bus;
    bus.addListener(this, DESTINATION.DB_SYNC);
  }

  @Override
  public void onMessage(final Message message) throws Exception
  {
    checkServiceStarted();

    log.info("incoming message : " + message.getHeaders());

    if (message instanceof NewUserMessage)
    {
      handleNewUserRequest((NewUserMessage) message);
    }
    else if (message instanceof RemoveUserMessage)
    {
      handleRemoveUserRequest((RemoveUserMessage) message);
    }
    else if (message instanceof LoginMessage)
    {
      handleLoginRequest((LoginMessage) message);
    }
    else if (message instanceof TokenMessage)
    {
      handleTokenRequest((TokenMessage) message);
    }
    else if (message instanceof ProductCreationMessage)
    {
      handleProductCreationRequest((ProductCreationMessage) message);
    }
    else if (message instanceof ApiMessageAbstract)
    {
      ApiMessageAbstract m = (ApiMessageAbstract) message;
      m.setStatus(STATUS.ERROR);
      m.setStatusMessage("message type \"" + m.getType() + "\" not handled");
    }
    else
    {
      throw new CamelException("can not handle this message:" + message.getHeaders() + " \n" + message);
    }
  }

  private void handleProductCreationRequest(final ProductCreationMessage message)
  {
    String token = message.getToken();
    if (userDB.validateToken(token))
    {
      String user = userDB.findUserByToken(token);
      productDB.addProduct(user, message.getProduct());
      message.setStatus(STATUS.OK);
    }
    else
    {
      message.setStatus(STATUS.NOT_OK);
      message.setStatusMessage("token expired");
    }
  }

  private void handleTokenRequest(final TokenMessage message)
  {
    if (message.isRemoveToken())
    {
      if (userDB.removeToken(message.getToken()))
      {
        message.setStatus(STATUS.OK);
      }
      else
      {
        message.setStatus(STATUS.NOT_OK);
        message.setStatusMessage("token expired");
      }
    }
    else
    {
      if (userDB.validateToken(message.getToken()))
      {
        message.setStatus(STATUS.OK);
      }
      else
      {
        message.setStatus(STATUS.NOT_OK);
        message.setStatusMessage("token expired");
      }
    }
  }

  private void checkServiceStarted() throws ServiceUnavailableException
  {
    if (!isStarted())
    {
      throw new ServiceUnavailableException("service is not started");
    }
  }

  private void handleRemoveUserRequest(final RemoveUserMessage message)
  {
    log.info("remove user");
    if (userDB.removeUser(message.getUser()))
    {
      message.setStatus(STATUS.OK);
    }
    else
    {
      message.setStatus(STATUS.NOT_OK);
    }

  }

  private void handleLoginRequest(final LoginMessage m)
  {
    log.info("login user");
    if (m.getPass() == null)
    {
      if (userDB.getUserTable().containsKey(m.getUser()))
      {
        m.setStatus(STATUS.OK);
      }
      else
      {
        m.setStatus(STATUS.NOT_OK);
      }
    }
    else
    {
      if (userDB.checkLogin(m.getUser(), m.getPass()))
      {
        m.setStatus(STATUS.OK);
        m.setBody(userDB.getTokenForUser(m.getUser()), String.class);
      }
      else
      {
        m.setStatus(STATUS.NOT_OK);
      }
    }
  }

  private void handleNewUserRequest(final NewUserMessage m)
  {
    log.info("add new user");
    if (userDB.addNewUser(m.getUser(), m.getPass()))
    {
      m.setStatus(STATUS.OK);
    }
    else
    {
      m.setStatus(STATUS.NOT_OK);
      m.setStatusMessage("already existing");
    }
  }
}
