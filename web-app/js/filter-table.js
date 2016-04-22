$(function() {
    $("#search").on("keyup", function(event) {
        var criteria = $(this).val().toLowerCase();

        $(".data").each(function() {
            var _this = $(this);

            if (_this.text().toLowerCase().includes(criteria)) {
                _this.parent().show();
            } else {
                _this.parent().hide();
            }
        })
    });
})
