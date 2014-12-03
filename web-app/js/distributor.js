$(document).ready(function() {
	$("#addTelephone").on("click", function(){	
	    $(".form-control:last").clone().appendTo("#newTelephone")
	});
});