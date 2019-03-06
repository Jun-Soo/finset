package com.koscom.util;

public class SmsUtil {
	
	public static String getResultCodeSK(String code) throws Exception {
		String result = "";
		if (code.equals("1000")) {
			result = "G000"; // 성공
		} else if (code.equals("2002")) {
			result = "G019"; // 번호오류
		} else if (code.equals("2003")) {
			result = "G007"; // 실패(비가입/결번/중지)
		} else {
			result = "G103"; // 기타오류
		}
		
		return result;
	}
	
	public static String getResultCodeLG(String code) throws Exception {
		String result = "";
		if (code.equals("00")) {
			result = "X000"; // 결과수신대기
		} else if (code.equals("05") || code.equals("21") || code.equals("2001")) {
			result = "G019"; // 번호오류
		} else if (code.equals("06") || code.equals("1000")) {
			result = "G000"; // 성공
		} else if (code.equals("07") || code.equals("4001")) {
			result = "G007"; // 실패(비가입/결번/중지)
		} else if (code.equals("08") || code.equals("3004")) {
			result = "G008"; // 실패(전원OFF)
		} else if (code.equals("09") || code.equals("3005")) {
			result = "G009"; // 실패(음영)
		} else if (code.equals("10") || code.equals("3001")) {
			result = "G010"; // 실패(메시지FULL)
		} else if (code.equals("11") || code.equals("14") || code.equals("4002")) {
			result = "G011"; // 실패(통신사처리실패)
		} else if (code.equals("22")) {
			result = "G001"; // 국번오류
		} else if (code.equals("23") || code.equals("40")) {
			result = "G016"; // 실패(수신거부)
		} else if (code.equals("80") || code.equals("81")) {
			result = "G007"; // 실패(비가입/결번/중지)
		} else if (code.equals("84")) {
			result = "G011"; // 실패(통신사처리실패)
		} else {
			result = "G103"; // 기타오류
		}
		
		return result;
	}

}
