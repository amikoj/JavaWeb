package cn.enjoytoday.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class HttpServer {
	
	private static Map servletMap=new ConcurrentHashMap<>();

	
	public static void main(String[] args) {
		int port=8080;
		if (args!=null && args.length>0 &&  args[0].trim().length()>0) {
			port=Integer.parseInt(args[0]);
		}
		
		ServerSocket serverSocket=null;
		try {
		   serverSocket=new ServerSocket(port);
			System.out.printf("[*] Start Server,server address:%s", serverSocket.getLocalSocketAddress().toString());
			System.out.println("");
			
			while (true) {
				Socket socket=serverSocket.accept();
				System.out.printf("[*] Receiver request %s:%d", socket.getInetAddress().getHostAddress(),socket.getPort());
				System.out.println("");
				handlerClient(socket);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("[*] Start Server failed,server address:%s", serverSocket.getLocalSocketAddress().toString());
			System.out.println("");
			try {
				serverSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	
	
	
	
	/**
	 * Receiver from client.
	 * @param socket
	 * @throws IOException 
	 */
	private static void handlerClient(Socket socket) throws Exception{
		
		InputStream inputStream=socket.getInputStream();
		OutputStream outputStream=socket.getOutputStream();
		
		Thread.sleep(1000);
		
		int size=inputStream.available();
		byte[] bs=new byte[size];
		inputStream.read(bs);
		String request=new String(bs);
		System.out.println(request);
		
		
		int lineover;
		if (request.indexOf("\r\n")!=-1) {
			lineover=request.indexOf("\r\n");
		}else if (request.indexOf("\n")!=-1) {
			lineover=request.indexOf("\n");
		}else  if(request.indexOf("\r")!=-1){
		  lineover=	request.indexOf("\r");
		}else {
			System.out.println("can\'t find \\n or \\r,request error.");
			return;
		}
		String httpContent=request.substring(0,lineover);
		
		String[] units=httpContent.split(" ");
		String uri=units[1];
		
		if (uri.indexOf("servlet")!=-1) {
			String servletName=null;
			if (uri.indexOf("?")!=-1) {
				servletName=uri.substring(uri.indexOf("servlet/")+8,uri.indexOf("?"));
			}else {
				servletName=uri.substring(uri.indexOf("servlet/")+8,uri.length());
			}
			
			Servlet servlet=(Servlet) servletMap.get(servletName);
			System.out.println("servletName:"+servletName);
			
			if (servlet==null) {
				servlet=(Servlet) Class.forName("cn.enjoytoday.servlet."+servletName).newInstance();
				servlet.init();
				servletMap.put(servletName, servlet);
			}
			servlet.service(bs, outputStream);
			outputStream.close();
			inputStream.close();
			socket.close();
			return;
		}
		
		
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
		InputStream in=HttpServer.class.getResourceAsStream("../root"+File.separator+uri);
		outputStream.write(firstLine.getBytes());
		outputStream.write(responseHeader.getBytes());
		int len=0;
		bs=new byte[128];
		if (in!=null) {
			while ((len=in.read(bs))!=-1) {
				outputStream.write(bs,0,len);			
			}
		}
		Thread.sleep(1000);
		in.close();
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
