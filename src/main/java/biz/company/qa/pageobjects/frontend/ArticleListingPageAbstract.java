/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.SeleniumContext;
import biz.company.qa.pageobjects.Type;
import biz.company.qa.pageobjects.frontend.D2C.TopD2CNavigation;

import org.apache.commons.lang.NotImplementedException;

/**
 
 */
public abstract class ArticleListingPageAbstract extends PageAbstract {

  // private static final String LOGOUT = null;

  /**
   * If its not the first page you entered, use this constructor
   */
  public ArticleListingPageAbstract(final PageAbstract pageFrom) {
    super(pageFrom);
  }

  public ArticleListingPageAbstract(final SeleniumContext context) {
    super(context);
  }

  public abstract ArticleDetailPageAbstract gotoFirstArticleDetailPage();

  //    public CheckoutStep1Page goToCheckoutViaMenu() {
  //        getSeleniumHelper().clickAndWait("//*[@id='basketStatus']//a");
  //        return new CheckoutStep1Page(this);
  //    }
  //
  //    public CheckoutStep1Page goToCheckoutViaMiniBasket() {
  //        if (s().isElementPresent("//*[@id='userChart']//a")) {
  //            getSeleniumHelper().clickAndWait("//*[@id='userChart']//a"); // SHOP
  //        } else {
  //            getSeleniumHelper().clickAndWait("//[@id='basketStatus']//a"); // company
  //        }
  //        return new CheckoutStep1Page(this);
  //    }
  //
  //    public CheckoutStep1Page putArticleInBasket() {
  //        // go to detail page of first article
  //        ArticleDetailPageAbstract detail = gotoFirstArticleDetailPage();
  //
  //        // select size
  //        detail.setFirstSize();
  //
  //        // put article in basket
  //        CheckoutStep1Page checkout = detail.putArticleInBasket();
  //
  //        return checkout;
  //    }

  public abstract LoginPage goToLoginPage();

  /**
   * Get The Top Navigation for the given type
   * 
   * @param type
   * @return
   */
  public ITopNavigation getTopNavigation(final Type type) {
    switch (type) {
      case MARKETPLACE:
        return new TopD2CNavigation(this);
      default:
        throw new NotImplementedException("Not Implemented for " + type);
    }
  }

}
