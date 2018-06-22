<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/login.css" />
<title>로그인 페이지</title>
</head>
<body>

<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->
<div class="BLOCK80"></div>
<div id="loginWrap">
	<div class="page-body">
		<div class="mlog-sign">
			<div class="mlog">
				<form name='f' action='login' method='post'>
					<ul class="frm-list">
						<li class="id"><label>
							<span class="name">ID(User name)</span>
							<span><input type="text" name="id" maxlength="20" 
										 onblur="document.f.focus();"class="MS_login_id" /></span></label>
						</li>
						<li class="pwd"><label>
							<span class="name">Password</span>
							<span><input type="password" name="pw" maxlength="20" 
										 value="" class="MS_login_pw" /></span></label>
						</li>
					</ul>
					<input class="buttonlogin btnlogin" style="height: 35px;" type="submit" value="LOGIN">
				</form>
			</div>
			<div class="sign">
				<p class="msg">회원가입</p>
				<p class="buttonlogin1">
					<span><a href="join" class="btnlogin1">JOIN US</a></span>
				</p>
				<p class="msg">아이디 비밀번호 찾기</p>
				<p class="buttonlogin1">
					<span><a href="javascript:findIdPw();" class="btnlogin1">ID/PW SEARCH</a></span>
				</p>
			</div>
		</div>
	</div>
</div>		
<div class="BLOCK95"></div>
<c:if test="${param.fail == 'true'}">
<script type="text/javascript">
 alert('<spring:message code="security.badCredentials"/>');
</script>
</c:if>
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>