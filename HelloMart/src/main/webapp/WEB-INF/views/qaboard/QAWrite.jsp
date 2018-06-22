<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<section id="bo_w">
    <h2 id="container_title">글 작성</h2>

    <div class="tbl_frm01 tbl_wrap">
    <form:form action="write" method="post" modelAttribute="qaboard" id="qaboard_form">
    	
        <table>
        
        <tbody>
        <tr>
            <th scope="row">
            	<label for="wr_name">이름</label>
            </th>
            <td>
            	<form:input path="id" name="wr_name" class="frm_input required" size="10" maxlength="20" placeholder="아이디"/>
            	<form:errors path="id" class="errors"/>
            </td>
        </tr>
        <tr>
            <th scope="row"><label for="wr_subject">제목</label></th>
            <td class="wr_subject">
            	<form:input path="subject" name="wr_subject" size="50" maxlength="100"/>
            </td>
        </tr>

        <tr>
            <th scope="row"><label for="wr_content">내용</label></th>
            <td class="wr_content">
            	<form:textarea path="content" rows="20" cols="50"/>
            </td>
        </tr>
        </tbody>
        </table>
    </div>

    <div class="btn_confirm">
        <input type="submit" value="글쓰기" id="btn_submit" accesskey="s" class="btn_submit">
        <input type="reset" value="취소" class="btn_cancel">
    </div>
    </form:form>

</section>
</div>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>
