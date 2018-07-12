<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상품 장바구니 목록</title>
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
					alert("종료날짜는 시작날짜 이전이 될 수 없습니다");
				}
				else{
					location.href = "/mypage/history/period?id=" + id + "&startDate=" + startDate + "&endDate=" + endDate;
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
<sec:authentication property="principal" var="id"/>
   <!-- 헤더 -->
   <jsp:include page="/WEB-INF/views/inc/header.jsp" />
   <!-- 헤더 -->

   <div class="article_wrap" style="width: 1026px; margin: auto;'">
	<h2 align="center">구매목록 확인</h2>
			<table style="width: 100%">
				<tr>
				<td colspan="4">
				<input type="date" name="startDate" id="startDate" min="2010-01-01"> ~부터 &nbsp;&nbsp;
				<input type="date" name="endDate" id="endDate" min="2010-01-01"> ~까지 &nbsp;&nbsp;
				<input type="button" value="검색" onclick="search('${id}')">
				</td>
				</tr>
				<tr><td><br><br></td></tr>
				<tr>
					<th align="center">이미지</th>
					<th align="center">상품명</th>
					<th align="center">구매날짜</th>
					<th align="center">상품금액</th>
					<th align="center">수량</th>
					<th align="center">총금액</th>
					<th align="center">리뷰작성</th>
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
						<fmt:formatNumber pattern="###,###,###" value="${row.price}"/> 원
					</td>
					<td>
						${row.orderCount} 개
					</td>
					<td>
						<fmt:formatNumber pattern="###,###,###" value="${row.price*row.orderCount}"/> 원
					</td>
					<td>
						<%-- <c:choose>
							<c:when test="${map.check == 0}">
								<input type="button" value="리뷰작성" onclick="fnRv('${row.no}')">
							</c:when>
							<c:otherwise>
								<input type="button" value="수정" onclick="location.href='/remodify?idx=${row.idx}'">
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
	
   <!-- 푸터 -->
   <jsp:include page="/WEB-INF/views/inc/footer.jsp" />
   <!-- 푸터 -->
</body>
</html>