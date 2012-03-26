/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend;


/**
 * an abstract class for accessing forms
 * 
 
 */
public abstract class FormObjectAbstract {

    /**
     * here the form is filled with data
     */
    public abstract void fillIn();

    /**
     * fetch data from form
     */
    public abstract void retrieve();

    /**
     * this could be overriden for pressing 'submit' or 'send' or whatever
     * 
     *
     */
    public void send(){
        throw new IllegalAccessError("not implemented");
    }

    /**
     * this could be overriden for pressing 'delete' or 'remove' or whatever
     * 
     *
     */
    public void delete(){
        throw new IllegalAccessError("not implemented");
    }

}
