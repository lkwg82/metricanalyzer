/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;

/**
 
 */
public class SellPage extends PageD2CAbstract {

    /**
     * @param page
     *
     */
    public SellPage(final PageAbstract page){
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
            String xpath = String.format(xpathMask, "-C5782");
            String msg = "Link fehlt : 'Motive anbieten' ";
            getAssertHelper().isElementPresent(xpath, msg);
        }
        {
            String xpath = String.format(xpathMask, "-C5781");
            String msg = "Link fehlt : 'Shop eröffnen' ";
            getAssertHelper().isElementPresent(xpath, msg);
        }
        {
            String xpath = String.format(xpathMask, "-C5783");
            String msg = "Link fehlt : 'Kooperationen' ";
            getAssertHelper().isElementPresent(xpath, msg);
        }
    }

    /**
     * @return
     *
     */
    public OpenNewShopSellPage goToOpenNewShop(){

        String xpathMask = "xpath=//a[contains(@href,'%s')]";
        String xpath = String.format(xpathMask, "-C5781");

        getSeleniumHelper().clickAndWait(xpath);
        return new OpenNewShopSellPage(this);
    }

}
