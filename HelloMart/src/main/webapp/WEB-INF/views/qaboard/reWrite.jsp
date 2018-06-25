<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/pigeon.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

<div class="article_wrap">
	<!-- 게시물 읽기 시작 { -->
		<header>
			<h1 id="bo_v_title">${qaboard.subject }</h1>
		</header>

		<section id="bo_v_info">
			<h2>페이지 정보</h2>
			작성자 <strong>${qaboard.id }</strong> &nbsp;&nbsp;&nbsp; <span class="sound_only">작성일</span>
			<strong>
				<fmt:formatDate value="${qaboard.date}" pattern="yyyy.MM.dd HH:mm:ss"/>
			</strong>
		</section>

		<section id="bo_v_atc">
			<h2 id="bo_v_atc_title">본문</h2>



			<!-- 본문 내용 시작 { -->
			<div id="bo_v_con">${qaboard.content }</div>

			<!-- } 본문 내용 끝 -->
		</section>

<section id="bo_w">

    <h2 id="container_title">답글 작성</h2>
    <form action="rewrite" method="post">
    <input type="hidden" name="reRef" value="${qaboard.reRef }">
    <input type="hidden" name="reSeq" value="${qaboard.reSeq }">
    <div class="tbl_frm01 tbl_wrap">
        <table>
        <tbody>
        <tr>
            <td>
            	<input type="text" name="id" class="frm_input required" size="10" maxlength="20" placeholder="아이디">
            </td>
        </tr>
        <tr>
            <td>
            	<input type="text" name="subject" size="80" maxlength="100" placeholder="제목">
            </td>
        </tr>

        <tr>
            <td>
            	<textarea name="content" rows="10" cols="82" placeholder="5자 이상 입력 하세요."></textarea>
            </td>
        </tr>
        </tbody>
        </table>
    </div>

    <div class="btn_confirm">
        <input type="submit" value="글쓰기" id="btn_submit" accesskey="s" class="btn_submit">
        <input type="reset" value="취소" class="btn_cancel">
    </div>
	</form>
	
</section>
</div>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>
