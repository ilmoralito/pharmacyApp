$(function(){
	$("#trigger").on("click", function(e){
		e.preventDefault();

		$("#box").toggle()
	})

	$("#sendProduct").on("click", function(e){
		e.preventDefault();
		var product = $("#newProduct"), providerId = $("#providerId").val();

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


	$("#sendClient").on("click", function(e){
		e.preventDefault();
		var fullName = $("#fullName").val();
		var address = $("#address").val();
		var identificationCard = $("#identificationCard").val();
		$.ajax({
			url:"addClient/",
			data:{fullName:fullName, address:address, identificationCard:identificationCard},
			success:function(data) {
				var opt = $("<option>", { value:data.id, text:data.name, selected:true });

				$("#client.id").append(opt);
			}
		})
		fullName.val("");
		address.val("");
		identificationCard.val("");
	})

});