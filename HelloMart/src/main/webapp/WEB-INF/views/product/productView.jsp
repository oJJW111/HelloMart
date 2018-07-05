<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@	taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<script type="text/javascript">
	function fnBuy(no, smallCategory, id){
		var orderCount = document.getElementById("orderCount").value;
		location.href = "/buy?no=" + no + "&smallCategory=" + smallCategory
								+ "&orderCount=" + orderCount + "&id=" + id; 
	} 
	
	$(function(){
	    $('.__count_range input[count_range]').click(function(e){
	        e.preventDefault();
	        var type = $(this).attr('count_range');
	        var $count = $(this).parent().children('input.count');
	        var count_val = $count.val(); // min 1
	        if(type=='m'){
	            if(count_val<1){
	                return;
	            }
	            $count.val(parseInt(count_val)-1);
	        }else if(type=='p'){
	            $count.val(parseInt(count_val)+1);
	        }
	    });
	});
</script>

</head>
<body>

<sec:authentication property="principal" var="id" />

<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

<div align="center">
	<table style="border: 1px solid;">
		<tr>
			<td rowspan="9">
				<img src="${detail.ImagePath}" width="400px">
			</td>
			<td>이름</td>
			<td>${detail.ProductName}</td>
		</tr>
		<tr>
			<td>주문횟수</td>
			<td>${detail.OrderCount}</td>
		</tr>
		<tr>
			<td>상품평</td>
			<!-- 점수에 따라서 별 이미지로 처리? -->
			<td>${detail.Score}</td> 
		</tr>
		<tr>
			<td>제작년도</td>
			<td>${detail.ProdDate}</td>
		</tr>
		<tr>
			<td>제작회사</td>
			<td>${detail.MfCompany}</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>${detail.Price}</td>	
		</tr>
		<tr>
			<td>무게</td>
			<td>${detail.Weight}</td>
		</tr>
		<tr>	
			<td colspan="2">
				<c:forEach items="${columnList}" varStatus="status">
					<!-- 상세정보 이름 -->	
					${columnList[status.index]} &nbsp;
					
					<!-- 상세정보 값 -->
					<c:if test="${detail[columnListEng[status.index]] == null}">ㅡ</c:if>
					${detail[columnListEng[status.index]]}
					
					<c:if test="${!status.last}"> / &nbsp; </c:if>
				</c:forEach> 
			</td>
		</tr> 
		<tr>
			<td colspan="2">판매자 코멘트 : ${detail.Comment}</td>
		</tr>
	</table>                       
</div>
<br><br>
	<div align="center"> 
		수량 &nbsp;&nbsp;
		<div class="__count_range">
		<input value="-" count_range="m" type="button">
 		<input class="count" value="1" name="orderCount" id="orderCount" type="number">
  		<input value="+" count_range="p" type="button">
	</div>	
	&nbsp;&nbsp;<input type="button" value="구매" 
					onclick="fnBuy(${detail.No}, '${detail.SmallCategory}','${id}')">
	&nbsp;&nbsp;<input type="button" value="장바구니 담기" onclick="">
</div>

<div>
	<jsp:include page="/review?no=${detail.No}"/>
</div>
	
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->

</body>
</html>


