<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 다음 api js 파일 추가 -->
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/daum_postcode_v4.js"></script>
<script src="resources/jQuery/jQuery-2.1.3.min.js"></script>
<!-- 다음 api js 파일 추가 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/join.css" />
<title>회원가입</title>
<script type="text/javascript">
function send(){
	document.f.submit();
}
//회원가입 input태그 클릭 함수
(function($) {
	$(function() {
	    $('.input-label').keypress(function(){
	        $(this).trigger('focusin');
	    });
	    $('.input-label').each(function() {
	        if($(this).val().length) {
	            $(this).trigger('focusin');
	        }
	    });
	    
		$('.input-label').focusin(function(){
			$(this).css({'opacity':1, filter: 'alpha(enabled="false")'});
		});
		$('.input-label').focusout(function(){
			if(!$(this).val()) {
				$(this).animate({'opacity':0}, 500);
			} else {
				$(this).css({'opacity':1});
			}
		});
	});
})(jQuery);

</script>
</head>
<body>
<h2 align="center">회원가입</h2>
<div id="join_test">
	<form:form action="join" name="f" method="post" modelAttribute="account" id="join_form">
		<div class="box-wrap">
			<ul class="join-form">
				<li><label>이름</label> 
					<input type="text" name="hname" id="hname" value=""
					class="MS_input_txt w137 txt-input input-label" size="15" maxlength="30" /></li>
				<li><label>아이디</label> 
					<input type="text" name="id" id="id" value=""
					class="MS_input_txt w137 txt-input input-label" size="10" maxlength="12" /></li>
				<li><label>비밀번호</label> 
					<input type="password" name="password1" id="password1"
					class="MS_input_txt w137 txt-input input-label" value="" size="15" maxlength="20" /></li>
				<li><label>비밀번호 확인</label> 
					<input type="password" name="password2" id="password2"
					class="MS_input_txt w137 txt-input input-label" value="" size="15" maxlength="20" /></li>
				<li><label>이메일</label> 
					<input type="hidden" name="oldemail" value="" /> 
					<input type="text" name="email" id="email" class="MS_input_txt MS_input_txt06 w137 txt-input input-label" 
					size="20" maxlength="35" id="email" value="" /></li>
				<li class="birth">
					<dl class="type1">
						<dd>
							<select name="birthyear" class="new-birth" style="width: 60px; height: 40px;">
								<option value="">생년</option>
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
							<select name="birthmonth" class="new-birth" style="width: 60px; height: 40px;">
								<option value="">월</option>
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
							<select name="birthdate" class="new-birth" style="width: 60px; height: 40px;">
								<option value="">일</option>
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
					<label><input type="radio" name="sex" value="1" class="input-radio" />남</label> 
					<label><input type="radio" name="sex" value="2" class="input-radio" checked />여</label>
				</li>
				<li><label>휴대폰번호</label> 
					<input type="text" name="phone" id="phone"
					class="MS_input_tel w137 txt-input input-label" size="15" maxlength="30" value="" /></li>
				<li>
					<input type="text" name="postcode" id="sample6_postcode" placeholder="우편번호"> 
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br></li>
				<li>
					<input type="text" name="address" id="sample6_address" placeholder="주소"> 
					<input type="text" name="address2" id="sample6_address2" placeholder="상세주소"></li>
			</ul><br>
			<div class="new-btn-area" style="height: 65px; margin-top: 10px;" >
				<a href="javascript:send();"> 
					<span class="buttonlogin btnlogin" style="line-height: 35px;">가입 완료</span>
				</a>
			</div>
		</div>
	</form:form>
</div>
</body>
</html>
