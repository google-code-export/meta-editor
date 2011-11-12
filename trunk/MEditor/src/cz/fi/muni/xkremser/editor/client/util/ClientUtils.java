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

package cz.fi.muni.xkremser.editor.client.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.regexp.shared.SplitResult;
import com.google.gwt.user.client.DOM;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

import cz.fi.muni.xkremser.editor.client.mods.ModsCollectionClient;
import cz.fi.muni.xkremser.editor.client.mods.StringPlusAuthorityClient;
import cz.fi.muni.xkremser.editor.client.mods.StringPlusAuthorityPlusTypeClient;
import cz.fi.muni.xkremser.editor.client.view.other.RecentlyModifiedRecord;

import cz.fi.muni.xkremser.editor.shared.rpc.DublinCore;
import cz.fi.muni.xkremser.editor.shared.rpc.NewDigitalObject;
import cz.fi.muni.xkremser.editor.shared.rpc.RecentlyModifiedItem;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerUtils.
 */
public class ClientUtils {

    /**
     * <p>
     * Converts a String to a boolean (optimised for performance).
     * </p>
     * <p>
     * <code>'true'</code>, <code>'on'</code> or <code>'yes'</code> (case
     * insensitive) will return <code>true</code>. Otherwise, <code>false</code>
     * is returned.
     * </p>
     * <p>
     * This method performs 4 times faster (JDK1.4) than
     * <code>Boolean.valueOf(String)</code>. However, this method accepts 'on'
     * and 'yes' as true values.
     * 
     * <pre>
	 *   BooleanUtils.toBoolean(null)    = false
	 *   BooleanUtils.toBoolean("true")  = true
	 *   BooleanUtils.toBoolean("TRUE")  = true
	 *   BooleanUtils.toBoolean("tRUe")  = true
	 *   BooleanUtils.toBoolean("on")    = true
	 *   BooleanUtils.toBoolean("yes")   = true
	 *   BooleanUtils.toBoolean("false") = false
	 *   BooleanUtils.toBoolean("x gti") = false
	 * </pre>
     * 
     * @param str
     *        the String to check
     * @return the boolean value of the string, <code>false</code> if no match
     */
    public static boolean toBoolean(String str) {
        // Previously used equalsIgnoreCase, which was fast for interned 'true'.
        // Non interned 'true' matched 15 times slower.
        //
        // Optimisation provides same performance as before for interned 'true'.
        // Similar performance for null, 'false', and other strings not length
        // 2/3/4.
        // 'true'/'TRUE' match 4 times slower, 'tRUE'/'True' 7 times slower.
        if (str == "true") {
            return true;
        }
        if (str == null) {
            return false;
        }
        switch (str.length()) {
            case 2: {
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                return (ch0 == 'o' || ch0 == 'O') && (ch1 == 'n' || ch1 == 'N');
            }
            case 3: {
                char ch = str.charAt(0);
                if (ch == 'y') {
                    return (str.charAt(1) == 'e' || str.charAt(1) == 'E')
                            && (str.charAt(2) == 's' || str.charAt(2) == 'S');
                }
                if (ch == 'Y') {
                    return (str.charAt(1) == 'E' || str.charAt(1) == 'e')
                            && (str.charAt(2) == 'S' || str.charAt(2) == 's');
                }
                return false;
            }
            case 4: {
                char ch = str.charAt(0);
                if (ch == 't') {
                    return (str.charAt(1) == 'r' || str.charAt(1) == 'R')
                            && (str.charAt(2) == 'u' || str.charAt(2) == 'U')
                            && (str.charAt(3) == 'e' || str.charAt(3) == 'E');
                }
                if (ch == 'T') {
                    return (str.charAt(1) == 'R' || str.charAt(1) == 'r')
                            && (str.charAt(2) == 'U' || str.charAt(2) == 'u')
                            && (str.charAt(3) == 'E' || str.charAt(3) == 'e');
                }
            }
        }
        return false;
    }

    /**
     * To record.
     * 
     * @param item
     *        the item
     * @return the recently modified record
     */
    public static RecentlyModifiedRecord toRecord(RecentlyModifiedItem item) {
        return new RecentlyModifiedRecord(item.getUuid(),
                                          item.getName(),
                                          item.getDescription(),
                                          item.getModel());
    }

    /**
     * Escape html.
     * 
     * @param maybeHtml
     *        the maybe html
     * @return the string
     */
    public static String escapeHtml(String maybeHtml) {
        final com.google.gwt.user.client.Element div = DOM.createDiv();
        DOM.setInnerText(div, maybeHtml);
        return DOM.getInnerHTML(div);
    }

    /**
     * To list of list of strings.
     * 
     * @param frequency
     *        the frequency
     * @return the list
     */
    public static List<List<String>> toListOfListOfStrings(List<StringPlusAuthorityClient> frequency) {
        if (frequency == null) return null;
        List<List<String>> outerList = new ArrayList<List<String>>(frequency.size());
        for (StringPlusAuthorityClient value : frequency) {
            if (value == null) continue;
            List<String> innerList = new ArrayList<String>(2);
            innerList.add(value.getValue());
            innerList.add(value.getAuthority());
            outerList.add(innerList);
        }
        return outerList;
    }

    /**
     * To list of list of strings.
     * 
     * @param toConvert
     *        the to convert
     * @param something
     *        the something
     * @return the list
     */
    public static List<List<String>> toListOfListOfStrings(List<StringPlusAuthorityPlusTypeClient> toConvert,
                                                           boolean something) {
        if (toConvert == null) return null;
        List<List<String>> outerList = new ArrayList<List<String>>(toConvert.size());
        for (StringPlusAuthorityPlusTypeClient value : toConvert) {
            if (value == null) continue;
            List<String> innerList = new ArrayList<String>(2);
            innerList.add(value.getValue());
            innerList.add(value.getType());
            innerList.add(value.getAuthority());
            outerList.add(innerList);
        }
        return outerList;
    }

    /**
     * Subtract.
     * 
     * @param whole
     *        the whole
     * @param part
     *        the part
     * @return the list grid record[]
     */
    public static ListGridRecord[] subtract(ListGridRecord[] whole, ListGridRecord[] part) {
        if (whole == null || whole.length == 0) return null;
        if (part == null || part.length == 0) return whole;
        List<ListGridRecord> list = new ArrayList<ListGridRecord>();
        for (ListGridRecord record : whole) {
            boolean add = true;
            for (ListGridRecord counterpart : part) {
                if (record == counterpart) {
                    add = false;
                }
            }
            if (add) list.add(record);
        }
        return list.toArray(new ListGridRecord[] {});
    }

    public static NewDigitalObject createTheStructure(DublinCore dc, ModsCollectionClient mods, ListGrid tree) {
        return null;
        // iterate the tree from bottom to top and add DO to fedora
    }

    public static String numbersParse(int number) {
        if (number < 10) {
            return smallNumbersParse(number);
        } else if (number < 50) {
            if (number == 49) {
                return "IL";
            } else {
                return getXPart(number);
            }
        } else {
            return "L" + numbersParse(number - 50);
        }
    }

    private static String getXPart(int number) {
        StringBuffer sb = new StringBuffer();
        int countOfTen = number / 10;
        for (int i = 0; i < countOfTen; i++) {
            sb.append("X");
        }
        sb.append(smallNumbersParse(number % 10));
        return sb.toString();

    }

    private static String smallNumbersParse(int number) {
        if (number < 4) {
            return getIPart(number);

        } else if (number == 4) {
            return "IV";
        } else if (number < 9) {
            return ("V" + getIPart(number - 5));
        } else if (number == 9) {
            return "IX";
        }
        return "";
    }

    private static String getIPart(int count) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            sb.append("I");
        }
        return sb.toString();
    }

    /**
     * String.format is not accessible on the gwt client-side
     * 
     * @param format
     * @param args
     * @return formatted string
     */
    public static String format(final String format, final Object... args) {
        final RegExp regex = RegExp.compile("%[a-z]");
        final SplitResult split = regex.split(format);
        final StringBuffer msg = new StringBuffer();
        for (int pos = 0; pos < split.length() - 1; pos += 1) {
            msg.append(split.get(pos));
            msg.append(args[pos].toString());
        }
        msg.append(split.get(split.length() - 1));
        return msg.toString();
    }
}