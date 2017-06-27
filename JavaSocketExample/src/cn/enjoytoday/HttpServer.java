package cn.enjoytoday;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * 
 * @author hfcai
 *http server handler
 */
public class HttpServer {
	
	public static void main(String[] args) {
		ServerSocket serverSocket=null;
		int port;
		try{
		    port =Integer.parseInt(args[0]);
		}catch (Exception e) {
		   port=8080;
		   System.out.println("default listen the port of 8080.");
		}
		
		try {
			serverSocket=new ServerSocket(port);
			System.out.println("start server to listen "+serverSocket.getLocalPort());
			while (true) {
				Socket socket=serverSocket.accept();
				System.out.println("Receiver Socket from: "+socket.getInetAddress()+" and port is: "+socket.getPort() );
				handlerSocket(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("server socket accept occured error,and close socket");
			try {
				if (serverSocket!=null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	public static void handlerSocket(Socket socket){
		System.out.println("get handler client socket");

		try {
			InputStreamReader inputStreamReader=new InputStreamReader(socket.getInputStream());
			BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
			
			String line="";
			StringBuffer stringBuffer=new StringBuffer();
			List<String> params=new CopyOnWriteArrayList();
			while ((line=bufferedReader.readLine())!=null) {
				stringBuffer.append(line);
				params.add(line);
			}
			System.out.println("Received message from client is:"+stringBuffer.toString());
			
			String[] parts=params.get(0).split(" ");
			String uri=parts[1];
			
			
			String contentType;
			if (uri.indexOf("html") !=-1 || uri.indexOf("htm")!=-1){
				contentType="text/html";
			}else if(uri.indexOf("jpg")!=-1 || uri.indexOf("jpeg")!=-1) {
				contentType="image/jpeg";
			}else if(uri.indexOf("gif")!=-1){
				contentType="image/gif";
			}else {
				contentType="application/octet-stream";
			}
			
			
			String firstLine="HTTP/1.1 200 ok\r\n";
			String responseHeader="Content-Type:"+contentType+"\r\n\r\n";
			InputStream inputStream=HttpServer.class.getResourceAsStream("root/"+uri);	
			OutputStream outputStream=socket.getOutputStream();
			outputStream.write(firstLine.getBytes());
			outputStream.write(responseHeader.getBytes());
			int len=0;
			byte[] buffer=new byte[128];
			while ((len=inputStream.read(buffer))!=-1) {
				outputStream.write(buffer, 0, len);
			}
		
			Thread.sleep(500);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	

}
