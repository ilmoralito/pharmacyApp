$(function(){
	$("#trigger").on("click", function(e){
		e.preventDefault();

		$("#box").toggle()
	})

	$("#sendClient").on("click", function(e){
		e.preventDefault();

		var fullName = $("#fullName");
		var address = $("#address");
		var identificationCard = $("#identificationCard");

		$.ajax({
			url:"addClient/",
			data:{fullName:fullName.val(), address:address.val(), identificationCard:identificationCard.val()},
			success:function(data) {
				if (data) {
					var opt = $("<option>", { value:data.id, text:data.fullName, selected:true });

					$("#client\\.id").append(opt);
				}
			}
		})

		fullName.val("");
		address.val("");
		identificationCard.val("");
	})
});
