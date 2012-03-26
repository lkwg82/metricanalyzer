package biz.company.qa.infrastrastructure.designer;

import static org.testng.Assert.assertTrue;

import com.thoughtworks.selenium.Selenium;

import biz.company.qa.infrastrastructure.SeleniumTests;

public class Confo6 extends Designer {

    private static final String DEFAULT_SELENIUM_TIMEOUT = "300000";

    public Confo6(final Selenium selenium, final String flashObjectId) {
        super(selenium, flashObjectId);
    }

    /**
     * Opens a new designer in the main window
     * 
     * @return the designer, ready for testing (all necessary wait methods have been called already)
     */
    public static Designer initDesigner(final SeleniumTests provider) {
        final Designer designer = new Confo6(provider.s(), "confomat");
        designer.waitFor().fullyLoaded();
        return designer;
    }

    /**
     * Opens a new designer in a popup window and changes the focus to this window
     * 
     * @return the designer, ready for testing (all necessary wait methods have been called already)
     */
    public static Designer initDesignerInPopup(final SeleniumTests provider, final String popupName) {
        provider.s().waitForPopUp(popupName, DEFAULT_SELENIUM_TIMEOUT);
        provider.s().selectWindow(popupName);
        log.info("Current window is " + provider.s().getTitle());
        final Designer designer = Confo6.initDesigner(provider);
        assertTrue(designer.isUIStarted(), "Could not load designer in popup " + popupName);
        return designer;
    }

    /**
     * Opens the designer and sends the first product to server
     */
    @Override
    public void createProduct() {
        waitFor().fullyLoaded();
        sendArticleToServer();
    }

    @Override
    public int getArticleQuantity() {
        return Integer.valueOf(call("selenium_getArticleQuantity"));
    }

    @Override
    public int getProductTypeColorID() {
        return Integer.valueOf(call("selenium_getProductTypeColorID"));
    }

    @Override
    public int getProductTypeID() {
        return Integer.valueOf(call("selenium_getProductTypeID"));
    }

    @Override
    public int getProductTypeSizeID() {
        return Integer.valueOf(call("selenium_getProductTypeSizeID"));
    }

    @Override
    public void sendArticleToServer() {
        call("selenium_sendArticleToServer");
    }

    @Override
    public void setArticleQuantity(final int quantity) {
        call("selenium_setArticleQuantity", Integer.toString(quantity));
    }

    @Override
    public void setDefaultProductTypeSize() {
        call("selenium_setDefaultProductTypeSize");
    }

    @Override
    public boolean isUIStarted() {
        return Boolean.valueOf(call("selenium_UIStarted"));
    }
}
