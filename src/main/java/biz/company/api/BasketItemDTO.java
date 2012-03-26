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
 * <p>Java class for basketItemDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="basketItemDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.company.biz}dto">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="element" type="{http://api.company.biz}elementDTO" minOccurs="0"/>
 *         &lt;element name="links" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://api.company.biz}link" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://api.company.biz}price" minOccurs="0"/>
 *         &lt;element name="shop" type="{http://api.company.biz}reference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basketItemDTO", propOrder = {
    "description",
    "quantity",
    "element",
    "links",
    "price",
    "shop"
})
public class BasketItemDTO
    extends Dto
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected String description;
    protected Integer quantity;
    protected ElementDTO element;
    protected BasketItemDTO.Links links;
    protected ElementPrice price;
    protected Reference shop;

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
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantity(Integer value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the element property.
     * 
     * @return
     *     possible object is
     *     {@link ElementDTO }
     *     
     */
    public ElementDTO getElement() {
        return element;
    }

    /**
     * Sets the value of the element property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElementDTO }
     *     
     */
    public void setElement(ElementDTO value) {
        this.element = value;
    }

    /**
     * Gets the value of the links property.
     * 
     * @return
     *     possible object is
     *     {@link BasketItemDTO.Links }
     *     
     */
    public BasketItemDTO.Links getLinks() {
        return links;
    }

    /**
     * Sets the value of the links property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasketItemDTO.Links }
     *     
     */
    public void setLinks(BasketItemDTO.Links value) {
        this.links = value;
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
     * Gets the value of the shop property.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getShop() {
        return shop;
    }

    /**
     * Sets the value of the shop property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setShop(Reference value) {
        this.shop = value;
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
            String theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            Integer theQuantity;
            theQuantity = this.getQuantity();
            strategy.appendField(locator, this, "quantity", buffer, theQuantity);
        }
        {
            ElementDTO theElement;
            theElement = this.getElement();
            strategy.appendField(locator, this, "element", buffer, theElement);
        }
        {
            BasketItemDTO.Links theLinks;
            theLinks = this.getLinks();
            strategy.appendField(locator, this, "links", buffer, theLinks);
        }
        {
            ElementPrice thePrice;
            thePrice = this.getPrice();
            strategy.appendField(locator, this, "price", buffer, thePrice);
        }
        {
            Reference theShop;
            theShop = this.getShop();
            strategy.appendField(locator, this, "shop", buffer, theShop);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BasketItemDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final BasketItemDTO that = ((BasketItemDTO) object);
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
            Integer lhsQuantity;
            lhsQuantity = this.getQuantity();
            Integer rhsQuantity;
            rhsQuantity = that.getQuantity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "quantity", lhsQuantity), LocatorUtils.property(thatLocator, "quantity", rhsQuantity), lhsQuantity, rhsQuantity)) {
                return false;
            }
        }
        {
            ElementDTO lhsElement;
            lhsElement = this.getElement();
            ElementDTO rhsElement;
            rhsElement = that.getElement();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "element", lhsElement), LocatorUtils.property(thatLocator, "element", rhsElement), lhsElement, rhsElement)) {
                return false;
            }
        }
        {
            BasketItemDTO.Links lhsLinks;
            lhsLinks = this.getLinks();
            BasketItemDTO.Links rhsLinks;
            rhsLinks = that.getLinks();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "links", lhsLinks), LocatorUtils.property(thatLocator, "links", rhsLinks), lhsLinks, rhsLinks)) {
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
            Reference lhsShop;
            lhsShop = this.getShop();
            Reference rhsShop;
            rhsShop = that.getShop();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "shop", lhsShop), LocatorUtils.property(thatLocator, "shop", rhsShop), lhsShop, rhsShop)) {
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
            String theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
        }
        {
            Integer theQuantity;
            theQuantity = this.getQuantity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "quantity", theQuantity), currentHashCode, theQuantity);
        }
        {
            ElementDTO theElement;
            theElement = this.getElement();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "element", theElement), currentHashCode, theElement);
        }
        {
            BasketItemDTO.Links theLinks;
            theLinks = this.getLinks();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "links", theLinks), currentHashCode, theLinks);
        }
        {
            ElementPrice thePrice;
            thePrice = this.getPrice();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "price", thePrice), currentHashCode, thePrice);
        }
        {
            Reference theShop;
            theShop = this.getShop();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "shop", theShop), currentHashCode, theShop);
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
        if (draftCopy instanceof BasketItemDTO) {
            final BasketItemDTO copy = ((BasketItemDTO) draftCopy);
            if (this.description!= null) {
                String sourceDescription;
                sourceDescription = this.getDescription();
                String copyDescription = ((String) strategy.copy(LocatorUtils.property(locator, "description", sourceDescription), sourceDescription));
                copy.setDescription(copyDescription);
            } else {
                copy.description = null;
            }
            if (this.quantity!= null) {
                Integer sourceQuantity;
                sourceQuantity = this.getQuantity();
                Integer copyQuantity = ((Integer) strategy.copy(LocatorUtils.property(locator, "quantity", sourceQuantity), sourceQuantity));
                copy.setQuantity(copyQuantity);
            } else {
                copy.quantity = null;
            }
            if (this.element!= null) {
                ElementDTO sourceElement;
                sourceElement = this.getElement();
                ElementDTO copyElement = ((ElementDTO) strategy.copy(LocatorUtils.property(locator, "element", sourceElement), sourceElement));
                copy.setElement(copyElement);
            } else {
                copy.element = null;
            }
            if (this.links!= null) {
                BasketItemDTO.Links sourceLinks;
                sourceLinks = this.getLinks();
                BasketItemDTO.Links copyLinks = ((BasketItemDTO.Links) strategy.copy(LocatorUtils.property(locator, "links", sourceLinks), sourceLinks));
                copy.setLinks(copyLinks);
            } else {
                copy.links = null;
            }
            if (this.price!= null) {
                ElementPrice sourcePrice;
                sourcePrice = this.getPrice();
                ElementPrice copyPrice = ((ElementPrice) strategy.copy(LocatorUtils.property(locator, "price", sourcePrice), sourcePrice));
                copy.setPrice(copyPrice);
            } else {
                copy.price = null;
            }
            if (this.shop!= null) {
                Reference sourceShop;
                sourceShop = this.getShop();
                Reference copyShop = ((Reference) strategy.copy(LocatorUtils.property(locator, "shop", sourceShop), sourceShop));
                copy.setShop(copyShop);
            } else {
                copy.shop = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new BasketItemDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof BasketItemDTO) {
            final BasketItemDTO target = this;
            final BasketItemDTO leftObject = ((BasketItemDTO) left);
            final BasketItemDTO rightObject = ((BasketItemDTO) right);
            {
                String lhsDescription;
                lhsDescription = leftObject.getDescription();
                String rhsDescription;
                rhsDescription = rightObject.getDescription();
                String mergedDescription = ((String) strategy.merge(LocatorUtils.property(leftLocator, "description", lhsDescription), LocatorUtils.property(rightLocator, "description", rhsDescription), lhsDescription, rhsDescription));
                target.setDescription(mergedDescription);
            }
            {
                Integer lhsQuantity;
                lhsQuantity = leftObject.getQuantity();
                Integer rhsQuantity;
                rhsQuantity = rightObject.getQuantity();
                Integer mergedQuantity = ((Integer) strategy.merge(LocatorUtils.property(leftLocator, "quantity", lhsQuantity), LocatorUtils.property(rightLocator, "quantity", rhsQuantity), lhsQuantity, rhsQuantity));
                target.setQuantity(mergedQuantity);
            }
            {
                ElementDTO lhsElement;
                lhsElement = leftObject.getElement();
                ElementDTO rhsElement;
                rhsElement = rightObject.getElement();
                ElementDTO mergedElement = ((ElementDTO) strategy.merge(LocatorUtils.property(leftLocator, "element", lhsElement), LocatorUtils.property(rightLocator, "element", rhsElement), lhsElement, rhsElement));
                target.setElement(mergedElement);
            }
            {
                BasketItemDTO.Links lhsLinks;
                lhsLinks = leftObject.getLinks();
                BasketItemDTO.Links rhsLinks;
                rhsLinks = rightObject.getLinks();
                BasketItemDTO.Links mergedLinks = ((BasketItemDTO.Links) strategy.merge(LocatorUtils.property(leftLocator, "links", lhsLinks), LocatorUtils.property(rightLocator, "links", rhsLinks), lhsLinks, rhsLinks));
                target.setLinks(mergedLinks);
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
                Reference lhsShop;
                lhsShop = leftObject.getShop();
                Reference rhsShop;
                rhsShop = rightObject.getShop();
                Reference mergedShop = ((Reference) strategy.merge(LocatorUtils.property(leftLocator, "shop", lhsShop), LocatorUtils.property(rightLocator, "shop", rhsShop), lhsShop, rhsShop));
                target.setShop(mergedShop);
            }
        }
    }

    public BasketItemDTO withDescription(String value) {
        setDescription(value);
        return this;
    }

    public BasketItemDTO withQuantity(Integer value) {
        setQuantity(value);
        return this;
    }

    public BasketItemDTO withElement(ElementDTO value) {
        setElement(value);
        return this;
    }

    public BasketItemDTO withLinks(BasketItemDTO.Links value) {
        setLinks(value);
        return this;
    }

    public BasketItemDTO withPrice(ElementPrice value) {
        setPrice(value);
        return this;
    }

    public BasketItemDTO withShop(Reference value) {
        setShop(value);
        return this;
    }

    @Override
    public BasketItemDTO withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public BasketItemDTO withHref(String value) {
        setHref(value);
        return this;
    }

    @Override
    public BasketItemDTO withLifeCycleState(String value) {
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
     *         &lt;element ref="{http://api.company.biz}link" maxOccurs="unbounded" minOccurs="0"/>
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
        "link"
    })
    public static class Links
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        protected List<LinkDTO> link;

        /**
         * Gets the value of the link property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the link property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLink().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LinkDTO }
         * 
         * 
         */
        public List<LinkDTO> getLink() {
            if (link == null) {
                link = new ArrayList<LinkDTO>();
            }
            return this.link;
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
                List<LinkDTO> theLink;
                theLink = (((this.link!= null)&&(!this.link.isEmpty()))?this.getLink():null);
                strategy.appendField(locator, this, "link", buffer, theLink);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof BasketItemDTO.Links)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final BasketItemDTO.Links that = ((BasketItemDTO.Links) object);
            {
                List<LinkDTO> lhsLink;
                lhsLink = (((this.link!= null)&&(!this.link.isEmpty()))?this.getLink():null);
                List<LinkDTO> rhsLink;
                rhsLink = (((that.link!= null)&&(!that.link.isEmpty()))?that.getLink():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "link", lhsLink), LocatorUtils.property(thatLocator, "link", rhsLink), lhsLink, rhsLink)) {
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
                List<LinkDTO> theLink;
                theLink = (((this.link!= null)&&(!this.link.isEmpty()))?this.getLink():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "link", theLink), currentHashCode, theLink);
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
            if (draftCopy instanceof BasketItemDTO.Links) {
                final BasketItemDTO.Links copy = ((BasketItemDTO.Links) draftCopy);
                if ((this.link!= null)&&(!this.link.isEmpty())) {
                    List<LinkDTO> sourceLink;
                    sourceLink = (((this.link!= null)&&(!this.link.isEmpty()))?this.getLink():null);
                    @SuppressWarnings("unchecked")
                    List<LinkDTO> copyLink = ((List<LinkDTO> ) strategy.copy(LocatorUtils.property(locator, "link", sourceLink), sourceLink));
                    copy.link = null;
                    if (copyLink!= null) {
                        List<LinkDTO> uniqueLinkl = copy.getLink();
                        uniqueLinkl.addAll(copyLink);
                    }
                } else {
                    copy.link = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new BasketItemDTO.Links();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof BasketItemDTO.Links) {
                final BasketItemDTO.Links target = this;
                final BasketItemDTO.Links leftObject = ((BasketItemDTO.Links) left);
                final BasketItemDTO.Links rightObject = ((BasketItemDTO.Links) right);
                {
                    List<LinkDTO> lhsLink;
                    lhsLink = (((leftObject.link!= null)&&(!leftObject.link.isEmpty()))?leftObject.getLink():null);
                    List<LinkDTO> rhsLink;
                    rhsLink = (((rightObject.link!= null)&&(!rightObject.link.isEmpty()))?rightObject.getLink():null);
                    List<LinkDTO> mergedLink = ((List<LinkDTO> ) strategy.merge(LocatorUtils.property(leftLocator, "link", lhsLink), LocatorUtils.property(rightLocator, "link", rhsLink), lhsLink, rhsLink));
                    target.link = null;
                    if (mergedLink!= null) {
                        List<LinkDTO> uniqueLinkl = target.getLink();
                        uniqueLinkl.addAll(mergedLink);
                    }
                }
            }
        }

        public BasketItemDTO.Links withLink(LinkDTO... values) {
            if (values!= null) {
                for (LinkDTO value: values) {
                    getLink().add(value);
                }
            }
            return this;
        }

        public BasketItemDTO.Links withLink(Collection<LinkDTO> values) {
            if (values!= null) {
                getLink().addAll(values);
            }
            return this;
        }

    }

}
