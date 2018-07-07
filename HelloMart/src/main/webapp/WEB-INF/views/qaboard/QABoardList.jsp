<<<<<<< HEAD
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
<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<!-- 헤더 -->

	<div class="article_wrap">
		<h2 align="center">Q&A게시판</h2>
		<div id="bo_list">
			<div id="bo_list_total">
				전체<span>${paging.totalRecord}</span>건
			</div>
			<div class="tbl_head01 tbl_wrap">
				<table>
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">제목</th>
							<th scope="col">글쓴이</th>
							<th scope="col">날짜</th>
							<th scope="col">조회</th>
						</tr>
					</thead>
					<tbody> 
						<c:if test="${list ne null}">
							<c:forEach var="board" items="${list}">
								<c:set var="wid" value="0" />
								<tr class="bo_notice">
									<td class="td_num">${board.idx}</td>
									<td class="td_subject">
									    <a href="/view?idx=${board.idx}">${board.subject}</a>
									</td>
									<td class="td_name sv_use">${board.id}</td>
									<td class="td_date"><fmt:formatDate value="${board.date}" pattern="yyyy-MM-dd" /></td>
									<td class="td_num">${board.count}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="bo_fx">
				<ul class="btn_bo_adm">
					<li><input type="submit" name="btn_submit" value="선택삭제"
						onclick="document.pressed=this.value"></li>
				</ul>
				<ul class="btn_bo_user">
					<li><a href="/write" class="btn_b02">글쓰기</a></li>
				</ul>
			</div>

		</div>
		<c:if test="${paging.totalRecord gt 0}">
		<div align="center">
			<c:if test="${paging.nowBlock gt 0}">
				<a href="qaboard?page=${paging.beginPage - 1}">[이전]</a>
			</c:if>			
			<c:forEach 	var="i" 
						begin="${paging.beginPage}"
						end="${paging.endPage}">
				<a href="qaboard?page=${i}">[${i}]</a>
			</c:forEach>
			<c:if test="${paging.nowBlock lt paging.totalBlock}">
				<a href="qaboard?page=${paging.endPage + 1}">[다음]</a>
			</c:if>
		</div>
		</c:if>
		<!-- 게시판 검색 시작 { -->
		<fieldset id="bo_sch">
			<legend>게시물 검색</legend>

			<form name="fsearch" method="get">
				<label for="sfl" class="sound_only">검색대상</label> <select name="sfl"
					id="sfl">
					<option value="wr_subject">제목</option>
					<option value="wr_content">내용</option>
					<option value="wr_subject||wr_content">제목+내용</option>
					<option value="mb_id,1">회원아이디</option>
					<option value="mb_id,0">회원아이디(코)</option>
					<option value="wr_name,1">글쓴이</option>
					<option value="wr_name,0">글쓴이(코)</option>
				</select> <label for="stx" class="sound_only">검색어<strong
					class="sound_only"> 필수</strong></label> <input type="text" name="stx"
					required id="stx" class="frm_input required" size="15"
					maxlength="15"> <input type="submit" value="검색"
					class="btn_submit">
			</form>
		</fieldset>
		<!-- 게시판 검색 끝 -->
	</div>


	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
	<!-- 푸터 -->
</body>
</html>
=======
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
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<!-- 헤더 -->

	<div class="article_wrap">
		<h2 align="center">Q&A게시판</h2>
		<div id="bo_list">
			<div id="bo_list_total">
				전체<span>${paging.totalRecord}</span>건
			</div>
			<div class="tbl_head01 tbl_wrap">
				<table>
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">제목</th>
							<th scope="col">글쓴이</th>
							<th scope="col">날짜</th>
							<th scope="col">조회</th>
						</tr>
					</thead>
					<tbody> 
						<c:if test="${list ne null}">
							<c:forEach var="board" items="${list}">
								<c:set var="wid" value="0" />
								<tr class="bo_notice">
									<td class="td_num">${board.idx}</td>
									<td class="td_subject">
									    <a href="/view?idx=${board.idx}">${board.subject}</a>
									</td>
									<td class="td_name sv_use">${board.id}</td>
									<td class="td_date"><fmt:formatDate value="${board.date}" pattern="yyyy-MM-dd" /></td>
									<td class="td_num">${board.count}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="bo_fx">
				<ul class="btn_bo_adm">
					<li><input type="submit" name="btn_submit" value="선택삭제"
						onclick="document.pressed=this.value"></li>
				</ul>
				<ul class="btn_bo_user">
					<li><a href="/write" class="btn_b02">글쓰기</a></li>
				</ul>
			</div>

		</div>
		<c:if test="${paging.totalRecord gt 0}">
		<div align="center">
			<c:if test="${paging.nowBlock gt 0}">
				<a href="qaboard?page=${paging.beginPage - 1}">[이전]</a>
			</c:if>			
			<c:forEach 	var="i" 
						begin="${paging.beginPage}"
						end="${paging.endPage}">
				<a href="qaboard?page=${i}">[${i}]</a>
			</c:forEach>
			<c:if test="${paging.nowBlock lt paging.totalBlock}">
				<a href="qaboard?page=${paging.endPage + 1}">[다음]</a>
			</c:if>
		</div>
		</c:if>
		<!-- 게시판 검색 시작 { -->
		<fieldset id="bo_sch">
			<legend>게시물 검색</legend>

			<form name="fsearch" method="get">
				<label for="sfl" class="sound_only">검색대상</label> <select name="sfl"
					id="sfl">
					<option value="wr_subject">제목</option>
					<option value="wr_content">내용</option>
					<option value="wr_subject||wr_content">제목+내용</option>
					<option value="mb_id,1">회원아이디</option>
					<option value="mb_id,0">회원아이디(코)</option>
					<option value="wr_name,1">글쓴이</option>
					<option value="wr_name,0">글쓴이(코)</option>
				</select> <label for="stx" class="sound_only">검색어<strong
					class="sound_only"> 필수</strong></label> <input type="text" name="stx"
					required id="stx" class="frm_input required" size="15"
					maxlength="15"> <input type="submit" value="검색"
					class="btn_submit">
			</form>
		</fieldset>
		<!-- 게시판 검색 끝 -->
	</div>


	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
	<!-- 푸터 -->
</body>
</html>
>>>>>>> refs/remotes/origin/kms
