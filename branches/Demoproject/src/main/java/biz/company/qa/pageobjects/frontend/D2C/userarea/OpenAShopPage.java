/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C.userarea;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.frontend.D2C.PageD2CAbstract;

import org.testng.Assert;

/**
 * As Stanard user in the sidebar you can select to open a shop! This is the page where you land in.
 * 
 
 */
public class OpenAShopPage extends PageD2CAbstract {

    /**
     * @param page
     */
    public OpenAShopPage(PageAbstract page) {
        super(page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.pageobjects.frontend.D2C.userarea.UserAreaPageAbstract#validate()
     */
    @Override
    public void validate() {
        Assert.assertTrue(s().getLocation().contains("C3131"));
    }

}
