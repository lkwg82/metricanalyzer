/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 
 */
public abstract class AbstractHttpCallCommand implements HttpCallCommand {
    private static final Logger log = LoggerFactory.getLogger(AbstractHttpCallCommand.class);

    protected HttpUrlConnectionFactory connectionFactory = null;
    protected String url;
    protected HttpMethod method;
    protected HttpMethod tunneledMethod;
    protected boolean apiKeyProtected;
    protected boolean sessionIdProtected;
    protected boolean headerAllowed;
    protected String acceptType = null;
    protected Object input;
    protected Object output;
    private String errorMessage;
    private int status;
    private String location;
    private String contentType;

    public AbstractHttpCallCommand(final HttpUrlConnectionFactory connectionFactory, final String url,
            final HttpMethod method, final HttpMethod tunneledMethod, final boolean apiKeyProtected,
            final boolean sessionIdProtected, final boolean headerAllowed) {
        this.sessionIdProtected = sessionIdProtected;
        this.connectionFactory = connectionFactory;
        this.apiKeyProtected = apiKeyProtected;
        this.url = url;
        this.headerAllowed = headerAllowed;
        this.method = method;
        this.tunneledMethod = tunneledMethod;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(final String url) {
        this.url = url;
    }

    @Override
    public HttpMethod getMethod() {
        return method;
    }

    @Override
    public void setMethod(final HttpMethod method) {
        this.method = method;
    }

    @Override
    public HttpMethod getTunneledMethod() {
        return tunneledMethod;
    }

    @Override
    public void setTunneledMethod(final HttpMethod tunneledMethod) {
        this.tunneledMethod = tunneledMethod;
    }

    @Override
    public boolean isApiKeyProtected() {
        return apiKeyProtected;
    }

    @Override
    public void setApiKeyProtected(final boolean apiKeyProtected) {
        this.apiKeyProtected = apiKeyProtected;
    }

    @Override
    public boolean isSessionIdProtected() {
        return sessionIdProtected;
    }

    @Override
    public void setSessionIdProtected(final boolean sessionIdProtected) {
        this.sessionIdProtected = sessionIdProtected;
    }

    @Override
    public boolean isHeaderAllowed() {
        return headerAllowed;
    }

    @Override
    public void setHeaderAllowed(final boolean headerAllowed) {
        this.headerAllowed = headerAllowed;
    }

    @Override
    public String getAcceptType() {
        return acceptType;
    }

    @Override
    public void setAcceptType(final String acceptType) {
        this.acceptType = acceptType;
    }

    @Override
    public Object getInput() {
        return input;
    }

    @Override
    public void setInput(final Object input) {
        this.input = input;
    }

    @Override
    public Object getOutput() {
        return output;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public boolean didErrorOccur() {
        return (status == -1 || status >= 400);
    }

    @Override
    public void execute() {
        errorMessage = "";
        status = -1;
        output = null;
        location = null;
        contentType = null;

        HttpURLConnection connection = null;
        try {
            connection = connectionFactory.createHttpConnection(url, method, tunneledMethod, apiKeyProtected,
                    sessionIdProtected, headerAllowed);
            if (input != null && (HttpMethod.POST.equals(method) || HttpMethod.PUT.equals(method))) {
                writeInput(connection);
            }
            if (acceptType != null) {
                connection.setRequestProperty("Accept", acceptType);
            }
            connection.connect();
            status = connection.getResponseCode();
            contentType = connection.getHeaderField("Content-Type");
            if (status >= 400) {
                errorMessage = getResponseMessage(connection);
            }
            if (status == 201) {
                location = connection.getHeaderField("Location");
            }
            if (status == 200) {
                if (HttpMethod.GET.equals(method)
                        || (HttpMethod.POST.equals(method) && !(HttpMethod.PUT.equals(tunneledMethod) || HttpMethod.DELETE
                                .equals(tunneledMethod))) || HttpMethod.OPTIONS.equals(method)) {
                    readOutput(connection);
                }
            }
        } catch (Exception e) {
            log.error("Connection could not be established!", e);
            try {
                errorMessage = getResponseMessage(connection);
            } catch (IOException e1) {
                log.error("Error message could not be read!", e);
                errorMessage = "Error message could not be read!";
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    protected abstract void readOutput(HttpURLConnection connection) throws Exception;

    protected abstract void writeInput(HttpURLConnection connection) throws Exception;

    protected String getResponseMessage(final HttpURLConnection connection) throws IOException {
        InputStream in = (connection.getResponseCode() < 400) ? connection.getInputStream() : connection
                .getErrorStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int redBytes = 0;
        while ((redBytes = in.read(bytes)) != -1) {
            out.write(bytes, 0, redBytes);
        }
        in.close();
        out.close();
        return out.toString("UTF-8");
    }
}
