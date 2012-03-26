/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.api;

import javax.xml.bind.JAXBContext;

import biz.company.api.ObjectFactory;

/**
 
 */
public class HttpCallCommandFactory {
    private HttpUrlConnectionFactory connectionFactory = null;
    private JAXBContext jaxbContext = null;

    public HttpCallCommandFactory(final HttpUrlConnectionFactory connectionFactory) throws Exception {
        this(connectionFactory, "biz.company.api");

    }

    public HttpCallCommandFactory(final HttpUrlConnectionFactory connectionFactory, final String contextPath)
            throws Exception {
        this.connectionFactory = connectionFactory;
        jaxbContext = JAXBContext.newInstance(contextPath, new ObjectFactory().getClass().getClassLoader());
    }

    public HttpUrlConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(final HttpUrlConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public JAXBContext getJaxbContext() {
        return jaxbContext;
    }

    public void setJaxbContext(final JAXBContext jaxbContext) {
        this.jaxbContext = jaxbContext;
    }

    public HttpCallCommand createPlainHttpCallCommand(final String url, final HttpMethod method,
            final HttpMethod tunneledMethod) {
        return new PlainHttpCallCommand(connectionFactory, url, method, tunneledMethod, false, false, false);
    }

    public HttpCallCommand createJaxbHttpCallCommand(final String url, final HttpMethod method,
            final HttpMethod tunneledMethod) {
        return new JAXBHttpCallCommand(connectionFactory, jaxbContext, url, method, tunneledMethod, false, false, false);
    }
}
