import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ProxyServer {
	public static int cntProxySvr = 0;
	/** 주요 기능
	 * 1. LCA 메시지 수신 -> 수신된 메시지 금융사 전달 -> 응답 수신 ->  최초 요청했던 LCA 쪽에 수신된 전문 결과 전달
	 * 2. 금융사로부터 기표 상태 수신 -> LCA 쪽에 전달을 위해 URL 형태로 변환 ->  LCA 쪽에 URL 요청
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		ServerSocket server = null;
		Socket sock = null;
		ThreadProxy proxy = null;
        ThreadLog   log = null;
        if ( args.length < 1 ) {
			System.err.println("Usage: java ProxyServer <localport>");
			System.exit(0);
		}

		try {
			// Proxy 서버 포트
			int localport = Integer.parseInt(args[0]);
			LogUtil.print("============================== Proxy Server Start, Listen Port : "+ localport);
			server = new ServerSocket(localport);
			while (true) {
				sock = server.accept();
				proxy = new ThreadProxy(sock);
                log = new ThreadLog(proxy);
			}
		} catch (Exception e) {
			System.err.println("Usage: java ProxyServer <localport>");
			System.err.println(e);
		}
	}
}
class ThreadLog extends Thread {
    ThreadProxy threadProxy;
    int logCount = 0;
    public ThreadLog(ThreadProxy threadProxy) {
        this.threadProxy = threadProxy;
        this.start();
    }

    public void run(){

        while(threadProxy.isRunning == true) {
            logCount++;
            if(logCount % 10 == 0) {
                System.out.println("Thread : no="+threadProxy.indexProxy+",time="+logCount);
            }
            try {
                Thread.sleep(1000);
            } catch (java.lang.Exception exception) {
                exception.printStackTrace();
                break;
            }
        }
        System.out.println("Thread : end no="+threadProxy.indexProxy+",time="+logCount);
    }
}
// Proxy 에 접속한 클라이언트 처리 핸들러
class ThreadProxy extends Thread {
	private Socket clientSocket;
	public int indexProxy;
	public boolean isRunning = false;
	ThreadProxy(Socket sock) {
		indexProxy = ProxyServer.cntProxySvr++;
		this.clientSocket = sock;
		this.start();
	}
	@Override
	public void run() {
		try {
            isRunning = true;
			LogUtil.print("=================================");
			LogUtil.print("================ThreadProxy:"+indexProxy+"=================");
			LogUtil.print("[INFO] connect! "+ clientSocket);

			// 15 byte : IP , 5 byte  = 총 20byte 를 읽고
			String ip_port = readIpPort(clientSocket);

			// 만약 readIpPort 에 사전에 정의된 형태의 IP/Port 가 아니라면 금융사로부터 수신된 메시지라 판단하고 처리한다.


			if(ip_port == null)
			{
				System.err.println("[ERROR] Fail to read ip port :"+clientSocket);
				socketClose(clientSocket);
				return;
			}


			LogUtil.print("[INFO] proxy ip port : "+ ip_port);
			// 다음 4byte 길이만큼 데이터 읽기
			byte[] data = readMsg(clientSocket);
			// 해당 ip / port 접속 , 접속 성공하면  데이터 전송
			byte[] recvData = sendAndRecv(ip_port.split(",")[0], Integer.parseInt(ip_port.split(",")[1]), data);


			// 응답전문은 최초 요청한 소켓으로 리턴
			if(recvData != null)
			{
				OutputStream out = clientSocket.getOutputStream();
				out.write(recvData);
				out.flush();
			}else
			{
				System.err.println("[ERROR] Fail to recv data, proxy end");
			}

			LogUtil.print("[INFO] proxy delivery END  : "+ ip_port);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			LogUtil.print("[INFO] Socket close : "+clientSocket);
			socketClose(clientSocket);
			LogUtil.print("================ThreadProxy:"+indexProxy+"=================");
			LogUtil.print("=================================");
            isRunning = false;
        }
	}

	public void socketClose(Socket s)
	{
		if (s != null) {
			try {
				s.close();
			} catch (IOException e) {
			}
			s = null;
		}
	}


	/**  15 byte : IP , 5 byte  = 총 20byte 읽고   ip , port 형태 스트링으로 리턴
	 * @param s
	 * @return
	 * @throws Exception
	 */
	private String readIpPort(Socket s) throws Exception {
		String ip = "";
		String port = "";
		String result = null;
		LogUtil.print("==============readIpPort:"+indexProxy+"============");
		LogUtil.print("readIpPort : Socket="+ s);

		InputStream is = s.getInputStream();
		boolean isHaveException = false;
		try {

			byte[] buffer = new byte[15];

			if(is.read(buffer) != -1) {
				ip= new String(buffer).trim();
			}
			LogUtil.print("readIpPort : ip="+ ip);
			buffer = new byte[5];
			if(is.read(buffer) != -1) {
				port= new String(buffer).trim();
			}
			LogUtil.print("readIpPort : port="+ port);
		}// 대기 시간이 지나면 SocketTimeoutException이 발생한다.
		catch (SocketTimeoutException ste) {
			ste.printStackTrace();
			isHaveException = true;
		} catch (Exception e) {
			e.printStackTrace();
			isHaveException = true;
		}

		// 시도하여 형변환 예외상황이 발생하는 상황
		try {
			Integer.parseInt(port);
		} catch (Exception e) {
			System.out.println(ip+port);
			recvStatus(s);
			isHaveException = true;
		}
		result = (isHaveException == false)? ip+","+port : null;
		LogUtil.print("readIpPort : result="+ result);
		LogUtil.print("==============readIpPort:"+indexProxy+"========");
		return result;
	}


	/** 최초 4BYTE 길이만큼 전문을 읽어들이고 이후 응답처리
	 * @param s
	 * @return
	 * @throws Exception
	 */
	private byte[] readMsg(Socket s) throws Exception{

		LogUtil.print("==============readMsg:"+indexProxy+"=============");
		LogUtil.print("readMsg : Socket="+ s);
		CzByteBuffer recvBuffer = null;

		InputStream is = s.getInputStream();
		int count = 0;
		byte[] result = null;
		boolean isHaveException = false;
		try {

			byte[] buffer = new byte[4];

			if((count = is.read(buffer)) != -1) {

				int size = Integer.parseInt(new String(buffer).trim());

				System.out.println("["+s.getPort()+"] Total length["+ size +"]");

				// 총 데이터 수신길이 만큼 버퍼 확보
				recvBuffer = new CzByteBuffer(size);
				recvBuffer.append(buffer,0,count);

				buffer = new byte[size-4];
				int readSize =count;
				//헤더부 길이에 전문길이는 제외하고 읽어야 됨
				while (readSize < size && (count = is.read(buffer)) != -1) {
					readSize+=count;
					LogUtil.print(s.getPort()+"] recv packet read size = " + count);
					recvBuffer.append(buffer,0,count );
				}
				if(recvBuffer != null)
					LogUtil.print(s.getPort()+"] RECV MSG "+ recvBuffer.size() + " byte ["+  new String(recvBuffer.getBuffer()) +"]");
			}
		}// 대기 시간이 지나면 SocketTimeoutException이 발생한다.
		catch (SocketTimeoutException ste) {
			ste.printStackTrace();
			isHaveException = true;
//			return null;
		} catch (Exception e) {
			e.printStackTrace();
			isHaveException = true;
//			return null;
		}
		if(isHaveException == false) {
			result = (recvBuffer != null)?recvBuffer.getBuffer() : null;
		}
		LogUtil.print("==============readMsg:"+indexProxy+"=============");

		return result;
	}


	/** data 를  입력받은 ip , port 로 전달하고 응답받은 byte[] 를 리턴
	 * @param ip
	 * @param port
	 * @param data
	 * @return
	 */
	private byte[] sendAndRecv(String ip,int port, byte[] data) {

		LogUtil.print("==============sendAndRecv=============");
		LogUtil.print("sendAndRecv:ip  ="+ip  );
		LogUtil.print("sendAndRecv:port="+port);
		LogUtil.print("sendAndRecv:data="+data);
		if(data == null) return null;

		byte[] recvData = null;	// 프록시 통신 결과로 받은 데이터

		Socket xSocket = null;
		OutputStream out = null;

		try {

			xSocket = new Socket(ip, port);

			// 응답 대기시간 타임아웃 설정  1분
			xSocket.setSoTimeout(2000000);

			out = xSocket.getOutputStream();
			out.write(data);
			out.flush();

			recvData = readMsg(xSocket);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			socketClose(xSocket);
		}
		LogUtil.print("sendAndRecv:recvData="+recvData);
		LogUtil.print("==============sendAndRecv=============");

		return recvData;

	}

	// 금융사로부터 수신된 Http 메시지 인지 체크하고
	// HTTP 메시지가 맞다면 LCA 쪽에 redirect 처리한다.
	// LCA_URL 값은 시스템 프로퍼티로 정의한다.
	private String recvStatus(Socket s) throws Exception {
		String line = null;
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			line = reader.readLine();
			while (!line.isEmpty()) {
				System.out.println(s.toString() + ") " + line);

				// http 메시지 인지 체크  만약 URL 패턴을 구분해야하는 상황이 생긴다면 하단에 URL 패턴에 의한 분기 코드를 추가하면된다.
				// 하지만 가급적이면 포트를 구분하여 사용하는게 좋다. 현재는 전문을 프록시 처리하는 port 와 , http 메시지를 혼재하고 있는 상황이라
				// 혼돈 및 에러의 여지가 있다. 만약 URL 패턴을 구분해야한다면 포트를 구분해서 사용하는것을 추천한다.
				if(line.contains("?")&&line.contains("HTTP/1"))
				{
					String msg = line.substring(line.indexOf("?")+1, line.lastIndexOf("HTTP/1"));
					LogUtil.print("redirect : "+ msg);
					//lca 쪽으로 redirect
					redirectReqest(msg);
					break;
				}

				line = reader.readLine();
			}

			// 금융사 Http에 대한  200 OK 응답처리
			recv200OK(s);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			isr.close();
			socketClose(s);
		}

		return line;
	}
	/** LCA 쪽에 기표 전달
	 * @param substring
	 * @throws Exception
	 */
	private void redirectReqest(String url) throws Exception {

		String lca_url =  System.getProperty("LCA_URL");

		HttpURLConnection connection = null;

		URL aURL = new URL(lca_url+"?"+url);

		connection = (HttpURLConnection) aURL.openConnection();
		connection.setConnectTimeout(30000); // millisecond//
		connection.setReadTimeout(30000); // millisecond//
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);

		// LCA 쪽에서 정상 수신했다면  데이터 수신
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			LogUtil.print("[OK] Redireted msg ");
		}else
		{
			System.err.println("[ERROR] fail to lca");
		}

	}
	private void recv200OK(Socket s) throws Exception{

		Date today = new Date();

		BufferedWriter out = null;
		try {

			out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			out.write("HTTP/1.0 200 OK\r\");");
			out.write("Date: "+today+"\r\n");
			out.write("Server: FEP/1.0.0\r\n");
			out.write("Content-Type: text/html\r\n");
			out.write("Content-Length: 59\r\n");

			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.add(Calendar.HOUR, 1);
			today = cal.getTime();

			out.write("Expires: "+today+"\r\n");
			out.write("Last-modified: "+today+"\r\n");
			out.write("\r\n");

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			out.close();

		}

	}

}



// java 의 bytebuffer 용도로 사용하는 클래스
class CzByteBuffer
{
	byte[] buffer = null;
	int recvIndex = 0;

	public CzByteBuffer(int size)
	{
		buffer = new byte[size];
	}

	/** byte append 처리
	 * @param byteString
	 * @param start
	 * @param len
	 */
	public void append(byte[] byteString, int start, int len) {

		for (int i = 0; i < len; i++)
		{
			buffer[recvIndex] = byteString[start + i];
			recvIndex++;
		}
	}

	public byte[] getBuffer() {
		return buffer;
	}

	public int size()
	{	if(buffer != null)
		return buffer.length;
		return 0;
	}

}

/** system.println 대신 시간 출력을 위한 wrapper class
 * @author dhkim
 *
 */
class LogUtil
{
	public static void print(String msg)
	{
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss ");
		System.out.println(date.format(today)+" || " + msg );
	}
}
