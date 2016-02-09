function addCity() {
	var newWaypoints = $("#waypoints").val();
	if (newWaypoints !== "") {
		var start = $("#start").val();
		var end = $("#end").val();
		$(".blocWaypoitsCity").append('<div class="newWaypoints"><span class="thisWaipoints">' + newWaypoints + '</span><input type="hidden" name="waypoints[]" value="' + newWaypoints + '" /> <a class="removeNewWaypoints button-red">Supprimer</a></div>')
		if (start !== "" && end !== "") {
			calculateAndDisplayRoute()
		}
		$(".removeNewWaypoints").off("click");
		$(".removeNewWaypoints").on("click", function() {
			$(this).parent().remove();

			if (start !== "" && end !== "") {
				calculateAndDisplayRoute()
			}
		});
	}
}

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
