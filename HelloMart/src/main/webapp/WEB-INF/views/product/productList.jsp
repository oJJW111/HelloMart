<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/pigeon.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/product.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

<div class="article_wrap">

<div class=BLOCK70></div>


<!-- small 카테고리가 null이 아니라면 상세검색 기능을 제공한다. -->
<c:if test="${param.small ne null}">
	<jsp:include page="/WEB-INF/views/product/inc/detail_search.jsp"/>
</c:if>
<!-- small 카테고리가 null이 아니라면 상세검색 기능을 제공한다. -->


<!-- 상품리스트 -->
<div class="product_list">
	<div class="product_list_content">
		<div class="product_img"><a href="#"><img src="/resources/images/product/washing_machine01.jpg"></a></div>
		<div class="product_info">
			<a class="title" href="#">
			제품 이름
			</a>
			<div class="additional_info">
				<span class="brand">[LG전자]</span>
				<span class="category">
					<a href="#">가전제품</a> > 
					<a href="#">주방가전</a> > 
					<a href="#">냉장고</a></span>
			</div>
		</div>
		<div class="product_addition">
			<div class="price"><strong>600,000원</strong></div>
			<div class="additional_info">
				<span class="satisfaction">만족도 98%</span>
				<span class="buy">구  &nbsp;&nbsp;매 1285</span>
				<span class="review">상품평 1564</span>
			</div>
			<button class="add_to_cart btn_yellow"></button>
		</div>
	</div>
	<hr class="style14">
	<div class="product_list_content">
		<div class="product_img"><a href="#"><img src="/resources/images/product/washing_machine01.jpg"></a></div>
		<div class="product_info">
			<a class="title" href="#">
			제품 이름
			</a>
			<div class="additional_info">
				<span class="brand">[LG전자]</span>
				<span class="category">
					<a href="#">가전제품</a> > 
					<a href="#">주방가전</a> > 
					<a href="#">냉장고</a></span>
			</div>
		</div>
		<div class="product_addition">
			<div class="price"><strong>600,000원</strong></div>
			<div class="additional_info">
				<span class="satisfaction">만족도 98%</span>
				<span class="buy">구  &nbsp;&nbsp;매 1285</span>
				<span class="review">상품평 1564</span>
			</div>
			<button class="add_to_cart btn_yellow"></button>
		</div>
	</div>
	<hr class="style14">
	<div class="product_list_content">
		<div class="product_img"><a href="#"><img src="/resources/images/product/washing_machine01.jpg"></a></div>
		<div class="product_info">
			<a class="title" href="#">
			제품 이름
			</a>
			<div class="additional_info">
				<span class="brand">[LG전자]</span>
				<span class="category">
					<a href="#">가전제품</a> > 
					<a href="#">주방가전</a> > 
					<a href="#">냉장고</a></span>
			</div>
		</div>
		<div class="product_addition">
			<div class="price"><strong>600,000원</strong></div>
			<div class="additional_info">
				<span class="satisfaction">만족도 98%</span>
				<span class="buy">구  &nbsp;&nbsp;매 1285</span>
				<span class="review">상품평 1564</span>
			</div>
			<button class="add_to_cart btn_yellow"></button>
		</div>
	</div>
	<hr class="style14">
</div>

<div class="BLOCK50"></div>

</div> <!-- article_wrap 끝 -->

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>