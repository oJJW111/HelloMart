<%@page import="org.omg.PortableInterceptor.INACTIVE"%>
<%@page import="java.util.List"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="ch.qos.logback.core.subst.Tokenizer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
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
<link rel="stylesheet" type="text/css" href="/resources/css/product.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>

<script type="text/javascript">
function move(mainCategory, small){
	location.href = "/productList"
			+ "?mainCategory=" + encodeURIComponent(mainCategory)
			+ "&selectedSmallCategory=" + encodeURIComponent(small);
}
</script>

</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

<div class="article_wrap">

<div class=BLOCK70></div>

<form action="/product/list" method="post"> 
<div class="category_detail noselect">
	<div class="category_detail_up">
		<div class="category_major">
			<h5>세부 분류</h5>
			<ul>
				<c:forEach var="small" items="${smallCategory}">
					<li>
						<a href="#" onclick="move('${mainCategory}', '${small}'); return false;">${small}</a>
					</li>
				</c:forEach> 
			</ul>
		</div>
		<div class="category_small">
			<h5>상세검색</h5>
			<%
				if(request.getAttribute("smallCategoryColumn") != null){
					// 각 검색 조건의 이름과, 그에 해당하는 값
					HashMap<String,	String> smallCategoryColumn 
									= (HashMap<String, String>)request.getAttribute("smallCategoryColumn");
					// 각 검색 조건이 몇개의 체크박스를 가지는지 저장할 변수
					HashMap<String,	Integer> smallCategoryColumnCount = new HashMap<String, Integer>();
					// 선택된 하위 카테고리에 해당하는 검색 조건들(페이지에 보여줄 한글명)
					List<String> columnList = (List<String>) request.getAttribute("columnList");
					// 선택된 하위 카테고리에 해당하는 검색 조건들(db검색용 영어명)
					List<String> columnListEng = (List<String>) request.getAttribute("columnListEng");
					
					for(int index=0; index<columnList.size(); index++){
						String column = columnList.get(index);
						String columnEng = columnListEng.get(index);
			%>
						<div> 
							<%=column%> <br><br> 
							<% 
								String allValue = smallCategoryColumn.get(column).trim();
								StringTokenizer tokenizer = new StringTokenizer(allValue, ",");
						
								int i = 0;
								while(tokenizer.hasMoreTokens()){ 
									String value = tokenizer.nextToken();
							%>
									<label class="ck_container">
										<input type="checkbox" name="<%=columnEng%>_<%=i%>" value="<%=value%>">
										<span class="checkmark"></span>
										<%=value%>
									</label>
							<% 
									i++;
								} // while(tokenizer.hasMoreElements()) 종료
								smallCategoryColumnCount.put(columnEng, i);
							%> 
						</div>
			<% 
						if(index+1 < columnList.size()){
			%>
							<hr>
			<% 
						}
					} // for문 종료
				} // smallCategoryColumn의 null여부 if문 종료
			%>
		</div> <!-- <div class="category_small"> -->
	</div> <!-- <div class="category_detail_up"> -->
	<div class="category_detail_down">
		<input type="text" placeholder="제품명 검색" name="search">
		<input type="text" placeholder="0원" name="price_range1">
		<div class="range">~</div>
		<input type="text" placeholder="999,999,999원" name="price_range2">
		<div class="currency">원</div>
		<button type="submit"><i class="fa fa-search"></i></button>
	</div>
</div> <!-- <div class="category_detail noselect"> -->
</form>
<form action="/product/list" method="post"> 
<div class="category_detail noselect">
	<div class="category_detail_up">
		<div class="category_major">
			<h5>세부 분류</h5>
			<ul>
				<c:forEach var="small" items="${smallCategory}">
					<li onclick="move('${mainCategory}', '${small}'); return false;"> 
						${small}
					</li>
				</c:forEach> 
			</ul>
		</div>
		<div class="category_small">
			<h5>상세검색</h5>
			<%
				if(request.getAttribute("smallCategoryColumn") != null){
					// 각 검색 조건의 이름과, 그에 해당하는 값
					HashMap<String,	String> smallCategoryColumn 
									= (HashMap<String, String>)request.getAttribute("smallCategoryColumn");
					// 각 검색 조건이 몇개의 체크박스를 가지는지 저장할 변수
					HashMap<String,	Integer> smallCategoryColumnCount = new HashMap<String, Integer>();
					// 선택된 하위 카테고리에 해당하는 검색 조건들(페이지에 보여줄 한글명)
					List<String> columnList = (List<String>) request.getAttribute("columnList");
					// 선택된 하위 카테고리에 해당하는 검색 조건들(db검색용 영어명)
					List<String> columnListEng = (List<String>) request.getAttribute("columnListEng");
					
					for(int index=0; index<columnList.size(); index++){
						String column = columnList.get(index);
						String columnEng = columnListEng.get(index);
			%>
						<div> 
							<%=column%> <br><br> 
							<% 
								String allValue = smallCategoryColumn.get(column).trim();
								StringTokenizer tokenizer = new StringTokenizer(allValue, ",");
						
								int i = 0;
								while(tokenizer.hasMoreTokens()){ 
									String value = tokenizer.nextToken();
							%>
									<label class="ck_container">
										<input type="checkbox" name="<%=columnEng%>_<%=i%>" value="<%=value%>">
										<span class="checkmark"></span>
										<%=value%>
									</label>
							<% 
									i++;
								} // while(tokenizer.hasMoreElements()) 종료
								smallCategoryColumnCount.put(columnEng, i);
							%> 
						</div>
			<% 
						if(index+1 < columnList.size()){
			%>
							<hr>
			<% 
						}
					} // for문 종료
				} // smallCategoryColumn의 null여부 if문 종료
			%>
		</div> <!-- <div class="category_small"> -->
	</div> <!-- <div class="category_detail_up"> -->
	<div class="category_detail_down">
		<input type="text" placeholder="제품명 검색" name="search">
		<input type="text" placeholder="0원" name="price_range1">
		<div class="range">~</div>
		<input type="text" placeholder="999,999,999원" name="price_range2">
		<div class="currency">원</div>
		<button type="submit"><i class="fa fa-search"></i></button>
	</div>
</div> <!-- <div class="category_detail noselect"> -->
</form>

<!-- small 카테고리가 null이 아니라면 상세검색 기능을 제공한다. -->
<c:if test="${param.small ne null}">
	<jsp:include page="/WEB-INF/views/product/inc/detail_search.jsp"/>
</c:if>
<!-- small 카테고리가 null이 아니라면 상세검색 기능을 제공한다. -->


<!-- 상품리스트 -->
<div class="product_list">
	<div class="product_list_content">
		<div class="product_img"><a href="#"><img src="/resources/images/product/washing_machine01.jpg"></a></div>
		<div class="product_info">
			<a class="title" href="#">
			제품 이름
			</a>
			<div class="additional_info">
				<span class="brand">[LG전자]</span>
				<span class="category">
					<a href="#">가전제품</a> > 
					<a href="#">주방가전</a> > 
					<a href="#">냉장고</a></span>
			</div>
		</div>
		<div class="product_addition">
			<div class="price"><strong>600,000원</strong></div>
			<div class="additional_info">
				<span class="satisfaction">만족도 98%</span>
				<span class="buy">구  &nbsp;&nbsp;매 1285</span>
				<span class="review">상품평 1564</span>
			</div>
			<button class="add_to_cart btn_yellow"></button>
		</div>
	</div>
	<hr class="style14">
	<div class="product_list_content">
		<div class="product_img"><a href="#"><img src="/resources/images/product/washing_machine01.jpg"></a></div>
		<div class="product_info">
			<a class="title" href="#">
			제품 이름
			</a>
			<div class="additional_info">
				<span class="brand">[LG전자]</span>
				<span class="category">
					<a href="#">가전제품</a> > 
					<a href="#">주방가전</a> > 
					<a href="#">냉장고</a></span>
			</div>
		</div>
		<div class="product_addition">
			<div class="price"><strong>600,000원</strong></div>
			<div class="additional_info">
				<span class="satisfaction">만족도 98%</span>
				<span class="buy">구  &nbsp;&nbsp;매 1285</span>
				<span class="review">상품평 1564</span>
			</div>
			<button class="add_to_cart btn_yellow"></button>
		</div>
	</div>
	<hr class="style14">
	<div class="product_list_content">
		<div class="product_img"><a href="#"><img src="/resources/images/product/washing_machine01.jpg"></a></div>
		<div class="product_info">
			<a class="title" href="#">
			제품 이름
			</a>
			<div class="additional_info">
				<span class="brand">[LG전자]</span>
				<span class="category">
					<a href="#">가전제품</a> > 
					<a href="#">주방가전</a> > 
					<a href="#">냉장고</a></span>
			</div>
		</div>
		<div class="product_addition">
			<div class="price"><strong>600,000원</strong></div>
			<div class="additional_info">
				<span class="satisfaction">만족도 98%</span>
				<span class="buy">구  &nbsp;&nbsp;매 1285</span>
				<span class="review">상품평 1564</span>
			</div>
			<button class="add_to_cart btn_yellow"></button>
		</div>
	</div>
	<hr class="style14">
</div>

<div class="BLOCK50"></div>

</div> <!-- article_wrap 끝 -->

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>