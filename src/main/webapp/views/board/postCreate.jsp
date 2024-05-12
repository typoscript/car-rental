<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
	<c:import url="/header"></c:import>
</head>
<body>
	<div class="root">
		<form method="POST" action="/postCreate">
			<div>
				<p>제목</p>
				<input type="text" name="title" id="title">
				<p class="err err-hidden err-msg-title">제목이 비어있습니다.</p>
			</div>
			<div>
				<p>내용</p>
				<textarea name="content" id="content"></textarea>
				<p class="err err-hidden err-msg-content">내용이 비어있습니다.</p>
			</div>
			<input type="submit" class="btn btn-success" value="업로드" >
		</form>
	</div>
	<c:import url="/footer"></c:import>
</body>
<script src="/resources/script/board/post-create-validation.js"></script>
</html>