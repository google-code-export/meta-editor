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

package cz.fi.muni.xkremser.editor.server.fedora.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.util.UUID;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import com.uwyn.jhighlight.renderer.XhtmlRendererFactory;

import org.apache.log4j.Logger;

import org.dom4j.DocumentHelper;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.XPath;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import cz.fi.muni.xkremser.editor.client.util.Constants;

import cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess;
import cz.fi.muni.xkremser.editor.server.newObject.Namespaces;
import cz.fi.muni.xkremser.editor.server.newObject.Policy;
import cz.fi.muni.xkremser.editor.server.newObject.RelsExtRelation;

import cz.fi.muni.xkremser.editor.shared.rpc.Foxml;

/**
 * The Class FoxmlUtils.
 */
public class FoxmlUtils {

    private static final String VALUE_ATRIBUTE = "VALUE";

    /** The constant of Label value **/
    public static final String LABEL_VALUE = "info:fedora/fedora-system:def/model#label";

    private static final Logger LOGGER = Logger.getLogger(FoxmlUtils.class.getPackage().toString());

    /**
     * Title from Foxml.
     * 
     * @param foxml
     *        the foxml
     * @return the string
     */
    public static String getLabel(org.w3c.dom.Document foxml) {
        String propertyLabelXPath = "//foxml:objectProperties/foxml:property[@NAME=\'" + LABEL_VALUE + "\']";

        try {
            XPathExpression all = FedoraUtils.makeNSAwareXpath().compile(propertyLabelXPath);
            NodeList listOfstream = (NodeList) all.evaluate(foxml, XPathConstants.NODESET);
            Element propertyLabelElement = null;
            if (listOfstream.getLength() != 0) {
                propertyLabelElement = (Element) listOfstream.item(0);
            }
            return propertyLabelElement.getAttribute(VALUE_ATRIBUTE);

        } catch (XPathExpressionException e) {
            LOGGER.error("XPath failure", e);
            return null;
        }
    }

    /**
     * Handle foxml.
     * 
     * @param uuid
     *        the uuid
     * @param onlyTitleAndUuid
     *        the only title and uuid
     * @return the Foxml
     */
    public static Foxml handleFoxml(String uuid, FedoraAccess fedoraAccess) {
        Foxml foxml = new Foxml();

        String stringFoxml = fedoraAccess.getFOXML(uuid);
        foxml.setNoCodedfoxml(stringFoxml);
        InputStream is = getInputStreamFromString(stringFoxml, uuid);

        try {
            foxml.setFoxml(handleFOXMLString(stringFoxml, uuid));
            Document foxmlDocument = getFoxmlDocument(is);
            foxml.setIdentifier(uuid);
            foxml.setLabel(FoxmlUtils.getLabel(foxmlDocument));

        } catch (IOException e) {
            LOGGER.error("Unable to get Foxml metadata for " + uuid + "[" + e.getMessage() + "]", e);
        }
        return foxml;
    }

    public static Document getFoxmlDocument(InputStream docStream) throws IOException {
        try {
            return XMLUtils.parseDocument(docStream, true);
        } catch (ParserConfigurationException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IOException(e);
        } catch (SAXException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IOException(e);
        } finally {
            docStream.close();
        }
    }

    /**
     * Handle foxml.
     * 
     * @param uuid
     *        the uuid
     * @return the string
     */
    private static String handleFOXMLString(String returnString, String uuid) {
        String returnedString = returnString;
        String highlighted = null;
        try {
            highlighted =
                    XhtmlRendererFactory.getRenderer("xml").highlight("foxml",
                                                                      returnedString,
                                                                      "Windows-1250",
                                                                      true);
        } catch (IOException e) {
            LOGGER.error("Unable to get FOXML representation for " + uuid + "[" + e.getMessage() + "]", e);
            return returnedString;
        }
        return highlighted.substring(highlighted.indexOf('\n'));
    }

    public static InputStream getInputStreamFromString(String stringFoxml, String uuid) {
        try {
            return new ByteArrayInputStream(stringFoxml.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Unable to get InputStream of Foxml from String format for " + uuid + "["
                                 + e.getMessage() + "]",
                         e);
        }
        return null;
    }

    public static String encodeToURL(String string) {
        try {
            return java.net.URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Unable to encode string " + string, e);
            e.printStackTrace();
        }
        return null;
    }

    public static org.dom4j.Element createModsCollectionEl() {
        org.dom4j.Element modsCollection =
                DocumentHelper.createElement(new QName("modsCollection", Namespaces.mods));
        modsCollection.add(Namespaces.mods);
        String modsSchema = "http://www.loc.gov/standards/mods/v3/mods-3-3.xsd";
        modsCollection.addAttribute("schemaLocation", Namespaces.mods.getURI() + " " + modsSchema);
        return modsCollection;
    }

    public static void addRelationshipToRelsExt(org.dom4j.Document relsExtDoc, RelsExtRelation reference) {
        org.dom4j.Element descriptionEl = findRelsExtDescriptionElement(relsExtDoc);
        org.dom4j.Element referenceEl =
                descriptionEl.addElement(new QName(reference.getRelationName().getStringRepresentation(),
                                                   Namespaces.kramerius));
        String resourceStr = "info:fedora/".concat(Constants.FEDORA_UUID_PREFIX + reference.getTargetUuid());
        referenceEl.addAttribute(new QName("resource", Namespaces.rdf), resourceStr);
    }

    private static org.dom4j.Element findRelsExtDescriptionElement(org.dom4j.Document relsExt) {
        XPath descriptionXPath = Dom4jUtils.createXPath("/rdf:RDF/rdf:Description");
        return (org.dom4j.Element) descriptionXPath.selectSingleNode(relsExt);
    }

    public static org.dom4j.Element findDescriptionElement(org.dom4j.Document relsExt) {
        XPath descriptionXPath = Dom4jUtils.createXPath("//rdf:Description");
        return (org.dom4j.Element) descriptionXPath.selectSingleNode(relsExt);
    }

    public static org.dom4j.Document createRelsExtSkeleton(String uuid, String model, Policy policy) {
        Namespace rdfNs = Namespaces.rdf;
        Namespace krameriusNs = Namespaces.kramerius;
        org.dom4j.Element rootElement = DocumentHelper.createElement(new QName("RDF", rdfNs));
        rootElement.add(rdfNs);
        rootElement.add(krameriusNs);
        org.dom4j.Document doc = DocumentHelper.createDocument(rootElement);
        org.dom4j.Element description = rootElement.addElement(new QName("Description", rdfNs));
        description.addAttribute(new QName("about", rdfNs),
                                 "info:fedora/".concat(Constants.FEDORA_UUID_PREFIX + uuid));
        org.dom4j.Element hasModel = description.addElement(new QName("hasModel", Namespaces.fedora_model));
        hasModel.addAttribute(new QName("resource", rdfNs), "info:fedora/model:".concat(model));
        org.dom4j.Element itemId = description.addElement(new QName("itemID", Namespaces.oai));
        itemId.addText(Constants.FEDORA_UUID_PREFIX + uuid);
        org.dom4j.Element krameriusPolicy = description.addElement(new QName("policy", krameriusNs));
        krameriusPolicy.addText("policy:".concat(policy.toString().toLowerCase()));
        return doc;
    }

    public static String getRandomUuid() {
        return UUID.randomUUID().toString();
    }
}
