<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@	taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/cartTable.css" />
<title>오늘 본 상품</title>
<script type="text/javascript">
function fnCart(no, smallCategory, id){
	var isMove = window.confirm("장바구니 페이지로 이동하시겠습니까?");
	
	if(isMove){
		var orderCount = document.getElementById("orderCount").value;
		location.href = "/addCart?no=" + no + "&orderCount=" + orderCount + "&id=" + id; 	
	}
	else{
		var orderCount = document.getElementById("orderCount").value;
		location.href = "/addCartNo?no=" + no + "&orderCount=" + orderCount 
								+ "&smallCategory=" + smallCategory; 		
	}
}
</script>
</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<sec:authentication property="principal" var="id" />
<!-- 헤더 -->
<div class="titbox">
	<div class="title">
		<span class="name">TODAY VIEW</span>
	</div>
</div>
<!-- 상품리스트 -->
<div class="article_wrap" style="width: 900px;">
	<table id="cartBuy">
		<tr>
			<th width="125">이미지</th>
			<th width="140">분류</th>
			<th width="230">상품명</th>
			<th width="100">가격</th>
			<th width="80">장바구니</th>
		</tr>
		<c:forEach var="board" items="${todayView}">
			<tr>
				<td>
					<a href="javascript:void(0)" onclick="javascript:location.href=encodeURI('/productView?no=${board.no}&smallCategory=${board.smallCategory}')">
						<img src="${board.imagePath}" style="width: 120px;">
					</a>
				</td>
				<td style="text-align: left;">
					<a href="javascript:void(0)"
					onclick="javascript:location.href=encodeURI('/productList?mainCategory=${board.mainCategory}')">${board.mainCategory}</a> > 
					<a href="javascript:void(0)"
					onclick="javascript:location.href=encodeURI('/productList?mainCategory=${board.mainCategory}&smallCategory=${board.smallCategory}')">${board.smallCategory}</a>
				</td>
				<td style="text-align: left;">${board.productName}</td>
				<td>￦&nbsp;<fmt:formatNumber pattern="###,###,###" value="${board.price}" /></td>
				<td>
					<input type="button" value="담기" class="board_btn01" onclick="fnCart(${board.no}, '${board.smallCategory}','${id}')">
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div class="BLOCK30"></div>
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>