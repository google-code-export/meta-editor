/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.xkremser.editor.fedora.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class KConfiguration {

	public static final String DEFAULT_CONF_LOCATION = "res/configuration.properties";

	public static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(KConfiguration.class.getName());
	public static final String CONFIGURATION = File.separator + "configuration.properties";

	private static KConfiguration _sharedInstance = null;

	private Configuration allConfigurations;

	KConfiguration() {
		try {
			allConfigurations = findAllConfigurations();
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.severe("Can't load configuration");
			throw new RuntimeException(ex.toString());
		}
	}

	private Configuration findAllConfigurations() throws IOException {
		CompositeConfiguration allConfiguration = new CompositeConfiguration();
		try {
			Enumeration<URL> resources = this.getClass().getClassLoader().getResources("res/configuration.properties");
			while (resources.hasMoreElements()) {
				URL nextElement = resources.nextElement();
				PropertiesConfiguration bundled = new PropertiesConfiguration(nextElement);
				String name = disectName(nextElement.getFile());
				if ((name != null) || (bundled.containsKey("_ext_configuration_file_name"))) {
					String moduleName = name;
					if (bundled.containsKey("_ext_configuration_file_name")) {
						LOGGER.info("Replacing configuration file name from '" + name + "' to '" + bundled.getString("_ext_configuration_file_name") + "'");
						name = bundled.getString("_ext_configuration_file_name");
					}
					String path = File.separator + (name.toLowerCase().endsWith("properties") ? name : name + ".properties");
					File confFile = new File(path);
					if (!confFile.exists()) {
						boolean createdFile = confFile.createNewFile();
						if (!createdFile)
							throw new RuntimeException("cannot crete conf file '" + confFile.getAbsolutePath() + "'");
						FileOutputStream confFos = new FileOutputStream(confFile);
						try {
							new Properties().store(confFos, "configuration file for module '" + moduleName + "'");
						} finally {
							confFos.close();
						}
					}
					// _ext_configuration_file_name
					CompositeConfiguration constconf = new CompositeConfiguration();
					PropertiesConfiguration file = new PropertiesConfiguration(confFile);
					file.setReloadingStrategy(new FileChangedReloadingStrategy());
					constconf.addConfiguration(file);
					constconf.addConfiguration(bundled);
					allConfiguration.addConfiguration(constconf);
				} else {
					LOGGER.severe("ommiting '" + nextElement.getFile() + "'");
				}
			}
			return allConfiguration;
		} catch (ConfigurationException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}

	}

	public String disectName(String path) {
		if (path.contains("!")) {
			String subString = path.substring(0, path.indexOf('!'));
			String[] pathElms = subString.split("/");
			if (pathElms.length > 0) {
				String fileName = pathElms[pathElms.length - 1];
				if (fileName.toLowerCase().endsWith(".jar")) {
					return fileName.substring(0, fileName.length() - ".jar".length());
				} else {
					return fileName;
				}
			} else
				throw new IllegalArgumentException("cannot disect name from '" + path + "'");
		} else {
			// ??
			return null;
		}
	}

	public String getFedoraHost() {
		return getProperty("fedoraHost");
	}

	public String getSolrHost() {
		return getProperty("solrHost");
	}

	public String getIndexerHost() {
		return getProperty("indexerHost");
	}

	public String getFedoraUser() {
		return getProperty("fedoraUser");
	}

	public String getFedoraPass() {
		return getProperty("fedoraPass");
	}

	public String getThumbServletUrl() {
		return getProperty("thumbUrl");
	}

	public String getScaledHeight() {
		return getProperty("scaledHeight");
	}

	public String getJdbcUrl() {
		return getProperty("jdbcUrl");
	}

	public String getJdbcUserName() {
		return getProperty("jdbcUserName");
	}

	public String getJdbcUserPass() {
		return getProperty("jdbcUserPass");
	}

	public String getProperty(String key) {
		return allConfigurations.getString(key);
	}

	public String[] getPropertyList(String key) {
		return allConfigurations.getStringArray(key);
	}

	public String getProperty(String key, String defaultValue) {
		return allConfigurations.getString(key, defaultValue);
	}

	public Configuration getConfiguration() {
		return this.allConfigurations;
	}

	public synchronized static KConfiguration getInstance() {
		if (_sharedInstance == null) {
			_sharedInstance = new KConfiguration();
		}
		return _sharedInstance;
	}

	public String getLongRunningProcessDefiniton() {
		return getProperty("longRunningProcessDefinition");
	}

	public String getLRServletURL() {
		return getProperty("lrControlUrl");
	}

	public String getApplicationURL() {
		String applicationUrl = getProperty("applicationUrl");
		if (applicationUrl != null) {
			applicationUrl = applicationUrl + (applicationUrl.endsWith("/") ? "" : "/");
		}
		return applicationUrl;
	}

	public List<String> getPatterns() {
		List<String> retval = new ArrayList<String>();
		String property = getProperty("accessPatterns");
		if (property != null) {
			StringTokenizer tokenizer = new StringTokenizer(property, "||");
			while (tokenizer.hasMoreTokens()) {
				retval.add(tokenizer.nextToken());
			}
		}
		return retval;
	}

	public List<String> getLRControllingAddresses() {
		List<String> retval = new ArrayList<String>();
		String property = getProperty("controllingPatterns", "localhost||127.*");
		if (property != null) {
			StringTokenizer tokenizer = new StringTokenizer(property, "||");
			while (tokenizer.hasMoreTokens()) {
				retval.add(tokenizer.nextToken());
			}
		}
		return retval;

	}

	public int getDeepZoomTileSize() {
		return getConfiguration().getInt("deepZoom.tileSize", 512);
	}

	public String getDeepZoomCacheDir() {
		return getConfiguration().getString("deepZoom.cachedir", "${sys:user.home}/.kramerius4/deepZoom");
	}

	public float getDeepZoomJPEGQuality() {
		return getConfiguration().getFloat("deepZoom.jpegQuality", 0.9f);

	}

	public String getUrlOfIIPServer() {
		return getConfiguration().getString("UrlOfIIPserver", "");
	}

	public String getFedoraDataFolderInIIPServer() {
		return getConfiguration().getString("fedoraDataFolderOnIIPServer", "");
	}

	public static void main(String[] args) throws IOException {
		KConfiguration kconf = KConfiguration.getInstance();
		Configuration conf = kconf.findAllConfigurations();
		System.out.println(conf);
		System.out.println(conf.getString("exportConf"));

		System.out.println(conf.getString("_fedoraTomcatHost"));
		System.out.println(conf.getString("indexerHost"));

	}
}
