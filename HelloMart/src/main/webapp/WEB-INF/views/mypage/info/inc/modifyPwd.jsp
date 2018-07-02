<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" type="text/css" href="/resources/css/mypage.css" />
<script>
function modifySubmit() {
	f.submit();
}
</script>
<form action="/mypage/info/modifyPwd" method="post" name="f">
	<table id="info_container">
		<tr>
			<th colspan=2>비밀번호 변경</th>
		</tr>
		<tr>
			<th>기존 비밀번호</th>
			<td>
				<input type="password" name="pw"/>
				<c:if test="${param.pwfail eq 'true'}">
					<span class="errors"><spring:message code="form.error.notequal.password"/></span>
				</c:if>
			</td>
		</tr>
		 <tr>
			<th>신규 비밀번호</th>
			<td>
				<input type="password" name="new_pw" class="joinTooltip"/>
				<span class="tooltiptext"><spring:message code="form.tooltip.validation.password"/></span>
			</td>
		</tr>
		<tr>
			<th>신규 비밀번호 확인</th>
			<td>
				<input type="password" name="re_pw"/>
				<c:if test="${param.refail eq 'true'}">
					<span class="errors"><spring:message code="form.error.notequal.password"/></span>
				</c:if>
			</td>
		</tr>
	</table>
	<p class="buttonlogin1">
		<span><a href="javascript:modifySubmit();" class="btnlogin1">수정 완료</a></span>
	</p>
</form>