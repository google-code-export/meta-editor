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

package cz.fi.muni.xkremser.editor.server;

import java.io.IOException;

import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.inject.Inject;

import com.google.inject.Injector;

import org.apache.commons.lang.time.DateUtils;

import cz.fi.muni.xkremser.editor.client.util.ClientUtils;
import cz.fi.muni.xkremser.editor.client.util.Constants;

import cz.fi.muni.xkremser.editor.server.config.EditorConfiguration;
import cz.fi.muni.xkremser.editor.server.fedora.utils.RESTHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class ScanImgServiceImpl.
 */
public class ScanImgServiceImpl
        extends HttpServlet {

    private static final long serialVersionUID = -6110151482519362291L;

    /** The configuration. */
    @Inject
    private EditorConfiguration config;

    private static final String DJATOKA_URL =
            "/djatoka/resolver?url_ver=Z39.88-2004&svc_id=info:lanl-repo/svc/getRegion&svc_val_fmt=info:ofi/fmt:kev:mtx:jpeg2000&svc.level=2&svc.scale=";

    private static final String DJATOKA_URL_SUFFIX = "&rft_id=";

    private static final String DJATOKA_URL_FULL_IMG =
            "/djatoka/resolver?url_ver=Z39.88-2004&svc_id=info:lanl-repo/svc/getRegion&svc_val_fmt=info:ofi/fmt:kev:mtx:jpeg2000&svc.level=5&svc.scale="
                    + Constants.IMAGE_FULL_HEIGHT + "&rft_id=";

    private static final String DJATOKA_URL_FULL_PAGE_DETAIL =
            "/djatoka/resolver?url_ver=Z39.88-2004&svc_id=info:lanl-repo/svc/getRegion&svc_val_fmt=info:ofi/fmt:kev:mtx:jpeg2000&svc.level=5&svc.format=image/jpeg&svc.scale=900,0&rft_id=";

    private static final String DJATOKA_URL_REGION = "&svc.region=";

    public static final String DJATOKA_URL_GET_METADATA =
            "/djatoka/resolver?url_ver=Z39.88-2004&svc_id=info:lanl-repo/svc/getMetadata&rft_id=";

    //    /** The config. */
    //    @Inject
    //    private EditorConfiguration config;

    //    private boolean baseOk;
    //
    //    private String base;

    /*
     * (non-Javadoc)
     * @see
     * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        resp.addDateHeader("Last-Modified", new Date().getTime());
        resp.addHeader("Cache-Control", "max-age=" + Constants.HTTP_CACHE_SECONDS);
        resp.addDateHeader("Expires", DateUtils.addMonths(new Date(), 1).getTime());
        boolean full = ClientUtils.toBoolean(req.getParameter(Constants.URL_PARAM_FULL));
        String topSpace = req.getParameter(Constants.URL_PARAM_TOP_SPACE);
        String urlHeight = req.getParameter(Constants.URL_PARAM_HEIGHT);

        String uuid = req.getParameter(Constants.URL_PARAM_UUID);

        if (uuid == null || "".equals(uuid)) {
            uuid =
                    req.getRequestURI().substring(req.getRequestURI().indexOf(Constants.SERVLET_SCANS_PREFIX)
                            + Constants.SERVLET_SCANS_PREFIX.length() + 1);
        }

        StringBuffer baseUrl = new StringBuffer();
        baseUrl.append("http");
        if (req.getProtocol().toLowerCase().contains("https")) {
            baseUrl.append('s');
        }
        baseUrl.append("://");
        if (!URLS.LOCALHOST()) {
            baseUrl.append(req.getServerName());
        } else {
            String hostname = config.getHostname();
            if (hostname.contains("://")) {
                baseUrl.append(hostname.substring(hostname.indexOf("://") + "://".length()));
            } else {
                baseUrl.append(hostname);
            }
        }
        StringBuffer sb = new StringBuffer();
        if (topSpace != null) {
            String metadata =
                    RESTHelper.convertStreamToString(RESTHelper
                            .get(baseUrl + DJATOKA_URL_GET_METADATA + uuid, null, null, true));
            String height = null;
            height = metadata.substring(metadata.indexOf("ght\": \"") + 7, metadata.indexOf("\",\n\"dw"));
            String width =
                    metadata.substring(metadata.indexOf("dth\": \"") + 7, metadata.indexOf("\",\n\"he"));

            int intHeight = Integer.parseInt(height);
            int intUrlHeight = Integer.parseInt(urlHeight);
            int intTopSpace = Integer.parseInt(topSpace);
            boolean isLower = intTopSpace > 0 && ((intHeight - intUrlHeight) < intTopSpace);
            String region =
                    (isLower ? intHeight - intUrlHeight : (intTopSpace < 0 ? 0 : topSpace)) + ",1,"
                            + urlHeight + "," + width;

            sb.append(baseUrl.toString()).append(DJATOKA_URL_FULL_PAGE_DETAIL).append(uuid)
                    .append(DJATOKA_URL_REGION).append(region);

        } else {
            sb.append(baseUrl.toString())
                    .append(full ? DJATOKA_URL_FULL_IMG : DJATOKA_URL + urlHeight + DJATOKA_URL_SUFFIX)
                    .append(uuid);
        }
        resp.setContentType("image/jpeg");
        resp.sendRedirect(resp.encodeRedirectURL(sb.toString()));
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException {
        super.init();
        Injector injector = getInjector();
        injector.injectMembers(this);
        //        base = config.getScanInputQueuePath();
        //        baseOk = base != null && !"".equals(base);
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Injector injector = getInjector();
        injector.injectMembers(this);
    }

    /**
     * Gets the injector.
     * 
     * @return the injector
     */
    protected Injector getInjector() {
        return (Injector) getServletContext().getAttribute(Injector.class.getName());
    }

}
