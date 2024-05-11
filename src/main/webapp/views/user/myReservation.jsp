<%@page import="carRental.car.model.CarResponseDto"%>
<%@page import="carRental.reservation.model.ReservationResponseDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 예약</title>
	<c:import url="/header"></c:import>
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/login"></c:redirect>
	</c:if>

	<div class="root">
	<c:forEach var="i" begin="0" end="${fn:length(reservations)-1}" step="1">
		<div class="card-reservation">
			<div class="card-car">
				<img src="${cars[i].getImgUrl()}">
				<div>
					<p>${cars[i].getBrand()}</p>
					<p>${cars[i].getName()}</p>
					<p>${cars[i].getType()}</p>
					<p>${cars[i].getFuelType()}</p>
					<p>${cars[i].getYear()}</p>
					<p>${cars[i].getFee()}</p>
					<p>${cars[i].getMileage()}</p>
				</div>
			</div>
			<div>
				<p>${reservations[i].getStartDate()}</p>
				<p>${reservations[i].getEndDate()}</p>
				<p>예약 상태: ${reservations[i].getStatus()}</p>
			</div>
			<div>
				<c:if test="${reservations[i].getStatus() eq '예약'}">
					<button class="btn btn-primary" onclick="location.href='/reservationUpdate?id=${reservations[i].getId()}'">수정</button>
					<form method="POST" action="/reservationUpdate?id=${reservations[i].getId()}&status=취소">
						<input type="submit" class="btn btn-danger" value="취소" >
					</form>
				</c:if>
			</div>
		</div>
	</c:forEach>
	</div>
	<c:import url="/footer"></c:import>
</body>
</html>