/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C.userarea;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.WebElementAbstract;

/**
 * This is the sub navigation element for the User Area: Overview, Designs, Products, Shops, Statistics, Account
 * settings
 * 
 
 */
public class UserAreaNavigation extends WebElementAbstract {

  public static final String URL_USER_AREA_ACCOUNT_SETTINGS = "-C162";
  public static final String URL_USER_AREA_NEWS = "-C3830";
  public static final String URL_USER_AREA_PRODUKTE = "-C2344";
  public static final String URL_USER_AREA_STATISTICS = "-C241";

  public static final String XPATH_NAVI_LEVEL_2 = "//div[@id='navi-level-2']";
  public static final String XPATH_PRODUCTS = XPATH_NAVI_LEVEL_2 + "//a[@id='navi-2-2340']";
  public static final String XPATH_DESIGNS = XPATH_NAVI_LEVEL_2 + "//a[@id='navi-2-254']";
  public static final String XPATH_SHOPS = XPATH_NAVI_LEVEL_2 + "//a[@id='navi-2-34']";
  public static final String XPATH_ACCOUNT_SETTINGS = XPATH_NAVI_LEVEL_2 + "//a[@id='navi-2-30']";
  private static final String STATISTICS = "navi-2-234";
  private static final String OVERVIEW = "navi-2-4";

  /**
   * @param accountSummaryPage
   */
  public UserAreaNavigation(final PageAbstract page) {
    super(page);
  }
  //
  //    /**
  //     * Clicks "Products" on Subnavigation. Returns Shops overview page.
  //     */
  //    public ProductsOverviewPage gotoProducts() {
  //        getLog().info("Going to User Area -> Products");
  //        getSeleniumHelper().clickAndWait(XPATH_PRODUCTS);
  //        return new ProductsOverviewPage(getPageFrom());
  //    }
  //
  //    /**
  //     * @return
  //     */
  //    public final MyDesignsPage goToDesigns() {
  //        getLog().info("Going to User Area -> Designs");
  //        getSeleniumHelper().clickAndWait(UserAreaNavigation.XPATH_DESIGNS);
  //        getLog().info("You are now on User Area -> Designs");
  //        return new MyDesignsPage(getPageFrom());
  //    }
  //
  //    /**
  //     * Clicks "Shops" on Subnavigation. Returns Shops overview page.
  //     */
  //    public ShopsOverviewPage gotoShops() {
  //        getLog().info("Going to User Area -> Shops");
  //        getSeleniumHelper().clickAndWait(UserAreaNavigation.XPATH_SHOPS);
  //        return new ShopsOverviewPage(getPageFrom());
  //    }
  //
  //    /**
  //     * Clicks "Account Settings" on Subnavigation. Returns User Data settings page.
  //     */
  //    public final UserDataPage goToAccountSettings() {
  //        getLog().info("Going to User Area -> Account Settings");
  //        getSeleniumHelper().clickAndWait(UserAreaNavigation.XPATH_ACCOUNT_SETTINGS);
  //        return new UserDataPage(getPageFrom());
  //    }

  @Override
  public final void validate() {
    {
      // checking occurence of links
      String xpath = "xpath=//a[contains(@href,'" + OverviewUAPage.PAGE_ID + "')]";
      String message = "where is the link for 'Übersicht' ?";
      getAssertHelper().isElementPresent(xpath, message);
    }
    //    {
    //      String xpath = "xpath=//a[contains(@href,'" + MyDesignsPage.PAGE_ID + "')]";
    //      String message = "where is the link for 'Motive' ?";
    //      getAssertHelper().isElementPresent(xpath, message);
    //    }
    {
      String xpath = "xpath=//a[starts-with(@id,'navi-') and contains(@href,'"
          + UserAreaNavigation.URL_USER_AREA_PRODUKTE + "')]";
      String message = "where is the link for 'Produkte' ?";
      getAssertHelper().isElementPresent(xpath, message);
    }
    {
      String xpath = "xpath=//a[starts-with(@id,'navi-') and contains(@href,'"
          + UserAreaNavigation.URL_USER_AREA_STATISTICS + "')]";
      String message = "where is the link for 'Statistiken' ?";
      getAssertHelper().isElementPresent(xpath, message);
    }

    {
      String xpath = "xpath=//a[starts-with(@id,'navi-') and contains(@href,'"
          + UserAreaNavigation.URL_USER_AREA_ACCOUNT_SETTINGS + "')]";
      String message = "where is the link for 'Kontoeinstellungen' ?";
      getAssertHelper().isElementPresent(xpath, message);
    }

    {
      String xpath = "xpath=//a[starts-with(@id,'navi-') and contains(@href,'"
          + UserAreaNavigation.URL_USER_AREA_NEWS + "')]";
      String message = "where is the link for 'News' ?";
      getAssertHelper().isElementPresent(xpath, message);
    }
  }
  //
  //    public StatisticsPage gotoStatistics() {
  //        getSeleniumHelper().clickAndWait(STATISTICS);
  //        return new StatisticsPage(getPageFrom());
  //    }

  public OverviewUAPage gotoOverViewPage() {
    getSeleniumHelper().clickAndWait(OVERVIEW);
    return new OverviewUAPage(getPageFrom());
  }
}
