/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.selenium;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.selenium.Selenium;

import biz.company.qa.pageobjects.CONFIG_KEYS;
import biz.company.qa.pageobjects.Configuration;

/**
 
 */
public interface SeleniumControllerBackend {
    Selenium getSelenium();

    WebDriver getDriver();

    void start() throws Exception;

    void stop();

    /**
     * @param configuration
     */
    void configure(Configuration<CONFIG_KEYS> configuration);

    /**
     *
     */
    void init();
}
