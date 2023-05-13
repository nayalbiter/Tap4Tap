$(".countryOption").on('click',function () {
    $("#selectedCountry").val($(this).text());
    $("#hiddenCountry").val($(this).text());
});

$("#selectedCountry").prop('disabled', true);

$("#submitButton").on('click',function(){
    $("#brewerySearchForm").submit();
});