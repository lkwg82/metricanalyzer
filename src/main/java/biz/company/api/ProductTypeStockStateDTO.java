//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.19 at 07:09:03 PM CEST 
//


package biz.company.api;

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
 * <p>Java class for productTypeStockStateDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productTypeStockStateDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appearance" type="{http://api.company.biz}reference"/>
 *         &lt;element name="size" type="{http://api.company.biz}reference"/>
 *         &lt;element name="available" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productTypeStockStateDTO", propOrder = {
    "appearance",
    "size",
    "available"
})
public class ProductTypeStockStateDTO
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected Reference appearance;
    @XmlElement(required = true)
    protected Reference size;
    protected boolean available;

    /**
     * Gets the value of the appearance property.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getAppearance() {
        return appearance;
    }

    /**
     * Sets the value of the appearance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setAppearance(Reference value) {
        this.appearance = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setSize(Reference value) {
        this.size = value;
    }

    /**
     * Gets the value of the available property.
     * 
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the value of the available property.
     * 
     */
    public void setAvailable(boolean value) {
        this.available = value;
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
            Reference theAppearance;
            theAppearance = this.getAppearance();
            strategy.appendField(locator, this, "appearance", buffer, theAppearance);
        }
        {
            Reference theSize;
            theSize = this.getSize();
            strategy.appendField(locator, this, "size", buffer, theSize);
        }
        {
            boolean theAvailable;
            theAvailable = (true?this.isAvailable():false);
            strategy.appendField(locator, this, "available", buffer, theAvailable);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductTypeStockStateDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductTypeStockStateDTO that = ((ProductTypeStockStateDTO) object);
        {
            Reference lhsAppearance;
            lhsAppearance = this.getAppearance();
            Reference rhsAppearance;
            rhsAppearance = that.getAppearance();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "appearance", lhsAppearance), LocatorUtils.property(thatLocator, "appearance", rhsAppearance), lhsAppearance, rhsAppearance)) {
                return false;
            }
        }
        {
            Reference lhsSize;
            lhsSize = this.getSize();
            Reference rhsSize;
            rhsSize = that.getSize();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "size", lhsSize), LocatorUtils.property(thatLocator, "size", rhsSize), lhsSize, rhsSize)) {
                return false;
            }
        }
        {
            boolean lhsAvailable;
            lhsAvailable = (true?this.isAvailable():false);
            boolean rhsAvailable;
            rhsAvailable = (true?that.isAvailable():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "available", lhsAvailable), LocatorUtils.property(thatLocator, "available", rhsAvailable), lhsAvailable, rhsAvailable)) {
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
            Reference theAppearance;
            theAppearance = this.getAppearance();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "appearance", theAppearance), currentHashCode, theAppearance);
        }
        {
            Reference theSize;
            theSize = this.getSize();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "size", theSize), currentHashCode, theSize);
        }
        {
            boolean theAvailable;
            theAvailable = (true?this.isAvailable():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "available", theAvailable), currentHashCode, theAvailable);
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
        if (draftCopy instanceof ProductTypeStockStateDTO) {
            final ProductTypeStockStateDTO copy = ((ProductTypeStockStateDTO) draftCopy);
            if (this.appearance!= null) {
                Reference sourceAppearance;
                sourceAppearance = this.getAppearance();
                Reference copyAppearance = ((Reference) strategy.copy(LocatorUtils.property(locator, "appearance", sourceAppearance), sourceAppearance));
                copy.setAppearance(copyAppearance);
            } else {
                copy.appearance = null;
            }
            if (this.size!= null) {
                Reference sourceSize;
                sourceSize = this.getSize();
                Reference copySize = ((Reference) strategy.copy(LocatorUtils.property(locator, "size", sourceSize), sourceSize));
                copy.setSize(copySize);
            } else {
                copy.size = null;
            }
            boolean sourceAvailable;
            sourceAvailable = (true?this.isAvailable():false);
            boolean copyAvailable = strategy.copy(LocatorUtils.property(locator, "available", sourceAvailable), sourceAvailable);
            copy.setAvailable(copyAvailable);
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new ProductTypeStockStateDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof ProductTypeStockStateDTO) {
            final ProductTypeStockStateDTO target = this;
            final ProductTypeStockStateDTO leftObject = ((ProductTypeStockStateDTO) left);
            final ProductTypeStockStateDTO rightObject = ((ProductTypeStockStateDTO) right);
            {
                Reference lhsAppearance;
                lhsAppearance = leftObject.getAppearance();
                Reference rhsAppearance;
                rhsAppearance = rightObject.getAppearance();
                Reference mergedAppearance = ((Reference) strategy.merge(LocatorUtils.property(leftLocator, "appearance", lhsAppearance), LocatorUtils.property(rightLocator, "appearance", rhsAppearance), lhsAppearance, rhsAppearance));
                target.setAppearance(mergedAppearance);
            }
            {
                Reference lhsSize;
                lhsSize = leftObject.getSize();
                Reference rhsSize;
                rhsSize = rightObject.getSize();
                Reference mergedSize = ((Reference) strategy.merge(LocatorUtils.property(leftLocator, "size", lhsSize), LocatorUtils.property(rightLocator, "size", rhsSize), lhsSize, rhsSize));
                target.setSize(mergedSize);
            }
            {
                boolean lhsAvailable;
                lhsAvailable = (true?leftObject.isAvailable():false);
                boolean rhsAvailable;
                rhsAvailable = (true?rightObject.isAvailable():false);
                boolean mergedAvailable = ((boolean) strategy.merge(LocatorUtils.property(leftLocator, "available", lhsAvailable), LocatorUtils.property(rightLocator, "available", rhsAvailable), lhsAvailable, rhsAvailable));
                target.setAvailable(mergedAvailable);
            }
        }
    }

    public ProductTypeStockStateDTO withAppearance(Reference value) {
        setAppearance(value);
        return this;
    }

    public ProductTypeStockStateDTO withSize(Reference value) {
        setSize(value);
        return this;
    }

    public ProductTypeStockStateDTO withAvailable(boolean value) {
        setAvailable(value);
        return this;
    }

}
