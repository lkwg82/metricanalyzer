/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C.userarea;

import org.testng.Assert;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.frontend.D2C.PageD2CAbstract;

/**
 * The Public Profile
 * 
 
 */
public class ViewMyPublicProfile extends PageD2CAbstract {

    /**
     * @param page
     */
    public ViewMyPublicProfile(PageAbstract page) {
        super(page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.pageobjects.frontend.D2C.userarea.UserAreaPageAbstract#validate()
     */
    @Override
    public void validate() {
        Assert.assertTrue(s().getLocation().contains("/user/"));
    }

}
