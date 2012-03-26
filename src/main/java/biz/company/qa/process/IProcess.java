/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

/**
 
 */
public interface IProcess {
    void executeProcess() throws ProcessExecutionException;

    ProcessConfiguration getConfig();

    IProcess setConfig(ProcessConfiguration config);
}
