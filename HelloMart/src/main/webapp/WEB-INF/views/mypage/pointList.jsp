<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>포인트 이력 보기</title>
<script type="text/javascript">
	function search(id){
		var startDate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;
		
		location.href = "/pointList?id=" + id + "&startDate=" + startDate + "&endDate=" + endDate;
	}
</script>
</head>
<body>
<sec:authentication property="principal" var="id"/>
	<table border="1">
		<tr>
			<td colspan="4">
				<input type="date" name="startDate" id="startDate" min="2010-01-01"> ~부터 &nbsp;&nbsp;
				<input type="date" name="endDate" id="endDate" min="2010-01-01"> ~까지 &nbsp;&nbsp;
				<input type="button" value="검색" onclick="search('${id}')">
			</td>
		</tr>
		<tr>
			<td>날짜</td>
			<td>증가</td>
			<td>감소</td>
			<td>내용</td>
		</tr>
	<c:forEach var="point" items="${pointList}">
		<tr>
			<td>${point.date}</td>
			<c:if test="${point.incDec == '+'}">
				<td>${point.point}</td>
				<td>-</td>
			</c:if>		
			<c:if test="${point.incDec == '-'}">
				<td>-</td>
				<td>${point.point}</td>
			</c:if>
			<td>${point.content}</td>	
		</tr>
	</c:forEach>
	</table>
</body>
</html>