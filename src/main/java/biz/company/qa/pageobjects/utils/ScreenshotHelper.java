/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

import biz.company.qa.pageobjects.SeleniumContext;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.selenium.Selenium;

/**
 * Helper-class for handling stuff around screenshots
 * 
 
 */
public class ScreenshotHelper {
  /**
   *
   */
  private static final String TARGET_SELENIUM_SCREENSHOTS = "target/selenium_screenshots";
  private final static Logger log = LoggerFactory.getLogger(ScreenshotHelper.class);
  private final SeleniumContext context;

  /**
   * @param selenium
   */
  public ScreenshotHelper(final SeleniumContext context) {
    this.context = context;
  }

  private Selenium s() {
    return context.getSelenium();
  }

  /**
   * saves a screenshot from the selenium browser to a local file
   * 
   * @param file
   */
  public void saveScreenshotToLocalFile(final File file) {

    final String screenshot = s().captureEntirePageScreenshotToString("background=#CCFFDD");
    //        final byte[] pngData = Base64.decodeBase64(screenshot.getBytes());
    //
    //        saveStringToFile(pngData, file);
  }

  /**
   * saves a screenshot from the desktop of the selenium instance to a local file
   * 
   * @param file
   */
  public void saveScreenshotDesktopToLocalFile(final File file) {

    final String screenshot = s().captureScreenshotToString();
    //    final byte[] pngData = Base64.decodeBase64(screenshot.getBytes());
    //
    //    saveStringToFile(pngData, file);
  }

  private void saveStringToFile(final byte[] pngData, final File file) {
    try {
      FileUtils.writeByteArrayToFile(file, pngData);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }

  /**
   * creates a screenshot in the temp-folder and returns the absolute path
   * 
   * @param filename - a specified filename without path
   */
  public String createRemoteScreenshot(final String filename) throws IOException {
    final String fileName = "/tmp/" + filename;
    s().captureScreenshot(fileName);
    return fileName;
  }

  public String getUniqueFileName(final String fileName) {
    // create a file name
    return TARGET_SELENIUM_SCREENSHOTS + '/' + System.nanoTime() + "_" + fileName + ".png";
  }

  /**
   * creates a screenshot from the browser session and returns the file name
   * 
   * @return filename
   * @throws IOException
   */
  public String createRemoteScreenshot() throws IOException {
    return createRemoteScreenshot(new SecureRandom().nextLong() + ".png");
  }
}
