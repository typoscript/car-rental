<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
	<c:import url="/header"></c:import>
	<link rel="stylesheet" href="/resources/style/board/board.css">
</head>
<body>
	<div class="root">
		<nav class="board-nav">
			<button class="btn btn-primary" onclick="location.href='/board'">게시글</button>
			<button class="btn btn-primary" onclick="location.href='/notice'">공지사항</button>
		</nav>
		<div class="board-action-container">
			<button class="btn btn-primary" onclick="location.href='/postCreate'">글쓰기</button>
		</div>

		<div class="board-post-container">
			<c:if test="${empty posts}">
				<div>
					<h1>글이 없습니다</h1>
				</div>
			</c:if>
			<c:forEach var="post" items="${posts}" >
			<div class="post-container" onclick="location.href='/postView?id=${post.getId()}'">
				<div>
					<span>ID</span>
					<span>${post.getId()}</span>
				</div>		
				<div>
					<span>제목</span>
					<span>${post.getTitle()}</span>
				</div>		
				<div>
					<span>글쓴이</span>
					<span>${post.getUserId()}</span>
				</div>		
				<div>
					<span>게시 날짜</span>
					<span>${post.getCreationDate()}</span>
				</div>		
			</div>		
			</c:forEach>
		</div>
	</div>
	<c:import url="/footer"></c:import>
</body>
</html>