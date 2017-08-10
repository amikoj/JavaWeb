package cn.enjoytoday;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author hfcai
 *
 */
public class Servlet_two extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		/**
		 * handler from service
		 */
		readString(req.getReader());
		getInitParmas();
		response(res.getWriter(), "request success!");
		
		
		ServletContext context=getServletContext();
		
		

	}
	
	

	
	private void  getInitParmas() {
		 Enumeration<String>names=	getInitParameterNames();
		 while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			System.out.println("initParam name:"+name+",and value is:"+getInitParameter(name));
			
		}
		
	}
	
	
	private void readString(BufferedReader bufferedReader) throws IOException {

		String line=null;
		StringBuffer stringBuffer=new StringBuffer();
		while( (line=bufferedReader.readLine())!=null) {
			stringBuffer.append(line+"\n");
		}
		System.out.println("get request String:"+stringBuffer.toString());
	}
	
	
	
	private void  response(PrintWriter writer,String content) {
		writer.append(content).flush();
	}
	
	


}
