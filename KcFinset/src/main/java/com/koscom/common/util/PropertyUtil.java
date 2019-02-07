package com.koscom.common.util;

import java.io.UnsupportedEncodingException;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * 증권사BIZ - MessageSourceAccessor를 이용하여 프로퍼티를 읽는 Class
 * @author EndFoint 개발팀 김학진
 * @since 2018.08.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2018.08.01 김학진 최초 생성
 *  </pre>
*/

public class PropertyUtil {
    private static MessageSourceAccessor propertyAccessor;

    public static void SetPropertiesAccessor(MessageSourceAccessor propertiesAccessor) {
    	PropertyUtil.propertyAccessor = propertiesAccessor;
    }

    public void setPropertiesAccessor(MessageSourceAccessor propertiesAccessor) {
    	PropertyUtil.SetPropertiesAccessor(propertiesAccessor);
    }

    /**
	 * 프로퍼티 값 조회
	 * 
	 * @param 조회할 프로퍼티 키값
	 * @return 프로퍼티 값
	 */
	public static String get(String key) {
		String msg = "";

		try {
			msg = PropertyUtil.propertyAccessor.getMessage(key);
			msg = ascToKsc(msg);
		} catch (NoSuchMessageException e) {
			e.printStackTrace();
			msg = "";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "";
		}

		return msg;
	}

	/**
	 * 프로퍼티 값 조회
	 * 
	 * @param 조회할 프로퍼티 키값
	 * @param 키가  존재하지 않을 경우 리턴할 기본값
	 * @return 프로퍼티 값
	 */
	public static String get(String key, String defValue) {
		String msg = "";

		try {
			msg = PropertyUtil.propertyAccessor.getMessage(key);
			msg = ascToKsc(msg);
		} catch (NoSuchMessageException e) {
			e.printStackTrace();
			msg = defValue;
		} catch (Exception e) {
			e.printStackTrace();
			msg = defValue;
		}

		return msg;
	}

	/**
	 * int property 값 조회
	 * 
	 * @param 조회할 프로퍼티 키값
	 * @return 프로퍼티 정수 값
	 */
	public static int getInt(String key) {

		int intVal = -1;

		try {
			String msg = PropertyUtil.propertyAccessor.getMessage(key);
			intVal = Integer.parseInt(msg);
		} catch (NoSuchMessageException e) {
			e.printStackTrace();
			intVal = 0;
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
			intVal = 0;
		} catch (Exception e) {
			e.printStackTrace();
			intVal = 0;
		}

		return intVal;
	}


	/**
	 * UTF-8 변환
	 * 
	 * @param 변환할 값
	 * @return 변환 결과값
	 */
	public static String ascToKsc(String value) {
		try {
			String strRet = new String(value.getBytes("8859_1"), "UTF-8");
			return strRet;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
