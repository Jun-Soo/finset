package com.koscom.util;

import java.util.LinkedHashMap;

public interface Constant {
	
	boolean IS_MOBILE_TEST = true;
	
	String SUCCESS = "00"; // 작업결과 성공
	String FAILED = "01"; // 작업결과 실패
	String TRANS_STATUS_10 = "10";	//정상접수
	String TRANS_STATUS_92 = "92";	//기타오류
	String TRANS_STATUS_93 = "93";	//필수항목누락
	
	long CD_LOAN 		= 10;
	long CD_RECEIPT 	= 20; 
	
	String BM = "10";
	String AI = "20";
	String AG = "60";
	
	// 10 : 성공, 21 : ID오류, 22 : PASSWORD 오류, 91 : 접근불가
	String LOGIN_SUCCESS 		= "10";
	String LOGIN_ID_ERR 		= "21";
	String LOGIN_PASS_ERR 		= "22";
	String LOGIN_ACCESS_ERR 	= "91";
	
	// 시스템 마감 여부 
	public static String IS_SYSTEM_CLOSED ="N";

	String ajaxHeader = "AJAX";
	String NOTNEED = "03"; 		// 작업결과 작업 필요없음
	
	int SESSION_MAX_PERIOD = 60 * 60 * 24 * 31;
	int SESSION_MAX_PERIOD_NOT_LOGIN = 60 * 60 * 2;
	String NOT_AUTH_PAGE = "/comm/notAuth";
	String JSON_VIEW = "jsonView";
	String LOCAL = "LOCAL";
	String FCM_TOPIC_ANDROID = "/topics/koscom";
	String FCM_TOPIC_IOS = "/topics/koscom_ios";
	String FCM_SERVER_KEY = "key=AAAAl-PTgOk:APA91bEBPEbDpz-eaOwZB5Zjh3zm7C0HBl4_dwcYeDK2wEmzznOh19251dTpEEf1Deoh8Ktv_W-qsjykrUeZ7TxR99U8xwxsmDaHnlebQds12c9crmksOlyMhJrbTs1fIFCV3M_d_tIn";
}
