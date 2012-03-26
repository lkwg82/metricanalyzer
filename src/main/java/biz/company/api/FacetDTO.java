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
 * <p>Java class for facetDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="facetDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entryCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="optionPattern" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="entries" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" type="{http://api.company.biz}facetEntryDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "facetDTO", propOrder = {
    "entryCount",
    "optionPattern",
    "entries"
})
public class FacetDTO
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected int entryCount;
    @XmlElement(required = true)
    protected String optionPattern;
    protected FacetDTO.Entries entries;
    @XmlAttribute(name = "type")
    protected String type;

    /**
     * Gets the value of the entryCount property.
     * 
     */
    public int getEntryCount() {
        return entryCount;
    }

    /**
     * Sets the value of the entryCount property.
     * 
     */
    public void setEntryCount(int value) {
        this.entryCount = value;
    }

    /**
     * Gets the value of the optionPattern property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionPattern() {
        return optionPattern;
    }

    /**
     * Sets the value of the optionPattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionPattern(String value) {
        this.optionPattern = value;
    }

    /**
     * Gets the value of the entries property.
     * 
     * @return
     *     possible object is
     *     {@link FacetDTO.Entries }
     *     
     */
    public FacetDTO.Entries getEntries() {
        return entries;
    }

    /**
     * Sets the value of the entries property.
     * 
     * @param value
     *     allowed object is
     *     {@link FacetDTO.Entries }
     *     
     */
    public void setEntries(FacetDTO.Entries value) {
        this.entries = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
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
            int theEntryCount;
            theEntryCount = (true?this.getEntryCount(): 0);
            strategy.appendField(locator, this, "entryCount", buffer, theEntryCount);
        }
        {
            String theOptionPattern;
            theOptionPattern = this.getOptionPattern();
            strategy.appendField(locator, this, "optionPattern", buffer, theOptionPattern);
        }
        {
            FacetDTO.Entries theEntries;
            theEntries = this.getEntries();
            strategy.appendField(locator, this, "entries", buffer, theEntries);
        }
        {
            String theType;
            theType = this.getType();
            strategy.appendField(locator, this, "type", buffer, theType);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FacetDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FacetDTO that = ((FacetDTO) object);
        {
            int lhsEntryCount;
            lhsEntryCount = (true?this.getEntryCount(): 0);
            int rhsEntryCount;
            rhsEntryCount = (true?that.getEntryCount(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "entryCount", lhsEntryCount), LocatorUtils.property(thatLocator, "entryCount", rhsEntryCount), lhsEntryCount, rhsEntryCount)) {
                return false;
            }
        }
        {
            String lhsOptionPattern;
            lhsOptionPattern = this.getOptionPattern();
            String rhsOptionPattern;
            rhsOptionPattern = that.getOptionPattern();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "optionPattern", lhsOptionPattern), LocatorUtils.property(thatLocator, "optionPattern", rhsOptionPattern), lhsOptionPattern, rhsOptionPattern)) {
                return false;
            }
        }
        {
            FacetDTO.Entries lhsEntries;
            lhsEntries = this.getEntries();
            FacetDTO.Entries rhsEntries;
            rhsEntries = that.getEntries();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "entries", lhsEntries), LocatorUtils.property(thatLocator, "entries", rhsEntries), lhsEntries, rhsEntries)) {
                return false;
            }
        }
        {
            String lhsType;
            lhsType = this.getType();
            String rhsType;
            rhsType = that.getType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "type", lhsType), LocatorUtils.property(thatLocator, "type", rhsType), lhsType, rhsType)) {
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
            int theEntryCount;
            theEntryCount = (true?this.getEntryCount(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "entryCount", theEntryCount), currentHashCode, theEntryCount);
        }
        {
            String theOptionPattern;
            theOptionPattern = this.getOptionPattern();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "optionPattern", theOptionPattern), currentHashCode, theOptionPattern);
        }
        {
            FacetDTO.Entries theEntries;
            theEntries = this.getEntries();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "entries", theEntries), currentHashCode, theEntries);
        }
        {
            String theType;
            theType = this.getType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "type", theType), currentHashCode, theType);
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
        if (draftCopy instanceof FacetDTO) {
            final FacetDTO copy = ((FacetDTO) draftCopy);
            int sourceEntryCount;
            sourceEntryCount = (true?this.getEntryCount(): 0);
            int copyEntryCount = strategy.copy(LocatorUtils.property(locator, "entryCount", sourceEntryCount), sourceEntryCount);
            copy.setEntryCount(copyEntryCount);
            if (this.optionPattern!= null) {
                String sourceOptionPattern;
                sourceOptionPattern = this.getOptionPattern();
                String copyOptionPattern = ((String) strategy.copy(LocatorUtils.property(locator, "optionPattern", sourceOptionPattern), sourceOptionPattern));
                copy.setOptionPattern(copyOptionPattern);
            } else {
                copy.optionPattern = null;
            }
            if (this.entries!= null) {
                FacetDTO.Entries sourceEntries;
                sourceEntries = this.getEntries();
                FacetDTO.Entries copyEntries = ((FacetDTO.Entries) strategy.copy(LocatorUtils.property(locator, "entries", sourceEntries), sourceEntries));
                copy.setEntries(copyEntries);
            } else {
                copy.entries = null;
            }
            if (this.type!= null) {
                String sourceType;
                sourceType = this.getType();
                String copyType = ((String) strategy.copy(LocatorUtils.property(locator, "type", sourceType), sourceType));
                copy.setType(copyType);
            } else {
                copy.type = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new FacetDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof FacetDTO) {
            final FacetDTO target = this;
            final FacetDTO leftObject = ((FacetDTO) left);
            final FacetDTO rightObject = ((FacetDTO) right);
            {
                int lhsEntryCount;
                lhsEntryCount = (true?leftObject.getEntryCount(): 0);
                int rhsEntryCount;
                rhsEntryCount = (true?rightObject.getEntryCount(): 0);
                int mergedEntryCount = ((int) strategy.merge(LocatorUtils.property(leftLocator, "entryCount", lhsEntryCount), LocatorUtils.property(rightLocator, "entryCount", rhsEntryCount), lhsEntryCount, rhsEntryCount));
                target.setEntryCount(mergedEntryCount);
            }
            {
                String lhsOptionPattern;
                lhsOptionPattern = leftObject.getOptionPattern();
                String rhsOptionPattern;
                rhsOptionPattern = rightObject.getOptionPattern();
                String mergedOptionPattern = ((String) strategy.merge(LocatorUtils.property(leftLocator, "optionPattern", lhsOptionPattern), LocatorUtils.property(rightLocator, "optionPattern", rhsOptionPattern), lhsOptionPattern, rhsOptionPattern));
                target.setOptionPattern(mergedOptionPattern);
            }
            {
                FacetDTO.Entries lhsEntries;
                lhsEntries = leftObject.getEntries();
                FacetDTO.Entries rhsEntries;
                rhsEntries = rightObject.getEntries();
                FacetDTO.Entries mergedEntries = ((FacetDTO.Entries) strategy.merge(LocatorUtils.property(leftLocator, "entries", lhsEntries), LocatorUtils.property(rightLocator, "entries", rhsEntries), lhsEntries, rhsEntries));
                target.setEntries(mergedEntries);
            }
            {
                String lhsType;
                lhsType = leftObject.getType();
                String rhsType;
                rhsType = rightObject.getType();
                String mergedType = ((String) strategy.merge(LocatorUtils.property(leftLocator, "type", lhsType), LocatorUtils.property(rightLocator, "type", rhsType), lhsType, rhsType));
                target.setType(mergedType);
            }
        }
    }

    public FacetDTO withEntryCount(int value) {
        setEntryCount(value);
        return this;
    }

    public FacetDTO withOptionPattern(String value) {
        setOptionPattern(value);
        return this;
    }

    public FacetDTO withEntries(FacetDTO.Entries value) {
        setEntries(value);
        return this;
    }

    public FacetDTO withType(String value) {
        setType(value);
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
     *         &lt;element name="entry" type="{http://api.company.biz}facetEntryDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "entry"
    })
    public static class Entries
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        protected List<FacetEntryDTO> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FacetEntryDTO }
         * 
         * 
         */
        public List<FacetEntryDTO> getEntry() {
            if (entry == null) {
                entry = new ArrayList<FacetEntryDTO>();
            }
            return this.entry;
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
                List<FacetEntryDTO> theEntry;
                theEntry = (((this.entry!= null)&&(!this.entry.isEmpty()))?this.getEntry():null);
                strategy.appendField(locator, this, "entry", buffer, theEntry);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof FacetDTO.Entries)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final FacetDTO.Entries that = ((FacetDTO.Entries) object);
            {
                List<FacetEntryDTO> lhsEntry;
                lhsEntry = (((this.entry!= null)&&(!this.entry.isEmpty()))?this.getEntry():null);
                List<FacetEntryDTO> rhsEntry;
                rhsEntry = (((that.entry!= null)&&(!that.entry.isEmpty()))?that.getEntry():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "entry", lhsEntry), LocatorUtils.property(thatLocator, "entry", rhsEntry), lhsEntry, rhsEntry)) {
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
                List<FacetEntryDTO> theEntry;
                theEntry = (((this.entry!= null)&&(!this.entry.isEmpty()))?this.getEntry():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "entry", theEntry), currentHashCode, theEntry);
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
            if (draftCopy instanceof FacetDTO.Entries) {
                final FacetDTO.Entries copy = ((FacetDTO.Entries) draftCopy);
                if ((this.entry!= null)&&(!this.entry.isEmpty())) {
                    List<FacetEntryDTO> sourceEntry;
                    sourceEntry = (((this.entry!= null)&&(!this.entry.isEmpty()))?this.getEntry():null);
                    @SuppressWarnings("unchecked")
                    List<FacetEntryDTO> copyEntry = ((List<FacetEntryDTO> ) strategy.copy(LocatorUtils.property(locator, "entry", sourceEntry), sourceEntry));
                    copy.entry = null;
                    if (copyEntry!= null) {
                        List<FacetEntryDTO> uniqueEntryl = copy.getEntry();
                        uniqueEntryl.addAll(copyEntry);
                    }
                } else {
                    copy.entry = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new FacetDTO.Entries();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof FacetDTO.Entries) {
                final FacetDTO.Entries target = this;
                final FacetDTO.Entries leftObject = ((FacetDTO.Entries) left);
                final FacetDTO.Entries rightObject = ((FacetDTO.Entries) right);
                {
                    List<FacetEntryDTO> lhsEntry;
                    lhsEntry = (((leftObject.entry!= null)&&(!leftObject.entry.isEmpty()))?leftObject.getEntry():null);
                    List<FacetEntryDTO> rhsEntry;
                    rhsEntry = (((rightObject.entry!= null)&&(!rightObject.entry.isEmpty()))?rightObject.getEntry():null);
                    List<FacetEntryDTO> mergedEntry = ((List<FacetEntryDTO> ) strategy.merge(LocatorUtils.property(leftLocator, "entry", lhsEntry), LocatorUtils.property(rightLocator, "entry", rhsEntry), lhsEntry, rhsEntry));
                    target.entry = null;
                    if (mergedEntry!= null) {
                        List<FacetEntryDTO> uniqueEntryl = target.getEntry();
                        uniqueEntryl.addAll(mergedEntry);
                    }
                }
            }
        }

        public FacetDTO.Entries withEntry(FacetEntryDTO... values) {
            if (values!= null) {
                for (FacetEntryDTO value: values) {
                    getEntry().add(value);
                }
            }
            return this;
        }

        public FacetDTO.Entries withEntry(Collection<FacetEntryDTO> values) {
            if (values!= null) {
                getEntry().addAll(values);
            }
            return this;
        }

    }

}
