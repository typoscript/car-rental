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
	<% int id = (Integer) request.getAttribute("id"); %>
	<div class="root">
		<form class="form" method="POST" action="/reservationUpdate">
			<input type="hidden" name="id" value="<%=id %>">
			<input type="hidden" name="carId" value="1">
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