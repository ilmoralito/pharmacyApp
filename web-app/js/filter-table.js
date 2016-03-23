var FilterTable = (function() {
    var config = {
        table: $("#employees tbody tr")
    };

    return {
        filter: function(queryString) {
            // TODO
            var target = config.table;

            for (var i = target.length - 1; i >= 0; i--) {
                console.log(target[i]);
            };
        }
    }
})();

$("#search").on("keyup", function(event) {
    FilterTable.filter(event.target.value);
});
