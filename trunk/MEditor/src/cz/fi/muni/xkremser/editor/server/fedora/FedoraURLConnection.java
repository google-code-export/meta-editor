package cz.fi.muni.xkremser.editor.server.fedora;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

public class FedoraURLConnection extends URLConnection {

	public static final String IMG_FULL = "IMG_FULL";
	public static final String IMG_THUMB = "IMG_THUMB";

	private final FedoraAccess fedoraAccess;

	FedoraURLConnection(URL url, FedoraAccess fedoraAccess) {
		super(url);
		this.fedoraAccess = fedoraAccess;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		String path = getURL().getPath();
		String uuid = null;
		String stream = null;
		StringTokenizer tokenizer = new StringTokenizer(path, "/");
		if (tokenizer.hasMoreTokens()) {
			uuid = tokenizer.nextToken();
		}
		if (tokenizer.hasMoreTokens()) {
			stream = tokenizer.nextToken();
		}
		if (stream.equals(IMG_FULL)) {
			return this.fedoraAccess.getImageFULL(uuid);
		} else if (stream.equals(IMG_THUMB)) {
			return this.fedoraAccess.getThumbnail(uuid);
		} else
			throw new IOException("uknown stream !");
	}

	@Override
	public void connect() throws IOException {
	}
}
