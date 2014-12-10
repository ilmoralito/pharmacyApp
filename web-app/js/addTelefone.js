$("#trigger").on("click", function(e) {
	e.preventDefault();
	var _this = $(this),
			len = $('.clientPhones').length;

	$(".form-group:last").clone().insertBefore(_this).find("input").val("").focus()

	if (len >= 2){
		_this.off('click')
	}
})