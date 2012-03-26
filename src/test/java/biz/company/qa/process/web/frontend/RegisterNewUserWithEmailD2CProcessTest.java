/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process.web.frontend;

import biz.company.qa.infrastrastructure.SeleniumTests;
import biz.company.qa.process.ProcessFactory;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 
 */
public class RegisterNewUserWithEmailD2CProcessTest extends SeleniumTests {

  @Test(groups = { "helper" }, enabled = false)
  public void testProcessD2C() throws Exception {

    /*
     * prepare
     */
    RegisterNewUserWithEmailD2CProcess p = ProcessFactory.createProcessWithRandomConfig(
        RegisterNewUserWithEmailD2CProcess.class, getContext());

    /*
     * action
     */
    p.executeProcess();

    /*
     * check
     */

    Assert.assertTrue(p.getOverviewPage().getLoginHelper().isLoggedIn());
  }
}
