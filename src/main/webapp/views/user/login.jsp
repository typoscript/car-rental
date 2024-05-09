<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
	<c:import url="/header"></c:import>
	<link rel="stylesheet" href="/resources/style/user/login.css"/>
</head>
<body>
	<div id="login-container">
		<form id="form-login" method="POST" action="/loginAction">
			<div>
				<p>아이디</p>
				<input type="text" name="id" >
			</div>
			<div>
				<p>비밀번호</p>
				<input type="text" name="password" >
			</div>
			<input type="submit" class="btn btn-login" value="로그인" >
		</form>
		<p id="sign-up"><a href="/signUp">회원가입</a></p>
	</div>
	<c:import url="/footer"></c:import>
</body>
<script src="/resources/script/user/login-validation.js"></script>
</html>