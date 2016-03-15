var boolProgress = true;

function signIn() {
	$(".errorConnexion").html("");

	if (boolProgress) {
		if ($(".email").val() !== "" && $(".pwd").val() !== "") {
			boolProgress = false;
			var self = this;
			$
					.ajax({
						type : "post",
						url : "ajaxConnexion",
						data : "email=" + $(".email").val() + "&pwd="
								+ $(".pwd").val(),
						success : function(t) {
							t = JSON.parse(t);
							if (t.statut == "ok") {
								if ($(".redirect").val() != "") {
									document.location.href= $(".redirect") .val();
									return;
								}
								window.location.replace(t.redirect);

							} else if (t.statut == "nok") {
								$(".errorConnexion").html(t.message);
							}
							self.boolProgress = true;
						}
					});
		}
	}
	return false;
}

function placeSignIn() {
	var valueDefaultHeigth = ((window.innerHeight / 2) - ($(".singnIn")
			.height() / 2))
			+ ($("header").height() / 2) + 20;
	var headerHeight = $("header").height() + 20;
	if (valueDefaultHeigth <= headerHeight) {
		$(".singnIn").css({
			"top" : headerHeight + 'px'
		});
	} else {
		$(".singnIn").css({
			"top" : valueDefaultHeigth + 'px'
		});
	}
}

$(document).ready(function() {

	placeSignIn();
	$(window).resize(function() {
		placeSignIn();
	});
	$(".singnIn").fadeIn( "slow" );
	$('.singnIn').on('submit', function(e) {
		e.preventDefault();
		if (boolProgress) {
			signIn();
		}
	});
});