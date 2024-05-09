<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
	<link rel="stylesheet" href="/resources/style/user/form.css">
	<c:import url="/header"></c:import>
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/login"></c:redirect>
	</c:if>

	<div class="root">
		<form class="form" method="POST" action="/userUpdateAction">
			<div>
				<p>기존 비밀번호</p>
				<input type="password" name="password">
				<p class="err err-msg-password-wrong">비밀번호가 10자리 이상 20자리 이하여야합니다.</p>
				<p class="err err-msg-password">비밀번호가 10자리 이상 20자리 이하여야합니다.</p>
			</div>
			<div>
				<p>새 비밀번호</p>
				<input type="password" name="passwordNew">
				<p class="err err-msg-password">비밀번호가 10자리 이상 20자리 이하여야합니다.</p>
			</div>
			<div>
				<p>이름</p>
				<input type="text" name="name" disabled>
			</div>
			<div>
				<p>주소</p>
				<input type="text" name="address">
				<p class="err err-msg-address">주소가 최소 5자리 이상이여야합니다.</p>
			</div>
			<div>
				<p>전화번호</p>
				<input type="text" name="phone">
				<p class="err err-msg-phone">올바른 전화번오 입력 (예: 010-1234-1234).</p>
				<p class="err err-msg-phone-exist">이미 사용중인 전화번호입니다.</p>
			</div>
			<input type="submit" class="btn btn-success" value="회원정보 수정">
		</form>	
	</div>
	<c:import url="/footer"></c:import>
</body>
</html>