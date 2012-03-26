//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.19 at 07:09:03 PM CEST 
//


package biz.company.api;

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
 * <p>Java class for productDefaultValuesDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productDefaultValuesDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="defaultView" type="{http://api.company.biz}reference" minOccurs="0"/>
 *         &lt;element name="defaultDesign" type="{http://api.company.biz}reference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productDefaultValuesDTO", propOrder = {
    "defaultView",
    "defaultDesign"
})
public class ProductDefaultValuesDTO
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected Reference defaultView;
    protected Reference defaultDesign;

    /**
     * Gets the value of the defaultView property.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getDefaultView() {
        return defaultView;
    }

    /**
     * Sets the value of the defaultView property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setDefaultView(Reference value) {
        this.defaultView = value;
    }

    /**
     * Gets the value of the defaultDesign property.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getDefaultDesign() {
        return defaultDesign;
    }

    /**
     * Sets the value of the defaultDesign property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setDefaultDesign(Reference value) {
        this.defaultDesign = value;
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
            Reference theDefaultView;
            theDefaultView = this.getDefaultView();
            strategy.appendField(locator, this, "defaultView", buffer, theDefaultView);
        }
        {
            Reference theDefaultDesign;
            theDefaultDesign = this.getDefaultDesign();
            strategy.appendField(locator, this, "defaultDesign", buffer, theDefaultDesign);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductDefaultValuesDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductDefaultValuesDTO that = ((ProductDefaultValuesDTO) object);
        {
            Reference lhsDefaultView;
            lhsDefaultView = this.getDefaultView();
            Reference rhsDefaultView;
            rhsDefaultView = that.getDefaultView();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "defaultView", lhsDefaultView), LocatorUtils.property(thatLocator, "defaultView", rhsDefaultView), lhsDefaultView, rhsDefaultView)) {
                return false;
            }
        }
        {
            Reference lhsDefaultDesign;
            lhsDefaultDesign = this.getDefaultDesign();
            Reference rhsDefaultDesign;
            rhsDefaultDesign = that.getDefaultDesign();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "defaultDesign", lhsDefaultDesign), LocatorUtils.property(thatLocator, "defaultDesign", rhsDefaultDesign), lhsDefaultDesign, rhsDefaultDesign)) {
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
            Reference theDefaultView;
            theDefaultView = this.getDefaultView();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "defaultView", theDefaultView), currentHashCode, theDefaultView);
        }
        {
            Reference theDefaultDesign;
            theDefaultDesign = this.getDefaultDesign();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "defaultDesign", theDefaultDesign), currentHashCode, theDefaultDesign);
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
        if (draftCopy instanceof ProductDefaultValuesDTO) {
            final ProductDefaultValuesDTO copy = ((ProductDefaultValuesDTO) draftCopy);
            if (this.defaultView!= null) {
                Reference sourceDefaultView;
                sourceDefaultView = this.getDefaultView();
                Reference copyDefaultView = ((Reference) strategy.copy(LocatorUtils.property(locator, "defaultView", sourceDefaultView), sourceDefaultView));
                copy.setDefaultView(copyDefaultView);
            } else {
                copy.defaultView = null;
            }
            if (this.defaultDesign!= null) {
                Reference sourceDefaultDesign;
                sourceDefaultDesign = this.getDefaultDesign();
                Reference copyDefaultDesign = ((Reference) strategy.copy(LocatorUtils.property(locator, "defaultDesign", sourceDefaultDesign), sourceDefaultDesign));
                copy.setDefaultDesign(copyDefaultDesign);
            } else {
                copy.defaultDesign = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new ProductDefaultValuesDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof ProductDefaultValuesDTO) {
            final ProductDefaultValuesDTO target = this;
            final ProductDefaultValuesDTO leftObject = ((ProductDefaultValuesDTO) left);
            final ProductDefaultValuesDTO rightObject = ((ProductDefaultValuesDTO) right);
            {
                Reference lhsDefaultView;
                lhsDefaultView = leftObject.getDefaultView();
                Reference rhsDefaultView;
                rhsDefaultView = rightObject.getDefaultView();
                Reference mergedDefaultView = ((Reference) strategy.merge(LocatorUtils.property(leftLocator, "defaultView", lhsDefaultView), LocatorUtils.property(rightLocator, "defaultView", rhsDefaultView), lhsDefaultView, rhsDefaultView));
                target.setDefaultView(mergedDefaultView);
            }
            {
                Reference lhsDefaultDesign;
                lhsDefaultDesign = leftObject.getDefaultDesign();
                Reference rhsDefaultDesign;
                rhsDefaultDesign = rightObject.getDefaultDesign();
                Reference mergedDefaultDesign = ((Reference) strategy.merge(LocatorUtils.property(leftLocator, "defaultDesign", lhsDefaultDesign), LocatorUtils.property(rightLocator, "defaultDesign", rhsDefaultDesign), lhsDefaultDesign, rhsDefaultDesign));
                target.setDefaultDesign(mergedDefaultDesign);
            }
        }
    }

    public ProductDefaultValuesDTO withDefaultView(Reference value) {
        setDefaultView(value);
        return this;
    }

    public ProductDefaultValuesDTO withDefaultDesign(Reference value) {
        setDefaultDesign(value);
        return this;
    }

}
