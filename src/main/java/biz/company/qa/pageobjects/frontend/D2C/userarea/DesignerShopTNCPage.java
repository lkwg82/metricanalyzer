/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C.userarea;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.frontend.D2C.PageD2CAbstract;

/**
 * represents the page, when the user has to accept the 'Terms and Conditions' after became premium
 * 
 
 */
public class DesignerShopTNCPage extends PageD2CAbstract {

    // private static final String xpathTermsAndConditions = "xpath=//a[contains(@href,'-C3579')";
    private static final String xpathConfirmTNCButton = "xpath=//form[@id='TNCAccept']/fieldset/input[3]";

    /**
     * @param page
     *
     */
    public DesignerShopTNCPage(final PageAbstract page){
        super(page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.pageobjects.pages.commons.IValidatableObject#validate()
     */
    @Override
    public final void validate(){
        getAssertHelper().isElementPresent(xpathConfirmTNCButton, "missing button to confirm terms and conditions");
    }

    /**
     *
     */
    public final void confirm(){
        getSeleniumHelper().clickAndWait(xpathConfirmTNCButton);
    }
}
