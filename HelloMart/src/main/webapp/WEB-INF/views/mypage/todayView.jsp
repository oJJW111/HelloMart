<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/product.css" />
</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header_nomenu.jsp"/>
<!-- 헤더 -->
<div class="BLOCK20"></div>

<div class="article_wrap">

<!-- 상품리스트 -->
<div class="product_list">
	<c:forEach var="board" items="${todayView}">
			<div class="product_list_content">
				<div class="product_img">
					<a href="/productView?no=${board.no}">
						<img src="${board.imagePath}">
					</a>
				</div>
				<div class="product_info">
					<a class="title" href="/productView?no=${board.no}">${board.productName}</a>
					<div class="additional_info">
						<span class="brand">${board.mfCompany}</span>
					</div>
				</div>
				<div class="product_addition">
					<div class="price">
						<strong>${board.price} 원</strong>
					</div>
					<div class="additional_info">
						<span class="satisfaction">만족도 : ${board.score}</span>
						<span class="buy">구  &nbsp;&nbsp;매 : ${board.orderCount}</span>  
						<span class="review">상품평 : ${board.no}</span>
					</div>
				</div>
			</div> <!-- <div class="product_list_content"> -->
			<hr class="style14">
	</c:forEach>
</div>
<!-- 상품리스트 -->

</div>

<div class="BLOCK20"></div>
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>