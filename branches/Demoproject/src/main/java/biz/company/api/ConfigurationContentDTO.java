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
 * <p>Java class for configurationContentDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configurationContentDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://api.company.biz}svg"/>
 *       &lt;/sequence>
 *       &lt;attribute name="unit" type="{http://api.company.biz}unit" />
 *       &lt;attribute name="dpi" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configurationContentDTO", propOrder = {
    "svg"
})
public class ConfigurationContentDTO
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected Svg svg;
    @XmlAttribute(name = "unit")
    protected Unit unit;
    @XmlAttribute(name = "dpi", required = true)
    protected double dpi;

    /**
     * Gets the value of the svg property.
     * 
     * @return
     *     possible object is
     *     {@link Svg }
     *     
     */
    public Svg getSvg() {
        return svg;
    }

    /**
     * Sets the value of the svg property.
     * 
     * @param value
     *     allowed object is
     *     {@link Svg }
     *     
     */
    public void setSvg(Svg value) {
        this.svg = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link Unit }
     *     
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Unit }
     *     
     */
    public void setUnit(Unit value) {
        this.unit = value;
    }

    /**
     * Gets the value of the dpi property.
     * 
     */
    public double getDpi() {
        return dpi;
    }

    /**
     * Sets the value of the dpi property.
     * 
     */
    public void setDpi(double value) {
        this.dpi = value;
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
            Svg theSvg;
            theSvg = this.getSvg();
            strategy.appendField(locator, this, "svg", buffer, theSvg);
        }
        {
            Unit theUnit;
            theUnit = this.getUnit();
            strategy.appendField(locator, this, "unit", buffer, theUnit);
        }
        {
            double theDpi;
            theDpi = (true?this.getDpi(): 0.0D);
            strategy.appendField(locator, this, "dpi", buffer, theDpi);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ConfigurationContentDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ConfigurationContentDTO that = ((ConfigurationContentDTO) object);
        {
            Svg lhsSvg;
            lhsSvg = this.getSvg();
            Svg rhsSvg;
            rhsSvg = that.getSvg();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "svg", lhsSvg), LocatorUtils.property(thatLocator, "svg", rhsSvg), lhsSvg, rhsSvg)) {
                return false;
            }
        }
        {
            Unit lhsUnit;
            lhsUnit = this.getUnit();
            Unit rhsUnit;
            rhsUnit = that.getUnit();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "unit", lhsUnit), LocatorUtils.property(thatLocator, "unit", rhsUnit), lhsUnit, rhsUnit)) {
                return false;
            }
        }
        {
            double lhsDpi;
            lhsDpi = (true?this.getDpi(): 0.0D);
            double rhsDpi;
            rhsDpi = (true?that.getDpi(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dpi", lhsDpi), LocatorUtils.property(thatLocator, "dpi", rhsDpi), lhsDpi, rhsDpi)) {
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
            Svg theSvg;
            theSvg = this.getSvg();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "svg", theSvg), currentHashCode, theSvg);
        }
        {
            Unit theUnit;
            theUnit = this.getUnit();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unit", theUnit), currentHashCode, theUnit);
        }
        {
            double theDpi;
            theDpi = (true?this.getDpi(): 0.0D);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dpi", theDpi), currentHashCode, theDpi);
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
        if (draftCopy instanceof ConfigurationContentDTO) {
            final ConfigurationContentDTO copy = ((ConfigurationContentDTO) draftCopy);
            if (this.svg!= null) {
                Svg sourceSvg;
                sourceSvg = this.getSvg();
                Svg copySvg = ((Svg) strategy.copy(LocatorUtils.property(locator, "svg", sourceSvg), sourceSvg));
                copy.setSvg(copySvg);
            } else {
                copy.svg = null;
            }
            if (this.unit!= null) {
                Unit sourceUnit;
                sourceUnit = this.getUnit();
                Unit copyUnit = ((Unit) strategy.copy(LocatorUtils.property(locator, "unit", sourceUnit), sourceUnit));
                copy.setUnit(copyUnit);
            } else {
                copy.unit = null;
            }
            double sourceDpi;
            sourceDpi = (true?this.getDpi(): 0.0D);
            double copyDpi = strategy.copy(LocatorUtils.property(locator, "dpi", sourceDpi), sourceDpi);
            copy.setDpi(copyDpi);
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new ConfigurationContentDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof ConfigurationContentDTO) {
            final ConfigurationContentDTO target = this;
            final ConfigurationContentDTO leftObject = ((ConfigurationContentDTO) left);
            final ConfigurationContentDTO rightObject = ((ConfigurationContentDTO) right);
            {
                Svg lhsSvg;
                lhsSvg = leftObject.getSvg();
                Svg rhsSvg;
                rhsSvg = rightObject.getSvg();
                Svg mergedSvg = ((Svg) strategy.merge(LocatorUtils.property(leftLocator, "svg", lhsSvg), LocatorUtils.property(rightLocator, "svg", rhsSvg), lhsSvg, rhsSvg));
                target.setSvg(mergedSvg);
            }
            {
                Unit lhsUnit;
                lhsUnit = leftObject.getUnit();
                Unit rhsUnit;
                rhsUnit = rightObject.getUnit();
                Unit mergedUnit = ((Unit) strategy.merge(LocatorUtils.property(leftLocator, "unit", lhsUnit), LocatorUtils.property(rightLocator, "unit", rhsUnit), lhsUnit, rhsUnit));
                target.setUnit(mergedUnit);
            }
            {
                double lhsDpi;
                lhsDpi = (true?leftObject.getDpi(): 0.0D);
                double rhsDpi;
                rhsDpi = (true?rightObject.getDpi(): 0.0D);
                double mergedDpi = ((double) strategy.merge(LocatorUtils.property(leftLocator, "dpi", lhsDpi), LocatorUtils.property(rightLocator, "dpi", rhsDpi), lhsDpi, rhsDpi));
                target.setDpi(mergedDpi);
            }
        }
    }

    public ConfigurationContentDTO withSvg(Svg value) {
        setSvg(value);
        return this;
    }

    public ConfigurationContentDTO withUnit(Unit value) {
        setUnit(value);
        return this;
    }

    public ConfigurationContentDTO withDpi(double value) {
        setDpi(value);
        return this;
    }

}
