var boolProgress = true;
var setTimeoutText;

function showTest(classMessage, text) {
	clearTimeout(setTimeoutText);
	$(classMessage).text(text).fadeIn();
	setTimeoutText = setTimeout(function() {
		$(classMessage).text(text).fadeOut();
	}, 5000);
}

function signIn() {
	$(".errorConnexion").html("");

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
					if ($(".redirect").val() != "") {
						window.location.replace($(".redirect").val());
						return;
					}
					window.location.replace(t.redirect);

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

function isNotInt(n) {
	return n % 1 != 0;
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
			$(".submitSearch").val("Rechercher");
			$(".form-search-home").attr("action", "rechercher-un-covoiturage");
		} else {
			$(".submitSearch").val("Proposer");
			$(".form-search-home").attr("action", "proposer-un-covoiturage");
		}
	})

	$("form").on("submit", function() {

		var mess_required = "Ce champ est obligatoire.";
		var mess_prix = "Ce champ doit être un prix et supérieur à 4 €.";

		$('.form-control').html();
		$('.has-error').removeClass("has-error");

		var submit = true;

		var regexp_prix = new RegExp("^[0-9]{1,}(,[0-9]{1,2}|[.][0-9]{1,2}){0,1}$");

		$('.required').each(function() {
			var type = $(this).attr('type');
			if (type === "checkbox") {
				if (!$(this).is(':checked')) {
					form = $(this).parent().parent();
					form.addClass("has-error");
					form.find(".help-block").html(mess_required);
					submit = false;
				}
			} else {
				if ($(this).val() == '') {
					form = $(this).parent().parent();
					form.addClass("has-error");
					form.find(".help-block").html(mess_required);
					submit = false;
				}
			}

		});

		if (submit) {
			$('.price').each(function() {
				if (!regexp_prix.test($(this).val()) && $(this).val() > 4) {
					form = $(this).parent().parent();
					form.addClass("has-error");
					form.find(".help-block").html(mess_prix);
					submit = false;
				} else {
					$(this).val($(this).val().replace(",", "."));
				}
			});
		}

		return submit;
	});

});