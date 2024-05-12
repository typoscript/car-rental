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
	<div class="root">
		<div class="login-container">
			<form class="form-login" method="POST" action="/login">
				<div>
					<div>
						<p>아이디</p>
						<input type="text" name="id" id="id" >
					</div>
					<p class="err err-hidden err-msg-id">아이디가 5자리 이상 20자리 이하여야합니다.</p>
				</div>
				<div>
					<div>
						<p>비밀번호</p>
						<input type="password" name="password" id="password" >
					</div>
					<p class="err err-hidden err-msg-password">비밀번호가 10자리 이상 20자리 이하여야합니다.</p>
				</div>
				<p class="err ${isInvalidLogin ? '' : 'err-hidden' } err-msg-login">아이디 혹은 비밀번호가 틀리거나 없는 계정입니다.</p>
				<input type="submit" class="btn btn-success" value="로그인" >
			</form>
			<p id="sign-up"><a href="/signUp">회원가입</a></p>
		</div>
	</div>
	<c:import url="/footer"></c:import>
</body>
<script src="/resources/script/user/login-validation.js"></script>
</html>