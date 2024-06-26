<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/style/common/style.css"/>
<link rel="stylesheet" href="/resources/style/common/header.css"/>
<link rel="stylesheet" href="/resources/style/car/car.css"/>
<body>
	<header>
		<h1><a href="/">렌터카</a></h1>
		<nav>
			<ul>
				<li><a href="/carList">예약</a></li>
				<li><a href="/board">게시판</a></li>
				<c:choose>
					<c:when test="${not empty user}">
						<li><a href="/myPage">마이페이지</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/login">로그인</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</header>
</body>
</html>