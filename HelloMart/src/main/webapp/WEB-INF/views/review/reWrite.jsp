<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
	<sec:authentication property="principal" var="id" />
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<!-- 헤더 -->

	<div class="article_wrap" style="width: 1026px; margin: auto;'">
		<section id="bo_w">

			<h2 id="container_title">리뷰작성</h2>
			<form action="/reWrite" method="post">
				<div class="tbl_frm01 tbl_wrap">
					<table>
						<tbody>
							<tr>
								<td><input type="text" name="id" class="frm_input required"
									size="10" maxlength="20" value="${id}" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td><textarea name="content" rows="10" cols="82"
										placeholder="5자 이상 입력 하세요."></textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
				<input type="hidden" name="no" value="${review.no}">
				<div class="btn_confirm">
					<input type="submit" value="글쓰기" id="btn_submit" accesskey="s"
						class="btn_submit"> <input type="reset" value="취소"
						class="btn_cancel">
				</div>

			</form>

		</section>
	</div>
	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
	<!-- 푸터 -->
</body>
</html>
