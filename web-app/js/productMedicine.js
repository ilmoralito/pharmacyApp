$(document).ready(function() {
	$(".unit").hide();
	$(".presentation").on("click", function() {
		$(".unit").next().toggle("show");
	});
});