$(function(){
	$(".cpNews").change(function(){
		if($(this).prop('checked')) {
			$('.togglebtn label').addClass('checked');
		} else {
			$('.togglebtn label').removeClass('checked');
		}
	});
});