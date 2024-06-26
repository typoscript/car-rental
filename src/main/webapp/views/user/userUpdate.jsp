<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
	<link rel="stylesheet" href="/resources/style/common/form.css">
	<c:import url="/header"></c:import>
</head>
<body>
	<div class="root">
		<form class="form" method="POST" action="/userUpdate">
			<div>
				<p>기존 비밀번호</p>
				<input type="password" name="password" id="password">
				<p class="err err-hidden err-msg-password">비밀번호가 10자리 이상 20자리 이하여야합니다.</p>
				<c:if test="${isInvalidPassword}">
					<p class="err err-msg-password-invalid">비밀번호가 틀립니다.</p>
				</c:if>
			</div>
			<div>
				<p>새 비밀번호</p>
				<input type="password" name="newPassword" id="newPassword">
				<p class="err err-hidden err-msg-new-password">비밀번호가 10자리 이상 20자리 이하여야합니다.</p>
			</div>
			<div>
				<p>이름</p>
				<input type="text" name="name" disabled value="${user.getName()}">
			</div>
			<div>
				<p>주소</p>
				<input type="text" name="address" id="address" value="${user.getAddress()}">
				<p class="err err-hidden err-msg-address">주소가 최소 5자리 이상이여야합니다.</p>
			</div>
			<div>
				<p>전화번호</p>
				<input type="text" name="phone" id="phone" value="${user.getPhone()}">
				<p class="err err-hidden err-msg-phone">올바른 전화번호 입력 (예: 010-1234-1234).</p>
				<c:if test="${isDuplPhone}">
					<p class="err err-msg-phone-dupl">중복 전화번호입니다.</p>
				</c:if>
			</div>
			<input type="submit" class="btn btn-success" value="회원정보 수정">
		</form>	
	</div>
	<c:import url="/footer"></c:import>
</body>
<script src="/resources/script/user/user-update-validation.js"></script>
</html>