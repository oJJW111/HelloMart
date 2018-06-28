<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<form:form action="" name="f" method="post">
	<table id="info_container">
		<tr>
			<th colspan=2>비밀번호 변경</th>
		</tr>
		<tr>
			<th>기존 비밀번호</th>
			<td>
				<input type="password">
			</td>
		</tr>
		<tr>
			<th>신규 비밀번호</th>
			<td>
				<input type="password">
			</td>
		</tr>
		<tr>
			<th>신규 비밀번호 확인</th>
			<td>
				<input type="password">
			</td>
		</tr>
	</table>
</form:form>