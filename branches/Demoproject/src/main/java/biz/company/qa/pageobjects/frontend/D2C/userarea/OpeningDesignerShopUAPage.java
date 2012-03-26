/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C.userarea;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.frontend.D2C.StartD2CPage;

/**
 
 */
public class OpeningDesignerShopUAPage extends UserAreaPageAbstract {

    private static final String XPATH_FORM_TNC_DECLINE = "xpath=//form[@id='TNCDecline']";
    private static final String XPATH_FORM_TNC_ACCEPT = "xpath=//form[@id='TNCAccept']";

    public OpeningDesignerShopUAPage(final PageAbstract page) {
        super(page);
    }

    @Override
    public void validate() {
        getAssertHelper().isElementPresent(XPATH_FORM_TNC_DECLINE, "missing form to decline the terms and conditions");
        getAssertHelper().isElementPresent(XPATH_FORM_TNC_ACCEPT, "missing form to accept the terms and conditions");
    }

    /**
     * ??
     */
    public StartD2CPage acceptTermsAndCondition() {
        getSeleniumHelper().clickAndWait(XPATH_FORM_TNC_ACCEPT + "//input[@type='submit']");
        return new StartD2CPage(this);
    }

    @Override
    public UserAreaSideBar getSideBar() {
        // TODO add when needed
        return null;
    }

}
