/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

import biz.company.qa.pageobjects.CONFIG_KEYS;
import biz.company.qa.pageobjects.PageValidationException;
import biz.company.qa.pageobjects.SeleniumContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.thoughtworks.selenium.Selenium;

/**
 
 */
public class SeleniumHelper {

  private final static Logger log = LoggerFactory.getLogger(SeleniumHelper.class);
  private final SeleniumContext context;

  /**
   * @param selenium
   */
  public SeleniumHelper(final SeleniumContext context) {
    this.context = context;
  }

  private Selenium s() {
    return context.getSelenium();
  }

  public SeleniumWaitHelper waitFor() {
    return waitFor(1000);
  }

  public SeleniumWaitHelper waitFor(final int timeout) {
    SeleniumWaitHelper waitHelper = new SeleniumWaitHelper(context);
    waitHelper.setTimeout(timeout);
    return waitHelper;
  }

  /**
   * Sleeps for the specified number of milliseconds
   */
  public final void pause(final int millisecs) {
    try {
      Thread.sleep(millisecs);
    } catch (final InterruptedException e) {
    }
  }

  public final boolean isElementPresentAndVisible(final String locator) {
    boolean present = false;
    present = s().isElementPresent(locator);
    if (present) {
      present = s().isVisible(locator);
    }
    return present;
  }

  /**
   * tries to click or throws Exception with errMsg
   * 
   * @param locator
   * @param errMsg
   */
  public final void clickAndWait(final String locator, final String errMsg) {
    if (s().isElementPresent(locator)) {
      s().click(locator);
      s().waitForPageToLoad(context.getConfiguration().getValue(CONFIG_KEYS.SELENIUM_TIMEOUT_DEFAULT));
    } else {
      Assert.fail(errMsg + "(locator=" + locator + ")");
    }
  }

  /**
   * tries to click or throws Exception with generic errMsg
   * 
   * @param locator
   */
  public void clickAndWait(final String locator) {
    clickAndWait(locator, "failed to click");
  }

  /**
   * @param selectLocator
   * @param optionLocator
   */
  public final void selectAndWait(final String selectLocator, final String optionLocator) {
    if (s().isElementPresent(selectLocator)) {
      s().select(selectLocator, optionLocator);
      s().waitForPageToLoad(context.getConfiguration().getValue(CONFIG_KEYS.SELENIUM_TIMEOUT_DEFAULT));
    } else {
      throw new PageValidationException("could not select (locator=" + selectLocator + ")");
    }

  }

  /**
   * @param locator
   * @param windowID
   */
  public final void clickAndWaitForPopup(final String locator, final String windowID) {
    clickAndWait(locator);
    waitFor().popup(windowID);
  }

  /**
   * Transfer a session (including login information) from one domain to another. To be called from the destination
   * domain. So the sessionId needs to be catched before going there.
   * 
   * @param targetURL The URL opened after calling this method
   * @param sessionId SessionId to store on the domain
   */
  public void transferSession(final String targetURL, final String sessionId) {
    s().open(targetURL);
    final String baseURL = targetURL.substring(0, targetURL.indexOf('/', 8));
    log.info("Session transfer: " + baseURL + "/tourist.php?sid=" + sessionId + "&targetUrl=" + targetURL);
    s().open(baseURL + "/tourist.php?sid=" + sessionId + "&targetUrl=" + targetURL);
  }

  public void goBackAndWaitForPageLoaded() {
    s().goBack();
    waitFor().pageToLoad();
  }

  /**
   * Removes the 'target' attribute on a given link. Good to prevent the browser from opening the page in a new window
   */
  public final void removeTargetAttribute(final String locator) {
    s().getEval("this.page().findElement(\"" + locator + "\").removeAttribute('target')");
  }
}
