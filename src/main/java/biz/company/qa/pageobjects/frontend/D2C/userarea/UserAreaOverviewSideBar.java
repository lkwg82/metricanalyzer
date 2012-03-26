/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C.userarea;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.frontend.DesignerPage;

/**
 * The Sidebar on the OverviewPage. Dashboard
 * 
 
 */
public class UserAreaOverviewSideBar extends UserAreaSideBar {

  private static final String SEE_MY_ORDERS = "//li[@id='ql_my_orders']/a";
  private static final String CREATE_AND_BUY_PRODUCT = "//li[@id='ql_create_product']/a";
  private static final String UPLOAD_A_DESIGN = "//li[@id='ql_upload_design']/a";
  private static final String OPEN_A_SHOP = "//li[@id='ql_create_shop']/a";
  private static final String VIEW_MY_PUBLIC_PROFILE = "//li[@id='ql_public_profile']/a";
  private static final String MY_PUBLISHED_DESIGNS = "//div[@id='quicklinks']//a[contains(., 'zu meinen veröffentlichten Motiven')]";
  private static final String MY_DESIGNS = "//li[@id='ql_designs']/a";
  private static final String MY_FINANCIAL_OVERVIEW = "//li[@id='ql_finance_overview']/a";
  private static final String EDIT_MY_PROFILE = "//li[@id='ql_edit_profile']/a";
  private static final String CREATE_A_PRODUCT = "//li[@id='ql_create_product']/a";
  private static final String SEE_MY_SHOP_OVERVIEW = "//li[@id='ql_shops_overview']/a";
  private static final String VIEW_MY_SHOP_ORDERS = "//li[@id='ql_shop_orders']/a";
  private static final String SEND_A_NEWSLETTER = "//li[@id='ql_shop_newsletter']/a";

  /**
   * @param page
   */
  public UserAreaOverviewSideBar(final PageAbstract page) {
    super(page);
  }
  //
  //    public MyOrdersPage gotoSeeMyOrders() {
  //        getSeleniumHelper().clickAndWait(SEE_MY_ORDERS);
  //        return new MyOrdersPage(getPageFrom());
  //    }

  public DesignerPage gotoCreateAndBuyProductPage() {
    getSeleniumHelper().clickAndWait(CREATE_AND_BUY_PRODUCT);
    return new DesignerPage(getPageFrom());
  }
  //
  //    public UploadDesignsPage gotoUploadADesignPage() {
  //        getSeleniumHelper().clickAndWait(UPLOAD_A_DESIGN);
  //        return new UploadDesignsPage(getPageFrom());
  //    }

  public OpenAShopPage gotoOpenAShopPage() {
    getSeleniumHelper().clickAndWait(OPEN_A_SHOP);
    return new OpenAShopPage(getPageFrom());
  }

  public ViewMyPublicProfile gotoViewMyPublicProfile() {
    getSeleniumHelper().clickAndWait(VIEW_MY_PUBLIC_PROFILE);
    return new ViewMyPublicProfile(getPageFrom());
  }
  //
  //    public MyDesignsPage gotoMyPublishedDesigns() {
  //        getSeleniumHelper().clickAndWait(MY_PUBLISHED_DESIGNS);
  //        return new MyDesignsPage(getPageFrom());
  //    }
  //
  //    public MyDesignsPage gotoMyDesigns() {
  //        getSeleniumHelper().clickAndWait(MY_DESIGNS);
  //        return new MyDesignsPage(getPageFrom());
  //    }
  //
  //    public StatisticsPage gotoMyFinancialOverview() {
  //        getSeleniumHelper().clickAndWait(MY_FINANCIAL_OVERVIEW);
  //        return new StatisticsPage(getPageFrom());
  //
  //    }
  //
  //    public AboutMePage gotoEditMyProfile() {
  //        getSeleniumHelper().clickAndWait(EDIT_MY_PROFILE);
  //        return new AboutMePage(getPageFrom());
  //    }

  /**
   * Will check for popup and close the popup
   */
  public void checkCreateAProduct() {
    s().click(CREATE_A_PRODUCT);
    getSeleniumHelper().waitFor().popup("confomat");
    s().selectWindow("confomat");
    s().close();
    s().selectWindow(null);
  }
  //
  //    public ShopsOverviewPage gotoSeeMyShopOverview() {
  //        getSeleniumHelper().clickAndWait(SEE_MY_SHOP_OVERVIEW);
  //        return new ShopsOverviewPage(getPageFrom());
  //    }
  //
  //    public StatisticsOrdersPage gotoMyShopOrders() {
  //        getSeleniumHelper().clickAndWait(VIEW_MY_SHOP_ORDERS);
  //        return new StatisticsOrdersPage(getPageFrom());
  //    }
  //
  //    public ShopNewsLetterPage gotoSendANewsletter() {
  //        getSeleniumHelper().clickAndWait(SEND_A_NEWSLETTER);
  //        return new ShopNewsLetterPage(getPageFrom());
  //    }

}
