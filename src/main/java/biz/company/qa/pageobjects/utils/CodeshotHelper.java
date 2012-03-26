/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.selenium.Selenium;

import biz.company.qa.pageobjects.SeleniumContext;

/**
 * why not saving code as well?
 * 
 
 */
public class CodeshotHelper {
    private final static Logger log = LoggerFactory.getLogger(CodeshotHelper.class);
    private static final String TARGET_SELENIUM_CODESHOTS = "target/selenium_screenshots";
    private final SeleniumContext context;
    private String directory = TARGET_SELENIUM_CODESHOTS;

    /**
     * @param selenium
     */
    public CodeshotHelper(final SeleniumContext context) {
        this.context = context;
    }

    private Selenium s() {
        return context.getSelenium();
    }

    public void saveCodeToLocalFile(final File file) {
        try {
            File dir = new File(directory);

            // double check due to concurrent runs
            if (!dir.exists() && !dir.mkdirs()) {
                if (!dir.exists()) {
                    log.error("error while creating hierarchy: " + dir);
                }
            }

            FileUtils.writeStringToFile(file, s().getHtmlSource());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public String getUniqueFileName(final String fileName) {
        // create a file name
        return directory + '/' + System.nanoTime() + "_" + fileName + ".html";
    }

    /**
     * let it create an unique name with a custom suffix
     * 
     * @param sufix
     */
    public void saveCodeWithCustomSuffix(final String suffix) {
        String filename = getUniqueFileName(suffix);
        saveCodeToLocalFile(new File(filename));
    }

    public CodeshotHelper useDirectory(final String directory) {
        this.directory = directory;
        return this;
    }
}
