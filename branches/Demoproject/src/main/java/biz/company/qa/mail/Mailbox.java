/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.mail;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * data access object for imap mailbox
 * 
 
 
 */
public class Mailbox {
    private final static Logger log = LoggerFactory.getLogger(Mailbox.class);

    private final String host;
    private final String user;
    private final String password;
    private final String foldername;
    private Store store;

    /**
     * @param host
     * @param user
     * @param password
     * @param folder
     */
    public Mailbox(final String host, final String user, final String password, final String folder) {
        this.host = host;
        this.user = user;
        this.password = password;
        foldername = folder;
    }

    /**
     * connects to the mailbox <br/>
     * if already connected is has no negative impact
     * 
     * @return
     * @throws MessagingException
     */
    public Mailbox connect() throws MessagingException {
        if (store != null && store.isConnected()) {
            log.info("Already connected to mail server " + host + " with user " + user);
            return this;
        }

        log.info("Connection to mail server " + host + " with user " + user);
        Session session = Session.getDefaultInstance(new Properties(), null);
        session.setDebug(false);

        // add handlers for main MIME types
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);

        store = session.getStore("imap");
        store.connect(host, user, password);
        return this;
    }

    /**
     * return the <b>x</b> first mails
     * 
     * @param int count - x
     */
    public List<Email> getAllMails(final int count) throws MessagingException, IOException {
        return onlineSearch().processOnlyLast(count).getMails();
    }

    /**
     * return all mails in the folder
     */
    public List<Email> getAllMails() throws MessagingException, IOException {
        return onlineSearch().getMails();
    }

    /**
     * open the folder read-only
     * <p/>
     * lazy connect if necessary
     */
    public Folder openFolder() throws MessagingException {
        log.info("Open mail folder: " + foldername);

        connect();

        Folder folder = store.getFolder(foldername);
        folder.open(Folder.READ_ONLY);
        log.info("Opened folder: " + folder.getFullName());

        return folder;
    }

    /**
     * return a filter for filtering online
     * <p/>
     * is faster on large volume
     * 
     * @return
     * @throws MessagingException
     */
    public OnlineFilter onlineSearch() throws MessagingException {
        OnlineFilter filter = new OnlineFilter();
        filter.setMailBox(this);
        return filter;
    }

    /**
     * wait at maximum this time to get a nonzero list as result
     * 
     * @param waitTime
     * @return
     */
    public MailWaiter wait(final int waitTime) {
        return new MailWaiter(waitTime, 1000, this);
    }

    /**
     * a waiter to handle asynchronuous mail events
     * 
     
     */
    public class MailWaiter {

        private final int waitTime;
        private final int intervall;
        private final Mailbox mailbox;

        /**
         * @param waitTime
         * @param mailbox
         */
        public MailWaiter(final int waitTime, final int intervall, final Mailbox mailbox) {
            this.waitTime = waitTime;
            this.intervall = intervall;
            this.mailbox = mailbox;
            log.info("using MailWaiter waiting " + waitTime + "ms and check every " + intervall + "ms for new results");
        }

        /**
         * waits until results are ready to fetch
         * 
         * @param onlinefilter - using for online filtering
         * @param offline queries - filtering already fetched to reduce resultset
         * @return
         * @throws MessagingException
         * @throws IOException
         */
        public List<Email> forResults(final OnlineFilter filter, final OfflineQuery... queries)
                throws MessagingException, IOException {
            List<Email> results = null;

            int waitedTime = 0;

            filter.setMailBox(mailbox);

            while (waitedTime < waitTime && (results == null || results.isEmpty())) {
                results = filter.filterOffline(queries).getMails();
                if (results == null || results.size() == 0) {
                    try {
                        Thread.sleep(intervall);
                        waitedTime += intervall;
                    } catch (InterruptedException e) {
                        // ok
                    }
                }
            }

            if (waitedTime >= waitTime && (results == null || results.size() == 0)) {
                log.error("timeout (" + waitTime + "ms) ran out : no expected mail");
            }

            return results;
        }
    }
}