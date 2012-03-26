package biz.company.qa.pageobjects.frontend.D2C;

import biz.company.qa.infrastrastructure.designer.Confo6;
import biz.company.qa.infrastrastructure.designer.Designer;
import biz.company.qa.pageobjects.PageAbstract;

/**
 * This step opens the designer
 * 
 
 */
public class ShopOpening4thPage extends PageD2CAbstract {

    private Designer designer;

    public ShopOpening4thPage(PageAbstract page) {
        super(page);
    }

    /**
     * @return the designer
     * @throws InterruptedException 
     */
    public Designer getDesigner() throws InterruptedException {
        getLog().info("new confo6");
        designer = new Confo6(s(), "confomat");
        getLog().info("wait for fully loaded");
        designer.waitFor().fullyLoaded();
        getLog().info("fully loaded");
        return designer;
    }

    @Override
    public void validate() {
        getAssertHelper().matchesUrlRegex(".*-C4175", "url sollte auf -C4175 enden : " + s().getLocation());
    }

}
