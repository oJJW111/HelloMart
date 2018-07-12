<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- ���� api js ���� �߰� -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/resources/js/daum_postcode_v6.js"></script>
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<!-- ���� api js ���� �߰� -->
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�ֹ� ������</title>
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
<!-- ��� -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- ��� -->

	<center>
		<div class=BLOCK70></div>
		<c:set var="totalPrice" value="0" />
		<c:set var="size" value="0"/> 
		<form action="/cartBuyOk" method="post">
		<table width="900" border="1"> 
			<!-- ��ٱ��Ͽ� ����ִ� ��ǰ ����Ʈ -->
			<tr>
				<th width="180">��ǰ�̹���</th>
				<th>��ǰ����</th>
				<th>��ǰ�ݾ�x����</th>
				<th>�ѱݾ�</th>
			</tr>
			<c:forEach items="${productList}" varStatus="status">
				<tr>
					<td><img src="${productList[status.index].imagePath}" width="130"></td>				
					<td style="text-align: left;">[ ${productList[status.index].productName} ] </td>
					<td>��&nbsp; ${productList[status.index].price} x ${orderCountList[status.index]}</td>
					<c:set var="orderPrice" value="${productList[status.index].price * orderCountList[status.index]}" />
					<c:set var="totalPrice" value="${totalPrice + orderPrice}" />
					<td>��&nbsp; ${orderPrice}</td>
				</tr>
				
			<%-- 	
				<tr>
					<td rowspan="4">
						<img src="${productList[status.index].imagePath}" width="200px">
					</td>
					<td>��ǰ��</td>
					<td>${productList[status.index].productName}</td>
				</tr> 
				<tr>
					<td>��ǰ����</td>
					<td>${productList[status.index].price}</td>
				</tr> 
				<tr>
					<td>��ǰ����</td>
					<td>${orderCountList[status.index]}</td>
				</tr>
				<tr>
					<td>�ݾ� �հ�</td>
					<c:set var="orderPrice" value="${productList[status.index].price * orderCountList[status.index]}" />
					<c:set var="totalPrice" value="${totalPrice + orderPrice}"  />
					<td>${orderPrice}</td>
				</tr>  
				 --%>
				<!-- �ֹ�����Ʈ ���̺� �� ���� -->
				<input type="hidden" name="prodNo${status.index}" value="${productList[status.index].no}">
				<input type="hidden" name="orderCount${status.index}" value="${orderCountList[status.index]}">
				<input type="hidden" name="orderPrice${status.index}" value="${orderPrice}">
				
				<c:if test="${status.first}">
					<!-- ����Ʈ �̷� ���̺� ǥ���� ��ǥ ��ǰ �̸� -->
					<input type="hidden" name="prodName0" value="${productList[status.index].productName}">			
				</c:if>
				<c:if test="${status.last}">
					<c:set var="size" value="${status.index}" />
				</c:if> 
			</c:forEach> 
			</table>
					�� �ݾ� �հ� : ${totalPrice}
			
			<!-- ��ǰ ������ ���� -->
		
		<%-- 	���� ��� �̸�<input type="text" name="receiverName" value="${account.name}">
			���� ��� ����ó<input type="text" name="receiverPhone" value="${account.phone}">
			�����ȣ<input type="text" name="receiverPostCode" value="${account.postCode}"
						id="sample6_postcode" readonly="readonly">
				<input type="button" onclick="sample6_execDaumPostcode()" value="�����ȣ ã��">
			���θ� �ּ�<input type="text" name="receiverRoadAddress" value="${account.roadAddress}"
						id="sample6_address" readonly="readonly">
			���ּ�<input type="text" name="receiverDetailAddress" value="${account.detailAddress}"
						id="sample6_address2">
			����Ʈ�� ����Ͻðڽ��ϱ�?
			&nbsp;&nbsp; <input type="radio" name="incDec" id="usePoint" value="-">��
			&nbsp;&nbsp; <input type="radio" name="incDec" id="noUsePoint" value="+" checked="checked">�ƴϿ�
			<div id="divPoint"></div> --%>
			
			
			<input type="submit" value="�ֹ��ϱ�">&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="����ϱ�" onclick="location.href='/mypage/cartlist'">
		<input type="hidden" name="orderId" id="orderId" value="${account.id}">
		<!-- ��ٱ��Ͽ� �� ������ ��ǰ�� ����־����� -->
		<input type="hidden" name="size" value="${size}">
		<input type="hidden" name="orderStatus" value="PAY_OK">
		<!-- ����Ʈ ������ ���� �� �հ� �ݾ� --> 
		<input type="hidden" name="totalPrice" value="${totalPrice}">
		</form>
	</center>

<!-- Ǫ�� -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- Ǫ�� -->

</body>
</html>


