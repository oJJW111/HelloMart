<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form action="#" method="post">
        <table>
        
        <tbody>
        <tr>
            <th scope="row"><label for="wr_name">이름</label></th>
            <td><input type="text" name="wr_name" value="아직구현안됨" id="wr_name" required class="frm_input required" size="10" maxlength="20"></td>
        </tr>
        <tr>
            <th scope="row"><label for="wr_password">패스워드</label></th>
            <td><input type="password" name="wr_password" id="wr_password" class="frm_input" maxlength="20"></td>
        </tr>
        <tr>
            <th scope="row"><label for="wr_email">이메일</label></th>
            <td><input type="text" name="wr_email" value="아직구현안됨" id="wr_email" class="frm_input email" size="50" maxlength="100"></td>
        </tr>
        <tr>
            <th scope="row"><label for="ca_name">분류</label></th>
            <td>
                <select name="ca_name" id="ca_name" required class="required" >
                    <option value="">선택하세요</option>
                </select>
            </td>
        </tr>
        <tr>
            <th scope="row"><label for="wr_subject">제목</label></th>
            <td class="wr_subject">
            	<input type="text" name="wr_subject" id="wr_subject" size="50" maxlength="100">
            </td>
        </tr>

        <tr>
            <th scope="row"><label for="wr_content">내용</label></th>
            <td class="wr_content">
               <textarea rows="20" cols="50"></textarea>
            </td>
        </tr>
        <tr>
            <th scope="row">파일</th>
            <td>
                <input type="file" name="bf_file" title="파일첨부" class="frm_file frm_input">
                <input type="text" name="bf_content" value="" title="파일 설명을 입력해주세요." class="frm_file frm_input" size="50">
                <input type="checkbox" id="bf_file_del" name="bf_file_del" value="1"> <label for="bf_file_del">파일 삭제</label>
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
