/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.pageobjects.PageAbstract;

/**
 * This step contains settings for the shop layout
 * 
 
 */
public class ShopOpening2ndPage extends PageD2CAbstract {

  public ShopOpening2ndPage(final PageAbstract page) {
    super(page);
  }

  /**
   * Click on the save button to set layout of shop. Returns next step of shop opening (shop name & language).
   */
  public ShopOpening3rdPage save() {
    getSeleniumHelper().clickAndWait("id=btnSave");

    return new ShopOpening3rdPage(this);
  }

  //    /**
  //     * Clicks on the X in the upper right corner of the assistant. Brings the user to the user area.
  //     */
  //    public OverviewUAPage closeAssistant() {
  //        getSeleniumHelper().clickAndWait("//div[@id='close-wizz']//a[contains(@class, 'close')]");
  //
  //        return new OverviewUAPage(this);
  //    }

  @Override
  public void validate() {
    getAssertHelper().matchesUrlRegex(".*-C4173", "url sollte auf -C4173 enden : " + s().getLocation());
  }

}
