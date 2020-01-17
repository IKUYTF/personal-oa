var openFlg = 0;
var sumFlg = 0;
var otherSum = 0;
var sumVal;

$(window).load(function() {
	//カード選択
	$('input[name="cardSelect"]:radio').change(function(){
		if($(this).closest(".cardSelectOther").length > 0){
			$('.cardSelectFirst ul li').prependTo('.cardSelectOther ul');
			$(this).parents('li').prependTo('.cardSelectFirst ul');
			cardSelectToggle();
		} else {
			if(openFlg == 1){
				$('.cardSelectOther').slideUp();
				$('.arrowButton img').attr('src','./lib/images/charge/ico_arrow_down.png');
				openFlg = 0;
			}
		}
		buttonCheck();
	});
	//金額選択
	$('input[name="cardSelectSum"]:radio').change(function(){
			$('li.sumButton').removeClass('on');
			$(this).parents('li').addClass('on');
		if($('input[name=cardSelectSum]:eq(3)').prop('checked')){
		} else {
			$('input:[name="cardSelectSumOther"]').attr("checked",false);
			sumFlg = 1;
			otherSum = 0;
			sumVal = $(this).val();
			$('.cardChargeSumMethod dd span').html(sumVal);
			buttonCheck();
		}
	});
	//その他選択
	$('input[name=cardSelectSum]:eq(3)').click(function(){
		$('.otherSumList').slideDown();
	});
	//その他モーダル戻るボタン
	$('.otherSumList .backButton').click(function(){
		if(sumFlg == 1){
			$('li.sumButton').removeClass('on');
			if(sumVal == "3,000"){
				$('input:[name="cardSelectSum"]:eq(0)').attr("checked",true);
				$('input:[name="cardSelectSum"]:eq(0)').parents('li').addClass('on');
			} else if(sumVal == "5,000"){
				$('input:[name="cardSelectSum"]:eq(1)').attr("checked",true);
				$('input:[name="cardSelectSum"]:eq(1)').parents('li').addClass('on');
			} else if(sumVal == "10,000"){
				$('input:[name="cardSelectSum"]:eq(2)').attr("checked",true);
				$('input:[name="cardSelectSum"]:eq(2)').parents('li').addClass('on');
			}
		}
		$('.otherSumList').slideUp();
	});
	//モーダル金額選択
	$('input[name="cardSelectSumOther"]:radio').change(function(){
		sumFlg = 0;
		otherSum = 1;
		sumVal = $(this).val();
		$('.cardChargeSumMethod dd span').html(sumVal);
		$('.otherSumList').slideUp();
		buttonCheck();
	});
	//カード選択
	$('input[name="cardSelect"]:radio').change(function(){
	});
	//カード選択矢印選択
	$('.arrowButton').click(function(){
		cardSelectToggle();
	});
	buttonCheck();
});
function cardSelectToggle(){
	if(openFlg == 0){
		$('.cardSelectOther').slideDown();
		$('.arrowButton img').attr('src','./lib/images/charge/ico_arrow_up.png');
		openFlg = 1;
	} else if(openFlg == 1){
		$('.cardSelectOther').slideUp();
		$('.arrowButton img').attr('src','./lib/images/charge/ico_arrow_down.png');
		openFlg = 0;
	}
}
function buttonCheck(){
	if($('input[name=cardSelect]').prop('checked') && $('input[name=cardSelectSum]:eq(0)').prop('checked')){
		$('.cardChargeButton').addClass('on');
	} else if($('input[name=cardSelect]').prop('checked') && $('input[name=cardSelectSum]:eq(1)').prop('checked')){
		$('.cardChargeButton').addClass('on');
	} else if($('input[name=cardSelect]').prop('checked') && $('input[name=cardSelectSum]:eq(2)').prop('checked')){
		$('.cardChargeButton').addClass('on');
	} else if($('input[name=cardSelect]').prop('checked') && $('input[name=cardSelectSum]:eq(3)').prop('checked')){
		if(otherSum == 1){
			$('.cardChargeButton').addClass('on');
		} else {
			$('.cardChargeButton').removeClass('on');
		}
	} else {
		$('.cardChargeButton').removeClass('on');
	}
}
