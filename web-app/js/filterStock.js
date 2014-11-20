if (localStorage.medicinesState == "true") {
	$("#medicines").show()
} else {
	$("#medicines").hide()
}

if (localStorage.itemsState == "true") {
	$("#items").show()
} else {
	$("#items").hide()
}

if (localStorage.brandProductsState == "true") {
	$("#brandProducts").show()
} else {
	$("#brandProducts").hide()
}

$("#medicine").on("click", function() {
	var target = $("#medicines")

	target.toggle()
	localStorage.medicinesState = target.is(':visible')
})

$("#item").on("click", function() {
	var target = $("#items")

	target.toggle()
	localStorage.itemsState = target.is(':visible')
})

$("#brandProduct").on("click", function() {
	var target = $("#brandProducts")

	target.toggle()
	localStorage.brandProductsState = target.is(':visible')
})