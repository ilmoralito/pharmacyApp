$("#purchasePrice").on("blur", function(){
	var purchasePrice = $(this).val() || 0,
			sellingPrice = parseFloat(purchasePrice) + (parseFloat(purchasePrice) * 25 / 100);

	$("#sellingPrice").val(sellingPrice.toFixed(2))
})