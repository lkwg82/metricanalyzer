/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects;


/**
 * represents a whole page, different to some navigational elements
 * 
 
 */
public abstract class PageAbstract extends WebElementAbstract {

  public PageAbstract(final SeleniumContext context) {
    super(context);
  }

  /**
   * @param page - another page I come from
   */
  public PageAbstract(final PageAbstract page) {
    super(page);
  }

  public void captureScreenShot(final String path) {
    s().captureScreenshot(path);
  }
}
