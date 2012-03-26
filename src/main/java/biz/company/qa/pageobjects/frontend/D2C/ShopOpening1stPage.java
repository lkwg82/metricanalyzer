/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;

/**
 * This step of opening a shop is asking for user data including name, address, email & password and is creating a new
 * account with a shop right after the first step. You don't have to finish the other steps, if you only want a new shop
 * partner account.
 * 
 
 */
public class ShopOpening1stPage extends PageD2CAbstract {

    public static final int CHECKCOMPANY = 128;

    public static final int FILLALL = 0;
    public static final int NOCITY = 16;

    public static final int NOCOMPANY = 256;
    public static final int NOFIRSTNAME = 1;
    public static final int NOPHONE = 32;
    public static final int NOSTREET = 4;
    public static final int NOSURNAME = 2;
    public static final int NOTERMS = 64;
    public static final int NOZIPCODE = 8;

    private static final String xpathTerms = "//form[@id='upgrade-to-partner']//input[@id='terms']";
    private static final String xpathTermsSubmit = "xpath=//form[@id='upgrade-to-partner']//input[@type='submit']";

    /**
     * @param page
     */
    public ShopOpening1stPage(final PageAbstract page) {
        super(page);
    }

    public void acceptTerms() {

        s().check(xpathTerms);

        getSeleniumHelper().clickAndWait(xpathTermsSubmit);
    }

    /**
     * fill the forms with generic data
     */
    public ShopOpening2ndPage fillWithGenericData(final String email, final String password) {
        return fillWithGenericData(email, password, password, 0);
    }

    /**
     * @param flag specifies which fields should be left empty
     */
    public ShopOpening2ndPage fillWithGenericData(final String email, final String password, final String confirmPWD,
            final int emptyFieldsFlag) {
        final String randomString = String.valueOf(System.currentTimeMillis());

        if ((emptyFieldsFlag & CHECKCOMPANY) == CHECKCOMPANY) {
            s().check("isCompanyYes");
            if ((emptyFieldsFlag & NOCOMPANY) != NOCOMPANY) {
                s().type("company", "company" + randomString);
            }
            s().type("tradeRegister", "" + randomString);
        } else {
            s().check("isCompanyNo");
        }
        s().select("salutation", "value=1"); // male : Herr
        if ((emptyFieldsFlag & NOFIRSTNAME) != NOFIRSTNAME) {
            s().type("firstname", "firstname" + randomString);
        }
        if ((emptyFieldsFlag & NOSURNAME) != NOSURNAME) {
            s().type("surname", "surname" + randomString);
        }
        if ((emptyFieldsFlag & NOSTREET) != NOSTREET) {
            s().type("street", "street" + randomString);
        }
        if ((emptyFieldsFlag & NOZIPCODE) != NOZIPCODE) {
            s().type("zipcode", randomString.substring(0, 4));
        }
        if ((emptyFieldsFlag & NOCITY) != NOCITY) {
            s().type("city", "Berlin");
        }
        s().select("countryId", "label=Deutschland");
        if ((emptyFieldsFlag & NOPHONE) != NOPHONE) {
            s().type("phone", randomString);
        }

        s().type("email", email);

        s().type("password", password);
        s().type("passwordConfirm", confirmPWD);

        if ((emptyFieldsFlag & NOTERMS) != NOTERMS) {
            s().check("terms");
        }

        getSeleniumHelper().clickAndWait("id=btnSubmit");
        getLog().info("Successfully registered user");

        return new ShopOpening2ndPage(this);
    }

    public boolean hasToAcceptTerms() {
        return s().isElementPresent(xpathTerms);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.pageobjects.frontend.commons.ValidatableObject#validate()
     */
    @Override
    public void validate() {
        getAssertHelper().matchesUrlRegex(".*-C4172", "url sollte auf -C4172 enden : " + s().getLocation());
    }
}
