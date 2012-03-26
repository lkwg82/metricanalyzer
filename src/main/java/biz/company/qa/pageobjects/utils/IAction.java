/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

/**
 * a wrapping-code interface
 * <p/>
 * a workaround for an anonymous function
 * <p/>
 * here a an example from class CheckoutUtil
 * 
 * <pre>
 * public void emptyBasket() {
 *     new OtherPageVisitor(selenium.get()).doSomethingAndGoBackAfterThat(
 * 
 *     new IAction() {
 * 
 *         &#064;Override
 *         public void doSomeAction() {
 * 
 *             s().click(&quot;//div[@id='minibasket']//a&quot;);
 * 
 *             String first = &quot;xpath=(&quot; + CommonXPaths.BASKET_DELETE_ITEM_BUTTON + &quot;)[1]&quot;;
 *             while (s().isElementPresent(first)) {
 *                 provider.clickAndWait(first);
 *             }
 *             assertEquals(getSizeOfBasket(), 0, &quot;Should not be greater than zero , empty means 0&quot;);
 *             log.info(&quot;emptied the basket&quot;);
 *         }
 *     });
 * }
 * </pre>
 * 
 
 */
public interface IAction {

    /**
     * 
     */
    void doSomeAction() throws Exception;

}
