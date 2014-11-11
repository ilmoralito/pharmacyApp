$(function(){
	var balance = parseFloat($("#balance").val()), currentBalance

	amountPayment.addEventListener("keyup", function(){
		var amountPayment = parseFloat(this.value), change
		var payment = parseFloat($("#payment").val())

		if (payment > balance) {
			$("#info").text("La cantidad a bonar es mayor que la duda!!");
		}else{
			$("#info").text("");
			change = amountPayment - payment
			currentBalance = balance - payment
		}

		$("#changeLabel").text(change);
		$("#change").attr('value', change);
		$("#currentBalance").text(currentBalance);

  	})

});