$(document).ready(function() {
	$("#from").datepicker({
	 	dateFormat: "yy-mm-dd"
	});

	$("#to").datepicker({
	 	dateFormat: "yy-mm-dd"
	});

	$("#showHideClients").toggle();
  	$("#clientLabel").click(function(){
    	$("#showHideClients").toggle('slow');
    });
});
