$(window).load(function() {
	histTopHeight = $('.historyTopArea').height();
	$('.historyListArea').css('margin-top',histTopHeight+10+'px');
	
	$('.historyListArea dl dt').click(function(){
		$(this).next('dd').slideToggle();
		$(this).toggleClass('open');
	});
	$('.historyListArea dl dd').click(function(){
		$(this).slideUp();
		$(this).prev().removeClass('open');
	});
});
