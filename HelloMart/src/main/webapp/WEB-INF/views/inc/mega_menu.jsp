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
		location.href="/productList?mainCategory=서비스";
	});

	$("#refrigerator").on('click', function(){
		location.href="/productList?mainCategory=가전제품&smallCategory+=냉장고";
	});
	
	$("#microwave").on('click', function(){
		location.href="/productList?mainCategory=가전제품&smallCategory=오븐_전자레인지";
	});
	
	$("#cleaner").on('click', function(){
		location.href="/productList?mainCategory=가전제품&smallCategory=청소기";
	});
	
	$("#airConditioner").on('click', function(){
		location.href="/productList?mainCategory=가전제품&smallCategory=에어컨";
	});
	
	$("#washer").on('click', function(){
		location.href="/productList?mainCategory=가전제품&smallCategory=세탁기";
	});
	
	$("#airPurifier").on('click', function(){
		location.href="/productList?mainCategory=가전제품&smallCategory=공기청정기_제습기";
	});
	
	$("#notebook").on('click', function(){
		location.href="/productList?mainCategory=IT&smallCategory=노트북";
	});
	
	$("#monitor").on('click', function(){
		location.href="/productList?mainCategory=IT&smallCategory=모니터";
	});
	
	$("#printer").on('click', function(){
		location.href="/productList?mainCategory=IT&smallCategory=프린터";
	});
	
	$("#smartphone").on('click', function(){
		location.href="/productList?mainCategory=모바일&smallCategory=스마트폰";
	});
	
	$("#tablet").on('click', function(){
		location.href="/productList?mainCategory=모바일&smallCategory=태블릿";
	});
	
});
</script>

<div class="header_menu_wrap">
		
	<div class="header_menu">
		<div class="F_left header_menu_item" id="main_menu_1"
			cd_key="ct_001" style="width: 167px;">가전제품</div>
		<div class="F_left header_menu_item" id="main_menu_2"
			cd_key="ct_002">IT</div>
		<div class="F_left header_menu_item" id="main_menu_3"
			cd_key="ct_003">모바일</div>
		<div class="F_left header_menu_item" id="main_menu_4"
			cd_key="ct_004">액세서리</div>
		<div class="F_left header_menu_item" id="main_menu_5"
			cd_key="ct_005">퍼스널케어</div>
		<div class="F_left header_menu_item" id="main_menu_6"
			cd_key="ct_006" style="width: 167px;">서비스</div>
		<div class="clear"></div>
	</div>

	<div class="absolute mega_menu_wrap" style="display: none;">
		<div class="mega_menu" style="height: 319px;">
			<div class="BLOCK30"></div>
			<div class="mega_menu_contents">

				<div class="F_left mega_menu_contents_part2">
					<div class="mega_menu_inner_menu mega_ct_001"
						style="display: none;">
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2" id="refrigerator">냉장고</div>
							<div class="clear"></div>
						</div>
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2" id="microwave">오븐/전자레인지</div>
							<div class="clear"></div>
						</div>
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2" id="cleaner">청소기</div>
							<div class="clear"></div>
						</div>
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2" id="airConditioner">에어컨</div>
							<div class="clear"></div>
						</div>
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2" id="washer">세탁기</div>
							<div class="clear"></div>
						</div>
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2" id="airPurifier">공기청정기/제습기</div>
							<div class="clear"></div>
						</div>
					</div>

					<div class="mega_menu_inner_menu mega_ct_002"
						style="display: none;">
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2" id="notebook">노트북</div>
							<div class="clear"></div>
						</div>
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2">데스크탑</div>
							<div class="clear"></div>
						</div>
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2" id="monitor">모니터</div>
							<div class="clear"></div>
						</div>
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2" id="printer">프린터</div>
							<div class="clear"></div>
						</div>
					</div>


					<div class="mega_menu_inner_menu mega_ct_003"
						style="display: none;">
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2" id="smartphone">스마트폰</div>
							<div class="clear"></div>
						</div>
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2" id="tablet">태블릿</div>
							<div class="clear"></div>
						</div>

					</div>
					<div class="mega_menu_inner_menu mega_ct_004"
						style="display: none;"></div>

					<div class="mega_menu_inner_menu mega_ct_005"
						style="display: none;">
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2">123</div>
							<div class="clear"></div>
						</div>

						<div>
							<div class="F_left megamenu_menu_title2 M_TOP3">
								<div class="megamenu_menu_title2_1">aaaa</div>
							</div>
							<div class="clear"></div>
						</div>


					</div>
					<div class="mega_menu_inner_menu mega_ct_006"
						style="display: none;">
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2">aaaa</div>
							<div class="clear"></div>
						</div>
						<div class="M_TOP9">
							<div class="F_left megamenu_menu_title2">bbbb</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="BLOCK30"></div>
	</div>
	<div class="BLOCK2 BG_DCDDDD"></div>
</div>