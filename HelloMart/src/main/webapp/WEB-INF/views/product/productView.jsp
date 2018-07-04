<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>

</head>
<body>

<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

<div align="center">
	<table style="border: 1px solid;">
		<tr>
			<td rowspan="9">
				<img src="${product.imagePath}" width="400px" height="300px">
			</td>
			<td>이름</td>
			<td>${product.productName}</td>
		</tr>
		<tr>
			<td>주문횟수</td>
			<td>${product.orderCount}</td>
		</tr>
		<tr>
			<td>상품평</td>
			<!-- 점수에 따라서 별 이미지로 처리? -->
			<td>${product.score}</td> 
		</tr>
		<tr>
			<td>제작년도</td>
			<td>${product.prodDate}</td>
		</tr>
		<tr>
			<td>제작회사</td>
			<td>${product.mfCompany}</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>${product.price}</td>	
		</tr>
		<tr>
			<td>무게</td>
			<td>${product.weight}</td>
		</tr>
		<tr>	
			<td colspan="2">
				<c:forEach items="${columnList}" varStatus="status">
<!-- 상세정보 이름 -->	${columnList[status.index]} &nbsp;
<!-- 상세정보 값 -->	${smallCategoryColumn[columnList[status.index]]} /	
				</c:forEach> 
			</td>
		</tr>
		<tr>
			<td colspan="2">판매자 코멘트 : ${product.comment}</td>
		</tr>
	</table>                       
</div>

<div align="center"> 
	수량 &nbsp;&nbsp;
	<select name="count">
		<option value="1">1개</option>
		<option value="2">2개</option>
		<option value="3">3개</option>	
		<option value="4">4개</option>
		<option value="5">5개</option>
	</select>
	&nbsp;&nbsp;<input type="button" value="구매" onclick="">
</div>

<div>
	<jsp:include page="/review?no=${product.no}"/>
</div>
	
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->

</body>
</html>













