<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이 페이지</title>
<style type="text/css">
* {
	box-sizing: border-box;
}

.mypage-container {
	width: 400px;
	margin: auto;
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
<div class="mypage-container">
	<ul class="mypage-list">
		<li class="mypage-list-item" id="info">회원정보 관리</li>
		<li class="mypage-list-item" id="shoppingcart">쇼핑카트</li>
		<li class="mypage-list-item" id="point">포인트 관리</li>
		<li class="mypage-list-item" id="history">구매이력</li>
	</ul>
</div>

<div class="BLOCK60"></div>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>