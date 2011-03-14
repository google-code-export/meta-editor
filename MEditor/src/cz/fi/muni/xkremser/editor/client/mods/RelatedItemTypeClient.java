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
 * The Class RelatedItemTypeClient.
 */
public class RelatedItemTypeClient implements IsSerializable {
	
	/** The mods. */
	protected ModsTypeClient mods;
	
	/** The display label. */
	protected String displayLabel;
	
	/** The id. */
	protected String id;
	
	/** The type. */
	protected String type;
	
	/** The xlink. */
	protected String xlink;

	/**
	 * Gets the mods.
	 *
	 * @return the mods
	 */
	public ModsTypeClient getMods() {
		return mods;
	}

	/**
	 * Sets the mods.
	 *
	 * @param mods the new mods
	 */
	public void setMods(ModsTypeClient mods) {
		this.mods = mods;
	}

	/**
	 * Gets the display label.
	 *
	 * @return the display label
	 */
	public String getDisplayLabel() {
		return displayLabel;
	}

	/**
	 * Sets the display label.
	 *
	 * @param displayLabel the new display label
	 */
	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the xlink.
	 *
	 * @return the xlink
	 */
	public String getXlink() {
		return xlink;
	}

	/**
	 * Sets the xlink.
	 *
	 * @param xlink the new xlink
	 */
	public void setXlink(String xlink) {
		this.xlink = xlink;
	}

}