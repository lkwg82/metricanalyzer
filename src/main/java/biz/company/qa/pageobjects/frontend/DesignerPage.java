/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend;

import biz.company.qa.infrastrastructure.designer.CNG;
import biz.company.qa.infrastrastructure.designer.Confo6;
import biz.company.qa.infrastrastructure.designer.Designer;
import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.PageValidationException;
import biz.company.qa.pageobjects.frontend.D2C.PageD2CAbstract;
import biz.company.qa.pageobjects.utils.ICondition;

/**
 
 */
public class DesignerPage extends PageD2CAbstract {

    private Designer designer;

    /**
     * @param page
     */
    public DesignerPage(final PageAbstract page) {
        super(page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.pageobjects.PageAbstract#validate()
     */
    @Override
    public void validate() {

        getSeleniumHelper().waitFor(10 * 1000).specialConditionElseTimeout(new ICondition() {

            @Override
            public String getErrorMessage() {
                return "can not find a swf notation in the html code";
            }

            @Override
            public boolean check() {
                return s().getHtmlSource().contains(".swf");
            }
        });

        int version = getVersionOfConformat();

        switch (version) {
            case 6:
                designer = new Confo6(s(), "confomat");
                break;
            case 7:
                designer = new CNG(getContext(), "Confomat");
                break;
            default:
                throw new PageValidationException("could instantiate a designer, unknown version " + version);
        }

        designer.waitFor(30 * 1000).fullyLoaded();
    }

    /**
     * @return
     */
    public int getVersionOfConformat() {
        String html = s().getHtmlSource();
        // confomat-7.3.155.swf

        if (html.contains("/confomat-7")) {
            return 7;
        } else if (html.contains("/confomatLoader6")) {
            return 6;
        } else {
            throw new PageValidationException("can not detect conformat version");
        }

    }

    /**
     * @return the designer
     */
    public Designer getDesigner() {
        return designer;
    }

}
