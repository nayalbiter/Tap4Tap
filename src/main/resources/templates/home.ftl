<!DOCTYPE html>
<html>
    <head>
        <title>tap4tap Project - Homepage</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="resources/css/tap4tap.css">
    </head>
    <body>
        <header>  
        <!--add here the nav section with the home button and  login/
            create an account & my name/ log out button-->

        <a href="index.html">Home</a>

            <div class="dropdown1">
                <button class="dropbtn" onclick="selectLoginOrCreateAccount()"> <#if loggedIn>Log out  <#else> Log in </#if>/ Create Account

                </button>
                <div class="dropdown-content" id="logInCreateAccount">
                    <a href="login.ftl">
                    <#--  <input type="hidden" id="cmd" name="cmd" value="login" /><#if loggedIn>Log Out<#else>Log In</#if></a>  -->
                    <a href="createAccount.html">Create an Account</a>

                </div>
            </div>
        </div>

        <h1> Tap4Tap</h1>

    </header>

    <section class="Image">

        <div class="imageContainer">

            <!--add the beer picture here  -->
            <img src="resources/images/18.jpg" alt="Beer" class="center">

        </div>

    </section>
    <#if user??><h2> Welcome ${user}!</h2></#if>
    <h3> Find the best beer places here!</h3>

    <section class="searchButtons"> 

        <!--add the buttons for the search here-->
        <div class="brewerySearch">

        <#if error??>
            <p>Error was ${error}</p>
        </#if>

            <form action="/tap4tap/servlet" method="get"> <!--FIX THIS PART WITH JAVA CODE--> 
                <input type="hidden" id="cmd" name="cmd" value="searchResult" />
                <label for="country"> Select a country:</label>
                <select name="country" id="countries">
                    <option value="Austria">Austria</option>
                    <option value="England">England</option>
                    <option value="France">France</option>
                    <option value="Isle of Man">Isle of Man</option>
                    <option value="Ireland">Ireland</option>
                    <option value="Poland">Poland</option>
                    <option value="Portugal">Portugal</option>
                    <option value="Scotland">Scotland</option>
                    <option value="South Korea">South Korea</option>
                    <option value="United States" selected>United States</option>
                </select>
                <br><br>
                <p> Search by brewery name:</p>
                <label for="breweryName">Brewery Name: </label>
                <input type="text" id="breweryName" name="breweryName"><br><br>

                <p> Or search by location:</p>
                <label for="stateOrProvince">State/Province: </label>
                <input type="text" id="stateProvince" name="stateProvince"><br><br>

                <label for="city">City: </label>
                <input type="text" id="city" name="city"><br><br>

                <label for="zipCode">Zip Code: </label>
                <input type="text" id="zipCode" name="zipCode"><br><br>

                <input type="submit" value="Let's go!"> <!--FIX THIS PART WITH JAVA CODE--> 
            </form>


        </div>


    </section>


    <footer>

        <h5> Disclaimer: This website is for informational purposes only and does not sell or otherwise provide any
            age-restricted products. Tap4Tap urges you to follow your local laws regarding the consumption, sales, and
            possession of alcoholic beverages. </h5>

        <p> Kirkland, WA &#169; 2023 Tap4Tap created by Carmen Albiter, Carolina Solar-Morales and Joy Hyunjung Oh. </p>
    </footer>



    <script> 
        /*this code is used for the dropdown button in the navigation bar*/

        function selectLoginOrCreateAccount() {
            document.getElementById("logInCreateAccount").classList.toggle("show");
        }

        // Close the dropdown if the user clicks outside of it
        window.onclick = function (e) {
            if (!e.target.matches('.dropbtn')) {
                var myDropdown = document.getElementById("logInCreateAccount");
                if (myDropdown.classList.contains('show')) {
                    myDropdown.classList.remove('show');
                }
            }
        }

    </script>
    </body>
</html> 
