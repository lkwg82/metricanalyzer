package biz.company.qa.prototype;

import java.awt.Color;

import javax.mail.MessagingException;

import biz.company.qa.prototype.API.APIService;
import biz.company.qa.prototype.Bus.BusService;
import biz.company.qa.prototype.DB.DBService;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class APIServiceTest
{
  private SystemContext context;
  private BusService    bus;
  private APIService    api;
  private DBService     db;

  @BeforeMethod
  public void setup() throws Exception
  {
    bus = new BusService();
    api = new APIService();
    api.connectTo(bus);
    db = new DBService();
    db.connectTo(bus);
    context = new SystemContext();
    context.//
    addService(bus).//
    addService(api).//
    addService(db).//
    start();
  }

  @AfterMethod
  public void after() throws Exception
  {
    context.stop();
  }

  @Test
  public void login() throws Exception
  {
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);

    Assert.assertNotNull(api.user().login(login, pass));
    AssertJUnit.assertTrue(api.user().login(login, pass).length() > 10);
  }

  @Test
  public void loginWrong() throws Exception
  {
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);

    String token = api.user().login(login, pass);
    Assert.assertNotNull(token);
    AssertJUnit.assertTrue(token.length() > 10);
  }

  @Test
  public void validateToken() throws Exception
  {
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);

    String token = api.user().login(login, pass);

    AssertJUnit.assertTrue(api.user().validateToken(token));
  }

  @Test
  public void validateExpiredToken() throws Exception
  {
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);

    String token = api.user().login(login, pass);

    Thread.sleep(800);

    AssertJUnit.assertFalse(api.user().validateToken(token));
  }

  @Test
  public void invalidateToken() throws Exception
  {
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);

    String token = api.user().login(login, pass);

    AssertJUnit.assertTrue(api.user().invalidateToken(token));
  }

  @Test
  public void invalidateExpiredToken() throws Exception
  {
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);

    String token = api.user().login(login, pass);

    Thread.sleep(800);
    AssertJUnit.assertFalse(api.user().invalidateToken(token));
  }

  @Test
  public void createNewUser() throws Exception
  {
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);
  }

  @Test(expectedExceptions = MessagingException.class)
  public void createNewUserTwice() throws Exception
  {
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);
    api.user().createNewUser(login, pass);
  }

  @Test
  public void removeUser() throws Exception
  {
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);
    api.user().removeUser(login);
  }

  @Test(expectedExceptions = MessagingException.class)
  public void removeUserTwice() throws Exception
  {
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);
    api.user().removeUser(login);
    api.user().removeUser(login);
  }

  @Test(expectedExceptions = MessagingException.class)
  public void removeUserNotExisting() throws Exception
  {
    String login = "user";

    api.user().removeUser(login);
  }

  @Test
  public void userExisting() throws Exception
  {
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);
    AssertJUnit.assertTrue(api.user().existUser(login));
  }

  @Test
  public void userNotExisting() throws Exception
  {
    String login = "user";
    AssertJUnit.assertFalse(api.user().existUser(login));
  }

  @Test
  public void createDummyProduct() throws Exception
  {
    Product product = ProductBuilder.builder().color(Color.black).type(Type.HOSE).size(SIZE.XXXXXL).build();
    String login = "user";
    String pass = "pass";

    api.user().createNewUser(login, pass);
    String token = api.user().login(login, pass);

    Thread.sleep(200);

    AssertJUnit.assertTrue(api.product().createProduct(token, product));
  }

}
