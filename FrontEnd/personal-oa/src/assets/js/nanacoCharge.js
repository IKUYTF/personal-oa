$(window).load(function() {
	function partChk(){
		if($('input[name=nanacoMethod]:eq(0)').prop('checked')){
			$('.allTabBody').hide();
			$('.nanacoChargeButton').addClass('on');
			$('label.all').addClass('on');
			$('label.part').removeClass('on');
		} else if($('input[name=nanacoMethod]:eq(1)').prop('checked')){
			$('.allTabBody').show();
			var pointVal = $('.usePoint').val();
			if(pointVal!=""){
				$('.nanacoChargeButton').addClass('on');
			} else {
				$('.nanacoChargeButton').removeClass('on');
			}
			$('label.all').removeClass('on');
			$('label.part').addClass('on');
		} else {
			$('.allTabBody').hide();
			$('label.all').removeClass('on');
			$('label.part').removeClass('on');
		}
	}
	$('.usePoint').keyup(function(){
		partChk();
	});
	$('input[name="nanacoMethod"]:radio').change(function(){
		partChk();
	});
	partChk();
});
