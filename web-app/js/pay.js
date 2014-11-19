$(function(){
	var balance = parseFloat($("#balance").val()), currentBalance

	payment.addEventListener("keyup", function(){
		this.value = (this.value + '').replace(/[^0-9+.]/g, '');
		var payment = parseFloat($("#payment").val())
		if (payment > balance) {
			$("#info").text("Aviso: La cantidad a bonar es mayor que la duda!!, debe corregir los datos.");
			$("#info").css({color: "#D2322D"})
			$("#amountPayment").val('');
			currentBalance = 0
			$("#currentBalance").text(currentBalance);
			$("#btnregistration").attr('disabled', 'disabled');
		}else{
			$("#info").text("Datos correctos");
			$("#info").css({color: "#428BCA"})
			currentBalance = balance - payment
			$("#currentBalance").text(currentBalance);
			$("#btnregistration").attr('disabled', false);
		}
  	})

	amountPayment.addEventListener("keyup", function(){
		this.value = (this.value + '').replace(/[^0-9+.]/g, '');
		var amountPayment = parseFloat(this.value), change
		var payment = parseFloat($("#payment").val())

		if (amountPayment < payment) {
			$("#info").text("Aviso: La cantidad con la que se paga es menor que el abono!!, debe corregir los datos.");
			$("#info").css({color: "#D2322D"})
			$("#changeLabel").text("0");
			$("#btnregistration").attr('disabled', 'disabled');
		}else{
			if (payment < balance) {
				$("#info").text("Datos correctos");
				$("#info").css({color: "#428BCA"})
			}else{
				$("#info").text("Aviso: La cantidad a bonar es mayor que la duda!!, debe corregir los datos.");
				$("#info").css({color: "#D2322D"})
			}
			change = amountPayment - payment
			$("#btnregistration").attr('disabled', false);
			if (payment > balance) {
				$("#btnregistration").attr('disabled', 'disabled');
			}
		}

		$("#changeLabel").text(change);
		$("#change").attr('value', change);
  	})

});