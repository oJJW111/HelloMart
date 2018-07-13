<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이 페이지</title>
<style type="text/css">
.myshopMain {
	overflow: hidden;
	width: 800px;
	text-align: center;
	border-left: 1px solid #ddd;
	border-top: 1px solid #ddd;
	margin: 60px auto;
}
.myshopMain .shopMain {
	float: left;
	width: 399px;
	border-right: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
}
.myshopMain .shopMain:hover {
	background: #E9E9E9;
}
.myshopMain .shopMain .tit {
	color: #333;
	display: block;
	padding: 25px 20px;
	font-size: 12px;
	font-family: sans-serif;
	font-weight: bold;
}
.myshopMain .shopMain a {
	display: block;
	font-size: 10px;
	text-decoration: none;
}
</style>

</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->
<div class="titbox">
	<div class="title">
		<span class="name">MY PAGE</span>
	</div>
</div>
<div class="myshopMain">
	<div class="shopMain">
		<a href="/mypage/info"> 
			<span class="tit">MODIFY<br>(회원정보)</span>
		</a>
	</div>
	<div class="shopMain">
		<a href="/mypage/history"> 
			<span class="tit">ORDER LIST<br>(주문내역)</span>
		</a>
	</div>
	<div class="shopMain">
		<a href="/mypage/cartlist"> 
			<span class="tit">CART<br>(장바구니)</span>
		</a>
	</div>
	<div class="shopMain">
		<a href="/mypage/point"> 
			<span class="tit">MY POINT<br>(적립금내역)</span>
		</a>
	</div>
	<div class="shopMain">
		<a href="/todayView">
			<span class="tit">TODAY VIEW<br>(오늘 본 상품)</span>
		</a>
	</div>
	<div class="shopMain">
		<a href="/mypage/info/delete"> 
			<span class="tit">USER EXIT<br>(회원탈퇴)</span>
		</a>
	</div>
</div>

<div class="BLOCK80"></div>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>