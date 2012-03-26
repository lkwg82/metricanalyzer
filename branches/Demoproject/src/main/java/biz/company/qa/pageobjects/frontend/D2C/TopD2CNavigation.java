/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.SeleniumContext;
import biz.company.qa.pageobjects.WebElementAbstract;
import biz.company.qa.pageobjects.frontend.ArticleListingPageAbstract;
import biz.company.qa.pageobjects.frontend.DesignerPage;
import biz.company.qa.pageobjects.frontend.ITopNavigation;
import biz.company.qa.pageobjects.frontend.D2C.D2CMenu.DEPARTMENT;
import biz.company.qa.pageobjects.frontend.D2C.userarea.OverviewUAPage;

/**
 * main navigation bar
 * 
 
 */
public class TopD2CNavigation extends WebElementAbstract implements ITopNavigation {

  private final static String xpathMask = "xpath=//*[contains(@id,'navi')]//a[contains(@id,'navi') and contains(@href,'%s')]";

  private final static String XPATH_CreateYourOwn = String.format(xpathMask, "-C59");
  private final static String XPATH_SELL = String.format(xpathMask, "-C5780");
  public final static String XPATH_YourAccount = String.format(xpathMask, "-C4");

  public static final String MY_ACCOUNT = "navi-1-5";
  public static final String SELL = "navi-1-56";
  public static final String BUY = "navi-1-2";
  public final static String COUNTRY_SELECT = "//*[@id='countrySelection']/select";

  /**
   * @param page
   */
  public TopD2CNavigation(final PageAbstract page) {
    super(page);
  }

  /**
   * @param context
   */
  public TopD2CNavigation(final SeleniumContext context) {
    super(context);
  }

  /*
   * (non-Javadoc)
   * 
   * @see biz.company.qa.pageobjects.frontend.commons.ValidatableObject#validate()
   */
  @Override
  public void validate() {
    getAssertHelper().isElementPresent(XPATH_CreateYourOwn, "where is 'create your own ..' link?");
    getAssertHelper().isElementPresent(XPATH_SELL, "where is 'XPATH_SELL' link?");
    getAssertHelper().isElementPresent(XPATH_YourAccount, "where is 'my account' link?");
  }

  /**
   * @return loginPage or OverviewPage
   */
  public OverviewUAPage gotoUserAreaOverviewPage() {
    getSeleniumHelper().clickAndWait(MY_ACCOUNT);
    return new OverviewUAPage(getPageFrom());
  }

  //  public <T extends UserAreaPageAbstract> T gotoRedirectedUAOverviewPage(final Class<T> clazz) {
  //    getSeleniumHelper().clickAndWait(UserAreaPageAbstract.XPATH_LINK_MY_ACCOUNT);
  //    return redirectToPageType(clazz);
  //  }

  /**
   * @return
   */
  public DesignerPage gotoDesignYourselfWithDesignerPage() {
    getSeleniumHelper().clickAndWait(XPATH_CreateYourOwn);
    return new DesignerPage(getPageFrom());
  }

  /**
   * Change the language/country. Use the label, for e.g. Suomi
   */
  public void setLanguage(final String label) {
    final String selectedLanguage = s().getSelectedLabel(COUNTRY_SELECT);
    if ((selectedLanguage != null) && !selectedLanguage.equals(label)) {
      getSeleniumHelper().selectAndWait(COUNTRY_SELECT, "label=" + label);
    }
  }

  //  public CheckoutStep1Page goToBasket() throws PageValidationException {
  //    getSeleniumHelper().waitFor().elementPresentAndVisible(MiniBasket.XPATH_DIV_ID_MINIBASKET);
  //    MiniBasket miniBasket = new MiniBasket(getPageFrom());
  //
  //    if (!miniBasket.isEmpty()) {
  //      return miniBasket.gotoCheckout();
  //    } else {
  //      throw new PageValidationException("Nothing in the Basket");
  //    }
  //  }

  @Override
  public ArticleListingPageAbstract logout() {
    getSeleniumHelper().waitFor(15000).elementPresentAndVisible("navi-0-2019");
    getSeleniumHelper().clickAndWait("navi-0-2019");
    StartD2CPage start = new StartD2CPage(getPageFrom());
    start.gotoDepartment(DEPARTMENT.MEN);
    return new ArticleListingD2CPage(getPageFrom());
  }

  /**
   *
   */
  public StartD2CPage gotoMarketPlace() {
    getSeleniumHelper().clickAndWait("navi-1-2");
    return new StartD2CPage(getPageFrom());

  }

  //  public OpeningDesignerShopUAPage gotoUserAreaOverviewPageExpectingOpenDesignerShopPage() {
  //    getSeleniumHelper().clickAndWait(MY_ACCOUNT);
  //    return new OpeningDesignerShopUAPage(getPageFrom());
  //  }
}
