/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.frontend.DesignerPage;

/**
 * This is a content page that shows different types of vouchers varying in amount (15€, 25€, ...) and delivery method
 * (mail or email)
 * 
 
 */
public class GiftCertificatesPage extends PageD2CAbstract {
    public static final String PAGE_ID = "-C6351";
    public final static String LOCATOR_15EUR_EMAIL = "link=15 € Gutschein (E-Mail)";

    public GiftCertificatesPage(PageAbstract page) {
        super(page);
    }

    @Override
    public void validate() {
        getAssertHelper().matchesUrlRegex(".*" + PAGE_ID + ".*");
    }

    /**
     * Choose 15€ gift certificate that is send via email (currently only works in German)
     */
    public DesignerPage get15EuroCertificateByEmail() {
        getSeleniumHelper().clickAndWait(LOCATOR_15EUR_EMAIL);
        return new DesignerPage(this);
    }
}
