<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" type="text/css" href="/resources/css/pigeon.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/product.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var $scrollHeight = $('.category_one')[0].scrollHeight;
	console.log("scrollHeight: " + $scrollHeight);

	$('.category_two')[0].css({'height' : $scrollHeight});
	$('.category_three')[0].css({'height' : $scrollHeight});
	$('.category_four')[0].css({'height' : $scrollHeight});
});
</script>
</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

<div class="article_wrap">

<div class=BLOCK70></div>

<div class="category_detail">
	<div class="category_detail_up">
		<div class="category_major">
			<h5>카테고리</h5>
			<a class="major" href="#">
				가전제품
			</a>
			<a class="middle" href="#">
				주방가전
			</a>
			<a class="middle" href="#">
				오븐/전자레인지
			</a>
			<a class="major" href="#">
				가전제품
			</a>
			<a class="middle" href="#">
				주방가전
			</a>
			<a class="middle" href="#">
				오븐/전자레인지
			</a>
			<a class="major" href="#">
				가전제품
			</a>
			<a class="middle" href="#">
				주방가전
			</a>
			<a class="middle" href="#">
				오븐/전자레인지
			</a>
			<a class="major" href="#">
				가전제품
			</a>
			<a class="middle" href="#">
				주방가전
			</a>
			<a class="middle" href="#">
				오븐/전자레인지
			</a>
		</div>
		<div class="category_small">
			<div class="category_one">
				<h5>세부분류</h5>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 세탁기
				</label>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 드럼 세탁기
				</label>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 드럼 세탁기
				</label>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 드럼 세탁기
				</label>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 드럼 세탁기
				</label>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 드럼 세탁기
				</label>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 드럼 세탁기
				</label>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 드럼 세탁기
				</label>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 드럼 세탁기
				</label>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 드럼 세탁기
				</label>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 드럼 세탁기
				</label>
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 드럼 세탁기
				</label>
			</div>
			<div class="category_two">
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 세탁기
				</label>
			</div>
			<div class="category_three">
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 에어컨
				</label>
			</div>
			<div class="category_four">
				<label class="ck_container">
					<input type="checkbox" name="_category" value="washer">
					<span class="checkmark"></span>
					 선풍기
				</label>
			</div>
		</div>
	</div>
</div>

<div class=BLOCK36></div>

<!-- 상품리스트 -->
<div class="product_list">
	<div class="product_list_content">
		<div class="product_img"><a href="#"><img src="/resources/images/washing_machine01.jpg"></a></div>
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
		<div class="product_img"><a href="#"><img src="/resources/images/washing_machine01.jpg"></a></div>
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
		<div class="product_img"><a href="#"><img src="/resources/images/washing_machine01.jpg"></a></div>
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
