$(document).ready(function() {
	var Sale = (function(){
		var getProductData = function(id) {
			$.ajax({
				url:"getProductData",
				data:{ id:id },
				dataType:"JSON",
				success:function(res) {
					var presentation = $("#presentation"),
							measure = $("#measure");

					console.log(res);
				}
			});
		}

		return {
			getProductData:getProductData
		}
	})();	

	$("#product").on("change", function() {
		Sale.getProductData($(this).val());
	})
});
