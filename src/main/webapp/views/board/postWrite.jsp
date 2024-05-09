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
		<form method="POST" action="/PostWrite">
			<div>
				<p>제목</p>
				<input type="text" name="title">
			</div>
			<div>
				<p>내용</p>
				<textarea name="content"></textarea>
			</div>
			<input type="submit" class="btn btn-success" value="업로드" >
		</form>
	</div>
	<c:import url="/footer"></c:import>
</body>
</html>