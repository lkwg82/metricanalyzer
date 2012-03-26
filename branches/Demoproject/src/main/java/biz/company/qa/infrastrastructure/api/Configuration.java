/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.api;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 
 */
public class Configuration {
    private static final Logger log = LoggerFactory.getLogger(Configuration.class);
    private final int apiVmId;
    private final int shopId;
    private final String apiHost;

    public Configuration(final int apiVmId, final int shopId) {
        this.apiVmId = apiVmId;
        this.shopId = shopId;
        this.apiHost=null;
    }
    
    public Configuration(final String apiHost, final int shopId) {
        this.apiVmId = -1;
        this.shopId = shopId;
        this.apiHost=apiHost;
    }

    private Properties properties = null;

    public String getAPIKey() {
        return getProperties().getProperty("biz.company.sampleapps.apiKey");
    }

    public String getSecret() {
        return getProperties().getProperty("biz.company.sampleapps.secret");
    }

    public String getShopUrl() {
        if (apiHost == null)
            return String.format("http://vm%02d.virtual:2305/api/v1/shops/%d", apiVmId, shopId);
        else
            return String.format("http://%s/api/v1/shops/%d", apiHost, shopId);
    }

    private Properties getProperties() {
        if (properties == null) {
            try {
                properties = new Properties();
                properties.load(this.getClass().getResourceAsStream("/company.properties"));
            } catch (IOException e) {
                log.error("Could not load properties!", e);
            }
        }
        return properties;
    }
}
