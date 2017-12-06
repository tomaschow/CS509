
  $(document).ready(function(){
      $("#ret-date-label").hide();
      $("#ret-date").hide();
    $( function() {
      $( "#dep-date" ).datepicker({
        defaultDate: "+1w",
        showOtherMonths: true,
        selectOtherMonths: true,
        dateFormat: "yy_mm_dd",
        minDate: -4,
        maxDate: +26,
        onClose: function(selectedDate){
          $("#ret-date").datepicker("option","minDate",selectedDate);
        }
      });
      $( "#ret-date" ).datepicker({
        defaultDate: "+1w",
        showOtherMonths: true,
        selectOtherMonths: true,
        dateFormat: "yy_mm_dd",
        minDate: -4,
        maxDate: +26,
        onClose: function(selectedDate){
          $("#dep-date").datepicker("option","maxDate",selectedDate);
        }
      });
    } );
    $("#flight-type-roundtrip").click(function(){
      $("#ret-date-label").show();
      $("#ret-date").show();
    });
    $("#flight-type-one-way").click(function(){
      $("#ret-date-label").hide();
      $("#ret-date").hide();
      $("#ret-date").val("");
      $("#dep-date").val("");
    });
    $(".modalbtn").click(function(){
      // alert("id= "+$(this).attr("id"));
    });
    $("input#filter0stop").change(function(){
      var ul,li,i,a,filter;
      filter = 0;
      ul = document.getElementById("flight-show");
      li = ul.getElementsByTagName("li");
      for(i = 0; i<li.length; i++){
        a = li[i].getElementsByTagName("a")[0];
        if(a.innerHTML==filter){
          if(li[i].style.display == ""){
            li[i].style.display = "none";
          }else if(li[i].style.display =="none"){
            li[i].style.display = "";
          }
        }
      }
    });
    $("input#filter1stop").change(function(){
      var ul,li,i,a,filter;
      filter = 1;
      ul = document.getElementById("flight-show");
      li = ul.getElementsByTagName("li");
      for(i = 0; i<li.length; i++){
        a = li[i].getElementsByTagName("a")[0];
        if(a.innerHTML==filter){
          if(li[i].style.display == ""){
            li[i].style.display = "none";
          }else if(li[i].style.display =="none"){
            li[i].style.display = "";
          }
        }
      }
    });
    $("input#filter2stop").change(function(){
      var ul,li,i,a,filter;
      filter = 2;
      ul = document.getElementById("flight-show");
      li = ul.getElementsByTagName("li");
      for(i = 0; i<li.length; i++){
        a = li[i].getElementsByTagName("a")[0];
        if(a.innerHTML==filter){
          if(li[i].style.display == ""){
            li[i].style.display = "none";
          }else if(li[i].style.display =="none"){
            li[i].style.display = "";
          }
        }
      }
    });
    function sortTravelTime(descending){
      var ul = $("#flight-show");
      if(descending==true){
      var li = ul.find("li").sort(function(a,b){
        return $(a).attr("travelTime") - $(b).attr("travelTime");})
      }else{
        var li = ul.find("li").sort(function(a,b){
        return $(b).attr("travelTime") - $(a).attr("travelTime");})
      }
      ul.find("li").remove();
      ul.append(li);
    }
    function sortPrice(descending){
      var ul = $("#flight-show");
      if(descending==true){
      var li = ul.find("li").sort(function(a,b){
        return $(a).attr("totalPrice") - $(b).attr("totalPrice");})
      }else{
        var li = ul.find("li").sort(function(a,b){
        return $(b).attr("totalPrice") - $(a).attr("totalPrice");})
      }
      ul.find("li").remove();
      ul.append(li);
    }
    function sortDepTime(descending){
      var ul = $("#flight-show");
      if(descending==true){
      var li = ul.find("li").sort(function(a,b){
        return $(a).attr("depTimeMillis") - $(b).attr("depTimeMillis");})
      }else{
        var li = ul.find("li").sort(function(a,b){
        return $(b).attr("depTimeMillis") - $(a).attr("depTimeMillis");})
      }
      ul.find("li").remove();
      ul.append(li);
    }
    function sortArrTime(descending){
      var ul = $("#flight-show");
      if(descending==true){
      var li = ul.find("li").sort(function(a,b){
        return $(a).attr("arrTimeMillis") - $(b).attr("arrTimeMillis");})
      }else{
        var li = ul.find("li").sort(function(a,b){
        return $(b).attr("arrTimeMillis") - $(a).attr("arrTimeMillis");})
      }
      ul.find("li").remove();
      ul.append(li);
    }
    $("select#sort_option").change(function(){

      if($(this).val()=="sort_price_lo"){
        sortPrice(true);
      }
      if($(this).val()=="sort_price_hi"){
        sortPrice(false);
      }
      if($(this).val()=="sort_dep_early"){
        sortDepTime(true);
      }
      if($(this).val()=="sort_dep_late"){
        sortDepTime(false);
      }
      if($(this).val()=="sort_arr_early"){
        sortArrTime(true);
      }
      if($(this).val()=="sort_arr_late"){
        sortArrTime(false);
      }
      if($(this).val()=="sort_flightTime_lo"){
        sortTravelTime(true);
      }
      if($(this).val()=="sort_flightTime_hi"){
        sortTravelTime(false);
      }
      
      
    }); 

    
  });
