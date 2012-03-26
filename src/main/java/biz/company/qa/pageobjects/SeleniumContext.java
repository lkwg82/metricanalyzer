/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects;

import java.util.Set;

import biz.company.qa.infrastrastructure.selenium.SeleniumController;
import biz.company.qa.mail.Mailbox;
import biz.company.qa.mail.MailboxFactory;
import biz.company.qa.pageobjects.utils.CodeshotHelper;
import biz.company.qa.pageobjects.utils.ScreenshotHelper;
import biz.company.qa.pageobjects.utils.SeleniumHelper;

import com.thoughtworks.selenium.Selenium;

/**
 * context for SeleniumTests
 * 
 
 */
public class SeleniumContext {
  private Selenium selenium;
  private Configuration<CONFIG_KEYS> configuration;
  private ScreenshotHelper screenshotHelper;
  private SeleniumHelper seleniumHelper;
  private CodeshotHelper codeshotHelper;
  private SeleniumController controller;
  private Mailbox mailbox;
  private Set<String> shopIds;

  public Set<String> getShopIds() {
    return shopIds;
  }

  public void setShopIds(final Set<String> shopIds) {
    this.shopIds = shopIds;
  }

  public void init() {
    seleniumHelper = new SeleniumHelper(this);
    setScreenshotHelper(new ScreenshotHelper(this));
    codeshotHelper = new CodeshotHelper(this);
  }

  /**
   * @param selenium the selenium to set
   */
  public void setSelenium(final Selenium selenium) {
    this.selenium = selenium;
  }

  /**
   * @return the selenium
   */
  public Selenium getSelenium() {
    return selenium;
  }

  /**
   * @param configuration the configuration to set
   */
  public void setConfiguration(final Configuration<CONFIG_KEYS> configuration) {
    this.configuration = configuration;
  }

  /**
   * @return the configuration
   */
  public Configuration<CONFIG_KEYS> getConfiguration() {
    return configuration;
  }

  /**
   * @param screenshotHelper the screenshotHelper to set
   */
  public void setScreenshotHelper(final ScreenshotHelper screenshotHelper) {
    this.screenshotHelper = screenshotHelper;
  }

  /**
   * @return the screenshotHelper
   */
  public ScreenshotHelper getScreenshotHelper() {
    return screenshotHelper;
  }

  /**
   * @return the seleniumHelper
   */
  public SeleniumHelper getSeleniumHelper() {
    return seleniumHelper;
  }

  /**
   * @return
   */
  public CodeshotHelper getCodeshotHelper() {
    return codeshotHelper;
  }

  /**
   * @return the controller
   */
  public SeleniumController getController() {
    return controller;
  }

  /**
   * @param controller the controller to set
   */
  public void setController(final SeleniumController controller) {
    this.controller = controller;
  }

  /**
   * lazy instantion (on first use)
   * 
   * @return the mailbox
   */
  public Mailbox getMailbox() {
    if (mailbox == null) {
      mailbox = MailboxFactory.createInstance(configuration);
    }
    return mailbox;
  }

  /**
   * @param mailbox the mailbox to set
   */
  public void setMailbox(final Mailbox mailbox) {
    this.mailbox = mailbox;
  }
}
