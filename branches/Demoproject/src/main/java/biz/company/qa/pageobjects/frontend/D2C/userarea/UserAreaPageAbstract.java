/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C.userarea;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.PageValidationException;
import biz.company.qa.pageobjects.frontend.HasSubNavigation;
import biz.company.qa.pageobjects.frontend.D2C.PageD2CAbstract;

/**
 * represents the userArea with its
 * 
 
 */
public abstract class UserAreaPageAbstract extends PageD2CAbstract implements HasSubNavigation<UserAreaNavigation> {

    protected static final String XPATH_MY_ACCOUNT_SELECTED = "xpath=//li[contains(@class,'current')]/a[contains(@href,'"
            + OverviewUAPage.PAGE_ID + "')]";
    public static final String XPATH_LINK_MY_ACCOUNT = "xpath=//li/a[contains(@href,'" + OverviewUAPage.PAGE_ID + "')]";

    public static final String XPATH_DESIGNS_MY_DESIGNS_DATA = "//div[@id='content']" + "//div[@id='designList']";
    public static final String XPATH_SYSTEM_MESSAGE_SUCCESS_BY_CLASS = "//*[contains(@class,'message success')]";

    private final UserAreaNavigation subnavigation;

    /**
     * @param page
     */
    public UserAreaPageAbstract(final PageAbstract page) {
        super(page);
        subnavigation = new UserAreaNavigation(this);
        try {
            subnavigation.validate();
        } catch (final AssertionError e) {
            throw new PageValidationException(e);
        }
    }

    @Override
    public final UserAreaNavigation getUserAreaNavigation() {
        return subnavigation;
    }

    public abstract UserAreaSideBar getSideBar();

    @Override
    public abstract void validate();

}
