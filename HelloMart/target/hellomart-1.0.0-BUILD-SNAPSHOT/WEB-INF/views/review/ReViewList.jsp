<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/pigeon.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
</head>
<body>

	<div class="article_wrap">
		<h4 align="left">리뷰쓰기</h4>
		<div id="bo_list">
			<div id="bo_list_total">
				전체<span>20</span>건
			</div>
			<div class="tbl_head01 tbl_wrap">
					<table>
						<tbody>
						<c:forEach var="list" items="${list}">
							<c:set var="wid" value="0"/>
								<tr class="bo_notice">
									<td class="td_num">${list.idx }</td>
									<td class="td_name sv_use">${list.id}</td>
									<td class="td_date">
										<fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</div>
			<div class="bo_fx">
				<ul class="btn_bo_user">
					<li><a href="/ReViewWrite" class="btn_b02">글쓰기</a></li>
				</ul>
			</div>

		</div>
		<c:if test="${pageCount>1 }">
			<div align="center" id="page">

				<c:if test="${startPage > pageBlock}">
					<a href="review?pageNum=${startPage-1}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<a href="review?pageNum=${i }">[${i }]</a>
				</c:forEach>
				<c:if test="${endPage < pageCount }">
					<a href="review?pageNum=${startPage+pageBlock}">[다음]</a>
				</c:if>
			</div>
		</c:if>
	
	</div>

</body>
</html>
