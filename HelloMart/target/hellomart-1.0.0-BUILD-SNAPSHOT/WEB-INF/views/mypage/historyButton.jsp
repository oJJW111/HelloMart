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
<<<<<<< HEAD

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

=======
</head>
<body>
	<c:if test="${check == false}">
		<input type="button" value="리뷰 작성" onclick="fnRv('${no}')">
	</c:if>
	
	<c:if test="${check == true}">
		<input type="button" value="리뷰 수정" onclick="location.href='/remodify?idx=${idx}'">
		&nbsp;&nbsp;&nbsp;
		<input type="button" value="리뷰 삭제" onclick="location.href='/redelete?idx=${idx}'">	
	</c:if>
>>>>>>> branch 'ydm' of https://github.com/oJJW111/HelloMart.git
</body>
</html>