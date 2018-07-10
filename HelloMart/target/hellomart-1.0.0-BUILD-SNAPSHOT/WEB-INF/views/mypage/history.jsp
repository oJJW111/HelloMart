<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��ǰ ��ٱ��� ���</title>
<script type="text/javascript">
	
	function fnRv(no) {
		location.href = "review/reWrite" + no;
	}
	
	function search(id){
		var startDate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;
		
		if( (startDate != null) && (startDate != "")){
			if((endDate != null) && (endDate != "")){
				if(startDate > endDate){
					alert("���ᳯ¥�� ���۳�¥ ������ �� �� �����ϴ�");
				}
				else{
					location.href = "/mypage/history?id=" + id + "&startDate=" + startDate + "&endDate=" + endDate;
				}
			}
			else{
				alert("���ᳯ¥�� �������ּ���");	
			}			
		}
		else{
			alert("���۳�¥�� �������ּ���");
		}
	}
</script>
</head>
<body>
	<h2>��ٱ��� Ȯ��</h2>
			<table border="1">
				<tr>
				<td colspan="4">
				<input type="date" name="startDate" id="startDate" min="2010-01-01"> ~���� &nbsp;&nbsp;
				<input type="date" name="endDate" id="endDate" min="2010-01-01"> ~���� &nbsp;&nbsp;
				<input type="button" value="�˻�" onclick="search('${id}')">
				</td>
				</tr>
				<tr>
					<th>�̹���</th>
					<th>��ǰ��</th>
					<th>���ų�¥</th>
					<th>��ǰ�ݾ�</th>
					<th>����</th>
					<th>�ѱݾ�</th>
					<th>�����ۼ�</th>
				</tr>
				<c:choose>
				<c:when test="${map.count == 0}">
				<tr>
				<td colspan="7">
        			���ų����� �����ϴ�.
        		</td>
        		</tr>
        		</c:when>
				<c:otherwise>
				<c:forEach var="row" items="${map.list}" varStatus="i">
				<tr>
					<td>
						<img src="${row.img}" width="150" height="80">
					</td>
					<td>
						${row.productname }
					</td>
					<td>
						<fmt:formatDate value="${row.orderDate }" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						<fmt:formatNumber pattern="###,###,###" value="${row.Price }"/> ��
					</td>
					<td>
						${row.orderCount } ��
					</td>
					<td>
						<fmt:formatNumber pattern="###,###,###" value="${row.money }"/> ��
					</td>
					<td>
						<input type="button" value="�����ۼ�" onclick="fnRv({'${row.no}'})">
					</td>
				</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>