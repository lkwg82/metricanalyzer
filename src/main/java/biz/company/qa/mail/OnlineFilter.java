/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.search.BodyTerm;
import javax.mail.search.RecipientTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * only fetch emails from the mailbox, which match this filter
 * <p/>
 * is the <b>online</b> filter in contrast to {@link OfflineFilter} Filter
 * <ul>
 * <li>Online (before downloading)</li>
 * <li>Offline (after downloading)</li>
 * </ul>
 * 
 
 */
public class OnlineFilter {
    private static final Logger log = LoggerFactory.getLogger(OnlineFilter.class);

    /**
     * messages in the mailbox (online)
     */
    private Message[] messages;

    /**
     * the folder in the mailbox
     */
    private Folder folder;

    private final List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();

    private class FilterConfig {
        private final SearchTerm term;
        private final String name;

        private FilterConfig() {
            // ok
            term = null;
            name = null;
        }

        public FilterConfig(final SearchTerm term, final String name) {
            this.term = term;
            this.name = name;
        }
    }

    public OnlineFilter() {
        // ok
    }

    public OnlineFilter setMailBox(final Mailbox mailbox) throws MessagingException {
        folder = mailbox.openFolder();
        return this;
    }

    /**
     * filter emails with special subjects
     * 
     * @param subject
     * @{see http://download.oracle.com/javaee/6/api/javax/mail/search/SubjectTerm.html}
     */
    public OnlineFilter subject(final String subject) throws MessagingException {
        filterConfigs.add(new FilterConfig(new SubjectTerm(subject), "subject"));
        return this;
    }

    public OnlineFilter recipient(final String emailAddress) throws MessagingException {
        filterConfigs.add(new FilterConfig(new RecipientTerm(Message.RecipientType.TO,
                new InternetAddress(emailAddress)), "recipient"));
        return this;
    }

    /**
     * All parts of the message that are of MIME type "text/*" are searched. The pattern is a simple string that must
     * appear as a substring in the message body.
     * 
     * @param pattern
     * @return
     * @throws MessagingException
     */
    public OnlineFilter bodyTermForPlainText(final String pattern) throws MessagingException {
        filterConfigs.add(new FilterConfig(new BodyTerm(pattern), "body"));
        return this;
    }

    /**
     * process only the last n messages
     * 
     * @param number
     * @return
     * @throws MessagingException
     */
    public OnlineFilter processOnlyLast(final int number) throws MessagingException {

        SearchTerm myLastSearchTerm = new SearchTerm() {

            private static final long serialVersionUID = 448357169473751084L;

            @Override
            public boolean match(final Message arg0) {
                int index = arg0.getMessageNumber();
                try {
                    int total = folder.getMessageCount();
                    int startIndex = Math.max(total - number, 1);
                    int lastIndex = total;
                    return startIndex >= index || index <= lastIndex;
                } catch (MessagingException e) {
                    log.error("MessagingException occured", e);
                }
                return false;
            }

        };
        filterConfigs.add(new FilterConfig(myLastSearchTerm, "lastProcessign"));
        return this;
    }

    /**
     * This method is only used internally to combine all the different SearchTerm classes
     */
    private OnlineFilter searchBy(final SearchTerm term, final String filterName) throws MessagingException {

        // if there was a search before, use the messages
        if (messages == null) {
            messages = folder.search(term);
        } else {
            messages = folder.search(term, messages);
        }

        log.info(messages.length + " mails were found in mailbox when searching for " + filterName + ": "
                + term.toString());

        return this;
    }

    public Email getLatestMail() throws MessagingException, IOException {
        List<Email> emails = getMails();

        return emails.get(emails.size() - 1);
    }

    /**
     * Returns all the messages that remained after the search as a list of Email object
     */
    public List<Email> getMails() throws MessagingException, IOException {

        messages = null;
        if (filterConfigs.isEmpty()) {
            /**
             * search with a NullFilter
             */
            searchBy(new SearchTerm() {

                private static final long serialVersionUID = -597578961568333124L;

                @Override
                public boolean match(final Message arg0) {
                    return true;
                }
            }, "null filter");
        } else {
            for (FilterConfig config : filterConfigs) {
                searchBy(config.term, config.name);
            }
        }

        return new EmailUtil().convertMessagesToEmails(messages);
    }

    /**
     * filter the received messages offline again
     * 
     * @param queries
     * @return
     * @throws MessagingException
     * @throws IOException
     */
    public OfflineFilter filterOffline(final OfflineQuery... queries) throws MessagingException, IOException {
        return new OfflineFilter(getMails(), queries);
    }
}