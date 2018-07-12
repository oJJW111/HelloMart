<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<body>

<!-- ��� -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- ��� -->

	<center>
		<h2>�ֹ� ������</h2>
		
		<form action="/buyOk" method="post">
		<table> 
			<tr>
				<td rowspan="5">
					<img src="${detail.ImagePath}" width="400px">
				</td>
				<td>��ǰ��</td>
				<td>${detail.ProductName}</td>
			</tr>
			<tr> 
				<td>��ǰ����</td>
				<td>${detail.Price}</td>
			</tr>	
			<tr>
				<td>��ǰ����</td>
				<td>${orderCount}</td>
			</tr>
			<tr>
				<td>��ǰ �� �ݾ�</td>
				<td>${detail.Price * orderCount}</td>
			</tr>
			<tr>
				<td colspan="3">
					<table>
						<tr>
							<td>���� ��� �̸�</td>
							<td>
								<input type="text" name="receiverName" value="${account.name}">
							</td>
						</tr>
						<tr>
							<td>���� ��� ����ó</td>
							<td>
								<input type="text" name="receiverPhone" value="${account.phone}">
							</td>
						</tr>
						<tr>
							<td>�����ȣ</td>
							<td>
								<input type="text" name="receiverPostCode" value="${account.postCode}"
										id="sample6_postcode" readonly="readonly">
								<input type="button" onclick="sample6_execDaumPostcode()" value="�����ȣ ã��">
							</td>
						</tr>
						<tr>
							<td>���θ� �ּ�</td>
							<td>
								<input type="text" name="receiverRoadAddress" value="${account.roadAddress}"
										id="sample6_address" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td>���ּ�</td>
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
					����Ʈ�� ����Ͻðڽ��ϱ�?
					&nbsp;&nbsp;
					<input type="radio" name="incDec" id="usePoint" value="-">��
					&nbsp;&nbsp;
					<input type="radio" name="incDec" id="noUsePoint" value="+" checked="checked">�ƴϿ�
					<div id="divPoint"></div>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					<br><br>
					<input type="submit" value="�ֹ��ϱ�">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="����ϱ�" onclick="location.href='/'">
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

<!-- Ǫ�� -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- Ǫ�� -->

</body>
</html>