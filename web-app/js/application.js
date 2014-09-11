$(document).ready(function() {
	$("#deadline").datepicker({
	 	dateFormat: "yy-mm-dd",
	 	minDate: +1
	});

	var ItemBuilder = (function() {
		var getPresentations = function(productId) {
			$.ajax({
				url:"getPresentationsByProduct/",
				data:{ productId:productId },
				dataType:"JSON",
				success:function(res) {
					var presentation = $("#presentation")

					presentation.find("option").remove()

					for (var i = res.length - 1; i >= 0; i--){
						var opt = $("<option>", {value:res[i].id, text:res[i].name});

						presentation.append(opt)
					}
				}
			})
		}

		return {
			getPresentations:getPresentations
		}
	})();

	ItemBuilder.getPresentations($("#product").val())

	$("#product").on("change", function(){
		ItemBuilder.getPresentations($(this).val())
	})
});
