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
 * <p>Java class for fontFamilyDTOList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fontFamilyDTOList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.company.biz}abstractList">
 *       &lt;sequence>
 *         &lt;element ref="{http://api.company.biz}fontFamily" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fontFamilyDTOList", propOrder = {
    "fontFamily"
})
public class FontFamilyDTOList
    extends AbstractList
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<FontFamilyDTO> fontFamily;

    /**
     * Gets the value of the fontFamily property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fontFamily property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFontFamily().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FontFamilyDTO }
     * 
     * 
     */
    public List<FontFamilyDTO> getFontFamily() {
        if (fontFamily == null) {
            fontFamily = new ArrayList<FontFamilyDTO>();
        }
        return this.fontFamily;
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
            List<FontFamilyDTO> theFontFamily;
            theFontFamily = (((this.fontFamily!= null)&&(!this.fontFamily.isEmpty()))?this.getFontFamily():null);
            strategy.appendField(locator, this, "fontFamily", buffer, theFontFamily);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FontFamilyDTOList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final FontFamilyDTOList that = ((FontFamilyDTOList) object);
        {
            List<FontFamilyDTO> lhsFontFamily;
            lhsFontFamily = (((this.fontFamily!= null)&&(!this.fontFamily.isEmpty()))?this.getFontFamily():null);
            List<FontFamilyDTO> rhsFontFamily;
            rhsFontFamily = (((that.fontFamily!= null)&&(!that.fontFamily.isEmpty()))?that.getFontFamily():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fontFamily", lhsFontFamily), LocatorUtils.property(thatLocator, "fontFamily", rhsFontFamily), lhsFontFamily, rhsFontFamily)) {
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
            List<FontFamilyDTO> theFontFamily;
            theFontFamily = (((this.fontFamily!= null)&&(!this.fontFamily.isEmpty()))?this.getFontFamily():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fontFamily", theFontFamily), currentHashCode, theFontFamily);
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
        if (draftCopy instanceof FontFamilyDTOList) {
            final FontFamilyDTOList copy = ((FontFamilyDTOList) draftCopy);
            if ((this.fontFamily!= null)&&(!this.fontFamily.isEmpty())) {
                List<FontFamilyDTO> sourceFontFamily;
                sourceFontFamily = (((this.fontFamily!= null)&&(!this.fontFamily.isEmpty()))?this.getFontFamily():null);
                @SuppressWarnings("unchecked")
                List<FontFamilyDTO> copyFontFamily = ((List<FontFamilyDTO> ) strategy.copy(LocatorUtils.property(locator, "fontFamily", sourceFontFamily), sourceFontFamily));
                copy.fontFamily = null;
                if (copyFontFamily!= null) {
                    List<FontFamilyDTO> uniqueFontFamilyl = copy.getFontFamily();
                    uniqueFontFamilyl.addAll(copyFontFamily);
                }
            } else {
                copy.fontFamily = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new FontFamilyDTOList();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof FontFamilyDTOList) {
            final FontFamilyDTOList target = this;
            final FontFamilyDTOList leftObject = ((FontFamilyDTOList) left);
            final FontFamilyDTOList rightObject = ((FontFamilyDTOList) right);
            {
                List<FontFamilyDTO> lhsFontFamily;
                lhsFontFamily = (((leftObject.fontFamily!= null)&&(!leftObject.fontFamily.isEmpty()))?leftObject.getFontFamily():null);
                List<FontFamilyDTO> rhsFontFamily;
                rhsFontFamily = (((rightObject.fontFamily!= null)&&(!rightObject.fontFamily.isEmpty()))?rightObject.getFontFamily():null);
                List<FontFamilyDTO> mergedFontFamily = ((List<FontFamilyDTO> ) strategy.merge(LocatorUtils.property(leftLocator, "fontFamily", lhsFontFamily), LocatorUtils.property(rightLocator, "fontFamily", rhsFontFamily), lhsFontFamily, rhsFontFamily));
                target.fontFamily = null;
                if (mergedFontFamily!= null) {
                    List<FontFamilyDTO> uniqueFontFamilyl = target.getFontFamily();
                    uniqueFontFamilyl.addAll(mergedFontFamily);
                }
            }
        }
    }

    public FontFamilyDTOList withFontFamily(FontFamilyDTO... values) {
        if (values!= null) {
            for (FontFamilyDTO value: values) {
                getFontFamily().add(value);
            }
        }
        return this;
    }

    public FontFamilyDTOList withFontFamily(Collection<FontFamilyDTO> values) {
        if (values!= null) {
            getFontFamily().addAll(values);
        }
        return this;
    }

    @Override
    public FontFamilyDTOList withFacets(AbstractList.Facets value) {
        setFacets(value);
        return this;
    }

    @Override
    public FontFamilyDTOList withHref(String value) {
        setHref(value);
        return this;
    }

    @Override
    public FontFamilyDTOList withOffset(long value) {
        setOffset(value);
        return this;
    }

    @Override
    public FontFamilyDTOList withLimit(long value) {
        setLimit(value);
        return this;
    }

    @Override
    public FontFamilyDTOList withCount(long value) {
        setCount(value);
        return this;
    }

    @Override
    public FontFamilyDTOList withSortField(String value) {
        setSortField(value);
        return this;
    }

    @Override
    public FontFamilyDTOList withSortOrder(String value) {
        setSortOrder(value);
        return this;
    }

    @Override
    public FontFamilyDTOList withQuery(String value) {
        setQuery(value);
        return this;
    }

    @Override
    public FontFamilyDTOList withSuggestedQuery(String value) {
        setSuggestedQuery(value);
        return this;
    }

    @Override
    public FontFamilyDTOList withExecutedQuery(String value) {
        setExecutedQuery(value);
        return this;
    }

    @Override
    public FontFamilyDTOList withUngroupedCount(Long value) {
        setUngroupedCount(value);
        return this;
    }

}
