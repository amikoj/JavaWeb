<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<p>This is dynamic includer jsp </p>
<% String name="hfcai"; %>

<jsp:include page="test.jsp" flush="true">
    <jsp:param name="name" value="hfcai1"/>
    <jsp:param name="age" value="24"/>
</jsp:include>

<p>contain over. </p>

</body>
</html>