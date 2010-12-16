/**
 * Metadata Editor
 * @author Jiri Kremser
 *  
 */
package cz.fi.muni.xkremser.editor.server.DAO;

import java.util.ArrayList;

import cz.fi.muni.xkremser.editor.shared.rpc.RecentlyModifiedItem;

// TODO: Auto-generated Javadoc
/**
 * The Interface RecentlyModifiedItemDAO.
 */
public interface RecentlyModifiedItemDAO {

	/**
	 * Put.
	 * 
	 * @param toPut
	 *          the to put
	 * @return true, if successful
	 */
	boolean put(RecentlyModifiedItem toPut, String openID);

	boolean putDescription(String uuid, String description);

	String getDescription(String uuid);

	boolean putUserDescription(String openID, String uuid, String description);

	String getUserDescription(String openID, String uuid);

	/**
	 * Gets the items.
	 * 
	 * @param nLatest
	 *          the n latest
	 * @param isForAll
	 *          the is for all
	 * @return the items
	 */
	ArrayList<RecentlyModifiedItem> getItems(int nLatest, String openID);
}
