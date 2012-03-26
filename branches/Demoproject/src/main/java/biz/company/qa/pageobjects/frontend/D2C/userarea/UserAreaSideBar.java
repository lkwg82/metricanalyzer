package biz.company.qa.pageobjects.frontend.D2C.userarea;

import org.testng.Assert;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.WebElementAbstract;

/**
 * The user area pages always contain a sidebar on the left side of the page. The links on this sidebar depend on the
 * user area sub sections, therefor this class is abstract.
 * 
 
 */
public abstract class UserAreaSideBar extends WebElementAbstract {

    public final static String UA_SHOPS_APPEARANCE_WIDGETS = "//div[@id='sidebar']//a[@id='navi-4-2522']";
    public static final String XPATH_QUICKLINKS = "//div[@id='quicklinks']";

    public UserAreaSideBar(PageAbstract page) {
        super(page);
    }

    @Override
    public void validate() {
        boolean sidebar = s().isElementPresent("//div[@id='sidebar']");
        boolean quicklinks = s().isElementPresent(XPATH_QUICKLINKS);

        if (!sidebar && !quicklinks) {
            Assert.fail("there is no sidebar on this page");
        }
    }

    public final boolean isSideBarElementHighlighted(final String xpath) {
        // the link element in which the link is present should be highlighted
        String classOfElement = s().getAttribute(xpath + "@class");

        return classOfElement.contains("active");
    }

}
