/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.api;

import java.net.HttpURLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;

/**
 
 */
public class JAXBHttpCallCommand extends AbstractHttpCallCommand {
    private JAXBContext jaxbContext = null;

    public JAXBHttpCallCommand(final HttpUrlConnectionFactory connectionFactory, final JAXBContext jaxbContext,
            final String url, final HttpMethod method, final HttpMethod tunneledMethod, final boolean apiKeyProtected,
            final boolean sessionIdProtected, final boolean headerAllowed) {
        super(connectionFactory, url, method, tunneledMethod, apiKeyProtected, sessionIdProtected, headerAllowed);
        this.jaxbContext = jaxbContext;
    }

    @Override
    protected void readOutput(final HttpURLConnection connection) throws Exception {
        output = jaxbContext.createUnmarshaller().unmarshal(connection.getInputStream());
    }

    @Override
    protected void writeInput(final HttpURLConnection connection) throws Exception {
        jaxbContext.createMarshaller().marshal(input, System.out);
        jaxbContext.createMarshaller().marshal(input, connection.getOutputStream());
    }

    @SuppressWarnings("rawtypes")
    public JAXBElement getJaxbOutput() {
        return (output == null) ? null : (JAXBElement) output;
    }
}