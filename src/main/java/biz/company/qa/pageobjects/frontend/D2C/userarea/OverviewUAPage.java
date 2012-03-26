/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C.userarea;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.PageValidationException;
import biz.company.qa.pageobjects.utils.Price;
import biz.company.qa.pageobjects.utils.Regexer;

import org.testng.Assert;

/**
 * The overview page of the user area. It looks a bit like a dashboard.
 * 
 
 */
public class OverviewUAPage extends UserAreaPageAbstract {

  public static final String PAGE_ID = "-C4";
  public static final String XPATH_STATUS = "xpath=//div[@id='account']/div/table/tbody/tr[6]/td";

  public static final String XPATH_PROFILE_MYDATA = "//*[@id='account']";
  public static final String XPATH_PROFILE_USERID = XPATH_PROFILE_MYDATA + "//*[.='Benutzer-ID']/../td";
  private static final String MY_DATA_BOX = "//*[@id='account']";
  private static final String MY_ORDER_BOX = "//*[@id='recentorders']";
  private static final String NEWS_BOX = "//*[@id='communityNews']";
  private static final String MY_SHOPS_BOX = "//div[@id='shopOverview']";
  private static final String MY_SALES_BOX = "//div[@id='sold_items']";
  private static final String GOOGLE_CALENDAR = "//div[@id='google_calendar']";;

  /**
   * @param page
   */
  public OverviewUAPage(final PageAbstract page) {
    super(page);
  }
  //
  //    public final boolean isPremiumUser() {
  //        return isPartnerType(PartnerType.PREMIUM);
  //    }
  //
  //    public final boolean isStandardUser() {
  //        return isPartnerType(PartnerType.STANDARD);
  //    }

  /**
   * Parses the string "Premium expires 05.08.2012" from page and returns the expiry date as Calendar object
   */
  public final Calendar getPremiumExpiryDate() {
    Calendar date = Calendar.getInstance();

    // fetch expiry date from premium status
    String text = s().getText(XPATH_STATUS);
    final String regex = ".*([0-9]{2})\\.([0-9]{2})\\.([0-9]{4}).*";
    final Regexer regexer = new Regexer(text, regex);
    regexer.findAll();

    // order-trans-id was displayed
    assertTrue(regexer.matched());

    String day = regexer.getMatchList().get(0).getMatchParts()[0];
    String month = regexer.getMatchList().get(0).getMatchParts()[1];
    String year = regexer.getMatchList().get(0).getMatchParts()[2];

    // setting hour and minute to 0 (important if comparing only the date, not the time)
    // and month is -1 because JAVA Calendar API is a mess and JANUARY = 0
    date.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day), 0, 0);

    return date;
  }

  //  /**
  //   * @param partnerType
  //   * @return
  //   */
  //  private boolean isPartnerType(final PartnerType type) {
  //    return s().getText(XPATH_STATUS).startsWith(type.label());
  //  }

  /**
   * @return
   */
  public final String getLoggedInUserId() {

    String locator = OverviewUAPage.XPATH_PROFILE_USERID;
    if (s().isElementPresent(locator)) {
      return s().getText(locator);
    } else {
      throw new PageValidationException("could not get userId, maybe not logged in");
    }
  }

  /**
   * returns true if the navigation contains the link to the shops
   */
  public boolean isShopsLinkPresent() {
    return s().isElementPresent(UserAreaNavigation.XPATH_SHOPS);
  }

  @Override
  public void validate() {
    getAssertHelper().matchesUrlRegex(".*" + PAGE_ID + ".*");

    // user id
    final String id = s().getText("xpath=//div[@id='account']//tr[1]/td[1]");

    Assert.assertTrue(id != null, "user id not found");
    Assert.assertTrue(id.matches("\\d+"), "user id is not numeric");
  }

  @Override
  public UserAreaOverviewSideBar getSideBar() {
    return new UserAreaOverviewSideBar(this);
  }

  /**
   * Returns all the orderIds of the orders in the recent order box on the dashboard
   */
  public List<String> getRecentOrdersIds() {
    List<String> ids = new ArrayList<String>();
    String xpath = "//*[@id='recentorders']//tbody/tr/td[2]";
    Number count = s().getXpathCount(xpath);

    for (int i = 1; i <= count.intValue(); i++) {
      final String nextPath = "xpath=(" + xpath + ")[" + i + "]";
      ids.add(s().getText(nextPath));
    }

    return ids;
  }

  /**
   * Searches for the orderId in the recent order box on dashboard. Than returns the price of the recent order.
   */
  public Price getRecentOrderTotal(final String orderId) {
    List<String> ids = getRecentOrdersIds();
    int index = ids.indexOf(orderId) + 1;
    final String xpath = "xpath=(//*[@id='recentorders']//tbody/tr/td[3])[" + index + "]";
    return Price.parse(s().getText(xpath));
  }

  /**
   * @return
   */
  public boolean isMyDataVisible() {
    if (getSeleniumHelper().isElementPresentAndVisible(MY_DATA_BOX)
        && getSeleniumHelper().isElementPresentAndVisible(MY_DATA_BOX + "/h2")
        && getSeleniumHelper().isElementPresentAndVisible(MY_DATA_BOX + "/div")) {
      return true;
    }
    return false;
  }

  /**
   * @return
   */
  public boolean isRecentOrderVisisble(final String orderId) {
    return s().getText(MY_ORDER_BOX).contains(orderId);
  }

  /**
   * @return
   */
  public boolean isNewsVisible() {
    if (getSeleniumHelper().isElementPresentAndVisible(NEWS_BOX)
        && getSeleniumHelper().isElementPresentAndVisible(NEWS_BOX + "/h2")
        && getSeleniumHelper().isElementPresentAndVisible(NEWS_BOX + "/div")) {
      return true;
    }
    return false;
  }

  public boolean isMyOrderVisible() {
    if (getSeleniumHelper().isElementPresentAndVisible(MY_ORDER_BOX)
        && getSeleniumHelper().isElementPresentAndVisible(MY_ORDER_BOX + "/h2")
        && getSeleniumHelper().isElementPresentAndVisible(MY_ORDER_BOX + "/div")) {
      return true;
    }
    return false;
  }

  public boolean isMySalesVisible() {
    if (getSeleniumHelper().isElementPresentAndVisible(MY_SALES_BOX)
        && getSeleniumHelper().isElementPresentAndVisible(MY_SALES_BOX + "/h2")
        && getSeleniumHelper().isElementPresentAndVisible(MY_SHOPS_BOX + "/div")) {
      return true;
    }
    return false;
  }

  public boolean isMyShopsVisisble() {
    if (getSeleniumHelper().isElementPresentAndVisible(MY_SHOPS_BOX)
        && getSeleniumHelper().isElementPresentAndVisible(MY_SHOPS_BOX + "/h2")
        && getSeleniumHelper().isElementPresentAndVisible(MY_SHOPS_BOX + "/div")) {
      return true;
    }
    return false;
  }

  public boolean isCalendarVisible() {
    if (getSeleniumHelper().isElementPresentAndVisible(GOOGLE_CALENDAR)
        && getSeleniumHelper().isElementPresentAndVisible(GOOGLE_CALENDAR + "/h2")
        && getSeleniumHelper().isElementPresentAndVisible(GOOGLE_CALENDAR + "/div")) {
      return true;
    }
    return false;
  }
}
