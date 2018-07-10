<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상품 장바구니 목록</title>
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
					alert("종료날짜는 시작날짜 이전이 될 수 없습니다");
				}
				else{
					location.href = "/mypage/history?id=" + id + "&startDate=" + startDate + "&endDate=" + endDate;
				}
			}
			else{
				alert("종료날짜를 선택해주세요");	
			}			
		}
		else{
			alert("시작날짜를 선택해주세요");
		}
	}
</script>
</head>
<body>
	<h2>장바구니 확인</h2>
			<table border="1">
				<tr>
				<td colspan="4">
				<input type="date" name="startDate" id="startDate" min="2010-01-01"> ~부터 &nbsp;&nbsp;
				<input type="date" name="endDate" id="endDate" min="2010-01-01"> ~까지 &nbsp;&nbsp;
				<input type="button" value="검색" onclick="search('${id}')">
				</td>
				</tr>
				<tr>
					<th>이미지</th>
					<th>상품명</th>
					<th>구매날짜</th>
					<th>상품금액</th>
					<th>수량</th>
					<th>총금액</th>
					<th>리뷰작성</th>
				</tr>
				<c:choose>
				<c:when test="${map.count == 0}">
				<tr>
				<td colspan="7">
        			구매내역이 없습니다.
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
						<fmt:formatNumber pattern="###,###,###" value="${row.Price }"/> 원
					</td>
					<td>
						${row.orderCount } 개
					</td>
					<td>
						<fmt:formatNumber pattern="###,###,###" value="${row.money }"/> 원
					</td>
					<td>
						<input type="button" value="리뷰작성" onclick="fnRv({'${row.no}'})">
					</td>
				</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>