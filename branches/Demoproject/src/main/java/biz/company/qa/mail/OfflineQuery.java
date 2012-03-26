/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.mail;

/**
 * query for offline filters
 * 
 
 */
public interface OfflineQuery {
    /**
     * does this email match the query?
     * 
     * @param mail
     * @return
     */
    boolean match(Email mail);
}