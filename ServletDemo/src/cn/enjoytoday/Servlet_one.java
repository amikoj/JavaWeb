package cn.enjoytoday;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author hfcai
 *@date 2017/8/7
 *继承HttpServlet实现Servlet
 *
 */
public class Servlet_one extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("servlet_one init.");
		getServletContext().setAttribute("init-context", this);
	}
	
	
	/**
	 * get方法回调
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("doGet,and request url:"+req.getRemoteAddr());

        readString(req.getReader());
        getInitParmas();
    	response(resp.getWriter(), "Servlet_one doGet");
		
	}
	
	
	
	/**
	 * post 方法回调
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost,and request url:"+req.getRemoteAddr());
		getInitParmas();
		readString(req.getReader());
		response(resp.getWriter(), "Servlet_one doPost");
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
