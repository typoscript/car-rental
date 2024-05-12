<%@page import="carRental.car.model.CarResponseDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 차량 리스트</title>
	<c:import url="/header"></c:import>
</head>
<body>
	<div class="root">
		<c:forEach var="car" items="${cars}" >
			<div class="card-car">
				<img src="${car.getImgUrl()}">
				<div class="card-car-detail-container">
					<div>
						<span>브랜드</span>
						<span>${car.getBrand()}</span>
					</div>
					<div>
						<span>이름</span>
						<span>${car.getName()}</span>
					</div>
					<div>
						<span>차종</span>
						<span>${car.getType()}</span>
					</div>
					<div>
						<span>연료 종류</span>
						<span>${car.getFuelType()}</span>
					</div>
					<div>
						<span>연식</span>
						<span>${car.getYear()}년형</span>
					</div>
					<div>
						<span>주행거리</span>
						<span>${car.getMileage()}km</span>
					</div>
					<div>
						<span>1일 렌트비</span>
						<span>${car.getFee()}원</span>
					</div>
				</div>
				<button class="btn btn-primary" 
				onclick="location.href='/reservationCreate?carId=${car.getId()}&brand=${car.getBrand()}&name=${car.getName()}&type=${car.getType()}&fuelType=${car.getFuelType()}&year=${car.getYear()}&fee=${car.getFee()}&mileage=${car.getMileage()}&imgUrl=${car.getImgUrl()}'">예약</button>
			</div>
		</c:forEach>
	</div>
	<c:import url="/footer"></c:import>
</body>
</html>