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
      <pre style="background:#eeeeee;color:#00008b">
             <\%@ 指令名 属性=“值” %>
    </pre>
    常用的三种指令位page、include 、taglib:
    <br/>    <br/>
      <li> <b>page指令</b></li><br/>
    <p>  page 指令可以指定所使用的编程语言、jsp编程生成的class所实现的接口，扩展类和引入的包，其语法格式如下:</p>
     <pre style="background:#eeeeee;color:#00008b">
             <\%@ page name1=“value1” name2="value2" %>
    </pre>
    
    <table border="1" cellpadding="10" cellspacing="0" width="100%">
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
      
      <pre style="background:#eeeeee;color:#00008b"> 
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
 <p> 在JSP文件中，可以通过<\%和%>标记中直接写入任何有效的java程序代码，也可称作Scriptlet程序片段，其作用域为成员函数范围内，方法可以通过page标签的method属性值进行设置，详情见
 <a href="/JspDemo/content.jsp?tag=jsp">method</a>介绍。默认为service()范围内程序片段。如下格式:</p>
 
 <pre style="background:#eeeeee;color:#00008b">
     <\%@ String name="hfcai";
                 System.out.printf(name);
         %>
            <p> This is html segment. </p>
          <\% 
             System.out.printf("get name again:"+name);
          %>
          get success.
 </pre>
 
 <p>
 程序之间可以通过html穿插，前后文的java代码片段按顺序执行。写起来比较方便，如此更加方便我们的逻辑执行。
 
 </p>


    	   
  <%  }else if("expression".equals(tag)){
    	  //java 表达式 
%>
<h3 style="color:#0000ff">JAVA 表达式</h3>

<p>
java表达式可以将具体的数据写到相应的html的位置，其类似一个简单的赋值语句，而不同的是被复制的变量是jsp文档本身，其基本格式如下：
</p>

<pre style="background:#eeeeee;color:#00008b">

   <\%= [statement]%>
   
   //example
   12+20= <\%=12+20 %>
</pre>

<p>
比较容易理解，也比较容易使用。
</p>
<h4>JSP注释</h4>
<p>jsp中可以对其中的java代码进行注释，其格式如下:</p>
<pre style="background:#eeeeee;color:#00008b">


&lt;%--  //这是jsp注释
     &lt;%!
       /**
        *这是申明定义标记
        **/
       int  counter=1;
     
      //创建一个成员函数
       private int getCounter(){
    	  return counter;
      }
     %&gt;
     
     &lt;!--  这是page指令 --&gt;
     &lt;%@ page method="doGet"      %&gt;
     
          &lt;!--  这是include指令 --&gt;
     &lt;%@ include file="index.jsp" %&gt;
     &lt;% 
          //这是作用在service()方法内的程序片段
           int counter=5;
           System.out.println("get counter:"+this.counter);
            this.counter=counter;
     %&gt;
     
     &lt;!-- 这是java表达式 -->
       &lt;p>&gt;get Counter is:&lt;%= getCounter()%>>&lt;/p&gt;
  
  --%>
  
  

</pre>




<% }else if( "declare".equals(tag)){
    	  //jsp声明
%>
<h3 style="color:#0000ff">JSP 声明</h3>
<p>
JSP 声明比较简单，主要是用于对于当前jsp映射的访问的servlet对象声明的类的成员变量和方法。其基本格式如下: 
 </p>
 
 <pre style="background:#eeeeee;color:#00008b">
    <\%! delarce ; [decalaration] %>
 </pre>
 
 
 <p>
 如上，其内部的具体语法和Java中一样，如下给出一个简单的示例演示:
 </p>

 <pre style="background:#eeeeee;color:#00008b">
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
<p>
 Servlet可以访问由Servlet容器提供的ServletContext、ServletRequest、ServletResponse等对象，jsp片段中，这些sevlet中存在的对象称为隐含对象，每个对象都被固定的引用变量引用。JSP本身并不需要再去对其定义申明，
 可以直接通过变量名直接调用。如下为JSP中所有隐含对象的引用变量和类型的对应关系。 
 </p>
 
 <table border="1" style="background:#eeeeef " cellpadding="10"  cellspacing="0" width="60%">
 
 <tr>
   <th> 引用变量</th>
   <th> 类型</th>
 </tr>
 <tr>
    <td>request </td>
    <td>javax.servlet.HttpServletRequest</td>
 </tr>
 
  <tr>
    <td>response </td>
    <td>javax.servlet.HttpServletResponse</td>
 </tr>
  <tr>
    <td>pageContext </td>
    <td>javax.servlet.jsp.PageContext</td>
 </tr>
  <tr>
    <td>application </td>
    <td>javax.servlet.ServletContext</td>
 </tr>
  <tr>
    <td>out </td>
    <td>javax.servlet.jsp.JspWriter</td>
 </tr>
  <tr>
    <td>config </td>
    <td>javax.servlet.ServletConfig</td>
 </tr>
  <tr>
    <td>page </td>
    <td>java.lang.Object(自身，当前对象,等同与this)</td>
 </tr>
  <tr>
    <td>session </td>
    <td>javax.servlet.http.HttpSession</td>
 </tr>
  <tr>
    <td>exception </td>
    <td>java.lang.Exception</td>
 </tr>

 </table>
 
 
  <br/><br/>
 <p>
 对于隐含对象的使用和一般java类中对对象的操作一样。
 
 
 </p>
 
    	   
 <%}else if("cycle".equals(tag)){
	 %>
	   <h3 style="color:#0000ff">JSP 周期</h3>
	   <br/>
	   <p> 
	   jsp文件需要servlet容器将其先编译成Servlet类，然后加载运行，其所包括的生命周期如下:
	   </p>
	   <br/> <br/>
	   <li> 解析阶段</li>
	   
	     <p> jsp特有，Servlet容器解析JSP文件代码，若语法错误，就会向客户端返回错误信息。</p>
	   
	    <li> 翻译阶段</li>
	    <p> jsp 特有，Servlet容器将jsp翻译生成Servlet源文件(java文件)</p>
	    
	     <li> 编译阶段</li>
	     <p> jspt特有,Servlet容器将JSP编译生成class文件</p>
	     
	      <li> 初始化阶段</li>
	      
	      <p> 和一般servlet文件一样，当Servlet加载该jsp文件时为其创建一个对应的servlet对象。</p>
	      
	       <li> 运行时阶段</li>
	       
	       <p>Sevlet运行时被发起请求所做的逻辑处理过程。</p>
	       
	         <li> 销毁阶段</li>
	       <p> 当前servlet被Servlet容器销毁 </p>
	 
	 
	 
	 <br/> <br/>
<p> jsp其实其为一个继承自Servlet接口的类(javax.servlet.jsp.JspPage),其对servlet的初始化和销毁方法进行了重定义，jsp文件可以通过实现jspInit()和jspDestory()方法对Jsp的初始化和销毁过程进行检测。
</p>




<h4>JSP标签 </h4>

<p>JSP标签和JSP指令很类系，如下为JSP标签的一个基本语法格式: </p> 
<pre style="background:#eeeeee;color:#00008b"> 
     
           &lt;jsp:标签名 属性名=“属性值” /&gt;

</pre>
<p>如下为一些常见的Jsp标签:</p><br/>
<li> forward </li><br/>
   <p> 为请求转发标签，格式为:</p>
   <pre style="background:#eeeeee;color:#00008b"> 
     
           &lt;jsp:forward page=“index.html” /&gt;

</pre>
<p> 等同servlet中的forward跳转，需注意的是当执行到此后，jsp会将结果return,之后的操作将会被忽略.</p>

   

<h5> param </h5>
<p> param标签可以在forward标签之前添加，可以实现向跳转的request中添加额外请求参数的作用，其基本格式如下所示:</p>
   <pre style="background:#eeeeee;color:#00008b"> 
      &lt;jsp:forward page=“index.html” &gt;
           &lt;jsp:param name=“name” value="hfcai" /&gt;
            &lt;jsp:param name=“age” value="24" /&gt;
   &lt;jsp:forward  /&gt;
</pre>

<p>同时也可以嵌套在&lt;jsp:include&gt;标签内，用于向被包含的目标组件传递请求参数. </p>



<h5> include </h5><br/>
<p> include标签主要是实现Jsp的包含功能，和nclude 指令功能相似，其基本的语法格式如下:</p>

   <pre style="background:#eeeeee;color:#00008b"> 
      &lt;jsp:include  page=“index.html” /&gt;
</pre>

<p>标签包含的对象可以是绝对路径URL,也可以是相对路径的URL。include标签属于动态包含而include指令属于静态包含。无论哪种包含方式，源组件和被包含组件都共享请求范围内的共享数据.</p>


<li> 静态包含</li>
<p> 静态包含发生在jsp的解析阶段,jsp源组件会将被包含的组建的所有内容原封不动的添加到源jsp组建中,其本质为同一个sevlet(jsp)。因此,被包含组件和源组建共享所有的数据信息(成员变量、成员方法等)。
但因此，也需要注意的是静态包含的组件可以为jsp或者html但不可以为一个servlet组件，静态包含使用inculde指令实现，演示如下:</p>

   <pre style="background:#eeeeee;color:#00008b"> 
        //a.jsp
        &lt;%! String name="hfcai";%&gt;
          &lt;%@ include file="index.jsp" %&gt;
          &lt;% out.print("a.jsp printf "); %&gt;
          
          //index.jsp
        &lt;p&gt;      get  the name from a.jsp ,and name is &lt;%=name %> &lt;/p&gt;
          
          
          //其实包含后就是两个文件的合并，如下为合并后的文件
          
            &lt;%! String name="hfcai"; %&gt;
             &lt;p&gt;       get  the name from a.jsp ,and name is &lt;%=name %&gt; &lt;/p&gt;
          &lt;% out.print("a.jsp printf "); %&gt;
          
</pre>

<p> 为了区分被静态包含的文件和一般的文件的区别，一般将被静态包含的jsp文件后缀改为.jspf,将被静态包含的html后缀修改为.htmlf。或者统一将被静态包含的jsp和html统一修改后缀为.inc</p>

<li> 动态包含</li>
<p> 动态包含发生在jsp组件运行阶段，jsp元组件会将源组件的请求参数转发给被包含的组件，被包含组建根据请求返回数据会被源组建添加到相应位置。因此，jsp动态包含的组件可以是
jsp、html或者servlet。其等同与Jsp自己发起一个请求后(将接受到的请求参数添加)将返回数据添加到自己对应位置。&lt;jsp:include&gt;中还包含一个属性“flush”,可选值为true或者false,默认为false.
当为true时，若被包含组件出现异常报错，会将include之前的内容提交给客户，而不显示错误给客户端。其使用如下:</p>
   <pre style="background:#eeeeee;color:#00008b"> 
   //dynamicIncluder.jsp
   &lt;p&gt;This is dynamic includer jsp &lt;/p&gt;
&lt;% String name="hfcai"; %&gt;

&lt;jsp:include page="test.jsp" flush="true"&gt;
    &lt;jsp:param name="name" value="hfcai1"/&gt;
    &lt;jsp:param name="age" value="24"/&gt;
&lt;/jsp:include&gt;

&lt;p&gt;contain over. &lt;/p&gt;

//test.jsp
&lt;p> This is test.jsp&lt;/p&gt;
&lt;%out.print(request.getParameter("name")); %&gt;

 //返回结果如下
 
 This is dynamic includer jsp

This is test.jsp  

hfcai1
contain over.

  </pre>


<h5>  </h5>

<h5> useBean </h5>
<p> useBean 标签用于在jsp中创建一个Bean实例对象</p>


<% }else{
  //请求异常
  %>
  <h3 style="color:#0000ff">请求异常</h3>
<h5>请求异常！</h5>  		   
<% }%>
      
</div>
</body>
</html>