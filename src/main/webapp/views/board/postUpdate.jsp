<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
	<c:import url="/header"></c:import>
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/login"></c:redirect>
	</c:if>
	
	<div class="root">
		<form method="POST" action="/postUpdate">
			<input type="hidden" name="id" value="${post.getId()}">
			<div>
				<p>제목</p>
				<input type="text" name="title" value="${post.getTitle()}">
			</div>
			<div>
				<p>내용</p>
				<textarea name="content">${post.getContent()}</textarea>
			</div>
			<input type="submit" class="btn btn-success" value="수정" >
		</form>
	</div>

	<c:import url="/footer"></c:import>
</body>
</html>