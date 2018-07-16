<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
   
   .msg{font-size: 10pt;  color: red;}
</style>
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>

<script type="text/javascript">
function cmtdelchk(cmtidx,idx){
    if(confirm("코멘트를 삭제하시겠습니까?")){
        location.href = "/qaboard/cmtdelete?cmtidx="+cmtidx+"&idx="+idx;
        return true;
    } else {
        return false;
    }
}

</script>
</head>


<body>

      <sec:authentication property="principal" var="userId"/>
         <hr>
         <h4>코멘트</h4>
         <table style="width: 840px">
         <c:if test="${cmtlist != null }">
            <c:forEach var="cmtboard" items="${cmtlist}"> 
            <tr height="70px">	
               <td>${cmtboard.id }</td>
               <td style="width: 500px">${cmtboard.content }</td>
               <td align="right">
                  <fmt:formatDate value="${cmtboard.date}" pattern="yyyy.MM.dd HH:mm:ss"/>
                  <a href="#" class="btn_b01" onclick="cmtdelchk('${cmtboard.cmtidx}','${idx }');">삭제</a>
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
               <form:input path="id" value="${userId}" readonly="true" size="13"/>
            </td>
            <td style="width: 500px">
               <form:textarea path="content" rows="2" cols="80" placeholder="5자이상 입력주세요"/>
               <form:errors path="content" cssClass="msg"/>
            </td>
            <td align="right">
               <input type="submit" value="글쓰기">
            </td>
         </tr>
         </form:form>      
         </table>
      <div align="center">
         <c:if test="${paging.totalRecord gt 0}">
            <c:if test="${paging.nowBlock gt 0}">
               <a href="/qaboard/CMTBoardList?page=${paging.beginPage - 1}">[이전]</a>
            </c:if>
            <c:forEach    var="i"
                     begin="${paging.beginPage}"
                     end="${paging.endPage}">
            <a href="/qaboard/CMTBoardList?page=${i}">[${i}]</a>
            </c:forEach>
            <c:if test="${paging.nowBlock lt paging.totalBlock}">
               <a href="/qaboard/CMTBoardList?page=${paging.endPage + 1}">[다음]</a>
            </c:if>
         </c:if>
      </div>
</body>
</html>