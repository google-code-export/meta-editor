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
// Generated on: 2010.11.13 at 05:02:55 odp. CET 
//

package cz.fi.muni.xkremser.editor.client.mods;

import com.google.gwt.user.client.rpc.IsSerializable;

// TODO: Auto-generated Javadoc
/**
 * The Class UrlTypeClient.
 */
public class UrlTypeClient
        implements IsSerializable {

    /** The value. */
    protected String value;

    /** The date last accessed. */
    protected String dateLastAccessed;

    /** The display label. */
    protected String displayLabel;

    /** The note. */
    protected String note;

    /** The access. */
    protected String access;

    /** The usage. */
    protected String usage;

    /**
     * Gets the value of the value property.
     * 
     * @return possible object is {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *        allowed object is {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the dateLastAccessed property.
     * 
     * @return possible object is {@link String }
     */
    public String getDateLastAccessed() {
        return dateLastAccessed;
    }

    /**
     * Sets the value of the dateLastAccessed property.
     * 
     * @param value
     *        allowed object is {@link String }
     */
    public void setDateLastAccessed(String value) {
        this.dateLastAccessed = value;
    }

    /**
     * Gets the value of the displayLabel property.
     * 
     * @return possible object is {@link String }
     */
    public String getDisplayLabel() {
        return displayLabel;
    }

    /**
     * Sets the value of the displayLabel property.
     * 
     * @param value
     *        allowed object is {@link String }
     */
    public void setDisplayLabel(String value) {
        this.displayLabel = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return possible object is {@link String }
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *        allowed object is {@link String }
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the access property.
     * 
     * @return possible object is {@link String }
     */
    public String getAccess() {
        return access;
    }

    /**
     * Sets the value of the access property.
     * 
     * @param value
     *        allowed object is {@link String }
     */
    public void setAccess(String value) {
        this.access = value;
    }

    /**
     * Gets the value of the usage property.
     * 
     * @return possible object is {@link String }
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Sets the value of the usage property.
     * 
     * @param value
     *        allowed object is {@link String }
     */
    public void setUsage(String value) {
        this.usage = value;
    }

}
