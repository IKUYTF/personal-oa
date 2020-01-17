$(function(){
	var appFlg = true;
	$('a , button').click(function () {
		cls = $(this).hasClass('noLoad');
		if(!cls){
			if(appFlg){
				$('head').append('<link rel="stylesheet" href="./lib/css/loaders.min.css">');
				$('body').append('<div class="outLoading"><div class="loader"><div class="line-spin-fade-loader"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div></div></div>');
				appFlg = false;
			}
		}
	});
});

