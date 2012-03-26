/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process.web.frontend;

import org.testng.Assert;
import org.testng.annotations.Test;

import biz.company.qa.infrastrastructure.SeleniumTests;
import biz.company.qa.pageobjects.frontend.D2C.ArticleListingD2CPage;
import biz.company.qa.pageobjects.frontend.D2C.StartD2CPage;
import biz.company.qa.pageobjects.frontend.D2C.userarea.OverviewUAPage;
import biz.company.qa.process.ProcessFactory;

/**
 
 */
public class RegisterNewUserD2CProcessTest extends SeleniumTests
{

  @Test(groups = { "helper" }, expectedExceptions = NullPointerException.class)
  public void testProcessD2C() throws Exception
  {

    /*
     * prepare
     */
    RegisterNewUserD2CProcess p = ProcessFactory.createProcessWithRandomConfig(RegisterNewUserD2CProcess.class, getContext());

    /*
     * action
     */
    p.executeProcess();

    /*
     * check
     */

    Assert.assertNotNull(p.getUserId(), "user id should not be null");
    Assert.assertTrue(p.getUserId().matches("^\\d+$"), "user id should be numerical");

    // test the new user account
    ArticleListingD2CPage page = new StartD2CPage(getContext()).gotoLoginPage().//
        login(null, p.getConfig().getEmail(), p.getConfig().getPassword());

    // logged in correctly
    OverviewUAPage overview = page.getTopNavigation().gotoUserAreaOverviewPage();
    Assert.assertEquals(overview.getLoggedInUserId(), p.getUserId());

  }
  //
  // @Test(groups = { "helper", "lgo" })
  // @TestRuntime(debugSeleniumCommands = true)
  // public void testProcess1() throws Exception {
  // testProcessD2C();
  // }
  //
  // @Test(groups = { "helper", "lgo" })
  // @TestRuntime(debugSeleniumCommands = true)
  // public void testProcess2() throws Exception {
  // testProcessD2C();
  // }
  //
  // @Test(groups = { "helper", "lgo" })
  // @TestRuntime(debugSeleniumCommands = true)
  // public void testProcess3() throws Exception {
  // testProcessD2C();
  // }
  //
  // @Test(groups = { "helper", "lgo" })
  // @TestRuntime(debugSeleniumCommands = true)
  // public void testProcess5() throws Exception {
  // testProcessD2C();
  // }
  //
  // @Test(groups = { "helper", "lgo" })
  // @TestRuntime(debugSeleniumCommands = true)
  // public void testProcess4() throws Exception {
  // testProcessD2C();
  // }
  //
  // @Test(groups = { "helper", "lgo" })
  // @TestRuntime(debugSeleniumCommands = true)
  // public void testProcess6() throws Exception {
  // testProcessD2C();
  // }
  //
  // @Test(groups = { "helper", "lgo" })
  // @TestRuntime(debugSeleniumCommands = true)
  // public void testProcess7() throws Exception {
  // testProcessD2C();
  // }
  //
  // @Test(groups = { "helper", "lgo" })
  // @TestRuntime(debugSeleniumCommands = true)
  // public void testProcess8() throws Exception {
  // testProcessD2C();
  // }
  //
  // @Test(groups = { "helper", "lgo" })
  // @TestRuntime(debugSeleniumCommands = true)
  // public void testProcess9() throws Exception {
  // testProcessD2C();
  // }
  //
  // @Test(groups = { "helper", "lgo" })
  // @TestRuntime(debugSeleniumCommands = true)
  // public void testProcess10() throws Exception {
  // testProcessD2C();
  // }
  //
  // @Test(groups = { "helper", "lgo" })
  // @TestRuntime(debugSeleniumCommands = true)
  // public void testProcess11() throws Exception {
  // testProcessD2C();
  // }
}
