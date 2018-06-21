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
<title>회원가입</title>
<script type="text/javascript">
function send(){
	document.f.submit();
}
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
					<form:input path="id" class="txt-input" maxlength="20" placeholder="아이디" />
					<form:errors path="id"/>
				</li>
				<li>
					<form:password path="password" class="txt-input" maxlength="16" placeholder="비밀번호" />
					<form:errors path="password"/>
				</li>
				<li>
					<form:password path="re_password" class="txt-input" maxlength="16" placeholder="비밀번호 확인" />
					<form:errors path="re_password"/>
				</li>
				<li>
					<form:input path="name" class="txt-input" maxlength="20" placeholder="이름" />
					<form:errors path="name"/>	
				</li>
				<li>
					<form:input path="email" class="txt-input" maxlength="100" placeholder="이메일" />
					<form:errors path="email"/>
				</li>
				<li class="birth">
					<dl class="type1" style="margin: 0;">
						<dd>
							<select name="birthYear" class="new-birth" style="width: 60px; height: 40px;">
								<option >생년</option>
								<option value=2001>2001</option>
								<option value=2000>2000</option>
								<option value=1999>1999</option>
								<option value=1998>1998</option>
								<option value=1997>1997</option>
								<option value=1996>1996</option>
								<option value=1995>1995</option>
								<option value=1994>1994</option>
								<option value=1993>1993</option>
								<option value=1992>1992</option>
								<option value=1991>1991</option>
								<option value=1990 selected>1990</option>
								<option value=1989>1989</option>
								<option value=1988>1988</option>
								<option value=1987>1987</option>
								<option value=1986>1986</option>
								<option value=1985>1985</option>
								<option value=1984>1984</option>
								<option value=1983>1983</option>
								<option value=1982>1982</option>
								<option value=1981>1981</option>
								<option value=1980>1980</option>
							</select>
						</dd>
						<dd>
							<select name="birthMonth" class="new-birth" style="width: 60px; height: 40px;">
								<option >월</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>
						</dd>
						<dd>
							<select name="birthDay" class="new-birth" style="width: 60px; height: 40px;">
								<option >일</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
								<option value="24">24</option>
								<option value="25">25</option>
								<option value="26">26</option>
								<option value="27">27</option>
								<option value="28">28</option>
								<option value="29">29</option>
								<option value="30">30</option>
								<option value="31">31</option>
							</select>
						</dd>
					</dl>
				</li>
				<li class="gender">&nbsp;
					<form:radiobutton path="gender" class="input-radio" value="F" label="남"/>
					<form:radiobutton path="gender" class="input-radio" value="M" label="여" checked="checked"/>
					<form:errors path="gender"/>
				</li>
				<li>
					<form:input path="phone" class="txt-input" size="15" maxlength="30" placeholder="휴대폰번호" />
					<form:errors path="phone"/>
				</li>
				<li>
					<form:input path="postCode" id="sample6_postcode" placeholder="우편번호" readonly="true"/>
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
					<form:errors path="postCode"/><br>
				</li>
				<li>
					<form:input path="roadAddress" id="sample6_address" placeholder="주소" readonly="true"/>
					<form:input path="detailAddress" id="sample6_address2" placeholder="상세주소"/>
					<form:errors path="roadAddress"/>
					<form:errors path="detailAddress"/>
				</li>
			</ul><br>
			<div class="new-btn-area" style="height: 65px; margin-top: 10px;" >
				<a href="javascript:send();"> 
					<span class="buttonlogin btnlogin" style="line-height: 35px;">가입 완료</span>
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
