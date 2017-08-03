package cn.enjoytoday;

import java.io.BufferedReader;
import java.io.File;
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
		} catch (Exception e) {
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
	
	
	
	
	
	public static void handlerSocket(Socket socket) {
		System.out.println("get handler client socket");
		
		try{
		InputStream inputStream=socket.getInputStream();
		Thread.sleep(500);
		int size=inputStream.available();
		byte[] buffer=new byte[size];
		inputStream.read(buffer);
		String request=new String(buffer);
		System.out.println(request);
		
		String firstLineofRequest=request.substring(0,request.indexOf("\r\n"));
		String[] parts=firstLineofRequest.split(" ");
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
		
		String firstLine="HTTP/1.1 200 OK\r\n";
		String responseHeader="Content-Type:"+contentType+"\r\n\r\n";
		InputStream in=HttpServer.class.getResourceAsStream("root"+File.separator+uri);
		OutputStream outputStream=socket.getOutputStream();
		outputStream.write(firstLine.getBytes());
		outputStream.write(responseHeader.getBytes());
		int len=0;
		buffer=new byte[128];
		if (in!=null) {
			while ((len=in.read(buffer))!=-1) {
				outputStream.write(buffer,0,len);			
			}
		}
		Thread.sleep(1000);
		in.close();
		outputStream.close();
		inputStream.close();
		socket.close();
		}catch (Exception e) {
	   	e.printStackTrace();
		}

//		try {
//			InputStreamReader inputStreamReader=new InputStreamReader(socket.getInputStream());
//			BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
//			String line="";
//			StringBuffer stringBuffer=new StringBuffer();
//			List<String> params=new CopyOnWriteArrayList();
//			while (!(line=bufferedReader.readLine()).trim().equals("EOF")) {
//				stringBuffer.append(line);
//				params.add(line);
//				System.out.println(line);
//			}
//		
//			System.out.println("read over.");
//
//			String[] parts=params.get(0).split(" ");
//			String uri=parts[1];
//			String contentType;
//			if (uri.indexOf("html") !=-1 || uri.indexOf("htm")!=-1){
//				contentType="text/html";
//			}else if(uri.indexOf("jpg")!=-1 || uri.indexOf("jpeg")!=-1) {
//				contentType="image/jpeg";
//			}else if(uri.indexOf("gif")!=-1){
//				contentType="image/gif";
//			}else {
//				contentType="application/octet-stream";
//			}
//			
//			
//			String firstLine="HTTP/1.1 200 OK\r\n";
//			String responseHeader="Content-Type:"+contentType+"\r\n\r\n";
//			InputStream inputStream=HttpServer.class.getResourceAsStream("root"+File.separator+uri);	
//			OutputStream outputStream=socket.getOutputStream();
//			outputStream.write(firstLine.getBytes());
//			outputStream.write(responseHeader.getBytes());
//			int len=0;
//			byte[] buffer=new byte[128];
//			while ((len=inputStream.read(buffer))!=-1) {
//				outputStream.write(buffer, 0, len);
//			}
//			
//			inputStream.close();
//			outputStream.close();
//			bufferedReader.close();
//			inputStreamReader.close();
//			Thread.sleep(1000);
//			socket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		
	}
	
	
	
	
	
	
	

}
