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

package cz.fi.muni.xkremser.editor.server.fedora;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import javax.inject.Inject;

import com.google.inject.name.Named;

import org.w3c.dom.Document;

import org.fedora.api.FedoraAPIA;
import org.fedora.api.FedoraAPIM;
import org.fedora.api.ObjectFactory;

import cz.fi.muni.xkremser.editor.shared.domain.DigitalObjectModel;

// TODO: Auto-generated Javadoc
/**
 * This is secured variant of class FedoraAccessImpl {@link FedoraAccessImpl}. <br>
 * Only three methos are secured:
 * <ul>
 * <li>FedoraAccess#getImageFULL(String)</li>
 * <li>FedoraAccess#isImageFULLAvailable(String)</li>
 * <li>FedoraAccess#getImageFULLMimeType(String)</li>
 * </ul>
 * 
 * @see FedoraAccess#getImageFULL(String)
 * @see FedoraAccess#isImageFULLAvailable(String)
 * @see FedoraAccess#getImageFULLMimeType(String)
 * @author xkremser
 */
public class SecuredFedoraAccessImpl
        implements FedoraAccess {

    /** The raw access. */
    private final FedoraAccess rawAccess;

    /** The acceptor. */
    private IPaddressChecker acceptor;

    /**
     * Instantiates a new secured fedora access impl.
     * 
     * @param rawAccess
     *        the raw access
     */
    @Inject
    public SecuredFedoraAccessImpl(@Named("rawFedoraAccess") FedoraAccess rawAccess) {
        super();
        this.rawAccess = rawAccess;
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getBiblioMods(java
     * .lang.String)
     */
    @Override
    public Document getBiblioMods(String uuid) throws IOException {
        return rawAccess.getBiblioMods(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getDC(java.lang
     * .String )
     */
    @Override
    public Document getDC(String uuid) throws IOException {
        return rawAccess.getDC(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getImageFULL(java
     * .lang.String)
     */
    @Override
    public InputStream getImageFULL(String uuid) throws IOException {
        if (!this.acceptor.privateVisitor()) {
            Document relsExt = this.rawAccess.getRelsExt(uuid);
            checkPolicyElement(relsExt);
        }
        return rawAccess.getImageFULL(uuid);
    }

    /**
     * Check policy element.
     * 
     * @param relsExt
     *        the rels ext
     * @throws IOException
     *         Signals that an I/O exception has occurred.
     */
    private void checkPolicyElement(Document relsExt) throws IOException {
        try {
            XPathFactory xpfactory = XPathFactory.newInstance();
            XPath xpath = xpfactory.newXPath();
            xpath.setNamespaceContext(new FedoraNamespaceContext());
            XPathExpression expr = xpath.compile("//kramerius:policy/text()");
            Object policy = expr.evaluate(relsExt, XPathConstants.STRING);
            if ((policy != null) && (policy.toString().trim().equals("policy:private"))) {
                throw new SecurityException("access denided");
            }
        } catch (XPathExpressionException e) {
            throw new IOException(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getKrameriusModel
     * (org.w3c.dom.Document)
     */
    @Override
    public DigitalObjectModel getDigitalObjectModel(Document relsExt) {
        return rawAccess.getDigitalObjectModel(relsExt);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getKrameriusModel
     * (java.lang.String)
     */
    @Override
    public DigitalObjectModel getDigitalObjectModel(String uuid) throws IOException {
        return rawAccess.getDigitalObjectModel(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getRelsExt(java
     * .lang .String)
     */
    @Override
    public Document getRelsExt(String uuid) throws IOException {
        return rawAccess.getRelsExt(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getThumbnail(java
     * .lang.String)
     */
    @Override
    public InputStream getThumbnail(String uuid) throws IOException {
        return rawAccess.getThumbnail(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#isImageFULLAvailable
     * (java.lang.String)
     */
    @Override
    public boolean isImageFULLAvailable(String uuid) throws IOException {
        // not checked method
        return rawAccess.isImageFULLAvailable(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#isContentAccessible
     * (java.lang.String)
     */
    @Override
    public boolean isContentAccessible(String uuid) throws IOException {
        if (!this.acceptor.privateVisitor()) {
            Document relsExt = this.rawAccess.getRelsExt(uuid);
            try {
                checkPolicyElement(relsExt);
            } catch (SecurityException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the acceptor.
     * 
     * @return the acceptor
     */
    public IPaddressChecker getAcceptor() {
        return acceptor;
    }

    /**
     * Sets the acceptor.
     * 
     * @param acceptor
     *        the new acceptor
     */
    @Inject
    public void setAcceptor(IPaddressChecker acceptor) {
        this.acceptor = acceptor;
    }

    /*
     * (non-Javadoc)
     * @see cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getAPIA()
     */
    @Override
    public FedoraAPIA getAPIA() {
        return rawAccess.getAPIA();
    }

    /*
     * (non-Javadoc)
     * @see cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getAPIM()
     */
    @Override
    public FedoraAPIM getAPIM() {
        return rawAccess.getAPIM();
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getObjectFactory()
     */
    @Override
    public ObjectFactory getObjectFactory() {
        return rawAccess.getObjectFactory();
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getPagesUuid(java
     * .lang.String)
     */
    @Override
    public List<String> getPagesUuid(String uuid) throws IOException {
        return rawAccess.getPagesUuid(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getIntCompPartsUuid
     * (java.lang.String)
     */
    @Override
    public List<String> getIntCompPartsUuid(String uuid) throws IOException {
        return rawAccess.getIntCompPartsUuid(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getMonographUnitsUuid
     * (java.lang.String)
     */
    @Override
    public List<String> getMonographUnitsUuid(String uuid) throws IOException {
        return rawAccess.getMonographUnitsUuid(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getFOXML(java.lang
     * .String)
     */
    @Override
    public String getFOXML(String uuid) {
        return rawAccess.getFOXML(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getFOXMLInputStream
     * (java.lang.String)
     */
    @Override
    public InputStream getFOXMLInputStream(String uuid) {
        return rawAccess.getFOXMLInputStream(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getPeriodicalItemsUuid
     * (java.lang.String)
     */
    @Override
    public List<String> getPeriodicalItemsUuid(String uuid) throws IOException {
        return rawAccess.getPeriodicalItemsUuid(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getVolumesUuid(java
     * .lang.String)
     */
    @Override
    public List<String> getVolumesUuid(String uuid) throws IOException {
        return rawAccess.getVolumesUuid(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess#getOcr(java.lang.
     * String)
     */
    @Override
    public String getOcr(String uuid) {
        return rawAccess.getOcr(uuid);
    }

    @Override
    public List<String> getChildrenUuid(String uuid,
                                        DigitalObjectModel parentModel,
                                        DigitalObjectModel childModel) throws IOException {
        return rawAccess.getChildrenUuid(uuid, parentModel, childModel);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public boolean isDigitalObjectPresent(String uuid) {
        return rawAccess.isDigitalObjectPresent(uuid);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public List<String> getIsOnPagesUuid(String uuid) throws IOException {
        return rawAccess.getIntCompPartsUuid(uuid);
    }

    @Override
    public String getMimeTypeForStream(String pid, String datastreamName) throws IOException {
        return rawAccess.getMimeTypeForStream(pid, datastreamName);

    }
}
