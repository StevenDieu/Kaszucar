var boolProgress = true;

function signIn(){
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

	$('.singnIn').on('submit', function(e) {
		e.preventDefault();
		if (boolProgress) {
			signIn();
		}
	});

});