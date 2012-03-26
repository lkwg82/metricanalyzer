/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

/**
 * indicates an error in workflow logic of a process
 * 
 
 */
public class ProcessExecutionException extends Exception {

    private static final long serialVersionUID = 1907677695340514968L;

    /**
     * 
     */
    public ProcessExecutionException() {
    }

    /**
     * @param message
     */
    public ProcessExecutionException(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ProcessExecutionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public ProcessExecutionException(final Throwable cause) {
        super(cause);
    }

}
