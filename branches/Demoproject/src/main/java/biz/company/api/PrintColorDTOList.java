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
 * <p>Java class for printColorDTOList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="printColorDTOList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.company.biz}abstractList">
 *       &lt;sequence>
 *         &lt;element ref="{http://api.company.biz}printColor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "printColorDTOList", propOrder = {
    "printColor"
})
public class PrintColorDTOList
    extends AbstractList
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<PrintColorDTO> printColor;

    /**
     * Gets the value of the printColor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the printColor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrintColor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrintColorDTO }
     * 
     * 
     */
    public List<PrintColorDTO> getPrintColor() {
        if (printColor == null) {
            printColor = new ArrayList<PrintColorDTO>();
        }
        return this.printColor;
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
            List<PrintColorDTO> thePrintColor;
            thePrintColor = (((this.printColor!= null)&&(!this.printColor.isEmpty()))?this.getPrintColor():null);
            strategy.appendField(locator, this, "printColor", buffer, thePrintColor);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PrintColorDTOList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final PrintColorDTOList that = ((PrintColorDTOList) object);
        {
            List<PrintColorDTO> lhsPrintColor;
            lhsPrintColor = (((this.printColor!= null)&&(!this.printColor.isEmpty()))?this.getPrintColor():null);
            List<PrintColorDTO> rhsPrintColor;
            rhsPrintColor = (((that.printColor!= null)&&(!that.printColor.isEmpty()))?that.getPrintColor():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "printColor", lhsPrintColor), LocatorUtils.property(thatLocator, "printColor", rhsPrintColor), lhsPrintColor, rhsPrintColor)) {
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
            List<PrintColorDTO> thePrintColor;
            thePrintColor = (((this.printColor!= null)&&(!this.printColor.isEmpty()))?this.getPrintColor():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "printColor", thePrintColor), currentHashCode, thePrintColor);
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
        if (draftCopy instanceof PrintColorDTOList) {
            final PrintColorDTOList copy = ((PrintColorDTOList) draftCopy);
            if ((this.printColor!= null)&&(!this.printColor.isEmpty())) {
                List<PrintColorDTO> sourcePrintColor;
                sourcePrintColor = (((this.printColor!= null)&&(!this.printColor.isEmpty()))?this.getPrintColor():null);
                @SuppressWarnings("unchecked")
                List<PrintColorDTO> copyPrintColor = ((List<PrintColorDTO> ) strategy.copy(LocatorUtils.property(locator, "printColor", sourcePrintColor), sourcePrintColor));
                copy.printColor = null;
                if (copyPrintColor!= null) {
                    List<PrintColorDTO> uniquePrintColorl = copy.getPrintColor();
                    uniquePrintColorl.addAll(copyPrintColor);
                }
            } else {
                copy.printColor = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new PrintColorDTOList();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof PrintColorDTOList) {
            final PrintColorDTOList target = this;
            final PrintColorDTOList leftObject = ((PrintColorDTOList) left);
            final PrintColorDTOList rightObject = ((PrintColorDTOList) right);
            {
                List<PrintColorDTO> lhsPrintColor;
                lhsPrintColor = (((leftObject.printColor!= null)&&(!leftObject.printColor.isEmpty()))?leftObject.getPrintColor():null);
                List<PrintColorDTO> rhsPrintColor;
                rhsPrintColor = (((rightObject.printColor!= null)&&(!rightObject.printColor.isEmpty()))?rightObject.getPrintColor():null);
                List<PrintColorDTO> mergedPrintColor = ((List<PrintColorDTO> ) strategy.merge(LocatorUtils.property(leftLocator, "printColor", lhsPrintColor), LocatorUtils.property(rightLocator, "printColor", rhsPrintColor), lhsPrintColor, rhsPrintColor));
                target.printColor = null;
                if (mergedPrintColor!= null) {
                    List<PrintColorDTO> uniquePrintColorl = target.getPrintColor();
                    uniquePrintColorl.addAll(mergedPrintColor);
                }
            }
        }
    }

    public PrintColorDTOList withPrintColor(PrintColorDTO... values) {
        if (values!= null) {
            for (PrintColorDTO value: values) {
                getPrintColor().add(value);
            }
        }
        return this;
    }

    public PrintColorDTOList withPrintColor(Collection<PrintColorDTO> values) {
        if (values!= null) {
            getPrintColor().addAll(values);
        }
        return this;
    }

    @Override
    public PrintColorDTOList withFacets(AbstractList.Facets value) {
        setFacets(value);
        return this;
    }

    @Override
    public PrintColorDTOList withHref(String value) {
        setHref(value);
        return this;
    }

    @Override
    public PrintColorDTOList withOffset(long value) {
        setOffset(value);
        return this;
    }

    @Override
    public PrintColorDTOList withLimit(long value) {
        setLimit(value);
        return this;
    }

    @Override
    public PrintColorDTOList withCount(long value) {
        setCount(value);
        return this;
    }

    @Override
    public PrintColorDTOList withSortField(String value) {
        setSortField(value);
        return this;
    }

    @Override
    public PrintColorDTOList withSortOrder(String value) {
        setSortOrder(value);
        return this;
    }

    @Override
    public PrintColorDTOList withQuery(String value) {
        setQuery(value);
        return this;
    }

    @Override
    public PrintColorDTOList withSuggestedQuery(String value) {
        setSuggestedQuery(value);
        return this;
    }

    @Override
    public PrintColorDTOList withExecutedQuery(String value) {
        setExecutedQuery(value);
        return this;
    }

    @Override
    public PrintColorDTOList withUngroupedCount(Long value) {
        setUngroupedCount(value);
        return this;
    }

}
