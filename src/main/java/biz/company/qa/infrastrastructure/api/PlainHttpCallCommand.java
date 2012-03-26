/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.api;

import java.net.HttpURLConnection;

/**
 
 */
public class PlainHttpCallCommand extends AbstractHttpCallCommand {
    public PlainHttpCallCommand(final HttpUrlConnectionFactory connectionFactory, final String url,
            final HttpMethod method, final HttpMethod tunneledMethod, final boolean apiKeyProtected,
            final boolean sessionIdProtected, final boolean headerAllowed) {
        super(connectionFactory, url, method, tunneledMethod, apiKeyProtected, sessionIdProtected, headerAllowed);
    }

    @Override
    protected void readOutput(final HttpURLConnection connection) throws Exception {
        output = getResponseMessage(connection);
    }

    @Override
    protected void writeInput(final HttpURLConnection connection) throws Exception {
        connection.getOutputStream().write(input.toString().getBytes());
    }
}
