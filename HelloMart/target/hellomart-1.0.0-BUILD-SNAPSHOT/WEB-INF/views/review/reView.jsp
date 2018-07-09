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

<script type="text/javascript">
function delchk(){
    if(confirm("삭제하시겠습니까?")){
        location.href = "/redelete?idx=${view.idx}";
        return true;
    } else {
        return false;
    }
}
</script>

</head>

<body>
	
	<div class="article_wrap">

		<section id="bo_v_info">
			<h2>페이지 정보</h2>
			작성자 <strong>${view.id }</strong> 
			&nbsp;&nbsp;&nbsp; <span class="sound_only">작성일</span>
			<strong>
				<fmt:formatDate value="${view.regdate}" pattern="yyyy.MM.dd HH:mm:ss"/>
			</strong> 
		</section>

		<section id="bo_v_atc">
			<h2 id="bo_v_atc_title">본문</h2>



			<!-- 본문 내용 시작 { -->
			<div id="bo_v_con">${view.content }</div>

			<!-- } 본문 내용 끝 -->

			<!-- 게시물 하단 버튼 시작 { -->
			<div id="bo_v_top">
				<ul class="bo_v_com">
					<li><a href="/remodify?idx=${view.idx }" class="btn_b01">수정</a></li>
					<li><a href="#" class="btn_b01" onclick="delchk();">삭제</a></li>
					<li><a href="/review" class="btn_b01">목록</a></li>
					<li><a href="/rerewrite?idx=${view.idx }" class="btn_b02">답변작성</a></li>
				</ul>
				

			</div>
			<!-- } 게시물 하단 버튼 끝 -->

		</section>

		<!-- } 게시판 읽기 끝 -->

	</div>

</body>
</html>
