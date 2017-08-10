package cn.enjoytoday;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletContextServlet implements Servlet {
	
	private ServletConfig servletConfig;
	private ServletContext  servletContext;
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("ServletContext init.");
		this.servletContext=config.getServletContext();
		
		handlerServletContext();
		

	}
	
	/**
	 * servletContext使用
	 */
	private void handlerServletContext() {
		
		/**
		 * web.xml添加的init-params参数读取
		 */
		getInitParmas();
 
		/**
		 * web应用内attribute
		 */
		Object  object=servletContext.getAttribute("init-context");
		System.out.println("get global attribute parmas object is :"+object.getClass().getCanonicalName());
		
		/**
		 * web基本信息
		 */
		getBasicInfo();
		
		
		/**
		 * web 应用间通信相关
		 */
		handlerServletTall();
		
		
		
		
	}
	
	
	/**
	 * 获取基本的web 信息
	 */
	private void getBasicInfo(){
		System.out.println("current web app root uri is:"+servletContext.getContextPath());
		System.out.println("current the major version of the Servlet:"+servletContext.getEffectiveMajorVersion());
		System.out.println("current the minor version of the Servlet:"+servletContext.getEffectiveMinorVersion());
		System.out.println(" the name and version of the servlet container:"+servletContext.getServerInfo());
		System.out.println("current web app name is:"+servletContext.getServletContextName());
	
	}
	
	
	/**
	 * web 应用间通信相关
	 */
	private void handlerServletTall(){

	
		
		
		
		
	}
	
	
	
	
	private void  getInitParmas() {
		 Enumeration<String>names=	servletContext.getInitParameterNames();
		 while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			System.out.println("initParam name:"+name+",and value is:"+servletContext.getInitParameter(name));
			
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
	
	
	
	
	
	
	
	

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
