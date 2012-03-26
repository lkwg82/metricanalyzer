package biz.company.qa.infrastrastructure;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import biz.company.qa.common.CommonXPaths;
import biz.company.qa.common.TestNGTests;
import biz.company.qa.infrastrastructure.selenium.SeleniumController;
import biz.company.qa.pageobjects.PageValidationException;
import biz.company.qa.pageobjects.SeleniumContext;

import org.openqa.jetty.util.MultiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.IResultMap;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.thoughtworks.selenium.Selenium;

/**
 * Abstract superclass for all Selenium RC tests.
 * 
 
 * @version $Revision$
 * @since 17.11.2007
 */
public abstract class SeleniumTests extends TestNGTests
{
  public final static int                    LOGIN_MAIN   = 0;
  public final static int                    LOGIN_SHOP   = 1;

  private final ThreadLocal<Logger>          log          = new ThreadLocal<Logger>();

  // list of shops to be deleted after each test run
  public ThreadLocal<Set<String>>            shopIdsLocal = new ThreadLocal<Set<String>>();

  private final ThreadLocal<SeleniumContext> context      = new ThreadLocal<SeleniumContext>();


  @BeforeMethod
  public void beforeMethod() throws Exception
  {
    /*
     * try everything and capture all exception and throw all (!)
     */
    List<Exception> exceptionsList = new ArrayList<Exception>();
    try
    {
      // The context has to be set
      setContext(new SeleniumContext());
      getContext().init();
    }
    catch (Exception e)
    {
      exceptionsList.add(e);
    }

    try
    {
      // this is important for cleaning up the db afterwards
      shopIdsLocal.set(new HashSet<String>());
      getContext().setShopIds(shopIdsLocal.get());
    }
    catch (Exception e)
    {
      exceptionsList.add(e);
    }

    try
    {
      openBrowser();
      sanitizeEnvironment();
    }
    catch (Exception e)
    {
      exceptionsList.add(e);
    }

    try
    {
      if (!exceptionsList.isEmpty())
      {
        if (exceptionsList.size() == 1)
        {
          throw exceptionsList.get(0);
        }
        else
        {
          MultiException multi = new MultiException();
          for (Exception e : exceptionsList)
          {
            multi.add(e);
          }
          throw multi;
        }
      }
    }
    catch (Exception e)
    {
      log().error("Caught an ignoring Exception", e);
    }
  }

  /**
   * lazy fetch logger
   * 
   * @return
   */
  private Logger log()
  {

    if (log.get() == null)
    { // check on null
      log.set(LoggerFactory.getLogger(getClass()));
    }
    else
    { // check on correct class name
      if (!log.get().getName().equals(getClass().getName()))
      {
        // replace with correct logger name
        log.set(LoggerFactory.getLogger(getClass()));
      }
    }

    return log.get();
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod(final ITestContext c, final Method m)
  {
    /*
     * try everything and capture all exception and throw all (!)
     */
    List<Exception> exceptionsList = new ArrayList<Exception>();
    try
    {
      captureScreenshot(c, m);
    }
    catch (Exception e)
    {
      exceptionsList.add(e);
    }
    try
    {
      closeBrowser();
    }
    catch (Exception e)
    {
      exceptionsList.add(e);
    }

    try
    {
      if (!exceptionsList.isEmpty())
      {
        if (exceptionsList.size() == 1)
        {
          throw exceptionsList.get(0);
        }
        else
        {
          MultiException multi = new MultiException();
          for (Exception e : exceptionsList)
          {
            multi.add(e);
          }
          throw multi;
        }
      }
    }
    catch (Exception e)
    {
      log().error("Caught an ignoring Exception", e);
    }
  }

  public void openBrowser() throws Exception
  {

    SeleniumController controller = new SeleniumController().configure(getContext().getConfiguration());
    getContext().setController(controller);
    controller.start();
    getContext().setSelenium(controller.getSelenium());

    getContext().getController().init();
  }

  /**
   * Clean up the start page after we get there. This is needed to work around some shortcomings in selenium RCs browser
   * launcher (in particular true for IE)
   */
  public void sanitizeEnvironment()
  {
    try
    {
      // delete cookies
      s().deleteAllVisibleCookies();

      //      if (System.getProperty("env").equals("company"))
      {
        // tests often fail here, so I am adding some extra debug to
        // understand what is going on
        getContext().getSeleniumHelper().waitFor().elementPresent(CommonXPaths.TOP_LANG);
        if (!s().isElementPresent(CommonXPaths.TOP_LANG))
        {
          log().info("No selected language. This should never happen!");
          log().info("Here I am: " + s().getLocation());
          log().info("This was the page: " + s().getBodyText());
          return;
        }

        // german please
        final String currentLang = getCurrentLanguage();
        if (!"Deutschland".equals(currentLang))
        {
          log().info("Switching lang to German for testing (was: " + currentLang + ")");
          selectLanguage("Deutschland");
        }

        // make sure we are logged out
        if (!s().isElementPresent("//body"))
        {
          log().info("Strangely I am logged in, will log out");
          getContext().getSeleniumHelper().waitFor().elementPresent("//body");
          getContext().getSeleniumHelper().clickAndWait("logout");
        }

        // make sure basket is empty
        if (s().isTextPresent("glob:Warenkorb (*)") || s().isElementPresent(CommonXPaths.BASKET_WITH_ARTICLE))
        {
          final String location = s().getLocation();
          getContext().getSeleniumHelper().clickAndWait("link=glob:Warenkorb (*)");
          while (s().isElementPresent("link=Löschen"))
          {
            log().info("Need to delete item from basket...");
            getContext().getSeleniumHelper().clickAndWait("link=Löschen");
          }
          log().info("Basket empty, going back to homepage...");
          s().open(location);
        }
      }
    }
    catch (final Throwable e)
    {
      log().warn("Deliberatly ignoring exception: " + e);
    }
  }

  /**
   * Capture screenshot when a test fails
   */
  public void captureScreenshot(final ITestContext c, final Method m) throws Exception
  {
    // container
    TestResultMessageVO vo = null;

    if (c.getFailedTests().size() > 0)
    { // findout if the current test failed
      vo = getTestResultAndMessage(c.getFailedTests(), m);
    }

    if ((vo == null) && (c.getSkippedTests().size() > 0))
    { // findout if the current test was skipped
      vo = getTestResultAndMessage(c.getSkippedTests(), m);
    }

    if ((vo != null) && (vo.getTestResult() != null) && (s() != null))
    {

      final String className = m.getDeclaringClass().getCanonicalName();
      String suffix = className.replace("biz.company.qa", "nsq") + "." + vo.getTestResult().getName();
      { // screenshot of the page
        String fileName = getContext().getScreenshotHelper().getUniqueFileName(suffix);
        final File file = new File(fileName);
        getContext().getScreenshotHelper().saveScreenshotToLocalFile(file);
        //        new ImageHelper().addMetaDataToImage(file, className + "." + vo.getTestResult().getName(), vo.getMessage());
      }
      {// screenshot of the window
        String fileName = getContext().getScreenshotHelper().getUniqueFileName(suffix);
        File file = new File(fileName);
        getContext().getScreenshotHelper().saveScreenshotDesktopToLocalFile(file);
        //        new ImageHelper().addMetaDataToImage(file, className + "." + vo.getTestResult().getName(), vo.getMessage());
      }

      getContext().getCodeshotHelper().saveCodeWithCustomSuffix(suffix);
    }
  }

  private List<String> shopIds()
  {
    return new ArrayList<String>();
  }

  public void closeBrowser()
  {
    getContext().getController().stop();
    getContext().setSelenium(null);
  }

  /**
   * Select language from top selection
   * 
   * @deprecated use the not yet implemented methods in page objects hierarchy
   */
  @Deprecated
  public void selectLanguage(final String language)
  {
    final String selectedLanguage = s().getSelectedLabel(CommonXPaths.TOP_LANG);
    if ((selectedLanguage != null) && !selectedLanguage.equals(language))
    {
      try
      {
        getContext().getSeleniumHelper().selectAndWait(CommonXPaths.TOP_LANG, "label=" + language);
      }
      catch (PageValidationException e)
      {
        log().error(e.getMessage(), e);
      }
    }
  }

  /**
   * This method restarts the browser after waiting for some time so that the new browser session will start after the
   * old cache has been deleted. Replace the contents of this method when a way to delete the platform cache from
   * selenium has been implemented.
   */
  public void emptyCache(final String browser, final String startURL, final String host, final String startServer) throws Exception
  {
    s().stop();
    openBrowser();
  }

  /**
   * use instead a not yet implemented method in the page object hierarchy
   * 
   * @deprecated
   * @return
   */
  @Deprecated
  public String getCurrentLanguage()
  {
    return s().getSelectedLabel(CommonXPaths.TOP_LANG);
  }

  /**
   * use instead the LoginPages or the LoginHelper
   * 
   * @deprecated
   * @return
   */
  @Deprecated
  public void login(final String login, final String password, final int loginType)
  {
    getContext().getSeleniumHelper().clickAndWait("link=Jetzt Anmelden!");
    s().type("//*[@id='frmLogin']//input", login);
    s().type("password", password);
    getContext().getSeleniumHelper().clickAndWait("//form[@id='frmLogin']//*[contains(@value, 'Anmelden')]");
    Assert.assertTrue(s().isTextPresent("Abmelden"));
  }

  /**
   * local container value object
   * 
   
   */
  private static class TestResultMessageVO
  {

    private final ITestResult testResult;
    private final String      message;

    /**
     * @param results
     * @param m
     */
    public TestResultMessageVO(final ITestResult testResult, final String message)
    {
      this.testResult = testResult;
      this.message = message;
    }

    /**
     * @return the testResult
     */
    public ITestResult getTestResult()
    {
      return testResult;
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
      return message;
    }

    @Override
    public String toString()
    {
      return testResult.getTestClass().getName() + "." + testResult.getName();
    }

  }

  private TestResultMessageVO getTestResultAndMessage(final IResultMap results, final Method m)
  {

    StringBuffer message = new StringBuffer("Error message: ");
    for (ITestResult testResult : results.getAllResults())
    {

      // current tests name is not trivial in some cases ;-)
      String currentTestName = m.getName();
      if (this instanceof ITest)
      {
        currentTestName += " (" + ((ITest) this).getTestName() + ")";
      }

      if (testResult.getName().equals(currentTestName))
      {
        if ((testResult.getThrowable() != null) && (testResult.getThrowable().getMessage() != null))
        {
          message.append(testResult.getThrowable().getMessage());
        }
        else
        {
          message.append("No error message recorded!");
        }
        return new TestResultMessageVO(testResult, message.toString());
      }
    }

    return null;
  }

  /**
   * short for <code>getContext().getSelenium()</code>
   * 
   * @return
   */
  public Selenium s()
  {
    return getContext().getSelenium();
  }

  /**
   * @return the context
   */
  public SeleniumContext getContext()
  {
    return context.get();
  }

  /**
   * @param seleniumContext
   * @return
   */
  private void setContext(final SeleniumContext seleniumContext)
  {
    context.set(seleniumContext);
  }
}
