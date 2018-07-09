<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이 페이지</title>
<style type="text/css">
/* * {
	box-sizing: border-box;
}
.mypage-container {
	width: 400px;
	margin: auto;
	user-select: none;
}
.mypage-list {
	border: 0 solid #ababab;
	border-width: 0 0 1px 1px;
	list-style-type: none;
	text-align: center;
	font-size: 0;
}
.mypage-list-item {
	border: 0 solid #ababab;
	border-width: 1px 1px 0 0;
	display: block;
	padding: 12px 20px;
	font-size: 20px;
}
.mypage-list-item:HOVER {
	cursor: pointer;
	background-color: #efefef;
} */
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
<jsp:include page="/WEB-INF/views/inc/header_nomenu.jsp"/>
<!-- 헤더 -->

<hr>

<div class="BLOCK60"></div>

<script>
$(document).ready(function(){
	var mypage = "/mypage";
	$("#info").click(function() {
		location.href= mypage + "/info";
	});
	$("#shoppingcart").click(function() {
		location.href= mypage + "/shoppingcart";
	});
	$("#point").click(function() {
		location.href= mypage + "/point";
	});
	$("#history").click(function() {
		location.href= mypage + "/history";
	});
	$("#history").click(function() {
		location.href= mypage + "/todayView";
	});
});
</script>

<!-- 회원정보 관리 -->
<!-- 쇼핑카트 -->
<!-- 포인트 관리 -->
<!-- 구매이력 -->
<!-- <div class="mypage-container">
	<ul class="mypage-list">
		<li class="mypage-list-item" id="info">회원정보 관리</li>
		<li class="mypage-list-item" id="shoppingcart">쇼핑카트</li>
		<li class="mypage-list-item" id="point">포인트 관리</li>
		<li class="mypage-list-item" id="history">구매이력</li>
	</ul>
</div> -->
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
		<a href="/mypage/shoppingcart"> 
			<span class="tit">CART<br>(장바구니)</span>
		</a>
	</div>
	<div class="shopMain">
		<a href="/mypage/point"> 
			<span class="tit">MY POINT<br>(적립금내역)</span>
		</a>
	</div>
	<div class="shopMain">
		<span class="tit">TODAY VIEW<br>(오늘 본 상품)</span>
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