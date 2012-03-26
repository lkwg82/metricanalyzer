/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

import biz.company.qa.pageobjects.SeleniumContext;

/**
 
 */
public class ProcessConfiguration {

    private String login;
    private SeleniumContext context;
    private String email;
    private String password;

    @Override
    public final String toString() {
        StringBuffer b = new StringBuffer();
        String format = "%-10s : %-30s \n";

        b.append(String.format(format, "login", login));
        b.append(String.format(format, "email", email));
        b.append(String.format(format, "password", password));

        return b.toString();
    }

    /**
     * @return
     */
    public ProcessConfiguration getCopy() {
        ProcessConfiguration freshCopy = new ProcessConfiguration();

        freshCopy.setContext(context);
        freshCopy.setEmail(email);
        freshCopy.setLogin(login);
        freshCopy.setPassword(password);

        return freshCopy;
    }

    /**
     * @param password the password to set
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return the password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * @param email the email to set
     */
    public final void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the email
     */
    public final String getEmail() {
        return email;
    }

    /**
     * @param login the login to set
     */
    public final void setLogin(final String login) {
        this.login = login;
    }

    /**
     * @return the login
     */
    public final String getLogin() {
        return login;
    }

    /**
     * @param context the context to set
     */
    public void setContext(final SeleniumContext context) {
        this.context = context;
    }

    /**
     * @return the context
     */
    public SeleniumContext getContext() {
        return context;
    }
}
