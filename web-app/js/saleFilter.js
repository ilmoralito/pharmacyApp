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
});
