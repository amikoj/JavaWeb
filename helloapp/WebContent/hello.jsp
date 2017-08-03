
<%@ taglib prefix="ex" uri="WEB-INF/mytaglib.tld" %>
<html>
<head>
<title>helloapp </title>
</head>

<body>
<b><ex:hello/>:<%= request.getAttribute("USER") %> </b>

</body>


</html>
