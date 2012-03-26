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
 * <p>Java class for productTypeViewDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productTypeViewDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.company.biz}dto">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="size" type="{http://api.company.biz}dimension"/>
 *         &lt;element name="perspective" type="{http://api.company.biz}perspective" minOccurs="0"/>
 *         &lt;element name="montage" type="{http://api.company.biz}productTypeViewMontageDTO" minOccurs="0"/>
 *         &lt;element name="viewMaps" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://api.company.biz}viewMap" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="resources" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://api.company.biz}resource" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productTypeViewDTO", propOrder = {
    "name",
    "size",
    "perspective",
    "montage",
    "viewMaps",
    "resources"
})
public class ProductTypeViewDTO
    extends Dto
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected String name;
    @XmlElement(required = true)
    protected Dimension size;
    protected Perspective perspective;
    protected ProductTypeViewMontageDTO montage;
    protected ProductTypeViewDTO.ViewMaps viewMaps;
    protected ProductTypeViewDTO.Resources resources;

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
     * Gets the value of the perspective property.
     * 
     * @return
     *     possible object is
     *     {@link Perspective }
     *     
     */
    public Perspective getPerspective() {
        return perspective;
    }

    /**
     * Sets the value of the perspective property.
     * 
     * @param value
     *     allowed object is
     *     {@link Perspective }
     *     
     */
    public void setPerspective(Perspective value) {
        this.perspective = value;
    }

    /**
     * Gets the value of the montage property.
     * 
     * @return
     *     possible object is
     *     {@link ProductTypeViewMontageDTO }
     *     
     */
    public ProductTypeViewMontageDTO getMontage() {
        return montage;
    }

    /**
     * Sets the value of the montage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductTypeViewMontageDTO }
     *     
     */
    public void setMontage(ProductTypeViewMontageDTO value) {
        this.montage = value;
    }

    /**
     * Gets the value of the viewMaps property.
     * 
     * @return
     *     possible object is
     *     {@link ProductTypeViewDTO.ViewMaps }
     *     
     */
    public ProductTypeViewDTO.ViewMaps getViewMaps() {
        return viewMaps;
    }

    /**
     * Sets the value of the viewMaps property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductTypeViewDTO.ViewMaps }
     *     
     */
    public void setViewMaps(ProductTypeViewDTO.ViewMaps value) {
        this.viewMaps = value;
    }

    /**
     * Gets the value of the resources property.
     * 
     * @return
     *     possible object is
     *     {@link ProductTypeViewDTO.Resources }
     *     
     */
    public ProductTypeViewDTO.Resources getResources() {
        return resources;
    }

    /**
     * Sets the value of the resources property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductTypeViewDTO.Resources }
     *     
     */
    public void setResources(ProductTypeViewDTO.Resources value) {
        this.resources = value;
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
            Dimension theSize;
            theSize = this.getSize();
            strategy.appendField(locator, this, "size", buffer, theSize);
        }
        {
            Perspective thePerspective;
            thePerspective = this.getPerspective();
            strategy.appendField(locator, this, "perspective", buffer, thePerspective);
        }
        {
            ProductTypeViewMontageDTO theMontage;
            theMontage = this.getMontage();
            strategy.appendField(locator, this, "montage", buffer, theMontage);
        }
        {
            ProductTypeViewDTO.ViewMaps theViewMaps;
            theViewMaps = this.getViewMaps();
            strategy.appendField(locator, this, "viewMaps", buffer, theViewMaps);
        }
        {
            ProductTypeViewDTO.Resources theResources;
            theResources = this.getResources();
            strategy.appendField(locator, this, "resources", buffer, theResources);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductTypeViewDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final ProductTypeViewDTO that = ((ProductTypeViewDTO) object);
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
            Dimension lhsSize;
            lhsSize = this.getSize();
            Dimension rhsSize;
            rhsSize = that.getSize();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "size", lhsSize), LocatorUtils.property(thatLocator, "size", rhsSize), lhsSize, rhsSize)) {
                return false;
            }
        }
        {
            Perspective lhsPerspective;
            lhsPerspective = this.getPerspective();
            Perspective rhsPerspective;
            rhsPerspective = that.getPerspective();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perspective", lhsPerspective), LocatorUtils.property(thatLocator, "perspective", rhsPerspective), lhsPerspective, rhsPerspective)) {
                return false;
            }
        }
        {
            ProductTypeViewMontageDTO lhsMontage;
            lhsMontage = this.getMontage();
            ProductTypeViewMontageDTO rhsMontage;
            rhsMontage = that.getMontage();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "montage", lhsMontage), LocatorUtils.property(thatLocator, "montage", rhsMontage), lhsMontage, rhsMontage)) {
                return false;
            }
        }
        {
            ProductTypeViewDTO.ViewMaps lhsViewMaps;
            lhsViewMaps = this.getViewMaps();
            ProductTypeViewDTO.ViewMaps rhsViewMaps;
            rhsViewMaps = that.getViewMaps();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "viewMaps", lhsViewMaps), LocatorUtils.property(thatLocator, "viewMaps", rhsViewMaps), lhsViewMaps, rhsViewMaps)) {
                return false;
            }
        }
        {
            ProductTypeViewDTO.Resources lhsResources;
            lhsResources = this.getResources();
            ProductTypeViewDTO.Resources rhsResources;
            rhsResources = that.getResources();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "resources", lhsResources), LocatorUtils.property(thatLocator, "resources", rhsResources), lhsResources, rhsResources)) {
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
            Dimension theSize;
            theSize = this.getSize();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "size", theSize), currentHashCode, theSize);
        }
        {
            Perspective thePerspective;
            thePerspective = this.getPerspective();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "perspective", thePerspective), currentHashCode, thePerspective);
        }
        {
            ProductTypeViewMontageDTO theMontage;
            theMontage = this.getMontage();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "montage", theMontage), currentHashCode, theMontage);
        }
        {
            ProductTypeViewDTO.ViewMaps theViewMaps;
            theViewMaps = this.getViewMaps();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "viewMaps", theViewMaps), currentHashCode, theViewMaps);
        }
        {
            ProductTypeViewDTO.Resources theResources;
            theResources = this.getResources();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "resources", theResources), currentHashCode, theResources);
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
        if (draftCopy instanceof ProductTypeViewDTO) {
            final ProductTypeViewDTO copy = ((ProductTypeViewDTO) draftCopy);
            if (this.name!= null) {
                String sourceName;
                sourceName = this.getName();
                String copyName = ((String) strategy.copy(LocatorUtils.property(locator, "name", sourceName), sourceName));
                copy.setName(copyName);
            } else {
                copy.name = null;
            }
            if (this.size!= null) {
                Dimension sourceSize;
                sourceSize = this.getSize();
                Dimension copySize = ((Dimension) strategy.copy(LocatorUtils.property(locator, "size", sourceSize), sourceSize));
                copy.setSize(copySize);
            } else {
                copy.size = null;
            }
            if (this.perspective!= null) {
                Perspective sourcePerspective;
                sourcePerspective = this.getPerspective();
                Perspective copyPerspective = ((Perspective) strategy.copy(LocatorUtils.property(locator, "perspective", sourcePerspective), sourcePerspective));
                copy.setPerspective(copyPerspective);
            } else {
                copy.perspective = null;
            }
            if (this.montage!= null) {
                ProductTypeViewMontageDTO sourceMontage;
                sourceMontage = this.getMontage();
                ProductTypeViewMontageDTO copyMontage = ((ProductTypeViewMontageDTO) strategy.copy(LocatorUtils.property(locator, "montage", sourceMontage), sourceMontage));
                copy.setMontage(copyMontage);
            } else {
                copy.montage = null;
            }
            if (this.viewMaps!= null) {
                ProductTypeViewDTO.ViewMaps sourceViewMaps;
                sourceViewMaps = this.getViewMaps();
                ProductTypeViewDTO.ViewMaps copyViewMaps = ((ProductTypeViewDTO.ViewMaps) strategy.copy(LocatorUtils.property(locator, "viewMaps", sourceViewMaps), sourceViewMaps));
                copy.setViewMaps(copyViewMaps);
            } else {
                copy.viewMaps = null;
            }
            if (this.resources!= null) {
                ProductTypeViewDTO.Resources sourceResources;
                sourceResources = this.getResources();
                ProductTypeViewDTO.Resources copyResources = ((ProductTypeViewDTO.Resources) strategy.copy(LocatorUtils.property(locator, "resources", sourceResources), sourceResources));
                copy.setResources(copyResources);
            } else {
                copy.resources = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new ProductTypeViewDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ProductTypeViewDTO) {
            final ProductTypeViewDTO target = this;
            final ProductTypeViewDTO leftObject = ((ProductTypeViewDTO) left);
            final ProductTypeViewDTO rightObject = ((ProductTypeViewDTO) right);
            {
                String lhsName;
                lhsName = leftObject.getName();
                String rhsName;
                rhsName = rightObject.getName();
                String mergedName = ((String) strategy.merge(LocatorUtils.property(leftLocator, "name", lhsName), LocatorUtils.property(rightLocator, "name", rhsName), lhsName, rhsName));
                target.setName(mergedName);
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
                Perspective lhsPerspective;
                lhsPerspective = leftObject.getPerspective();
                Perspective rhsPerspective;
                rhsPerspective = rightObject.getPerspective();
                Perspective mergedPerspective = ((Perspective) strategy.merge(LocatorUtils.property(leftLocator, "perspective", lhsPerspective), LocatorUtils.property(rightLocator, "perspective", rhsPerspective), lhsPerspective, rhsPerspective));
                target.setPerspective(mergedPerspective);
            }
            {
                ProductTypeViewMontageDTO lhsMontage;
                lhsMontage = leftObject.getMontage();
                ProductTypeViewMontageDTO rhsMontage;
                rhsMontage = rightObject.getMontage();
                ProductTypeViewMontageDTO mergedMontage = ((ProductTypeViewMontageDTO) strategy.merge(LocatorUtils.property(leftLocator, "montage", lhsMontage), LocatorUtils.property(rightLocator, "montage", rhsMontage), lhsMontage, rhsMontage));
                target.setMontage(mergedMontage);
            }
            {
                ProductTypeViewDTO.ViewMaps lhsViewMaps;
                lhsViewMaps = leftObject.getViewMaps();
                ProductTypeViewDTO.ViewMaps rhsViewMaps;
                rhsViewMaps = rightObject.getViewMaps();
                ProductTypeViewDTO.ViewMaps mergedViewMaps = ((ProductTypeViewDTO.ViewMaps) strategy.merge(LocatorUtils.property(leftLocator, "viewMaps", lhsViewMaps), LocatorUtils.property(rightLocator, "viewMaps", rhsViewMaps), lhsViewMaps, rhsViewMaps));
                target.setViewMaps(mergedViewMaps);
            }
            {
                ProductTypeViewDTO.Resources lhsResources;
                lhsResources = leftObject.getResources();
                ProductTypeViewDTO.Resources rhsResources;
                rhsResources = rightObject.getResources();
                ProductTypeViewDTO.Resources mergedResources = ((ProductTypeViewDTO.Resources) strategy.merge(LocatorUtils.property(leftLocator, "resources", lhsResources), LocatorUtils.property(rightLocator, "resources", rhsResources), lhsResources, rhsResources));
                target.setResources(mergedResources);
            }
        }
    }

    public ProductTypeViewDTO withName(String value) {
        setName(value);
        return this;
    }

    public ProductTypeViewDTO withSize(Dimension value) {
        setSize(value);
        return this;
    }

    public ProductTypeViewDTO withPerspective(Perspective value) {
        setPerspective(value);
        return this;
    }

    public ProductTypeViewDTO withMontage(ProductTypeViewMontageDTO value) {
        setMontage(value);
        return this;
    }

    public ProductTypeViewDTO withViewMaps(ProductTypeViewDTO.ViewMaps value) {
        setViewMaps(value);
        return this;
    }

    public ProductTypeViewDTO withResources(ProductTypeViewDTO.Resources value) {
        setResources(value);
        return this;
    }

    @Override
    public ProductTypeViewDTO withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public ProductTypeViewDTO withHref(String value) {
        setHref(value);
        return this;
    }

    @Override
    public ProductTypeViewDTO withLifeCycleState(String value) {
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
     *         &lt;element ref="{http://api.company.biz}resource" maxOccurs="unbounded"/>
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
        "resource"
    })
    public static class Resources
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        @XmlElement(required = true)
        protected List<Resource> resource;

        /**
         * Gets the value of the resource property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the resource property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getResource().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Resource }
         * 
         * 
         */
        public List<Resource> getResource() {
            if (resource == null) {
                resource = new ArrayList<Resource>();
            }
            return this.resource;
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
                List<Resource> theResource;
                theResource = (((this.resource!= null)&&(!this.resource.isEmpty()))?this.getResource():null);
                strategy.appendField(locator, this, "resource", buffer, theResource);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof ProductTypeViewDTO.Resources)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ProductTypeViewDTO.Resources that = ((ProductTypeViewDTO.Resources) object);
            {
                List<Resource> lhsResource;
                lhsResource = (((this.resource!= null)&&(!this.resource.isEmpty()))?this.getResource():null);
                List<Resource> rhsResource;
                rhsResource = (((that.resource!= null)&&(!that.resource.isEmpty()))?that.getResource():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "resource", lhsResource), LocatorUtils.property(thatLocator, "resource", rhsResource), lhsResource, rhsResource)) {
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
                List<Resource> theResource;
                theResource = (((this.resource!= null)&&(!this.resource.isEmpty()))?this.getResource():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "resource", theResource), currentHashCode, theResource);
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
            if (draftCopy instanceof ProductTypeViewDTO.Resources) {
                final ProductTypeViewDTO.Resources copy = ((ProductTypeViewDTO.Resources) draftCopy);
                if ((this.resource!= null)&&(!this.resource.isEmpty())) {
                    List<Resource> sourceResource;
                    sourceResource = (((this.resource!= null)&&(!this.resource.isEmpty()))?this.getResource():null);
                    @SuppressWarnings("unchecked")
                    List<Resource> copyResource = ((List<Resource> ) strategy.copy(LocatorUtils.property(locator, "resource", sourceResource), sourceResource));
                    copy.resource = null;
                    if (copyResource!= null) {
                        List<Resource> uniqueResourcel = copy.getResource();
                        uniqueResourcel.addAll(copyResource);
                    }
                } else {
                    copy.resource = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new ProductTypeViewDTO.Resources();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof ProductTypeViewDTO.Resources) {
                final ProductTypeViewDTO.Resources target = this;
                final ProductTypeViewDTO.Resources leftObject = ((ProductTypeViewDTO.Resources) left);
                final ProductTypeViewDTO.Resources rightObject = ((ProductTypeViewDTO.Resources) right);
                {
                    List<Resource> lhsResource;
                    lhsResource = (((leftObject.resource!= null)&&(!leftObject.resource.isEmpty()))?leftObject.getResource():null);
                    List<Resource> rhsResource;
                    rhsResource = (((rightObject.resource!= null)&&(!rightObject.resource.isEmpty()))?rightObject.getResource():null);
                    List<Resource> mergedResource = ((List<Resource> ) strategy.merge(LocatorUtils.property(leftLocator, "resource", lhsResource), LocatorUtils.property(rightLocator, "resource", rhsResource), lhsResource, rhsResource));
                    target.resource = null;
                    if (mergedResource!= null) {
                        List<Resource> uniqueResourcel = target.getResource();
                        uniqueResourcel.addAll(mergedResource);
                    }
                }
            }
        }

        public ProductTypeViewDTO.Resources withResource(Resource... values) {
            if (values!= null) {
                for (Resource value: values) {
                    getResource().add(value);
                }
            }
            return this;
        }

        public ProductTypeViewDTO.Resources withResource(Collection<Resource> values) {
            if (values!= null) {
                getResource().addAll(values);
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
     *         &lt;element ref="{http://api.company.biz}viewMap" maxOccurs="unbounded" minOccurs="0"/>
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
        "viewMap"
    })
    public static class ViewMaps
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        protected List<ProductTypeViewMapDTO> viewMap;

        /**
         * Gets the value of the viewMap property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the viewMap property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getViewMap().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProductTypeViewMapDTO }
         * 
         * 
         */
        public List<ProductTypeViewMapDTO> getViewMap() {
            if (viewMap == null) {
                viewMap = new ArrayList<ProductTypeViewMapDTO>();
            }
            return this.viewMap;
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
                List<ProductTypeViewMapDTO> theViewMap;
                theViewMap = (((this.viewMap!= null)&&(!this.viewMap.isEmpty()))?this.getViewMap():null);
                strategy.appendField(locator, this, "viewMap", buffer, theViewMap);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof ProductTypeViewDTO.ViewMaps)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ProductTypeViewDTO.ViewMaps that = ((ProductTypeViewDTO.ViewMaps) object);
            {
                List<ProductTypeViewMapDTO> lhsViewMap;
                lhsViewMap = (((this.viewMap!= null)&&(!this.viewMap.isEmpty()))?this.getViewMap():null);
                List<ProductTypeViewMapDTO> rhsViewMap;
                rhsViewMap = (((that.viewMap!= null)&&(!that.viewMap.isEmpty()))?that.getViewMap():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "viewMap", lhsViewMap), LocatorUtils.property(thatLocator, "viewMap", rhsViewMap), lhsViewMap, rhsViewMap)) {
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
                List<ProductTypeViewMapDTO> theViewMap;
                theViewMap = (((this.viewMap!= null)&&(!this.viewMap.isEmpty()))?this.getViewMap():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "viewMap", theViewMap), currentHashCode, theViewMap);
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
            if (draftCopy instanceof ProductTypeViewDTO.ViewMaps) {
                final ProductTypeViewDTO.ViewMaps copy = ((ProductTypeViewDTO.ViewMaps) draftCopy);
                if ((this.viewMap!= null)&&(!this.viewMap.isEmpty())) {
                    List<ProductTypeViewMapDTO> sourceViewMap;
                    sourceViewMap = (((this.viewMap!= null)&&(!this.viewMap.isEmpty()))?this.getViewMap():null);
                    @SuppressWarnings("unchecked")
                    List<ProductTypeViewMapDTO> copyViewMap = ((List<ProductTypeViewMapDTO> ) strategy.copy(LocatorUtils.property(locator, "viewMap", sourceViewMap), sourceViewMap));
                    copy.viewMap = null;
                    if (copyViewMap!= null) {
                        List<ProductTypeViewMapDTO> uniqueViewMapl = copy.getViewMap();
                        uniqueViewMapl.addAll(copyViewMap);
                    }
                } else {
                    copy.viewMap = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new ProductTypeViewDTO.ViewMaps();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof ProductTypeViewDTO.ViewMaps) {
                final ProductTypeViewDTO.ViewMaps target = this;
                final ProductTypeViewDTO.ViewMaps leftObject = ((ProductTypeViewDTO.ViewMaps) left);
                final ProductTypeViewDTO.ViewMaps rightObject = ((ProductTypeViewDTO.ViewMaps) right);
                {
                    List<ProductTypeViewMapDTO> lhsViewMap;
                    lhsViewMap = (((leftObject.viewMap!= null)&&(!leftObject.viewMap.isEmpty()))?leftObject.getViewMap():null);
                    List<ProductTypeViewMapDTO> rhsViewMap;
                    rhsViewMap = (((rightObject.viewMap!= null)&&(!rightObject.viewMap.isEmpty()))?rightObject.getViewMap():null);
                    List<ProductTypeViewMapDTO> mergedViewMap = ((List<ProductTypeViewMapDTO> ) strategy.merge(LocatorUtils.property(leftLocator, "viewMap", lhsViewMap), LocatorUtils.property(rightLocator, "viewMap", rhsViewMap), lhsViewMap, rhsViewMap));
                    target.viewMap = null;
                    if (mergedViewMap!= null) {
                        List<ProductTypeViewMapDTO> uniqueViewMapl = target.getViewMap();
                        uniqueViewMapl.addAll(mergedViewMap);
                    }
                }
            }
        }

        public ProductTypeViewDTO.ViewMaps withViewMap(ProductTypeViewMapDTO... values) {
            if (values!= null) {
                for (ProductTypeViewMapDTO value: values) {
                    getViewMap().add(value);
                }
            }
            return this;
        }

        public ProductTypeViewDTO.ViewMaps withViewMap(Collection<ProductTypeViewMapDTO> values) {
            if (values!= null) {
                getViewMap().addAll(values);
            }
            return this;
        }

    }

}
