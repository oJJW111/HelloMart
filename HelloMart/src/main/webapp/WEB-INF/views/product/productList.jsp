<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/product.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>

<script type="text/javascript">
function createURL(mainCategory, smallCategory, page) {
	var helper = (function() {
		var isFirst = true;
		var isLastAmp = function() {
			return url.lastIndexOf("&") != -1;
		}
		var addFirst = function() {
			if(isFirst) {
				url += "?";
				isFirst = false;
			}
		}
		var removeLast = function() {
			if(isLastAmp) {
				url = url.substr(0, url.length - 1);
			}
		}
		return {
			addFirst : addFirst,
			removeLast : removeLast
		}
	})();
	var url = "/productList";
	if(mainCategory != '' && mainCategory != undefined) {
		helper.addFirst();
		url += "mainCategory=" + mainCategory + "&";
	}
	if(smallCategory != '' && smallCategory != undefined) {
		helper.addFirst();
		url += "smallCategory=" + smallCategory + "&";
	}
	if(page != '' && page != undefined) {
		helper.addFirst();
		url += "page=" + page + "&";
	}
	helper.removeLast();
	return url;
}
$(document).ready(function(){
	$.submitForm = function(f) {
		$(f).submit(function() {
		    $(this).find(":input").filter(function(){return !this.value;}).attr("disabled", "disabled");
		});
	}
});
</script>

</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

<div class="article_wrap">

<div class=BLOCK70></div>

<form action="/productList" method="get"> 
<input type="hidden" name="mainCategory" value="${param.mainCategory}">
<input type="hidden" name="smallCategory" value="${param.smallCategory}">
<div class="category_detail noselect">
	<div class="category_detail_up">
		<div class="category_major">
			<h5>세부 분류</h5>
			<ul>
			<c:if test="${smallCategoryList ne null}">
				<c:forEach var="smallCategory" items="${smallCategoryList}">
					<li onclick="javascript:location.href = createURL('${param.mainCategory}', '${smallCategory}'); return false;">
					<c:out value="${smallCategory}"/>
					</li>
				</c:forEach>
			</c:if>
			</ul>
		</div>
		<div class="category_small">
			<h5>상세검색</h5>
			<c:if test="${smallCategoryColumn ne null}">
				<c:forEach var="column" items="${columnList}" varStatus="status">
					<div>
						<c:out value="${column}"/> <br><br>
						<c:forTokens var="value" items="${smallCategoryColumn[column]}" delims="," varStatus="status">
							<label class="ck_container">
								<input type="checkbox" name="${column}" value="${fn:trim(value)}">
								<span class="checkmark"></span>
								<c:out value="${fn:trim(value)}"/>
							</label>
						</c:forTokens>
					</div>
					<c:if test="${!status.last}"><hr></c:if>
				</c:forEach>
			</c:if>  
		</div> <!-- <div class="category_small"> -->
	</div> <!-- <div class="category_detail_up"> -->
	<div class="category_detail_down">
		<input type="text" placeholder="제품명 검색" name="search">
		<input type="text" placeholder="0원" name="price_range1">
		<div class="range">~</div>
		<input type="text" placeholder="999,999,999원" name="price_range2">
		<div class="currency">원</div>
		<button onclick="$.submitForm(this.form)"
		><i class="fa fa-search"></i></button>
	</div>
</div> <!-- <div class="category_detail noselect"> -->
</form>

<!-- 상품리스트 -->
<div class="product_list">
<c:choose>
	<c:when test="${list ne null}">
		<c:forEach var="board" items="${list}">
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
							<span class="category">
								<a href="/productList/main?mainCategory=${param.mainCategory}">${param.mainCategory}</a> > 
								<a href="/productList/small?mainCategory=${param.mainCategory}&smallCategory=${smallCategory}">${param.smallCategory}</a>
							</span>
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
						<button class="add_to_cart btn_yellow"></button>
					</div>
				</div> <!-- <div class="product_list_content"> -->
				<hr class="style14">
		</c:forEach>
	</c:when>
	<c:otherwise>
		<h4>해당되는 상품이 없습니다.</h4>
	</c:otherwise>
</c:choose>
</div> <!-- 상품리스트 -->

<!-- <div class="BLOCK50"></div> -->

</div> <!-- article_wrap 끝 -->

<br>
<div align="center">
	<c:if test="${paging.totalRecord gt 0}">
		<c:if test="${paging.nowBlock gt 0}">
			<a href="javascript:void(0);" 
			onclick="javascript:location.href = createURL('${param.mainCategory}', '${param.smallCategory}', '${paging.beginPage - 1}');">[이전]</a>
		</c:if>
		<c:forEach 	var="i"
					begin="${paging.beginPage}"
					end="${paging.endPage}">
			<a href="javascript:void(0);" 
			onclick="javascript:location.href = createURL('${param.mainCategory}', '${param.smallCategory}', '${i}');">[${i}]</a>
		</c:forEach>
		<c:if test="${paging.nowBlock lt paging.totalBlock}">
			<a href="javascript:void(0);" 
			onclick="javascript:location.href = createURL('${param.mainCategory}', '${param.smallCategory}', '${paging.endPage + 1}');">[다음]</a>
		</c:if>
	</c:if>
</div>
<br>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>