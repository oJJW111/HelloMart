<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
		location.href = "/productList?mainCategory=" + encodeURIComponent("������ǰ");	
	}
</script>
</head>
<body>

<!-- ��� -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- ��� -->

	<div align="center">
		<h1>���Ű� �Ϸ�Ǿ����ϴ�</h1>
		
		<br><br>
		
		<a href="#" onclick="buy(); return false;">����ؼ� �����ϱ�</a>
		&nbsp;&nbsp; / &nbsp;&nbsp; 
		<a href="/mypage/history">���� ���� ����</a>
	</div>

<!-- Ǫ�� -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- Ǫ�� -->

</body>
</html>