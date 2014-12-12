$("#triggerTelephone").on("click", function(e) {
	e.preventDefault();
	var _this = $(this),
			len = $('.clientPhones').length;

	$(".clientPhones:last").clone().insertAfter(_this).find("input").val("").focus()

	if (len >= 2){
		_this.off('click')
	}
})