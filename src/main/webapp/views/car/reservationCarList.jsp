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
	</div>
	<c:import url="/footer"></c:import>
</body>
<script src="/resources/script/car/reservationCarList.js"></script>
</html>