package cn.enjoytoday.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

public class UploadServlet implements Servlet {

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("UploadServlet inited.");
		

	}

	@Override
	public void service(byte[] buffer, OutputStream outputStream) throws Exception {
		// TODO Auto-generated method stub
		
		String request=new String(buffer);
		
		int startpos=0;
		if (request.indexOf("\r\n")!=-1) {
			startpos=request.indexOf("\r\n");
		}else if (request.indexOf("\n")!=-1) {
			startpos=request.indexOf("\n");
		}else  if(request.indexOf("\r")!=-1){
			startpos=	request.indexOf("\r");
		}else {
			System.out.println("can\'t find \\n or \\r,request error.");
			return;
		}
		
	
		String header=request.substring(startpos+2,request.indexOf("\r\n\r\n"));
		
		BufferedReader reader=new BufferedReader(new StringReader(header));
		String data=null;
		String boundary=null;
		
		while ((data=reader.readLine())!=null) {
			if (data.indexOf("Content-Type")!=-1) {
				boundary=data.substring(data.indexOf("boundary=")+9, data.length())+"\r\n";
				break;
			}
		}
		
		if (boundary==null) {
			String info="upload error!";
			outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
			outputStream.write("Content-Type:text/html\r\n\r\n".getBytes());
			outputStream.write(("<html><head><title>servlet</title><body><h1>"+info+"</h1></body></html>").getBytes());
			return;
		}
		
		int index1OfBoundary=request.indexOf(boundary);
		
		int index2OfBoundary=request.indexOf(boundary,index1OfBoundary+boundary.length());
		
		int index3OfBoundary=request.indexOf(boundary,index2OfBoundary+boundary.length());
		
		int beforeOfFilePart=request.indexOf("\r\n\r\n",index2OfBoundary)+3;
		
		int afterOfFilePart=index3OfBoundary-4;
		
		int afterOfFilePartLine1=request.indexOf("\r\n",index2OfBoundary+boundary.length());
		
		String header2OfFilePart=request.substring(index2OfBoundary+boundary.length(),afterOfFilePartLine1);
		
		String fileName=header2OfFilePart.substring(header2OfFilePart.lastIndexOf("\\")+1,header2OfFilePart.length()-1);
		
		int len1=request.substring(0,beforeOfFilePart+1).getBytes().length;
		
		int len2=request.substring(afterOfFilePart,request.length()).getBytes().length;
		
		int fileLen=buffer.length-len1-len2;
		
		FileOutputStream fileOutputStream=new FileOutputStream("/home/hfcai/Projects/javaWeb/"+fileName);
		fileOutputStream.write(buffer,len1,fileLen);
		fileOutputStream.flush();
		fileOutputStream.close();
		System.out.println("file close");
		
		
		String info="upload"+fileName+" success!";
		outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
		outputStream.write("Content-Type:text/html\r\n\r\n".getBytes());
		outputStream.write(("<html><head><title>servlet</title><body><h1>"+info+"</h1></body></html>").getBytes());
	

	}

}
