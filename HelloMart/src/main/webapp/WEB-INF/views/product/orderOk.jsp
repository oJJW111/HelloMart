<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a{
		text-decoration: none;
	}
	a:LINK, a:VISITED {
		color: black;
	}
	
	a:HOVER {
		color: blue;
	}
</style>
<script type="text/javascript">
	function buy(){
		location.href = "/productList?mainCategory=" + encodeURIComponent("가전제품");	
	}
</script>
</head>
<body>

<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

	<div align="center">
		<h1>구매가 완료되었습니다</h1>
		
		<br><br>
		
		<a href="#" onclick="buy(); return false;">계속해서 구매하기</a>
		&nbsp;&nbsp; / &nbsp;&nbsp; 
		<a href="/mypage/history">구매 내역 보기</a>
	</div>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->

</body>
</html>