$(function() {
    $('#q').select2();

    $('form#item').find("select:first").on('change', function() {
        var optionSelected = $("option:selected", this);
        var info = optionSelected.data("info");
        var target = $(this).parent().next().find('select');

        target.find('option').remove()

        for (var i = info.length - 1; i >= 0; i--) {
            var option = $('<option>', { value: info[i].id, text: info[i].name });

            target.append(option);
        }
    });

    $('.trigger').on('click', function() {
        $(this).next().toggle();
    });

    $('#toggleMeta').on('click', function() {
        $('#meta').toggleClass('show');
    });

    $('#dueDate').datetimepicker({
        format: 'YYYY-MM-DD',
        minDate: moment().add(61, 'days')
    });
});
