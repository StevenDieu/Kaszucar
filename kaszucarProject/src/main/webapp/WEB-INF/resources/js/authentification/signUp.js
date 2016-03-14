var boolProgress = true;

function setYearHtml() {
	var yearToday = new Date();
	var yearsEighteen = yearToday.getFullYear() - 18;
	for (var year = yearsEighteen; year >= yearsEighteen - 90; year--) {
		$("#yearBirth").append($('<option>', {
		value : year,
		text : year
		}));
	}
}

function signUp() {
	var gender = $('input[name=gender]:checked').val()
	$(".errorConnexion").html("");
	if (boolProgress) {
		if (gender !== undefined && $(".lastName").val() !== "" && $(".email").val() !== "" && $(".password").val() !== "" && $(".yearBirth").val() !== "") {
			if ($(".password").val() === $(".confirmPassword").val()) {
				if (isAdressMail($(".email").val())) {
					if ($(".password").val().length >= 6 && $(".password").val().length <= 54) {
						if ($(".cgv:checked").length !== 0) {
							boolProgress = false;
							var self = this;
							$.ajax({
							type : "post",
							url : "ajaxInscription",
							data : "name=" + $(".name").val() + "&lastName=" + $(".lastName").val() + "&email=" + $(".email").val() + "&password=" + $(".password").val() + "&yearBirth=" + $(".yearBirth").val() + "&gender=" + gender,
							success : function(t) {
								t = JSON.parse(t);
								if (t.statut == "ok") {
									window.location.replace(t.redirect);
								} else if (t.statut == "nok") {
									$(".errorConnexion").html(t.message);
								}
								self.boolProgress = true;
							}
							});
						} else {
							$(".errorConnexion").html("Les conditions d'utilisation doivent être acceptés.");
						}
					} else {
						$(".errorConnexion").html("Le mot de passe doit être compris entre 6 et 54 charatères.");
					}
				} else {
					$(".errorConnexion").html("L'adresse mail n'est pas valide.");
				}
			} else {
				$(".errorConnexion").html("Les mots de passe ne sont pas identiques.");
			}
		}

	}

	return false;
}

function placeSignUp() {
	var valueDefaultHeigth = ((window.innerHeight / 2) - ($(".signUp")
			.height() / 2))
			+ ($("header").height() / 2) + 20;
	var headerHeight = $("header").height() + 20;
	if (valueDefaultHeigth <= headerHeight) {
		$(".signUp").css({
			"top" : headerHeight + 'px'
		});
	} else {
		$(".signUp").css({
			"top" : valueDefaultHeigth + 'px'
		});
	}
}

$(document).ready(function() {
	setYearHtml();

	placeSignUp();
	$(window).resize(function() {
		placeSignUp();
	});
	$(".signUp").fadeIn( "slow" );
	$('.signUp').on('submit', function(e) {
		e.preventDefault();
		if (boolProgress) {
			signUp();
		}
	});
});