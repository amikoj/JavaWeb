package cn.enjoytoday;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class HttpClient {

	public static void main(String[] args) {
		String uri="index.htm";
		if (args.length!=0){
			uri=args[0];
		}
		doGet("localhost", 8080, uri);
	}
	
	
	/**
	 * 
	 * @param host
	 * @param port
	 * @param uri
	 * http get请求
	 * 
	 */
	public static void doGet(String host,int port,String uri){
		Socket socket=null;
		
		try {
			socket=new Socket(host, port);
			StringBuffer stringBuffer=new StringBuffer("GET " +uri+" HTTP/1.1\r\n");
			stringBuffer.append("Accept:*/*\r\n");
			stringBuffer.append("Accept-Language: zh-cn\r\n");
			stringBuffer.append("Accept-Encoding: gzip,deflate\r\n");
			stringBuffer.append("User-Agent: HTTPClient\r\n");
			stringBuffer.append("Host: localhost:8080\r\n");
			stringBuffer.append("Connection: Keep-Alive\r\n\r\n");
			
			
			OutputStream outputStream=socket.getOutputStream();
			outputStream.write(stringBuffer.toString().getBytes());
			Thread.sleep(2000);
			InputStream inputStream=socket.getInputStream();
			int size=inputStream.available();
			byte[] buffer=new byte[size];
			inputStream.read(buffer);
			System.out.println("get reqeust string:"+new String(buffer));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}finally {
			try {
				if (socket!=null && socket.isConnected()) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}
}
