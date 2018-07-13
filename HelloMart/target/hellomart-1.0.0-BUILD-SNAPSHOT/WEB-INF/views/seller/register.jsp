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

<script src="/resources/js/birthdatemaker.js"></script>
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>

<link rel="stylesheet" type="text/css" href="/resources/css/pigeon.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/register.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/tooltip.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>물품등록페이지</title>
<script type="text/javascript">
$(document).ready(function(){
	BIRTHDATEMAKER.make({
		year: 'year',
		month: 'month',
		day: 'day',
		begin: 1930,
		end: 2018,
		selectedYear: '${prodDate.selectedYear}',
		selectedMonth: '${prodDate.selectedMonth}',
		selectedDay: '${prodDate.selectedDay}'
	});
	$("#btnRegister").on("click", function(){
		var productImageFile = $("#productImageFile").val();
		var specEngNameList = '${specEngNameList}';
		var specKorNameList = '${specKorNameList}';
		alert(specKorNameList);
		alert(specEngNameList);
		var specEngNameList = specEngNameList.substring(1, specEngNameList.length-1);
		var specKorNameList = specKorNameList.substring(1, specKorNameList.length-1);
		alert(specKorNameList);
		alert(specEngNameList);
		var specEngNameList = specEngNameList.split(',');
		var specKorNameList = specKorNameList.split(',');
		for(var count = 0 ; count < specEngNameList.length; count++){
			specEngNameList[count] = specEngNameList[count].trim();
			specKorNameList[count] = specKorNameList[count].trim();
		}
		var flag = 0;
		var html;
		if(productImageFile == ''){
			$("#fileErrors").html("이미지를 업로드하세요.");
			$("#productImageFile").focus();
			return false;
		}else if(fileExtensionCapacityCheck('productImageFile') == 0){
			return false;
		}
		else{
			$("#fileErrors").html("");
		}
		
		function fileExtensionCapacityCheck(string){
			var result = 1;
			var ext = $("#" + string).val().split('.').pop().toLowerCase();
		    if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
		    	$("#fileErrors").html("gif,png,jpg,jpeg 파일만 업로드 할수 있습니다.");
		    	$("#" + string).val("");
		    	$("#" + string).focus();
		    	result = 0;
		    }
		    var file = document.getElementById(string).files[0];
		    var maxSize = 1024*1024;
		    if(file.size > maxSize){
		    	$("#fileErrors").html("사진 파일용량이 1MB를 초과했습니다.");
		    	$("#" + string).val("");
		    	$("#" + string).focus();
		    	result = 0;
		    }
		    return result;
		}

		for(var count = 0; count < specEngNameList.length; count++){
			var specValue = $("#"+specEngNameList[count]+" option:selected").val();
			alert(specValue + "안돼");
			if(specValue == ''){
				flag = 1;
				$("#" + specEngNameList[count] + "Errors").html(specKorNameList[count] +"를 입력하시지 않았습니다.");
			}else{
				$("#" + specEngNameList[count] + "Errors").html("");
			}
		}
		if(flag == 1){
			return false;
		}
		document.productRegister.submit();
	});
	
}); 
</script>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<!-- 헤더 -->
<div class="BLOCK80"></div>
<div id="register_test">
	<form:form action="/seller/productRegister" name="productRegister" commandName="ProductList" method="post" enctype="multipart/form-data">
		<form:hidden path="mainCategory" value="${mainCategory }"/>
		<form:hidden path="smallCategory" value="${smallCategory }"/>
		<h2>${mainCategory } >> ${smallCategory } 등록하기</h2><br/>
		<div class="box-wrap">
			<ul class="register-form">
				<li>	
					<form:input path="productName" class="txt-input joinTooltip" maxlength="50" placeholder="물품 이름" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.productName"/></span>
					<form:errors path="productName" class="errors"/>
				</li>
				<li>
					<form:input path="mfCompany" class="txt-input joinTooltip" maxlength="30" placeholder="제조 회사" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.mfCompany"/></span>
					<form:errors path="mfCompany" class="errors"/>
				</li>
				<li class="prodDate">
					<dl class="type1">
						<dd>
							<label for="prodDateLabel">제조 날짜</label>
						</dd>
						<%-- <dd>
							<form:select path="prodYear" id="year">
							</form:select>
						</dd>
						<dd>
							<form:select path="prodMonth" id="month">
							</form:select>
						</dd>
						<dd>
							<form:select path="prodDay" id="day">
							</form:select>
						</dd> --%>
						<dd>
							<input type="date">
						</dd>
					</dl>
					<%-- <form:errors path="prodYear" class="errors"/> --%>
				</li>
				<li>
					<form:input path="price" class="txt-input joinTooltip" maxlength="30" placeholder="물품 가격" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.price"/></span>
					<form:errors path="price" class="errors"/>
				</li>
				<li>
					<form:input path="weight" class="txt-input joinTooltip" maxlength="30" placeholder="물품 무게" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.weight"/></span>
					<form:errors path="weight" class="errors"/>
				</li>
				<li>
					<form:textarea path="comment" class="txt-input joinTooltip" cols="50" rows="5" placeholder="코멘트"/>
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.comment"/></span>
					<form:errors path="comment" class="errors"/>
				</li>
				<li class="file">
					<label for="productImageFile" class="control-label">이미지 업로드</label>
					<div class="productImageFile">
						<input type="file" name="productImageFile"  id="productImageFile"/>
					</div>
					<span class="errors" id="fileErrors">${msg }</span>
				</li>				
				
<c:forEach var="specName" items="${specKorNameList}" varStatus="status">
	<c:set var="specName" value="${specName }"/>
				<li class="select">
				<label for="${specEngNameList[status.index] }" class="control-label">${specName }</label>
				<select name='${specEngNameList[status.index] }' id="${specEngNameList[status.index] }" 
					title='${specName }리스트' required="required">
					<option value=''>선택</option>
	<c:forEach var="value" items="${specMapList[specName]}">
					<option value='${value }'>${value }</option>
	</c:forEach>
				</select>
				<span class="errors" id="${specEngNameList[status.index] }Errors">			
				</span>	
				</li>
</c:forEach>
			</ul><br>
			<div class="new-btn-area">
				<span class="btnlogin" id="btnRegister">등록 완료</span>
			</div>
		</div>
	</form:form>
</div>
		<div class=BLOCK50></div>
	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
	<!-- 푸터 -->
</body>
</html>