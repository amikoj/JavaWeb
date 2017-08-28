<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page errorPage="errorpage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=titlename %></title>
</head>
<body> 


     
<table width="100%" height="100%">
   <tr> 
           <td width="150" valigh="top" aligh="left" bgcolor="ccffcc" > 
                      <jsp:include page="sidebar.html"></jsp:include>
           </td>
           
           
           <td     height="100%" width="*">       
                       <table width="100%" height="100%">
                             <tr>  
                             
                                <td valigh="top" height="15%"> 
                                
                                        <jsp:include page="header.html"></jsp:include>
                                
                                  </td>
                             </tr>  
                     
                           <tr>
                                <td valigh="top">
                                
                                     <jsp:include page="<%=bodyfile %>"></jsp:include>
                                     

   <%! 
 private int toInt(String numString){
	return Integer.parseInt(numString);
}
%>



<p>count:<%=toInt("10q") %>></p>
                                
                                </td>
                                        
                            </tr>
                            
                            <tr> 
                                      <td valigh="bottom" height="15%">
                                      
                                              <jsp:include page="footer.html"></jsp:include>
                                      
                                       </td>
                            
                             </tr>
                       
                       </table>
                 
           </td>
   </tr>



</table>
     

</body>
</html>