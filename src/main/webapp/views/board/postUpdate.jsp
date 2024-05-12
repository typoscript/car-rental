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
	<div class="root">
		<form method="POST" action="/postUpdate">
			<input type="hidden" name="id" value="${post.getId()}">
			<div>
				<p>제목</p>
				<input type="text" name="title" value="${post.getTitle()}">
				<p class="err err-hidden err-msg-title">제목이 비어있습니다.</p>
			</div>
			<div>
				<p>내용</p>
				<textarea name="content">${post.getContent()}</textarea>
				<p class="err err-hidden err-msg-content">내용이 비어있습니다.</p>
			</div>
			<input type="submit" class="btn btn-success" value="수정" >
		</form>
	</div>

	<c:import url="/footer"></c:import>
</body>
<script src="/resources/script/board/post-form-validation.js"></script>
</html>