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
 * <p>Java class for point complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="point">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *       &lt;attribute name="unit" type="{http://api.company.biz}unit" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "point", propOrder = {
    "x",
    "y"
})
public class Point
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected double x;
    protected double y;
    @XmlAttribute(name = "unit")
    protected Unit unit;

    /**
     * Gets the value of the x property.
     * 
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     */
    public void setX(double value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     */
    public void setY(double value) {
        this.y = value;
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
            double theX;
            theX = (true?this.getX(): 0.0D);
            strategy.appendField(locator, this, "x", buffer, theX);
        }
        {
            double theY;
            theY = (true?this.getY(): 0.0D);
            strategy.appendField(locator, this, "y", buffer, theY);
        }
        {
            Unit theUnit;
            theUnit = this.getUnit();
            strategy.appendField(locator, this, "unit", buffer, theUnit);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Point)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Point that = ((Point) object);
        {
            double lhsX;
            lhsX = (true?this.getX(): 0.0D);
            double rhsX;
            rhsX = (true?that.getX(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "x", lhsX), LocatorUtils.property(thatLocator, "x", rhsX), lhsX, rhsX)) {
                return false;
            }
        }
        {
            double lhsY;
            lhsY = (true?this.getY(): 0.0D);
            double rhsY;
            rhsY = (true?that.getY(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "y", lhsY), LocatorUtils.property(thatLocator, "y", rhsY), lhsY, rhsY)) {
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
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            double theX;
            theX = (true?this.getX(): 0.0D);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "x", theX), currentHashCode, theX);
        }
        {
            double theY;
            theY = (true?this.getY(): 0.0D);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "y", theY), currentHashCode, theY);
        }
        {
            Unit theUnit;
            theUnit = this.getUnit();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unit", theUnit), currentHashCode, theUnit);
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
        if (draftCopy instanceof Point) {
            final Point copy = ((Point) draftCopy);
            double sourceX;
            sourceX = (true?this.getX(): 0.0D);
            double copyX = strategy.copy(LocatorUtils.property(locator, "x", sourceX), sourceX);
            copy.setX(copyX);
            double sourceY;
            sourceY = (true?this.getY(): 0.0D);
            double copyY = strategy.copy(LocatorUtils.property(locator, "y", sourceY), sourceY);
            copy.setY(copyY);
            if (this.unit!= null) {
                Unit sourceUnit;
                sourceUnit = this.getUnit();
                Unit copyUnit = ((Unit) strategy.copy(LocatorUtils.property(locator, "unit", sourceUnit), sourceUnit));
                copy.setUnit(copyUnit);
            } else {
                copy.unit = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new Point();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof Point) {
            final Point target = this;
            final Point leftObject = ((Point) left);
            final Point rightObject = ((Point) right);
            {
                double lhsX;
                lhsX = (true?leftObject.getX(): 0.0D);
                double rhsX;
                rhsX = (true?rightObject.getX(): 0.0D);
                double mergedX = ((double) strategy.merge(LocatorUtils.property(leftLocator, "x", lhsX), LocatorUtils.property(rightLocator, "x", rhsX), lhsX, rhsX));
                target.setX(mergedX);
            }
            {
                double lhsY;
                lhsY = (true?leftObject.getY(): 0.0D);
                double rhsY;
                rhsY = (true?rightObject.getY(): 0.0D);
                double mergedY = ((double) strategy.merge(LocatorUtils.property(leftLocator, "y", lhsY), LocatorUtils.property(rightLocator, "y", rhsY), lhsY, rhsY));
                target.setY(mergedY);
            }
            {
                Unit lhsUnit;
                lhsUnit = leftObject.getUnit();
                Unit rhsUnit;
                rhsUnit = rightObject.getUnit();
                Unit mergedUnit = ((Unit) strategy.merge(LocatorUtils.property(leftLocator, "unit", lhsUnit), LocatorUtils.property(rightLocator, "unit", rhsUnit), lhsUnit, rhsUnit));
                target.setUnit(mergedUnit);
            }
        }
    }

    public Point withX(double value) {
        setX(value);
        return this;
    }

    public Point withY(double value) {
        setY(value);
        return this;
    }

    public Point withUnit(Unit value) {
        setUnit(value);
        return this;
    }

}
