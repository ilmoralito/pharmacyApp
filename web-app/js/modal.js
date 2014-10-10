$(document).ready(function() {
	$("a#modalOrders").click(function() {

		var varId =  $("a#modalOrders").data("id");
	    $("#idModal").html(varId);

		var varInvoice =  $("a#modalOrders").data("invoice");
	    $("#invoiceModal").html(varInvoice);

	   	var varProvider =  $("a#modalOrders").data("provider");
	    $("#providerModal").html(varProvider);

		var varDutyDate =  $("a#modalOrders").data("dutyDate");
	    $("#dutyDateModal").html(varDutyDate);

	    var varBalance =  $("a#modalOrders").data("balance");
	    $("#balanceModal").html(varBalance);

	    var varTypeOfPurchase =  $("a#modalOrders").data("typeOfPurchase");
	    $("#typeOfPurchaseModal").html(varTypeOfPurchase);

	    var varStatus =  $("a#modalOrders").data("status");
	    $("#statusModal").html(varStatus);

	});
});