<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet"  href="<c:url value="/resources/css/style.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<div class="loginForm">
<h1>Login</h1>
	<form action="add" method="post">
		<table>
			<tr>
				<td>Enter Username :</td>
				<td><input type="text" name="email" id="emailId"></td>
			</tr>
			<tr>
				<td>Enter Password :</td>
				<td><input type="password" name="pass" id="passwordId"></td>
			</tr>

		</table>
		<input class="button" type="submit" value="Login"> 	
		<br><br>
		<a href="register">New User</a>	
	</form>
	
</div>
</body>
</html>