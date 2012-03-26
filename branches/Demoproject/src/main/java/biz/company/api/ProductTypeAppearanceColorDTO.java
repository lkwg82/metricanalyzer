//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.19 at 07:09:03 PM CEST 
//


package biz.company.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
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
 * <p>Java class for productTypeAppearanceColorDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productTypeAppearanceColorDTO">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="index" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productTypeAppearanceColorDTO", propOrder = {
    "value"
})
public class ProductTypeAppearanceColorDTO
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlValue
    protected String value;
    @XmlAttribute(name = "index", required = true)
    protected int index;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the index property.
     * 
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     */
    public void setIndex(int value) {
        this.index = value;
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
            String theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue);
        }
        {
            int theIndex;
            theIndex = (true?this.getIndex(): 0);
            strategy.appendField(locator, this, "index", buffer, theIndex);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductTypeAppearanceColorDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductTypeAppearanceColorDTO that = ((ProductTypeAppearanceColorDTO) object);
        {
            String lhsValue;
            lhsValue = this.getValue();
            String rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue)) {
                return false;
            }
        }
        {
            int lhsIndex;
            lhsIndex = (true?this.getIndex(): 0);
            int rhsIndex;
            rhsIndex = (true?that.getIndex(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "index", lhsIndex), LocatorUtils.property(thatLocator, "index", rhsIndex), lhsIndex, rhsIndex)) {
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
            String theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue);
        }
        {
            int theIndex;
            theIndex = (true?this.getIndex(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "index", theIndex), currentHashCode, theIndex);
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
        if (draftCopy instanceof ProductTypeAppearanceColorDTO) {
            final ProductTypeAppearanceColorDTO copy = ((ProductTypeAppearanceColorDTO) draftCopy);
            if (this.value!= null) {
                String sourceValue;
                sourceValue = this.getValue();
                String copyValue = ((String) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue));
                copy.setValue(copyValue);
            } else {
                copy.value = null;
            }
            int sourceIndex;
            sourceIndex = (true?this.getIndex(): 0);
            int copyIndex = strategy.copy(LocatorUtils.property(locator, "index", sourceIndex), sourceIndex);
            copy.setIndex(copyIndex);
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new ProductTypeAppearanceColorDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof ProductTypeAppearanceColorDTO) {
            final ProductTypeAppearanceColorDTO target = this;
            final ProductTypeAppearanceColorDTO leftObject = ((ProductTypeAppearanceColorDTO) left);
            final ProductTypeAppearanceColorDTO rightObject = ((ProductTypeAppearanceColorDTO) right);
            {
                String lhsValue;
                lhsValue = leftObject.getValue();
                String rhsValue;
                rhsValue = rightObject.getValue();
                String mergedValue = ((String) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue));
                target.setValue(mergedValue);
            }
            {
                int lhsIndex;
                lhsIndex = (true?leftObject.getIndex(): 0);
                int rhsIndex;
                rhsIndex = (true?rightObject.getIndex(): 0);
                int mergedIndex = ((int) strategy.merge(LocatorUtils.property(leftLocator, "index", lhsIndex), LocatorUtils.property(rightLocator, "index", rhsIndex), lhsIndex, rhsIndex));
                target.setIndex(mergedIndex);
            }
        }
    }

    public ProductTypeAppearanceColorDTO withValue(String value) {
        setValue(value);
        return this;
    }

    public ProductTypeAppearanceColorDTO withIndex(int value) {
        setIndex(value);
        return this;
    }

}
