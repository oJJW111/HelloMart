<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/pigeon.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/join.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/tooltip.css" />
<title>물품등록페이지</title>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<!-- 헤더 -->
<div class="BLOCK80"></div>
<div id="join_test">
	<form:form action="/seller/productRegister" name="productRegister" method="post" enctype="multipart/form-data">
		<div class="box-wrap">
			<ul class="join-form">
				<li>	
					<form:input path="id" class="txt-input joinTooltip" maxlength="20" placeholder="아이디" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.id"/></span>
					<form:errors path="id" class="errors"/>
				</li>
				<li>
					<form:password path="password" value="${account.password}" class="txt-input joinTooltip" maxlength="16" placeholder="비밀번호" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.password"/></span>
					<form:errors path="password" class="errors"/>
				</li>
				<li>
					<form:password path="re_password" class="txt-input" maxlength="16" placeholder="비밀번호 확인" />
					<form:errors path="re_password" class="errors"/>
				</li>
				<li>
					<form:input path="name" class="txt-input joinTooltip" maxlength="20" placeholder="이름" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.name"/></span>
					<form:errors path="name" class="errors"/>
				</li>
				<li>
					<form:input path="email" class="txt-input" maxlength="100" placeholder="이메일" />
					<form:errors path="email" class="errors"/>
				</li>
				<li class="gender">&nbsp;
					<form:radiobutton path="gender" class="input-radio" value="F" label="남"/>
					<form:radiobutton path="gender" class="input-radio" value="M" label="여" checked="checked"/>
					<form:errors path="gender" class="errors"/>
				</li>
			</ul><br>
			<div class="new-btn-area">
				<a href="javascript:send();"> 
					<span class="btnlogin">가입 완료</span>
				</a>
			</div>
		</div>
	</form:form>
</div>
	<!-- //UI Object -->

		${mainCategory } >> ${smallCategory }<br/>
		<c:forEach var="specName" items="${specKorNameList}" varStatus="status">
				${specName}=${specEngNameList[status.index] }
				<c:set var="specName" value="${specName }"/>
			<c:forEach var="value" items="${specMapList[specName]}">
				${value }
			</c:forEach>
			<br/>
		</c:forEach>
		<div class=BLOCK50></div>
	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
	<!-- 푸터 -->
</body>
</html>