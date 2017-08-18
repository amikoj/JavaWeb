<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>


Hello world:<%= request.getParameter("name") %>

      <div align="center"  color=“blue”> 
      
     <t1><b>   <font color="blue"  size="4"  face="verdana"  >Jsp标签 </font></b> </t1>
     
     
   <frameset cols="120,*">
      <frame src="/JspDemo/type.jsp"/> 
      <frame src="/JspDemo/content.jsp?tag=page" name="showframe"/> 
 <noframes>
         <body>
        <p>对不起，您的浏览器不支持“框架”！</p>
        
        </body>
</noframes>

  </frameset>
     
</div>

</html>