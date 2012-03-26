//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.19 at 07:09:03 PM CEST 
//


package biz.company.api;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Java class for priceRange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="priceRange">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://api.company.biz}reference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "priceRange", propOrder = {
    "from",
    "to",
    "currency"
})
public class PriceRange
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected BigDecimal from;
    protected BigDecimal to;
    protected Reference currency;

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFrom(BigDecimal value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTo(BigDecimal value) {
        this.to = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setCurrency(Reference value) {
        this.currency = value;
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
            BigDecimal theFrom;
            theFrom = this.getFrom();
            strategy.appendField(locator, this, "from", buffer, theFrom);
        }
        {
            BigDecimal theTo;
            theTo = this.getTo();
            strategy.appendField(locator, this, "to", buffer, theTo);
        }
        {
            Reference theCurrency;
            theCurrency = this.getCurrency();
            strategy.appendField(locator, this, "currency", buffer, theCurrency);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PriceRange)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PriceRange that = ((PriceRange) object);
        {
            BigDecimal lhsFrom;
            lhsFrom = this.getFrom();
            BigDecimal rhsFrom;
            rhsFrom = that.getFrom();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "from", lhsFrom), LocatorUtils.property(thatLocator, "from", rhsFrom), lhsFrom, rhsFrom)) {
                return false;
            }
        }
        {
            BigDecimal lhsTo;
            lhsTo = this.getTo();
            BigDecimal rhsTo;
            rhsTo = that.getTo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "to", lhsTo), LocatorUtils.property(thatLocator, "to", rhsTo), lhsTo, rhsTo)) {
                return false;
            }
        }
        {
            Reference lhsCurrency;
            lhsCurrency = this.getCurrency();
            Reference rhsCurrency;
            rhsCurrency = that.getCurrency();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "currency", lhsCurrency), LocatorUtils.property(thatLocator, "currency", rhsCurrency), lhsCurrency, rhsCurrency)) {
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
            BigDecimal theFrom;
            theFrom = this.getFrom();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "from", theFrom), currentHashCode, theFrom);
        }
        {
            BigDecimal theTo;
            theTo = this.getTo();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "to", theTo), currentHashCode, theTo);
        }
        {
            Reference theCurrency;
            theCurrency = this.getCurrency();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "currency", theCurrency), currentHashCode, theCurrency);
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
        if (draftCopy instanceof PriceRange) {
            final PriceRange copy = ((PriceRange) draftCopy);
            if (this.from!= null) {
                BigDecimal sourceFrom;
                sourceFrom = this.getFrom();
                BigDecimal copyFrom = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "from", sourceFrom), sourceFrom));
                copy.setFrom(copyFrom);
            } else {
                copy.from = null;
            }
            if (this.to!= null) {
                BigDecimal sourceTo;
                sourceTo = this.getTo();
                BigDecimal copyTo = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "to", sourceTo), sourceTo));
                copy.setTo(copyTo);
            } else {
                copy.to = null;
            }
            if (this.currency!= null) {
                Reference sourceCurrency;
                sourceCurrency = this.getCurrency();
                Reference copyCurrency = ((Reference) strategy.copy(LocatorUtils.property(locator, "currency", sourceCurrency), sourceCurrency));
                copy.setCurrency(copyCurrency);
            } else {
                copy.currency = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new PriceRange();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof PriceRange) {
            final PriceRange target = this;
            final PriceRange leftObject = ((PriceRange) left);
            final PriceRange rightObject = ((PriceRange) right);
            {
                BigDecimal lhsFrom;
                lhsFrom = leftObject.getFrom();
                BigDecimal rhsFrom;
                rhsFrom = rightObject.getFrom();
                BigDecimal mergedFrom = ((BigDecimal) strategy.merge(LocatorUtils.property(leftLocator, "from", lhsFrom), LocatorUtils.property(rightLocator, "from", rhsFrom), lhsFrom, rhsFrom));
                target.setFrom(mergedFrom);
            }
            {
                BigDecimal lhsTo;
                lhsTo = leftObject.getTo();
                BigDecimal rhsTo;
                rhsTo = rightObject.getTo();
                BigDecimal mergedTo = ((BigDecimal) strategy.merge(LocatorUtils.property(leftLocator, "to", lhsTo), LocatorUtils.property(rightLocator, "to", rhsTo), lhsTo, rhsTo));
                target.setTo(mergedTo);
            }
            {
                Reference lhsCurrency;
                lhsCurrency = leftObject.getCurrency();
                Reference rhsCurrency;
                rhsCurrency = rightObject.getCurrency();
                Reference mergedCurrency = ((Reference) strategy.merge(LocatorUtils.property(leftLocator, "currency", lhsCurrency), LocatorUtils.property(rightLocator, "currency", rhsCurrency), lhsCurrency, rhsCurrency));
                target.setCurrency(mergedCurrency);
            }
        }
    }

    public PriceRange withFrom(BigDecimal value) {
        setFrom(value);
        return this;
    }

    public PriceRange withTo(BigDecimal value) {
        setTo(value);
        return this;
    }

    public PriceRange withCurrency(Reference value) {
        setCurrency(value);
        return this;
    }

}