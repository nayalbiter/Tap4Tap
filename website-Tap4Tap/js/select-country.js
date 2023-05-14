$(".countryOption").on('click',function () {$("#selectedCountry").val($(this).text());});

$("#selectedCountry").prop('disabled', true);