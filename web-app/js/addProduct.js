$(function(){
	$("#trigger").on("click", function(e){
		e.preventDefault();

		var box = $("#box");

		box.toggle();

		if (box.is(":visible")) {
			$("#providerId").focus();
		}
	})

	$("#sendProduct").on("click", function(e){
		e.preventDefault();

		var provider = $("#providerId");
		var product = $("#newProduct");
		var location = $("#location");

		$.ajax({
			url:"addProduct/",
			data:{ providerId:provider.val(), productInstance:product.val(), locationInstance:location.val()},
			success:function(data) {
				if (data) {
					var opt = $("<option>", { value:data.id, text:data.name, selected:true });

					$("#product").append(opt);

					product.val("");
				}
			}
		})

		provider.focus()
	})
});