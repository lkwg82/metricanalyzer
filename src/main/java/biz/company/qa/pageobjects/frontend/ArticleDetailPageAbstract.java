/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.SeleniumContext;

/**
 
 */
public abstract class ArticleDetailPageAbstract extends PageAbstract {

  private static final String ADD_TO_BASKET = "//input[@type='button' or @type='submit' and contains(@class,'button')]";

  public ArticleDetailPageAbstract(final PageAbstract pageFrom) {
    super(pageFrom);

  }

  /**
   * @param context
   */
  public ArticleDetailPageAbstract(final SeleniumContext context) {
    super(context);
  }

  public abstract void setFirstSize();

  //    public CheckoutStep1Page putArticleInBasket() {
  //        getSeleniumHelper().clickAndWait(ADD_TO_BASKET); // TODO: Open ticket for this, we need a unified name tag
  //        return new CheckoutStep1Page(this);
  //    }

  public ArticleDetailPageAbstract putArticleInBasketExpectingError() {
    s().click(ADD_TO_BASKET); // TODO: Open ticket for this, we need a unified name tag
    return this;
  }

  public String getArticleId() {
    String articleId = s().getAttribute("//input[@name='article' and @type='hidden']@value");
    if (articleId.isEmpty()) {
      // company
      String formId = s().getAttribute("//form[contains(@id, 'addToBasket')]@id");
      articleId = formId.substring("addToBasket".length() - 1);
    }
    return articleId;
  }

  public String getProductId() {
    return s().getAttribute("//input[@name='product' and @type='hidden']@value");
  }
}
