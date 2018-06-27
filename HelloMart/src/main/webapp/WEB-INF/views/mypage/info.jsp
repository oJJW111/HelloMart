<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이 페이지</title>
<style>
#info_container {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
    margin-top: 10px;
}

#info_container td, #info_container th {
    border: 1px solid #ddd;
    padding: 8px;
}

#info_container tr:nth-child(even){background-color: #f2f2f2;}

#info_container th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: center;
}

.wrap {
	width: 800px;
    margin: auto;
}

.sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #111;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 25px;
    color: #818181;
    display: block;
    transition: 0.3s;
    width: 250px;
}

.sidenav a:hover {
    color: #f1f1f1;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    text-align: right;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>

<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>

</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header_nomenu.jsp"/>
<!-- 헤더 -->

<hr>

<div class="BLOCK20"></div>

<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="/mypage/info">회원정보 보기</a>
  <a href="/mypage/modify">회원정보 수정</a>
  <a href="/mypage/modifyPwd">비밀번호 변경</a>
  <a href="/mypage/delete">회원 탈퇴</a>
</div>

<div class="wrap">
	<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; open</span>
	<table id="info_container">
	<tr>
		<th colspan=2>회원정보</th>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${account.id}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${account.name}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${account.email}</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${account.birthYear} 년 ${account.birthMonth} 월 ${account.birthDay} 일</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>
				<c:if test="${account.gender eq 'F'.charAt(0)}">여성</c:if>
				<c:if test="${account.gender eq 'M'.charAt(0)}">남성</c:if>
			</td>
		</tr>
		<tr>
			<th>휴대폰번호</th>
			<td>${account.phone}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${account.postCode}</td>
		</tr>
		<tr>
			<th>도로명주소</th>
			<td>${account.roadAddress}</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>${account.detailAddress}</td>
		</tr>
	</table>
</div>

<div class="BLOCK20"></div>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>