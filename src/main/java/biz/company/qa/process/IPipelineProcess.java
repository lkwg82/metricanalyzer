/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

/**
 * linked process list
 * <p/>
 * this process is special, it can be feeded through another process
 * 
 
 */
public interface IPipelineProcess extends IProcess {

    void setPreviousProcess(IProcess process) throws ProcessPipelineException;

    IProcess getPreviousProcess();

}
