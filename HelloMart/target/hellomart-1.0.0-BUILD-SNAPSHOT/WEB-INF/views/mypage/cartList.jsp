<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
   uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/pigeon.css" />
<link rel="stylesheet" type="text/css"
   href="/resources/css/cartlist.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>

</head>
<body>
   <!-- 헤더 -->
   <jsp:include page="/WEB-INF/views/inc/header.jsp" />
   <!-- 헤더 -->

   <div class="article_wrap">
      <h2 align="center">장바구니 리스트</h2>
      <form name="cart_form" method="post" action="/cartmodify">
         <div id="cart_list">
            <table style="width: 100%">
               <tr>
                  <th width="10%">이미지</th>
                  <th width="5%">분류</th>
                  <th width="50%">상품명</th>
                  <th width="10%">가격</th>
                  <th width="5%">수량</th>
                  <th width="15%">판매자</th>
                  <th width="5%">취소</th>
               </tr>
               <c:choose>
               <c:when test="${map.count == 0}">
                  <tr>
                     <td colspan="7" align="center">
                        장바구니가 비어있습니다.
                     </td>
                  </tr>
               
               </c:when>
               <c:otherwise>
               <c:forEach var="row" items="${map.list }" varStatus="i">
               <tr class="cart_notice">
                  <td width="10%">
                     <img src="${row.imagepath }" width="100" height="100">
                  </td>
                  <td width="5%">
                     ${row.smallcategory }
                  </td>
                  <td width="50%">
                     ${row.productname }
                  </td>
                  <td width="10%">
                     <fmt:formatNumber pattern="###,###,###" value="${row.price}"/> 원
                  </td>
                  <td width="5%">
                     <select id="count">
                           <c:set var="count" value="${row.count }"/>
                           <c:if test="${row.count > 20 }">
                                <script>
                                  (function(){
                                    alert("주문개수는 20개를 초과할수 없습니다.");

                                     })()
                                </script>
                              <c:set var="count" value="20"/>
                           </c:if>
                           <c:forEach begin="1" end="20" var="i">
                              <c:if test="${count == i}">
                                 <option value="${i}" id="${i}" selected="selected">${i}</option>
                              </c:if>
                              <c:if test="${count != i}">
                                 <option value="${i}" id="${i}">${i}</option>
                              </c:if>
                           </c:forEach>
                     </select>
                  </td>
                  <td width="15%">
                     ${row.registerid } 
                  </td>    
                  <td width="5%">
                     <input type="button" name="delete" class="button_01" value="삭제">
                  </td>
               </tr>  
               </c:forEach>
               <tr class="cart_bottom">
                    <td colspan="7" align="right">
                        장바구니 금액 합계 : <fmt:formatNumber pattern="###,###,###" value="${map.sumMoney}"/> 원<br>
                        배송료 : ${map.fee} 원<br>
                        전체 주문금액  :<fmt:formatNumber pattern="###,###,###" value="${map.allSum}"/> 원
                    </td>
                </tr>
               </c:otherwise>
               </c:choose>
            </table>

         </div>
         <div class="cart_sub" align="right">
         <input type="hidden" name="count" value="${map.count}">
            <button type="submit" id="btnUpdate" class="button_02">수정</button>
         <input type="submit" class="submit_01" value="구매하기">
         </div>
      </form>
   </div>

   <!-- 푸터 -->
   <jsp:include page="/WEB-INF/views/inc/footer.jsp" />
   <!-- 푸터 -->
</body>
</html>