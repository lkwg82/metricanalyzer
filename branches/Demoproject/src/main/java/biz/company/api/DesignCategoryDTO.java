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
 * <p>Java class for designCategoryDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="designCategoryDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.company.biz}dto">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="entryCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="designs" type="{http://api.company.biz}reference"/>
 *         &lt;element name="designCategories" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://api.company.biz}designCategory" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="weight" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="type" use="required" type="{http://api.company.biz}type" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "designCategoryDTO", propOrder = {
    "name",
    "entryCount",
    "designs",
    "designCategories"
})
public class DesignCategoryDTO
    extends Dto
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected String name;
    protected Integer entryCount;
    @XmlElement(required = true)
    protected Reference designs;
    protected DesignCategoryDTO.DesignCategories designCategories;
    @XmlAttribute(name = "weight", required = true)
    protected double weight;
    @XmlAttribute(name = "type", required = true)
    protected Type type;

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
     * Gets the value of the entryCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEntryCount() {
        return entryCount;
    }

    /**
     * Sets the value of the entryCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEntryCount(Integer value) {
        this.entryCount = value;
    }

    /**
     * Gets the value of the designs property.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getDesigns() {
        return designs;
    }

    /**
     * Sets the value of the designs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setDesigns(Reference value) {
        this.designs = value;
    }

    /**
     * Gets the value of the designCategories property.
     * 
     * @return
     *     possible object is
     *     {@link DesignCategoryDTO.DesignCategories }
     *     
     */
    public DesignCategoryDTO.DesignCategories getDesignCategories() {
        return designCategories;
    }

    /**
     * Sets the value of the designCategories property.
     * 
     * @param value
     *     allowed object is
     *     {@link DesignCategoryDTO.DesignCategories }
     *     
     */
    public void setDesignCategories(DesignCategoryDTO.DesignCategories value) {
        this.designCategories = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     */
    public void setWeight(double value) {
        this.weight = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setType(Type value) {
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
        super.appendFields(locator, buffer, strategy);
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            Integer theEntryCount;
            theEntryCount = this.getEntryCount();
            strategy.appendField(locator, this, "entryCount", buffer, theEntryCount);
        }
        {
            Reference theDesigns;
            theDesigns = this.getDesigns();
            strategy.appendField(locator, this, "designs", buffer, theDesigns);
        }
        {
            DesignCategoryDTO.DesignCategories theDesignCategories;
            theDesignCategories = this.getDesignCategories();
            strategy.appendField(locator, this, "designCategories", buffer, theDesignCategories);
        }
        {
            double theWeight;
            theWeight = (true?this.getWeight(): 0.0D);
            strategy.appendField(locator, this, "weight", buffer, theWeight);
        }
        {
            Type theType;
            theType = this.getType();
            strategy.appendField(locator, this, "type", buffer, theType);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DesignCategoryDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final DesignCategoryDTO that = ((DesignCategoryDTO) object);
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
            Integer lhsEntryCount;
            lhsEntryCount = this.getEntryCount();
            Integer rhsEntryCount;
            rhsEntryCount = that.getEntryCount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "entryCount", lhsEntryCount), LocatorUtils.property(thatLocator, "entryCount", rhsEntryCount), lhsEntryCount, rhsEntryCount)) {
                return false;
            }
        }
        {
            Reference lhsDesigns;
            lhsDesigns = this.getDesigns();
            Reference rhsDesigns;
            rhsDesigns = that.getDesigns();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "designs", lhsDesigns), LocatorUtils.property(thatLocator, "designs", rhsDesigns), lhsDesigns, rhsDesigns)) {
                return false;
            }
        }
        {
            DesignCategoryDTO.DesignCategories lhsDesignCategories;
            lhsDesignCategories = this.getDesignCategories();
            DesignCategoryDTO.DesignCategories rhsDesignCategories;
            rhsDesignCategories = that.getDesignCategories();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "designCategories", lhsDesignCategories), LocatorUtils.property(thatLocator, "designCategories", rhsDesignCategories), lhsDesignCategories, rhsDesignCategories)) {
                return false;
            }
        }
        {
            double lhsWeight;
            lhsWeight = (true?this.getWeight(): 0.0D);
            double rhsWeight;
            rhsWeight = (true?that.getWeight(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "weight", lhsWeight), LocatorUtils.property(thatLocator, "weight", rhsWeight), lhsWeight, rhsWeight)) {
                return false;
            }
        }
        {
            Type lhsType;
            lhsType = this.getType();
            Type rhsType;
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            String theName;
            theName = this.getName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);
        }
        {
            Integer theEntryCount;
            theEntryCount = this.getEntryCount();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "entryCount", theEntryCount), currentHashCode, theEntryCount);
        }
        {
            Reference theDesigns;
            theDesigns = this.getDesigns();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "designs", theDesigns), currentHashCode, theDesigns);
        }
        {
            DesignCategoryDTO.DesignCategories theDesignCategories;
            theDesignCategories = this.getDesignCategories();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "designCategories", theDesignCategories), currentHashCode, theDesignCategories);
        }
        {
            double theWeight;
            theWeight = (true?this.getWeight(): 0.0D);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "weight", theWeight), currentHashCode, theWeight);
        }
        {
            Type theType;
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof DesignCategoryDTO) {
            final DesignCategoryDTO copy = ((DesignCategoryDTO) draftCopy);
            if (this.name!= null) {
                String sourceName;
                sourceName = this.getName();
                String copyName = ((String) strategy.copy(LocatorUtils.property(locator, "name", sourceName), sourceName));
                copy.setName(copyName);
            } else {
                copy.name = null;
            }
            if (this.entryCount!= null) {
                Integer sourceEntryCount;
                sourceEntryCount = this.getEntryCount();
                Integer copyEntryCount = ((Integer) strategy.copy(LocatorUtils.property(locator, "entryCount", sourceEntryCount), sourceEntryCount));
                copy.setEntryCount(copyEntryCount);
            } else {
                copy.entryCount = null;
            }
            if (this.designs!= null) {
                Reference sourceDesigns;
                sourceDesigns = this.getDesigns();
                Reference copyDesigns = ((Reference) strategy.copy(LocatorUtils.property(locator, "designs", sourceDesigns), sourceDesigns));
                copy.setDesigns(copyDesigns);
            } else {
                copy.designs = null;
            }
            if (this.designCategories!= null) {
                DesignCategoryDTO.DesignCategories sourceDesignCategories;
                sourceDesignCategories = this.getDesignCategories();
                DesignCategoryDTO.DesignCategories copyDesignCategories = ((DesignCategoryDTO.DesignCategories) strategy.copy(LocatorUtils.property(locator, "designCategories", sourceDesignCategories), sourceDesignCategories));
                copy.setDesignCategories(copyDesignCategories);
            } else {
                copy.designCategories = null;
            }
            double sourceWeight;
            sourceWeight = (true?this.getWeight(): 0.0D);
            double copyWeight = strategy.copy(LocatorUtils.property(locator, "weight", sourceWeight), sourceWeight);
            copy.setWeight(copyWeight);
            if (this.type!= null) {
                Type sourceType;
                sourceType = this.getType();
                Type copyType = ((Type) strategy.copy(LocatorUtils.property(locator, "type", sourceType), sourceType));
                copy.setType(copyType);
            } else {
                copy.type = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new DesignCategoryDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof DesignCategoryDTO) {
            final DesignCategoryDTO target = this;
            final DesignCategoryDTO leftObject = ((DesignCategoryDTO) left);
            final DesignCategoryDTO rightObject = ((DesignCategoryDTO) right);
            {
                String lhsName;
                lhsName = leftObject.getName();
                String rhsName;
                rhsName = rightObject.getName();
                String mergedName = ((String) strategy.merge(LocatorUtils.property(leftLocator, "name", lhsName), LocatorUtils.property(rightLocator, "name", rhsName), lhsName, rhsName));
                target.setName(mergedName);
            }
            {
                Integer lhsEntryCount;
                lhsEntryCount = leftObject.getEntryCount();
                Integer rhsEntryCount;
                rhsEntryCount = rightObject.getEntryCount();
                Integer mergedEntryCount = ((Integer) strategy.merge(LocatorUtils.property(leftLocator, "entryCount", lhsEntryCount), LocatorUtils.property(rightLocator, "entryCount", rhsEntryCount), lhsEntryCount, rhsEntryCount));
                target.setEntryCount(mergedEntryCount);
            }
            {
                Reference lhsDesigns;
                lhsDesigns = leftObject.getDesigns();
                Reference rhsDesigns;
                rhsDesigns = rightObject.getDesigns();
                Reference mergedDesigns = ((Reference) strategy.merge(LocatorUtils.property(leftLocator, "designs", lhsDesigns), LocatorUtils.property(rightLocator, "designs", rhsDesigns), lhsDesigns, rhsDesigns));
                target.setDesigns(mergedDesigns);
            }
            {
                DesignCategoryDTO.DesignCategories lhsDesignCategories;
                lhsDesignCategories = leftObject.getDesignCategories();
                DesignCategoryDTO.DesignCategories rhsDesignCategories;
                rhsDesignCategories = rightObject.getDesignCategories();
                DesignCategoryDTO.DesignCategories mergedDesignCategories = ((DesignCategoryDTO.DesignCategories) strategy.merge(LocatorUtils.property(leftLocator, "designCategories", lhsDesignCategories), LocatorUtils.property(rightLocator, "designCategories", rhsDesignCategories), lhsDesignCategories, rhsDesignCategories));
                target.setDesignCategories(mergedDesignCategories);
            }
            {
                double lhsWeight;
                lhsWeight = (true?leftObject.getWeight(): 0.0D);
                double rhsWeight;
                rhsWeight = (true?rightObject.getWeight(): 0.0D);
                double mergedWeight = ((double) strategy.merge(LocatorUtils.property(leftLocator, "weight", lhsWeight), LocatorUtils.property(rightLocator, "weight", rhsWeight), lhsWeight, rhsWeight));
                target.setWeight(mergedWeight);
            }
            {
                Type lhsType;
                lhsType = leftObject.getType();
                Type rhsType;
                rhsType = rightObject.getType();
                Type mergedType = ((Type) strategy.merge(LocatorUtils.property(leftLocator, "type", lhsType), LocatorUtils.property(rightLocator, "type", rhsType), lhsType, rhsType));
                target.setType(mergedType);
            }
        }
    }

    public DesignCategoryDTO withName(String value) {
        setName(value);
        return this;
    }

    public DesignCategoryDTO withEntryCount(Integer value) {
        setEntryCount(value);
        return this;
    }

    public DesignCategoryDTO withDesigns(Reference value) {
        setDesigns(value);
        return this;
    }

    public DesignCategoryDTO withDesignCategories(DesignCategoryDTO.DesignCategories value) {
        setDesignCategories(value);
        return this;
    }

    public DesignCategoryDTO withWeight(double value) {
        setWeight(value);
        return this;
    }

    public DesignCategoryDTO withType(Type value) {
        setType(value);
        return this;
    }

    @Override
    public DesignCategoryDTO withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public DesignCategoryDTO withHref(String value) {
        setHref(value);
        return this;
    }

    @Override
    public DesignCategoryDTO withLifeCycleState(String value) {
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
     *         &lt;element ref="{http://api.company.biz}designCategory" maxOccurs="unbounded" minOccurs="0"/>
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
        "designCategory"
    })
    public static class DesignCategories
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        protected List<DesignCategoryDTO> designCategory;

        /**
         * Gets the value of the designCategory property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the designCategory property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDesignCategory().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DesignCategoryDTO }
         * 
         * 
         */
        public List<DesignCategoryDTO> getDesignCategory() {
            if (designCategory == null) {
                designCategory = new ArrayList<DesignCategoryDTO>();
            }
            return this.designCategory;
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
                List<DesignCategoryDTO> theDesignCategory;
                theDesignCategory = (((this.designCategory!= null)&&(!this.designCategory.isEmpty()))?this.getDesignCategory():null);
                strategy.appendField(locator, this, "designCategory", buffer, theDesignCategory);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof DesignCategoryDTO.DesignCategories)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final DesignCategoryDTO.DesignCategories that = ((DesignCategoryDTO.DesignCategories) object);
            {
                List<DesignCategoryDTO> lhsDesignCategory;
                lhsDesignCategory = (((this.designCategory!= null)&&(!this.designCategory.isEmpty()))?this.getDesignCategory():null);
                List<DesignCategoryDTO> rhsDesignCategory;
                rhsDesignCategory = (((that.designCategory!= null)&&(!that.designCategory.isEmpty()))?that.getDesignCategory():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "designCategory", lhsDesignCategory), LocatorUtils.property(thatLocator, "designCategory", rhsDesignCategory), lhsDesignCategory, rhsDesignCategory)) {
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
                List<DesignCategoryDTO> theDesignCategory;
                theDesignCategory = (((this.designCategory!= null)&&(!this.designCategory.isEmpty()))?this.getDesignCategory():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "designCategory", theDesignCategory), currentHashCode, theDesignCategory);
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
            if (draftCopy instanceof DesignCategoryDTO.DesignCategories) {
                final DesignCategoryDTO.DesignCategories copy = ((DesignCategoryDTO.DesignCategories) draftCopy);
                if ((this.designCategory!= null)&&(!this.designCategory.isEmpty())) {
                    List<DesignCategoryDTO> sourceDesignCategory;
                    sourceDesignCategory = (((this.designCategory!= null)&&(!this.designCategory.isEmpty()))?this.getDesignCategory():null);
                    @SuppressWarnings("unchecked")
                    List<DesignCategoryDTO> copyDesignCategory = ((List<DesignCategoryDTO> ) strategy.copy(LocatorUtils.property(locator, "designCategory", sourceDesignCategory), sourceDesignCategory));
                    copy.designCategory = null;
                    if (copyDesignCategory!= null) {
                        List<DesignCategoryDTO> uniqueDesignCategoryl = copy.getDesignCategory();
                        uniqueDesignCategoryl.addAll(copyDesignCategory);
                    }
                } else {
                    copy.designCategory = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new DesignCategoryDTO.DesignCategories();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof DesignCategoryDTO.DesignCategories) {
                final DesignCategoryDTO.DesignCategories target = this;
                final DesignCategoryDTO.DesignCategories leftObject = ((DesignCategoryDTO.DesignCategories) left);
                final DesignCategoryDTO.DesignCategories rightObject = ((DesignCategoryDTO.DesignCategories) right);
                {
                    List<DesignCategoryDTO> lhsDesignCategory;
                    lhsDesignCategory = (((leftObject.designCategory!= null)&&(!leftObject.designCategory.isEmpty()))?leftObject.getDesignCategory():null);
                    List<DesignCategoryDTO> rhsDesignCategory;
                    rhsDesignCategory = (((rightObject.designCategory!= null)&&(!rightObject.designCategory.isEmpty()))?rightObject.getDesignCategory():null);
                    List<DesignCategoryDTO> mergedDesignCategory = ((List<DesignCategoryDTO> ) strategy.merge(LocatorUtils.property(leftLocator, "designCategory", lhsDesignCategory), LocatorUtils.property(rightLocator, "designCategory", rhsDesignCategory), lhsDesignCategory, rhsDesignCategory));
                    target.designCategory = null;
                    if (mergedDesignCategory!= null) {
                        List<DesignCategoryDTO> uniqueDesignCategoryl = target.getDesignCategory();
                        uniqueDesignCategoryl.addAll(mergedDesignCategory);
                    }
                }
            }
        }

        public DesignCategoryDTO.DesignCategories withDesignCategory(DesignCategoryDTO... values) {
            if (values!= null) {
                for (DesignCategoryDTO value: values) {
                    getDesignCategory().add(value);
                }
            }
            return this;
        }

        public DesignCategoryDTO.DesignCategories withDesignCategory(Collection<DesignCategoryDTO> values) {
            if (values!= null) {
                getDesignCategory().addAll(values);
            }
            return this;
        }

    }

}
