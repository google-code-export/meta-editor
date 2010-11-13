/**
 * Metadata Editor
 * @author Jiri Kremser
 *  
 */
package cz.fi.muni.xkremser.editor.client;

// TODO: Auto-generated Javadoc
/**
 * The Class Constants.
 */
public class Constants {

	/** The Constant SERVLET_IMAGES_PREFIX. */
	private static final String SERVLET_IMAGES_PREFIX = "images/";
	// must be the same as in web.xml
	/** The Constant SERVLET_THUMBNAIL_PREFIX. */
	public static final String SERVLET_THUMBNAIL_PREFIX = "thumbnail";

	/** The Constant SERVLET_FULL_PREFIX. */
	public static final String SERVLET_FULL_PREFIX = "full";

	/** The Constant URL_PARAM_UUID. */
	public static final String URL_PARAM_UUID = "uuid";

	/** The Constant URL_PARAM_ACTION. */
	public static final String URL_PARAM_ACTION = "action";

	/** The Constant URL_PARAM_ACTION_MODIFY. */
	public static final String URL_PARAM_ACTION_MODIFY = "modify";

	// db
	/** The Constant TABLE_INPUT_QUEUE_NAME. */
	public static final String TABLE_INPUT_QUEUE_NAME = "input_queue_item";

	/** The Constant TABLE_RECENTLY_MODIFIED_NAME. */
	public static final String TABLE_RECENTLY_MODIFIED_NAME = "recently_modified_item";

	// input queue tree
	/** The Constant ATTR_ID. */
	public static final String ATTR_ID = "path"; // path

	/** The Constant ATTR_PARENT. */
	public static final String ATTR_PARENT = "parent";

	/** The Constant ATTR_NAME. */
	public static final String ATTR_NAME = "atName";

	/** The Constant ATTR_PICTURE. */
	public static final String ATTR_PICTURE = "picture";

	/** The Constant ATTR_ISSN. */
	public static final String ATTR_ISSN = "issn";

	/** The Constant DIR_MAX_DEPTH. */
	public static final int DIR_MAX_DEPTH = 5;

	public static final int CLIPBOARD_MAX_WITHOUT_PROGRESSBAR = 20;

	/** The Constant FILE_SEPARATOR. */
	public static final String FILE_SEPARATOR = "/";

	// recently modified tree
	/** The Constant ATTR_UUID. */
	public static final String ATTR_UUID = "uuid";

	public static final String ATTR_FOO = "foo";

	/** The Constant ATTR_DESC. */
	public static final String ATTR_DESC = "description";

	/** The Constant ATTR_MODEL. */
	public static final String ATTR_MODEL = "model";

	/** The Constant ATTR_ALL. */
	public static final String ATTR_ALL = "all";

	// fedora
	/** The Constant FEDORA_MODEL_PREFIX. */
	public static final String FEDORA_MODEL_PREFIX = "model:";

	/** The Constant FEDORA_UUID_PREFIX. */
	public static final String FEDORA_UUID_PREFIX = "uuid:";

	public static final String FEDORA_INFO_PREFIX = "info:fedora/";

	public static final String MONOGRAPH_UNIT_ICON = "icons/128/monograph_unit.png";

	public static final String VOLUME_ICON = "icons/128/volume.png";

	public static final String INTERNAL_PART_ICON = "icons/128/internal_part.png";

	public static final String PERIODICAL_ITEM_ICON = "icons/128/periodical_item.png";
}