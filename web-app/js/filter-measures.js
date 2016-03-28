$('#search').on('keyup', function(event) {
    var criteria = $(this).val();

    $('.checkbox span').each(function() {
        var _this = $(this);

        if (_this.text().includes(criteria)) {
            _this.parent().addClass('show');
        } else {
            _this.parent().addClass('hide');
        }
    })
});