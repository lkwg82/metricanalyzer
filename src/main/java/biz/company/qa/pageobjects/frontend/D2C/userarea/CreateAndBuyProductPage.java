/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C.userarea;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.frontend.D2C.PageD2CAbstract;

import org.testng.Assert;

/**
 
 */
public class CreateAndBuyProductPage extends PageD2CAbstract {

    /**
     * @param page
     */
    public CreateAndBuyProductPage(PageAbstract page) {
        super(page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.pageobjects.frontend.D2C.userarea.UserAreaPageAbstract#validate()
     */
    @Override
    public void validate() {
        Assert.assertTrue(s().getLocation().contains("C59"));
        Assert.assertTrue(s().isElementPresent("//embed"));
    }

}
