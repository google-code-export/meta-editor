//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.13 at 05:02:55 odp. CET 
//

package cz.fi.muni.xkremser.editor.client.mods;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UnstructuredTextClient extends StringPlusDisplayLabelPlusTypeClient implements IsSerializable {

	protected String xmlLang;
	protected String lang;
	protected String script;
	protected String transliteration;
	protected String xlink;

	public String getXmlLang() {
		return xmlLang;
	}

	public void setXmlLang(String xmlLang) {
		this.xmlLang = xmlLang;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getTransliteration() {
		return transliteration;
	}

	public void setTransliteration(String transliteration) {
		this.transliteration = transliteration;
	}

	public String getXlink() {
		return xlink;
	}

	public void setXlink(String xlink) {
		this.xlink = xlink;
	}
}
