package com.koscom.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 소켓 관련 유틸
 *
 * @author dhkim
 *
 */
public class SocketUtil {

	private static final Logger log = LoggerFactory.getLogger(CodeUtil.class);

	// 동기화 처리를 위한 시간
	private int TIME_OUT_SEC = 60;

	public SocketUtil() {
		// 메시지 전송후 디폴트로 60초 대기한다.
		TIME_OUT_SEC = 60;
	}

	private int MAX_SIZE = 20480;					// recv 버퍼 최대 크기
	private byte[] recvBuffer = null;	// recv 버퍼
	private int	recvIndex = 0;						// recv 시작인덱스

	/**
	 * 문자열을 전송하고 해당 소켓으로 응답을 받는다.
	 *
	 * @param ip
	 *            대상 IP
	 * @param port
	 *            대상 PORT
	 * @param data
	 *            메시지 내용
	 * @param headsize
	 *            응답받을 문자열 헤더 길이
	 * @return
	 */
	public String request(String ip, int port, String data, int headsize) {
		Socket xSocket = null;
		OutputStream out = null;
		InputStream is = null;
		String rcvFullData = "";
		recvIndex =0 ;
		recvBuffer = new byte[MAX_SIZE];
		String null_value = "";

		// 동기화 처리를 위한 부분으로 서버에 연결 후 10초의 대기 시간을 설정해 둔다.
		int HEAD_LEN = headsize;

		try {

			// 접속 대상 IP 및 PORT로 소켓을 구성할 수 있다.

			// 생성자 내에서 Connection까지 자동으로 맺는다.
			xSocket = new Socket(ip, port);

			// 응답 대기시간 타임아웃 설정
			xSocket.setSoTimeout(TIME_OUT_SEC * 1000);

			// 소켓의 outputstream에 데이터를 쓰고 버퍼를 비우자(flush)
			out = xSocket.getOutputStream();
			out.write(data.getBytes("EUC-KR"));	//===> 913
//			out.write(data.getBytes());	//===> 918
//			out.write(data.getBytes("utf-8"));	//===> 913
			out.flush();

			// 응답 메시지 읽기
			is = xSocket.getInputStream();
			int count = 0;

			byte[] buffer = new byte[HEAD_LEN];

			if ((count = is.read(buffer)) != -1) {
				int size = NumberUtil.parseInt(new String(buffer));

				rcvFullData = new String(buffer);
				log.debug("Header 읽은 내용 [" + rcvFullData +"]");

				appendRecvBuffer(buffer,0,count);

				buffer = new byte[size];
				int readSize =0;
				while (readSize < size && (count = is.read(buffer)) != -1) { // 헤더부의 길이만큼 읽음.
					readSize+=count;
					//rcvFullData += StringUtil.getByte2String(buffer, 0, count);
					appendRecvBuffer(buffer,0,count);
				}
			}

		}

		// 대기 시간이 지나면 SocketTimeoutException이 발생한다.
		catch (SocketTimeoutException ste) {
			log.error(ip + ":" + port + " Socket " + TIME_OUT_SEC + "sec Timeout");
			null_value="";
		} catch (IOException e) {
			log.error(ip + ":" + port + " Socket exception");
			LogUtil.error(log, e);
			null_value="";
		}

		// 해당 작업이 끝나면 stream 및 소켓을 닫고 메모리를 해제하자.

		// 이 부분은 클라이언트 포트의 재사용을 위해 반드시 필요하다.
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {

				}
				is = null;
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
				out = null;
			}
			if (xSocket != null) {
				try {
					xSocket.close();
				} catch (IOException e) {
				}
				xSocket = null;
			}
		}

		if(recvIndex > 0)
		{
			byte[] tmp = new byte[recvIndex+1];
			for (int i = 0; i <= recvIndex; i++)
				tmp[i] = recvBuffer[i];

			recvBuffer = tmp;
			return new String(recvBuffer);
		}

		return null_value;
	}

	public int getTIME_OUT_SEC() {
		return TIME_OUT_SEC;
	}

	public void setTIME_OUT_SEC(int time_out_sec) {
		TIME_OUT_SEC = time_out_sec;
	}

	private void appendRecvBuffer(byte[] byteString, int start, int len) {

		// 버퍼 최대 크기를 초과하는경우
		if((recvIndex + len) > MAX_SIZE)
		{
			MAX_SIZE +=MAX_SIZE;
			byte[] tmp = new byte[MAX_SIZE];

			for (int i = 0; i <= recvIndex; i++)
				tmp[i] = recvBuffer[i];

			recvBuffer = tmp;

		}

		for (int i = 0; i < len; i++)
		{
			recvBuffer[recvIndex] = byteString[start + i];
			recvIndex++;
		}
	}


	/**
	 * Name   : bytesToHex
	 * Desc   : binaryData를 hex으로 변환
	 * input  : byte[]
	 * output : (hex)String
	 * Date   : 2017.07.06
	 */
	public static String bytesToHex(byte[] in) {
	    final StringBuilder builder = new StringBuilder();
	    for(byte b : in) {
	        builder.append(String.format("%02x", b));
	    }
	    return builder.toString();
	}

	/**
	 * int형을 byte배열로 바꿈<br>
	 * @param integer
	 * @param order
	 * @return
	 */
	public static byte[] intTobyte(int integer, int size) {

		//ByteOrder order = ByteOrder.LITTLE_ENDIAN;
		ByteOrder order = ByteOrder.BIG_ENDIAN;

		ByteBuffer buff = ByteBuffer.allocate(size);
		buff.order(order);

		// 인수로 넘어온 integer을 putInt로설정
		buff.putInt(integer);

		log.debug("intTobyte : " + buff);
		return buff.array();
	}

	public static byte[] setStringToByte(String pValue, int bLength){
		String value = pValue;
		byte[] bArrVal_01 = null;
		byte[] bArrVal_02 = null;

		if(value == null){
			 value = "";
		} else {
			bArrVal_01 = value.getBytes();
			bArrVal_02 = new byte[bLength];

			System.arraycopy(bArrVal_01, 0, bArrVal_02, 0, bArrVal_01.length);
		}

		return bArrVal_02;
	 }

	public static byte[] setObjectToByte(Object pValue, int bLength) {
		Object value = pValue;
		String sResult = null;

		byte[] bArrVal_01 = null;
		byte[] bArrVal_02 = null;

		/*
		if( StringUtil.isEmpty((String) value) ){
			value = "";
		}*/
		if( value == null ){
			value = "";
		} else {
			if (value instanceof String) {
				//logger.info("value는 String입니다.");
				sResult = (String) value; // String 개체로 형변환(Type Casting)
			}

			if( value instanceof Long ){
//				logger.info("value는 Long입니다.");
				sResult = String.format("%d", value);
			}

			if( value instanceof Integer ){
//				logger.info("value는 Integer입니다.");
				sResult = String.format("%d", value);
			}

			if( value instanceof Double ){
//				logger.info("value는 Double입니다.");
				sResult = String.format("%f", value);
			}

			if( value instanceof Float ){
//				logger.info("value는 Float입니다.");
				sResult = String.format("%f", value);
			}

			if(sResult != null && sResult.getBytes().length > bLength){
				sResult = "";
			}

			bArrVal_01 = sResult.getBytes();
			bArrVal_02 = new byte[bLength];

			System.arraycopy(bArrVal_01, 0, bArrVal_02, 0, bArrVal_01.length);
		}

		return bArrVal_02;
	 }
}
