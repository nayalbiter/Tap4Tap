<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width-device-width, initial-scale =1, shrink-to-fit=no">
    <title>BrewerySearchResults</title>
    <link rel="stylesheet" href="../css/homepage.css">

    <!--check the responsiveness of the website part here and the css-->
</head>

<body>
    <header>
        <div class="navbar">

            <nav>
                <a href="home.ftl">Home</a>

            </nav>


        </div>

        <h1> This page "Brewery Search Results" is under construction </h1>

    </header>
    <section>
    <p>
    You chose brewery: ${breweryName}
    <br>
    You chose country: ${country}
    <br>
    You chose state/province: ${stateProvince}
    <br>
    You chose city: ${city}
    <br>
    You chose zip code: ${zipCode}
    <br>
    Found these breweries: <br><br>
    <i>Breweries</i><br>
    <#list breweries as brewery>
    ${brewery.name} <br>
    </#list>
    <br>
   <i> All Breweries</i><br>
    <#list allBreweries as brewery>
    ${brewery.name} <br>
    ${brewery.country} <br>
    </#list>
    </p>
    </section>
    <footer>

        <h5> Disclaimer: This website is for informational purposes only and does not sell or otherwise provide any
            age-restricted products. Tap4Tap urges you to follow your local laws regarding the consumption, sales, and
            possession of alcoholic beverages. </h5>

        <p> Kirkland, WA &#169; 2023 Tap4Tap created by Carmen Albiter, Carolina Solar-Morales and Joy Hyunjung Oh. </p>
    </footer>
</body>

</html>
