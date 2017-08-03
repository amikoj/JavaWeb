package mypack;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class DispatcherServlet extends GenericServlet{

/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private String target="/hello.jsp";

public void service(ServletRequest request,ServletResponse response) throws
 ServletException, IOException{
  
     String username=request.getParameter("username");
     String password=request.getParameter("password");
     System.out.println("get servlet request,and username is:"+username+",password:"+password);
     request.setAttribute("USER",username);
     request.setAttribute("PASSWORD",password);
     ServletContext context=getServletContext();
     RequestDispatcher dispather=context.getRequestDispatcher(target);
     dispather.forward(request,response);

}




}
