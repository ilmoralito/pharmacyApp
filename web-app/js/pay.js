$(function(){
	var balance = parseFloat($("#balance").val()), currentBalance

	payment.addEventListener("keyup", function(){
		var payment = parseFloat($("#payment").val())
		if (payment > balance) {
			$("#info").text("Aviso: La cantidad a bonar es mayor que la duda!!, debe corregir los datos.");
			$("#info").css({color: "#D2322D"})
			$("#amountPayment").val('');
			currentBalance = 0
			$("#currentBalance").text(currentBalance);
		}else{
			$("#info").text("Datos correctos");
			$("#info").css({color: "#428BCA"})
			currentBalance = balance - payment
			$("#currentBalance").text(currentBalance);
		}
  	})

	amountPayment.addEventListener("keyup", function(){
		var amountPayment = parseFloat(this.value), change
		var payment = parseFloat($("#payment").val())

		if (amountPayment < payment) {
			$("#info").text("Aviso: La cantidad con la que se paga es menor que el abono!!, debe corregir los datos.");
			$("#info").css({color: "#D2322D"})
			$("#changeLabel").text("0");
		}else{
			$("#info").text("Datos correctos");
			$("#info").css({color: "#428BCA"})
			change = amountPayment - payment
		}

		$("#changeLabel").text(change);
		$("#change").attr('value', change);
		

  	})

});