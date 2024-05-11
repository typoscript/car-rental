<%@page import="carRental.car.model.CarResponseDto"%>
<%@page import="carRental.reservation.model.ReservationResponseDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<% List<ReservationResponseDto> reservations = (List<ReservationResponseDto>) request.getAttribute("reservations"); %>
	<% List<CarResponseDto> cars = (List<CarResponseDto>) request.getAttribute("cars"); %>
		
	<%
		for (int i = 0; i < reservations.size(); i++) {
			ReservationResponseDto reservation = reservations.get(i);
			CarResponseDto car = cars.get(i);
	%>
			<div class="card-reservation">
				<div class="card-car">
					<img src=<%= car.getImgUrl() %>>
					<div>
						<p><%= car.getBrand() %></p>
						<p><%= car.getName() %></p>
						<p><%= car.getType() %></p>
						<p><%= car.getFuelType() %></p>
						<p><%= car.getYear() %></p>
						<p><%= car.getFee() %></p>
						<p><%= car.getMileage() %></p>
					</div>
				</div>
				<div>
					<p><%= reservation.getStartDate() %></p>
					<p><%= reservation.getEndDate() %></p>
					<p><%= reservation.getStatus() %></p>
				</div>
				<div>
					<button class="btn btn-primary" onclick="location.href='/reservationUpdate?id=<%=reservation.getId()%>'">수정</button>
					<button class="btn btn-danger" onclick="location.href='/reservationUpdate?id=<%=reservation.getId()%>&status=취소'">취소</button>
				</div>
			</div>
	<%
		}
	%>
	</div>
	<c:import url="/footer"></c:import>
</body>
</html>