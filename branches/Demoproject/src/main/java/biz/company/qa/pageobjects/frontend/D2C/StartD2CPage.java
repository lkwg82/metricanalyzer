/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.PageValidationException;
import biz.company.qa.pageobjects.SeleniumContext;
import biz.company.qa.pageobjects.frontend.D2C.D2CMenu.DEPARTMENT;

import org.testng.Assert;

/**
 
 */
public class StartD2CPage extends PageD2CAbstract {

  public static final String URL = "/";
  public final static String LOCATOR_TOP_LOGIN_LINK = "navi-0-2105";
  public final static String LOCATOR_TOP_SELL = "navi-1-56";
  public final static String LOCATOR_TOP_MY_ACCOUNT = "navi-1-5";
  public static final String LOCATOR_LINK = "xpath=//a[@id='logo']";

  // private static final String TOP_LOGIN_LINK = "navi-0-2105";

  /**
   * @param selenium
   */
  public StartD2CPage(final SeleniumContext context) {
    super(context);
  }

  public StartD2CPage(final PageAbstract page) {
    super(page);
  }

  /*
   * (non-Javadoc)
   * 
   * @see biz.company.qa.pageobjects.PageAbstract#validate()
   */
  @Override
  public void validate() {

    try {
      Assert.assertEquals(new URI(s().getLocation()).getPath(), "/");
    } catch (final URISyntaxException e) {
      throw new PageValidationException(e);
    }
  }

  /**
   * Returns the page that has two options: login & register
   */
  public LoginD2CPage gotoLoginPage() {
    if (getLoginHelper().isLoggedIn()) {
      getLoginHelper().logout();
    }
    // is there a login button on the front page?
    getSeleniumHelper().waitFor().elementPresent(LOCATOR_TOP_LOGIN_LINK);
    assertTrue(s().isElementPresent(LOCATOR_TOP_LOGIN_LINK));
    assertTrue(s().isTextPresent("Login"));

    if (getLoginHelper().isLoggedIn()) {
      throw new PageValidationException("we are already logged in");
    } else {
      getSeleniumHelper().clickAndWait(LOCATOR_TOP_LOGIN_LINK);
      return new LoginD2CPage(this);
    }
  }

  /**
   * clicks on the "sell" button in the top navigation
   */
  public SellPage gotoSellPage() {
    getSeleniumHelper().clickAndWait(LOCATOR_TOP_SELL);
    return new SellPage(this);
  }

  /**
   * clicks on the "All gift ideas" button in the side navigation
   */
  public GiftIdeasPage gotoGiftIdeas() {
    getSeleniumHelper().clickAndWait(DEPARTMENT.GIFTS.getNaviId());
    return new GiftIdeasPage(this);
  }

  //    /**
  //     * clicks on the "my account" button in the top navigation and returns the UA overview page
  //     */
  //    public OverviewUAPage gotoUserAreaOverviewPage() {
  //        if (getLoginHelper().isLoggedIn()) {
  //            getSeleniumHelper().clickAndWait(TopD2CNavigation.MY_ACCOUNT);
  //            return new OverviewUAPage(this);
  //        } else {
  //            throw new PageValidationException("please login before accessing ");
  //        }
  //
  //    }

  public D2CMenu getCategoriesOf(final D2CMenu.DEPARTMENT department) {
    return new D2CMenu(this, department);
  }

  public ArticleListingD2CPage gotoDepartment(final D2CMenu.DEPARTMENT department) {
    getSeleniumHelper().clickAndWait(department.getNaviId());
    return new ArticleListingD2CPage(this);
  }

  /**
   * To search on the marketplace. TODO: should be available for other pages too (not all)
   */
  public ArticleListingD2CPage search(final String query) {
    s().type("navigation-search-box", query);
    getSeleniumHelper().clickAndWait("//input[@value='Suchen']");
    return new ArticleListingD2CPage(this);
  }

  /**
   * Clicks on the Jobs link in the footer
   */
  public JobsOverviewD2CPage gotoJobs() {
    getSeleniumHelper().clickAndWait("//a[contains(@href,'-C2834')]");
    return new JobsOverviewD2CPage(this);
  }

  public FAQD2CPage gotoFAQPage() {
    getSeleniumHelper().clickAndWait("link=FAQ");
    return new FAQD2CPage(this);
  }
  //
  //  /**
  //   * navigates to the Shop
  //   *
  //   * @param shopID
  //   */
  //  public ArticleListingShopPage gotoShop(final String shopId) {
  //    s().open("/shop.php?sid=" + shopId);
  //    return new ArticleListingShopPage(this);
  //  }

}
