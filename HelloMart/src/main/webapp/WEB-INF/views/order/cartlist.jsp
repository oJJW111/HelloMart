<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��ǰ ��ٱ��� ���</title>
</head>
<body>
	<h2>��ٱ��� Ȯ��</h2>
	<c:choose>
		<c:when test="${map.count == 0}">
            ��ٱ��ϰ� ����ֽ��ϴ�.
        </c:when>
		<c:otherwise>
			<form name="form1" id="form1" method="post" action="${path}/cart/update">
				<table border="1">
					<tr>
						<th>��ǰ�̹���</th>
						<th>��ǰ��</th>
						<th>�ܰ�</th>
						<th>����</th>
						<th>�ݾ�</th>
						<th>����</th>
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
						��ٱ��� �ݾ� �հ� : 	<fmt:formatNumber pattern="###,###,###" value="${map.sumMoney}" /> <br> 
						��۷� :	${map.fee} <br> 
						��ü �ֹ��ݾ� : <fmt:formatNumber pattern="###,###,###" value="${map.allSum}" />
						</td>
					</tr>
				</table>
				<input type="hidden" name="count" value="${map.count}">
				<button type="submit" id="reView">����</button>
			</form>
		</c:otherwise>
	</c:choose>��
</body>
</html>