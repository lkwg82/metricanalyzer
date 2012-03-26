/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.testng.Assert;

import com.thoughtworks.selenium.Selenium;

import biz.company.qa.pageobjects.SeleniumContext;

/**
 
 */
public class AssertionHelper {

    private final SeleniumContext context;

    /**
     * @param selenium
     */
    public AssertionHelper(final SeleniumContext context) {
        this.context = context;
    }

    /** Asserts that two objects are not the same (compares using .equals()) */
    public void assertNotEquals(final Object obj1, final Object obj2) {
        if (obj1.equals(obj2)) {
            fail("did not expect values to be equal (" + obj1.toString() + ")");
        }
    }

    /**
     * Sometimes elements are present but invisible
     */
    public void assertNotPresentOrNotVisible(final String locator) {
        if (s().isElementPresent(locator)) {
            assertFalse(s().isVisible(locator));
        } else {
            assertFalse(s().isElementPresent(locator));
        }
    }

    /**
     * Sometimes elements are present but invisible
     * 
     * @param logMessage
     */
    public void assertNotPresentOrNotVisible(final String locator, final String logMessage) {
        if (s().isElementPresent(locator)) {
            assertFalse(s().isVisible(locator), logMessage);
        } else {
            assertFalse(s().isElementPresent(locator), logMessage);
        }
    } // company specific

    /**
     * Sometimes elements are present but invisible
     */
    public void assertPresentAndVisible(final String locator) {
        Assert.assertTrue(s().isElementPresent(locator), locator + "not present");
        Assert.assertTrue(s().isVisible(locator), locator + "not visible");
    }

    /**
     * Sometimes elements are present but invisible
     * 
     * @param logMessage
     */
    public void assertPresentAndVisible(final String locator, final String logMessage) {
        assertTrue(s().isElementPresent(locator), logMessage);
        assertTrue(s().isVisible(locator), logMessage);
    }

    public void assertURLContainsRSSFeed(final String url, final String title, final String[] content) throws Exception {
        final GetMethod get = new GetMethod(url);
        final HttpClient httpclient = new HttpClient();
        final int result = httpclient.executeMethod(get);
        assertEquals(result, 200);
        final String feed = get.getResponseBodyAsString();
        assertTrue(feed.contains(title), "Feed from " + url + " must contain " + title);
        for (String element : content) {
            assertTrue(feed.contains("<" + element + ">"), "feed " + feed + " does not contain " + "<" + element + ">");
            assertTrue(feed.contains("</" + element + ">"), "feed " + feed + " does not contain " + "</" + element
                    + ">");
        }
    }

    /**
     * short for
     * 
     * <pre>
     * Assert.assertTrue(message, s().isElementPresent(xpath));
     * </pre>
     * 
     * @param xpath
     * @param message
     */
    public final void isElementPresent(final String xpath, final String message) {
        Assert.assertTrue(xpath.matches("^(xpath=)?/.*"), "must start with / or xpath=/ : " + xpath);
        Assert.assertTrue(s().isElementPresent(xpath), message);
    }

    /**
     * checks the current selenium.getLocation() against the regex
     * 
     * @param urlRegex
     */
    public final void matchesUrlRegex(final String urlRegex) {
        matchesUrlRegex(urlRegex, "failed:");
    }

    /**
     * checks the current selenium.getLocation() against the regex
     * 
     * @param urlRegex
     * @param errMsg
     */
    public final void matchesUrlRegex(final String urlRegex, final String errMsg) {
        String url = s().getLocation();
        Assert.assertTrue(url.matches(urlRegex), errMsg + "current location " + url + " is not matching the regex '"
                + urlRegex + "' - please check!");
    }

    /**
     * @return
     */
    private Selenium s() {
        return context.getSelenium();
    }

}
