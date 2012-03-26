/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.api;

/**
 * exception for api issues
 * 
 
 */
public class CompanyAPIException extends RuntimeException {

    /**
     * @param string
     */
    public CompanyAPIException(final String string) {
        super(string);
    }

    /**
     * @param string
     * @param e
     */
    public CompanyAPIException(final String string, final Exception e) {
        super(string, e);
    }

    /**
     *
     */
    private static final long serialVersionUID = -250667017799592501L;

}
