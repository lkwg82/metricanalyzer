/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects;

/**
 * this is only to replace method from new created page objects in old util classes
 * <p/>
 * can be removed when old code is completely migrated
 * 
 
 */
public class MigrationPage extends PageAbstract {

    /**
     * @param selenium
     */
    public MigrationPage(final SeleniumContext context) {
        super(context);
    }

    /**
     * @param pageFrom
     */
    public MigrationPage(final PageAbstract pageFrom) {
        super(pageFrom);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.pageobjects.pages.commons.PageAbstract#validate()
     */
    @Override
    public void validate() {
        // we do not validate this, because this is a adapter-page to the old system
    }
}
