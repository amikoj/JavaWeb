package cn.enjoytoday;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.UploadContext;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class ServletDemo extends HttpServlet {
	
	
	/**
	 * 主页请求
	 */
	private final String BASIC_URL="/test";
	
	
	/**
	 * 文件下载请求
	 */
	private final String DOWNLOAD_URL="/download";
	/**
	 * 文件下载请求
	 */
	private final String UPLOAD_URL="/upload";
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUrl=req.getPathInfo();
		System.out.println("request url:"+requestUrl);
		
		if (requestUrl==null || requestUrl.equals("/")) {
			handlerBasic(req,resp);
			return;
		}
		if ( requestUrl.endsWith(".html") || requestUrl.endsWith(".htm") || requestUrl.endsWith(".jsp") ) {
			System.out.println("ignore request.");
			return;
		}

        if(requestUrl.startsWith(DOWNLOAD_URL)) {
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
			resp.sendRedirect(req.getContextPath()+"/test.html");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("redirect error:"+e.getMessage());
		}
		
		
		
	}
	
	
	
	/**
	 * 文件下载管理
	 */
	private void download(HttpServletRequest req, HttpServletResponse resp){
		String filename=req.getParameter("filename");
		if (filename==null || filename.trim().equals("")) {
			try {
				resp.getOutputStream().write("Please input filename.".getBytes());
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String path="/download"+File.separator+filename;
		System.out.println("file path is:"+path);
		
		InputStream inputStream=getServletContext().getResourceAsStream(path);
		try {
			int leg=inputStream.available();
			resp.setContentType("application/force-download");
			resp.setContentLength(leg);
			resp.setHeader("Content-Disposition", "attachment;filename=\""+filename+"\"");
			
			OutputStream outputStream=resp.getOutputStream();
			
			int length=0;
			byte[] buffer=new byte[1024];
			
			while ((length=inputStream.read(buffer))!=-1) {
				outputStream.write(buffer,0,length);
			}
			
			inputStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
	/**
	 * 文件上传
	 * @param request
	 * @param resp
	 */
	private void upload(HttpServletRequest request,HttpServletResponse resp){
		String filename=request.getParameter("upload_file");
		
		if (filename==null) {
		    try {
				resp.sendRedirect(request.getContextPath()+"/upload.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return;
		}

		
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUrl=req.getPathInfo();
		System.out.println("request url:"+requestUrl);
		
		if (requestUrl==null || requestUrl.equals("/")) {
			handlerBasic(req,resp);
			return;
		}
		if ( requestUrl.endsWith(".html") || requestUrl.endsWith(".htm") || requestUrl.endsWith(".jsp") ) {
			System.out.println("ignore request.");
			return;
		}

        if(requestUrl.startsWith(DOWNLOAD_URL)) {
			download(req,resp);
		}else if(requestUrl.startsWith(UPLOAD_URL)){
			
			
			DiskFileItemFactory factory=new DiskFileItemFactory();
			factory.setSizeThreshold(4*1024);
			factory.setRepository(new File(getServletContext().getRealPath("tmp")));
			
			
			
			ServletFileUpload upload=new ServletFileUpload(factory);
			
			upload.setSizeMax(10*1024*1024);
			 try {
				java.util.List<FileItem> items=upload.parseRequest(req);
				
				Iterator  iterator=items.iterator(); 
				
				while (iterator.hasNext()) {
				
					FileItem item=(FileItem) iterator.next();
					if (item.isFormField()) {
						//普通表单域
						String name=item.getFieldName();
						String value=item.getString();
						System.out.println("name is:"+name+",and value is:"+value+"\r\n");
						
					}else {
						/**
						 * 上传文件部分
						 */
						String name=item.getName();
						int index=name.lastIndexOf("\\");
						name=name.substring(index+1,name.length());
						long size=item.getSize();
						if (size==0) {
						  	 resp.getOutputStream().write("file is empty , try agin please!".getBytes());
								resp.getOutputStream().flush();
								resp.getOutputStream().close();
							return;
						}
						File uploadFile=new File(getServletContext().getRealPath("user/"+name));
						item.write(uploadFile);
						System.out.println("name is:"+name+",and size is:"+size);
					}
					
				}		
				
			    	 resp.getOutputStream().write("upload success!".getBytes());
					resp.getOutputStream().flush();
					resp.getOutputStream().close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				 resp.getOutputStream().write("upload failed!".getBytes());
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
			}
			 
			 

		
		}
	}

}
