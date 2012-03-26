/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * easy combination of processes
 * 
 
 */
public class ProcessPipeline {

    private final List<IProcess> processes = new LinkedList<IProcess>();
    private final ProcessConfiguration processConfiguration;

    /**
     * @param config
     */
    public ProcessPipeline(final ProcessConfiguration config) {
        processConfiguration = config;
    }

    /**
     * fill the pipe with another process
     * 
     * @param process
     * @return
     */
    public final ProcessPipeline pipe(final IProcess process) {
        process.setConfig(processConfiguration);
        getProcesses().add(process);
        return this;
    }

    public final void execute() throws ProcessPipelineException {

        validatingPipeline();

        for (int i = 0; i < processes.size(); i++) {
            IProcess pPrevious = i > 0 ? getProcesses().get(i - 1) : null;
            IProcess pCurrent = processes.get(i);
            try {
                if (pPrevious != null && pCurrent instanceof IPipelineProcess) {

                    ((IPipelineProcess) pCurrent).setPreviousProcess(pPrevious);
                }

                Logger log = LoggerFactory.getLogger(this.getClass());
                log.debug("starting process " + pCurrent.getClass().getSimpleName());
                pCurrent.executeProcess();
                log.debug("finishing process " + pCurrent.getClass().getSimpleName());

            } catch (ProcessExecutionException e) {

                String message = formatExceptionMessage(i, pCurrent);

                throw new ProcessPipelineException(message, e);
            }
        }
    }

    /**
     * @param i
     * @return
     */
    private String formatExceptionMessage(final int i, final IProcess failedProcess) {
        StringBuffer b = new StringBuffer("\n");
        final String LINE = "--------------------------------------------\n";
        b.append(LINE);
        b.append(processes.size() + " in the pipeline | " + i + " already executed \n");
        b.append(LINE);
        for (int z = 0; z < processes.size(); z++) {

            b.append(String.format(" %3d. %8s ", (z + 1), z == i ? "failed" : z < i ? "ok" : "n/a"));
            b.append("process ");
            b.append(processes.get(z));

            b.append("\n");
        }

        b.append(LINE);
        b.append(" failed process: ");
        b.append(failedProcess);
        b.append(LINE);

        return b.toString();
    }

    /**
     * validating the sequence of the processes
     * <p/>
     * is the previous process compatible against the current
     */
    private void validatingPipeline() throws ProcessPipelineException {

        for (int i = 1; i < getProcesses().size(); i++) {
            IProcess pCurrent = getProcesses().get(i);

            if (pCurrent instanceof IPipelineProcess) {
                IProcess pPrevious = getProcesses().get(i - 1);

                /**
                 * every pipeline process need an annotation, where it is declared what is accepted as input
                 */
                if (!pCurrent.getClass().isAnnotationPresent(AcceptedInput.class)) {
                    throw new ProcessPipelineException("please add the " + AcceptedInput.class + " annotation ");
                }

                AcceptedInput acceptedInput = pCurrent.getClass().getAnnotation(AcceptedInput.class);
                Set<Class<? extends IProcess>> acceptedProcesses = new HashSet<Class<? extends IProcess>>(
                        Arrays.asList(acceptedInput.types()));

                /**
                 * not accepted, because not on the list
                 */
                if (!acceptedProcesses.contains(pPrevious.getClass())) {
                    StringBuffer b = new StringBuffer("\t accepted processes:\n");
                    for (Class<? extends IProcess> p : acceptedProcesses) {
                        b.append("\t");
                        b.append(p.toString());
                        b.append("\n");
                    }

                    throw new ProcessPipelineException("this process " + pPrevious.getClass() + " is not accepted for "
                            + pCurrent.getClass() + " as input\n" + b.toString());
                }
            }
        }

    }

    /**
     * @return the processes
     */
    public final List<IProcess> getProcesses() {
        return processes;
    }

    /**
     * @return
     */
    public final IProcess getLast() {
        return processes.isEmpty() ? null : processes.get(processes.size() - 1);
    }
}
