/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects;

/**
 
 */
public enum CONFIG_KEYS {
    /**
     * basic selenium config
     */
    SELENIUM_TIMEOUT_DEFAULT, SELENIUM_BROWSER, SELENIUM_START_URL,
    /**
     * selenium grid configuration
     */
    SELENIUM_GRID_HOST, SELENIUM_GRID_PORT, SELENIUM_GRID_VERSION,
    /**
     *
     */
    SELENIUM_START_SERVER, SELENIUM_PROXY_HOST, SELENIUM_PROXY_PORT, SELENIUM_TIMEOUT_SHORT,
    /**
     * keep it in mind for switching sites
     */
    BASE_URL,
    /**
     * which vm we are using?
     */
    VMID, API_VMID,
    /**
     * which top level domain
     */
    TLD,
    /**
     * Mailbox settings
     */
    MAILHOST, MAILUSER, MAILPASSWORD,
    /**
     * switch for using webdriver or old selenium rc
     */
    USE_WEBDRIVER,
    /**
     * use local instance to see what happens in realtime
     */
    USE_LOCAL,
    /**
     * shop types
     */
    STANDARD_SHOP_ID, PREMIUM_SHOP_ID, DESIGNER_SHOP_ID;
}
