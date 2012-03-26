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
 * <p>Java class for productTypeDepartmentDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productTypeDepartmentDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.company.biz}dto">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categories" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="category" type="{http://api.company.biz}productTypeCategoryDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
@XmlType(name = "productTypeDepartmentDTO", propOrder = {
    "name",
    "categories"
})
public class ProductTypeDepartmentDTO
    extends Dto
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected String name;
    protected ProductTypeDepartmentDTO.Categories categories;
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
     * Gets the value of the categories property.
     * 
     * @return
     *     possible object is
     *     {@link ProductTypeDepartmentDTO.Categories }
     *     
     */
    public ProductTypeDepartmentDTO.Categories getCategories() {
        return categories;
    }

    /**
     * Sets the value of the categories property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductTypeDepartmentDTO.Categories }
     *     
     */
    public void setCategories(ProductTypeDepartmentDTO.Categories value) {
        this.categories = value;
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
            ProductTypeDepartmentDTO.Categories theCategories;
            theCategories = this.getCategories();
            strategy.appendField(locator, this, "categories", buffer, theCategories);
        }
        {
            Double theWeight;
            theWeight = this.getWeight();
            strategy.appendField(locator, this, "weight", buffer, theWeight);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductTypeDepartmentDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final ProductTypeDepartmentDTO that = ((ProductTypeDepartmentDTO) object);
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
            ProductTypeDepartmentDTO.Categories lhsCategories;
            lhsCategories = this.getCategories();
            ProductTypeDepartmentDTO.Categories rhsCategories;
            rhsCategories = that.getCategories();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "categories", lhsCategories), LocatorUtils.property(thatLocator, "categories", rhsCategories), lhsCategories, rhsCategories)) {
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
            ProductTypeDepartmentDTO.Categories theCategories;
            theCategories = this.getCategories();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "categories", theCategories), currentHashCode, theCategories);
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
        if (draftCopy instanceof ProductTypeDepartmentDTO) {
            final ProductTypeDepartmentDTO copy = ((ProductTypeDepartmentDTO) draftCopy);
            if (this.name!= null) {
                String sourceName;
                sourceName = this.getName();
                String copyName = ((String) strategy.copy(LocatorUtils.property(locator, "name", sourceName), sourceName));
                copy.setName(copyName);
            } else {
                copy.name = null;
            }
            if (this.categories!= null) {
                ProductTypeDepartmentDTO.Categories sourceCategories;
                sourceCategories = this.getCategories();
                ProductTypeDepartmentDTO.Categories copyCategories = ((ProductTypeDepartmentDTO.Categories) strategy.copy(LocatorUtils.property(locator, "categories", sourceCategories), sourceCategories));
                copy.setCategories(copyCategories);
            } else {
                copy.categories = null;
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
        return new ProductTypeDepartmentDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ProductTypeDepartmentDTO) {
            final ProductTypeDepartmentDTO target = this;
            final ProductTypeDepartmentDTO leftObject = ((ProductTypeDepartmentDTO) left);
            final ProductTypeDepartmentDTO rightObject = ((ProductTypeDepartmentDTO) right);
            {
                String lhsName;
                lhsName = leftObject.getName();
                String rhsName;
                rhsName = rightObject.getName();
                String mergedName = ((String) strategy.merge(LocatorUtils.property(leftLocator, "name", lhsName), LocatorUtils.property(rightLocator, "name", rhsName), lhsName, rhsName));
                target.setName(mergedName);
            }
            {
                ProductTypeDepartmentDTO.Categories lhsCategories;
                lhsCategories = leftObject.getCategories();
                ProductTypeDepartmentDTO.Categories rhsCategories;
                rhsCategories = rightObject.getCategories();
                ProductTypeDepartmentDTO.Categories mergedCategories = ((ProductTypeDepartmentDTO.Categories) strategy.merge(LocatorUtils.property(leftLocator, "categories", lhsCategories), LocatorUtils.property(rightLocator, "categories", rhsCategories), lhsCategories, rhsCategories));
                target.setCategories(mergedCategories);
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

    public ProductTypeDepartmentDTO withName(String value) {
        setName(value);
        return this;
    }

    public ProductTypeDepartmentDTO withCategories(ProductTypeDepartmentDTO.Categories value) {
        setCategories(value);
        return this;
    }

    public ProductTypeDepartmentDTO withWeight(Double value) {
        setWeight(value);
        return this;
    }

    @Override
    public ProductTypeDepartmentDTO withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public ProductTypeDepartmentDTO withHref(String value) {
        setHref(value);
        return this;
    }

    @Override
    public ProductTypeDepartmentDTO withLifeCycleState(String value) {
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
     *         &lt;element name="category" type="{http://api.company.biz}productTypeCategoryDTO" maxOccurs="unbounded" minOccurs="0"/>
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
        "category"
    })
    public static class Categories
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        protected List<ProductTypeCategoryDTO> category;

        /**
         * Gets the value of the category property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the category property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCategory().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProductTypeCategoryDTO }
         * 
         * 
         */
        public List<ProductTypeCategoryDTO> getCategory() {
            if (category == null) {
                category = new ArrayList<ProductTypeCategoryDTO>();
            }
            return this.category;
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
                List<ProductTypeCategoryDTO> theCategory;
                theCategory = (((this.category!= null)&&(!this.category.isEmpty()))?this.getCategory():null);
                strategy.appendField(locator, this, "category", buffer, theCategory);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof ProductTypeDepartmentDTO.Categories)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ProductTypeDepartmentDTO.Categories that = ((ProductTypeDepartmentDTO.Categories) object);
            {
                List<ProductTypeCategoryDTO> lhsCategory;
                lhsCategory = (((this.category!= null)&&(!this.category.isEmpty()))?this.getCategory():null);
                List<ProductTypeCategoryDTO> rhsCategory;
                rhsCategory = (((that.category!= null)&&(!that.category.isEmpty()))?that.getCategory():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "category", lhsCategory), LocatorUtils.property(thatLocator, "category", rhsCategory), lhsCategory, rhsCategory)) {
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
                List<ProductTypeCategoryDTO> theCategory;
                theCategory = (((this.category!= null)&&(!this.category.isEmpty()))?this.getCategory():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "category", theCategory), currentHashCode, theCategory);
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
            if (draftCopy instanceof ProductTypeDepartmentDTO.Categories) {
                final ProductTypeDepartmentDTO.Categories copy = ((ProductTypeDepartmentDTO.Categories) draftCopy);
                if ((this.category!= null)&&(!this.category.isEmpty())) {
                    List<ProductTypeCategoryDTO> sourceCategory;
                    sourceCategory = (((this.category!= null)&&(!this.category.isEmpty()))?this.getCategory():null);
                    @SuppressWarnings("unchecked")
                    List<ProductTypeCategoryDTO> copyCategory = ((List<ProductTypeCategoryDTO> ) strategy.copy(LocatorUtils.property(locator, "category", sourceCategory), sourceCategory));
                    copy.category = null;
                    if (copyCategory!= null) {
                        List<ProductTypeCategoryDTO> uniqueCategoryl = copy.getCategory();
                        uniqueCategoryl.addAll(copyCategory);
                    }
                } else {
                    copy.category = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new ProductTypeDepartmentDTO.Categories();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof ProductTypeDepartmentDTO.Categories) {
                final ProductTypeDepartmentDTO.Categories target = this;
                final ProductTypeDepartmentDTO.Categories leftObject = ((ProductTypeDepartmentDTO.Categories) left);
                final ProductTypeDepartmentDTO.Categories rightObject = ((ProductTypeDepartmentDTO.Categories) right);
                {
                    List<ProductTypeCategoryDTO> lhsCategory;
                    lhsCategory = (((leftObject.category!= null)&&(!leftObject.category.isEmpty()))?leftObject.getCategory():null);
                    List<ProductTypeCategoryDTO> rhsCategory;
                    rhsCategory = (((rightObject.category!= null)&&(!rightObject.category.isEmpty()))?rightObject.getCategory():null);
                    List<ProductTypeCategoryDTO> mergedCategory = ((List<ProductTypeCategoryDTO> ) strategy.merge(LocatorUtils.property(leftLocator, "category", lhsCategory), LocatorUtils.property(rightLocator, "category", rhsCategory), lhsCategory, rhsCategory));
                    target.category = null;
                    if (mergedCategory!= null) {
                        List<ProductTypeCategoryDTO> uniqueCategoryl = target.getCategory();
                        uniqueCategoryl.addAll(mergedCategory);
                    }
                }
            }
        }

        public ProductTypeDepartmentDTO.Categories withCategory(ProductTypeCategoryDTO... values) {
            if (values!= null) {
                for (ProductTypeCategoryDTO value: values) {
                    getCategory().add(value);
                }
            }
            return this;
        }

        public ProductTypeDepartmentDTO.Categories withCategory(Collection<ProductTypeCategoryDTO> values) {
            if (values!= null) {
                getCategory().addAll(values);
            }
            return this;
        }

    }

}