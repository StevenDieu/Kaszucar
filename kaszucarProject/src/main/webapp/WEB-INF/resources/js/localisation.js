var directionsService;
var directionsDisplay;
var geocoder;
var map;
var needInitMap = false;

function initMap() {
	directionsService = new google.maps.DirectionsService;
	directionsDisplay = new google.maps.DirectionsRenderer;
	geocoder = new google.maps.Geocoder();

	map = new google.maps.Map(document.getElementById('map'), {
		zoom : 5,
		center : {
			lat : 47,
			lng : 1.7
		}
	});

	directionsDisplay.setMap(map);
}

function calculateAndDisplayRoute() {

	initMap();

	var waypts = [];
	$('.cityWaypoints').each(function() {
		waypts.push({
			location : $(this).text(),
			stopover : true
		});
	});

	directionsService.route({
		origin : $("#from").val(),
		destination : $("#to").val(),
		waypoints : waypts,
		optimizeWaypoints : true,
		travelMode : google.maps.TravelMode.DRIVING
	}, function(response, status) {
		if (status === google.maps.DirectionsStatus.OK) {
			directionsDisplay.setDirections(response);
			var route = response.routes[0];
			needInitMap = true;
		} else {
			error = true;
			$("#from").parent().parent().find(".help-block").html(
					"Le chemin est introuvable.");
			$("#from").parent().parent().addClass("has-error");
			$("#to").parent().parent().find(".help-block").html(
					"Le chemin est introuvable.");
			$("#to").parent().parent().addClass("has-error");
		}
	});
}

function geocodeAddress(address) {

	initMap();
	geocoder.geocode({
		'address' : address.val()
	}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			map.setCenter(results[0].geometry.location);
			var marker = new google.maps.Marker({
				map : map,
				position : results[0].geometry.location
			});
		} else {
			error = true;
			address.parent().parent().find(".help-block").html(
					"Cette ville est introuvable.");
			address.parent().parent().addClass("has-error");
		}
	});
}

function chooseMethod() {
	var start = $("#from").val();
	var end = $("#to").val();

	error = false;
	$("#from").parent().parent().find(".help-block").html("");
	$("#from").parent().parent().removeClass("has-error");
	$("#to").parent().parent().find(".help-block").html("");
	$("#to").parent().parent().removeClass("has-error");

	deleteIfExistWaypoints(start, end);
	
	if (start !== "" && end === "") {
		geocodeAddress($("#from"));
	} else if (start === "" && end !== "") {
		geocodeAddress($("#to"));
	} else if (start !== "" && end !== "") {
		calculateAndDisplayRoute()
	}

	if (error == false && start !== "" && start == end) {
		error = true
		$("#from").parent().parent().find(".help-block").html(
				"Les deux adresses sont identiques");
		$("#from").parent().parent().addClass("has-error");
		$("#to").parent().parent().find(".help-block").html(
				"Les deux adresses sont identiques");
		$("#to").parent().parent().addClass("has-error");
	}
	
}

function deleteIfExistWaypoints(start, end){
	$('.cityWaypoints').each(function() {
		if(start === $(this).text() || end ===  $(this).text() ){
			$(this).parent().remove();
		}
	});
}

function dontSubmitFormWithGoogle(idInput) {
	if (document.getElementById(idInput) !== null) {
		$('#' + idInput).keydown(function(e) {
			if (e.which == 13 && $('.pac-container:visible').length)
				return false;
		});
	}
}

function autoComplete(idInput) {
	if (document.getElementById(idInput) !== null) {
		input = document.getElementById(idInput);

		var options = {
			types : [ '(cities)' ],
			componentRestrictions : {
				country : 'fr'
			}
		};
		autocomplete = new google.maps.places.Autocomplete(input, options);

	}

}

$(document).ready(function() {

	$(window).load(function() {
		dontSubmitFormWithGoogle('from');
		dontSubmitFormWithGoogle('waypoints');
		dontSubmitFormWithGoogle('to');

		autoComplete('from');
		autoComplete('waypoints');
		autoComplete('to');
	});
})