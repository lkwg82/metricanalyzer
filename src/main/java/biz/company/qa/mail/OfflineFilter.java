/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.mail;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * filter for fetched emails to reduce the result set
 * <p/>
 * is the <b>offline</b> filter in contrast to {@link OnlineFilter} Filter
 * <ul>
 * <li>Online (before downloading)</li>
 * <li>Offline (after downloading)</li>
 * </ul>
 * 
 
 */
public class OfflineFilter {

    private final static Logger log = LoggerFactory.getLogger(OfflineFilter.class);

    private final List<Email> mails;
    private final OfflineQuery[] queries;

    /**
     * @param mails - result set from online search
     * @param queries - queries for offline filter
     */
    public OfflineFilter(final List<Email> mails, final OfflineQuery... queries) {
        this.mails = mails;
        this.queries = queries;
    }

    /**
     * chaining the offline queries
     * 
     * @param queries
     * @return
     */
    public OfflineFilter filter(final OfflineQuery... queries) {
        return new OfflineFilter(getMails(), queries);
    }

    /**
     * filter fetched mails sequentially with the offline queries to reduce the result set
     * 
     * @return
     */
    public List<Email> getMails() {
        List<Email> result = mails;
        for (OfflineQuery query : queries) {
            result = filter(result, query);
            log.debug("filtered " + result.size() + " of " + mails.size());
        }
        return result;
    }

    /**
     * filter the list with a single filter
     * 
     * @param emails
     * @param query
     * @return
     */
    private List<Email> filter(final List<Email> emails, final OfflineQuery query) {
        List<Email> result = new ArrayList<Email>();
        for (Email mail : emails) {
            if (query.match(mail)) {
                result.add(mail);
            }
        }
        return result;
    }

}