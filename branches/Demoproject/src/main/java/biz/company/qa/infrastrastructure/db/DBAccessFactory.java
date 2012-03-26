/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.db;

import biz.company.qa.pageobjects.CONFIG_KEYS;
import biz.company.qa.pageobjects.Configuration;

/**
 
 */
public class DBAccessFactory {

    private DBAccessFactory() {
        // ok
    }

    /**
     * @param configuration
     * @return
     */
    public static DBAccess createInstance(final Configuration<CONFIG_KEYS> configuration) {
        return new DBAccess("www.vm" + configuration.getValue(CONFIG_KEYS.VMID) + ".virtual");
    }

}
