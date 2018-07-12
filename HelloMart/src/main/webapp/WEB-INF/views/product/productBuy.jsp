<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<body>

<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

	<center>
		<h2>주문 페이지</h2>
		
		<form action="/buyOk" method="post">
		<table> 
			<tr>
				<td rowspan="5">
					<img src="${detail.ImagePath}" width="400px">
				</td>
				<td>상품명</td>
				<td>${detail.ProductName}</td>
			</tr>
			<tr> 
				<td>상품가격</td>
				<td>${detail.Price}</td>
			</tr>	
			<tr>
				<td>상품수량</td>
				<td>${orderCount}</td>
			</tr>
			<tr>
				<td>상품 총 금액</td>
				<td>${detail.Price * orderCount}</td>
			</tr>
			<tr>
				<td colspan="3">
					<table>
						<tr>
							<td>받을 사람 이름</td>
							<td>
								<input type="text" name="receiverName" value="${account.name}">
							</td>
						</tr>
						<tr>
							<td>받을 사람 연락처</td>
							<td>
								<input type="text" name="receiverPhone" value="${account.phone}">
							</td>
						</tr>
						<tr>
							<td>우편번호</td>
							<td>
								<input type="text" name="receiverPostCode" value="${account.postCode}"
										id="sample6_postcode" readonly="readonly">
								<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
							</td>
						</tr>
						<tr>
							<td>도로명 주소</td>
							<td>
								<input type="text" name="receiverRoadAddress" value="${account.roadAddress}"
										id="sample6_address" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td>상세주소</td>
							<td>
								<input type="text" name="receiverDetailAddress" value="${account.detailAddress}"
										id="sample6_address2">
							</td>
						</tr>						
					</table>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					포인트를 사용하시겠습니까?
					&nbsp;&nbsp;
					<input type="radio" name="incDec" id="usePoint" value="-">예
					&nbsp;&nbsp;
					<input type="radio" name="incDec" id="noUsePoint" value="+" checked="checked">아니오
					<div id="divPoint"></div>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					<br><br>
					<input type="submit" value="주문하기">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="취소하기" onclick="location.href='/'">
				</td>
			</tr>
		</table>
		<input type="hidden" name="orderId" id="orderId" value="${account.id}">
		<input type="hidden" name="prodNo" value="${detail.No}">
		<input type="hidden" name="prodName" value="${detail.ProductName}">
		<input type="hidden" name="orderCount" value="${orderCount}">
		<input type="hidden" name="orderPrice" value="${detail.Price * orderCount}">
		<input type="hidden" name="orderStatus" value="PAY_OK">
		</form>
	</center>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->

</body>
</html>