//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.19 at 07:09:03 PM CEST 
//


package biz.company.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MY_DESIGNS"/>
 *     &lt;enumeration value="SHOP_DEFAULT"/>
 *     &lt;enumeration value="UPLOADED"/>
 *     &lt;enumeration value="GRABBED"/>
 *     &lt;enumeration value="HISTORY"/>
 *     &lt;enumeration value="MARKETPLACE"/>
 *     &lt;enumeration value="SHOP"/>
 *     &lt;enumeration value="BESTSELLER"/>
 *     &lt;enumeration value="OWN"/>
 *     &lt;enumeration value="SHOP_CUSTOM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "type")
@XmlEnum
public enum Type {

    MY_DESIGNS,
    SHOP_DEFAULT,
    UPLOADED,
    GRABBED,
    HISTORY,
    MARKETPLACE,
    SHOP,
    BESTSELLER,
    OWN,
    SHOP_CUSTOM;

    public String value() {
        return name();
    }

    public static Type fromValue(String v) {
        return valueOf(v);
    }

}
