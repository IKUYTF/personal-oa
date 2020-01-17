$(window).load(function() {
	var attLgt = $('.attentionArea').length;
	var brHeight = window.innerHeight;
	var brWidth = window.innerWidth;
	var agreeButtonHeight = $('.agreeButtonArea').height() + 60;
	var ifHeight;
	if(attLgt==0){
		//エラーなし
		ifHeight = brHeight - agreeButtonHeight;
	} else {
		//エラーあり
		var attHeight = $('.attentionArea').height() +52;
		ifHeight = brHeight - agreeButtonHeight - attHeight;
	}
	$('iframe.autoHeight').height(ifHeight+'px');
	$('iframe.autoHeight').width(brWidth+'px');

	if ( (navigator.userAgent.indexOf('iPhone') > 0 && navigator.userAgent.indexOf('iPad') == -1) || navigator.userAgent.indexOf('iPod') > 0) {
		$('.agreeTopArea').height(ifHeight+'px');
		$('.agreeTopArea').width(brWidth+'px');
	}

});
