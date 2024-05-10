<%@page import="carRental.car.model.CarResponseDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 차량 리스트</title>
	<c:import url="/header"></c:import>
	<link rel="stylesheet" href="/resources/style/car/reservationCarList.css"/>
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/login"></c:redirect>
	</c:if>

	<div class="root">
		<% List<CarResponseDto> cars = (List<CarResponseDto>) request.getAttribute("cars"); %>
		
		<%
		for (CarResponseDto car : cars)	{ %>
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
				<button class="btn btn-primary" 
				onclick="location.href='/carReservationCreate?carId=<%= car.getId()%>&brand=<%= car.getBrand()%>&name=<%= car.getName()%>&type=<%= car.getType()%>&fuelType=<%= car.getFuelType()%>&year=<%= car.getYear()%>&fee=<%= car.getFee()%>&mileage=<%= car.getMileage()%>&imgUrl=<%= car.getImgUrl()%>'">예약</button>
			</div>
		<%
		}
		%>
	</div>
	<c:import url="/footer"></c:import>
</body>
</html>