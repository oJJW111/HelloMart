<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/resources/css/pigeon.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<!-- 부트스트랩 추가로 기존의 css 파일이 뒤틀려져 보이기 때문에 모든 페이지에 부트스트랩을 추가시켜 똑같이 보이도록 하였다. -->
<script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<link href='/resources/bootstrap/css/bootstrap.min.css' rel="stylesheet" type="text/css">
<!-- 부트스트랩 추가로 기존의 css 파일이 뒤틀려져 보이기 때문에 모든 페이지에 부트스트랩을 추가시켜 똑같이 보이도록 하였다. -->

<script src="/resources/js/dropdown.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	$('#bar_menu1').on('click', function(){
		window.open("고객센터");
	});
	$('#bar_menu2').on('click', function(){
		location.href="/login";
	});
	$('#bar_menu3').on('click', function(){
		location.href="/join";
	});

	$("#main_menu_1").on('click', function(){
		location.href="/productList?main=가전제품";
	});
	$("#main_menu_2").on('click', function(){
		location.href="/productList?main=IT";
	});
	$("#main_menu_3").on('click', function(){
		location.href="/productList?main=모바일";
	});
	$("#main_menu_4").on('click', function(){
		location.href="/productList?main=액세서리";
	});
	$("#main_menu_5").on('click', function(){
		location.href="/productList?main=퍼스널케어";
	});
	$("#main_menu_6").on('click', function(){
		location.href="/productList?main=서비스";
	});

	$('#logo').on('click', function(){
		location.href="/";
	});

	$("#refrigerator").on('click', function(){
		location.href="/productList?main=가전제품&small=냉장고";
	});
	
	$("#microwave").on('click', function(){
		location.href="/productList?main=가전제품&small=오븐_전자레인지";
	});
	
	$("#cleaner").on('click', function(){
		location.href="/productList?main=가전제품&small=청소기";
	});
	
	$("#airConditioner").on('click', function(){
		location.href="/productList?main=가전제품&small=에어컨";
	});
	
	$("#washer").on('click', function(){
		location.href="/productList?main=가전제품&small=세탁기";
	});
	
	$("#airPurifier").on('click', function(){
		location.href="/productList?main=가전제품&small=공기청정기_제습기";
	});
	
	$("#notebook").on('click', function(){
		location.href="/productList?main=IT&small=노트북";
	});
	
	$("#monitor").on('click', function(){
		location.href="/productList?main=IT&small=모니터";
	});
	
	$("#printer").on('click', function(){
		location.href="/productList?main=IT&small=프린터";
	});
	
	$("#smartphone").on('click', function(){
		location.href="/productList?main=모바일&small=스마트폰";
	});
	
	$("#tablet").on('click', function(){
		location.href="/productList?main=모바일&small=태블릿";
	});
	
});
</script>
<input type="hidden" id="headeron" />
<div id="main_wrap">
	<div id="pigeon_header">
		<div class="header_bar_wrap">
			<div class="header_bar">
				<div class="F_right haeder_bar_menu" id="bar_menu1">고객센터</div>

				<div class="F_right haeder_bar_menu_line"></div>
				<div class="F_right haeder_bar_menu" id="bar_menu3">회원가입</div>

				<div class="F_right haeder_bar_menu_line"></div>
				<div class="F_right haeder_bar_menu" id="bar_menu2">로그인</div>
				<div class="clear"></div>
			</div>
		</div>
		
		<div class="header_bg_wrap A_center">
			<div class="BLOCK20"></div>
			<div class="logo_wrap">
				<h1 id="logo"><a href="/"><img src="/resources/images/logo.png"></a></h1>
			</div>
		</div>
		
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
	</div>
</div>