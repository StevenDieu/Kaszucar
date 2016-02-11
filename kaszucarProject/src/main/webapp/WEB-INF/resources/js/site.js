var boolProgress = true;

function signIn() {
	if (boolProgress) {
		if ($(".email").val() !== "" && $(".pwd").val() !== "") {
			boolProgress = false;
			var self = this;
			$.ajax({
			type : "post",
			url : "ajaxConnexion",
			data : "email=" + $(".email").val() + "&pwd=" + $(".pwd").val(),
			success : function(t) {
				t = JSON.parse(t);
				if (t.statut == "ok") {
					if($(".redirect").val() != ""){
						window.location.replace($(".redirect").val());
					}
				} else if (t.statut == "nok") {
					$(".errorConnexion").html(t.message);
				}
				self.boolProgress = true;
			}
			});
		} else {
			$(".errorConnexion").html("Aucun champ ne doit \352tre vide.");
		}
	}
	return false;
}

function isAdressMail(email) {
	var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');
	if (reg.test(email)) {
		return true;
	} else {
		return false;
	}
}

$(document).ready(function() {

	$('.singnIn').on('submit', function(e) {
		e.preventDefault();
		if (boolProgress) {
			signIn();
		}
	});

	$(".proposition").on("change", function() {
		if ($(".proposition").val() == "search") {
			$(".submitSearch").val("Rechercher")
			$(".form-search-home").attr("action", "rechercher-un-covoiturage");
		} else {
			$(".submitSearch").val("Proposer")
			$(".form-search-home").attr("action", "proposer-un-covoiturage");
		}
	})




});