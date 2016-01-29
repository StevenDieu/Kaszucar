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

var boolProgress = true;

function signUp(){
	var gender = $('input[name=gender]:checked').val()

	if (gender !== undefined && $(".lastName").val() !== "" && $(".email").val() !== "" && 
			$(".password").val() !== "" && $(".password").val() === $(".confirmPassword").val() && $(".yearBirth").val() !== "") {
		boolProgress = false;
		var self = this;
			$.ajax({
				type : "post",
				url : "ajaxInscription",
				data : "name=" + $(".name").val() + "&lastName=" + $(".lastName").val() + "&email=" + $(".email").val() +
				"&password=" + $(".password").val() + "&yearBirth=" + $(".yearBirth").val() + "&gender=" + gender,
				success : function(t) {
					t = JSON.parse(t);
					if (t.statut == "ok") {
						
					} else if (t.statut == "nok") {
						$(".errorConnexion").html(t.message);
					}
					self.boolProgress = true;
				}
			});
	}else{
		$(".errorConnexion").html("Aucun champ ne doit \352tre vide.");
	}
	return false;
}

$(document).ready(function() {
	setYearHtml();
	
	$('.signUp').on('submit', function(e) {
		e.preventDefault();
		if (boolProgress) {
			signUp();
		}
	});
});