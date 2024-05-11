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
				<div class="card-car-detail-container">
					<div>
						<span>브랜드</span>
						<span>${cars[i].getBrand()}</span>
					</div>
					<div>
						<span>이름</span>
						<span>${cars[i].getName()}</span>
					</div>
					<div>
						<span>차종</span>
						<span>${cars[i].getType()}</span>
					</div>
					<div>
						<span>연료 종류</span>
						<span>${cars[i].getFuelType()}</span>
					</div>
					<div>
						<span>연식</span>
						<span>${cars[i].getYear()}년형</span>
					</div>
					<div>
						<span>주행거리</span>
						<span>${cars[i].getMileage()}km</span>
					</div>
					<div>
						<span>1일 렌트비</span>
						<span>${cars[i].getFee()}원</span>
					</div>
				</div>
			</div>
			<div>
				<div>
					<span>렌트 시작일</span>
					<span>${reservations[i].getStartDate()}</span>
				</div>
				<div>
					<span>렌트 종료일</span>
					<span>${reservations[i].getEndDate()}</span>
				</div>
				<div>
					<span>예약 상태</span>
					<span>${reservations[i].getStatus()}</span>
				</div>
			</div>
			<div>
				<c:if test="${reservations[i].getStatus() eq '예약'}">
					<button class="btn btn-primary" onclick="location.href='/reservationUpdate?id=${reservations[i].getId()}&startDate=${reservations[i].getStartDate()}&endDate=${reservations[i].getEndDate()}&carId=${cars[i].getId()}&brand=${cars[i].getBrand()}&name=${cars[i].getName()}&type=${cars[i].getType()}&fuelType=${cars[i].getFuelType()}&year=${cars[i].getYear()}&fee=${cars[i].getFee()}&mileage=${cars[i].getMileage()}&imgUrl=${cars[i].getImgUrl()}'">수정</button>
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