<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="/resources/js/dropdown.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#main_menu_1").on('click', function(){
		location.href="/productList?mainCategory=" + encodeURIComponent("가전제품");
	});
	$("#main_menu_2").on('click', function(){
		location.href="/productList?mainCategory=IT";
	});
	$("#main_menu_3").on('click', function(){
		location.href="/productList?mainCategory=" + encodeURIComponent("모바일");
	});
	$("#main_menu_4").on('click', function(){
		location.href="/productList?mainCategory=" + encodeURIComponent("액세서리");
	});
	$("#main_menu_5").on('click', function(){
		location.href="/productList?mainCategory=퍼스널케어";
	});
	$("#main_menu_6").on('click', function(){
		location.href="/qaboard";
	});
});
</script>

<div class="header_menu_wrap">
	<div class="header_menu">
		<div class="F_left header_menu_item" id="main_menu_1" style="width: 167px;">가전제품</div>
		<div class="F_left header_menu_item" id="main_menu_2">IT</div>
		<div class="F_left header_menu_item" id="main_menu_3">모바일</div>
		<div class="F_left header_menu_item" id="main_menu_4">액세서리</div>
		<div class="F_left header_menu_item" id="main_menu_5">퍼스널케어</div>
		<div class="F_left header_menu_item" id="main_menu_6" style="width: 167px;">게시판</div>
		<div class="clear"></div>
	</div>
</div>