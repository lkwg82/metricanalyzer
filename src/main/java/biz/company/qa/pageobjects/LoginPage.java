package biz.company.qa.pageobjects;

import biz.company.qa.pageobjects.PageAbstract;

public interface LoginPage {

    /**
     * Use the form to login with given credentials (long xor email)
     * <p/>
     * either set login or email null, not both
     * 
     * @param login
     * @param email
     * @param password
     * @return
     */
    PageAbstract login(String login, String email, String password);

    PageAbstract register(String email);

    void registerWithEmailAndUsername(String email, String username);

}