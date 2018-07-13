<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>포인트 이력 보기</title>
<script type="text/javascript">
	function search(id){
		var startDate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;
		
		if( (startDate != null) && (startDate != "")){
			if((endDate != null) && (endDate != "")){
				if(startDate > endDate){
					alert("종료날짜는 시작날짜 이전이 될 수 없습니다");
				}
				else{
					location.href = "/mypage/point/period?id=" + id + "&startDate=" + startDate + "&endDate=" + endDate;
				}
			}
			else{
				alert("종료날짜를 선택해주세요");	
			}			
		}
		else{
			alert("시작날짜를 선택해주세요");
		}
	}
</script>
</head>
<body>
<sec:authentication property="principal" var="id"/>
	<br><br>
	<table border="1" align="center" width="100%">
		<tr>
			<td colspan="4" align="center">
				<input type="date" name="startDate" id="startDate" min="2010-01-01">&nbsp;~&nbsp;
				<input type="date" name="endDate" id="endDate" min="2010-01-01">&nbsp;&nbsp;
				<input type="button" value="검색" onclick="search('${id}')">
			</td>
		</tr>
		<tr>
			<td colspan="4"><br><br><br></td>
		</tr>
		<tr>
			<td align="center" width="15%">날짜</td>
			<td align="center" width="15%">증가</td>
			<td align="center" width="15%">감소</td>
			<td align="center" width="55%">내용</td>
		</tr>
		
	<c:forEach var="point" items="${pointList}">
		<tr>
			<td align="center">
				<fmt:formatDate value="${point.date}" pattern="yyyy-MM-dd"/>
			</td>
			<c:if test="${point.incDec == '+'}">
				<td align="center">${point.point}</td>
				<td align="center">-</td>
			</c:if>		
			<c:if test="${point.incDec == '-'}">
				<td align="center">-</td>
				<td align="center">${point.point}</td>
			</c:if>
			<td align="center">${point.content}</td>	
		</tr>
	</c:forEach>
	
	</table>
	<br><br><br><br>
</body>
</html>