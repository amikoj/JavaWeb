<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jsp标签 </title>
</head>

    
  <frameset rows="15%,*">
     <frame src="/JspDemo/head.jsp">
      <frameset cols="150,*">
      <frame src="/JspDemo/type.jsp"/> 
      <frame src="/JspDemo/content.jsp?tag=jsp" name="showframe"/> 
      </frameset>
       <noframes>
         <body>
        <p>对不起，您的浏览器不支持“框架”！</p> 
        </body>
</noframes>

  </frameset>

</html>