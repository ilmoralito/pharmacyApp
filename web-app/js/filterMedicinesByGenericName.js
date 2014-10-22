$(function() {
	$("#grenericName").on("change", function(){
		var genericName = $(this).val()

		$.ajax({
			url:"filterMedicinesByGenericName/",
			data:{ genericName:genericName },
			success:function(data) {
				var product = $("#product")

				if (data.length) {
					product.find("option").remove()

					for (var i = 0; i < data.length; i++) {
						var opt = $("<option>", { value:data[i].id, text:data[i].name });

						product.append(opt)
					}
				}
			}
		})
	})
});