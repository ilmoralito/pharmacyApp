$(function() {
	$("#genericName").on("change", function(){
		var genericName = $(this).val()

		$.ajax({
			url:"filterMedicinesByGenericName/",
			data:{ genericName:genericName },
			success:function(data) {
				var product = $("#product")

				if (data.length) {
					product.find("option").remove()

					$.each (data, function(index, medicine) {
						var option = $("<option>", { value:medicine.name, text:medicine.name });

						product.append(option)
					})
				}
			}
		})
	})
});