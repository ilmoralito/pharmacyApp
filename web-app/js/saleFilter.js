$(document).ready(function() {
	$("#from").datepicker({
	 	dateFormat: "yy-mm-dd"
	});

	$("#to").datepicker({
	 	dateFormat: "yy-mm-dd"
	});

    $(".chosen-select").chosen();
    $(".chosen-select-deselect").chosen({ allow_single_deselect: true });
});
