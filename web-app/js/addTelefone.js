$("#triggerAddTelephone").on("click", function(e) {
    e.preventDefault();

    var context = $(this),
        len = $('.telephones').length;

    $(".telephones:last")
        .parent()
        .clone()
        .insertAfter(context)
        .find("input")
        .val("")
        .focus()

    if (len >= 2){
        context.off('click')
    }
})