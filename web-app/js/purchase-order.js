$('form').find("select:first").on('change', function() {
    var $this = $(this);
    var data = JSON.parse(JSON.stringify($this.find(':selected').data('info')));
    var target = $this.parent().parent().next().find('select');

    target.find('option').remove()
    console.log(data);

    for (var i = data.length - 1; i >= 0; i--) {
        /*
        var option = $('<option>', { value: data[i].id}, text: data[i].name });

        target.append(option);
        */
    }
});
