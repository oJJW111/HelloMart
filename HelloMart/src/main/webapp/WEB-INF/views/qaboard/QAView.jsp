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

		<!-- 게시물 읽기 시작 { -->
		<header>
			<h1 id="bo_v_title">${view.subject }</h1>
		</header>

		<section id="bo_v_info">
			<h2>페이지 정보</h2>
			작성자 <strong>${view.id }</strong> 
			&nbsp;&nbsp;&nbsp; <span class="sound_only">작성일</span>
			<strong>
				<fmt:formatDate value="${view.date}" pattern="yyyy.MM.dd HH:mm:ss"/>
			</strong> 
			&nbsp;&nbsp;&nbsp; 
			<span class="sound_only">수정일</span>
			<strong>
				<fmt:formatDate value="${view.modate}" pattern="yyyy.MM.dd HH:mm:ss"/>
			</strong>
		</section>

		<section id="bo_v_atc">
			<h2 id="bo_v_atc_title">본문</h2>



			<!-- 본문 내용 시작 { -->
			<div id="bo_v_con">${view.content }</div>

			<!-- } 본문 내용 끝 -->

			<!-- 게시물 하단 버튼 시작 { -->
			<div id="bo_v_top">
				<ul class="bo_v_nb">
					<li><a href="#" class="btn_b01">이전글</a></li>
					<li><a href="#" class="btn_b01">다음글</a></li>
				</ul>
				<ul class="bo_v_com">
					<li><a href="/modify?idx=${view.idx }" class="btn_b01">수정</a></li>
					<li><a href="/delete?idx=${view.idx }" class="btn_b01">삭제</a></li>
					<li><a href="/qaboard" class="btn_b01">목록</a></li>
					<li><a href="/rewrite?idx=${view.idx }" class="btn_b02">답변작성</a></li>
				</ul>

			</div>
			<!-- } 게시물 하단 버튼 끝 -->

		</section>

		<!-- } 게시판 읽기 끝 -->

	</div>

	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
	<!-- 푸터 -->
</body>
</html>
