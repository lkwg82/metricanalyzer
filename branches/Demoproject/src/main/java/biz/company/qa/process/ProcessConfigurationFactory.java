/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

import java.security.SecureRandom;

import biz.company.qa.pageobjects.SeleniumContext;

/**
 
 */
public final class ProcessConfigurationFactory {
    private ProcessConfigurationFactory() {
    }

    /**
     * @param s
     * @return
     */
    public static ProcessConfiguration generateBasicSeleniumConfig(final SeleniumContext context) {
        ProcessConfiguration config = new ProcessConfiguration();
        config.setContext(context);
        return config;
    }

    public static ProcessConfiguration generateRandomConfig(final SeleniumContext context) {
        String login = "user" + System.currentTimeMillis() + new SecureRandom().nextLong();
        String password = "xxxxxx";
        String email = login + "@qa.company.de";

        ProcessConfiguration config = new ProcessConfiguration();

        config.setLogin(login);
        config.setEmail(email);
        config.setPassword(password);
        config.setContext(context);

        return config;
    }
}
