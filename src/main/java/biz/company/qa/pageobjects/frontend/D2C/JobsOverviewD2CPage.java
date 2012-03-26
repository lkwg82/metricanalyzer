/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;

/**
 * This is the jobs overview page that shows a picture and a quote of an employee and our mission.
 * 
 
 */
public class JobsOverviewD2CPage extends PageD2CAbstract {
    public static final String PAGE_ID = "-C2834";

    public JobsOverviewD2CPage(final PageAbstract page) {
        super(page);
    }

    @Override
    public void validate() {
        getAssertHelper().matchesUrlRegex(".*" + PAGE_ID + ".*");
    }

    public JobOffersD2CPage goToVacancies() {
        getSeleniumHelper().clickAndWait("//*[@id='navi-5-2835']");
        return new JobOffersD2CPage(this);
    }
}
