// 회원가입 input태그 클릭 함수
(function($) {
	$(function() {
	    $('.input-label').keypress(function(){
	        $(this).trigger('focusin');
	    });
	    $('.input-label').each(function() {
	        if($(this).val().length) {
	            $(this).trigger('focusin');
	        }
	    });
	    
		$('.input-label').focusin(function(){
			$(this).css({'opacity':1, filter: 'alpha(enabled="false")'});
		});
		$('.input-label').focusout(function(){
			if(!$(this).val()) {
				$(this).animate({'opacity':0}, 500);
			} else {
				$(this).css({'opacity':1});
			}
		});
	});
})(jQuery);
