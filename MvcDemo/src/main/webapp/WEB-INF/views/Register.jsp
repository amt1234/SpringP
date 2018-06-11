<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  href="<c:url value="/resources/css/style.css"/>">
<script src="<c:url value="/resources/js/Validation.js"/>" type="text/javascript" ></script>
</head>
<body>
<div class="loginForm">
<h1>Register Page</h1>
<!-- <form action="index.jsp" method="post" onsubmit="return ValidationTextname()">
 -->
 <form action="registerController" method="post" modelAttribute="registerUser" onsubmit="return ValidationTextname()">
 <table>
			<tr>
				<td>Enter Name :</td>
				<td><input type="text" name="username" id="nameId" /></td>
			</tr>
			<tr>
				<td>Enter Email :</td>
				<td><input type="email" name="email" id="emailId" /></td>
			</tr>
			<tr>
				<td>Enter Password :</td>
				<td><input type="password" name="password" id="passwordId" /></td>
			</tr>
			<tr>
				<td>Enter Mobile Number :</td>
				<td><input type="text" name="mobileNo" id="mobileNo"  /></td>
			</tr>
			<tr>
				<td>Enter DOB :</td>
				<td><input type="date" name="dob" /></td>
			</tr>
		</table>
		<input class="button" type="submit" value="submit"/>
		
</form>
<font color="red">${message}</font>

</div>
</body>
</html>