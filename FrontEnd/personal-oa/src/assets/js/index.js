$(function(){
	var flg = {};
	$('.agree').change(function(){
		if ($('.agree').is(':checked')) {
			flg.chk1 = true;
			$('.agreeLabel1').addClass('on');
		} else {
			flg.chk1 = false;
			$('.agreeLabel1').removeClass('on');
		}
		if(flg.chk1) {
			$('.agreeButton').addClass('on');
		} else {
			$('.agreeButton').removeClass('on');
		}
	});
});

