<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function fnRv(no) {
		location.href = "/reWrite?no=" + no;
	}
</script>

</head>
<body>

		<c:choose>
			<c:when test="${check == 0}">
				<input type="button" value="�����ۼ�" onclick="fnRv('${no}')">
			</c:when>
			<c:otherwise>
				<input type="button" value="����"
					onclick="location.href='/remodify?idx=${idx}'">
			</c:otherwise>
		</c:choose>

</body>
</html>