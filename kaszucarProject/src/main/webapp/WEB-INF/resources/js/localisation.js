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

	$("#from").focusout(function() {
		chooseMethod()
	})

	$("#to").focusout(function() {
		chooseMethod()
	})
}

function autoComplete(input) {

	if (input !== undefined) {

		var options = {
		types : [ '(cities)' ],
		componentRestrictions : {
			country : 'fr'
		}
		};
		autocomplete = new google.maps.places.Autocomplete(input, options);
	}
}

function calculateAndDisplayRoute() {

	initMap();

	var waypts = [];
	$('.thisWaipoints').each(function() {
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
			window.alert('Directions request failed due to ' + status);
		}
	});
}

function geocodeAddress(address) {

	initMap();
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			map.setCenter(results[0].geometry.location);
			var marker = new google.maps.Marker({
			map : map,
			position : results[0].geometry.location
			});
		} else {
			alert('Geocode was not successful for the following reason: ' + status);
		}
	});
}

function chooseMethod() {
	var start = $("#from").val();
	var end = $("#to").val();

	if (start !== "" && end === "") {
		geocodeAddress(start);
	} else if (start === "" && end !== "") {
		geocodeAddress(end);
	} else if (start !== "" && end !== "") {
		calculateAndDisplayRoute()
	}
}

