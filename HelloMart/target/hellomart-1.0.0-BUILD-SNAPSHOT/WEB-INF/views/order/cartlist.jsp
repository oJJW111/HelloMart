<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상품 장바구니 목록</title>
</head>
<body>
	<h2>장바구니 확인</h2>
	<c:choose>
		<c:when test="${map.count == 0}">
            장바구니가 비어있습니다.
        </c:when>
		<c:otherwise>
			<form name="form1" id="form1" method="post" action="${path}/cart/update">
				<table border="1">
					<tr>
						<th>상품이미지</th>
						<th>상품명</th>
						<th>단가</th>
						<th>수량</th>
						<th>금액</th>
						<th>리뷰</th>
					</tr>
					<c:forEach var="row" items="${map.list}" varStatus="i">
						<tr>
							<td><img src="images/${row.img}" width="150" height="80">
							<td>${row.Name}</td>
							<td style="width: 80px" align="right">
							<fmt:formatNumber pattern="###,###,###" value="${row.Price}" />
							</td>
							<td>
							<input type="number" style="width: 40px" name="qty" value="${row.qty}" min="1"> 
								<input type="hidden" name="no" value="${row.no}">
								</td>
							<td style="width: 100px" align="right">
							<fmt:formatNumber pattern="###,###,###" value="${row.money}" />
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5" align="right">
						장바구니 금액 합계 : 	<fmt:formatNumber pattern="###,###,###" value="${map.sumMoney}" /> <br> 
						배송료 :	${map.fee} <br> 
						전체 주문금액 : <fmt:formatNumber pattern="###,###,###" value="${map.allSum}" />
						</td>
					</tr>
				</table>
				<input type="hidden" name="count" value="${map.count}">
				<button type="submit" id="reView">리뷰</button>
			</form>
		</c:otherwise>
	</c:choose>ㄴ
</body>
</html>