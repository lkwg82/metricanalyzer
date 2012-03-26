/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.designer;

import java.util.concurrent.TimeUnit;

import biz.company.qa.pageobjects.utils.ICondition;
import biz.company.qa.pageobjects.utils.Waiter;

/**
 
 */
public class DesignerWaitHelper {

    private final int timeout;
    private final Designer designer;

    /**
     * @param timeout
     */
    public DesignerWaitHelper(final int timeout, final Designer designer) {
        this.timeout = timeout;
        this.designer = designer;
    }

    /**
     *
     */
    public void fullyLoaded() {
        condition(new ICondition() {

            @Override
            public String getErrorMessage() {
                return "flash not loaded yet";
            }

            @Override
            public boolean check() {
                return designer.isUIStarted();
            }
        });

    }

    public void condition(final ICondition condition) {
        new Waiter(timeout, TimeUnit.MILLISECONDS).forCondition(condition);
    }

}
