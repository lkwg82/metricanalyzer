/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend.D2C;

import java.util.ArrayList;
import java.util.List;

import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.WebElementAbstract;

/**
 * This is the sidebar with the search filter on marketplace. To filter for a color, just get all the color names by
 * getAvailableColors() and set the filter with filterColor("lila").
 * 
 
 */
public class SearchFacetD2CSideBar extends WebElementAbstract {
    public static final String FACET_BOX = "//div[@id='sidebar']//div[contains(@class,'facets')]";
    public static final String FACET_COLORS = "//div[@id='facets_FACET_COLORS']";
    public static final String FACET_PRODUCTTYPES = FACET_BOX + "//div[@id='facets_FACET_PRODUCTTYPES']";
    public static final String FACET_THEMES = FACET_BOX + "//div[@id='facets_FACET_DESIGNS']";

    public SearchFacetD2CSideBar(final PageAbstract page) {
        super(page);
    }

    @Override
    public void validate() {
        getAssertHelper().isElementPresent(FACET_COLORS, "Facet to search for colors is missing");
    }

    /**
     * Returns the current selected department from the left search facet box
     */
    public String getCurrentDepartment() {
        if (!getSeleniumHelper().isElementPresentAndVisible(FACET_BOX + "//a[@class='current_parent']")) {
            getLog().error("No department is selected");
        }
        return s().getText(FACET_BOX + "//a[@class='current_parent']");
    }

    /**
     * Returns the current selected category from the left search facet box
     */
    public String getCurrentProductTypeCategory() {
        if (!getSeleniumHelper().isElementPresentAndVisible(
                FACET_BOX + "//*[@class='facet_entry_child current_child']//a")) {
            getLog().error("No product type category is selected");
        }
        return s().getText(FACET_BOX + "//*[@class='facet_entry_child current_child']//a");
    }

    public void resetColorSelection() {
        getSeleniumHelper().clickAndWait(FACET_COLORS + "//*[@class='facet_reset']");
    }

    public void resetProductTypeCategorySelection() {
        getSeleniumHelper().clickAndWait(FACET_PRODUCTTYPES + "//*[@class='facet_reset']");
    }

    public void resetThemeCategorySelection() {
        getSeleniumHelper().clickAndWait(FACET_THEMES + "//*[@class='facet_reset']");
    }

    /**
     * Reset all selections (color, product type category, theme category)
     */
    public void resetSelection() {
        resetColorSelection();
        resetProductTypeCategorySelection();
        resetThemeCategorySelection();
    }

    /**
     * Returns a list of all current available color names (translated)
     */
    public List<String> getAvailableColors() {
        List<String> colors = new ArrayList<String>();
        Number count = s().getXpathCount(FACET_COLORS + "//a");
        for (int i = 1; i <= count.intValue(); i++) {
            colors.add(s().getText("xpath=(" + FACET_COLORS + "//a[" + i + "]"));
        }
        return colors;
    }

    /**
     * Returns a list of all current available product type category names (translated)
     */
    public List<String> getAvailableProductTypeCategories() {
        List<String> categories = new ArrayList<String>();
        Number count = s().getXpathCount(FACET_PRODUCTTYPES + "//a");
        for (int i = 1; i <= count.intValue(); i++) {
            categories.add(s().getText("xpath=(" + FACET_PRODUCTTYPES + "//a[" + i + "]"));
        }
        return categories;
    }

    /**
     * Returns a list of all current available theme category names (translated)
     */
    public List<String> getAvailableThemeCategories() {
        List<String> categories = new ArrayList<String>();
        Number count = s().getXpathCount(FACET_PRODUCTTYPES + "//a");
        for (int i = 1; i <= count.intValue(); i++) {
            categories.add(s().getText("xpath=(" + FACET_PRODUCTTYPES + "//a[" + i + "]"));
        }
        return categories;
    }

    public void filterColor(final String name) {
        getSeleniumHelper().clickAndWait(FACET_COLORS + "//a[.='" + name + "']");
    }

    public void filterProductTypeCategory(final String name) {
        getSeleniumHelper().clickAndWait(FACET_PRODUCTTYPES + "//a[.='" + name + "']");
    }

    public void filterThemeCategory(final String name) {
        getSeleniumHelper().clickAndWait(FACET_THEMES + "//a[.='" + name + "']");
    }
}
