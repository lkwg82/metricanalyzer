/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process.web.frontend;

import com.thoughtworks.selenium.Selenium;

import biz.company.qa.pageobjects.CONFIG_KEYS;
import biz.company.qa.pageobjects.PageValidationException;
import biz.company.qa.pageobjects.frontend.D2C.StartD2CPage;
import biz.company.qa.pageobjects.frontend.D2C.userarea.OverviewUAPage;
import biz.company.qa.process.ProcessAbstract;
import biz.company.qa.process.ProcessExecutionException;

/**
 
 */
public class RegisterNewUserWithEmailD2CProcess extends ProcessAbstract {

    private String email;
    private OverviewUAPage overviewPage;

    /*
     * (non-Javadoc)
     * 
     * @see biz.company.qa.process.IProcess#executeProcess()
     */
    @Override
    public final void executeProcess() throws ProcessExecutionException {
        try {
            email = getConfig().getEmail();
            if (email == null) {
                throw new ProcessExecutionException(new NullPointerException("email must not be null"));
            }

            Selenium s = getConfig().getContext().getSelenium();

            String baseUrl = getConfig().getContext().getConfiguration().getValue(CONFIG_KEYS.BASE_URL);
            s.open(baseUrl);

            StartD2CPage startPage = new StartD2CPage(getConfig().getContext());

            overviewPage = startPage.gotoLoginPage().register(email);

            getConfig().setPassword(null);

        } catch (PageValidationException pe) {
            throw new ProcessExecutionException(pe);
        }
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    public OverviewUAPage getOverviewPage() {
        return overviewPage;
    }
}
