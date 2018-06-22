<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 다음 api js 파일 추가 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/resources/js/daum_postcode_v6.js"></script>
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<!-- 다음 api js 파일 추가 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/join.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/tooltip.css" />
<title>회원가입</title>
<script type="text/javascript">
function send(){
	document.f.submit();
}

$(document).ready(function(){
	for(i=1960; i<2001; i++){
		$("#year").append('<option value='+i+'>'+i+'</option>');
	}
	for(i=1; i<13; i++){
		$("#month").append('<option value='+i+'>'+i+'</option>');
	} 
	for(i=1; i<32; i++){
		$("#day").append('<option value='+i+'>'+i+'</option>');
	} 
	console.log("val():"+$("#month").val());
	
	$("#month").on("change",function(){
		$("#month option:selected").attr("value");
		
		
		console.log("change():"+$("#month option:selected").attr("value"));
	});
});


</script>
</head>
<body>

<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->
<div class="BLOCK80"></div>
<div id="join_test">
	<form:form action="join" name="f" method="post" modelAttribute="account" id="join_form">
		<div class="box-wrap">
			<ul class="join-form">
				<li>	
					<form:input path="id" class="txt-input joinTooltip" maxlength="20" placeholder="아이디" />
						<span class="tooltiptext">아이디를 6~20자까지 입력해주세요.</span>
					<form:errors path="id" class="errors"/>
				</li>
				<li>
					<form:password path="password" class="txt-input joinTooltip" maxlength="16" placeholder="비밀번호" />
						<span class="tooltiptext">비밀번호를 4~16자까지 입력해주세요.</span>
					<form:errors path="password" class="errors"/>
				</li>
				<li>
					<form:password path="re_password" class="txt-input" maxlength="16" placeholder="비밀번호 확인" />
					<form:errors path="re_password" class="errors"/>
				</li>
				<li>
					<form:input path="name" class="txt-input joinTooltip" maxlength="20" placeholder="이름" />
						<span class="tooltiptext">이름은 한글만 입력 가능합니다.</span>
					<form:errors path="name" class="errors"/>
				</li>
				<li>
					<form:input path="email" class="txt-input" maxlength="100" placeholder="이메일" />
					<form:errors path="email" class="errors"/>
				</li>
				<li class="birth">
					<dl>
						<dd>
							<form:select path="birthYear" id="year">
								<form:option value="">생년</form:option>
							</form:select>
						</dd>
						<dd>
							<form:select path="birthMonth" id="month">3
								<form:option value="">월</form:option>
							</form:select>
						</dd>
						<dd>
							<form:select path="birthDay" id="day">
								<form:option value="">일</form:option>
							</form:select>
						</dd>
					</dl>
				</li>
				<li class="gender">&nbsp;
					<form:radiobutton path="gender" class="input-radio" value="F" label="남"/>
					<form:radiobutton path="gender" class="input-radio" value="M" label="여" checked="checked"/>
					<form:errors path="gender" class="errors"/>
				</li>
				<li>
					<form:input path="phone" class="txt-input" size="15" maxlength="30" placeholder="휴대폰번호" />
					<form:errors path="phone" class="errors"/>
				</li>
				<li>
					<form:input path="postCode" id="sample6_postcode" placeholder="우편번호" readonly="true"/>
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
					<form:errors path="postCode" class="errors"/><br>
				</li>
				<li>
					<form:input path="roadAddress" id="sample6_address" placeholder="주소" readonly="true"/>
					<form:input path="detailAddress" id="sample6_address2" placeholder="상세주소"/>
					<form:errors path="roadAddress" class="errors"/>
					<form:errors path="detailAddress" class="errors"/>
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
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>
