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
	/** �ֿ� ���
	 * 1. LCA �޽��� ���� -> ���ŵ� �޽��� ������ ���� -> ���� ���� ->  ���� ��û�ߴ� LCA �ʿ� ���ŵ� ���� ��� ����
	 * 2. ������κ��� ��ǥ ���� ���� -> LCA �ʿ� ������ ���� URL ���·� ��ȯ ->  LCA �ʿ� URL ��û
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
			// Proxy ���� ��Ʈ
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
// Proxy �� ������ Ŭ���̾�Ʈ ó�� �ڵ鷯
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

			// 15 byte : IP , 5 byte  = �� 20byte �� �а�
			String ip_port = readIpPort(clientSocket);

			// ���� readIpPort �� ������ ���ǵ� ������ IP/Port �� �ƴ϶�� ������κ��� ���ŵ� �޽����� �Ǵ��ϰ� ó���Ѵ�.


			if(ip_port == null)
			{
				System.err.println("[ERROR] Fail to read ip port :"+clientSocket);
				socketClose(clientSocket);
				return;
			}


			LogUtil.print("[INFO] proxy ip port : "+ ip_port);
			// ���� 4byte ���̸�ŭ ������ �б�
			byte[] data = readMsg(clientSocket);
			// �ش� ip / port ���� , ���� �����ϸ�  ������ ����
			byte[] recvData = sendAndRecv(ip_port.split(",")[0], Integer.parseInt(ip_port.split(",")[1]), data);


			// ���������� ���� ��û�� �������� ����
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


	/**  15 byte : IP , 5 byte  = �� 20byte �а�   ip , port ���� ��Ʈ������ ����
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
		}// ��� �ð��� ������ SocketTimeoutException�� �߻��Ѵ�.
		catch (SocketTimeoutException ste) {
			ste.printStackTrace();
			isHaveException = true;
		} catch (Exception e) {
			e.printStackTrace();
			isHaveException = true;
		}

		// �õ��Ͽ� ����ȯ ���ܻ�Ȳ�� �߻��ϴ� ��Ȳ
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


	/** ���� 4BYTE ���̸�ŭ ������ �о���̰� ���� ����ó��
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

				// �� ������ ���ű��� ��ŭ ���� Ȯ��
				recvBuffer = new CzByteBuffer(size);
				recvBuffer.append(buffer,0,count);

				buffer = new byte[size-4];
				int readSize =count;
				//����� ���̿� �������̴� �����ϰ� �о�� ��
				while (readSize < size && (count = is.read(buffer)) != -1) {
					readSize+=count;
					LogUtil.print(s.getPort()+"] recv packet read size = " + count);
					recvBuffer.append(buffer,0,count );
				}
				if(recvBuffer != null)
					LogUtil.print(s.getPort()+"] RECV MSG "+ recvBuffer.size() + " byte ["+  new String(recvBuffer.getBuffer()) +"]");
			}
		}// ��� �ð��� ������ SocketTimeoutException�� �߻��Ѵ�.
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


	/** data ��  �Է¹��� ip , port �� �����ϰ� ������� byte[] �� ����
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

		byte[] recvData = null;	// ���Ͻ� ��� ����� ���� ������

		Socket xSocket = null;
		OutputStream out = null;

		try {

			xSocket = new Socket(ip, port);

			// ���� ���ð� Ÿ�Ӿƿ� ����  1��
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

	// ������κ��� ���ŵ� Http �޽��� ���� üũ�ϰ�
	// HTTP �޽����� �´ٸ� LCA �ʿ� redirect ó���Ѵ�.
	// LCA_URL ���� �ý��� ������Ƽ�� �����Ѵ�.
	private String recvStatus(Socket s) throws Exception {
		String line = null;
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			line = reader.readLine();
			while (!line.isEmpty()) {
				System.out.println(s.toString() + ") " + line);

				// http �޽��� ���� üũ  ���� URL ������ �����ؾ��ϴ� ��Ȳ�� ����ٸ� �ϴܿ� URL ���Ͽ� ���� �б� �ڵ带 �߰��ϸ�ȴ�.
				// ������ �������̸� ��Ʈ�� �����Ͽ� ����ϴ°� ����. ����� ������ ���Ͻ� ó���ϴ� port �� , http �޽����� ȥ���ϰ� �ִ� ��Ȳ�̶�
				// ȥ�� �� ������ ������ �ִ�. ���� URL ������ �����ؾ��Ѵٸ� ��Ʈ�� �����ؼ� ����ϴ°��� ��õ�Ѵ�.
				if(line.contains("?")&&line.contains("HTTP/1"))
				{
					String msg = line.substring(line.indexOf("?")+1, line.lastIndexOf("HTTP/1"));
					LogUtil.print("redirect : "+ msg);
					//lca ������ redirect
					redirectReqest(msg);
					break;
				}

				line = reader.readLine();
			}

			// ������ Http�� ����  200 OK ����ó��
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
	/** LCA �ʿ� ��ǥ ����
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

		// LCA �ʿ��� ���� �����ߴٸ�  ������ ����
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



// java �� bytebuffer �뵵�� ����ϴ� Ŭ����
class CzByteBuffer
{
	byte[] buffer = null;
	int recvIndex = 0;

	public CzByteBuffer(int size)
	{
		buffer = new byte[size];
	}

	/** byte append ó��
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

/** system.println ��� �ð� ����� ���� wrapper class
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
