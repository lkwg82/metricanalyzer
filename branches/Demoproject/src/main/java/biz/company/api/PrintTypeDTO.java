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
 * <p>Java class for printTypeDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="printTypeDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.company.biz}dto">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="size" type="{http://api.company.biz}dimension"/>
 *         &lt;element name="dpi" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="colors" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="color" type="{http://api.company.biz}printColorDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="restrictions" type="{http://api.company.biz}printTypeRestrictionsDTO"/>
 *         &lt;element ref="{http://api.company.biz}price" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "printTypeDTO", propOrder = {
    "name",
    "description",
    "size",
    "dpi",
    "colors",
    "restrictions",
    "price"
})
public class PrintTypeDTO
    extends Dto
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected String name;
    protected String description;
    @XmlElement(required = true)
    protected Dimension size;
    protected double dpi;
    protected PrintTypeDTO.Colors colors;
    @XmlElement(required = true)
    protected PrintTypeRestrictionsDTO restrictions;
    protected ElementPrice price;
    @XmlAttribute(name = "weight")
    protected Double weight;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link Dimension }
     *     
     */
    public Dimension getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dimension }
     *     
     */
    public void setSize(Dimension value) {
        this.size = value;
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

    /**
     * Gets the value of the colors property.
     * 
     * @return
     *     possible object is
     *     {@link PrintTypeDTO.Colors }
     *     
     */
    public PrintTypeDTO.Colors getColors() {
        return colors;
    }

    /**
     * Sets the value of the colors property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrintTypeDTO.Colors }
     *     
     */
    public void setColors(PrintTypeDTO.Colors value) {
        this.colors = value;
    }

    /**
     * Gets the value of the restrictions property.
     * 
     * @return
     *     possible object is
     *     {@link PrintTypeRestrictionsDTO }
     *     
     */
    public PrintTypeRestrictionsDTO getRestrictions() {
        return restrictions;
    }

    /**
     * Sets the value of the restrictions property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrintTypeRestrictionsDTO }
     *     
     */
    public void setRestrictions(PrintTypeRestrictionsDTO value) {
        this.restrictions = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link ElementPrice }
     *     
     */
    public ElementPrice getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElementPrice }
     *     
     */
    public void setPrice(ElementPrice value) {
        this.price = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setWeight(Double value) {
        this.weight = value;
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
        super.appendFields(locator, buffer, strategy);
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            Dimension theSize;
            theSize = this.getSize();
            strategy.appendField(locator, this, "size", buffer, theSize);
        }
        {
            double theDpi;
            theDpi = (true?this.getDpi(): 0.0D);
            strategy.appendField(locator, this, "dpi", buffer, theDpi);
        }
        {
            PrintTypeDTO.Colors theColors;
            theColors = this.getColors();
            strategy.appendField(locator, this, "colors", buffer, theColors);
        }
        {
            PrintTypeRestrictionsDTO theRestrictions;
            theRestrictions = this.getRestrictions();
            strategy.appendField(locator, this, "restrictions", buffer, theRestrictions);
        }
        {
            ElementPrice thePrice;
            thePrice = this.getPrice();
            strategy.appendField(locator, this, "price", buffer, thePrice);
        }
        {
            Double theWeight;
            theWeight = this.getWeight();
            strategy.appendField(locator, this, "weight", buffer, theWeight);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PrintTypeDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final PrintTypeDTO that = ((PrintTypeDTO) object);
        {
            String lhsName;
            lhsName = this.getName();
            String rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                return false;
            }
        }
        {
            String lhsDescription;
            lhsDescription = this.getDescription();
            String rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                return false;
            }
        }
        {
            Dimension lhsSize;
            lhsSize = this.getSize();
            Dimension rhsSize;
            rhsSize = that.getSize();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "size", lhsSize), LocatorUtils.property(thatLocator, "size", rhsSize), lhsSize, rhsSize)) {
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
        {
            PrintTypeDTO.Colors lhsColors;
            lhsColors = this.getColors();
            PrintTypeDTO.Colors rhsColors;
            rhsColors = that.getColors();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "colors", lhsColors), LocatorUtils.property(thatLocator, "colors", rhsColors), lhsColors, rhsColors)) {
                return false;
            }
        }
        {
            PrintTypeRestrictionsDTO lhsRestrictions;
            lhsRestrictions = this.getRestrictions();
            PrintTypeRestrictionsDTO rhsRestrictions;
            rhsRestrictions = that.getRestrictions();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "restrictions", lhsRestrictions), LocatorUtils.property(thatLocator, "restrictions", rhsRestrictions), lhsRestrictions, rhsRestrictions)) {
                return false;
            }
        }
        {
            ElementPrice lhsPrice;
            lhsPrice = this.getPrice();
            ElementPrice rhsPrice;
            rhsPrice = that.getPrice();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "price", lhsPrice), LocatorUtils.property(thatLocator, "price", rhsPrice), lhsPrice, rhsPrice)) {
                return false;
            }
        }
        {
            Double lhsWeight;
            lhsWeight = this.getWeight();
            Double rhsWeight;
            rhsWeight = that.getWeight();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "weight", lhsWeight), LocatorUtils.property(thatLocator, "weight", rhsWeight), lhsWeight, rhsWeight)) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            String theName;
            theName = this.getName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
        }
        {
            Dimension theSize;
            theSize = this.getSize();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "size", theSize), currentHashCode, theSize);
        }
        {
            double theDpi;
            theDpi = (true?this.getDpi(): 0.0D);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dpi", theDpi), currentHashCode, theDpi);
        }
        {
            PrintTypeDTO.Colors theColors;
            theColors = this.getColors();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "colors", theColors), currentHashCode, theColors);
        }
        {
            PrintTypeRestrictionsDTO theRestrictions;
            theRestrictions = this.getRestrictions();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "restrictions", theRestrictions), currentHashCode, theRestrictions);
        }
        {
            ElementPrice thePrice;
            thePrice = this.getPrice();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "price", thePrice), currentHashCode, thePrice);
        }
        {
            Double theWeight;
            theWeight = this.getWeight();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "weight", theWeight), currentHashCode, theWeight);
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof PrintTypeDTO) {
            final PrintTypeDTO copy = ((PrintTypeDTO) draftCopy);
            if (this.name!= null) {
                String sourceName;
                sourceName = this.getName();
                String copyName = ((String) strategy.copy(LocatorUtils.property(locator, "name", sourceName), sourceName));
                copy.setName(copyName);
            } else {
                copy.name = null;
            }
            if (this.description!= null) {
                String sourceDescription;
                sourceDescription = this.getDescription();
                String copyDescription = ((String) strategy.copy(LocatorUtils.property(locator, "description", sourceDescription), sourceDescription));
                copy.setDescription(copyDescription);
            } else {
                copy.description = null;
            }
            if (this.size!= null) {
                Dimension sourceSize;
                sourceSize = this.getSize();
                Dimension copySize = ((Dimension) strategy.copy(LocatorUtils.property(locator, "size", sourceSize), sourceSize));
                copy.setSize(copySize);
            } else {
                copy.size = null;
            }
            double sourceDpi;
            sourceDpi = (true?this.getDpi(): 0.0D);
            double copyDpi = strategy.copy(LocatorUtils.property(locator, "dpi", sourceDpi), sourceDpi);
            copy.setDpi(copyDpi);
            if (this.colors!= null) {
                PrintTypeDTO.Colors sourceColors;
                sourceColors = this.getColors();
                PrintTypeDTO.Colors copyColors = ((PrintTypeDTO.Colors) strategy.copy(LocatorUtils.property(locator, "colors", sourceColors), sourceColors));
                copy.setColors(copyColors);
            } else {
                copy.colors = null;
            }
            if (this.restrictions!= null) {
                PrintTypeRestrictionsDTO sourceRestrictions;
                sourceRestrictions = this.getRestrictions();
                PrintTypeRestrictionsDTO copyRestrictions = ((PrintTypeRestrictionsDTO) strategy.copy(LocatorUtils.property(locator, "restrictions", sourceRestrictions), sourceRestrictions));
                copy.setRestrictions(copyRestrictions);
            } else {
                copy.restrictions = null;
            }
            if (this.price!= null) {
                ElementPrice sourcePrice;
                sourcePrice = this.getPrice();
                ElementPrice copyPrice = ((ElementPrice) strategy.copy(LocatorUtils.property(locator, "price", sourcePrice), sourcePrice));
                copy.setPrice(copyPrice);
            } else {
                copy.price = null;
            }
            if (this.weight!= null) {
                Double sourceWeight;
                sourceWeight = this.getWeight();
                Double copyWeight = ((Double) strategy.copy(LocatorUtils.property(locator, "weight", sourceWeight), sourceWeight));
                copy.setWeight(copyWeight);
            } else {
                copy.weight = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new PrintTypeDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof PrintTypeDTO) {
            final PrintTypeDTO target = this;
            final PrintTypeDTO leftObject = ((PrintTypeDTO) left);
            final PrintTypeDTO rightObject = ((PrintTypeDTO) right);
            {
                String lhsName;
                lhsName = leftObject.getName();
                String rhsName;
                rhsName = rightObject.getName();
                String mergedName = ((String) strategy.merge(LocatorUtils.property(leftLocator, "name", lhsName), LocatorUtils.property(rightLocator, "name", rhsName), lhsName, rhsName));
                target.setName(mergedName);
            }
            {
                String lhsDescription;
                lhsDescription = leftObject.getDescription();
                String rhsDescription;
                rhsDescription = rightObject.getDescription();
                String mergedDescription = ((String) strategy.merge(LocatorUtils.property(leftLocator, "description", lhsDescription), LocatorUtils.property(rightLocator, "description", rhsDescription), lhsDescription, rhsDescription));
                target.setDescription(mergedDescription);
            }
            {
                Dimension lhsSize;
                lhsSize = leftObject.getSize();
                Dimension rhsSize;
                rhsSize = rightObject.getSize();
                Dimension mergedSize = ((Dimension) strategy.merge(LocatorUtils.property(leftLocator, "size", lhsSize), LocatorUtils.property(rightLocator, "size", rhsSize), lhsSize, rhsSize));
                target.setSize(mergedSize);
            }
            {
                double lhsDpi;
                lhsDpi = (true?leftObject.getDpi(): 0.0D);
                double rhsDpi;
                rhsDpi = (true?rightObject.getDpi(): 0.0D);
                double mergedDpi = ((double) strategy.merge(LocatorUtils.property(leftLocator, "dpi", lhsDpi), LocatorUtils.property(rightLocator, "dpi", rhsDpi), lhsDpi, rhsDpi));
                target.setDpi(mergedDpi);
            }
            {
                PrintTypeDTO.Colors lhsColors;
                lhsColors = leftObject.getColors();
                PrintTypeDTO.Colors rhsColors;
                rhsColors = rightObject.getColors();
                PrintTypeDTO.Colors mergedColors = ((PrintTypeDTO.Colors) strategy.merge(LocatorUtils.property(leftLocator, "colors", lhsColors), LocatorUtils.property(rightLocator, "colors", rhsColors), lhsColors, rhsColors));
                target.setColors(mergedColors);
            }
            {
                PrintTypeRestrictionsDTO lhsRestrictions;
                lhsRestrictions = leftObject.getRestrictions();
                PrintTypeRestrictionsDTO rhsRestrictions;
                rhsRestrictions = rightObject.getRestrictions();
                PrintTypeRestrictionsDTO mergedRestrictions = ((PrintTypeRestrictionsDTO) strategy.merge(LocatorUtils.property(leftLocator, "restrictions", lhsRestrictions), LocatorUtils.property(rightLocator, "restrictions", rhsRestrictions), lhsRestrictions, rhsRestrictions));
                target.setRestrictions(mergedRestrictions);
            }
            {
                ElementPrice lhsPrice;
                lhsPrice = leftObject.getPrice();
                ElementPrice rhsPrice;
                rhsPrice = rightObject.getPrice();
                ElementPrice mergedPrice = ((ElementPrice) strategy.merge(LocatorUtils.property(leftLocator, "price", lhsPrice), LocatorUtils.property(rightLocator, "price", rhsPrice), lhsPrice, rhsPrice));
                target.setPrice(mergedPrice);
            }
            {
                Double lhsWeight;
                lhsWeight = leftObject.getWeight();
                Double rhsWeight;
                rhsWeight = rightObject.getWeight();
                Double mergedWeight = ((Double) strategy.merge(LocatorUtils.property(leftLocator, "weight", lhsWeight), LocatorUtils.property(rightLocator, "weight", rhsWeight), lhsWeight, rhsWeight));
                target.setWeight(mergedWeight);
            }
        }
    }

    public PrintTypeDTO withName(String value) {
        setName(value);
        return this;
    }

    public PrintTypeDTO withDescription(String value) {
        setDescription(value);
        return this;
    }

    public PrintTypeDTO withSize(Dimension value) {
        setSize(value);
        return this;
    }

    public PrintTypeDTO withDpi(double value) {
        setDpi(value);
        return this;
    }

    public PrintTypeDTO withColors(PrintTypeDTO.Colors value) {
        setColors(value);
        return this;
    }

    public PrintTypeDTO withRestrictions(PrintTypeRestrictionsDTO value) {
        setRestrictions(value);
        return this;
    }

    public PrintTypeDTO withPrice(ElementPrice value) {
        setPrice(value);
        return this;
    }

    public PrintTypeDTO withWeight(Double value) {
        setWeight(value);
        return this;
    }

    @Override
    public PrintTypeDTO withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public PrintTypeDTO withHref(String value) {
        setHref(value);
        return this;
    }

    @Override
    public PrintTypeDTO withLifeCycleState(String value) {
        setLifeCycleState(value);
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
     *         &lt;element name="color" type="{http://api.company.biz}printColorDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "color"
    })
    public static class Colors
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        protected List<PrintColorDTO> color;

        /**
         * Gets the value of the color property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the color property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getColor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PrintColorDTO }
         * 
         * 
         */
        public List<PrintColorDTO> getColor() {
            if (color == null) {
                color = new ArrayList<PrintColorDTO>();
            }
            return this.color;
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
                List<PrintColorDTO> theColor;
                theColor = (((this.color!= null)&&(!this.color.isEmpty()))?this.getColor():null);
                strategy.appendField(locator, this, "color", buffer, theColor);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof PrintTypeDTO.Colors)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final PrintTypeDTO.Colors that = ((PrintTypeDTO.Colors) object);
            {
                List<PrintColorDTO> lhsColor;
                lhsColor = (((this.color!= null)&&(!this.color.isEmpty()))?this.getColor():null);
                List<PrintColorDTO> rhsColor;
                rhsColor = (((that.color!= null)&&(!that.color.isEmpty()))?that.getColor():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "color", lhsColor), LocatorUtils.property(thatLocator, "color", rhsColor), lhsColor, rhsColor)) {
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
                List<PrintColorDTO> theColor;
                theColor = (((this.color!= null)&&(!this.color.isEmpty()))?this.getColor():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "color", theColor), currentHashCode, theColor);
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
            if (draftCopy instanceof PrintTypeDTO.Colors) {
                final PrintTypeDTO.Colors copy = ((PrintTypeDTO.Colors) draftCopy);
                if ((this.color!= null)&&(!this.color.isEmpty())) {
                    List<PrintColorDTO> sourceColor;
                    sourceColor = (((this.color!= null)&&(!this.color.isEmpty()))?this.getColor():null);
                    @SuppressWarnings("unchecked")
                    List<PrintColorDTO> copyColor = ((List<PrintColorDTO> ) strategy.copy(LocatorUtils.property(locator, "color", sourceColor), sourceColor));
                    copy.color = null;
                    if (copyColor!= null) {
                        List<PrintColorDTO> uniqueColorl = copy.getColor();
                        uniqueColorl.addAll(copyColor);
                    }
                } else {
                    copy.color = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new PrintTypeDTO.Colors();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof PrintTypeDTO.Colors) {
                final PrintTypeDTO.Colors target = this;
                final PrintTypeDTO.Colors leftObject = ((PrintTypeDTO.Colors) left);
                final PrintTypeDTO.Colors rightObject = ((PrintTypeDTO.Colors) right);
                {
                    List<PrintColorDTO> lhsColor;
                    lhsColor = (((leftObject.color!= null)&&(!leftObject.color.isEmpty()))?leftObject.getColor():null);
                    List<PrintColorDTO> rhsColor;
                    rhsColor = (((rightObject.color!= null)&&(!rightObject.color.isEmpty()))?rightObject.getColor():null);
                    List<PrintColorDTO> mergedColor = ((List<PrintColorDTO> ) strategy.merge(LocatorUtils.property(leftLocator, "color", lhsColor), LocatorUtils.property(rightLocator, "color", rhsColor), lhsColor, rhsColor));
                    target.color = null;
                    if (mergedColor!= null) {
                        List<PrintColorDTO> uniqueColorl = target.getColor();
                        uniqueColorl.addAll(mergedColor);
                    }
                }
            }
        }

        public PrintTypeDTO.Colors withColor(PrintColorDTO... values) {
            if (values!= null) {
                for (PrintColorDTO value: values) {
                    getColor().add(value);
                }
            }
            return this;
        }

        public PrintTypeDTO.Colors withColor(Collection<PrintColorDTO> values) {
            if (values!= null) {
                getColor().addAll(values);
            }
            return this;
        }

    }

}