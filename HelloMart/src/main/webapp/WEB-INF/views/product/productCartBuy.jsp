<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2><font color="yellow">결제 페이지</font></h2>
		
		<table width="800">
			<tr height="40">
				<td align="center" width="200">
					<font size="2" color="white">상품이미지</font>
				</td>
				<td align="center" width="200">
					<font size="2" color="white">상품명</font>
				</td>
				<td align="center" width="100">
					<font size="2" color="white">상품가격</font>
				</td>
				<td align="center" width="100">
					<font size="2" color="white">상품수량</font>
				</td>
				<td align="center" width="200">
					<font size="2" color="white">상품총금액</font>
				</td>
			</tr>

<%-- 
<c:set var="total" value="${0}"/>
<c:if test="${cart != null}">
	<c:forEach var="item" items="${cart.itemlist}">
			<tr height="80">
				<td align="center" width="200">
					<font size="2" color="white">
						<img src="img/${item.suimg}" width="180" height="70">
					</font>
				</td>
				<td align="center" width="200">
					<font size="2" color="white">${item.suname}</font>
				</td>
				<td align="center" width="100">
					<font size="2" color="white">${item.suprice}원</font>
				</td>
				<td align="center" width="100">
					<font size="2" color="white">${item.suqty}</font>
				</td>
				<td align="center" width="200">
					<font size="2" color="white">${item.suprice * item.suqty}</font>
				</td>
			</tr>	
					
		<c:set var="total" value="${total + item.suprice * item.suqty}" />
	</c:forEach>
</c:if>
			<tr height="70">
				<td align="center" colspan="5">
					<font color="red" size="5">총 결제 금액 = ${total}원 입니다</font>
				</td>
			</tr> --%>
			<tr height="70">
				<td align="center" colspan="5">
					<input type="button" value="계산 완료하기"
							onclick="location.href='cartalldel.do'">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="취소하기"
							onclick="location.href='index.do'">
				</td>
			</tr>
		</table>
	</center>

</body>
</html>

