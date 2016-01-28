function setYearHtml() {
	var yearToday = new Date();
	var yearsEighteen = yearToday.getFullYear() - 18;
	for (var year = yearsEighteen; year >= yearsEighteen - 90; year--) {
		$("#yearBirth").append($('<option>', {
		    value: year,
		    text: year
		}));
	}
}

$(document).ready(function() {
	setYearHtml();
});