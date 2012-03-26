/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

import java.util.concurrent.TimeUnit;

import biz.company.qa.pageobjects.CONFIG_KEYS;
import biz.company.qa.pageobjects.SeleniumContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

/**
 * contains waiter-methods and can be extendy by more specialCase-waiting methods
 * 
 
 */
public class SeleniumWaitHelper {
  private final static Logger log = LoggerFactory.getLogger(SeleniumWaitHelper.class);

  private final SeleniumContext context;
  private int timeout;
  private String timeoutStr;

  /**
   * @param context
   */
  public SeleniumWaitHelper(final SeleniumContext context) {
    this.context = context;
    setTimeout(10000);
  }

  private Selenium s() {
    return context.getSelenium();
  }

  /**
   * wait until ..
   * 
   * @param locator
   */
  public final void elementVisible(final String locator) {

    Assert.assertTrue(s().isElementPresent(locator), "element not present " + locator + " on page "
        + s().getLocation() + ". abort check for visibility.");

    specialConditionElseTimeout(new ICondition() {

      @Override
      public boolean check() {
        return s().isVisible(locator);
      }

      @Override
      public String getErrorMessage() {
        return "element not visible " + locator + " on page " + s().getLocation();
      }
    });
  }

  /**
   * wait until ..
   * 
   * @param text
   */
  public final void textPresent(final String text) {

    specialConditionElseTimeout(new ICondition() {

      @Override
      public boolean check() {
        return s().isTextPresent(text);
      }

      @Override
      public String getErrorMessage() {
        return "text does not appeared: " + text;
      }
    });
  }

  /**
   * wait until ..
   * 
   * @param text
   */
  public final void textNotPresentAnymore(final String text) {

    specialConditionElseTimeout(new ICondition() {

      @Override
      public boolean check() {
        return !s().getBodyText().contains(text);
      }

      @Override
      public String getErrorMessage() {
        return "text does not appeared: " + text;
      }
    });
  }

  /**
   * wait until ..
   * 
   * @param locator
   */
  public final void elementPresent(final String locator) {
    elementPresent(locator, false);
  }

  /**
   * wait until ..
   * 
   * @param locator
   */
  public final void elementPresentWithReloads(final String locator) {
    elementPresent(locator, true);
  }

  /**
   * wait until ..
   * 
   * @param locator
   */
  private void elementPresent(final String locator, final boolean reload) {

    specialConditionElseTimeout(new ICondition() {

      @Override
      public boolean check() {
        if (reload) {
          String location = s().getLocation();
          s().open(location);
          s().waitForPageToLoad(context.getConfiguration().getValue(CONFIG_KEYS.SELENIUM_TIMEOUT_DEFAULT));
        }
        return s().isElementPresent(locator);
      }

      @Override
      public String getErrorMessage() {
        return "element not present " + locator + " on page " + s().getLocation();
      }
    });
  }

  /**
   * wait until ..
   * 
   * @param locator
   */
  public final void elementNotPresent(final String locator) {

    specialConditionElseTimeout(new ICondition() {

      @Override
      public boolean check() {
        return !s().isElementPresent(locator);
      }

      @Override
      public String getErrorMessage() {
        return "element is present " + locator + " on page " + s().getLocation();
      }
    });
  }

  /**
   * wait until ..
   * 
   * @param locator
   */
  public final void elementPresentAndVisible(final String locator) {
    elementPresent(locator);
    elementVisible(locator);
  }

  /**
   * wait until ..
   * 
   * @param locator
   */
  public final void elementNotPresentOrNotVisible(final String locator) {
    specialConditionElseTimeout(new ICondition() {
      @Override
      public boolean check() {
        if (!s().isElementPresent(locator)) {
          return true;
        }
        try {
          return !s().isVisible(locator);
        } catch (SeleniumException se) {
          // timing problem if the first remote call was done before the element vanished
          return true;
        }
      }

      @Override
      public String getErrorMessage() {
        return "element is present or visible " + locator + " on page " + s().getLocation();
      }
    });
  }

  /**
   * wait for a condition to be come true or throw
   * 
   * @param iCondition
   */
  public void specialConditionElseTimeout(final ICondition iCondition) {
    // TODO: the condition evalution may result in an NPE while
    // waiting for a condition e.g. if the page reloads
    // better in Selenium 2?
    // wrapped for Selenium reason issue

    new Waiter(timeout, TimeUnit.MILLISECONDS).forCondition(new ICondition() {

      @Override
      public String getErrorMessage() {
        return iCondition.getErrorMessage();
      }

      @Override
      public boolean check() {
        try {
          return iCondition.check();
        } catch (NullPointerException e) {
          log.debug("Ignoring exception: " + e.getMessage());
          return false;
        }
      }
    });
  }

  /**
   * waits until text appears (length of text is greater zero)
   * 
   * @param locator
   */
  public final void textNonEmpty(final String locator) {

    Assert.assertTrue(s().isElementPresent(locator), "element not present " + locator + " on page "
        + s().getLocation() + ". abort check for visibility.");

    specialConditionElseTimeout(new ICondition() {

      @Override
      public boolean check() {
        return !s().getText(locator).isEmpty();
      }

      @Override
      public String getErrorMessage() {
        return "element still empty " + locator + " on page " + s().getLocation();
      }
    });
  }

  /**
   * simply wrapping {@link Selenium#waitForPopUp(windowId, timeoutStr)}
   * 
   * @param windowID
   */
  public void popup(final String windowID) {
    s().waitForPopUp(windowID, timeoutStr);
  }

  /**
   * simply wrapping {@link Selenium#waitForPageToLoad(TimeoutString)}
   */
  public void pageToLoad() {
    s().waitForPageToLoad(timeoutStr);
  }

  /**
   * @param timeout
   */
  public final void setTimeout(final int timeout) {
    this.timeout = timeout;
    timeoutStr = String.valueOf(timeout);
  }
}
