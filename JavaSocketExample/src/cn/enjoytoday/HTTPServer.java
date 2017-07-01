package cn.enjoytoday;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.midi.VoiceStatus;

public class HTTPServer {
	
	public static void main(String[] args) {
		int port=9000;
		if (args!=null && args.length>0 && args[0]!=null && args[0].trim().length()>0) {
			port=Integer.parseInt(args[0]);
		}
		
		ServerSocket serverSocket=null;
		try {
			serverSocket=new ServerSocket(port);
			System.out.printf("[*] Listener success,and address  %s",serverSocket.getLocalSocketAddress().toString());
			
			while (true) {
				Socket socket=serverSocket.accept();
				System.out.printf("[*]Receiver Request,from %s:%d",socket.getInetAddress().getHostAddress(),socket.getPort());
				handl_client(socket);
				
			}
	
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.printf("[*] Listener failed,and address  %s",serverSocket.getLocalSocketAddress().toString());
			try {
				serverSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	private static void handl_client(Socket socket) throws Exception{
		DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
		DataOutputStream outputStream=new DataOutputStream(socket.getOutputStream());
		
		int size=dataInputStream.read();
		byte[] bs=new byte[size];
		dataInputStream.read(bs);
		
		String request=new String(bs);
		System.out.println("[*]Request content is:"+request);
		
		String requestParams=request.substring(0,request.indexOf("\r\n")).split(" ")[1];
		String contentType;
		if (requestParams.indexOf("html") !=-1 || requestParams.indexOf("htm")!=-1){
			contentType="text/html"; 
		}else if(requestParams.indexOf("jpg")!=-1 || requestParams.indexOf("jpeg")!=-1) { 
			contentType="image/jpeg";
		}else if(requestParams.indexOf("gif")!=-1){
			contentType="image/gif"; 
		}else {
			contentType="application/octet-stream";
		}
		
		String firstLine="HTTP/1.1 200 ok\r\n";
		String responseHeader="Content-Type:"+contentType+"\r\n\r\n";
		InputStream in=HttpServer.class.getResourceAsStream("html"+File.separator+requestParams);
		outputStream.write(firstLine.getBytes());
		outputStream.write(responseHeader.getBytes());
		int len=0;
		bs=new byte[128];
		if (in!=null) {
			while ((len=in.read(bs))!=-1) {
				outputStream.write(bs,0,len);			
			}
		}
		in.close();
		outputStream.close();
		dataInputStream.close();
		socket.close();
	}
	
	
	
	
	
	
	
	
	
	

}
