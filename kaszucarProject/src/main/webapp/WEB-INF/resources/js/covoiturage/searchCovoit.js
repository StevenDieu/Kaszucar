function applyFilter() {
  $(".allCovoit").each(function() {
    var  isFinish = false;
    $(this).removeClass("hide");
    if ($(".hoursFilter").val() !== "toutesLesHeures") {
      var hours = parseInt($(this).data("hours").split(':')[0]);
      if(hours < parseInt($(".hoursFilter").val() - 5) || hours > parseInt($(".hoursFilter").val())){
        $(this).addClass("hide");
        isFinish = true;
      }
    }
    if (!isFinish && $(".prixFilter").val() !== "tousLesPrix") {
      var price = parseInt($(this).data("price"));
      if(price < parseInt($(".prixFilter").val() - 10) || price > parseInt($(".prixFilter").val())){
        $(this).addClass("hide");
        isFinish = true;
      }
    }
    if (!isFinish && $(".legFilter").val() !== "tousLesBagages") {
      if($(".legFilter").val() !== $(this).data("sizeofluggage")){
        $(this).addClass("hide");
        isFinish = true;
      }
      
    }
    if(!isFinish){
      var array = [];
      if($(this).data("smoking") != undefined){
        array.push("smoking");
      }
      if($(this).data("animals") != undefined){
        array.push("animals");
      }
      if($(this).data("musics") != undefined){
        array.push("musics");
      }
      if($(this).data("detour") != undefined){
        array.push("detour");
      }
      if($(this).data("food") != undefined){
        array.push("food");
      }
      $(".optionFilter:checked").each(function() {
        if(!isFinish && array.indexOf($(this).data("type")) == -1){
          isFinish = true;
        }
      });
      if(isFinish){
        $(this).addClass("hide");
        isFinish = true;
      }
    }


    if (!isFinish && $(".placeFilter").val() !== "1") {
      if($(this).data("sitnumber") < $(".placeFilter").val()){
        $(this).addClass("hide");
        isFinish = true;
      }
    }
    if (!isFinish && $(".confortFilter").val() !== "tousLesConforts") {
      if($(".confortFilter").val() !== $(this).data("confort")){
        $(this).addClass("hide");
        isFinish = true;
      }
    }
    if (!isFinish && $(".photoFilter:checked").length == 1) {
      if($(this).data("photo") == undefined){
        $(this).addClass("hide");
      }
    }
  });
}

$(document).ready(function() {
  $(".filterSearch").on("change", function() {
    applyFilter();
  })
});
