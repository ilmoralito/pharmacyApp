$(document).ready(function() {
	$("#from").datepicker({
	 	dateFormat: "yy-mm-dd"
	});

	$("#to").datepicker({
	 	dateFormat: "yy-mm-dd"
	});

	$("#clients").multiselect({
		disableIfEmpty: true,
		nonSelectedText: "Selecciona cliente(s)",
		allSelectedText: "Todos seleccionados",
		numberDisplayed: 3
	});

	if (localStorage.Filter == "true") {
	$("#filterSales").show()
	} else {
		$("#filterSales").hide()
	}

	$("#filterSale").on("click", function() {
	var target = $("#filterSales")
	target.toggle()
	localStorage.Filter = target.is(':visible')
	})
});
