$(function(){
	$("#trigger").on("click", function(e){
		e.preventDefault();

		$("#box").toggle()
	})

	$("#send").on("click", function(e){
		e.preventDefault();
		var product = $("#newProduct"),
				providerId = $("#providerId").val();

		$.ajax({
			url:"addProduct/",
			data:{ productInstance:product.val(), providerId:providerId },
			success:function(data) {
				var opt = $("<option>", { value:data.id, text:data.name, selected:true });

				$("#product").append(opt);
			}
		})

		product.val("");
	})
});