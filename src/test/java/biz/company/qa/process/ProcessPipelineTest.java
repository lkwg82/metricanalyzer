/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 
 */
public class ProcessPipelineTest {

    private final static Logger log = LoggerFactory.getLogger(ProcessPipelineTest.class);

    @Test(groups = { "helper" })
    public void executeProcessSequence() throws ProcessPipelineException {

        new ProcessPipeline(new ProcessConfiguration()).//
                pipe(new Process1()).//
                pipe(new Process2()).//
                execute();
    }

    @Test(groups = { "helper" })
    public void simplePipeline() throws ProcessPipelineException {
        ProcessPipeline pipeline = new ProcessPipeline(new ProcessConfiguration()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1());

        pipeline.execute();

        PipelineProcess1 lastProcess = (PipelineProcess1) pipeline.getProcesses().get(
                pipeline.getProcesses().size() - 1);

        Assert.assertEquals(lastProcess.getProcessList().size(), pipeline.getProcesses().size());
    }

    @Test(groups = { "helper" })
    public void simplePipelineFailValidating() throws ProcessPipelineException {
        ProcessPipeline pipeline = new ProcessPipeline(new ProcessConfiguration()).//
                pipe(new PipelineProcess2()).//
                pipe(new PipelineProcess1());

        try {
            pipeline.execute();
            Assert.fail("this should fail, because the process is expecting a special class (not given)");
        } catch (ProcessPipelineException e) {
            // ok
        }
    }

    @Test(groups = { "helper" }, expectedExceptions = ProcessPipelineException.class)
    public void simplePipelineFailExecuting() throws ProcessPipelineException {
        ProcessPipeline pipeline = new ProcessPipeline(new ProcessConfiguration()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1(false));

        pipeline.execute();
        Assert.fail("this should fail, because the process is expecting a special class (not given)");

    }

    @Test(groups = { "helper" })
    public void pipelineGetLast() throws ProcessPipelineException {

        ProcessPipeline pipeline = new ProcessPipeline(new ProcessConfiguration()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1()).//
                pipe(new PipelineProcess1());

        pipeline.execute();

        PipelineProcess1 last = (PipelineProcess1) pipeline.getLast();

        log.info("list" + last.getProcessList().size());

    }
}

class Process1 implements IProcess {

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.commons.IProcess#executeProcess()
     */
    @Override
    public void executeProcess() {
        // ok
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.commons.IProcess#getConfig()
     */
    @Override
    public ProcessConfiguration getConfig() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.commons.IProcess#setConfig(biz.company.qa.process.web.ProcessConfiguration)
     */
    @Override
    public IProcess setConfig(final ProcessConfiguration config) {
        return this;
    }
}

class Process2 implements IProcess {

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.commons.IProcess#executeProcess()
     */
    @Override
    public void executeProcess() {
        // System.out.println("put ur hands in the air");
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.commons.IProcess#getConfig()
     */
    @Override
    public ProcessConfiguration getConfig() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.commons.IProcess#setConfig(biz.company.qa.process.web.ProcessConfiguration)
     */
    @Override
    public IProcess setConfig(final ProcessConfiguration config) {
        return this;
    }
}

@AcceptedInput(types = PipelineProcess1.class)
class PipelineProcess1 extends ProcessAbstract implements IPipelineProcess {

    private PipelineProcess1 previous;

    private List<String> processList = new ArrayList<String>();

    private final boolean succeed;

    /**
     * @param succeed
     */
    public PipelineProcess1(final boolean succeed) {
        this.succeed = succeed;
    }

    /**
     * 
     */
    public PipelineProcess1() {
        succeed = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.commons.IPipelineProcess#setPreviousProcess(biz.company.qa.process.commons.IProcess)
     */
    @Override
    public void setPreviousProcess(final IProcess process) throws ProcessPipelineException {

        if (!(process instanceof PipelineProcess1)) {
            throw new ProcessPipelineException("I expected as input this process : " + PipelineProcess1.class);
        }

        previous = (PipelineProcess1) process;
    }/*
      * (non-Javadoc)
      * 
      * @see biz.company.qa.process.commons.IPipelineProcess#getPreviousProcess()
      */

    @Override
    public IProcess getPreviousProcess() {
        return previous;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.commons.IProcess#executeProcess()
     */
    @Override
    public void executeProcess() throws ProcessExecutionException {

        if (!succeed) {
            throw new ProcessExecutionException("this is failing for tests");
        }

        if (previous != null) {
            processList.addAll(previous.getProcessList());
        }

        processList.add(this.getClass().getCanonicalName());
    }

    /**
     * @param processList the processList to set
     */
    public void setProcessList(final List<String> processList) {
        this.processList = processList;
    }

    /**
     * @return the processList
     */
    public List<String> getProcessList() {
        return processList;
    }
}

/**
 * process mask (empty, just a ghost)
 * 
 
 */
class PipelineProcess2 extends ProcessAbstract implements IPipelineProcess {

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.commons.IProcess#executeProcess()
     */
    @Override
    public void executeProcess() {
        // dummy

    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.commons.IPipelineProcess#setPreviousProcess(biz.company.qa.process.commons.IProcess)
     */
    @Override
    public void setPreviousProcess(final IProcess process) throws ProcessPipelineException {
        // ok
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.commons.IPipelineProcess#getPreviousProcess()
     */
    @Override
    public IProcess getPreviousProcess() {
        return null;
    }
}