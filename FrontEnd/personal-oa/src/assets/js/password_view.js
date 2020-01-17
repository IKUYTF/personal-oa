$(document).ready(function() {
	flg=true;
	var target1 = document.querySelector(".pw1");
	var target2 = document.querySelector(".pw2");
	var target3 = document.querySelector(".pw3");
	$('.viewPw1').click(function(){
		if (flg) {
			target1.setAttribute("type", "text");
			$(this).children('img').attr('src', 'lib/images/common/ico_eye_open.jpg');
			flg=false;
		} else {
			target1.setAttribute("type", "password");
			$(this).children('img').attr('src', 'lib/images/common/ico_eye_close.jpg');
			flg=true;
		}
	});
	$('.viewPw2').click(function(){
		if (flg) {
			target2.setAttribute("type", "text");
			$(this).children('img').attr('src', 'lib/images/common/ico_eye_open.jpg');
			flg=false;
		} else {
			target2.setAttribute("type", "password");
			$(this).children('img').attr('src', 'lib/images/common/ico_eye_close.jpg');
			flg=true;
		}
	});
	$('.viewPw3').click(function(){
		if (flg) {
			target3.setAttribute("type", "text");
			$(this).children('img').attr('src', 'lib/images/common/ico_eye_open.jpg');
			flg=false;
		} else {
			target3.setAttribute("type", "password");
			$(this).children('img').attr('src', 'lib/images/common/ico_eye_close.jpg');
			flg=true;
		}
	});
});
