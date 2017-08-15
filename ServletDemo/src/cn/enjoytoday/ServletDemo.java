package cn.enjoytoday;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.UploadContext;

public class ServletDemo extends HttpServlet {
	
	
	/**
	 * 主页请求
	 */
	private final String BASIC_URL="/test";
	
	
	/**
	 * 文件下载请求
	 */
	private final String DOWNLOAD_URL=BASIC_URL+"/download";
	/**
	 * 文件下载请求
	 */
	private final String UPLOAD_URL=BASIC_URL+"/upload";
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUrl=req.getRequestURL().toString();
		System.out.println("request url:"+requestUrl);
		if (requestUrl==null) {
			 throw new ServletException("request url is invaild.");
		}
	
		if (requestUrl.equals(BASIC_URL)) {
			handlerBasic(req,resp);
		}else if(requestUrl.startsWith(DOWNLOAD_URL)) {
			download(req,resp);
		}else if(requestUrl.startsWith(UPLOAD_URL)){
			upload(req, resp);
		}
		
		
	}
	
	
	
	
	/**
	 * 首页请求
	 */
	private void handlerBasic(HttpServletRequest req, HttpServletResponse resp){
		try {
			resp.sendRedirect("test.html");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("redirect error:"+e.getMessage());
		}
		
		
		
	}
	
	
	
	/**
	 * 文件下载管理
	 */
	private void download(HttpServletRequest req, HttpServletResponse resp){
		
		
	}
	
	
	/**
	 * 文件上传
	 * @param request
	 * @param resp
	 */
	private void upload(HttpServletRequest request,HttpServletResponse resp){
		
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
