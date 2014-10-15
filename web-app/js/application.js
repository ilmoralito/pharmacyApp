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

		var getPresentations = function(id) {
			$.ajax({
				url:"getPresentationsByProduct/",
				data:{ id:id },
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

		return {
			getPresentations:getPresentations,
			getMeasuresByPresentation:getMeasuresByPresentation
		}
	})();

	ItemBuilder.getPresentations($("#product").val())

	$("#product").on("change", function(){
		ItemBuilder.getPresentations($(this).val())
	})

	$("#presentation").on("change", function(){
		ItemBuilder.getMeasuresByPresentation($(this).val())
	})

	$('[data-toggle=tooltip]').tooltip();
});
