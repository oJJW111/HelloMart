<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<style type="text/css">
   
   .msg{font-size: 10pt;  color: red;}

</style>
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<script type="text/javascript">
function delchk(){
    if(confirm("글을 삭제하시겠습니까?")){
        location.href = "/delete?idx=${view.idx}";
        return true;
    } else {
        return false;
    }
}

function cmtdelchk(cmtidx,idx){
    if(confirm("코멘트를 삭제하시겠습니까?")){
        location.href = "/cmtdelete?cmtidx="+cmtidx+"&idx="+idx;
        return true;
    } else {
        return false;
    }
}

</script>
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
            <ul class="bo_v_com">
               <li><a href="/modify?idx=${view.idx }" class="btn_b01">수정</a></li>
               <li><a href="#" class="btn_b01" onclick="delchk();">삭제</a></li>
               <li><a href="/qaboard" class="btn_b01">목록</a></li>
            </ul>
            

         </div>
         <!-- } 게시물 하단 버튼 끝 -->
         
         <!-- 코멘트 영역 -->
         <sec:authentication property="principal" var="id"/>
         <hr>
         <h4>코멘트</h4>
         <table style="width: 1026px">
         <c:if test="${pageCount!=0 }">
            <c:forEach var="cmtlist" items="${cmtlist}"> 
            <tr height="70px">
               <td>${cmtlist.id }</td>
               <td style="width: 500px">${cmtlist.content }</td>
               <td align="right">
                  <fmt:formatDate value="${cmtlist.date}" pattern="yyyy.MM.dd HH:mm:ss"/>
                  <a href="#" class="btn_b01" onclick="cmtdelchk('${cmtlist.cmtidx}','${view.idx }');">삭제</a>
               </td>
            </tr>
            </c:forEach>
         </c:if>
         <tr>
         <td colspan="3"><hr></td>
         </tr>
         <form:form action="cmtinsert" modelAttribute="cmtboard" method="post" id="cmt_form">
         <tr height="70px">
            <form:hidden path="cmtpar" value="${view.idx }" />
            <td>
               <form:input path="id" value="${id}" readonly="true" size="9"/>
            </td>
            <td style="width: 500px">
               <form:textarea path="content" rows="2" cols="100" placeholder="5자이상 입력주세요"/>
               <form:errors path="content" cssClass="msg"/>
            </td>
            <td align="right">
               <input type="submit" value="글쓰기">
            </td>
         </tr>
         </form:form>      
         </table>
         
         <c:if test="${pageCount>1 }">
         <div align="center" id="page">
            <c:if test="${startPage > pageBlock}">
               <a href="view?idx=${view.idx }&cmtnum=${startPage-1 }">[이전]</a>
            </c:if>         
            <c:forEach var="i" begin="${startPage }" end="${endPage }">
               <a href="view?idx=${view.idx }&cmtnum=${i }">[${i }]</a>
            </c:forEach>
            <c:if test="${endPage < pageCount }">
               <a href="view?idx=${view.idx }&cmtnum=${startPage+pageBlock }">[다음]</a>
            </c:if>
         </div>
      </c:if>
      </section>

      <!-- 코멘트영역 끝 -->
      
      <!-- } 게시판 읽기 끝 -->
   <table>
      <tr>
         <td>
      </tr>
      
      
   
   </table>


   </div>


   <!-- 푸터 -->
   <jsp:include page="/WEB-INF/views/inc/footer.jsp" />
   <!-- 푸터 -->
</body>
</html>