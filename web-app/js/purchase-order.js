$(function() {
    $('#q').select2({
        theme: "bootstrap"
    });

    $('#dueDate').datetimepicker({
        format: 'YYYY-MM-DD',
        minDate: moment().add(61, 'days')
    });

    $('#presentation\\.id, #brand\\.id').on('change', function() {
        var optionSelected = $("option:selected", this);
        var info = optionSelected.data("info");
        var target = ($(this).attr('id') == 'presentation.id') ? $('#measure\\.id') : $('#detail\\.id')

        target.find('option').remove();

        for (var i = 0; i < info.length; i++) {
            var option = $('<option>', { value: info[i].id, text: info[i].name });

            target.append(option);
        }
    });
});
