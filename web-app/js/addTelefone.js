$("#trigger").on("click", function(e) {
	e.preventDefault();
	var count = $('.phonesClient').length;
    $(".phonesClient:last").clone().insertBefore($(this)).find('input').val('').focus();
    if (count >= 2){
        $(this).off('click')
    }
})