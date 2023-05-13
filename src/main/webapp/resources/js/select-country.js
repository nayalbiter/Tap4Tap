$(".countryOption").on('click',function () {
    $("#hiddenCountry").val($(this).text());
    $("#selectedCountry").val($(this).text());
    
});

$("#selectedCountry").prop('disabled', true);

$("#submitButton").on('click',function(){
    $("#brewerySearchForm").submit();
});