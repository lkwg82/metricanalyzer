/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

import org.testng.Assert;

import com.thoughtworks.selenium.Selenium;


/**
 * a Visitor, which stores the location, where we came from and where we will return after do some actions
 * <p/>
 * e.g.
 * <ol>
 * <li>login</li>
 * <li>buy something</li>
 * <li>use Designservice</li>
 * <li>
 * <ul>
 * <li>go to the basket</li>
 * <li>remove fist and last item</li>
 * <li>go back, where u came from</li>
 * </ul>
 * </li>
 * <li>buy another ....</li>
 * </ol>
 * 
 
 */
public class OtherPageVisitor {

  private final Selenium selenium;

  /**
   * @param s
   */
  public OtherPageVisitor(final Selenium s) {
    selenium = s;
  }

  /**
   * @param iAction
   * @throws Exception
   */
  public void doSomethingAndGoBackAfterThat(final IAction iAction) throws Exception {
    final String currentLocation = selenium.getLocation();
    iAction.doSomeAction();
    selenium.open(currentLocation);
    Assert.assertEquals(selenium.getLocation(), currentLocation, "sorry could go back to former location at "
        + currentLocation + " we are now at " + selenium.getLocation() + " dont know why");
  }

}
