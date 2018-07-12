<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��ǰ ��ٱ��� ���</title>
<script type="text/javascript">
	
	function fnRv(no) {
		location.href = "/reWrite?no=" + no;
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
					location.href = "/mypage/history/period?id=" + id + "&startDate=" + startDate + "&endDate=" + endDate;
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
<sec:authentication property="principal" var="id"/>
   <!-- ��� -->
   <jsp:include page="/WEB-INF/views/inc/header.jsp" />
   <!-- ��� -->

   <div class="article_wrap" style="width: 1026px; margin: auto;'">
	<h2 align="center">���Ÿ�� Ȯ��</h2>
			<table style="width: 100%">
				<tr>
				<td colspan="4">
				<input type="date" name="startDate" id="startDate" min="2010-01-01"> ~���� &nbsp;&nbsp;
				<input type="date" name="endDate" id="endDate" min="2010-01-01"> ~���� &nbsp;&nbsp;
				<input type="button" value="�˻�" onclick="search('${id}')">
				</td>
				</tr>
				<tr><td><br><br></td></tr>
				<tr>
					<th align="center">�̹���</th>
					<th align="center">��ǰ��</th>
					<th align="center">���ų�¥</th>
					<th align="center">��ǰ�ݾ�</th>
					<th align="center">����</th>
					<th align="center">�ѱݾ�</th>
					<th align="center">�����ۼ�</th>
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
						<a href="/productView?no=${row.no}&smallCategory=${row.smallcategory}">
							<img src="${row.imagepath}" width="150" height="150">
						</a>
					</td>
					<td>
						<a href="/productView?no=${row.no}&smallCategory=${row.smallcategory}"
						style="color: black;"> 
						${row.productname}</a>
					</td>
					<td>
						<fmt:formatDate value="${row.orderDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						<fmt:formatNumber pattern="###,###,###" value="${row.price}"/> ��
					</td>
					<td>
						${row.orderCount} ��
					</td>
					<td>
						<fmt:formatNumber pattern="###,###,###" value="${row.price*row.orderCount}"/> ��
					</td>
					<td>
						<%-- <c:choose>
							<c:when test="${map.check == 0}">
								<input type="button" value="�����ۼ�" onclick="fnRv('${row.no}')">
							</c:when>
							<c:otherwise>
								<input type="button" value="����" onclick="location.href='/remodify?idx=${row.idx}'">
							</c:otherwise>
						</c:choose> --%>
						<jsp:include page="/mypage/historyButton?no=${row.prodNo}"></jsp:include>
						
					</td>
				</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	</div>
	
   <!-- Ǫ�� -->
   <jsp:include page="/WEB-INF/views/inc/footer.jsp" />
   <!-- Ǫ�� -->
</body>
</html>