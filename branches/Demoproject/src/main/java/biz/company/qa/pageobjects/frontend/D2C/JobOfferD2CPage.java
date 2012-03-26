/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;

/**
 * This is the individual page that shows one job description with title, department, location and job offer text
 * 
 
 */
public class JobOfferD2CPage extends PageD2CAbstract {

    public JobOfferD2CPage(PageAbstract page) {
        super(page);
    }

    @Override
    public void validate() {

    }

    public String getTitle() {
        return s().getText("//h2");
    }

}
