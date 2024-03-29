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

package cz.fi.muni.xkremser.editor.server.guice;

import com.google.inject.Provider;
import com.google.inject.Singleton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class LogProvider.
 */
@Singleton
public class LogProvider
        implements Provider<Log> {

    /*
     * (non-Javadoc)
     * @see com.google.inject.Provider#get()
     */
    @Override
    public Log get() {
        return new Log4JLogger("MeditorLogger");
    }

}