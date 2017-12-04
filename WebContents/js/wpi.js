
  $(document).ready(function(){
      $("#ret-date-label").hide();
      $("#ret-date").hide();
    $( function() {
      $( "#dep-date" ).datepicker({
        defaultDate: "+1w",
        showOtherMonths: true,
        selectOtherMonths: true,
        dateFormat: "yy_mm_dd",
        onClose: function(selectedDate){
          $("#ret-date").datepicker("option","minDate",selectedDate);
        }
      });
      $( "#ret-date" ).datepicker({
        defaultDate: "+1w",
        showOtherMonths: true,
        selectOtherMonths: true,
        dateFormat: "yy_mm_dd",
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
    $("select#sort_option").change(function(){
            alert("Selected: "+$(this).val());
    }); // THis is for result.jsp
  });
