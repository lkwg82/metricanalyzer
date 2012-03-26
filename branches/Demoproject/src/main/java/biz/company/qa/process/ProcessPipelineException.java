/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

/**
 * indicates an error in the combination of processes
 * 
 
 */
public class ProcessPipelineException extends Exception {

    private static final long serialVersionUID = 1907677695340514968L;

    /**
     * 
     */
    public ProcessPipelineException() {
    }

    /**
     * @param message
     */
    public ProcessPipelineException(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ProcessPipelineException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public ProcessPipelineException(final Throwable cause) {
        super(cause);
    }

}
