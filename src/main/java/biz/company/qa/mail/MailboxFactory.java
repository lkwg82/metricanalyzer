/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.mail;

import biz.company.qa.pageobjects.CONFIG_KEYS;
import biz.company.qa.pageobjects.Configuration;

/**
 * factory to easily create instances of {@link Mailbox} from the configuration
 * 
 
 */
public class MailboxFactory {

    private MailboxFactory() {
        // ok
    }

    /**
     * creates instance from configuration
     * 
     * @param configuration
     * @return
     */
    public static Mailbox createInstance(final Configuration<CONFIG_KEYS> configuration) {

        String mailhost = configuration.getValue(CONFIG_KEYS.MAILHOST);
        String mailuser = configuration.getValue(CONFIG_KEYS.MAILUSER);
        String mailpwd = configuration.getValue(CONFIG_KEYS.MAILPASSWORD);
        String vmId = configuration.getValue(CONFIG_KEYS.VMID);

        return new Mailbox(mailhost, mailuser, mailpwd, "vm" + vmId);
    }

}
