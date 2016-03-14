var errorMaps;

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

		$(".blocWaypoitsCity").append('<div class="newWaypoints"><div class="cityWaypoints">' + newWaypoints + '</span><input type="hidden" name="waypoints[]" value="' + newWaypoints + '" /> <button type="button" class="btn btn-danger btn-xs removeNewWaypoints">Supprimer</button></div></div>')
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

function initialiseInputCar() {

	if ($(".chooseCar").val() == -1) {
		$(".brand").val("")
		$(".model").val("")
		$(".comfort").val("NORMAL")
		$(".color").val("noir")

		$(".brand").attr("disabled", false);
		$(".model").attr("disabled", false);
		$(".comfort").attr("disabled", false);
		$(".color").attr("disabled", false);
	} else {
		$(".brand").val($(".chooseCar").find(':selected').data("brand"))
		$(".model").val($(".chooseCar").find(':selected').data("model"))
		$(".comfort").val($(".chooseCar").find(':selected').data("comfort"))
		$(".color").val($(".chooseCar").find(':selected').data("color"))

		$(".brand").attr("disabled", true);
		$(".model").attr("disabled", true);
		$(".comfort").attr("disabled", true);
		$(".color").attr("disabled", true);
	}
}

function testNumber() {
	var number = $(".place").val();

	if (isNaN(number)) {
		$(".place").val(1);
	} else if (number <= 0) {
		$(".place").val(1);
	} else if (number >= 6) {
		$(".place").val(5);
	} else if (isNotInt(number)) {
		if (parseInt(number) + 1 == 6) {
			$(".place").val(5);
		} else {
			$(".place").val(parseInt(number) + 1);
		}
	}
}

$(document).ready(function() {
	$(window).load(function() {
		if ($("#from").val != "" || $("#to").val != "") {
			chooseMethod();
		}
	});

	$(".chooseCar").on("change", function() {
		initialiseInputCar();
	})

	$(".goReturn").on("click", function() {
		if ($(".goReturn").is(':checked')) {
			$(".blocDateReturn").show();
			$(".dateReturnTrip").addClass("required");
			$(".hoursReturnTrip").addClass("required");
			$(".minReturnTrip").addClass("required");

		} else {
			$(".blocDateReturn").hide();
			$(".dateReturnTrip").removeClass("required");
			$(".hoursReturnTrip").removeClass("required");
			$(".minReturnTrip").removeClass("required");

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

	$(".place").on("change", function() {
		testNumber();
	});

	$(".plus").on("click", function() {
		if (isNaN($(".place").val())) {
			$(".place").val(1);
		} else {
			$(".place").val(parseInt($(".place").val()) + 1);
			testNumber();
		}

	});

	$(".minus").on("click", function() {
		if (isNaN($(".place").val())) {
			$(".place").val(1);
		} else {
			$(".place").val(parseInt($(".place").val()) - 1);
			testNumber();
		}
	});
});
