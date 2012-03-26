/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import static org.testng.Assert.assertEquals;

import biz.company.qa.pageobjects.PageAbstract;

/**
 
 */
public class BasketPage extends PageD2CAbstract {

    public static final String XPATH_OPEN_BASKET = "//div[@id='minibasket']//a";
    public static final String XPATH_BASKET_DELETE_ITEM_BUTTON = "//div[@id='basketItems']//input[@class='button delete']";

    /**
     * @param page
     */
    public BasketPage(final PageAbstract page) {
        super(page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.pageobjects.pages.commons.IValidatableObject#validate()
     */
    @Override
    public void validate() {
        getAssertHelper().matchesUrlRegex(".*-C2001");
    }

    /**
     * remove all items from the basket
     */
    public void empty() {
        int size = size();
        if (size > 0) {
            getLog().info("removing " + size() + " items");
            final String first = "xpath=(" + BasketPage.XPATH_BASKET_DELETE_ITEM_BUTTON + ")[1]";
            while (s().isElementPresent(first)) {
                getSeleniumHelper().clickAndWait(first);
                getLog().info("removed one item");
            }
            assertEquals(size(), 0, "Should not be greater than zero , empty means 0");
            getLog().info("emptied the basket");
        } else {
            getLog().info("baskeet was already empty");
        }
    }

    /**
     * count of items
     * 
     * @return
     */
    public int size() {
        final String id = "minibasket-count";
        getSeleniumHelper().waitFor().elementVisible(id);
        final String count = s().getText(id);
        return Integer.parseInt(count);
    }

}
