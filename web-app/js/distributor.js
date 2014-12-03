$(document).ready(function() {
	$("#addTelephone").on("click", function(){	
	    $(".form-group:last").clone().appendTo("#newTelephone").find("#telephones").val("");
	});
});