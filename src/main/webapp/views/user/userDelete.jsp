<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
	<c:import url="/header"></c:import>
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/login"></c:redirect>
	</c:if>

	<div class="root">
		<form class="form" method="POST" action="/userDeleteAction">
			<div>
				<p>기존 비밀번호</p>
				<input type="password" name="password" >
			</div>
			<input type="submit" class="btn btn-danger" value="회원탈퇴" >
		</form>
	</div>

	<c:import url="/footer"></c:import>
</body>
</html>