<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"  href="<c:url value="/resources/css/style.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<h2>Hello User !</h2>
<%-- <%= request.getAttribute("emailid")%> --%>

${emailid} 
<form action="index.jsp">
<%

	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	HttpSession httpSession=request.getSession();

	if(httpSession.getAttribute("userObject")==null)
	{
		response.sendRedirect("index.jsp");
	}
	
	
%>
<h2>Welcome ${username}</h2>

<br>
<!-- <a href="visit">visit here</a> -->
<br>
<br>
<center><input class="bottonlogout" type="submit" value="logout"/></center>
</form>
</body>
</html>