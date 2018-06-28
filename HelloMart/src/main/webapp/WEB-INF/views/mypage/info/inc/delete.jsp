<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	    padding: 8px;
	}
	
	#info_container tr:nth-child(even){background-color: #f2f2f2;}
	
	#info_container th {
	    padding-top: 12px;
	    padding-bottom: 12px;
	    text-align: center;
	}
</style>
<script>

</script>
<form:form action="/mypage/info/delete" method="post">
	<sec:authentication var="id" property="principal"/>
	<table id="info_container">
		<tr>
			<th colspan=2>회원 탈퇴</th>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="pw">
			</td>
		</tr>
	</table>
	<input type="submit" value="탈퇴">
</form:form>