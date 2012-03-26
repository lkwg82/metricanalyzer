/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;

/**
 
 */
public class OpenNewShopSellPage extends PageD2CAbstract {

    /**
     * @param page
     *
     */
    public OpenNewShopSellPage(final PageAbstract page){
        super(page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.pageobjects.frontend.commons.ValidatableObject#validate()
     */
    @Override
    public void validate(){
        String xpathMask = "xpath=//a[contains(@href,'%s')]";

        {
            String xpath = String.format(xpathMask, "-C5479");
            String msg = "Link fehlt : zu Spreaducation ";
            getAssertHelper().isElementPresent(xpath, msg);
        }
        {
            String xpath = String.format(xpathMask, "-C5483");
            String msg = "Link fehlt : Spreaducation-Shop-Stories ";
            getAssertHelper().isElementPresent(xpath, msg);
        }
        {
            String xpath = String.format(xpathMask, "-C4172");
            String msg = "Link fehlt :Shop-Eröffnungsassistent ";
            getAssertHelper().isElementPresent(xpath, msg);
        }
    }

    /**
     * @return
     *
     */
    public ShopOpening1stPage goToShopOpeningAssistant(){
        String xpathMask = "xpath=//a[contains(@href,'%s')]";
        String xpath = String.format(xpathMask, "-C4172");

        getSeleniumHelper().clickAndWait(xpath);
        return new ShopOpening1stPage(this);
    }

}
