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
	<link rel="stylesheet" href="/resources/style/car/reservationCarList.css"/>
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/login"></c:redirect>
	</c:if>

	<div class="root">
		<c:forEach var="i" begin="0" end="${fn:length(cars)-1}" step="1">
			<div class="card-car">
				<img src="${cars[i].getImgUrl()}">
				<div>
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
						<span>${cars[i].getYear()}</span>
					</div>
					<div>
						<span>주행거리</span>
						<span>${cars[i].getMileage()}</span>
					</div>
					<div>
						<span>1일 렌트비</span>
						<span>${cars[i].getFee()}</span>
					</div>
				</div>
				<button class="btn btn-primary" 
				onclick="location.href='/reservationCreate?carId=${cars[i].getId()}&brand=${cars[i].getBrand()}&name=${cars[i].getName()}&type=${cars[i].getType()}&fuelType=${cars[i].getFuelType()}&year=${cars[i].getYear()}&fee=${cars[i].getFee()}&mileage=${cars[i].getMileage()}&imgUrl=${cars[i].getImgUrl()}'">예약</button>
			</div>
		</c:forEach>
	</div>
	<c:import url="/footer"></c:import>
</body>
</html>