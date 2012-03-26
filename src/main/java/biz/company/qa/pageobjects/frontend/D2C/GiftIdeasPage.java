/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;

/**
 * A crazy content page on the way to get gift certificates
 * 
 
 */
public class GiftIdeasPage extends PageD2CAbstract {
    public static final String PAGE_ID = "-C5735";
    public final static String LOCATOR_GIFT_CERTIFICATES = "link=Geschenkgutscheine";

    public GiftIdeasPage(PageAbstract page) {
        super(page);
    }

    @Override
    public void validate() {
        getAssertHelper().matchesUrlRegex(".*" + PAGE_ID + ".*");
    }

    /**
     * clicks on the "Gift certificates" button in the side navigation
     */
    public GiftCertificatesPage gotoGiftCertificates() {
        getSeleniumHelper().clickAndWait(LOCATOR_GIFT_CERTIFICATES);
        return new GiftCertificatesPage(this);
    }
}
