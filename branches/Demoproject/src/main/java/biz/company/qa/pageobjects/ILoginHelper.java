/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects;

/**
 * handle topics around
 * <ul>
 * <li>logout</li>
 * <li>logged in user</li>
 * </ul>
 * 
 
 */
public interface ILoginHelper {
    /**
     * logout
     */
    void logout();

    /**
     * check whether user is loggin or not
     * 
     * @return
     */
    boolean isLoggedIn();
}
