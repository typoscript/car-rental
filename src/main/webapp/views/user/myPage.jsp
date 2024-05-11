<%@page import="carRental.reservation.model.ReservationResponseDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
	<c:import url="/header"></c:import>
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/login"></c:redirect>
	</c:if>

	<div class="root">
		<h1>${user.getName()}님의 마이페이지</h1>
		<div>
			<button class="btn btn-primary" onclick="location.href='/myReservation'">나의 예약</button>
			<button class="btn btn-primary" onclick="location.href='/userUpdate'">회원정보 수정</button>
			<button class="btn btn-danger" onclick="location.href='/logoutAction'">로그아웃</button>
			<button class="btn btn-danger" onclick="location.href='/userDelete'">회원탈퇴</button>
		</div>
	</div>
	<c:import url="/footer"></c:import>
</body>
</html>