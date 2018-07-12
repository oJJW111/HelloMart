<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 다음 api js 파일 추가 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/resources/js/daum_postcode_v6.js"></script>
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<!-- 다음 api js 파일 추가 -->
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>주문 페이지</title>
<script type="text/javascript">
	$(function(){
		$('#usePoint').on({
			"click" : function(){ 
				$('#divPoint').load("/pointView?id=${account.id}");
			}
		});
		
		$('#noUsePoint').on({
			"click" : function(){ 
				$('#divPoint').empty();
			}
		});
	});
</script>
<style type="text/css">
table tr{
	text-align: center;
	border: 1px dotted #ababab;
	border-left: none;
	border-right: none;
	height: 30px;
}
table tr th{
	text-align: center;
	border: 1px solid #ababab;
	border-left: none;
	border-right: none;
}
table tr td{ 
	text-align: center;
}

</style>
</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

	<center>
		<div class=BLOCK70></div>
		<c:set var="totalPrice" value="0" />
		<c:set var="size" value="0"/> 
		<form action="/cartBuyOk" method="post">
		<table width="900" border="1"> 
			<!-- 장바구니에 담겨있던 상품 리스트 -->
			<tr>
				<th width="180">상품이미지</th>
				<th>상품정보</th>
				<th>상품금액x수량</th>
				<th>총금액</th>
			</tr>
			<c:forEach items="${productList}" varStatus="status">
				<tr>
					<td><img src="${productList[status.index].imagePath}" width="130"></td>				
					<td style="text-align: left;">[ ${productList[status.index].productName} ] </td>
					<td>￦&nbsp; ${productList[status.index].price} x ${orderCountList[status.index]}</td>
					<c:set var="orderPrice" value="${productList[status.index].price * orderCountList[status.index]}" />
					<c:set var="totalPrice" value="${totalPrice + orderPrice}" />
					<td>￦&nbsp; ${orderPrice}</td>
				</tr>
				
			<%-- 	
				<tr>
					<td rowspan="4">
						<img src="${productList[status.index].imagePath}" width="200px">
					</td>
					<td>상품명</td>
					<td>${productList[status.index].productName}</td>
				</tr> 
				<tr>
					<td>상품가격</td>
					<td>${productList[status.index].price}</td>
				</tr> 
				<tr>
					<td>상품수량</td>
					<td>${orderCountList[status.index]}</td>
				</tr>
				<tr>
					<td>금액 합계</td>
					<c:set var="orderPrice" value="${productList[status.index].price * orderCountList[status.index]}" />
					<c:set var="totalPrice" value="${totalPrice + orderPrice}"  />
					<td>${orderPrice}</td>
				</tr>  
				 --%>
				<!-- 주문리스트 테이블에 들어갈 값들 -->
				<input type="hidden" name="prodNo${status.index}" value="${productList[status.index].no}">
				<input type="hidden" name="orderCount${status.index}" value="${orderCountList[status.index]}">
				<input type="hidden" name="orderPrice${status.index}" value="${orderPrice}">
				
				<c:if test="${status.first}">
					<!-- 포인트 이력 테이블에 표시할 대표 상품 이름 -->
					<input type="hidden" name="prodName0" value="${productList[status.index].productName}">			
				</c:if>
				<c:if test="${status.last}">
					<c:set var="size" value="${status.index}" />
				</c:if> 
			</c:forEach> 
			</table>
					총 금액 합계 : ${totalPrice}
			
			<!-- 상품 수령인 정보 -->
		
		<%-- 	받을 사람 이름<input type="text" name="receiverName" value="${account.name}">
			받을 사람 연락처<input type="text" name="receiverPhone" value="${account.phone}">
			우편번호<input type="text" name="receiverPostCode" value="${account.postCode}"
						id="sample6_postcode" readonly="readonly">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
			도로명 주소<input type="text" name="receiverRoadAddress" value="${account.roadAddress}"
						id="sample6_address" readonly="readonly">
			상세주소<input type="text" name="receiverDetailAddress" value="${account.detailAddress}"
						id="sample6_address2">
			포인트를 사용하시겠습니까?
			&nbsp;&nbsp; <input type="radio" name="incDec" id="usePoint" value="-">예
			&nbsp;&nbsp; <input type="radio" name="incDec" id="noUsePoint" value="+" checked="checked">아니오
			<div id="divPoint"></div> --%>
			
			
			<input type="submit" value="주문하기">&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="취소하기" onclick="location.href='/mypage/cartlist'">
		<input type="hidden" name="orderId" id="orderId" value="${account.id}">
		<!-- 장바구니에 몇 종류의 상품이 들어있었는지 -->
		<input type="hidden" name="size" value="${size}">
		<input type="hidden" name="orderStatus" value="PAY_OK">
		<!-- 포인트 적립을 위한 총 합계 금액 --> 
		<input type="hidden" name="totalPrice" value="${totalPrice}">
		</form>
	</center>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->

</body>
</html>


