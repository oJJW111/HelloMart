<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/resources/css/pigeon.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<!-- 부트스트랩 추가로 기존의 css 파일이 뒤틀려져 보이기 때문에 모든 페이지에 부트스트랩을 추가시켜 똑같이 보이도록 하였다. -->
<script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<link href='/resources/bootstrap/css/bootstrap.min.css' rel="stylesheet" type="text/css">
<!-- 부트스트랩 추가로 기존의 css 파일이 뒤틀려져 보이기 때문에 모든 페이지에 부트스트랩을 추가시켜 똑같이 보이도록 하였다. -->
<script type="text/javascript">
$(document).ready(function(){
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

		  ga('create', 'UA-78696252-1', 'auto');
	$('.header_menu_item').each(function(){

		$(this).on('mouseover', function(){

			$('.header_menu_item').removeClass('on');
							
			$('.megamenu_menu_title2').removeClass('on');
			var cd_key = $(this).attr('cd_key');
			$('.mega_menu_inner_menu').hide();
			$('.mega_menu_wrap').show();
			$('.megamenu_img_class').css({display: 'none'});
			$('.mega_'+cd_key).css({display: 'block'});

			$('.megamenu_menu_title2').attr({menu: cd_key});

			var menu = $('#category_key').val();
			$('#headeron').val(cd_key);
			
			if(cd_key == menu){
				$('.megamenu_menu_title2.list_all').addClass('on');	
			}
			var txt_title = $('#product_name').val();
			$('.megamenu_menu_title2').each(function(){
				var text = $(this).text();
				if(txt_title == text){
					$(this).addClass('on');
				}
			});
			$('.megamenu_menu_title2_1').each(function(){
				var text = $(this).text();
				if(txt_title == text){
					$(this).addClass('on');
				}
			});
		});
	});
	$('.megamenu_menu_title2').each(function(){
		$(this).on('click', function(){
			var cd = $(this).attr('cd_code');
			var cd_key = $(this).attr('menu');
			if(cd == '0'){
				location.href="sub.php?menu="+cd_key;
			}
			else{
				location.href="sub_detail.php?prd_idx="+cd+"&category_key="+cd_key;
			}
		});
	});

	$('.header_menu_wrap').on('mouseleave', function(event){
		console.log(event.pageY);
		if(event.pageY<199){
			$('.header_menu_item').each(function(){
				var cd_key = $(this).attr('cd_key');
				if($('#category_key').val() == cd_key){
					$('.header_menu_item').removeClass('on');
					$(this).addClass('on');
				}
			});
			if(!$('#category_key').val()){
				$('.header_menu_item').removeClass('on');
				$('.mega_menu_wrap').hide();
			}
		}
	});
	
	$('.mega_menu_wrap').on('mouseleave', function(){
		var _this = this;
		$('.header_menu_wrap').on('mouseleave', function(){
			$(_this).hide();
			$('.header_menu_item').each(function(){
				var cd_key = $(this).attr('cd_key');
				if($('#category_key').val() == cd_key){
					$('.header_menu_item').removeClass('on');
					$(this).addClass('on');
				}
			});
			if(!$('#category_key').val()){
				$('.header_menu_item').removeClass('on');
			}
		});
		
	});
	$('.mega_menu_wrap').on('mouseenter', function(){
		$('.header_menu_item').each(function(){
			var cd_key = $(this).attr('cd_key');
			if($('#headeron').val() == cd_key){
				$('.header_menu_item').removeClass('on');
				$(this).addClass('on');
			}
		});
	});
	$('#bar_menu1').on('click', function(){
		window.open("http://pigeon.co.kr/cms/content.php?mCode=1074");
	});
	$('#bar_menu2').on('click', function(){
		location.href="/login";
	});
	$('#bar_menu3').on('click', function(){
		location.href="/join";
	});
	$("#main_menu_1").on('click', function(){
		location.href="/product/list?mainCategory=" + encodeURIComponent("가전제품");
	});
	$("#main_menu_2").on('click', function(){
		location.href="/product/list?mainCategory=IT";
	});
	$("#main_menu_3").on('click', function(){
		location.href="/product/list?mainCategory=" + encodeURIComponent("모바일");
	});
	$("#main_menu_4").on('click', function(){
		location.href="/product/list?mainCategory=" + encodeURIComponent("액세서리");
	});
	$("#main_menu_5").on('click', function(){
		location.href="sub.php?menu=ct_005";
	});
	$("#main_menu_6").on('click', function(){
		location.href="sub.php?menu=ct_006";
	});

	$('#logo').on('click', function(){
		location.href="/";
	});

	$('.haeder_bar_menu_lanen').on('click', function(){
		window.open('http://pigeon.co.kr/cms/content.php?mCode=1112');
	});
	
	$('.haeder_bar_menu_lankr').on('click', function(){
		window.open('http://pigeon.co.kr/cms/content.php?mCode=1074');
	});
	
});
</script>
<input type="hidden" id="headeron" />
<div id="main_wrap">
	<div id="pigeon_header">
		<div class="header_bar_wrap">
			<div class="header_bar">
				<div class="F_right haeder_bar_menu_lanen"></div>
				<div class="F_right haeder_bar_menu_lankr"></div>
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
									<div class="F_left megamenu_menu_title2">주방가전</div>
									<div class="clear"></div>
								</div>
								<div>
									<div class="F_left megamenu_menu_title2 M_TOP3">
										<div class="megamenu_menu_title2_1">냉장고</div>
									</div>
									<div class="clear"></div>
								</div>
								<div>
									<div class="F_left megamenu_menu_title2 M_TOP3">
										<div class="megamenu_menu_title2_1">오븐/전자레인지</div>
									</div>
									<div class="clear"></div>
								</div>

								<div class="M_TOP9">
									<div class="F_left megamenu_menu_title2">생활가전</div>
									<div class="clear"></div>
								</div>
								<div>
									<div class="F_left megamenu_menu_title2 M_TOP3">
										<div class="megamenu_menu_title2_1">청소기</div>
									</div>
									<div class="clear"></div>
								</div>
								<div>
									<div class="F_left megamenu_menu_title2 M_TOP3">
										<div class="megamenu_menu_title2_1">에어컨</div>
									</div>
									<div class="clear"></div>
								</div>
								<div>
									<div class="F_left megamenu_menu_title2 M_TOP3">
										<div class="megamenu_menu_title2_1">세탁기</div>
									</div>
									<div class="clear"></div>
								</div>
								<div>
									<div class="F_left megamenu_menu_title2 M_TOP3">
										<div class="megamenu_menu_title2_1">공기청정기/제습기</div>
									</div>
									<div class="clear"></div>
								</div>
							</div>

							<div class="mega_menu_inner_menu mega_ct_002"
								style="display: none;">
								<div class="M_TOP9">
									<div class="F_left megamenu_menu_title2">PC</div>
									<div class="clear"></div>
								</div>
								<div>
									<div class="F_left megamenu_menu_title2 M_TOP3">
										<div class="megamenu_menu_title2_1">노트북</div>
									</div>
									<div class="clear"></div>
								</div>
								<div>
									<div class="F_left megamenu_menu_title2 M_TOP3">
										<div class="megamenu_menu_title2_1">데스크탑</div>
									</div>
									<div class="clear"></div>
								</div>
								<div>
									<div class="F_left megamenu_menu_title2 M_TOP3">
										<div class="megamenu_menu_title2_1">모니터</div>
									</div>
									<div class="clear"></div>
								</div>
								<div>
									<div class="F_left megamenu_menu_title2 M_TOP3">
										<div class="megamenu_menu_title2_1">프린터</div>
									</div>
									<div class="clear"></div>
								</div>

							</div>

							<div class="mega_menu_inner_menu mega_ct_003"
								style="display: none;">
								<div class="M_TOP9">
									<div class="F_left megamenu_menu_title2">스마트폰</div>
									<div class="clear"></div>
								</div>
								<div class="M_TOP9">
									<div class="F_left megamenu_menu_title2">태블릿</div>
									<div class="clear"></div>
								</div>
								<div class="M_TOP9">
									<div class="F_left megamenu_menu_title2">피쳐폰</div>
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
					<div class="F_right mega_menu_contents_part3">
						<a href="http://www.pigeon.co.kr/mall/sub_detail.php?prd_idx=18">
							<img link="" order_no="1"
							class="pointer megamenu_img_class mega_ct_001" src=""
							style="width: 510px; height: 221px; display: none;" />
						</a> <a href="http://www.pigeon.co.kr/mall/sub_detail.php?prd_idx=43">
							<img link="" order_no="2"
							class="pointer megamenu_img_class mega_ct_002" src=""
							style="width: 510px; height: 221px; display: none;" />
						</a> <a href="http://www.pigeon.co.kr/mall/sub_detail.php?prd_idx=25">
							<img link="" order_no="3"
							class="pointer megamenu_img_class mega_ct_003" src="a"
							style="width: 510px; height: 221px; display: none;" />
						</a> <a href="http://www.pigeon.co.kr/mall/sub_detail.php?prd_idx=33">
							<img link="" order_no="4"
							class="pointer megamenu_img_class mega_ct_004" src=""
							style="width: 510px; height: 221px; display: none;" />
						</a> <a href="http://www.pigeon.co.kr/mall/sub_detail.php?prd_idx=34">
							<img link="" order_no="5"
							class="pointer megamenu_img_class mega_ct_005" src=""
							style="width: 510px; height: 221px; display: none;" />
						</a> <a href="http://www.pigeon.co.kr/mall/sub_detail.php?prd_idx=37">
							<img link="" order_no="6"
							class="pointer megamenu_img_class mega_ct_006" src=""
							style="width: 510px; height: 221px; display: none;" />
						</a>
					</div>
					<div class="clear"></div>
				</div>
				<div class="BLOCK30"></div>
			</div>
			<div class="BLOCK2 BG_DCDDDD"></div>
		</div>
	</div>
</div>