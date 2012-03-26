/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import org.testng.SkipException;

import biz.company.qa.pageobjects.HasLoginHelper;
import biz.company.qa.pageobjects.ILoginHelper;
import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.SeleniumContext;
import biz.company.qa.pageobjects.frontend.HasTopNavigation;

/**
 
 */
public abstract class PageD2CAbstract extends PageAbstract implements HasTopNavigation<TopD2CNavigation>,
        HasLoginHelper {

    public static final String LOCATOR_UA_OPEN_SHOP = "link=Jetzt anmelden";
    public static final String LOCATOR_TOP_LOGIN_LINK = "navi-0-2105";
    public static final String LOCATOR_TOP_LOGOUT_LINK = "navi-0-2019";
    public static final String LOCATOR_TOP_USER_PROFILE = "navi-0-1999";
    public static final String LOCATOR_TOP_HELP_LINK = "navi-0-1328";
    public static final String XPATH_SYSTEM_MESSAGE_ERROR_BY_CLASS = "//*[contains(@class,'message error')]";;
    /**
     * @see LoginHelperD2C
     */
    private final ILoginHelper loginHelper;

    /**
     * @param page
     */
    public PageD2CAbstract(final PageAbstract page) {
        super(page);
        loginHelper = new LoginHelperD2C(getContext());
    }

    /**
     * @param selenium
     */
    public PageD2CAbstract(final SeleniumContext context) {
        super(context);
        loginHelper = new LoginHelperD2C(context);
    }

    /**
     * @return
     */
    @Override
    public TopD2CNavigation getTopNavigation() {
        return new TopD2CNavigation(this);
    }

    public StartD2CPage goToStartpage() {
        getSeleniumHelper().clickAndWait(StartD2CPage.LOCATOR_LINK);

        return new StartD2CPage(getContext());
    }

    /**
     * @return the loginHelper
     */
    @Override
    public ILoginHelper getLoginHelper() {
        return loginHelper;
    }

    /**
     * This method returns true if there a 505 server error aka Henning
     */
    private boolean isHenning() {
        String HENNING_PAGE_ID = "-C5031";

        String url = s().getLocation();
        return url.matches(".*" + HENNING_PAGE_ID);
    }

    /**
     * Before validating the page, make sure there is no 505 aka Henning
     */
    @Override
    protected final void preValidate() {

        if (isHenning()) {
            throw new SkipException("505 SERVER ERROR (Henning) Please check mails of vm to see the real problem.");
        }
    }

}
