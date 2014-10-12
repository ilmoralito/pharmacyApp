$(document).ready(function() {
	$("a#modalOrders").on("click", function(e) {
		e.preventDefault();
		var data = $(this).data();
		$("a#idModal").attr("href", "/pharmacyApp/purchaseOrder/show/" + data.id);
	    $("#invoiceModal").html(data.invoice);
	    $("#providerModal").html(data.provider);
	    $("#dutyDateModal").html((data.date).substr(0,10));
	    $("#balanceModal").html(data.balance);
	    $("#typeOfPurchaseModal").html(data.type);
	    $("#statusModal").html(data.status);
	});
});