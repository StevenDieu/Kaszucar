$(document).ready(function() {
	$(window).load(function() {
		if($("#from").val != "" || $("#to").val != ""){
			chooseMethod();
		}
	});


	
	$(".goReturn").on("click", function() {
		if ($(".goReturn").is(':checked') ) {
			$(".blocDateReturn").show();
		} else {
			$(".blocDateReturn").hide();
		}
	});
	
	$(".addCity").on("click", function() {
		addCity();
	});
});
