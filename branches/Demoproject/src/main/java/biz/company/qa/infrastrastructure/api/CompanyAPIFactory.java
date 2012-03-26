/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.api;

import biz.company.qa.pageobjects.CONFIG_KEYS;
import biz.company.qa.pageobjects.Configuration;

/**
 
 */
public class CompanyAPIFactory {

    /**
     * creates an instance with the correct api vm
     * 
     * @param configuration
     * @return
     */
    public static CompanyAPI createInstance(Configuration<CONFIG_KEYS> configuration, CONFIG_KEYS shopId) {
        int apiVmId = Integer.valueOf(configuration.getValue(CONFIG_KEYS.API_VMID));
        int theShopId = Integer.valueOf(configuration.getValue(shopId));
        biz.company.qa.infrastrastructure.api.Configuration config = new biz.company.qa.infrastrastructure.api.Configuration(
                apiVmId, theShopId);
        return new CompanyAPI(config);
    }

    /**
     * creates an instance with the correct api vm
     * 
     * @param configuration
     * @return
     */
    public static CompanyAPI createInstance(String apiHost, int shopId) {
        biz.company.qa.infrastrastructure.api.Configuration config = new biz.company.qa.infrastrastructure.api.Configuration(
                apiHost, shopId);
        return new CompanyAPI(config);
    }
}
