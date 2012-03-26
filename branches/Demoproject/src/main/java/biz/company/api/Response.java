//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.19 at 07:09:03 PM CEST 
//


package biz.company.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.MergeStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{http://api.company.biz}status" minOccurs="0"/>
 *         &lt;element name="headers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="header" type="{http://api.company.biz}header" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="payload" type="{http://api.company.biz}payload" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response", propOrder = {
    "status",
    "headers",
    "payload"
})
public class Response
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected Status status;
    protected Response.Headers headers;
    protected Payload payload;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the headers property.
     * 
     * @return
     *     possible object is
     *     {@link Response.Headers }
     *     
     */
    public Response.Headers getHeaders() {
        return headers;
    }

    /**
     * Sets the value of the headers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Response.Headers }
     *     
     */
    public void setHeaders(Response.Headers value) {
        this.headers = value;
    }

    /**
     * Gets the value of the payload property.
     * 
     * @return
     *     possible object is
     *     {@link Payload }
     *     
     */
    public Payload getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link Payload }
     *     
     */
    public void setPayload(Payload value) {
        this.payload = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            Status theStatus;
            theStatus = this.getStatus();
            strategy.appendField(locator, this, "status", buffer, theStatus);
        }
        {
            Response.Headers theHeaders;
            theHeaders = this.getHeaders();
            strategy.appendField(locator, this, "headers", buffer, theHeaders);
        }
        {
            Payload thePayload;
            thePayload = this.getPayload();
            strategy.appendField(locator, this, "payload", buffer, thePayload);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Response)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Response that = ((Response) object);
        {
            Status lhsStatus;
            lhsStatus = this.getStatus();
            Status rhsStatus;
            rhsStatus = that.getStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "status", lhsStatus), LocatorUtils.property(thatLocator, "status", rhsStatus), lhsStatus, rhsStatus)) {
                return false;
            }
        }
        {
            Response.Headers lhsHeaders;
            lhsHeaders = this.getHeaders();
            Response.Headers rhsHeaders;
            rhsHeaders = that.getHeaders();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "headers", lhsHeaders), LocatorUtils.property(thatLocator, "headers", rhsHeaders), lhsHeaders, rhsHeaders)) {
                return false;
            }
        }
        {
            Payload lhsPayload;
            lhsPayload = this.getPayload();
            Payload rhsPayload;
            rhsPayload = that.getPayload();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "payload", lhsPayload), LocatorUtils.property(thatLocator, "payload", rhsPayload), lhsPayload, rhsPayload)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            Status theStatus;
            theStatus = this.getStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus);
        }
        {
            Response.Headers theHeaders;
            theHeaders = this.getHeaders();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "headers", theHeaders), currentHashCode, theHeaders);
        }
        {
            Payload thePayload;
            thePayload = this.getPayload();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "payload", thePayload), currentHashCode, thePayload);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof Response) {
            final Response copy = ((Response) draftCopy);
            if (this.status!= null) {
                Status sourceStatus;
                sourceStatus = this.getStatus();
                Status copyStatus = ((Status) strategy.copy(LocatorUtils.property(locator, "status", sourceStatus), sourceStatus));
                copy.setStatus(copyStatus);
            } else {
                copy.status = null;
            }
            if (this.headers!= null) {
                Response.Headers sourceHeaders;
                sourceHeaders = this.getHeaders();
                Response.Headers copyHeaders = ((Response.Headers) strategy.copy(LocatorUtils.property(locator, "headers", sourceHeaders), sourceHeaders));
                copy.setHeaders(copyHeaders);
            } else {
                copy.headers = null;
            }
            if (this.payload!= null) {
                Payload sourcePayload;
                sourcePayload = this.getPayload();
                Payload copyPayload = ((Payload) strategy.copy(LocatorUtils.property(locator, "payload", sourcePayload), sourcePayload));
                copy.setPayload(copyPayload);
            } else {
                copy.payload = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new Response();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof Response) {
            final Response target = this;
            final Response leftObject = ((Response) left);
            final Response rightObject = ((Response) right);
            {
                Status lhsStatus;
                lhsStatus = leftObject.getStatus();
                Status rhsStatus;
                rhsStatus = rightObject.getStatus();
                Status mergedStatus = ((Status) strategy.merge(LocatorUtils.property(leftLocator, "status", lhsStatus), LocatorUtils.property(rightLocator, "status", rhsStatus), lhsStatus, rhsStatus));
                target.setStatus(mergedStatus);
            }
            {
                Response.Headers lhsHeaders;
                lhsHeaders = leftObject.getHeaders();
                Response.Headers rhsHeaders;
                rhsHeaders = rightObject.getHeaders();
                Response.Headers mergedHeaders = ((Response.Headers) strategy.merge(LocatorUtils.property(leftLocator, "headers", lhsHeaders), LocatorUtils.property(rightLocator, "headers", rhsHeaders), lhsHeaders, rhsHeaders));
                target.setHeaders(mergedHeaders);
            }
            {
                Payload lhsPayload;
                lhsPayload = leftObject.getPayload();
                Payload rhsPayload;
                rhsPayload = rightObject.getPayload();
                Payload mergedPayload = ((Payload) strategy.merge(LocatorUtils.property(leftLocator, "payload", lhsPayload), LocatorUtils.property(rightLocator, "payload", rhsPayload), lhsPayload, rhsPayload));
                target.setPayload(mergedPayload);
            }
        }
    }

    public Response withStatus(Status value) {
        setStatus(value);
        return this;
    }

    public Response withHeaders(Response.Headers value) {
        setHeaders(value);
        return this;
    }

    public Response withPayload(Payload value) {
        setPayload(value);
        return this;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="header" type="{http://api.company.biz}header" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "header"
    })
    public static class Headers
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        protected List<Header> header;

        /**
         * Gets the value of the header property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the header property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHeader().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Header }
         * 
         * 
         */
        public List<Header> getHeader() {
            if (header == null) {
                header = new ArrayList<Header>();
            }
            return this.header;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                List<Header> theHeader;
                theHeader = (((this.header!= null)&&(!this.header.isEmpty()))?this.getHeader():null);
                strategy.appendField(locator, this, "header", buffer, theHeader);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Response.Headers)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Response.Headers that = ((Response.Headers) object);
            {
                List<Header> lhsHeader;
                lhsHeader = (((this.header!= null)&&(!this.header.isEmpty()))?this.getHeader():null);
                List<Header> rhsHeader;
                rhsHeader = (((that.header!= null)&&(!that.header.isEmpty()))?that.getHeader():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "header", lhsHeader), LocatorUtils.property(thatLocator, "header", rhsHeader), lhsHeader, rhsHeader)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                List<Header> theHeader;
                theHeader = (((this.header!= null)&&(!this.header.isEmpty()))?this.getHeader():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "header", theHeader), currentHashCode, theHeader);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public Object clone() {
            return copyTo(createNewInstance());
        }

        public Object copyTo(Object target) {
            final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
            return copyTo(null, target, strategy);
        }

        public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
            final Object draftCopy = ((target == null)?createNewInstance():target);
            if (draftCopy instanceof Response.Headers) {
                final Response.Headers copy = ((Response.Headers) draftCopy);
                if ((this.header!= null)&&(!this.header.isEmpty())) {
                    List<Header> sourceHeader;
                    sourceHeader = (((this.header!= null)&&(!this.header.isEmpty()))?this.getHeader():null);
                    @SuppressWarnings("unchecked")
                    List<Header> copyHeader = ((List<Header> ) strategy.copy(LocatorUtils.property(locator, "header", sourceHeader), sourceHeader));
                    copy.header = null;
                    if (copyHeader!= null) {
                        List<Header> uniqueHeaderl = copy.getHeader();
                        uniqueHeaderl.addAll(copyHeader);
                    }
                } else {
                    copy.header = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new Response.Headers();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof Response.Headers) {
                final Response.Headers target = this;
                final Response.Headers leftObject = ((Response.Headers) left);
                final Response.Headers rightObject = ((Response.Headers) right);
                {
                    List<Header> lhsHeader;
                    lhsHeader = (((leftObject.header!= null)&&(!leftObject.header.isEmpty()))?leftObject.getHeader():null);
                    List<Header> rhsHeader;
                    rhsHeader = (((rightObject.header!= null)&&(!rightObject.header.isEmpty()))?rightObject.getHeader():null);
                    List<Header> mergedHeader = ((List<Header> ) strategy.merge(LocatorUtils.property(leftLocator, "header", lhsHeader), LocatorUtils.property(rightLocator, "header", rhsHeader), lhsHeader, rhsHeader));
                    target.header = null;
                    if (mergedHeader!= null) {
                        List<Header> uniqueHeaderl = target.getHeader();
                        uniqueHeaderl.addAll(mergedHeader);
                    }
                }
            }
        }

        public Response.Headers withHeader(Header... values) {
            if (values!= null) {
                for (Header value: values) {
                    getHeader().add(value);
                }
            }
            return this;
        }

        public Response.Headers withHeader(Collection<Header> values) {
            if (values!= null) {
                getHeader().addAll(values);
            }
            return this;
        }

    }

}