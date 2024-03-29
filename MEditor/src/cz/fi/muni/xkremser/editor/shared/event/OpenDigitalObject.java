/*
 * Metadata Editor
 * @author Matous Jobanek
 * 
 * 
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Matous Jobanek (matous.jobanek@mzk.cz)
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

package cz.fi.muni.xkremser.editor.shared.event;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

import cz.fi.muni.xkremser.editor.shared.rpc.StoredItem;

// TODO: Auto-generated Javadoc
/**
 * The Class OpenDigitalObject.
 */
@GenEvent
@SuppressWarnings("unused")
public class OpenDigitalObject {

    /** The uuid of the object which is going to be opened. */
    @Order(1)
    private String uuid;

    /**
     * The info about the stored working copy of a digital object which is going
     * to be opened; <code>storedItem == null </code> when the object being
     * opened is not stored.
     */
    private StoredItem storedItem;

}