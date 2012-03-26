/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import java.util.ArrayList;
import java.util.List;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.SeleniumContext;
import biz.company.qa.pageobjects.frontend.ArticleListingPageAbstract;
import biz.company.qa.pageobjects.frontend.HasTopNavigation;
import biz.company.qa.pageobjects.frontend.LoginPage;

import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import org.testng.SkipException;

/**
 * The page shows a number of articles
 * 
 
 */
public class ArticleListingD2CPage extends ArticleListingPageAbstract implements HasTopNavigation<TopD2CNavigation> {

    public static final String XPATH_ERROR = "//div[@id='system_err']";
    public static final String NEXT_BUTTON = "//img[contains(@class,'pager_next')]";
    private static final String PAGE_TITLE = "//*[@id='content']//h1";

    public enum COLOR {
        RED {
            @Override
            public String getXPath() {
                return "//*[@class='facet_entry_unselected red']/a";
            }
        },
        GREEN {
            @Override
            public String getXPath() {
                return "//*[@class='facet_entry_unselected green']/a";
            }
        };
        public abstract String getXPath();
    }

    public enum DESIGNS {
        HUMOUR {
            @Override
            public String getXPath() {
                return "//*[@id='facets_FACET_DESIGNS']//a[contains(@href,'/humor')]";
            }
        };
        public abstract String getXPath();

    }

    public ArticleListingD2CPage(final PageAbstract pageFrom) {
        super(pageFrom);
    }

    public ArticleListingD2CPage(final SeleniumContext context) {
        super(context);
    }

    @Override
    public void validate() {

    }

    /**
     * Returns the sidebar box that let's you refine the search for articles by color, department, product type or theme
     */
    public SearchFacetD2CSideBar getSearchFacetSideBar() {
        return new SearchFacetD2CSideBar(this);
    }

    @Override
    public ArticleDetailD2CPage gotoFirstArticleDetailPage() {
        // try to find an available article
        int i = 1;
        do {
            getLog().info(i + ". attempt to find an available MP article");
            getSeleniumHelper().clickAndWait("xpath=(" + ArticleDetailD2CPage.MP_PRODUCT_DETAILS_PAGE + ")[" + i + "]");
            i++;
            if (i > 5) {
                Assert.fail("Could not find an available MP article, aborting");
            }
        } while (s().getLocation().contains("error/articlestatus"));

        return new ArticleDetailD2CPage(this);
    }

    @Override
    public LoginPage goToLoginPage() {
        getSeleniumHelper().waitFor().elementPresent("//*[@id='navi-0-2105']");
        getSeleniumHelper().clickAndWait("//*[@id='navi-0-2105']");
        return new LoginD2CPage(this);
    }

    @Override
    public TopD2CNavigation getTopNavigation() {
        return new TopD2CNavigation(this);
    }

    public List<String> getArticleIDsOfPage() {
        final List<String> ids = new ArrayList<String>();

        final Number count = s().getXpathCount("//div[@class='article_details']");
        for (int i = 1; i <= count.intValue(); i++) {
            final String attribute = s().getAttribute("xpath=(//div[@class='article_details'])[" + i + "]//h3@id");
            ids.add(attribute.replace("namelist_", ""));
        }
        return ids;
    }

    public ArticleDetailD2CPage gotoArticleDetailPage(final String articleId) {
        final List<String> ids = getArticleIDsOfPage();
        if (ids.contains(articleId)) {
            getSeleniumHelper()
                    .clickAndWait("//div[@class='article_details']//h3[@id='namelist_" + articleId + "']//a");
            return new ArticleDetailD2CPage(this);
        } else {
            throw new SkipException("Article is not on current page");
        }
    }

    /**
     * Returns the detail page of the first article on the list that is usable (means: is found on opossum and has
     * available sizes)
     */
    public ArticleDetailD2CPage goToAvailableArticleDetailPage() {
        final List<String> ids = getArticleIDsOfPage();

        for (final String id : ids) {
            getSeleniumHelper().clickAndWait("//div[@class='article_details']//h3[@id='namelist_" + id + "']//a");

            // if an error occurs try next article
            if (getSeleniumHelper().isElementPresentAndVisible(XPATH_ERROR)) {
                s().goBack();
                continue;
            }

            // check if the article has sizes to choose, otherwise go back and try next
            getSeleniumHelper().waitFor().elementPresentAndVisible(ArticleDetailD2CPage.XPATH_SIZE_LIST);
            if (getSeleniumHelper().isElementPresentAndVisible(ArticleDetailD2CPage.XPATH_SIZE_LIST + "//li")) {
                return new ArticleDetailD2CPage(this);
            } else {
                s().goBack();
            }
        }

        // if you are here and there was no return, there where no usable articles on page
        throw new SkipException("None of the following articles was available to put in basket: " + ids);
    }

    public ArticleListingD2CPage goToNextPage() {
        getSeleniumHelper().clickAndWait(NEXT_BUTTON);
        return new ArticleListingD2CPage(this);
    }

    public String getTitle() {
        return s().getText(PAGE_TITLE).trim();
    }

    /**
     * uses the search bar on the D2C Page
     * 
     * @param string
     * @return articleListingPage
     */
    public ArticleListingD2CPage searchFor(final String query) {
        getSeleniumHelper().waitFor(15000).elementPresentAndVisible("navigation-search-box");
        s().type("navigation-search-box", query);
        getSeleniumHelper().clickAndWait("//*[@id='searchRegular']");
        return this;
    }

    /**
     * @param red2
     */
    public ArticleListingD2CPage selectColor(final COLOR color) {
        if (getSeleniumHelper().isElementPresentAndVisible(color.getXPath())) {
            getSeleniumHelper().clickAndWait(color.getXPath());
            return this;
        } else {
            throw new NotFoundException("Could not found color: " + color);
        }
    }

    /**
     * @param humour
     */
    public ArticleListingD2CPage selectDesign(final DESIGNS design) {
        getSeleniumHelper().clickAndWait(design.getXPath());
        return this;
    }

    /**
     * @param humour
     * @return
     */
    public String getDesignName(final DESIGNS humour) {
        getSeleniumHelper().waitFor(15000).elementPresentAndVisible(humour.getXPath());
        return s().getText(humour.getXPath());
    }

    /**
     * @return
     */
    public String getSelectedDepartment() {
        if (getSeleniumHelper().isElementPresentAndVisible("//li[@class='current']/a[contains(@id,'navi-2')]")) {
            return s().getText("//li[@class='current']/a[contains(@id,'navi-2')]");
        } else {
            return null;
        }
    }

    /**
     * @return
     */
    public String getSelectedProductCategorie() {
        return s().getText("//*[@class='current_nested']");
    }

    /**
     * @return
     */
    public boolean isDesignsVisible() {
        return getSeleniumHelper().isElementPresentAndVisible("facets_FACET_DESIGNS");
    }

    public List<String> getProductCategories() {
        final int xpathCount = s().getXpathCount("//*[@id='facets_FACET_PRODUCTTYPES']/ul/li/ul/li").intValue();
        final List<String> productCategories = new ArrayList<String>();
        for (int i = 1; i <= xpathCount; i++) {
            productCategories.add(s().getText("xpath=(//*[@id='facets_FACET_PRODUCTTYPES']/ul/li/ul/li)[" + i + "]/*"));
        }
        return productCategories;
    }

    public ArticleListingD2CPage selectProductCategory(final String category) {
        getSeleniumHelper().clickAndWait("//a[contains(.,'" + category + "')]");
        return this;
    }

    public List<String> getDesignCategories() {
        final int xpathCount = s().getXpathCount("//*[@id='facets_FACET_DESIGNS']/ul/li/*").intValue();
        final List<String> designCategories = new ArrayList<String>();
        for (int i = 1; i <= xpathCount; i++) {
            designCategories.add(s().getText("xpath=(//*[@id='facets_FACET_DESIGNS']/ul/li)[" + i + "]/*"));
        }
        return designCategories;
    }

    public List<String> getColors() {
        final int xpathCount = s().getXpathCount("//*[@id='facets_FACET_COLORS']/ul/li").intValue();
        final List<String> colors = new ArrayList<String>();
        for (int i = 1; i <= xpathCount; i++) {
            colors.add(s().getText("xpath=(//*[@id='facets_FACET_COLORS']/ul/li)[" + i + "]/*"));
        }
        return colors;
    }

    public List<String> getAllUsers() {
        final List<String> allDesigners = new ArrayList<String>();
        int i = 1;
        String designerName = "";
        final String designerPath = "//span[contains(@id, 'ownerlist')]/a[contains(@href, 'designer')]";
        while (s().isElementPresent("xpath=(" + designerPath + ")[" + i + "]")) {
            designerName = s().getText("xpath=(" + designerPath + ")[" + i + "]");
            allDesigners.add(designerName);
            i++;
        }
        return allDesigners;
    }

    public ArticleListingD2CPage selectDesignCategory(final String designCategory) {
        getSeleniumHelper().clickAndWait("//*[@id='facets_FACET_DESIGNS']//a[contains(.,'" + designCategory + "')]");
        return this;
    }

    public ArticleListingD2CPage selectColor(final String color) {
        getSeleniumHelper().clickAndWait("//*[@id='facets_FACET_COLORS']//a[contains(.,'" + color + "')]");
        return this;
    }

    public ArticleListingD2CPage gotoUserProducts(final String username) {
        getContext().getSeleniumHelper().clickAndWait("link=" + username);
        return this;
    }

    public boolean isUserProfileVisible(final String username) {
        if (getSeleniumHelper().isElementPresentAndVisible("//*[@id='content']/div[1]/div[2]")) {
            return s().getText("//*[@id='content']/div[1]/div[2]/div/div[2]/h2").contains(username);
        } else {
            return false;
        }
    }

    public boolean isResultVisible() {
        return !getSeleniumHelper().isElementPresentAndVisible("//*[@id='tpl_noRresults']/h1");
    }

    public String getSelectedDesignCategory() {
        return s().getText("//*[@id='facets_FACET_DESIGNS']//a[@class='current_nested']");
    }

    public String getCanonicalURL() {
        return s().getAttribute("//*[@rel='canonical']/@href");
    }

    public void resetProductSelection() {
        getSeleniumHelper().clickAndWait("//*[@id='facets_FACET_PRODUCTTYPES']//*[@class='facet_reset']/a");
    }

    public void resetDesignSelection() {
        getSeleniumHelper().clickAndWait("//*[@id='facets_FACET_DESIGNS']//*[@class='facet_reset']/a");
    }

    public List<String> getDesignSubCategories() {
        final int xpathCount = s().getXpathCount("//*[@id='facets_FACET_DESIGNS']/ul/li/ul/li").intValue();
        final List<String> designCategories = new ArrayList<String>();
        for (int i = 1; i <= xpathCount; i++) {
            designCategories.add(s().getText("xpath=(//*[@id='facets_FACET_DESIGNS']/ul/li/ul/li)[" + i + "]"));
        }
        return designCategories;
    }

    public ArticleListingD2CPage selectDesignSubCategory(final String designCategory) {
        getSeleniumHelper().clickAndWait("//*[@id='facets_FACET_DESIGNS']//a[contains(.,'" + designCategory + "')]");
        return this;
    }

    public boolean isNoResultsVisible() {
        return getSeleniumHelper().isElementPresentAndVisible("tpl_noRresults");
    }

    public ArticleListingD2CPage search(final String query) {
        s().type("navigation-search-box", query);
        getSeleniumHelper().clickAndWait("//input[@value='Suchen']");
        return new ArticleListingD2CPage(this);
    }

}
