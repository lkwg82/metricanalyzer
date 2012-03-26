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
 * <p>Java class for printColorRestrictionsDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="printColorRestrictionsDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="simplifiedFill" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="printableAbovePrintColors" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="printableAbovePrintColor" type="{http://api.company.biz}reference" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "printColorRestrictionsDTO", propOrder = {
    "simplifiedFill",
    "printableAbovePrintColors"
})
public class PrintColorRestrictionsDTO
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected boolean simplifiedFill;
    protected PrintColorRestrictionsDTO.PrintableAbovePrintColors printableAbovePrintColors;

    /**
     * Gets the value of the simplifiedFill property.
     * 
     */
    public boolean isSimplifiedFill() {
        return simplifiedFill;
    }

    /**
     * Sets the value of the simplifiedFill property.
     * 
     */
    public void setSimplifiedFill(boolean value) {
        this.simplifiedFill = value;
    }

    /**
     * Gets the value of the printableAbovePrintColors property.
     * 
     * @return
     *     possible object is
     *     {@link PrintColorRestrictionsDTO.PrintableAbovePrintColors }
     *     
     */
    public PrintColorRestrictionsDTO.PrintableAbovePrintColors getPrintableAbovePrintColors() {
        return printableAbovePrintColors;
    }

    /**
     * Sets the value of the printableAbovePrintColors property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrintColorRestrictionsDTO.PrintableAbovePrintColors }
     *     
     */
    public void setPrintableAbovePrintColors(PrintColorRestrictionsDTO.PrintableAbovePrintColors value) {
        this.printableAbovePrintColors = value;
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
            boolean theSimplifiedFill;
            theSimplifiedFill = (true?this.isSimplifiedFill():false);
            strategy.appendField(locator, this, "simplifiedFill", buffer, theSimplifiedFill);
        }
        {
            PrintColorRestrictionsDTO.PrintableAbovePrintColors thePrintableAbovePrintColors;
            thePrintableAbovePrintColors = this.getPrintableAbovePrintColors();
            strategy.appendField(locator, this, "printableAbovePrintColors", buffer, thePrintableAbovePrintColors);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PrintColorRestrictionsDTO)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PrintColorRestrictionsDTO that = ((PrintColorRestrictionsDTO) object);
        {
            boolean lhsSimplifiedFill;
            lhsSimplifiedFill = (true?this.isSimplifiedFill():false);
            boolean rhsSimplifiedFill;
            rhsSimplifiedFill = (true?that.isSimplifiedFill():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "simplifiedFill", lhsSimplifiedFill), LocatorUtils.property(thatLocator, "simplifiedFill", rhsSimplifiedFill), lhsSimplifiedFill, rhsSimplifiedFill)) {
                return false;
            }
        }
        {
            PrintColorRestrictionsDTO.PrintableAbovePrintColors lhsPrintableAbovePrintColors;
            lhsPrintableAbovePrintColors = this.getPrintableAbovePrintColors();
            PrintColorRestrictionsDTO.PrintableAbovePrintColors rhsPrintableAbovePrintColors;
            rhsPrintableAbovePrintColors = that.getPrintableAbovePrintColors();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "printableAbovePrintColors", lhsPrintableAbovePrintColors), LocatorUtils.property(thatLocator, "printableAbovePrintColors", rhsPrintableAbovePrintColors), lhsPrintableAbovePrintColors, rhsPrintableAbovePrintColors)) {
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
            boolean theSimplifiedFill;
            theSimplifiedFill = (true?this.isSimplifiedFill():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "simplifiedFill", theSimplifiedFill), currentHashCode, theSimplifiedFill);
        }
        {
            PrintColorRestrictionsDTO.PrintableAbovePrintColors thePrintableAbovePrintColors;
            thePrintableAbovePrintColors = this.getPrintableAbovePrintColors();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "printableAbovePrintColors", thePrintableAbovePrintColors), currentHashCode, thePrintableAbovePrintColors);
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
        if (draftCopy instanceof PrintColorRestrictionsDTO) {
            final PrintColorRestrictionsDTO copy = ((PrintColorRestrictionsDTO) draftCopy);
            boolean sourceSimplifiedFill;
            sourceSimplifiedFill = (true?this.isSimplifiedFill():false);
            boolean copySimplifiedFill = strategy.copy(LocatorUtils.property(locator, "simplifiedFill", sourceSimplifiedFill), sourceSimplifiedFill);
            copy.setSimplifiedFill(copySimplifiedFill);
            if (this.printableAbovePrintColors!= null) {
                PrintColorRestrictionsDTO.PrintableAbovePrintColors sourcePrintableAbovePrintColors;
                sourcePrintableAbovePrintColors = this.getPrintableAbovePrintColors();
                PrintColorRestrictionsDTO.PrintableAbovePrintColors copyPrintableAbovePrintColors = ((PrintColorRestrictionsDTO.PrintableAbovePrintColors) strategy.copy(LocatorUtils.property(locator, "printableAbovePrintColors", sourcePrintableAbovePrintColors), sourcePrintableAbovePrintColors));
                copy.setPrintableAbovePrintColors(copyPrintableAbovePrintColors);
            } else {
                copy.printableAbovePrintColors = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new PrintColorRestrictionsDTO();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof PrintColorRestrictionsDTO) {
            final PrintColorRestrictionsDTO target = this;
            final PrintColorRestrictionsDTO leftObject = ((PrintColorRestrictionsDTO) left);
            final PrintColorRestrictionsDTO rightObject = ((PrintColorRestrictionsDTO) right);
            {
                boolean lhsSimplifiedFill;
                lhsSimplifiedFill = (true?leftObject.isSimplifiedFill():false);
                boolean rhsSimplifiedFill;
                rhsSimplifiedFill = (true?rightObject.isSimplifiedFill():false);
                boolean mergedSimplifiedFill = ((boolean) strategy.merge(LocatorUtils.property(leftLocator, "simplifiedFill", lhsSimplifiedFill), LocatorUtils.property(rightLocator, "simplifiedFill", rhsSimplifiedFill), lhsSimplifiedFill, rhsSimplifiedFill));
                target.setSimplifiedFill(mergedSimplifiedFill);
            }
            {
                PrintColorRestrictionsDTO.PrintableAbovePrintColors lhsPrintableAbovePrintColors;
                lhsPrintableAbovePrintColors = leftObject.getPrintableAbovePrintColors();
                PrintColorRestrictionsDTO.PrintableAbovePrintColors rhsPrintableAbovePrintColors;
                rhsPrintableAbovePrintColors = rightObject.getPrintableAbovePrintColors();
                PrintColorRestrictionsDTO.PrintableAbovePrintColors mergedPrintableAbovePrintColors = ((PrintColorRestrictionsDTO.PrintableAbovePrintColors) strategy.merge(LocatorUtils.property(leftLocator, "printableAbovePrintColors", lhsPrintableAbovePrintColors), LocatorUtils.property(rightLocator, "printableAbovePrintColors", rhsPrintableAbovePrintColors), lhsPrintableAbovePrintColors, rhsPrintableAbovePrintColors));
                target.setPrintableAbovePrintColors(mergedPrintableAbovePrintColors);
            }
        }
    }

    public PrintColorRestrictionsDTO withSimplifiedFill(boolean value) {
        setSimplifiedFill(value);
        return this;
    }

    public PrintColorRestrictionsDTO withPrintableAbovePrintColors(PrintColorRestrictionsDTO.PrintableAbovePrintColors value) {
        setPrintableAbovePrintColors(value);
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
     *         &lt;element name="printableAbovePrintColor" type="{http://api.company.biz}reference" maxOccurs="unbounded" minOccurs="0"/>
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
        "printableAbovePrintColor"
    })
    public static class PrintableAbovePrintColors
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        protected List<Reference> printableAbovePrintColor;

        /**
         * Gets the value of the printableAbovePrintColor property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the printableAbovePrintColor property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPrintableAbovePrintColor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Reference }
         * 
         * 
         */
        public List<Reference> getPrintableAbovePrintColor() {
            if (printableAbovePrintColor == null) {
                printableAbovePrintColor = new ArrayList<Reference>();
            }
            return this.printableAbovePrintColor;
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
                List<Reference> thePrintableAbovePrintColor;
                thePrintableAbovePrintColor = (((this.printableAbovePrintColor!= null)&&(!this.printableAbovePrintColor.isEmpty()))?this.getPrintableAbovePrintColor():null);
                strategy.appendField(locator, this, "printableAbovePrintColor", buffer, thePrintableAbovePrintColor);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof PrintColorRestrictionsDTO.PrintableAbovePrintColors)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final PrintColorRestrictionsDTO.PrintableAbovePrintColors that = ((PrintColorRestrictionsDTO.PrintableAbovePrintColors) object);
            {
                List<Reference> lhsPrintableAbovePrintColor;
                lhsPrintableAbovePrintColor = (((this.printableAbovePrintColor!= null)&&(!this.printableAbovePrintColor.isEmpty()))?this.getPrintableAbovePrintColor():null);
                List<Reference> rhsPrintableAbovePrintColor;
                rhsPrintableAbovePrintColor = (((that.printableAbovePrintColor!= null)&&(!that.printableAbovePrintColor.isEmpty()))?that.getPrintableAbovePrintColor():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "printableAbovePrintColor", lhsPrintableAbovePrintColor), LocatorUtils.property(thatLocator, "printableAbovePrintColor", rhsPrintableAbovePrintColor), lhsPrintableAbovePrintColor, rhsPrintableAbovePrintColor)) {
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
                List<Reference> thePrintableAbovePrintColor;
                thePrintableAbovePrintColor = (((this.printableAbovePrintColor!= null)&&(!this.printableAbovePrintColor.isEmpty()))?this.getPrintableAbovePrintColor():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "printableAbovePrintColor", thePrintableAbovePrintColor), currentHashCode, thePrintableAbovePrintColor);
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
            if (draftCopy instanceof PrintColorRestrictionsDTO.PrintableAbovePrintColors) {
                final PrintColorRestrictionsDTO.PrintableAbovePrintColors copy = ((PrintColorRestrictionsDTO.PrintableAbovePrintColors) draftCopy);
                if ((this.printableAbovePrintColor!= null)&&(!this.printableAbovePrintColor.isEmpty())) {
                    List<Reference> sourcePrintableAbovePrintColor;
                    sourcePrintableAbovePrintColor = (((this.printableAbovePrintColor!= null)&&(!this.printableAbovePrintColor.isEmpty()))?this.getPrintableAbovePrintColor():null);
                    @SuppressWarnings("unchecked")
                    List<Reference> copyPrintableAbovePrintColor = ((List<Reference> ) strategy.copy(LocatorUtils.property(locator, "printableAbovePrintColor", sourcePrintableAbovePrintColor), sourcePrintableAbovePrintColor));
                    copy.printableAbovePrintColor = null;
                    if (copyPrintableAbovePrintColor!= null) {
                        List<Reference> uniquePrintableAbovePrintColorl = copy.getPrintableAbovePrintColor();
                        uniquePrintableAbovePrintColorl.addAll(copyPrintableAbovePrintColor);
                    }
                } else {
                    copy.printableAbovePrintColor = null;
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new PrintColorRestrictionsDTO.PrintableAbovePrintColors();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof PrintColorRestrictionsDTO.PrintableAbovePrintColors) {
                final PrintColorRestrictionsDTO.PrintableAbovePrintColors target = this;
                final PrintColorRestrictionsDTO.PrintableAbovePrintColors leftObject = ((PrintColorRestrictionsDTO.PrintableAbovePrintColors) left);
                final PrintColorRestrictionsDTO.PrintableAbovePrintColors rightObject = ((PrintColorRestrictionsDTO.PrintableAbovePrintColors) right);
                {
                    List<Reference> lhsPrintableAbovePrintColor;
                    lhsPrintableAbovePrintColor = (((leftObject.printableAbovePrintColor!= null)&&(!leftObject.printableAbovePrintColor.isEmpty()))?leftObject.getPrintableAbovePrintColor():null);
                    List<Reference> rhsPrintableAbovePrintColor;
                    rhsPrintableAbovePrintColor = (((rightObject.printableAbovePrintColor!= null)&&(!rightObject.printableAbovePrintColor.isEmpty()))?rightObject.getPrintableAbovePrintColor():null);
                    List<Reference> mergedPrintableAbovePrintColor = ((List<Reference> ) strategy.merge(LocatorUtils.property(leftLocator, "printableAbovePrintColor", lhsPrintableAbovePrintColor), LocatorUtils.property(rightLocator, "printableAbovePrintColor", rhsPrintableAbovePrintColor), lhsPrintableAbovePrintColor, rhsPrintableAbovePrintColor));
                    target.printableAbovePrintColor = null;
                    if (mergedPrintableAbovePrintColor!= null) {
                        List<Reference> uniquePrintableAbovePrintColorl = target.getPrintableAbovePrintColor();
                        uniquePrintableAbovePrintColorl.addAll(mergedPrintableAbovePrintColor);
                    }
                }
            }
        }

        public PrintColorRestrictionsDTO.PrintableAbovePrintColors withPrintableAbovePrintColor(Reference... values) {
            if (values!= null) {
                for (Reference value: values) {
                    getPrintableAbovePrintColor().add(value);
                }
            }
            return this;
        }

        public PrintColorRestrictionsDTO.PrintableAbovePrintColors withPrintableAbovePrintColor(Collection<Reference> values) {
            if (values!= null) {
                getPrintableAbovePrintColor().addAll(values);
            }
            return this;
        }

    }

}
