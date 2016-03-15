var boolProgress = true;
var setTimeoutText;
var error = false;

function showTest(classMessage, text) {
	clearTimeout(setTimeoutText);
	$(classMessage).text(text).fadeIn();
	setTimeoutText = setTimeout(function() {
		$(classMessage).text(text).fadeOut();
	}, 5000);
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


	$(".proposition").on("change", function() {
		if ($(".proposition").val() == "search") {
			$(".submitSearch").val("Rechercher");
			$(".form-search-home").attr("action", "rechercher-un-covoiturage");
			$(".from").addClass("required");
			$(".to").addClass("required");

		} else {
			$(".submitSearch").val("Proposer");
			$(".form-search-home").attr("action", "proposer-un-covoiturage");
			$(".from").removeClass("required");
			$(".to").removeClass("required");
			$(".form-group").removeClass("has-error");
		}
	})

	$("form").on("submit", function() {

		if(error){
			return false;
		}
		
		var mess_required = "Ce champ est obligatoire.";
		var mess_prix = "Ce champ doit être un prix et supérieur à 4 €.";
		var mess_date = "La date de retour doit être supérieur à la date d'aller.";

		$('.help-block').html("");
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
		
		if(submit && $(".checkDate").size() != 0){
			if($(".checkDate").is(':checked')){
				var checkDateDayFirst = $(".checkDateDayFirst").val().split("-");
				var dateFirst = new Date(checkDateDayFirst[0], checkDateDayFirst[1], checkDateDayFirst[2]);
				dateFirst.setHours($(".checkDateHoursFirst").val());
				dateFirst.setMinutes($(".checkDateMinsFirst").val());

				var checkDateDayLast = $(".checkDateDayLast").val().split("-");
				var dateLast = new Date(checkDateDayLast[0], checkDateDayLast[1], checkDateDayLast[2]);
				dateLast.setHours($(".checkDateHoursLast").val());
				dateLast.setMinutes($(".checkDateMinsLast").val());
				
				if(dateLast <= dateFirst){
					var submit = false;
					$(".checkDateLast").addClass("has-error");
					$(".checkDateLast").find(".help-block").html(mess_date);
				}
			}
		}


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