$(function() {
    $('#query').on('keyup', function() {
        var $this = $(this);
        var url = window.queryPath;
        var val = $this.val();

        $.ajax({
            url: url,
            method: "POST",
            data: { criteria: val },
            dataType: "json",
            success: function(data) {
                console.log(data)
            },
            error(xhr, ajaxOptions, error) {
                console.log(xhr, ajaxOptions, error);
            }
        })
    });
});
