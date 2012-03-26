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
 * <p>Java class for fontFamilyDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fontFamilyDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.company.biz}dto">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="printTypes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="printType" type="{http://api.company.biz}reference" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="fonts" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://api.company.biz}font" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="deprecated" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "fontFamilyDTO", propOrder = {
    "name",
    "printTypes",
    "fonts",
    "deprecated"
})
public class FontFamilyDTO
    extends Dto
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected String name;
    protected FontFamilyDTO.PrintTypes printTypes;
    protected FontFamilyDTO.Fonts fonts;
    protected Boolean deprecated;
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
     * Gets the value of the printTypes property.
     * 
     * @return
     *     possible object is
     *     {@link FontFamilyDTO.PrintTypes }
     *     
     */
    public FontFamilyDTO.PrintTypes getPrintTypes() {
        return printTypes;
    }

    /**
     * Sets the value of the printTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link FontFamilyDTO.PrintTypes }
     *     
     */
    public void setPrintTypes(FontFamilyDTO.PrintTypes value) {
        this.printTypes = value;
    }

    /**
     * Gets the value of the fonts property.
     * 
     * @return
     *     possible object is
     *     {@link FontFamilyDTO.Fonts }
     *     
     */
    public FontFamilyDTO.Fonts getFonts() {
        return fonts;
    }

    /**
     * Sets the value of the fonts property.
     * 
     * @param value
     *     allowed object is
     *     {@link FontFamilyDTO.Fonts }
     *     
     */
    public void setFonts(FontFamilyDTO.Fonts value) {
        this.fonts = value;
    }

    /**
     * Gets the value of the deprecated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDeprecated() {
        return deprecated;
    }

    /**
     * Sets the value of the deprecated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDeprecated(Boolean value) {
        this.deprecated = value;
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
            FontFamilyDTO.PrintTypes thePrintTypes;
            thePrintTypes = this.getPrintTypes();
            strategy.appendField(locator, this, "printTypes", buffer, thePrintTypes);
        }
        {
            FontFamilyDTO.Fonts theFonts;
            theFonts = this.getFonts();
            strategy.appendField(locator, this, "fonts", buffer, theFonts);
        }
        {
            Boolean theDeprecated;
            theDeprecated = this.isDeprecated();
            strategy.appendField(locator, this, "deprecated", buffer, theDeprecated);
        }
        {
            Double theWeight;
            theWeight = this.getWeight();
            strategy.appendField(locator, this, "weight", buffer, theWeight);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FontFamilyDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final FontFamilyDTO that = ((FontFamilyDTO) object);
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
            FontFamilyDTO.PrintTypes lhsPrintTypes;
            lhsPrintTypes = this.getPrintTypes();
            FontFamilyDTO.PrintTypes rhsPrintTypes;
            rhsPrintTypes = that.getPrintTypes();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "printTypes", lhsPrintTypes), LocatorUtils.property(thatLocator, "printTypes", rhsPrintTypes), lhsPrintTypes, rhsPrintTypes)) {
                return false;
            }
        }
        {
            FontFamilyDTO.Fonts lhsFonts;
            lhsFonts = this.getFonts();
            FontFamilyDTO.Fonts rhsFonts;
            rhsFonts = that.getFonts();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fonts", lhsFonts), LocatorUtils.property(thatLocator, "fonts", rhsFonts), lhsFonts, rhsFonts)) {
                return false;
            }
        }
        {
            Boolean lhsDeprecated;
            lhsDeprecated = this.isDeprecated();
            Boolean rhsDeprecated;
            rhsDeprecated = that.isDeprecated();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "deprecated", lhsDeprecated), LocatorUtils.property(thatLocator, "deprecated", rhsDeprecated), lhsDeprecated, rhsDeprecated)) {
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
            FontFamilyDTO.PrintTypes thePrintTypes;
            thePrintTypes = this.getPrintTypes();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "printTypes", thePrintTypes), currentHashCode, thePrintTypes);
        }
        {
            FontFamilyDTO.Fonts theFonts;
            theFonts = this.getFonts();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fonts", theFonts), currentHashCode, theFonts);
        }
        {
            Boolean theDeprecated;
            theDeprecated = this.isDeprecated();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "deprecated", theDeprecated), currentHashCode, theDeprecated);
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
        if (draftCopy instanceof FontFamilyDTO) {
            final FontFamilyDTO copy = ((FontFamilyDTO) draftCopy);
            if (this.name!= null) {
                String sourceName;
                sourceName = this.getName();
                String copyName = ((String) strategy.copy(LocatorUtils.property(locator, "name", sourceName), sourceName));
                copy.setName(copyName);
            } else {
                copy.name = null;
            }
            if (this.printTypes!= null) {
                FontFamilyDTO.PrintTypes sourcePrintTypes;
                sourcePrintTypes = this.getPrintTypes();
                FontFamilyDTO.PrintTypes copyPrintTypes = ((FontFamilyDTO.PrintTypes) strategy.copy(LocatorUtils.property(locator, "printTypes", sourcePrintTypes), sourcePrintTypes));
                copy.setPrintTypes(copyPrintTypes);
            } else {
                copy.printTypes = null;
            }
            if (this.fonts!= null) {
                FontFamilyDTO.Fonts sourceFonts;
                sourceFonts = this.getFonts();
                FontFamilyDTO.Fonts copyFonts = ((FontFamilyDTO.Fonts) strategy.copy(LocatorUtils.property(locator, "fonts", sourceFonts), sourceFonts));
                copy.setFonts(copyFonts);
            } else {
                copy.fonts = null;
            }
            if (this.deprecated!= null) {
                Boolean sourceDeprecated;
                sourceDeprecated = this.isDeprecated();
                Boolean copyDeprecated = ((Boolean) strategy.copy(LocatorUtils.property(locator, "deprecated", sourceDeprecated), sourceDeprecated));
                copy.setDeprecated(copyDeprecated);
            } else {
                copy.deprecated = null;
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
        return new FontFamilyDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof FontFamilyDTO) {
            final FontFamilyDTO target = this;
            final FontFamilyDTO leftObject = ((FontFamilyDTO) left);
            final FontFamilyDTO rightObject = ((FontFamilyDTO) right);
            {
                String lhsName;
                lhsName = leftObject.getName();
                String rhsName;
                rhsName = rightObject.getName();
                String mergedName = ((String) strategy.merge(LocatorUtils.property(leftLocator, "name", lhsName), LocatorUtils.property(rightLocator, "name", rhsName), lhsName, rhsName));
                target.setName(mergedName);
            }
            {
                FontFamilyDTO.PrintTypes lhsPrintTypes;
                lhsPrintTypes = leftObject.getPrintTypes();
                FontFamilyDTO.PrintTypes rhsPrintTypes;
                rhsPrintTypes = rightObject.getPrintTypes();
                FontFamilyDTO.PrintTypes mergedPrintTypes = ((FontFamilyDTO.PrintTypes) strategy.merge(LocatorUtils.property(leftLocator, "printTypes", lhsPrintTypes), LocatorUtils.property(rightLocator, "printTypes", rhsPrintTypes), lhsPrintTypes, rhsPrintTypes));
                target.setPrintTypes(mergedPrintTypes);
            }
            {
                FontFamilyDTO.Fonts lhsFonts;
                lhsFonts = leftObject.getFonts();
                FontFamilyDTO.Fonts rhsFonts;
                rhsFonts = rightObject.getFonts();
                FontFamilyDTO.Fonts mergedFonts = ((FontFamilyDTO.Fonts) strategy.merge(LocatorUtils.property(leftLocator, "fonts", lhsFonts), LocatorUtils.property(rightLocator, "fonts", rhsFonts), lhsFonts, rhsFonts));
                target.setFonts(mergedFonts);
            }
            {
                Boolean lhsDeprecated;
                lhsDeprecated = leftObject.isDeprecated();
                Boolean rhsDeprecated;
                rhsDeprecated = rightObject.isDeprecated();
                Boolean mergedDeprecated = ((Boolean) strategy.merge(LocatorUtils.property(leftLocator, "deprecated", lhsDeprecated), LocatorUtils.property(rightLocator, "deprecated", rhsDeprecated), lhsDeprecated, rhsDeprecated));
                target.setDeprecated(mergedDeprecated);
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

    public FontFamilyDTO withName(String value) {
        setName(value);
        return this;
    }

    public FontFamilyDTO withPrintTypes(FontFamilyDTO.PrintTypes value) {
        setPrintTypes(value);
        return this;
    }

    public FontFamilyDTO withFonts(FontFamilyDTO.Fonts value) {
        setFonts(value);
        return this;
    }

    public FontFamilyDTO withDeprecated(Boolean value) {
        setDeprecated(value);
        return this;
    }

    public FontFamilyDTO withWeight(Double value) {
        setWeight(value);
        return this;
    }

    @Override
    public FontFamilyDTO withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public FontFamilyDTO withHref(String value) {
        setHref(value);
        return this;
    }

    @Override
    public FontFamilyDTO withLifeCycleState(String value) {
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
     *         &lt;element ref="{http://api.company.biz}font" maxOccurs="unbounded"/>
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
        "font"
    })
    public static class Fonts
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        @XmlElement(required = true)
        protected List<FontDTO> font;

        /**
         * Gets the value of the font property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the font property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFont().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FontDTO }
         * 
         * 
         */
        public List<FontDTO> getFont() {
            if (font == null) {
                font = new ArrayList<FontDTO>();
            }
            return this.font;
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
                List<FontDTO> theFont;
                theFont = (((this.font!= null)&&(!this.font.isEmpty()))?this.getFont():null);
                strategy.appendField(locator, this, "font", buffer, theFont);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof FontFamilyDTO.Fonts)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final FontFamilyDTO.Fonts that = ((FontFamilyDTO.Fonts) object);
            {
                List<FontDTO> lhsFont;
                lhsFont = (((this.font!= null)&&(!this.font.isEmpty()))?this.getFont():null);
                List<FontDTO> rhsFont;
                rhsFont = (((that.font!= null)&&(!that.font.isEmpty()))?that.getFont():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "font", lhsFont), LocatorUtils.property(thatLocator, "font", rhsFont), lhsFont, rhsFont)) {
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
                List<FontDTO> theFont;
                theFont = (((this.font!= null)&&(!this.font.isEmpty()))?this.getFont():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "font", theFont), currentHashCode, theFont);
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
            if (draftCopy instanceof FontFamilyDTO.Fonts) {
                final FontFamilyDTO.Fonts copy = ((FontFamilyDTO.Fonts) draftCopy);
                if ((this.font!= null)&&(!this.font.isEmpty())) {
                    List<FontDTO> sourceFont;
                    sourceFont = (((this.font!= null)&&(!this.font.isEmpty()))?this.getFont():null);
                    @SuppressWarnings("unchecked")
                    List<FontDTO> copyFont = ((List<FontDTO> ) strategy.copy(LocatorUtils.property(locator, "font", sourceFont), sourceFont));
                    copy.font = null;
                    if (copyFont!= null) {
                        List<FontDTO> uniqueFontl = copy.getFont();
                        uniqueFontl.addAll(copyFont);
                    }
                } else {
                    copy.font = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new FontFamilyDTO.Fonts();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof FontFamilyDTO.Fonts) {
                final FontFamilyDTO.Fonts target = this;
                final FontFamilyDTO.Fonts leftObject = ((FontFamilyDTO.Fonts) left);
                final FontFamilyDTO.Fonts rightObject = ((FontFamilyDTO.Fonts) right);
                {
                    List<FontDTO> lhsFont;
                    lhsFont = (((leftObject.font!= null)&&(!leftObject.font.isEmpty()))?leftObject.getFont():null);
                    List<FontDTO> rhsFont;
                    rhsFont = (((rightObject.font!= null)&&(!rightObject.font.isEmpty()))?rightObject.getFont():null);
                    List<FontDTO> mergedFont = ((List<FontDTO> ) strategy.merge(LocatorUtils.property(leftLocator, "font", lhsFont), LocatorUtils.property(rightLocator, "font", rhsFont), lhsFont, rhsFont));
                    target.font = null;
                    if (mergedFont!= null) {
                        List<FontDTO> uniqueFontl = target.getFont();
                        uniqueFontl.addAll(mergedFont);
                    }
                }
            }
        }

        public FontFamilyDTO.Fonts withFont(FontDTO... values) {
            if (values!= null) {
                for (FontDTO value: values) {
                    getFont().add(value);
                }
            }
            return this;
        }

        public FontFamilyDTO.Fonts withFont(Collection<FontDTO> values) {
            if (values!= null) {
                getFont().addAll(values);
            }
            return this;
        }

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
     *         &lt;element name="printType" type="{http://api.company.biz}reference" maxOccurs="unbounded" minOccurs="0"/>
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
        "printType"
    })
    public static class PrintTypes
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        protected List<Reference> printType;

        /**
         * Gets the value of the printType property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the printType property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPrintType().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Reference }
         * 
         * 
         */
        public List<Reference> getPrintType() {
            if (printType == null) {
                printType = new ArrayList<Reference>();
            }
            return this.printType;
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
                List<Reference> thePrintType;
                thePrintType = (((this.printType!= null)&&(!this.printType.isEmpty()))?this.getPrintType():null);
                strategy.appendField(locator, this, "printType", buffer, thePrintType);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof FontFamilyDTO.PrintTypes)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final FontFamilyDTO.PrintTypes that = ((FontFamilyDTO.PrintTypes) object);
            {
                List<Reference> lhsPrintType;
                lhsPrintType = (((this.printType!= null)&&(!this.printType.isEmpty()))?this.getPrintType():null);
                List<Reference> rhsPrintType;
                rhsPrintType = (((that.printType!= null)&&(!that.printType.isEmpty()))?that.getPrintType():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "printType", lhsPrintType), LocatorUtils.property(thatLocator, "printType", rhsPrintType), lhsPrintType, rhsPrintType)) {
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
                List<Reference> thePrintType;
                thePrintType = (((this.printType!= null)&&(!this.printType.isEmpty()))?this.getPrintType():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "printType", thePrintType), currentHashCode, thePrintType);
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
            if (draftCopy instanceof FontFamilyDTO.PrintTypes) {
                final FontFamilyDTO.PrintTypes copy = ((FontFamilyDTO.PrintTypes) draftCopy);
                if ((this.printType!= null)&&(!this.printType.isEmpty())) {
                    List<Reference> sourcePrintType;
                    sourcePrintType = (((this.printType!= null)&&(!this.printType.isEmpty()))?this.getPrintType():null);
                    @SuppressWarnings("unchecked")
                    List<Reference> copyPrintType = ((List<Reference> ) strategy.copy(LocatorUtils.property(locator, "printType", sourcePrintType), sourcePrintType));
                    copy.printType = null;
                    if (copyPrintType!= null) {
                        List<Reference> uniquePrintTypel = copy.getPrintType();
                        uniquePrintTypel.addAll(copyPrintType);
                    }
                } else {
                    copy.printType = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new FontFamilyDTO.PrintTypes();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof FontFamilyDTO.PrintTypes) {
                final FontFamilyDTO.PrintTypes target = this;
                final FontFamilyDTO.PrintTypes leftObject = ((FontFamilyDTO.PrintTypes) left);
                final FontFamilyDTO.PrintTypes rightObject = ((FontFamilyDTO.PrintTypes) right);
                {
                    List<Reference> lhsPrintType;
                    lhsPrintType = (((leftObject.printType!= null)&&(!leftObject.printType.isEmpty()))?leftObject.getPrintType():null);
                    List<Reference> rhsPrintType;
                    rhsPrintType = (((rightObject.printType!= null)&&(!rightObject.printType.isEmpty()))?rightObject.getPrintType():null);
                    List<Reference> mergedPrintType = ((List<Reference> ) strategy.merge(LocatorUtils.property(leftLocator, "printType", lhsPrintType), LocatorUtils.property(rightLocator, "printType", rhsPrintType), lhsPrintType, rhsPrintType));
                    target.printType = null;
                    if (mergedPrintType!= null) {
                        List<Reference> uniquePrintTypel = target.getPrintType();
                        uniquePrintTypel.addAll(mergedPrintType);
                    }
                }
            }
        }

        public FontFamilyDTO.PrintTypes withPrintType(Reference... values) {
            if (values!= null) {
                for (Reference value: values) {
                    getPrintType().add(value);
                }
            }
            return this;
        }

        public FontFamilyDTO.PrintTypes withPrintType(Collection<Reference> values) {
            if (values!= null) {
                getPrintType().addAll(values);
            }
            return this;
        }

    }

}
