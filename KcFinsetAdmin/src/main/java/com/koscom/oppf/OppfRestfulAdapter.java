package com.koscom.oppf;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OppfRestfulAdapter {
	public static final int CONNECT_TIMEOUT = 15;
    public static final int WRITE_TIMEOUT = 15;
    public static final int READ_TIMEOUT = 15;

//    private static final String SERVER_URL = "http://sangcomz.xyz/open_sns/"; //2���� url�ڿ� /�� �Է��ؾ� �մϴ�.
//    private static final String DIRECTFOLDER_SERVER = "https://api.server.net/"; //2���� url�ڿ� /�� �Է��ؾ� �մϴ�.
    private static OkHttpClient client;
    
    private static String client_id = "l7xx10d2d6c7fa83464cb15ecf5cca60b563";
    private static String client_secret = "030c5577c6484dc98c5248c8414f5f55";
    private static String TOKEN_URL = "https://sandbox-apigw.koscom.co.kr/auth/oauth/v2/token";
    
	private static final Logger logger = LoggerFactory.getLogger(OppfRestfulAdapter.class);
    
    public static String getToken(String code) throws IOException{
		
		String plainCreds = client_id + ":" + client_secret;
		byte[] plainCredsBytes = plainCreds.getBytes(Charset.forName("US-ASCII"));
//		String base64Creds = "Basic "+ java.util.Base64.getEncoder().encodeToString(plainCredsBytes);
		String base64Creds = "Basic "+ DatatypeConverter.printBase64Binary(plainCredsBytes);
         OkHttpClient client = getInstance();

         RequestBody formBody = new FormBody.Builder()
                    .add("grant_type", "authorization_code")
                    .add("code", code)
                    .add("redirect_uri", "http://lkhuns2.cafe24.com/test/oppf/callback")
                    .build();

         Request builder = new Request.Builder()
         .url(TOKEN_URL)
         .addHeader("Authorization", base64Creds)
         .addHeader("Content-type", "application/x-www-form-urlencoded")
         .post(formBody)
         .build();

         Response response = client.newCall(builder).execute();
         String result = response.body().string();
         logger.debug("code : " + code);
         logger.debug("result :" + result);

         return result;

    }

    public synchronized static OkHttpClient getInstance() {
        if (client == null) {

            //OkHttpClient�� �����մϴ�.
            client = configureClient(new OkHttpClient().newBuilder()) //������ ����
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //���� Ÿ�Ӿƿ� �ð� ����
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) //���� Ÿ�Ӿƿ� �ð� ����
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) //�б� Ÿ�Ӿƿ� �ð� ����
                    .build();
        }
        return client;
    }

    /**
     * UnCertificated
     */
    public static OkHttpClient.Builder configureClient(final OkHttpClient.Builder builder) {
    	
        final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(final String hostname, final SSLSession session) {
                return true;
            }
        };
        builder.sslSocketFactory(new OppfSocketFactory(), x509TrustManager);
        builder.hostnameVerifier(hostnameVerifier);

        return builder;
    }
    
    public static X509TrustManager x509TrustManager = new X509TrustManager() {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            X509Certificate[] x509Certificates = new X509Certificate[0];
            return x509Certificates;
        }

        @Override
        public void checkServerTrusted(final X509Certificate[] chain,
                                       final String authType) throws CertificateException {
        }

        @Override
        public void checkClientTrusted(final X509Certificate[] chain,
                                       final String authType) throws CertificateException {
        }
    };
    
 }
