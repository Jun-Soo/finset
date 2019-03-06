package com.koscom.oppf;

import com.koscom.util.FinsetException;
import com.koscom.util.LogUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

public class OppfSocketFactory extends SSLSocketFactory {
    static final Logger logger = LoggerFactory.getLogger(OppfSocketFactory.class);

	private SSLContext sslContext = null;

	private SSLContext createEasySSLContext() throws IOException {

        String certificateString = "-----BEGIN CERTIFICATE-----\n"
                + "MIIF7jCCBNagAwIBAgIQNWoMkw+ihFgPMcWZFGMxKDANBgkqhkiG9w0BAQsFADBE\n"
                + "MQswCQYDVQQGEwJVUzEWMBQGA1UEChMNR2VvVHJ1c3QgSW5jLjEdMBsGA1UEAxMU\n"
                + "R2VvVHJ1c3QgU1NMIENBIC0gRzMwHhcNMTcwODE3MDAwMDAwWhcNMTgxMDE2MjM1\n"
                + "OTU5WjBnMQswCQYDVQQGEwJLUjEOMAwGA1UECAwFU2VvdWwxGDAWBgNVBAcMD1ll\n"
                + "b25nZGV1bmdwby1ndTEVMBMGA1UECgwMS29zY29tIENvcnAuMRcwFQYDVQQDDA4q\n"
                + "Lmtvc2NvbS5jby5rcjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALND\n"
                + "Llbla+WVo6Hiy2tvztj81wUh58baMLbCg9kNWW7UYFDPVlJJ1Ctdqxe7BYwdJHep\n"
                + "aRRktbg9J80V2CHDOWH8mR7+LxypjqYFykub0msIR4XCTDvtbdNoKPqCTL4v6yOU\n"
                + "gNbSKZxJe8RvuL0R/dBqcbA4vExn3kM98wNYLbYPYT0kZtk9CBoV2okUd0rqFrFh\n"
                + "GcjatlyXoeJqhZk551OE/2bqUFG5jBbSau+TQK3UEeC+Pe84exVxZTmAH91xQU5V\n"
                + "Bj/dNprtKcjPHk5AAg9pJ83rM7mGGJGu4Xu3bD3gQEEq31kCGvzbvV0rMZYtQ15e\n"
                + "U2iG5uYCzuTZsxBzl7UCAwEAAaOCArcwggKzMCcGA1UdEQQgMB6CDioua29zY29t\n"
                + "LmNvLmtyggxrb3Njb20uY28ua3IwCQYDVR0TBAIwADAOBgNVHQ8BAf8EBAMCBaAw\n"
                + "KwYDVR0fBCQwIjAgoB6gHIYaaHR0cDovL2duLnN5bWNiLmNvbS9nbi5jcmwwgZ0G\n"
                + "A1UdIASBlTCBkjCBjwYGZ4EMAQICMIGEMD8GCCsGAQUFBwIBFjNodHRwczovL3d3\n"
                + "dy5nZW90cnVzdC5jb20vcmVzb3VyY2VzL3JlcG9zaXRvcnkvbGVnYWwwQQYIKwYB\n"
                + "BQUHAgIwNQwzaHR0cHM6Ly93d3cuZ2VvdHJ1c3QuY29tL3Jlc291cmNlcy9yZXBv\n"
                + "c2l0b3J5L2xlZ2FsMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcDAjAfBgNV\n"
                + "HSMEGDAWgBTSb/eW9IU/cjwwfSPahXibo3xafDBXBggrBgEFBQcBAQRLMEkwHwYI\n"
                + "KwYBBQUHMAGGE2h0dHA6Ly9nbi5zeW1jZC5jb20wJgYIKwYBBQUHMAKGGmh0dHA6\n"
                + "Ly9nbi5zeW1jYi5jb20vZ24uY3J0MIIBBQYKKwYBBAHWeQIEAgSB9gSB8wDxAHcA\n"
                + "3esdK3oNT6Ygi4GtgWhwfi6OnQHVXIiNPRHEzbbsvswAAAFd7eIO8QAABAMASDBG\n"
                + "AiEA0xlpwMGDxSi9UCDR2cVXOF5oMwh1/dDb4kc0exCJTxACIQD9mV4E0wQtkmnG\n"
                + "DRDObLW5A1w1+aMuWyjROrDwGs+n4wB2AKS5CZC0GFgUh7sTosxncAo8NZgE+Rvf\n"
                + "uON3zQ7IDdwQAAABXe3iDx8AAAQDAEcwRQIhAOPDLmN2yZk0e6YBXznsjk6bVvrc\n"
                + "LFsIUH6LoNEJdyzGAiBWZwitY6JB5S0Ejbks7J6XdF/oX8ImWNU0FTYY7qjqszAN\n"
                + "BgkqhkiG9w0BAQsFAAOCAQEAoUI3g2wFGQIMubPn/bDrdY97JTlu064z7rqRoX6K\n"
                + "PeHLS7W/Km+DvZMixzYl0YtJrXEL2S1goCkR40KEM0G9VCSjJAGK/Nu75hW3Qomn\n"
                + "gVQC0TF9KxjUTGHvhsb/MhKULDhE7Ld3MwwrDP8CWg0F1Y1DmBzqKaFqzEKENuo5\n"
                + "Pj9GICcb/smR1zzltjJ5hWVYhoYvvD2c0Nl7Mx4KFBq6pYAXCd7RU4sD/v/9+r0T\n"
                + "Dguv6V7EzE5CdYNy/W3EINdqQ8Bv69C+lCcV1D8xKEmK4YrUqz94qi4Yt653YWdg\n"
                + "t+UqHpwc8pancOYVuocCPZR5RvVaCLtXGP+v6P5eU4Q2/A==\n" + "-----END CERTIFICATE-----";

        ByteArrayInputStream derInputStream = null;
		CertificateFactory certificateFactory = null;
		X509Certificate cert = null;
		String alias = null;
		KeyStore trustStore = null;
        TrustManagerFactory tmf = null;
        try {
            derInputStream = new ByteArrayInputStream(certificateString.getBytes());
            certificateFactory = CertificateFactory.getInstance("X.509");
            cert = (X509Certificate) certificateFactory.generateCertificate(derInputStream);
            alias = cert.getSubjectX500Principal().getName();

            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            trustStore.setCertificateEntry(alias, cert);

            sslContext = SSLContext.getInstance("TLSv1");
            tmf = TrustManagerFactory.getInstance("X509");
            tmf.init(trustStore);
            sslContext.init(null, tmf.getTrustManagers(), null);
        } catch (CertificateException e) {
            throw new IOException(e);
        } catch (KeyStoreException e) {
            throw new IOException(e);
        } catch (IOException e) {
            throw new IOException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        } catch (KeyManagementException e) {
            throw new IOException(e);
        }

        return sslContext;

	}

	private SSLContext getSSLContext() throws IOException {
		if (this.sslContext == null) {
			this.sslContext = createEasySSLContext();
		}
		return this.sslContext;
	}
	
	@Override
	public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
		// TODO Auto-generated method stub
		Socket soket = null;
        soket = getSSLContext().getSocketFactory().createSocket(s, host, port, autoClose);
        return soket;
	}

	@Override
	public String[] getDefaultCipherSuites() {
		// TODO Auto-generated method stub
		String[] result = null;
		try {
			return getSSLContext().getSocketFactory().getDefaultCipherSuites();
		} catch (IOException e) {
			LogUtil.error(logger,e);
			result = new String[0];
		}
		return result;
	}

	@Override
	public String[] getSupportedCipherSuites() {
		// TODO Auto-generated method stub
        String[] result = null;
		try {
			result = getSSLContext().getSocketFactory().getSupportedCipherSuites();
		} catch (IOException e) {
            LogUtil.error(logger,e);
		}
		return result;
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
		// TODO Auto-generated method stub
		return getSSLContext().getSocketFactory().createSocket(host, port);
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		// TODO Auto-generated method stub
		return getSSLContext().getSocketFactory().createSocket(host, port);
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
			throws IOException, UnknownHostException {
		// TODO Auto-generated method stub
		return getSSLContext().getSocketFactory().createSocket(host, port, localHost, localPort);
	}

	@Override
	public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
			throws IOException {
		// TODO Auto-generated method stub
		return getSSLContext().getSocketFactory().createSocket(address, port, localAddress, localPort);
	}

}
