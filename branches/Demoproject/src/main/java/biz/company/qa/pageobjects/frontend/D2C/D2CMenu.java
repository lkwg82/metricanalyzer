package biz.company.qa.pageobjects.frontend.D2C;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import biz.company.qa.pageobjects.WebElementAbstract;

import com.thoughtworks.selenium.SeleniumException;

/**
 * TODO: please add comments
 * 
 
 */
public class D2CMenu extends WebElementAbstract {

    private final DEPARTMENT department;
    private static final Map<DEPARTMENT, List<String>> categoryXPathMapPRODUCTTYPES = new ConcurrentHashMap<DEPARTMENT, List<String>>();
    private static final Map<DEPARTMENT, List<String>> categoryXPathMapTHEMES = new ConcurrentHashMap<DEPARTMENT, List<String>>();

    public enum CSS_CLASS {
        SEARCH_FOR_PRODUCTTYPECATEGORIES {
            @Override
            public String getName() {
                return "list-category";
            }
        },
        SEARCH_FOR_THEMECATEGORIES {
            @Override
            public String getName() {
                return "list-theme";
            }
        };

        public abstract String getName();
    }

    public enum DEPARTMENT {
        MEN {
            @Override
            public String getNaviId() {
                return "navi-2-4409";
            }

            public String getDeText() {
                return "Männer";
            }
        },
        WOMEN {
            @Override
            public String getNaviId() {
                return "navi-2-4413";
            }

        },
        BABIES {
            @Override
            public String getNaviId() {
                return "navi-2-4417";
            }

        },
        ACCESSOIRS {
            @Override
            public String getNaviId() {
                return "navi-2-4421";
            }

        },
        REDUCED {
            @Override
            public String getNaviId() {
                return "navi-2-5721";
            }

        },
        NEW_PRODUCTS {
            @Override
            public String getNaviId() {
                return "navi-2-5476";
            }

        },
        SORTIMENT {
            @Override
            public String getNaviId() {
                return "navi-2-66";
            }
        },
        GIFTS {
            @Override
            public String getNaviId() {
                return "navi-2-5735";
            }
        };
        public abstract String getNaviId();
    }

    public D2CMenu(StartD2CPage page, DEPARTMENT dep) {
        super(page);
        this.department = dep;
    }

    @Override
    public void validate() {
        for (DEPARTMENT c : DEPARTMENT.values()) {
            getAssertHelper().isElementPresent("//a[@id='" + c.getNaviId() + "']",
                    "missing main dep with id :" + c.getNaviId());
        }
    }

    /**
     
     */
    public static class IndexedLinkList {

        private final D2CMenu menu;
        private final List<String> linkList;

        public IndexedLinkList(D2CMenu menu, List<String> categories) {
            this.menu = menu;
            this.linkList = categories;
        }

        public int size() {
            return linkList.size();
        }

        /**
         * selecting index of links (1..n)
         * 
         * @param index
         * @return
         */
        public ArticleListingD2CPage gotoIndex(int index) {

            if (linkList.isEmpty()) {
                throw new SeleniumException("can not select a category, does not have");
            } else {
                if (linkList.size() < index) {
                    throw new SeleniumException("can not select this category [" + index
                            + "], because it is out of the boundary, size:" + linkList.size());
                } else {
                    menu.getSeleniumHelper().clickAndWait(linkList.get(index));
                }
            }

            return new ArticleListingD2CPage(menu.getPageFrom());
        }

    }

    public static class SearchFor {
        private final D2CMenu menu;
        private final DEPARTMENT department;

        public SearchFor(D2CMenu menu, DEPARTMENT department) {
            this.menu = menu;
            this.department = department;
        }

        public IndexedLinkList productTypeCategories() {
            List<String> categories = menu.findCategories(department, CSS_CLASS.SEARCH_FOR_PRODUCTTYPECATEGORIES);
            return new IndexedLinkList(menu, categories);
        }

        public IndexedLinkList themeCategories() {
            List<String> categories = menu.findCategories(department, CSS_CLASS.SEARCH_FOR_THEMECATEGORIES);
            return new IndexedLinkList(menu, categories);
        }

    }

    public SearchFor searchFor() {

        s().mouseOver(department.getNaviId());

        return new SearchFor(this, department);
    }

    /**
     * Returns a list of themes or producttype categories
     */
    private List<String> findCategories(final DEPARTMENT category, CSS_CLASS cssClass) {

        Map<DEPARTMENT, List<String>> currentLinkMap = null;
        String cssClassName = null;

        switch (cssClass) {
            case SEARCH_FOR_PRODUCTTYPECATEGORIES:
                currentLinkMap = categoryXPathMapPRODUCTTYPES;
                cssClassName = CSS_CLASS.SEARCH_FOR_PRODUCTTYPECATEGORIES.getName();
                break;
            case SEARCH_FOR_THEMECATEGORIES:
                currentLinkMap = categoryXPathMapTHEMES;
                cssClassName = CSS_CLASS.SEARCH_FOR_THEMECATEGORIES.getName();
                break;
        }

        if (!currentLinkMap.containsKey(category)) {

            List<String> categories = new ArrayList<String>();

            String xpath_base = "//a[@id='" + category.getNaviId() + "']/following-sibling::div"; //
            String xpath_list_category = xpath_base + "/ul[@class='" + cssClassName + "']/li/a";
            String xpath_list_category_iterate = xpath_base + "/ul[@class='" + cssClassName + "']/li[a][%d]/a";

            Number count = s().getXpathCount(xpath_list_category);
            for (int i = 1; i <= count.intValue(); i++) {
                String xpath = String.format(xpath_list_category_iterate, i);
                String href = s().getAttribute(xpath + "@href");
                if (href == null) {
                    throw new SeleniumException("sth wrong with this xpath: " + xpath);
                }
                categories.add(xpath + "[@href='" + href + "']");
            }

            currentLinkMap.put(category, categories);
        }

        return currentLinkMap.get(category);
    }

}
