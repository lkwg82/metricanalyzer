/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.frontend.LoginPage;
import biz.company.qa.pageobjects.frontend.D2C.userarea.OverviewUAPage;

import org.apache.commons.lang.NotImplementedException;
import org.testng.Assert;
import org.testng.SkipException;

/**
 
 */
public class LoginD2CPage extends PageD2CAbstract implements LoginPage
{

  public static final String  LINK_LOGIN   = "navi-0-2105";
  public static final String  LINK_LOGOUT  = "navi-0-2019";

  public static final String  URL          = "-C2108";
  public static final String  LOGIN_BUTTON = "btnLogin";
  private static final String ERROR_BOX    = "//div[contains(@class,'error') and contains(@class,'message')]";

  public LoginD2CPage(final PageAbstract page)
  {
    super(page);
  }

  @Override
  public void validate()
  {
    getAssertHelper().isElementPresent("xpath=//input[@id='login']", "where is our login-field?");
    getAssertHelper().isElementPresent("xpath=//input[@id='password']", "where is our password-field?");
    getAssertHelper().isElementPresent("xpath=//input[attribute::id='btnLogin']", "where is our login-button?");
    getAssertHelper().isElementPresent("xpath=//input[@id='email']", "where is our email-field?");
    getAssertHelper().isElementPresent("xpath=//input[@id='bntSignUp']", "where is our signup-button?");
  }

  /**
   * login and return article listing page of marketplace
   */
  @Override
  public ArticleListingD2CPage login(final String login, final String email, final String password)
  {

    // either ... or ...
    Assert.assertTrue(((login == null) && (email != null)) || ((login != null) && (email == null)), "one should be null and the other not : XOR");
    Assert.assertTrue((login == null) || !login.contains("@"), "the username should not contain an @-sign, is this an email address?");
    Assert.assertTrue((email == null) || email.contains("@"), "an email contains normally an @-sign");
    Assert.assertNotNull(password, "we actually need the password");

    if (email == null)
    {
      // use username as login
      s().type("login", login);
    }
    else
    {
      // use email as login
      s().type("login", email);
    }

    s().type("password", password);
    s().uncheck("saveLogin");

    getSeleniumHelper().clickAndWait(LOGIN_BUTTON);

    getSeleniumHelper().waitFor().elementPresentAndVisible("xpath=//a[@id='" + LINK_LOGIN + "' or @id='" + LINK_LOGOUT + "']");

    // Check for errors
    if (!getSeleniumHelper().isElementPresentAndVisible(LINK_LOGOUT))
    {
      if (email == null)
      {
        throw new SkipException("Login failed, wrong login data (" + login + "," + password + ")");
      }
      else
      {
        throw new SkipException("Login failed, wrong login data (" + email + "," + password + ")");
      }
    }

    return new ArticleListingD2CPage(this);
  }

  public LoginD2CPage loginWithWrongData()
  {
    s().type("login", "InvalidLogin" + System.currentTimeMillis());
    s().type("password", "InvalidPassword" + System.currentTimeMillis());
    getSeleniumHelper().clickAndWait(LOGIN_BUTTON);
    return this;
  }

  /**
   * Use the form to sign up via email
   */
  @Override
  public OverviewUAPage register(final String email)
  {
    s().type("email", email);
    getSeleniumHelper().clickAndWait("bntSignUp");

    return new OverviewUAPage(this);
  }

  /*
   * (non-Javadoc)
   * @see biz.company.qa.pageobjects.frontend.LoginPage#registerWithEmailAndUsername(java.lang.String, java.lang.String)
   */
  @Override
  public void registerWithEmailAndUsername(final String email, final String username)
  {
    throw new NotImplementedException("not implemented yet");
  }

  /**
   * @return
   */
  public boolean isTopLoginLinkVisible()
  {
    getSeleniumHelper().waitFor(15000).elementPresentAndVisible("navi-0-2105");
    return getSeleniumHelper().isElementPresentAndVisible("navi-0-2105");
  }

  /**
   * Login with:
   * 
   * <pre>
   *  login: ""
   *  password: ""
   * </pre>
   * 
   * @return
   */
  public LoginD2CPage loginWithNoData()
  {
    s().type("login", "");
    s().type("password", "");
    getSeleniumHelper().clickAndWait(LOGIN_BUTTON);
    return this;

  }

  public boolean isErrorMessageVisible()
  {
    return getSeleniumHelper().isElementPresentAndVisible(ERROR_BOX);
  }

  /**
   * @param login
   * @return
   */
  public LoginD2CPage loginWithInvalidPassword(final String login)
  {
    s().type("login", login);
    s().type("password", "");
    getSeleniumHelper().clickAndWait(LOGIN_BUTTON);
    return this;
  }
}
