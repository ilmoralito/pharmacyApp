$(document).ready(function() {
	$(".presentation").on("click", function(e){
		e.preventDefault()
    $(this).parent().next().toggle();
  });
});