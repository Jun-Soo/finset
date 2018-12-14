package com.koscom.util;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

public class URLConnection {
	
	private static final Logger logger = LoggerFactory.getLogger(URLConnection.class);
	
	public URLConnection() {}
	
	/**
	 * URL GET 요청
	 * @param targetUrl
	 * @param param
	 * @return
	 */
	public ReturnClass sendReqGET(String targetUrl, String param) {

		if (StringUtil.isEmpty(targetUrl)) {
			logger.info("==== 요청 URL 이 없습니다. ====");
			return new ReturnClass(Constant.FAILED, "요청 URL 이 없습니다.");
		}		
			
		HttpURLConnection connection = null;
		String returnMsg = "";
		String cd_result = Constant.FAILED;
        BufferedReader in = null;

		try {
			String preg = targetUrl.indexOf("?") > 0 ? "&" : "?";
			URL aURL = new URL(targetUrl + preg  + param);

			connection = (HttpURLConnection) aURL.openConnection();
			connection.setConnectTimeout(30000); // millisecond//
			connection.setReadTimeout(30000); // millisecond//
			connection.setRequestProperty("contentType", "text/html; charset=EUC-KR");
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			
			logger.info("1. 요청 URL["+targetUrl + preg + param + "]");
			logger.info("==== 요청 처리 결과 리턴 [" + connection.getResponseCode() + "]: \r\n[" + targetUrl + "]\r\n[" + param + "]");

            // 데이터 수신
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                // 리턴되는 메시지를 읽는다 .

                String strLine ="";
                while ((strLine=in.readLine())!= null) {
                    returnMsg+=strLine;
                }
                cd_result = Constant.SUCCESS;
                if( in != null ){
                      in = null;
                 }
            } else {
                returnMsg = "전송 실패("+connection.getResponseCode()+")";
            }

		} catch (ConnectException ex) {
            if (connection != null) {
                returnMsg = "[" + connection.getURL().toString() + "] 연결 실패";
            } else {
                returnMsg = "[" + connection + "] 연결 실패";
            }
        } catch (Exception e) {
			e.printStackTrace();
			logger.error("**** 요청 처리 실패" + e);
		} finally {
            if (in !=null) {try {in.close();} catch (IOException e) {}}
            // 연결 종료
            if (connection != null) {
                connection.disconnect();
            }
        }

		logger.info("2. 요청 처리 결과 메시지 ["+returnMsg+"]");
		return new ReturnClass(cd_result, returnMsg);
	}
	public ReturnClass sendReqPOST_KB(String targetUrl, String postString) {
		if (StringUtil.isEmpty(targetUrl)) {
			logger.info("==== 요청 된 URL 이 없습니다. ====");
			return new ReturnClass(Constant.FAILED, "요청 URL 이 없습니다.");
		}		
		String returnMsg = "";
		String cd_result = Constant.FAILED;
		HttpURLConnection     conn = null;
        ByteArrayOutputStream bout = null;

        DataInputStream    in = null;
        OutputStreamWriter os = null;
        try {
            byte[] resMessage = null;
            conn = (HttpURLConnection) new URL(targetUrl).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            os = new OutputStreamWriter(conn.getOutputStream());
            os.write(postString);
            os.flush();
            os.close();
            logger.info("1. 요청 URL["+ targetUrl + "]");
            logger.info("==== 요청 처리 결과 리턴 [" + conn.getResponseCode() + "]: \r\n[" + targetUrl + "]\r\n[" + postString + "]");

            // 데이터 수신
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = new DataInputStream(conn.getInputStream());
                bout = new ByteArrayOutputStream();
                byte[] buf = new byte[2048];

                while (true) {
                    int n = in.read(buf);
                    if (n == -1) break;
                    bout.write(buf, 0, n);
                }
                bout.flush();
                resMessage = bout.toByteArray();
                returnMsg = new String(resMessage, "UTF-8");
                returnMsg = returnMsg.replaceAll("\r\n","");
                returnMsg = returnMsg.replaceAll("\r","");
                returnMsg = returnMsg.replaceAll("\n","");
                logger.info(resMessage.toString());
                logger.info(returnMsg.trim());
                cd_result = Constant.SUCCESS;

            }else if(conn.getResponseCode() ==  HttpURLConnection.HTTP_MOVED_TEMP || conn.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM){
                cd_result = Constant.SUCCESS;
                String redirectedUrl = conn.getHeaderField("Location");
                logger.info(redirectedUrl + "<<<<<<<<<<<<<<<<<< redirectedUrl");
            }else {
                returnMsg = "전송 실패("+conn.getResponseCode()+")";
            }
        } catch (IOException e) {
            if (conn !=null) {
                try {
                    returnMsg = "[" + conn.getURL().toString() + "] 연결 실패("+conn.getResponseCode()+")";
                } catch (IOException e1) {
                    returnMsg = "[conn=" + conn + "] 연결 실패";
                }
            }
            LogUtil.error(logger,e);
		}finally {
            if (conn!=null) {conn.disconnect();}
            if (bout != null) {try {bout.close();} catch (IOException e) {}}
            if (os   != null) {try {os  .close();} catch (IOException e) {}}
            if (in   != null) {try {in  .close();} catch (IOException e) {}}
            if (in   != null) {try {in  .close();} catch (IOException e) {}}
        }
    	logger.info("2. 요청 처리 결과 메시지 ["+returnMsg+"]");
		return new ReturnClass(cd_result, "요청을 처리하였습니다." , returnMsg);
	}
	
	/**
	 * URL POST 요청
	 * @param targetUrl
	 * @param param
	 * @return
	 */
	public ReturnClass sendReqPOST(String targetUrl, String param) {
		
		if (StringUtil.isEmpty(targetUrl)) {
			logger.info("==== 요청 된 URL 이 없습니다. ====");
			return new ReturnClass(Constant.FAILED, "요청 URL 이 없습니다.");
		}		
		
		HttpURLConnection connection = null;
		String returnMsg = "";
		String cd_result = Constant.FAILED;

		URL aURL = null;
		BufferedReader in = null;
		OutputStream opstrm = null;

		try {
			aURL = new URL(targetUrl);

			connection = (HttpURLConnection) aURL.openConnection();
			connection.setConnectTimeout(30000); // millisecond//
			connection.setReadTimeout(60000); // millisecond//
			// 헤더값 설정
			connection.setRequestProperty("contentType", "text/html; charset=EUC-KR");
			// 전달방식을 설정한다. POST or GET, 기본값은 GET 이다.
			connection.setRequestMethod("POST");
			// 서버로 데이터를 전송할 수 있도록 한다. GET 방식이면 사용될 일이 없으나,
			// true 로 설정하면 자동으로 POST 로 설정된다. 기본값은 false 이다.
			connection.setDoOutput(true);
			// 서버로부터 메세지를 받을 수 있도록 한다. 기본값은 true 이다.
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setDefaultUseCaches(false);
			
			opstrm = connection.getOutputStream();
			opstrm.write( param.getBytes() );
			opstrm.flush();
			opstrm.close();

			logger.info("1. 요청 URL["+ targetUrl + "]");
			logger.info("==== 요청 처리 결과 리턴 [" + connection.getResponseCode() + "]: \r\n[" + targetUrl + "]\r\n[" + param + "]");
			
				// 데이터 수신
				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					// 리턴되는 메시지를 읽는다 .
					
					String strLine ="";
					while ((strLine=in.readLine())!= null) {
						returnMsg+=strLine;
					}
					if( in != null ){
					      in = null;
				     }
					cd_result = Constant.SUCCESS;
				//리턴 302,303
				}else if(connection.getResponseCode() ==  HttpURLConnection.HTTP_MOVED_TEMP 
						|| connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM){
					
					cd_result = Constant.SUCCESS;
					String redirectedUrl = connection.getHeaderField("Location");
 		            logger.info(redirectedUrl + "<<<<<<<<<<<<<<<<<< redirectedUrl");
				}else {
					returnMsg = "전송 실패("+connection.getResponseCode()+")";
				}

		} catch (ConnectException ex) {
			if (connection !=null) {
				try {
					returnMsg = "[" + connection.getURL().toString() + "] 연결 실패("+connection.getResponseCode()+")";
				} catch (IOException e) {

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("**** 요청 처리 실패" + e);
		} finally {
			if(opstrm != null) {
				try {
					opstrm.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				connection.disconnect();
			}
		}
		
		logger.info("2. 요청 처리 결과 메시지 ["+returnMsg+"]");
		return new ReturnClass(cd_result, "요청을 처리하였습니다." , returnMsg);
	}
	
	/** VUE
	 * URL GET 요청 (핀테크 오픈플렛폼)
	 * @param targetUrl
	 * @param param
	 * @return
	 */
	public ReturnClass sendReqGET_Direct(String targetUrl, String apikey, String param) {

		if (StringUtil.isEmpty(targetUrl)) {
			logger.info("==== 요청 URL 이 없습니다. ====");
			return new ReturnClass(Constant.FAILED, "요청 URL 이 없습니다.");
		}		
			
		HttpURLConnection connection = null;
		String returnMsg = "";
		String cd_result = Constant.FAILED;
        BufferedReader in = null;

		try {
			// SSL 처리(통과)
    		TrustManager[] trustAllCerts = new TrustManager[] { 
    				new X509TrustManager() { 
    					public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
    						return null; 
    					} 
    					public void checkClientTrusted(X509Certificate[] certs, String authType) { } 
    					public void checkServerTrusted(X509Certificate[] certs, String authType) { } 
    				} 
    		}; 
    		SSLContext sc = SSLContext.getInstance("SSL");
    		sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
    		
    		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory()); 
			String preg = targetUrl.indexOf("?") > 0 ? "&" : "?";
			URL aURL = new URL(targetUrl + preg  + param);

			connection = (HttpURLConnection) aURL.openConnection();
			connection.setConnectTimeout(30000); // millisecond//
			connection.setReadTimeout(30000); // millisecond//
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setRequestProperty("apikey", apikey);
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			
			logger.info("1. 요청 URL["+targetUrl + preg + param + "]");
			logger.info("==== 요청 처리 결과 리턴 [" + connection.getResponseCode() + "]: \r\n[" + targetUrl + "]\r\n[" + param + "]");

            // 데이터 수신
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                // 리턴되는 메시지를 읽는다 .

                String strLine ="";
                while ((strLine=in.readLine())!= null) {
                    returnMsg+=strLine;
                }
                cd_result = Constant.SUCCESS;
                if( in != null ){
                      in = null;
                 }
            } else {
                returnMsg = "전송 실패("+connection.getResponseCode()+")";
            }

		} catch (ConnectException ex) {
            if (connection != null) {
                returnMsg = "[" + connection.getURL().toString() + "] 연결 실패";
            } else {
                returnMsg = "[" + connection + "] 연결 실패";
            }
        } catch (Exception e) {
			e.printStackTrace();
			logger.error("**** 요청 처리 실패" + e);
		} finally {
            if (in !=null) {try {in.close();} catch (IOException e) {}}
            // 연결 종료
            if (connection != null) {
                connection.disconnect();
            }
        }

		logger.info("2. 요청 처리 결과 메시지 ["+returnMsg+"]");
		return new ReturnClass(cd_result, "요청을 처리하였습니다." , returnMsg);
	}
	
	/** VUE
	 * URL POST 요청 (핀테크 오픈플렛폼)
	 * @param targetUrl
	 * @param param
	 * @return
	 */
	public ReturnClass sendReqPOST_Direct(String targetUrl, HashMap<String, String> headerMap, String body) {
		if (StringUtil.isEmpty(targetUrl)) {
			logger.error("==== 요청 된 URL 이 없습니다. ====");
			return new ReturnClass(Constant.FAILED, "요청 URL 이 없습니다.");
		}
		
		HttpURLConnection connection = null;
		int returnCd = 0;
		String returnMsg = "";
		String cd_result = Constant.FAILED;

		URL aURL = null;
		BufferedReader in = null;
		OutputStream opstrm = null;

		try {
			// SSL 처리(통과)
    		TrustManager[] trustAllCerts = new TrustManager[] { 
    				new X509TrustManager() { 
    					public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
    						return null; 
    					} 
    					public void checkClientTrusted(X509Certificate[] certs, String authType) { } 
    					public void checkServerTrusted(X509Certificate[] certs, String authType) { } 
    				} 
    		}; 
    		SSLContext sc = SSLContext.getInstance("SSL");
    		sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
    		
    		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory()); 
    		
			aURL = new URL(targetUrl);
			connection = (HttpURLConnection) aURL.openConnection();
			connection.setConnectTimeout(30000); // millisecond//
			connection.setReadTimeout(60000); // millisecond//
		
			// 헤더값 설정 - header map 추가
			Set<Entry<String, String>> set = headerMap.entrySet();
			Iterator<Entry<String, String>> itr = set.iterator();

			while (itr.hasNext())
			{
				Map.Entry<String, String> e = (Map.Entry<String, String>)itr.next();
				connection.setRequestProperty(e.getKey(), e.getValue());
			}
		
			// 전달방식을 설정한다. POST or GET, 기본값은 GET 이다.
			connection.setRequestMethod("POST");
			// 서버로 데이터를 전송할 수 있도록 한다. GET 방식이면 사용될 일이 없으나,
			// true 로 설정하면 자동으로 POST 로 설정된다. 기본값은 false 이다.
			connection.setDoOutput(true);
			// 서버로부터 메세지를 받을 수 있도록 한다. 기본값은 true 이다.
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setDefaultUseCaches(false);

			opstrm = connection.getOutputStream();
			opstrm.write(body.getBytes("UTF-8"));
			opstrm.flush();
			opstrm.close();
			
			InputStream ipstrm = null;
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				ipstrm = connection.getInputStream();
				cd_result = Constant.SUCCESS;
			}
			else	{
				ipstrm = connection.getErrorStream();
			}
			
			Scanner scn = new Scanner(ipstrm, "UTF-8").useDelimiter("\\A");
			if (scn.hasNext()) {
				returnMsg =  scn.next();					
			}
			ipstrm.close();		
			logger.info("1. 요청 URL["+ targetUrl + "]");
			returnCd = connection.getResponseCode();
			
	
		} catch (ConnectException ex) {
			if (connection !=null) {
				try {
					returnMsg = "[" + connection.getURL().toString() + "] 연결 실패("+connection.getResponseCode()+")";
					logger.error("******" + returnMsg + "*******");
				} catch (IOException e) {

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("**** 요청 처리 실패" + e);
		} finally {
			if(opstrm != null) {
				try {
					opstrm.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				connection.disconnect();
			}
		}
		
		logger.info("2. 요청 처리 결과  ["+returnCd+"]["+returnMsg+"]");
		return new ReturnClass(cd_result, "요청을 처리하였습니다." , returnMsg);
	}
}
