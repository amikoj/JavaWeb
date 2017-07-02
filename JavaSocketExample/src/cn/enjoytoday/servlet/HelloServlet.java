package cn.enjoytoday.servlet;

import java.io.OutputStream;


/**
 * hello Servlet handler.
 * @author hfcai
 *
 */
public class HelloServlet implements Servlet {
	
	@Override
	public void init() throws Exception {
		System.out.println("init HelloServlet.");
	}
	
	@Override
	public void service(byte[] buffer, OutputStream outputStream) throws Exception {
		String request=new String(buffer);
		
		String httpContent=request.substring(0,request.indexOf("\r\n"));
		String[] parts=httpContent.split(" ");
		String method=parts[0];
		String uri=parts[1];
		String username=null;
		
		if (method.equalsIgnoreCase("get") && uri.indexOf("username")!=-1) {
			
			String parameters=uri.substring(uri.indexOf("?"), uri.length());
			parts=parameters.split("&");
			parts=parts[0].split("=");
			username=parts[1];
		}else if(method.equalsIgnoreCase("post")){
			int locate=request.indexOf("\r\n\r\n");
			String content=request.substring(locate+4,request.length());
			if (content.indexOf("username=")!=-1) {
				parts=content.split("&");
				parts=parts[0].split("=");
				username=parts[1];
			}
		}
	 
		
		outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
		outputStream.write("Content-Type:text/html\r\n\r\n".getBytes());
		outputStream.write(("<html><head><title>servlet</title><body><h1>"+username+"</h1></body></html>").getBytes());
//		outputStream.close();
	}

}
