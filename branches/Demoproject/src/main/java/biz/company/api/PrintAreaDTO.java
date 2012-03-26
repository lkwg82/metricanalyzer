//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.19 at 07:09:03 PM CEST 
//


package biz.company.api;

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
 * <p>Java class for printAreaDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="printAreaDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.company.biz}dto">
 *       &lt;sequence>
 *         &lt;element name="appearanceColorIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="defaultView" type="{http://api.company.biz}reference"/>
 *         &lt;element name="restrictions" type="{http://api.company.biz}printAreaRestrictionsDTO"/>
 *         &lt;element name="boundary" type="{http://api.company.biz}printAreaBoundaryDTO"/>
 *         &lt;element name="defaultPositioningBox" type="{http://api.company.biz}defaultPositioningBoxDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "printAreaDTO", propOrder = {
    "appearanceColorIndex",
    "defaultView",
    "restrictions",
    "boundary",
    "defaultPositioningBox"
})
public class PrintAreaDTO
    extends Dto
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected int appearanceColorIndex;
    @XmlElement(required = true)
    protected Reference defaultView;
    @XmlElement(required = true)
    protected PrintAreaRestrictionsDTO restrictions;
    @XmlElement(required = true)
    protected PrintAreaBoundaryDTO boundary;
    protected DefaultPositioningBoxDTO defaultPositioningBox;

    /**
     * Gets the value of the appearanceColorIndex property.
     * 
     */
    public int getAppearanceColorIndex() {
        return appearanceColorIndex;
    }

    /**
     * Sets the value of the appearanceColorIndex property.
     * 
     */
    public void setAppearanceColorIndex(int value) {
        this.appearanceColorIndex = value;
    }

    /**
     * Gets the value of the defaultView property.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getDefaultView() {
        return defaultView;
    }

    /**
     * Sets the value of the defaultView property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setDefaultView(Reference value) {
        this.defaultView = value;
    }

    /**
     * Gets the value of the restrictions property.
     * 
     * @return
     *     possible object is
     *     {@link PrintAreaRestrictionsDTO }
     *     
     */
    public PrintAreaRestrictionsDTO getRestrictions() {
        return restrictions;
    }

    /**
     * Sets the value of the restrictions property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrintAreaRestrictionsDTO }
     *     
     */
    public void setRestrictions(PrintAreaRestrictionsDTO value) {
        this.restrictions = value;
    }

    /**
     * Gets the value of the boundary property.
     * 
     * @return
     *     possible object is
     *     {@link PrintAreaBoundaryDTO }
     *     
     */
    public PrintAreaBoundaryDTO getBoundary() {
        return boundary;
    }

    /**
     * Sets the value of the boundary property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrintAreaBoundaryDTO }
     *     
     */
    public void setBoundary(PrintAreaBoundaryDTO value) {
        this.boundary = value;
    }

    /**
     * Gets the value of the defaultPositioningBox property.
     * 
     * @return
     *     possible object is
     *     {@link DefaultPositioningBoxDTO }
     *     
     */
    public DefaultPositioningBoxDTO getDefaultPositioningBox() {
        return defaultPositioningBox;
    }

    /**
     * Sets the value of the defaultPositioningBox property.
     * 
     * @param value
     *     allowed object is
     *     {@link DefaultPositioningBoxDTO }
     *     
     */
    public void setDefaultPositioningBox(DefaultPositioningBoxDTO value) {
        this.defaultPositioningBox = value;
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
            int theAppearanceColorIndex;
            theAppearanceColorIndex = (true?this.getAppearanceColorIndex(): 0);
            strategy.appendField(locator, this, "appearanceColorIndex", buffer, theAppearanceColorIndex);
        }
        {
            Reference theDefaultView;
            theDefaultView = this.getDefaultView();
            strategy.appendField(locator, this, "defaultView", buffer, theDefaultView);
        }
        {
            PrintAreaRestrictionsDTO theRestrictions;
            theRestrictions = this.getRestrictions();
            strategy.appendField(locator, this, "restrictions", buffer, theRestrictions);
        }
        {
            PrintAreaBoundaryDTO theBoundary;
            theBoundary = this.getBoundary();
            strategy.appendField(locator, this, "boundary", buffer, theBoundary);
        }
        {
            DefaultPositioningBoxDTO theDefaultPositioningBox;
            theDefaultPositioningBox = this.getDefaultPositioningBox();
            strategy.appendField(locator, this, "defaultPositioningBox", buffer, theDefaultPositioningBox);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PrintAreaDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final PrintAreaDTO that = ((PrintAreaDTO) object);
        {
            int lhsAppearanceColorIndex;
            lhsAppearanceColorIndex = (true?this.getAppearanceColorIndex(): 0);
            int rhsAppearanceColorIndex;
            rhsAppearanceColorIndex = (true?that.getAppearanceColorIndex(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "appearanceColorIndex", lhsAppearanceColorIndex), LocatorUtils.property(thatLocator, "appearanceColorIndex", rhsAppearanceColorIndex), lhsAppearanceColorIndex, rhsAppearanceColorIndex)) {
                return false;
            }
        }
        {
            Reference lhsDefaultView;
            lhsDefaultView = this.getDefaultView();
            Reference rhsDefaultView;
            rhsDefaultView = that.getDefaultView();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "defaultView", lhsDefaultView), LocatorUtils.property(thatLocator, "defaultView", rhsDefaultView), lhsDefaultView, rhsDefaultView)) {
                return false;
            }
        }
        {
            PrintAreaRestrictionsDTO lhsRestrictions;
            lhsRestrictions = this.getRestrictions();
            PrintAreaRestrictionsDTO rhsRestrictions;
            rhsRestrictions = that.getRestrictions();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "restrictions", lhsRestrictions), LocatorUtils.property(thatLocator, "restrictions", rhsRestrictions), lhsRestrictions, rhsRestrictions)) {
                return false;
            }
        }
        {
            PrintAreaBoundaryDTO lhsBoundary;
            lhsBoundary = this.getBoundary();
            PrintAreaBoundaryDTO rhsBoundary;
            rhsBoundary = that.getBoundary();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "boundary", lhsBoundary), LocatorUtils.property(thatLocator, "boundary", rhsBoundary), lhsBoundary, rhsBoundary)) {
                return false;
            }
        }
        {
            DefaultPositioningBoxDTO lhsDefaultPositioningBox;
            lhsDefaultPositioningBox = this.getDefaultPositioningBox();
            DefaultPositioningBoxDTO rhsDefaultPositioningBox;
            rhsDefaultPositioningBox = that.getDefaultPositioningBox();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "defaultPositioningBox", lhsDefaultPositioningBox), LocatorUtils.property(thatLocator, "defaultPositioningBox", rhsDefaultPositioningBox), lhsDefaultPositioningBox, rhsDefaultPositioningBox)) {
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
            int theAppearanceColorIndex;
            theAppearanceColorIndex = (true?this.getAppearanceColorIndex(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "appearanceColorIndex", theAppearanceColorIndex), currentHashCode, theAppearanceColorIndex);
        }
        {
            Reference theDefaultView;
            theDefaultView = this.getDefaultView();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "defaultView", theDefaultView), currentHashCode, theDefaultView);
        }
        {
            PrintAreaRestrictionsDTO theRestrictions;
            theRestrictions = this.getRestrictions();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "restrictions", theRestrictions), currentHashCode, theRestrictions);
        }
        {
            PrintAreaBoundaryDTO theBoundary;
            theBoundary = this.getBoundary();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "boundary", theBoundary), currentHashCode, theBoundary);
        }
        {
            DefaultPositioningBoxDTO theDefaultPositioningBox;
            theDefaultPositioningBox = this.getDefaultPositioningBox();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "defaultPositioningBox", theDefaultPositioningBox), currentHashCode, theDefaultPositioningBox);
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
        if (draftCopy instanceof PrintAreaDTO) {
            final PrintAreaDTO copy = ((PrintAreaDTO) draftCopy);
            int sourceAppearanceColorIndex;
            sourceAppearanceColorIndex = (true?this.getAppearanceColorIndex(): 0);
            int copyAppearanceColorIndex = strategy.copy(LocatorUtils.property(locator, "appearanceColorIndex", sourceAppearanceColorIndex), sourceAppearanceColorIndex);
            copy.setAppearanceColorIndex(copyAppearanceColorIndex);
            if (this.defaultView!= null) {
                Reference sourceDefaultView;
                sourceDefaultView = this.getDefaultView();
                Reference copyDefaultView = ((Reference) strategy.copy(LocatorUtils.property(locator, "defaultView", sourceDefaultView), sourceDefaultView));
                copy.setDefaultView(copyDefaultView);
            } else {
                copy.defaultView = null;
            }
            if (this.restrictions!= null) {
                PrintAreaRestrictionsDTO sourceRestrictions;
                sourceRestrictions = this.getRestrictions();
                PrintAreaRestrictionsDTO copyRestrictions = ((PrintAreaRestrictionsDTO) strategy.copy(LocatorUtils.property(locator, "restrictions", sourceRestrictions), sourceRestrictions));
                copy.setRestrictions(copyRestrictions);
            } else {
                copy.restrictions = null;
            }
            if (this.boundary!= null) {
                PrintAreaBoundaryDTO sourceBoundary;
                sourceBoundary = this.getBoundary();
                PrintAreaBoundaryDTO copyBoundary = ((PrintAreaBoundaryDTO) strategy.copy(LocatorUtils.property(locator, "boundary", sourceBoundary), sourceBoundary));
                copy.setBoundary(copyBoundary);
            } else {
                copy.boundary = null;
            }
            if (this.defaultPositioningBox!= null) {
                DefaultPositioningBoxDTO sourceDefaultPositioningBox;
                sourceDefaultPositioningBox = this.getDefaultPositioningBox();
                DefaultPositioningBoxDTO copyDefaultPositioningBox = ((DefaultPositioningBoxDTO) strategy.copy(LocatorUtils.property(locator, "defaultPositioningBox", sourceDefaultPositioningBox), sourceDefaultPositioningBox));
                copy.setDefaultPositioningBox(copyDefaultPositioningBox);
            } else {
                copy.defaultPositioningBox = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new PrintAreaDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof PrintAreaDTO) {
            final PrintAreaDTO target = this;
            final PrintAreaDTO leftObject = ((PrintAreaDTO) left);
            final PrintAreaDTO rightObject = ((PrintAreaDTO) right);
            {
                int lhsAppearanceColorIndex;
                lhsAppearanceColorIndex = (true?leftObject.getAppearanceColorIndex(): 0);
                int rhsAppearanceColorIndex;
                rhsAppearanceColorIndex = (true?rightObject.getAppearanceColorIndex(): 0);
                int mergedAppearanceColorIndex = ((int) strategy.merge(LocatorUtils.property(leftLocator, "appearanceColorIndex", lhsAppearanceColorIndex), LocatorUtils.property(rightLocator, "appearanceColorIndex", rhsAppearanceColorIndex), lhsAppearanceColorIndex, rhsAppearanceColorIndex));
                target.setAppearanceColorIndex(mergedAppearanceColorIndex);
            }
            {
                Reference lhsDefaultView;
                lhsDefaultView = leftObject.getDefaultView();
                Reference rhsDefaultView;
                rhsDefaultView = rightObject.getDefaultView();
                Reference mergedDefaultView = ((Reference) strategy.merge(LocatorUtils.property(leftLocator, "defaultView", lhsDefaultView), LocatorUtils.property(rightLocator, "defaultView", rhsDefaultView), lhsDefaultView, rhsDefaultView));
                target.setDefaultView(mergedDefaultView);
            }
            {
                PrintAreaRestrictionsDTO lhsRestrictions;
                lhsRestrictions = leftObject.getRestrictions();
                PrintAreaRestrictionsDTO rhsRestrictions;
                rhsRestrictions = rightObject.getRestrictions();
                PrintAreaRestrictionsDTO mergedRestrictions = ((PrintAreaRestrictionsDTO) strategy.merge(LocatorUtils.property(leftLocator, "restrictions", lhsRestrictions), LocatorUtils.property(rightLocator, "restrictions", rhsRestrictions), lhsRestrictions, rhsRestrictions));
                target.setRestrictions(mergedRestrictions);
            }
            {
                PrintAreaBoundaryDTO lhsBoundary;
                lhsBoundary = leftObject.getBoundary();
                PrintAreaBoundaryDTO rhsBoundary;
                rhsBoundary = rightObject.getBoundary();
                PrintAreaBoundaryDTO mergedBoundary = ((PrintAreaBoundaryDTO) strategy.merge(LocatorUtils.property(leftLocator, "boundary", lhsBoundary), LocatorUtils.property(rightLocator, "boundary", rhsBoundary), lhsBoundary, rhsBoundary));
                target.setBoundary(mergedBoundary);
            }
            {
                DefaultPositioningBoxDTO lhsDefaultPositioningBox;
                lhsDefaultPositioningBox = leftObject.getDefaultPositioningBox();
                DefaultPositioningBoxDTO rhsDefaultPositioningBox;
                rhsDefaultPositioningBox = rightObject.getDefaultPositioningBox();
                DefaultPositioningBoxDTO mergedDefaultPositioningBox = ((DefaultPositioningBoxDTO) strategy.merge(LocatorUtils.property(leftLocator, "defaultPositioningBox", lhsDefaultPositioningBox), LocatorUtils.property(rightLocator, "defaultPositioningBox", rhsDefaultPositioningBox), lhsDefaultPositioningBox, rhsDefaultPositioningBox));
                target.setDefaultPositioningBox(mergedDefaultPositioningBox);
            }
        }
    }

    public PrintAreaDTO withAppearanceColorIndex(int value) {
        setAppearanceColorIndex(value);
        return this;
    }

    public PrintAreaDTO withDefaultView(Reference value) {
        setDefaultView(value);
        return this;
    }

    public PrintAreaDTO withRestrictions(PrintAreaRestrictionsDTO value) {
        setRestrictions(value);
        return this;
    }

    public PrintAreaDTO withBoundary(PrintAreaBoundaryDTO value) {
        setBoundary(value);
        return this;
    }

    public PrintAreaDTO withDefaultPositioningBox(DefaultPositioningBoxDTO value) {
        setDefaultPositioningBox(value);
        return this;
    }

    @Override
    public PrintAreaDTO withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public PrintAreaDTO withHref(String value) {
        setHref(value);
        return this;
    }

    @Override
    public PrintAreaDTO withLifeCycleState(String value) {
        setLifeCycleState(value);
        return this;
    }

}