<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

var BIRTHDATEMAKER = function() {
		var dayOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
		
		var appendOption = function(id, value, valueAttr) {
			$('#'+id).append('<option value=' + valueAttr + '>' + value + '</option>');
		}
		
		var dayId = 'day';
		
		appendOption('year', '생년', '');
		var y = function(n, m) { 
			for(i=n; i<=m; i++){
				appendOption('year', i, i);
			}
		}(1950, 2018);

		appendOption('month', '월', '');
		var m = function() {
			for(i=1; i<=12; i++){
				appendOption('month', i, i);
			}
		}();
		
		appendOption('day', '일', '');
		var d = function(n) {
			for(i=1; i<=n; i++){
				appendOption('day', i, i);
			} 
		};
		
		var getSelectedVal = function(id) {
			return $('#' + id + ' option:selected').attr("value");
		}
		
		var dayEmpty = function() {
			$('#' + dayId).empty();
		}
		
		$("#month").on("change",function(){
			var y = getSelectedVal('year');
			
			if(y != "") {
				var m = getSelectedVal('month');
				
				calculateDay(m, y);
			}
		});
		
		$("#year").on("change",function(){
			m = getSelectedVal('month');
			
			if(m != "") {
				y = getSelectedVal('year');
				
				calculateDay(m, y);
			}
		});
		
		var dayOnChangeEvent = function() {
			
		}
		
		var calculateDay = function(m, y) {
			dayEmpty();
			
			appendOption('day', '일', '');
			
			$tempOfDay = dayOfMonth[m-1];
			
			$day = $tempOfDay;
			
			$b = false;
			if(m == 2) {
				if((y % 400) == 0) {
					$b = true;
				} else if((y % 4) == 0 && (y % 100) != 0) {
					$b = true;
				}
			}
			
			if($b) {
				$day++;
			}
			
			d($day);
		}
	};
	BIRTHDATEMAKER();
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
				<li class="birth">
					<dl class="type1" style="margin: 0;">
						<dd>
							<form:select path="birthYear" id="year">
							</form:select>
						</dd>
						<dd>
							<form:select path="birthMonth" id="month">
							</form:select>
						</dd>
						<dd>
							<form:select path="birthDay" id="day">
							</form:select>
						</dd>
					</dl>
					<form:errors path="birthYear" class="errors"/>
				</li>
				<li class="gender">&nbsp;
					<form:radiobutton path="gender" class="input-radio" value="F" label="남"/>
					<form:radiobutton path="gender" class="input-radio" value="M" label="여" checked="checked"/>
					<form:errors path="gender" class="errors"/>
				</li>
				<li>
					<form:input path="phone" class="txt-input" maxlength="20" placeholder="휴대폰번호" />
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
