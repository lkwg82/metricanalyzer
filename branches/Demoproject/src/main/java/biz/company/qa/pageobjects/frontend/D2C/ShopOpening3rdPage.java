/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;

/**
 * This step contains settings for shop name, language, currency, URL
 * 
 
 */
public class ShopOpening3rdPage extends PageD2CAbstract {

    public ShopOpening3rdPage(PageAbstract page) {
        super(page);
    }

    /**
     * Returns the sub-domain part of the shop URL. In case you didn't already changed it, it's the shop number.
     */
    public String getShopURL() {
        return s().getValue("id=shopURL");
    }

    /**
     * Click on the save button to set shop name and language. Returns next step of shop opening.
     */
    public ShopOpening4thPage save() {
        getSeleniumHelper().clickAndWait("id=submit_button");

        return new ShopOpening4thPage(this);
    }

    @Override
    public void validate() {
        getAssertHelper().matchesUrlRegex(".*-C4174", "url sollte auf -C4174 enden : " + s().getLocation());
    }

}
