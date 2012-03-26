/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.ILoginHelper;
import biz.company.qa.pageobjects.SeleniumContext;
import biz.company.qa.pageobjects.utils.ICondition;
import biz.company.qa.pageobjects.utils.SeleniumHelper;

import com.thoughtworks.selenium.Selenium;

public class LoginHelperD2C implements ILoginHelper {

  public static final String LOCATOR_SHOP_TOP_LOGOUT = "//*[@id='userLogout']/a";

  private final SeleniumContext context;
  private final SeleniumHelper helper;

  /**
   * @param s
   */
  public LoginHelperD2C(final SeleniumContext context) {
    this.context = context;
    helper = context.getSeleniumHelper();
  }

  private Selenium s() {
    return context.getSelenium();
  }

  /**
   * logout user from userarea
   */
  @Override
  public final void logout() {
    helper.waitFor().elementPresent(PageD2CAbstract.LOCATOR_TOP_LOGOUT_LINK);
    helper.clickAndWait(PageD2CAbstract.LOCATOR_TOP_LOGOUT_LINK);
  }

  /**
   * @return
   */
  @Override
  public final boolean isLoggedIn() {

    // waitForLoginFormAccessible();

    String xpath = "xpath=//a[@id='" + PageD2CAbstract.LOCATOR_TOP_LOGIN_LINK + "']";

    return !s().isElementPresent(xpath);
  }

  /**
   * 
   */
  public void waitForLoginFormAccessible() {
    helper.waitFor().specialConditionElseTimeout(new ICondition() {

      @Override
      public boolean check() {
        return s().isElementPresent(PageD2CAbstract.LOCATOR_TOP_LOGIN_LINK)
            || s().isElementPresent(PageD2CAbstract.LOCATOR_TOP_LOGOUT_LINK);
      }

      @Override
      public String getErrorMessage() {
        return "login not present on page " + s().getLocation();
      }
    });
  }
}
