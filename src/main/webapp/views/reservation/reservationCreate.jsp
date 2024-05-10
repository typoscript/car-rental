<%@page import="carRental.car.model.CarResponseDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약</title>
	<c:import url="/header"></c:import>
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/login"></c:redirect>
	</c:if>
	<div>
		<% CarResponseDto car = (CarResponseDto) request.getAttribute("car"); %>

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
		
		<form class="form" method="POST" action="/reservationCreate">
			<input type="hidden" name="carId" value="<%= car.getId()%>">
			<div>
				<p>렌트 시작일</p>
				<input type="date" name="rentalStartDate">
			</div>
			<div>
				<p>렌트 종료일</p>
				<input type="date" name="rentalEndDate">
			</div>
			<div>
				<p>지불금액</p>
				<input type="number" name="payAmount" disabled>
				<p>입금액</p>
				<input type="number" name="userPayAmount">
			</div>
			<input type="submit" class="btn btn-success" value="예약">
		</form>
	</div>
	<c:import url="/footer"></c:import>
</body>
</html>