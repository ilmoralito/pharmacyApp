$(document).ready(function() {
	$("#dutyDate").datepicker({
	 	dateFormat: "yy-mm-dd",
	 	minDate: +1
	});

	$("#bash").datepicker({
	 	dateFormat: "yy-mm-dd",
	 	minDate: +60
	});

	var ItemBuilder = (function() {
		var getPresentations = function(productId) {
			$.ajax({
				url:"getPresentationsByProduct/",
				data:{ productId:productId },
				dataType:"JSON",
				success:function(res) {
					var presentation = $("#presentation"),
							measure = $("#measure"),
							presentations = res.presentations;

					presentation.find("option").remove()
					measure.find("option").remove()

					//add presentations
					for (var i = presentations.length - 1; i >= 0; i--){
						var opt = $("<option>", { value:presentations[i].id, text:presentations[i].name });

						//add measures only for first presentation
						if (i == presentations.length - 1) {
							for (var j = presentations[i].measures.length - 1; j >= 0; j--) {
								var optionMeasure = $("<option>", { value:presentations[i].measures[j], text:presentations[i].measures[j] })

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

			$("#sellingPrice").val(sellingPrice.toFixed(2))
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
		ItemBuilder.calcSellingPrice($(this).val() || 0)
	})

	$('[data-toggle=tooltip]').tooltip();
});
