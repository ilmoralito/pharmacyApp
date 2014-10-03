$(document).ready(function() {
	$(".presentation").on("click", function(){
      $(this).parent().next().toggle();
    });
});