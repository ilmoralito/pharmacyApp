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
					var presentation = $("#presentation"),
							measure = $("#measure")

					presentation.find("option").remove()
					measure.find("option").remove()

					//add presentations
					for (var i = res.length - 1; i >= 0; i--){
						var opt = $("<option>", { value:res[i].id, text:res[i].name });

						//add measures only for first presentation
						if (i == res.length - 1) {
							for (var j = res[i].measures.length - 1; j >= 0; j--) {
								var optionMeasure = $("<option>", { value:res[i].measures[j], text:res[i].measures[j] })

								measure.append(optionMeasure)
							};
						}

						presentation.append(opt)
					}
				}
			})
		}

		var getMeasuresByPresentation = function(presentationId) {
			$.ajax({
				url:"getMeasuresByPresentation/",
				data:{ presentationId:presentationId },
				dataType:"JSON",
				success:function(res) {
					var measure = $("#measure");
					measure.find("option").remove()

					for (var i = res.length - 1; i >= 0; i--) {
						var option = $("<option>", { value:res[i], text:res[i] })

						measure.append(option)
					};
				}
			})
		}

		var calcSellingPrice = function(purchasePrice) {
			var sellingPrice = parseFloat(purchasePrice) + (parseFloat(purchasePrice) * 25 / 100)

			$("#sellingPrice").val(sellingPrice)
		}

		return {
			getPresentations:getPresentations,
			getMeasuresByPresentation:getMeasuresByPresentation,
			calcSellingPrice:calcSellingPrice
		}
	})();

	ItemBuilder.getPresentations($("#product").val())

	$("#product").on("change", function(){
		ItemBuilder.getPresentations($(this).val())
	})

	$("#presentation").on("change", function(){
		ItemBuilder.getMeasuresByPresentation($(this).val())
	})

	$("#purchasePrice").on("blur", function(){
		ItemBuilder.calcSellingPrice($(this).val())
	})
});
