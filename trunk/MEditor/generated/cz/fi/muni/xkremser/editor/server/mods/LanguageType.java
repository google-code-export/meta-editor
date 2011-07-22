/*
 * Metadata Editor
 * @author Jiri Kremser
 * 
 * 
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Jiri Kremser (kremser@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.14 at 07:28:52 PM CET 
//


package cz.fi.muni.xkremser.editor.server.mods;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


// TODO: Auto-generated Javadoc
/**
 *  008/35-37, 041
 * 
 * <p>Java class for languageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="languageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="languageTerm" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="authority">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="rfc3066"/>
 *                       &lt;enumeration value="iso639-2b"/>
 *                       &lt;enumeration value="iso639-3"/>
 *                       &lt;enumeration value="rfc4646"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="type" type="{http://www.loc.gov/mods/v3}codeOrText" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="objectPart" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "languageType", propOrder = {
    "languageTerm"
})
public class LanguageType {

    /** The language term. */
    @XmlElement(required = true)
    protected List<LanguageType.LanguageTerm> languageTerm;
    
    /** The object part. */
    @XmlAttribute
    protected String objectPart;

    /**
     * Gets the value of the languageTerm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the languageTerm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getLanguageTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     *
     * @return the language term
     * {@link LanguageType.LanguageTerm }
     */
    public List<LanguageType.LanguageTerm> getLanguageTerm() {
        if (languageTerm == null) {
            languageTerm = new ArrayList<LanguageType.LanguageTerm>();
        }
        return this.languageTerm;
    }

    /**
     * Gets the value of the objectPart property.
     *
     * @return the object part
     * possible object is
     * {@link String }
     */
    public String getObjectPart() {
        return objectPart;
    }

    /**
     * Sets the value of the objectPart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectPart(String value) {
        this.objectPart = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="authority">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;enumeration value="rfc3066"/>
     *             &lt;enumeration value="iso639-2b"/>
     *             &lt;enumeration value="iso639-3"/>
     *             &lt;enumeration value="rfc4646"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="type" type="{http://www.loc.gov/mods/v3}codeOrText" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class LanguageTerm {

        /** The value. */
        @XmlValue
        protected String value;
        
        /** The authority. */
        @XmlAttribute
        protected String authority;
        
        /** The type. */
        @XmlAttribute
        protected CodeOrText type;

        /**
         * Gets the value of the value property.
         *
         * @return the value
         * possible object is
         * {@link String }
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the authority property.
         *
         * @return the authority
         * possible object is
         * {@link String }
         */
        public String getAuthority() {
            return authority;
        }

        /**
         * Sets the value of the authority property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthority(String value) {
            this.authority = value;
        }

        /**
         * Gets the value of the type property.
         *
         * @return the type
         * possible object is
         * {@link CodeOrText }
         */
        public CodeOrText getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link CodeOrText }
         *     
         */
        public void setType(CodeOrText value) {
            this.type = value;
        }

    }

}
