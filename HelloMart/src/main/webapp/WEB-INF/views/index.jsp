<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

<!-- 캐러셀 -->
<jsp:include page="/WEB-INF/views/inc/carousel.jsp"/>
<!-- 캐러셀 -->

<div class="BLOCK600"> 본문데스 </div>
		
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
<script>
var a = {
	name : 'defaultName',
	pass : 'defaultpass'
}

var b = {
	name : '홍길동',
}
$.extend(a, b);
console.log('a.name : ' + a.name + ', a.pass : ' + a.pass);
</script>
</body>
</html>
