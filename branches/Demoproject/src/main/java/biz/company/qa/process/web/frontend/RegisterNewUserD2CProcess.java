/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process.web.frontend;

import biz.company.qa.pageobjects.CONFIG_KEYS;
import biz.company.qa.pageobjects.PageValidationException;
import biz.company.qa.pageobjects.frontend.D2C.OpenNewShopSellPage;
import biz.company.qa.pageobjects.frontend.D2C.SellPage;
import biz.company.qa.pageobjects.frontend.D2C.ShopOpening1stPage;
import biz.company.qa.pageobjects.frontend.D2C.ShopOpening2ndPage;
import biz.company.qa.pageobjects.frontend.D2C.StartD2CPage;
import biz.company.qa.process.ProcessAbstract;
import biz.company.qa.process.ProcessExecutionException;

import com.thoughtworks.selenium.Selenium;

/**
 
 */
public class RegisterNewUserD2CProcess extends ProcessAbstract {

  private String userId;

  /*
   * (non-Javadoc)
   * 
   * @see biz.company.qa.process.IProcess#executeProcess()
   */
  @Override
  public final void executeProcess() throws ProcessExecutionException {
    try {
      Selenium s = getConfig().getContext().getSelenium();

      String baseUrl = getConfig().getContext().getConfiguration().getValue(CONFIG_KEYS.BASE_URL);
      s.open(baseUrl);

      StartD2CPage startPage = new StartD2CPage(getConfig().getContext());
      SellPage sellPage = startPage.gotoSellPage();

      OpenNewShopSellPage openNewShopPage = sellPage.goToOpenNewShop();
      ShopOpening1stPage shopOpener1stStep = openNewShopPage.goToShopOpeningAssistant();

      if (shopOpener1stStep.hasToAcceptTerms()) {
        shopOpener1stStep.acceptTerms();
      }

      ShopOpening2ndPage shopOpener2ndStep = shopOpener1stStep.fillWithGenericData(getConfig().getEmail(),
          getConfig().getPassword(), getConfig().getPassword(), 0);

      startPage = shopOpener2ndStep.goToStartpage();

      //            OverviewUAPage overviewUAPage = (OverviewUAPage) startPage.getTopNavigation().gotoUserAreaOverviewPage();
      //
      //            userId = overviewUAPage.getLoggedInUserId();

      startPage.getLoginHelper().logout();
    } catch (PageValidationException pe) {
      throw new ProcessExecutionException(pe);
    }
  }

  /**
   * @param userId the userId to set
   */
  public final void setUserId(final String userId) {
    this.userId = userId;
  }

  /**
   * @return the userId
   */
  public final String getUserId() {
    return userId;
  }

}
