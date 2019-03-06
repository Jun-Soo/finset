package com.koscom.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.person.model.PersonVO;

public class FcmUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FcmUtil.class);
	
	private static FcmUtil _fcmUtil;
	
	static {
		if(_fcmUtil == null)
			_fcmUtil = new FcmUtil();
	}
	
	public static FcmUtil getFcmUtil() {
		return _fcmUtil;
	}

	/**
	 * FCM 메세지 전송
	 * @param pSendTo: Constant.FCM_TOPIC : 전체, 개인 보낼때는 token 값
	 * @param title : 제목
	 * @param msg   : 내용
	 * return true: 성공, false: 실패
	 */
	@SuppressWarnings("unchecked")
	public static boolean sendFcm(String pSendTo, String title, String msg , String os , String type) {
		String sendTo = pSendTo;
		if(StringUtil.isEmpty(sendTo)) {
			if(os.equals("1")){
				sendTo = Constant.FCM_TOPIC_ANDROID;
			}else{
				sendTo = Constant.FCM_TOPIC_IOS;
			}
		} 
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("https://fcm.googleapis.com/fcm/send");
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", Constant.FCM_SERVER_KEY);
		JSONObject message = new JSONObject();
		message.put("to", sendTo);
		message.put("priority", "high");

		JSONObject notification = new JSONObject();
		notification.put("title", title);
		notification.put("body", msg);
		notification.put("type", type);
		

		if(os.equals("1")){
			//안드로이드 인 경우
			JSONObject data = new JSONObject();
			data.put("message", notification); 
			message.put("data", data);
			} else {
			//IOS인경우
			message.put("notification", notification);
			}
		
//		message.put("notification", notification);
		logger.debug(">>> FCM JSON message       : " + message.toString());
		post.setEntity(new StringEntity(message.toString(), "UTF-8"));
		try {
			HttpResponse response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			String responseBody = EntityUtils.toString(response.getEntity());
			logger.debug(">>> FCM sendTo       : " + sendTo);
			logger.debug(">>> FCM statusCode   : " + statusCode);
			logger.debug(">>> FCM responseBody : " + responseBody); 	// json 오브젝트 messageid가 담겨져 옴
			if(HttpStatus.SC_OK == statusCode) {
				return true;
			} else {
				return false;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			 client = null;
		}
		return false;
	}
	
	/**
	 * 테스트용 
	 * @param args
	 */
//	public static void main(String [ ] args) {
//		sendFcm("/topics/koscom", "test1", "테스트입니다.");
//	}
	
	

}
