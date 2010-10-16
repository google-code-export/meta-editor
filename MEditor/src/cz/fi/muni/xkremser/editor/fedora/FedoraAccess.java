package cz.fi.muni.xkremser.editor.fedora;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import javax.xml.xpath.XPathExpressionException;

import org.fedora.api.FedoraAPIA;
import org.fedora.api.FedoraAPIM;
import org.fedora.api.ObjectFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cz.fi.muni.xkremser.editor.client.Constants.KrameriusModel;

/**
 * This is main point to access to fedora through REST-API
 * 
 * @see FedoraAccessImpl
 * @see SecuredFedoraAccessImpl
 * @author pavels
 */
public interface FedoraAccess {

	/**
	 * Returns parsed rels-ext
	 * 
	 * @param uuid
	 *          Object uuid
	 * @return
	 * @throws IOException
	 */
	public Document getRelsExt(String uuid) throws IOException;

	/**
	 * Returns KrameriusModel parsed from given document
	 * 
	 * @param relsExt
	 *          RELS-EXT document
	 * @see KrameriusModel
	 * @return
	 */
	public KrameriusModel getKrameriusModel(Document relsExt);

	/**
	 * Returns KrameriusModel of given object
	 * 
	 * @param uuid
	 *          uuid of object
	 * @return
	 * @throws IOException
	 */
	public KrameriusModel getKrameriusModel(String uuid) throws IOException;

	/**
	 * Recursive processing fedora objects
	 * 
	 * @param uuid
	 *          UUID of top level object
	 * @param handler
	 *          handler fo handling events
	 * @throws IOException
	 */
	public void processRelsExt(String uuid, RelsExtHandler handler) throws IOException;

	/**
	 * Recursive processing fedora objects
	 * 
	 * @param relsExtDocument
	 *          Document of top level object
	 * @param handler
	 *          handler fo handling events
	 * @throws IOException
	 */
	public void processRelsExt(Document relsExtDocument, RelsExtHandler handler) throws IOException;

	/**
	 * Return parsed biblio mods stream
	 * 
	 * @param uuid
	 * @return
	 * @throws IOException
	 */
	public Document getBiblioMods(String uuid) throws IOException;

	/**
	 * Returns DC stream
	 * 
	 * @param uuid
	 * @return
	 * @throws IOException
	 */
	public Document getDC(String uuid) throws IOException;

	/**
	 * Parse, find and returns all pages
	 * 
	 * @param uuid
	 *          UUID of object
	 * @return
	 */
	public List<Element> getPages(String uuid, boolean deep) throws IOException;

	/**
	 * Find and returns all pages
	 * 
	 * @param uuid
	 *          UUID of object
	 * @param rootElementOfRelsExt
	 *          Root element of RelsExt
	 * @return
	 * @throws IOException
	 */
	public List<Element> getPages(String uuid, Element rootElementOfRelsExt) throws IOException;

	/**
	 * Returns input stream of thumbnail
	 * 
	 * @param uuid
	 * @return
	 * @throws IOException
	 */
	public InputStream getThumbnail(String uuid) throws IOException;

	Document getThumbnailProfile(String uuid) throws IOException;

	public String getThumbnailMimeType(String uuid) throws IOException, XPathExpressionException;

	/**
	 * Returns djvu image of the object
	 * 
	 * @param uuid
	 * @return
	 * @throws IOException
	 */
	public InputStream getImageFULL(String uuid) throws IOException;

	/**
	 * REturns profile of full image stream
	 * 
	 * @param uuid
	 * @return
	 * @throws IOException
	 */
	public Document getImageFULLProfile(String uuid) throws IOException;

	/**
	 * Returns full image mime type
	 * 
	 * @param uuid
	 * @return
	 * @throws IOException
	 * @throws XPathExpressionException
	 */
	public String getImageFULLMimeType(String uuid) throws IOException, XPathExpressionException;

	/**
	 * Check whether full image is available, is present and accessible
	 * 
	 * @param uuid
	 * @return
	 * @throws IOException
	 */
	public boolean isImageFULLAvailable(String uuid) throws IOException;

	/**
	 * Checks whetere content is acessiable
	 * 
	 * @param uuid
	 *          uuid of object which can be protected
	 * @return
	 * @throws IOException
	 */
	public boolean isContentAccessible(String uuid) throws IOException;

	/**
	 * Checks whetere content is present
	 * 
	 * @param uuid
	 *          uuid of object which can be protected
	 * @return
	 * @throws IOException
	 */
	public boolean isDigitalObjectPresent(String uuid);

	public FedoraAPIA getAPIA();

	public FedoraAPIM getAPIM();

	public ObjectFactory getObjectFactory();

	public void processSubtree(String pid, TreeNodeProcessor processor);

	public Set<String> getPids(String pid);

	public InputStream getDataStream(String pid, String datastreamName) throws IOException;

	public String getMimeTypeForStream(String pid, String datastreamName) throws IOException;

	public List<String> getIsOnPagesUuid(String uuid) throws IOException;

	public List<String> getPagesUuid(String uuid) throws IOException;

}
