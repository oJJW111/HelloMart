<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	    padding: 0 12px;
	}
	
	#info_container tr:nth-child(even){background-color: #f2f2f2;}
	
	#info_container th {
	    padding-top: 12px;
	    padding-bottom: 12px;
	    text-align: center;
	    width: 25%;
	}
	
	.errors {
		color: red;
		float: right;
	}
	.btnlogin1 { 
		display:inline-block;
		border:1px solid #ddd; 
		background:#f2f2f2; 
		color:#333;  
		font-family: Tahoma, 'Roboto', 'arial'; 
		font-size:11px; 
		text-decoration: none; 
	}
	.btnlogin1:hover{ 
		background:#333;
		border:1px solid #333; 
		color:#fff;
	}
	.buttonlogin1 { 
		width:270px; 
		margin:30px 250px;
	}
	.buttonlogin1 span a { 
		width:100%;
		line-height:14px;
		padding:10px 0;
		font-size:13px;
		text-align:center;
		margin:0;
		letter-spacing:0.5px; 
		text-decoration: none;
	}
</style>
<script>
function delSubmit() {
	f.submit();
}
</script>
<form action="/mypage/info/delete" method="post" name="f">
	<table id="info_container">
		<tr>
			<th colspan=2>회원 탈퇴</th>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="pw">
				<c:if test="${param.fail eq 'true'}">
					<span class="errors"><spring:message code="form.error.notequal.password"/></span>
				</c:if>
			</td>
		</tr>
	</table>
	<p class="buttonlogin1">
		<span><a href="javascript:delSubmit();" class="btnlogin1">탈퇴하기</a></span>
	</p>
</form>