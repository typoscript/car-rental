<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 보기</title>
	<c:import url="/header"></c:import>
	<link rel="stylesheet" href="/resources/style/board/postView.css"/>
</head>
<body>
	<div class="root">
		<div class="post-container">
			<div class="post-info-container">
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
				<div>
					<span>수정 날짜</span>
					<span>${post.getModificationDate()}</span>
				</div>		
			</div>

			<c:if test="${post.getUserId().equals(user.getId()) || user.isAdmin()}">
			<div class="post-action-container">
				<button class="btn btn-primary" onclick="location.href='/postUpdate?id=${post.getId()}&title=${post.getTitle()}&content=${post.getContent()}&isNotice=${post.isNotice()}&creationDate=${post.getCreationDate()}&modificationDate=${post.getModificationDate()}'">수정</button>
				<button class="btn btn-danger" onclick="location.href='/postDelete?id=${post.getId()}'">삭제</button>
			</div>
			</c:if>
			
			<div class="post-content-container">
				<span>${post.getContent()}</span>
			</div>		
		</div>
	</div>

	<c:import url="/footer"></c:import>
</body>
</html>