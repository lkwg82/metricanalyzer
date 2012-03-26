/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure;

/**
 
 */
public class SeleniumTestsException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public SeleniumTestsException() {
        super();
    }

    /**
     * @param message
     */
    public SeleniumTestsException(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public SeleniumTestsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public SeleniumTestsException(final Throwable cause) {
        super(cause);
    }

}
