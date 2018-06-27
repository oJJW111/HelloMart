<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/resources/js/daum_postcode_v6.js"></script>

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
</style>

<form:form action="join" name="f" method="post" modelAttribute="account" id="join_form">
	<table id="info_container">
		<tr>
			<th colspan=2>회원정보</th>
		</tr>
		<tr>
			<th>아이디</th>
			<td>
				<form:input path="id" class="txt-input joinTooltip" maxlength="20" value="${account.id}" />
				<form:errors path="id" class="errors"/>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<form:input path="name" class="txt-input joinTooltip" maxlength="20" value="${account.name}" />
				<form:errors path="name" class="errors"/>
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<form:input path="email" class="txt-input joinTooltip" maxlength="20" value="${account.email}" />
				<form:errors path="email" class="errors"/>
			</td>
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
			<td>
				<form:input path="phone" class="txt-input joinTooltip" maxlength="20" value="${account.phone}" />
				<form:errors path="phone" class="errors"/>
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>
				<form:input path="postCode" id="sample6_postcode" placeholder="우편번호" readonly="true"/>
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
				<form:errors path="postCode" class="errors"/><br>
			</td>
		</tr>
		<tr>
			<th>도로명주소</th>
			<td>
				<form:input path="roadAddress" id="sample6_address" placeholder="주소" readonly="true" value="${account.roadAddress}"/>
				<form:errors path="roadAddress" class="errors"/>
			</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>
				<form:input path="detailAddress" id="sample6_address2" placeholder="상세주소"/>
				<form:errors path="detailAddress" class="errors" value="${account.detailAddress}" />
			</td>
		</tr>
	</table>
	<form:button value="modify"/>
</form:form>