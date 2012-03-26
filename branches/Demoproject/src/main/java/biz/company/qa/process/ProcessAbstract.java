/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

/**
 
 */
public abstract class ProcessAbstract implements IProcess {
    private ProcessConfiguration config;

    /**
     * @param config the config to set
     */
    @Override
    public final ProcessAbstract setConfig(final ProcessConfiguration config) {
        this.config = config;
        return this;
    }

    /**
     * @return the config
     */
    @Override
    public final ProcessConfiguration getConfig() {
        return config;
    }
}
