$("#trigger").on("click", function(e) {
	e.preventDefault();

	$(".form-group:last").clone().insertBefore($(this))
})