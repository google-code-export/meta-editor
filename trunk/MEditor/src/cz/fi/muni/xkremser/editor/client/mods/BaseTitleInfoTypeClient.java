//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.13 at 05:02:55 odp. CET 
//

package cz.fi.muni.xkremser.editor.client.mods;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BaseTitleInfoTypeClient implements IsSerializable {
	protected List<String> title;
	protected List<String> subTitle;
	protected List<String> partNumber;
	protected List<String> partName;
	protected List<String> nonSort;
	protected String displayLabel;
	protected String id;
	protected String authority;
	protected String xlink;
	protected String xmlLang;
	protected String lang;
	protected String script;
	protected String transliteration;

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public List<String> getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(List<String> subTitle) {
		this.subTitle = subTitle;
	}

	public List<String> getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(List<String> partNumber) {
		this.partNumber = partNumber;
	}

	public List<String> getPartName() {
		return partName;
	}

	public void setPartName(List<String> partName) {
		this.partName = partName;
	}

	public List<String> getNonSort() {
		return nonSort;
	}

	public void setNonSort(List<String> nonSort) {
		this.nonSort = nonSort;
	}

	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getXlink() {
		return xlink;
	}

	public void setXlink(String xlink) {
		this.xlink = xlink;
	}

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

}
