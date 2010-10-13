
package org.fedora.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "Fedora-API-M-Service", targetNamespace = "http://www.fedora.info/definitions/1/0/api/", wsdlLocation = "file:/home/freon/kramerius4/fedora-api/src/wsdl/APIM.wsdl")
public class FedoraAPIMService
    extends Service
{

    private final static URL FEDORAAPIMSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(org.fedora.api.FedoraAPIMService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = org.fedora.api.FedoraAPIMService.class.getResource(".");
            url = new URL(baseUrl, "file:/home/freon/kramerius4/fedora-api/src/wsdl/APIM.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/home/freon/kramerius4/fedora-api/src/wsdl/APIM.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        FEDORAAPIMSERVICE_WSDL_LOCATION = url;
    }

    public FedoraAPIMService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FedoraAPIMService() {
        super(FEDORAAPIMSERVICE_WSDL_LOCATION, new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-M-Service"));
    }

    /**
     * 
     * @return
     *     returns FedoraAPIM
     */
    @WebEndpoint(name = "Fedora-API-M-Service-HTTP-Port")
    public FedoraAPIM getFedoraAPIMServiceHTTPPort() {
        return super.getPort(new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-M-Service-HTTP-Port"), FedoraAPIM.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FedoraAPIM
     */
    @WebEndpoint(name = "Fedora-API-M-Service-HTTP-Port")
    public FedoraAPIM getFedoraAPIMServiceHTTPPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-M-Service-HTTP-Port"), FedoraAPIM.class, features);
    }

}
