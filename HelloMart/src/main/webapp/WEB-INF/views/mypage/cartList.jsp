<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<script type="text/javascript">
	function fnCartDel(no){
		var isMove = window.confirm("정말 삭제하시겠습니까?");
		
		if(isMove){
			location.href = "/mypage/cartdelete?no="+no;
		}
	}
	function buyCart() {
		document.cart_form.action='/cartBuy';
		document.cart_form.method='post';
		document.cart_form.submit();
	}
	function cartmodify() {
		document.cart_form.action='/mypage/cartmodify';
		document.cart_form.method='post';
		document.cart_form.submit();
	}
</script>
<style type="text/css">
#cart_list table tr{
	text-align: center;
	border: 1px dotted #ababab;
	border-left: none;
	border-right: none;
	height: 30px;
}
#cart_list table tr th{
	text-align: center;
	border: 1px solid #ababab;
	border-left: none;
	border-right: none;
}
#cart_list table tr td{ 
	text-align: center;
}

td {
	text-align: right;
}

.board_btn01 {
	display: inline-block;
	*display: inline;
	padding: 2px 10px;
	height: 25px;
	line-height: 10px;
	background-color: #636363;
	border: 1px solid #EFEFEF;
	color: #fff;
	text-decoration: none;
	font-size: 10pt;
	font-family: 'Nanum Gothic', 돋움;
	letter-spacing: 0px;
}

.board_btn01:hover {
	background-color: #000;
	border: 1px solid #EFEFEF;
	color: #fff;
}

</style>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<!-- 헤더 -->
	<div class="BLOCK80"></div>
	<div class="article_wrap">
		<form name="cart_form" action="#">
			<div id="cart_list">
				<table style="width: 100%">
					<tr>
						<th width="125">이미지</th>
						<th width="130">분류</th>
						<th width="230">상품명</th>
						<th width="120">가격</th>
						<th width="70">수량</th>
						<th width="110">판매자</th>
						<th width="80">취소</th>
					</tr>
					<c:choose>
						<c:when test="${map.count == 0}">
							<tr>
								<td colspan="7" align="center">장바구니가 비어있습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="row" items="${map.list }" varStatus="i">
								<tr class="cart_notice">
									<td><input type="hidden" name="no" value="${row.no }">
										<img src="${row.imagepath }" width="100" height="100"></td>
									<td style="text-align: left; padding: 3px;">[ ${row.smallcategory} ]</td>
									<td style="text-align: left; padding: 20px;">${row.productname }</td>
									<td>￦ &nbsp;<fmt:formatNumber pattern="###,###,###" value="${row.price}" /></td>
									<td><select name="orderCount">
											<c:set var="orderCount" value="${row.orderCount }" />
											<c:if test="${orderCount > 10 }">
							  <script>
	                               (function(){
	                                 alert("주문 개수는 10개를 초과할 수 없습니다.");
	                                  })()
	                          </script>
											<c:set var="orderCount" value="10" />
											</c:if>
											<c:forEach begin="1" end="10" var="i">
												<c:if test="${orderCount == i}">
													<option value="${i}" id="${i}" selected="selected">${i}</option>
												</c:if>
												<c:if test="${orderCount != i}">
													<option value="${i}" id="${i}">${i}</option>
												</c:if>
											</c:forEach>
									</select></td>
									<td>${row.registerid }</td>
									<td><input type="button" name="delete" class="board_btn01"
										value="삭제" onclick="fnCartDel(${row.no})"></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			<div class="BLOCK30"></div>
			<table style="width: 25%; float: right;">
				<tr>
					<td><b>장바구니 금액 합계 :</b></td>
					<td>
						￦&nbsp;<fmt:formatNumber pattern="###,###,###" value="${map.sumMoney}" />
					</td>
				</tr>
				<tr>
					<td><b>배송료 :</b></td>
					<td>￦&nbsp;<fmt:formatNumber pattern="#,###" value="${map.fee}" /></td>
				</tr>
				<tr>
					<td><b>전체 주문금액 :</b></td>
					<td>
						￦&nbsp;<fmt:formatNumber pattern="###,###,###" value="${map.allSum}" />
					</td>
				</tr>
			</table>
			<div class="cart_sub" align="right">
				<input type="button" value="수정" id="cart_stn" onclick="cartmodify()">
				<input type="button" value="구매하기" id="cart_stn" onclick="buyCart()">
			</div>
		</form>

	</div>
	<div class="BLOCK30"></div>
	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
	<!-- 푸터 -->
</body>
</html>