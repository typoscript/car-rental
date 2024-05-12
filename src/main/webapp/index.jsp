<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>렌터카</title>
	<c:import url="/header"></c:import>
	<link rel="stylesheet" href="/resources/style/common/home.css">
</head>
<body>
	<div class="root">
		<h1 class="home-heading">초저가 렌트</h1>
		<div class="home-container">
			<div>
				<img src="https://www.hyundai.com/contents/comparison/carimg_1052.png">
				<img src="https://www.hyundai.com/contents/comparison/carimg_1107.png">
				<img src="https://www.hyundai.com/contents/comparison/carimg_1129.png">
				<img src="https://www.hyundai.com/contents/comparison/carimg_1267.png">
			</div>
			<button class="btn btn-primary" onclick="location.href='/carList'">렌트 바로가기</button>
		</div>
	</div>
	<c:import url="/footer"></c:import>
</body>
</html>