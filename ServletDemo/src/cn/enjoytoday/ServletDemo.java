package cn.enjoytoday;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.UploadContext;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.prism.Graphics;
import com.sun.prism.paint.Gradient;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class ServletDemo extends HttpServlet {
	
	
	
	@Override
	public void init() throws ServletException {
	
	    
	    
	    
	    
	}
	
	
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
	
	/**
	 * create image.
	 */
	private final String CREATE_IMAGE="/image";
	
	

	private final String COOKIE="/cookie";
	
	
	private final String DIR="/dir";
	
	
	
	
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
		}else if (requestUrl.startsWith(CREATE_IMAGE)) {
			createImage(req, resp);
		}else if (requestUrl.startsWith(COOKIE)) {
			handlerCookie(req,resp);
		}else if (requestUrl.startsWith(DIR)) {
			parseContext(req, resp);	
		}
		
		
	}
	
	
	
	
	private void handlerCookie(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Cookie[] cookies=request.getCookies();
		
		Cookie c=null;
		OutputStream outputStream=response.getOutputStream();
		if (cookies==null || cookies.length==0)  {
			outputStream.write("no cookie get".getBytes());
	
		}else {
			for (Cookie cookie:cookies){
				outputStream.write(("\r\ncookie name:"+cookie.getName()).getBytes());
				outputStream.write(("\r\ncookie value:"+cookie.getValue()).getBytes());
				outputStream.write(("\r\ncookie path:"+cookie.getPath()).getBytes());
				outputStream.write(("\r\ncookie max age::"+cookie.getMaxAge()).getBytes());
				if (cookie.getName().trim().equals("username")) {
					c=cookie;
				}
			}
			if (c==null) {
				c=new Cookie("username", "cai");
				c.setMaxAge(60*60);
			}else if (c.getValue().equals("cai")) {
				System.out.println("c is cai");
				c.setValue("hfcai");
			}else if (c.getValue().equals("hfcai")) {
				c.setMaxAge(0);
			}
			response.addCookie(c);
			
		}
		
//		response.addCookie(new Cookie("cookie", "cookie_value"));
		outputStream.flush();
		outputStream.close();
	}
	
	
	
	
	
	private void parseContext(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		PrintWriter printWriter=response.getWriter();
		
    ServletContext context=getServletContext();
	    
	    if (context!=null) {
	    	Enumeration<String> attributes= context.getAttributeNames();
	    	
	    	if (attributes!=null ) {
	    		while (attributes.hasMoreElements()) {
					String name = (String) attributes.nextElement();
					printWriter.append("attribute name is:"+name+"\n");
					printWriter.append("attribute value is:"+context.getAttribute(name)+"\n");
					printWriter.append("\n\n\n");
	    		}
	    		
	    		Enumeration<String> paramters=context.getInitParameterNames();
	    		if (paramters!=null) {
	    			while (paramters.hasMoreElements()) {
						String name = (String) paramters.nextElement();
						printWriter.append("parmas name is:"+name+"\n");
						printWriter.append("paeams value is:"+context.getInitParameter(name)+"\n");
						printWriter.append("\n\n\n");
		    		}
				}
	    		
	    	printWriter.flush();
	    	printWriter.close();
				
			}
	    	
	    	
	    	
	    	
	    	
	    	
			
		}
	    
	}
	
	
	/**
	 * 动态图片
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void createImage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String name=request.getParameter("name");
		if (name==null|| name.trim().length()==0) {
			try {
				response.sendRedirect(getServletName()+"/test");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		
		response.setContentType("image/jpeg");
		int length=name.trim().length();
		int unit_w=20;
		int unit_h=50;
		
		BufferedImage image=new BufferedImage(length*unit_w, unit_h, BufferedImage.TYPE_INT_RGB);
		
		
		java.awt.Graphics graphics=image.getGraphics();
		graphics.setColor(Color.BLUE);
		graphics.fill3DRect(0, 0, unit_w*length, unit_h, false);
	
		Font font=new Font("Courier", Font.BOLD, 18);
		graphics.setFont(font);
		graphics.setColor(Color.white);
		
		for (int i = 0; i < name.length(); i++) {
			graphics.drawString(name.charAt(i)+"", unit_w*i+5, unit_h-8);
			
		}
		
		
		JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(response.getOutputStream());
		encoder.encode(image);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
		
		
		
		
	
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
