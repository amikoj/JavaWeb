<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<div align="left" >
  <%
      String 
      tag=request.getParameter("tag");
   if( "jsp".equals(tag)){
    	  //jsp 指令
 %>
   <h3 style="color:#0000ff"> JSP 指令</h3>
     <p>JSP指令是用来设置和整个JSP网页相关的属性，如网页编码和脚本语言等，其基本的语言格式如下:
     </p>
      <pre color="#00ff00">
             <\%@ 指令名 属性=“值” %>
    </pre>
    常用的三种指令位page、include 、taglib:
    <br/>    <br/>
      <li> <b>page指令</b></li><br/>
    <p>  page 指令可以指定所使用的编程语言、jsp编程生成的class所实现的接口，扩展类和引入的包，其语法格式如下:</p>
     <pre color="#00ff00">
             <\%@ page name1=“value1” name2="value2" %>
    </pre>
    
    <table border="1">
    <tr>
      <th>指令属性 </th>
       <th> 描述</th>
       <th> 说明</th>
    </tr>
    
    <tr>
          <td> language</td>
           <td>jsp编译所需语言,目前只支持java,<br/>只有第一次有效 。</td>
            <td>  <\%@ page language="java" % > </td>
    </tr>
    
     <tr>
          <td> method</td>
           <td> 指定java程序片段的运行的对应的java方法名，<br/>默认是service()方法，多次使用时只有第一次有效，<br/>有效值有:service、doGet、doPost</td>
            <td>  <\%@ page method="doGet" %> </td>
    </tr>
        <tr>
          <td>import </td>
           <td>对应java文件中的import命令导入包或者类，该列表用\"",\""分离,<br/>可多次使用 </td>
            <td> <\%@ page import="java.io.*,java.util.*" %> </td>
    </tr>
        <tr>
          <td>content_type </td>
           <td>设置响应的MIME类型，默认位text/html,<br/> 默认字符编码为ISO-8859-1,不支持多次使用</td>
            <td><\%@ page content_type="text/html;charset=UTF-8" %>  </td>
    </tr>
        <tr>
          <td> session</td>
           <td> 指定jsp页面是否支持Session,类型只能为"true"<br/>或者""false",默认为true</td>
            <td><\%@ page session="true" %>  </td>
    </tr>
        <tr>
          <td> errorPage</td>
           <td> 请求异常转发地址</td>
            <td><\%@ page errorPage="error.jsp" %>  </td>
    </tr>
        <tr>
          <td>isErrorPage </td>
           <td> 表示改jsp是否为异常处理页面</td>
            <td><\%@ page isErrorPage="true" %>  </td>
    </tr>

    
    
    </table>
    <br/><br/>
      
      
      <li> <b>include指令</b></li>   <br/>
      <p> jsp通过include指令来导入其他文件,被导入的文件可以为jsp或者html文件，其基本语法格式:</p>
      
      <pre> 
      <\%@ inculde  file="head.jsp" %>  
      </pre>
      
      <p>
       用include导入的其他文件被看成静态包含。
       </p>
       <br/> <br/>
      <li> <b>taglib指令</b></li>    <br/>
      
      <p> 略</p>
      
      <br/> <br/>
   </p>
<% }else if( "scriptlet".equals(tag)){
    	  //java程序片段
%>


<h3 style="color:#0000ff">JAVA 程序片段</h3>



    	   
  <%  }else if("expression".equals(tag)){
    	  //java 表达式
%>
<h3 style="color:#0000ff">JAVA 表达式</h3>




<% }else if( "declare".equals(tag)){
    	  //jsp声明
%>
<h3 style="color:#0000ff">JSP 声明</h3>
<p>
JSP 声明比较简单，主要是用于对于当前jsp映射的访问的servlet对象声明的类的成员变量和方法。其基本格式如下: 
 </p>
 
 <pre style="color:#888">
    <\%! delarce ; [decalaration] %>
 </pre>
 
 
 <p>
 如上，其内部的具体语法和Java中一样，如下给出一个简单的示例演示:
 </p>

 <pre style="color:#888">
    <\%! //成员变量
          String tag="jsp";
          static int number=10;
     %>
         <\%! //方法
         public String  getName(){
               return "hfcai";
         }
      
     %>
     
     
 </pre>


    	   
 <%  }else if("implied".equals(tag)){
    	  //java 隐含对象
 %>
 <h3 style="color:#0000ff">JAVA  隐含对象</h3>
    	   
 <%}else{
    		   //请求异常
  %>
  <h3 style="color:#0000ff">请求异常</h3>
<h5>请求异常！</h5>  		   
<% }%>
      
</div>
</body>
</html>