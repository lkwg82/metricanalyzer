/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import java.awt.event.KeyEvent;
import java.util.Random;

import biz.company.qa.infrastrastructure.designer.CNG;
import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.frontend.ArticleDetailPageAbstract;
import biz.company.qa.pageobjects.utils.Price;

/**
 
 */
public class ArticleDetailD2CPage extends ArticleDetailPageAbstract {

  public static final String MP_PRODUCT_DETAILS_PAGE = "//*[@class='article_link']";
  public static final String XPATH_SIZE_LIST = "//ul[@id='SizesBarEntries']";
  public static final String XPATH_SIZES = "//a[starts-with(@id,'sizeEntry')]";
  private static final String PERSONALISE_PRODUCT = "//*[@id='marketplace-product-description']/div/a";

  public ArticleDetailD2CPage(final PageAbstract pageFrom) {
    super(pageFrom);
  }

  @Override
  public void validate() {
    getAssertHelper().isElementPresent("//*[@id='marketplace-product-description']",
        "Not on article detail page on marketplace");
  }

  public ArticleListingD2CPage gotoDepartment(final D2CMenu.DEPARTMENT department) {
    getSeleniumHelper().clickAndWait(department.getNaviId());
    return new ArticleListingD2CPage(this);
  }

  @Override
  public void setFirstSize() {
    getSeleniumHelper().waitFor(2000).elementPresentAndVisible(XPATH_SIZES);
    s().click(XPATH_SIZES);
    getSeleniumHelper().waitFor(2000).elementVisible(
        "//*[@id='selectedSize' and contains(@class, 'currentSelection')]");
  }

  public Price getArticlePrice() {
    final String text = s().getText("//span[contains(@class,'price_total')]");
    final Price result = Price.parse(text);
    return result;
  }

  public String getArticleName() {
    return s().getText("//*[@id='marketplace_product_images']//h2");
  }

  public String getArticleDescription() {
    return s().getText("//*[@id='marketplace_product_images']//p");
  }

  //    public CheckoutStep1Page addToBasket() {
  //        getSeleniumHelper().clickAndWait("//a[@id='addToBasketLink']//input");
  //        return new CheckoutStep1Page(this);
  //    }

  public String getDesignerName() {
    return s().getText("//*[@id='user_info']//a");
  }

  public String getProductType() {
    return s().getText("//*[@id='marketplace-product-description']/h2");
  }

  public boolean isSizeErrorVisible() {
    return getSeleniumHelper().isElementPresentAndVisible("id=size_error");
  }

  public void setQuantity(final int count) throws InterruptedException {
    final String old = s().getText("//*[@id='priceTotal']");
    s().type("quantity", String.valueOf(count));
    s().focus("quantity");
    s().fireEvent("quantity", "keypress");
    s().keyPress("quantity", Integer.toString(KeyEvent.VK_ENTER));

    while (true) {
      if (getSeleniumHelper().isElementPresentAndVisible("//*[@id='priceTotal']")
          && !s().getText("//*[@id='priceTotal']").equals(old)) {
        break;
      }
      Thread.sleep(1000);
    }
  }

  public void setDifferentColor() {
    final int count = s().getXpathCount("//*[@id='ColorBarEntries']/li/a").intValue();
    final int colorEntry = new Random().nextInt(count);
    s().click("xpath=(//*[@id='ColorBarEntries']/li/a)[" + colorEntry + "]");
    getSeleniumHelper().waitFor().elementPresentAndVisible(
        "//*[@id='marketplace_product_images']//img[contains(@src, 'producttypecolor')]");
  }

  public String getColor() {
    final String src = s().getAttribute(
        "//*[@id='marketplace_product_images']//img[contains(@src, 'producttypecolor')]/@src");
    return src.split("/producttypecolor/")[1].split("/")[0];

  }

  public void setQuantity4() {
    s().focus("quantity");
    s().type("quantity", "");
    s().keyPressNative(Integer.toString(KeyEvent.VK_4));
    s().fireEvent("quantity", "keyup");
    getContext().getSeleniumHelper().pause(5000);

  }

  public boolean isPersonaliseProductVisible() {
    return getSeleniumHelper().isElementPresentAndVisible(PERSONALISE_PRODUCT);
  }

  public CNG gotoPersonaliseProduct() {
    getSeleniumHelper().clickAndWait(PERSONALISE_PRODUCT);
    final CNG designer = new CNG(getContext(), "Confomat");
    designer.waitFor().fullyLoaded();
    return designer;
  }

}
