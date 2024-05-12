<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 변경</title>
	<c:import url="/header"></c:import>
</head>
<body>
	<div class="root">
		<form class="form" method="POST" action="/reservationUpdate">
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
			</div>
			<input type="hidden" name="id" value="${reservation.getId()}">
			<input type="hidden" name="carId" value="${car.getId()}">
			<div>
				<p>렌트 시작일</p>
				<input type="date" name="startDate" id="startDate" value="${reservation.getStartDate()}">
				<p class="err ${isInvalidStartDate ? '' : 'err-hidden'} err-msg-startDate-invalid">유효하지 않는 날짜입니다.</p>
			</div>
			<div>
				<p>렌트 종료일</p>
				<input type="date" name="endDate" id="endDate" value="${reservation.getEndDate()}">
				<p class="err ${isInvalidEndDate ? '' : 'err-hidden'} err-msg-endDate-invalid">유효하지 않는 날짜입니다.</p>
			</div>
			<div>
				<p>지불금액</p>
				<input type="hidden" id="pricePerDay" value="${car.getFee()}">
				<input type="number" name="price" id="price" disabled>
				<p>입금액</p>
				<input type="number" name="payAmount" id="payAmount">
				<p class="err ${isInvalidPayAmount ? '' : 'err-hidden'} err-msg-payAmount-invalid">금액이 부족합니다.</p>
			</div>
			<div>
				<p class="err err-hidden err-msg-reservationDate-invalid">유효하지 않는 렌트 기간입니다.</p>
				<p class="err ${isInvalidReservationDateRange ? '' : 'err-hidden'} err-msg-reservationDate-invalid">해당 렌트 기간에 렌트 예약이 존재합니다.</p>
			</div>
			<input type="submit" class="btn btn-success" value="예약 수정">
		</form>
	</div>
	<c:import url="/footer"></c:import>
</body>
<script src="/resources/script/reservation/reservation-form-validation.js"></script>
</html>