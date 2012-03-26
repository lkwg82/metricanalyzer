/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 
 */
public class HttpUrlConnectionFactory {
    private static final Logger log = LoggerFactory.getLogger(HttpUrlConnectionFactory.class);

    private static final String company_AUTH = "companyAuth";
    public static final String API_KEY = "apiKey";
    public static final String SIG = "sig";
    public static final String DATA = "data";
    public static final String TIME = "time";
    public static final String SESSION_ID = "sessionId";

    private String apiKey;
    private String secret;
    private String sessionId;

    public HttpUrlConnectionFactory() {
    }

    public HttpUrlConnectionFactory(final String apiKey, final String secret, final String sessionId) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.sessionId = sessionId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(final String secret) {
        this.secret = secret;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }

    public HttpURLConnection createHttpConnection(final String theUrl, final HttpMethod method,
            final HttpMethod tunneledMethod) throws Exception {
        return createHttpConnection(theUrl, method, tunneledMethod, false, false, false);
    }

    public HttpURLConnection createHttpConnection(String theUrl, final HttpMethod method,
            final HttpMethod tunneledMethod, final boolean apiKeyProtected, final boolean sessionIdProtected,
            final boolean headerAllowed) throws Exception {
        String query = (theUrl.indexOf('?') == -1) ? "" : theUrl.substring(theUrl.indexOf('?') + 1);
        if (apiKeyProtected) {
            if (query.contains(API_KEY) || query.contains(SIG) || query.contains(TIME)) {
                throw new IllegalArgumentException("URL to be secured must not contain " + "" + API_KEY + " or " + SIG
                        + " or " + TIME + "!");
            }
            if (apiKey == null || secret == null) {
                throw new IllegalArgumentException("APIKey or secret not found!");
            }
        }
        if (sessionIdProtected) {
            if (query.contains(SESSION_ID)) {
                throw new IllegalArgumentException("URL to be secured must not contain " + "" + SESSION_ID + "!");
            }
            if (sessionId == null) {
                throw new IllegalArgumentException("SessionId not found!");
            }
        }

        String time = Long.toString(System.currentTimeMillis());
        String companyAuth = company_AUTH + " ";
        if (headerAllowed) {
            if (apiKeyProtected) {
                companyAuth += " " + API_KEY + "=\"" + apiKey + "\", " + DATA + "=\""
                        + calculateData(theUrl, method, tunneledMethod, time) + "\", " + SIG + "=\""
                        + calculateSignature(theUrl, method, tunneledMethod, time) + "\"";
            }
            if (sessionIdProtected) {
                if (apiKeyProtected) {
                    companyAuth += "\", ";
                }
                companyAuth += " " + SESSION_ID + "=\"" + sessionId + "\"";
            }
        } else {
            if (apiKeyProtected || sessionIdProtected) {
                theUrl = (theUrl.indexOf('?') != -1) ? theUrl : theUrl + "?";
            }
            if (apiKeyProtected) {
                if (theUrl.charAt(theUrl.length() - 1) != '?') {
                    theUrl += "&";
                }
                theUrl += API_KEY + "=" + apiKey + "&" + SIG + "="
                        + calculateSignature(theUrl, method, tunneledMethod, time) + "&" + TIME + "=" + time;
            }
            if (sessionIdProtected) {
                if (theUrl.charAt(theUrl.length() - 1) != '?') {
                    theUrl += "&";
                }
                theUrl += "&" + SESSION_ID + "=" + sessionId;
            }
        }

        log.info("Url is {} {}", method.name(), theUrl);

        if (tunneledMethod != null) {
            theUrl += (theUrl.indexOf("?") == -1) ? "?" : "&";
            theUrl += "method=" + tunneledMethod.toString();
        }

        HttpURLConnection connection = null;
        try {
            URL url = new URL(theUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setRequestMethod(method.name());
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(10000);

            if (headerAllowed && (apiKeyProtected || sessionIdProtected)) {
                log.info(company_AUTH + " header is " + companyAuth);
                connection.setRequestProperty("Authorization", companyAuth);
            }

            // connection.setRequestProperty("Content-Encoding", "gzip");

            return connection;
        } catch (IOException e) {
            if (connection != null) {
                connection.disconnect();
            }
            throw e;
        }
    }

    private String calculateSignature(final String theUrl, final HttpMethod method, final HttpMethod tunneledMethod,
            final String time) {
        return SHA1.getHashAsHex(calculateData(theUrl, method, tunneledMethod, time) + " " + secret);
    }

    private String calculateData(final String theUrl, HttpMethod method, final HttpMethod tunneledMethod,
            final String time) {
        if (tunneledMethod != null) {
            method = tunneledMethod;
        }
        return method + " " + ((theUrl.indexOf('?') != -1) ? theUrl.substring(0, theUrl.indexOf('?')) : theUrl) + " "
                + time;
    }
}
