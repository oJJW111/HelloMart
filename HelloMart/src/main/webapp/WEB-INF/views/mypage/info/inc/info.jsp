<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#info_container {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
    margin-top: 10px;
	}
	
	#info_container td, #info_container th {
	    border: 1px solid #ddd;
	    padding: 12px;
	}
	
	#info_container tr:nth-child(even){background-color: #f2f2f2;}
	
	#info_container th {
	    padding-top: 12px;
	    padding-bottom: 12px;
	    text-align: center;
	    width: 25%;
	}
</style>
<sec:authentication var="id" property="principal"/>
<table id="info_container">
	<tr>
		<th colspan=2>회원정보</th>
	</tr>
	<tr>
		<th>아이디</th>
		<td>${id}</td>
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
		<td>${account.birthYear}년 ${account.birthMonth} 월
			${account.birthDay} 일</td>
	</tr>
	<tr>
		<th>성별</th>
		<td><c:if test="${account.gender eq 'F'.charAt(0)}">여성</c:if> <c:if
				test="${account.gender eq 'M'.charAt(0)}">남성</c:if></td>
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