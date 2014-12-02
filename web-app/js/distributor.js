$(document).ready(function() {
	$("#addTelephone").on("click", function(){	
	    $("ul:last").clone().appendTo("#newTelephone").find("#telephones").val("");
	});
});