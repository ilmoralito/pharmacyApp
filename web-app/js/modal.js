$(document).ready(function() {
	$("a #modalOrders").click(function() {

		var varId =  $(this).data("id");
	    $("#idModal").html(varId);

		var varInvoice =  $(this).data("invoice");
	    $("#invoiceModal").html(varInvoice);

	   	var varProvider =  $(this).data("provider");
	    $("#providerModal").html(varProvider);

		var varDutyDate =  $(this).data("dutyDate");
	    $("#dutyDateModal").html(varDutyDate);

	    var varBalance =  $(this).data("balance");
	    $("#balanceModal").html(varBalance);

	    var varTypeOfPurchase =  $(this).data("typeOfPurchase");
	    $("#typeOfPurchaseModal").html(varTypeOfPurchase);

	    var varStatus =  $(this).data("status");
	    $("#statusModal").html(varStatus);

	});
});