/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.selenium;

import java.net.URL;

import biz.company.qa.pageobjects.CONFIG_KEYS;
import biz.company.qa.pageobjects.Configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.thoughtworks.selenium.Selenium;

/**
 
 */
public abstract class SeleniumControllerBackendAbstract implements SeleniumControllerBackend {

  /*
   * configs
   */
  protected String gridhost;
  protected String browser;
  protected URL startUrl;
  protected Integer gridport;
  private Configuration<CONFIG_KEYS> config;

  /*
   * results after starting
   */
  protected Selenium selenium;
  protected RemoteWebDriver driver;

  @Override
  public Selenium getSelenium() {
    return selenium;
  }

  @Override
  public WebDriver getDriver() {
    return driver;
  }

  @Override
  public void configure(final Configuration<CONFIG_KEYS> configuration) {

    config = configuration;
  }

  @Override
  public void init() {
    // set time-out and go
    //    selenium.setTimeout(config.getValue(CONFIG_KEYS.SELENIUM_TIMEOUT_DEFAULT));
    //    selenium.open(config.getValue(CONFIG_KEYS.SELENIUM_START_URL));

    // maximize and focus browser window
    //    selenium.windowFocus();
    //    selenium.windowMaximize();
  }
}
