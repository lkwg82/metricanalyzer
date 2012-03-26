/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import java.util.List;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.WebElementAbstract;
import biz.company.qa.pageobjects.utils.Regexer;
import biz.company.qa.pageobjects.utils.Regexer.MyMatcher;

/**
 
 */
public class MiniBasket extends WebElementAbstract {

  public static final String XPATH_DIV_ID_MINIBASKET = "//div[@id='minibasket']";
  public static final String XPATH_LINK_TO_CHECKOUT = "//div[@id='minibasket']//a";

  /**
   * @param pageFrom
   */
  public MiniBasket(final PageAbstract pageFrom) {
    super(pageFrom);
  }

  /*
   * (non-Javadoc)
   * 
   * @see biz.company.qa.pageobjects.IValidatableObject#validate()
   */
  @Override
  public void validate() {
    getAssertHelper().assertPresentAndVisible(XPATH_DIV_ID_MINIBASKET);
  }

  public int getArticleCount() {
    return Integer.valueOf(s().getText(XPATH_DIV_ID_MINIBASKET + "//*[@id='minibasket-count']"));
  }

  public Double getTotalPrice() {
    String price = s().getText(XPATH_DIV_ID_MINIBASKET + "//*[@id='minibasket-price']");
    price = price.replaceAll(",", ".");
    List<MyMatcher> matchList = new Regexer(price, "(\\d+[.,]\\d+)").findAll().getMatchList();

    return Double.valueOf(matchList.get(0).getMatch());
  }

  public boolean isEmpty() {
    // has link to checkout, when article count != 0
    return !getSeleniumHelper().isElementPresentAndVisible(MiniBasket.XPATH_LINK_TO_CHECKOUT);
  }

  //  /**
  //   * @return
  //   */
  //  public CheckoutStep1Page gotoCheckout() {
  //    getSeleniumHelper().clickAndWait(XPATH_LINK_TO_CHECKOUT);
  //    return new CheckoutStep1Page(getPageFrom());
  //  }

}
