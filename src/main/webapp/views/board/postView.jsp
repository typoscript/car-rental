<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 보기</title>
	<c:import url="/header"></c:import>
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/login"></c:redirect>
	</c:if>
	
	<div class="root">
		<div>
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
		</div>

		<div>
			<div>
				<span>게시 날짜</span>
				<span>${post.getCreationDate()}</span>
			</div>		
			<div>
				<span>수정 날짜</span>
				<span>${post.getModificationDate()}</span>
			</div>		
			<div>
				<button class="btn btn-primary" onclick="location.href='/postUpdate?id=${post.getId()}'">수정</button>
				<button class="btn btn-danger" onclick="location.href='/postDelete?id=${post.getId()}'">삭제</button>
			</div>
		</div>
		
		<div>
			<span>내용</span>
			<span>${post.getContent()}</span>
		</div>		
	</div>

	<c:import url="/footer"></c:import>
</body>
</html>