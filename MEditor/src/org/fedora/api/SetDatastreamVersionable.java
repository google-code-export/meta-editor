/**
 * Metadata Editor
 * @author Jiri Kremser
 *  
 */

package org.fedora.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


// TODO: Auto-generated Javadoc
/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dsID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="versionable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="logMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pid",
    "dsID",
    "versionable",
    "logMessage"
})
@XmlRootElement(name = "setDatastreamVersionable")
public class SetDatastreamVersionable {

    /** The pid. */
    @XmlElement(required = true)
    protected String pid;
    
    /** The ds id. */
    @XmlElement(required = true)
    protected String dsID;
    
    /** The versionable. */
    protected boolean versionable;
    
    /** The log message. */
    @XmlElement(required = true)
    protected String logMessage;

    /**
     * Gets the value of the pid property.
     *
     * @return the pid
     * possible object is
     * {@link String }
     */
    public String getPid() {
        return pid;
    }

    /**
     * Sets the value of the pid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPid(String value) {
        this.pid = value;
    }

    /**
     * Gets the value of the dsID property.
     *
     * @return the ds id
     * possible object is
     * {@link String }
     */
    public String getDsID() {
        return dsID;
    }

    /**
     * Sets the value of the dsID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsID(String value) {
        this.dsID = value;
    }

    /**
     * Gets the value of the versionable property.
     *
     * @return true, if is versionable
     */
    public boolean isVersionable() {
        return versionable;
    }

    /**
     * Sets the value of the versionable property.
     *
     * @param value the new versionable
     */
    public void setVersionable(boolean value) {
        this.versionable = value;
    }

    /**
     * Gets the value of the logMessage property.
     *
     * @return the log message
     * possible object is
     * {@link String }
     */
    public String getLogMessage() {
        return logMessage;
    }

    /**
     * Sets the value of the logMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogMessage(String value) {
        this.logMessage = value;
    }

}
