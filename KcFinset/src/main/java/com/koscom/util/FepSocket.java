package com.koscom.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FEP 프록시 통신용 소켓 유틸
 *
 * @author dhkim
 *
 */
public class FepSocket {

	private static final Logger log = LoggerFactory.getLogger(FepSocket.class);

	// 동기화 처리를 위한 시간 
	private int TIME_OUT_SEC = 60*5;	//페퍼 연동 시 응답이 2분이 넘어가는 경우가 종종 생김.	
	private int HEAD_LEN = 4;

	public FepSocket() {
		// 메시지 전송후 디폴트로 60초 대기한다.
		TIME_OUT_SEC = 60*5;
	}

	private int MAX_SIZE = 20480;		// recv 버퍼 최대 크기
	private byte[] recvBuffer = null;	// recv 버퍼
	private int	recvIndex = 0;			// recv 시작인덱스

	/**
	 * 문자열을 전송하고 해당 소켓으로 응답을 받는다.
	 *
	 * @param ip
	 *            대상 IP
	 * @param port
	 *            대상 PORT
	 * @param data
	 *            메시지
	 * @return
	 */
	public byte[] request(String ip, int port, byte[] data) throws IOException {
		Socket xSocket = null;
		OutputStream out = null;
		InputStream is = null;
		String rcvFullData = "";
		recvIndex =0 ;
		recvBuffer = new byte[MAX_SIZE];

		// 동기화 처리를 위한 부분으로 서버에 연결 후 10초의 대기 시간을 설정해 둔다.
		log.debug("fep send byte size [" + data.length +"]"); 
		try {

			// 접속 대상 IP 및 PORT로 소켓을 구성할 수 있다.

			// 생성자 내에서 Connection까지 자동으로 맺는다.
			xSocket = new Socket(ip, port);

			// 응답 대기시간 타임아웃 설정
			xSocket.setSoTimeout(TIME_OUT_SEC * 1000);

			out = xSocket.getOutputStream();
			out.write(data);
			out.flush();

			// 응답 메시지 읽기
			is = xSocket.getInputStream();
			int count = 0;

			byte[] buffer = new byte[HEAD_LEN];

			if ((count = is.read(buffer)) != -1) {
				int size = NumberUtil.parseInt(new String(buffer));

				rcvFullData = new String(buffer);
				log.debug("fep will be received byte size [" + rcvFullData +"]");

				appendRecvBuffer(buffer,0,count);

				// 전문 사이즈에서 전문길이 부분 빼기
				size -= HEAD_LEN;
				buffer = new byte[size];
				int readSize =0;
				while (readSize < size && (count = is.read(buffer)) != -1) { // 헤더부의 길이만큼 읽음.
					readSize+=count;
					appendRecvBuffer(buffer,0,count);
				}
				log.debug("fep receivd byte size [" + readSize +"]");
			}
		}

		// 대기 시간이 지나면 SocketTimeoutException이 발생한다.
		catch (SocketTimeoutException ste) {
			String message = ip + ":" + port + " Socket " + TIME_OUT_SEC + "sec Timeout";
			LogUtil.error(log, message);
			throw ste;
		} catch (IOException e) {
            String message = ip + ":" + port + " Socket " + TIME_OUT_SEC + "sec Timeout";
            LogUtil.error(log, message);
            throw e;
        }
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
			return recvBuffer;
		}

		return null;
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
}

