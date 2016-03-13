function addCity() {
	var newWaypoints = $("#waypoints").val();
	if (newWaypoints !== "" && $(".newWaypoints").size() < 6) {

		var exitFunction = false;
		$('.cityWaypoints').each(function() {
			if ($(this).text() === $("#waypoints").val()) {
				exitFunction = true;
			}
		});

		if (exitFunction === true) {
			showTest(".messageErrorWaypoints", "Ce lieu de passage est déja dans la liste")
			return false;
		}

		$(".blocWaypoitsCity").append('<div class="newWaypoints"><label class="cityWaypoints">' + newWaypoints + '</span><input type="hidden" name="waypoints[]" value="' + newWaypoints + '" /> <button type="button" class="btn btn-danger btn-xs removeNewWaypoints">Supprimer</button></label></div>')
		chooseMethod();
		$("#waypoints").val("");
		$(".removeNewWaypoints").off("click");
		$(".removeNewWaypoints").on("click", function() {
			$(this).parent().remove();
			chooseMethod();
		});
	} else {
		showTest(".messageErrorWaypoints", "Aucun lieu de passage saisi.")
	}
}

$(document).ready(function() {
	$(window).load(function() {
		if ($("#from").val != "" || $("#to").val != "") {
			chooseMethod();
		}
	});

	$(".goReturn").on("click", function() {
		if ($(".goReturn").is(':checked')) {
			$(".blocDateReturn").show();
		} else {
			$(".blocDateReturn").hide();
		}
	});

	$(".addCity").on("click", function() {
		addCity();
	});

	$("#from").focusout(function() {
		chooseMethod();
	})

	$("#to").focusout(function() {
		chooseMethod();
	})
});
