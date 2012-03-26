/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;

/**
 * Shows a list of current offered jobs at Company
 * 
 
 */
public class JobOffersD2CPage extends PageD2CAbstract {
    public static final String PAGE_ID = "-C2835";

    public JobOffersD2CPage(final PageAbstract page) {
        super(page);
    }

    @Override
    public void validate() {
        getAssertHelper().matchesUrlRegex(".*" + PAGE_ID + ".*");
    }

    public JobOfferD2CPage goToFirstJobOffer() {
        getSeleniumHelper().clickAndWait("//table[@class='job-offers']//a[@class!='rss']");
        return new JobOfferD2CPage(this);
    }

}
