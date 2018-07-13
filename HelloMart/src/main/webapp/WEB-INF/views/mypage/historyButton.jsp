<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function fnRv(no) {
		location.href = "/reWrite?no=" + no;
	}
</script>


</head>
<body>

			<c:if test="${check == false}">
				<input type="button" value="리뷰작성" onclick="fnRv('${no}')">
			</c:if>
			<c:if test="${check == true}">
				<input type="button" value="수정"
					onclick="location.href='/remodify?idx=${idx}'">&nbsp;&nbsp;&nbsp;
				<input type="button" value="삭제"
					onclick="location.href='/redelete?idx=${idx}'">	
			</c:if>


</body>
</html>