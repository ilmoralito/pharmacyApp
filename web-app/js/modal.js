$(document).ready(function() {
	$("a#modalOrders").on("click", function(e) {
		e.preventDefault();

		var data = $(this).data();

		$("a#idModal").attr("href", "/pharmacyApp/purchaseOrder/edit/" + data.id);
    $("#invoiceModal").html("Factura #" + data.invoice);
    $("#providerModal").html(data.provider);
    $("#payDateModal").html((data.paydata).substr(0,10));
    $("#dutyDateModal").html((data.date).substr(0,10));
    $("#balanceModal").html(data.balance);
    $("#typeOfPurchaseModal").html(data.type);

    $("#statusModal").html((data.status) ? "Cancelado" : "Pendiente")
	});
});