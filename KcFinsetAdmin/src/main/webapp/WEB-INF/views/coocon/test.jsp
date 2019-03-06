<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page import="java.net.*"%>
<%@ page import = "java.io.*" %>
<%@ page import = "java.net.URLConnection" %>
<%@ page import = "javax.net.ssl.TrustManager" %>
<%@ page import = "javax.net.ssl.HostnameVerifier" %>
<%@ page import = "javax.net.ssl.HttpsURLConnection" %>
<%@ page import = "javax.net.ssl.SSLContext" %>
<%@ page import = "javax.net.ssl.SSLSession" %>
<%@ page import = "javax.net.ssl.X509TrustManager" %>
<%@ page import = "java.security.cert.X509Certificate" %>
<%
TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
    public void checkClientTrusted(X509Certificate[] certs, String authType) {  }
    public void checkServerTrusted(X509Certificate[] certs, String authType) {  }
 }
};    
SSLContext sc = SSLContext.getInstance("SSL");
sc.init(null, trustAllCerts, new java.security.SecureRandom());
HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());	
HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

//API 호출주소

//String url = "http://XXXXXXXX";//api를 호출할 url주소를 입력하시면 됩니다.
String url = "http://dev.coocon.co.kr/0127_test.jsp";//현금영수증 api 호출예제.
byte[] resMessage = null;

JSONObject JSONDataVal = new JSONObject();
JSONDataVal.put("API_KEY", "3s4Gis8kOAHwPSce4FHe");
JSONDataVal.put("API_ID", "0127");
JSONDataVal.put("ORG_TYPE", "1");
JSONDataVal.put("ORG_CD", "002");
JSONDataVal.put("GUBUN", "1");

HttpURLConnection conn = null;

String decodeString = null;
OutputStreamWriter os = null;
DataInputStream in = null;
ByteArrayOutputStream bout = null;

try {
    conn = (HttpURLConnection) new URL(url).openConnection();	
    conn.setDoInput(true); 
    conn.setDoOutput(true);
    conn.setRequestMethod("POST");
    conn.setUseCaches(false);
    os = new OutputStreamWriter(conn.getOutputStream());

    String postString = "JSONData="+JSONDataVal;
    os.write(postString);
    os.flush();
    os.close();

    in = new DataInputStream(conn.getInputStream());
    bout = new ByteArrayOutputStream();
    int bcount = 0;
    byte[] buf = new byte[2048];

    while (true) {
        int n = in.read(buf);
        if (n == -1) break;
        bout.write(buf, 0, n);
    }
    bout.flush(); 
    resMessage = bout.toByteArray();
    conn.disconnect();
    
    String temp = new String(resMessage, "UTF-8");
	decodeString = URLDecoder.decode(temp);
	
	String temp = new String(resMessage, "UTF-8");
	decodeString = "REQ_DATA"+URLDecoder.decode(temp);
	
	
	Gson gson = new Gson();
	CooconJsonArray cooconJsonArray = new CooconJsonArray();
	cooconJsonArray = gson.fromJson( JSONSerializer.toJSON(decodeString).toString() , CooconJsonArray.class ) ;
	if(cooconJsonArray != null  && cooconJsonArray.getRESULT_CD().equals("00000000") && cooconJsonArray.getRESP_DATA() != null){
		System.out.println(cooconJsonArray.getRESULT_CD() + ":" + cooconJsonArray.getRESULT_MG()  + ":" +  cooconJsonArray.getTOTAL_COUNT());
	}
%>
tset