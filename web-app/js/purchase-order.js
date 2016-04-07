$('form').find("select:first").on('change', function() {
    var $this = $(this);
    var optionSelected = $("option:selected", this);
    var info = optionSelected.data("info")
    var target = $this.parent().parent().next().find('select');

    target.find('option').remove()

    for (var i = info.length - 1; i >= 0; i--) {
        var option = $('<option>', { value: info[i].id, text: info[i].name });

        target.append(option);
    }
});
