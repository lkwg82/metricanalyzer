/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.selenium.Selenium;

import biz.company.qa.pageobjects.utils.AssertionHelper;
import biz.company.qa.pageobjects.utils.SeleniumHelper;

/**
 * base class for page objects with selenium
 * 
 
 */
public abstract class WebElementAbstract implements IValidatableObject {

    private final Logger log;
    private final PageAbstract pageFrom;
    private final SeleniumHelper helper;
    private final AssertionHelper assertHelper;
    private final SeleniumContext context;

    public WebElementAbstract(final SeleniumContext context) {
        this.context = context;
        pageFrom = null;
        if (context.getSelenium() == null) {
            throw new PageValidationException("selenium should not be null");
        }

        log = LoggerFactory.getLogger(this.getClass());
        helper = new SeleniumHelper(context);
        assertHelper = new AssertionHelper(context);

        try {
            long start = System.currentTimeMillis();

            preValidate();
            validate();

            // each class should be display as it is, dont show the parent oder super-super-type
            getLog().debug("opened (validated in " + (System.currentTimeMillis() - start) + "ms)");
        } catch (final AssertionError e) {
            throw new PageValidationException("Validation failed for " + this, e);
        }
    }

    /**
     * @param page - another page I come from
     */
    public WebElementAbstract(final PageAbstract page) {
        context = page.getContext();
        pageFrom = page;

        log = LoggerFactory.getLogger(this.getClass());
        helper = page.getSeleniumHelper();
        assertHelper = page.getAssertHelper();

        try {
            long start = System.currentTimeMillis();

            preValidate();
            validate();

            // each class should be display as it is, dont show the parent oder super-super-type
            getLog().debug("opened (validated in " + (System.currentTimeMillis() - start) + "ms)");
        } catch (final AssertionError e) {
            throw new PageValidationException("Validation failed for " + this, e);
        }
    }

    /**
     * here something can be done before really validating;<br/>
     * be carefull while using this, could cause insconsistence in lifecycle
     */
    protected void preValidate() {
        // ok
    }

    /**
     * @return
     */
    protected final SeleniumHelper getSeleniumHelper() {
        return helper;
    }

    /**
     * @return the selenium
     */
    protected final Selenium s() {
        return getContext().getSelenium();
    }

    /**
     * @return the pageFrom
     */
    public final PageAbstract getPageFrom() {
        return pageFrom;
    }

    /**
     * @return the assertHelper
     */
    protected final AssertionHelper getAssertHelper() {
        return assertHelper;
    }

    /**
     * @return the context
     */
    public SeleniumContext getContext() {
        return context;
    }

    /**
     * returns a Page to be redirected to the given instance of this class
     * 
     * <pre>
     * LandingPage entry = new LandingPage(selenium);
     * RedirectPage redirectPage = entry.gotoPage();
     * LandingPage entry2 = redirectPage.redirectToPageType(LandingPage.class);
     * 
     * if (entry2 == null) {
     *     HomePage home = redirectPage.redirectToPageType(HomePage.class);
     *     .. do something ...
     * }
     * 
     * </pre>
     * 
     * @param <T>
     * @param clazz
     * @return
     */
    public <T extends PageAbstract> T redirectToPageType(final Class<T> clazz) {

        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(PageAbstract.class);

            return constructor.newInstance(getPageFrom());
        } catch (IllegalArgumentException e) {
            throw new PageValidationException(e);
        } catch (InstantiationException e) {
            throw new PageValidationException(e);
        } catch (IllegalAccessException e) {
            throw new PageValidationException(e);
        } catch (InvocationTargetException e) {
            throw new PageValidationException(e);
        } catch (SecurityException e) {
            throw new PageValidationException(e);
        } catch (NoSuchMethodException e) {
            throw new PageValidationException(e);
        }
    }

    /**
     * @return the log
     */
    protected Logger getLog() {
        return log;
    }

}
