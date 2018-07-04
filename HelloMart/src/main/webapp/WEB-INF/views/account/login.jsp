<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/login.css" />

<title>로그인 페이지</title>
<script>
 window.onload = function() {
	 
    if (getCookie("id")) { // getCookie함수로 id라는 이름의 쿠키를 불러와서 있을 경우
        document.f.id.value = getCookie("id"); //input 이름이 id인곳에 getCookie("id")값을 넣어줌
        document.f.idsave.checked = true; // 체크는 체크됨으로
    }
}
function setCookie(name, value, expiredays) {
    var todayDate = new Date();
    todayDate.setDate(todayDate.getDate() + expiredays);
    document.cookie = name + "=" + encodeURIComponent(value) + "; path=/; expires="
            + todayDate.toGMTString() + ";"
}
function getCookie(Name) {
    var search = Name + "=";
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1)
                end = document.cookie.length;
            return decodeURIComponent(document.cookie.substring(offset, end));
        }
    }
}
function login() {
    if (document.f.idsave.checked == true) { // 아이디 저장을 체크 하였을 때
        setCookie("id", document.f.id.value, 7); //쿠키 이름을 id로 아이디입력필드값을 7일 동안 저장
    } else { // 아이디 저장을 체크 하지 않았을 때
        setCookie("id", document.f.id.value, 0); //날짜를 0으로 저장하여 쿠키 삭제
    }
    $('#loginForm').submit(); //유효성 검사가 통과되면 서버로 전송.
}
function enterKeyTab() {
	if (window.event.keyCode == 13) {
		$('input[name="pw"]').focus();
	}
}
function enterKey() {
	if (window.event.keyCode == 13) {
        login();
   }
}
</script>
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
				<form name='f' action='login' method='post' id="loginForm">
					<ul class="frm-list">
						<li class="id"><label>
							<span class="name">ID(User name)</span>
							<span><input type="text" name="id" maxlength="20" 
										 onblur="document.f.focus();"class="MS_login_id" onkeydown="enterKeyTab()"/></span></label>
						</li>
						<li class="pwd"><label>
							<span class="name">Password</span>
							<span><input type="password" name="pw" maxlength="20" 
										 value="" class="MS_login_pw" onkeydown="enterKey()"/></span></label>
						</li>
						<c:if test="${param.fail == 'true'}">
						<li class="error">
							<span><spring:message code="security.badCredentials"/></span>
						</li>
						</c:if>
					</ul>
					<label id="check"><input type="checkbox" name="idsave"/> 아이디 기억하기</label>
					<input class="buttonlogin btnlogin" type="button" value="LOGIN" onclick="login()">
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
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>