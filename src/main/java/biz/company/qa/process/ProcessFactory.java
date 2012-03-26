/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

import biz.company.qa.pageobjects.SeleniumContext;

/**
 
 */
public class ProcessFactory {
    private ProcessFactory() {
        // ok
    }

    public static final <T extends IProcess> T createProcessWithRandomConfig(final Class<T> clazz,
            final SeleniumContext context) throws ProcessExecutionException {
        try {
            ProcessConfiguration config = ProcessConfigurationFactory.generateRandomConfig(context);

            T p = clazz.newInstance();
            p.setConfig(config);
            return p;
        } catch (InstantiationException e) {
            throw new ProcessExecutionException(e);
        } catch (IllegalAccessException e) {
            throw new ProcessExecutionException(e);
        }
    }

}