package mypack;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * 
 * @author hfcai
 * 
 * this is the classes  what implements of Servlet
 *
 */
public class BasicServlet implements Servlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
             /**
              * 
              */
		System.out.println("BasicServlet init");

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		/**
		 * when client request
		 */
		System.out.println("BasicServlet service.");
		
		
		
		res.getWriter().write("<html><body><b>This  is basic serlvet use. </b></body></html>");

	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

		 System.out.println("servlet destory.");
	}

}
