$(function(){

	$(".modal").click(function(){

		$("body").append('<div class="modal-bg"></div>');

		modalResize();
 
		$(".modal-bg,.overlayButtunArea").fadeIn(200);
 
		$(".cancel").click(function(){
			$(".overlayButtunArea,.modal-bg").fadeOut(200,function(){
				$('.modal-bg').remove() ;
			});
		});

		$(window).resize(modalResize);
		function modalResize(){

			var w = $(window).width();
			var h = $(window).height();

			var cw = $(".overlayButtunArea").outerWidth();
			var ch = $(".overlayButtunArea").outerHeight();
 
			$(".overlayButtunArea").css({
				"left": ((w - cw)/2) + "px",
				"top": ((h - ch)/2) + "px"
			});
		}
	});

});