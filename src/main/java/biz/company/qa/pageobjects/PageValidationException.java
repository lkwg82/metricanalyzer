/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects;

/**
 
 */
public class PageValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** 
     * 
     */
    public PageValidationException() {
    }

    /**
     * @param message
     */
    public PageValidationException(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public PageValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public PageValidationException(final Throwable cause) {
        super(cause);
    }

}
