$(function(){
	$("#trigger").on("click", function(e){
		e.preventDefault();

		var box = $("#box");

		box.toggle();

		if (box.is(":visible")) {
			$("#fullName").focus();
		}
	})

	$("#triggerTelephone").on("click", function(e) {
		e.preventDefault();
		var _this = $(this),
				len = $('.clientPhones').length;
		$(".clientPhones:last").clone().insertAfter(_this).find("input").val("").focus()
		if (len >= 2){
			_this.off('click')
		}
	})

	$("#sendClient").on("click", function(e){
		e.preventDefault();
		var fullName = $("#fullName");
		var address = $("#address");
		var identificationCard = $("#identificationCard");
		var phones = [];
		$(".phones").each(function(){
        if(this.value) {
	            phones.push(this.value)
	        }
	    })

		$.ajax({
			url:"addClient/",
			data:{ fullName:fullName.val(), address:address.val(), identificationCard:identificationCard.val(), phones:phones.join(", ") },
			success:function(data) {
				if (data) {
					var opt = $("<option>", { value:data.id, text:data.fullName, selected:true });
					$("#client\\.id").append(opt);
					fullName.val("");
					address.val("");
					identificationCard.val("");
					$(".phones").val("");
				}
			}
		})
		fullName.focus()
	})
});
