/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;

/**
 
 */
public class RedirectPageTest {
  @Test(groups = { "helper" })
  public void test() throws Exception {
    Selenium selenium = mock(Selenium.class);
    when(selenium.getLocation()).thenReturn("/");
    SeleniumContext context = new SeleniumContext();
    context.setSelenium(selenium);
    MyStartPage startPage = new MyStartPage(context);

    // this page could be very different, so we need a mediator
    RedirectPage redirect = startPage.gotoNextPage();
    OtherPage otherPage = redirect.redirectToPageType(OtherPage.class);
    otherPage.beHappyBeFree();

  }

  public static class OtherPage extends PageAbstract {

    /**
     * @param pageFrom
     */
    public OtherPage(final PageAbstract pageFrom) {
      super(pageFrom);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.pageobjects.pages.commons.IValidatableObject#validate()
     */
    @Override
    public void validate() {
      // ok
    }

    public void beHappyBeFree() {
      // ok
    }
  }

}

class RedirectPage extends PageAbstract {

  /**
   * @param page
   */
  public RedirectPage(final PageAbstract page) {
    super(page);
  }

  @Override
  public void validate() {
    // not
  }
}

class MyStartPage extends PageAbstract {

  /**
   * @param selenium
   */
  public MyStartPage(final SeleniumContext context) {
    super(context);
  }

  public MyStartPage(final PageAbstract page) {
    super(page);
  }

  /*
   * (non-Javadoc)
   * 
   * @see biz.company.qa.pageobjects.pages.commons.IValidatableObject#validate()
   */
  @Override
  public void validate() {
    // ok
  }

  /**
   * @return
   */
  public RedirectPage gotoNextPage() {
    return new RedirectPage(this);
  }
}
