<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
	<link rel="stylesheet" href="/resources/style/user/form.css">
	<c:import url="/header"></c:import>
</head>
<body>
	<div class="root">
		<form class="form" method="POST" action="/signUp">
			<div>
				<p>아이디</p>
				<input type="text" name="id" id="id" >
				<p class="err err-hidden err-msg-id">아이디가 5자리 이상 20자리 이하여야합니다.</p>
				<c:if test="${isDuplId}">
					<p class="err err-msg-id-duplication">이미 존재하는 아이디입니다.</p>
				</c:if>
			</div>
			<div>
				<p>비밀번호</p>
				<input type="password" name="password" id="password" >
				<p class="err err-hidden err-msg-password">비밀번호가 10자리 이상 20자리 이하여야합니다.</p>
			</div>
			<div>
				<p>이름</p>
				<input type="text" name="name" id="name" >
				<p class="err err-hidden err-msg-name">이름이 최소 1자리 이상이여합니다.</p>
			</div>
			<div>
				<p>주소</p>
				<input type="text" name="address" id="address" >
				<p class="err err-hidden err-msg-address">주소가 최소 5자리 이상이여야합니다.</p>
			</div>
			<div>
				<p>전화번호</p>
				<input type="text" name="phone" id="phone" >
				<p class="err err-hidden err-msg-phone">올바른 전화번호 입력 (예: 010-1234-1234).</p>
				<c:if test="${isDuplPhone}">
					<p class="err err-msg-phone-duplication">이미 존재하는 전화번호입니다.</p>
				</c:if>
			</div>
			<div>
				<p>관리자입니까?</p>
				<input type="checkbox" name="isAdmin" id="isAdmin" >
			</div>

			<input type="submit" class="btn btn-success" value="회원가입" >
		</form>
	</div>
	<c:import url="/footer"></c:import>
</body>
<script src="/resources/script/user/sign-up-validation.js"></script>
</html>