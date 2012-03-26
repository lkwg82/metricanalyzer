/*
 * (C) 2001 - 2011 company.biz AG
 */ 
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;

/**
 
 *
 */
public class FAQD2CPage extends PageD2CAbstract {

    public FAQD2CPage(PageAbstract page) {
        super(page);
    }

    @Override
    public void validate() {

    }

    public String getTitle() {
        return s().getText("//h1");
    }

}
