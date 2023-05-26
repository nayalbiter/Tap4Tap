<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content=" tap4tap website">
    <meta name="author" content="Created by Carmen Albiter, Carolina Solar-Morales and Joy Hyunjung Oh">

    <title>Tap4Tap</title>

    <!-- Custom fonts for this page-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        type="text/css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
        type="text/css">

    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this page-->
    <link href="resources/css/sb-admin-2.min.css" rel="stylesheet" type="text/css">
    <link href="resources/css/tap4tap.css" rel="stylesheet" type="text/css">
</head>


<body id="page-top" class="bg-gradient-warning">
    <!-- Page Wrapper -->
    <div id="wrapper">
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content" class="bg-gradient-danger">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-gradient-warning topbar mb-4 static-top shadow">

                    <!-- Topbar Home -->

                    <div class="input-group">
                        <div class="input-group-appesnd">
                            <button class="btn btn-danger btn-lg alert-warning" type="button">
                                <a href="/tap4tap/servlet?cmd=home">Home</a>
                                <i class="fa fa-home fa-lg"></i>
                            </button>
                        </div>
                    </div>


                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-900">
                                    <#if loggedIn>Hello ${owner}!
                                    <#else>Login
                                    </#if>
                                </span>
                                <img class="img-profile rounded-circle" src="resources/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in bg-gradient-primary"
                                aria-labelledby="userDropdown">
                                <#if !loggedIn>
                                <a class="dropdown-item text-white" href="/tap4tap/servlet?cmd=showLogin">

                                    <i class="fa fa-sign-in mr-2 text-gray-100"></i>
                                    Login
                                </a>

                                <a class="dropdown-item  text-white" href="/tap4tap/servlet?cmd=createAccount">
                                    <i class="fa fa-user mr-2 text-gray-100"></i>
                                    Create Account
                                </a>
                                <div class="dropdown-divider"></div>

                                <#else>
                                <!---------add link to logout----------->
                                <a class="dropdown-item  text-white" href="/tap4tap/servlet?cmd=logout">
                                    <i class="fa fa-sign-out mr-2 text-gray-100"></i>
                                    Logout
                                </a>
                                 <!---------add link to manage account----------->
                                <a class="dropdown-item  text-white" href="#" data-toggle="modal"
                                    data-target="#logoutModal">
                                    <i class="fa fa-user mr-2 text-gray-100"></i>
                                    Manage Account
                                </a>
                                </#if>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->


                <!-- Begin Page Content -->
                <div class="container-xl">
                    <div class="row">

                        <div class="col-12">
                            <!-- Logo Text -->
                            <div class="text-center">
                                <div class="logo text-gray-400" data-text="Tap4Tap">Tap4Tap</div>
                                <br /><br />
                            </div>
                        </div>

                    </div>

                    <div class="row bg-gradient-warning">
                        <div class="col-lg">
                            <br />

                            <section class="Image">

                                <div class="imageContainer">

                                    <!--add the beer picture here  -->
                                    <img src="resources/img/18.jpg" alt="Beer" class="center">

                                </div>

                            </section>
                            <br />
                        </div>
                        <div class="col-lg">
                            <div class="container">
                                <div class="row">

                                    <div class="col-12">
                                        <br />
                                        <h1 class="logo-sm mb-1 text-gray-900">Find the best places here!</h1>

                                    </div>

                                </div>
                                <div class="row bg-gradient-warning">

                                    <div class="col-12">
                                        <div class="text-center">
                                            <br />
                                        </div>

                                        <!--FIX THIS PART WITH JAVA CODE-->
                                        <form id="brewerySearchForm" class="user" action="/tap4tap/servlet"
                                            method="get">
                                            <input type="hidden" id="cmd" name="cmd" value="searchResult" />
                                            <input type="hidden" id="hiddenCountry" name="country" value="United States" />
                                            <div class="container">
                                                <div class="row w-100">
                                                    <div class="col-6">
                                                        <h4 class="country1 text-gray-800">Select a country: </h4>
                                                    </div>
                                                    <div class="col-6">
                                                        <div class="input-group">
                                                            <input id="selectedCountry" type="text"
                                                                class="form-control form-control-user"
                                                                value="United States" />

                                                            <div class="input-group-append">
                                                                <button type="button"
                                                                    class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
                                                                    data-toggle="dropdown" aria-haspopup="true"
                                                                    aria-expanded="false">
                                                                    <span class="sr-only">Toggle Dropdown</span>
                                                                </button>
                                                                <div class="dropdown-menu bg-gradient-danger">

                                                                    <a class="countryOption dropdown-item text-white"
                                                                        href="#">Austria</a>
                                                                    <a class="countryOption dropdown-item text-white"
                                                                        href="#">England</a>
                                                                    <a class="countryOption dropdown-item text-white"
                                                                        href="#">France</a>
                                                                    <a class="countryOption dropdown-item text-white"
                                                                        href="#">Isle of Man</a>
                                                                    <a class="countryOption dropdown-item text-white"
                                                                        href="#">Ireland</a>
                                                                    <a class="countryOption dropdown-item text-white"
                                                                        href="#">Poland</a>
                                                                    <a class="countryOption dropdown-item text-white"
                                                                        href="#">Portugal</a>
                                                                    <a class="countryOption dropdown-item text-white"
                                                                        href="#">Scotland</a>
                                                                    <a class="countryOption dropdown-item text-white"
                                                                        href="#">South Korea</a>
                                                                    <a class="countryOption dropdown-item  text-white"
                                                                        href="#">United States</a>

                                                                    <div role="separator"
                                                                        class="dropdown-divider text-white"></div>

                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <br /><br /><br /><br /><br /><br />

                                                    <div class="row w-100">
                                                        <div class="col-4 mb-3 mb-sm-0">

                                                            <h4 class="name1 text-gray-800">Search by name:</h4>
                                                        </div>
                                                        <div class="col-8">
                                                            <input name="breweryName"
                                                                class="form-control form-control-user "
                                                                placeholder="Brewery Name">
                                                            <br />
                                                        </div>
                                                    </div>
                                                    <div class="row w-100">
                                                        <div class="col-4 mb-3 mb-sm-0">
                                                            <h4 class="location1 text-left text-gray-800">Search by
                                                                location:</h4>
                                                        </div>
                                                        <div class="col-8">
                                                            <input name="stateProvince"
                                                                class="form-control form-control-user"
                                                                id="stateProvince" placeholder="State/Province:">
                                                            <br />
                                                            <input name="city" class="form-control form-control-user"
                                                                id="city" placeholder="City:">
                                                            <br />
                                                            <input name="zipCode" class="form-control form-control-user"
                                                                id="zipCode" placeholder="Zip Code:">
                                                            <br />
                                                        </div>
                                                    </div>

                                                    <hr>
                                                    <a id="submitButton" href="#"
                                                        class="btn btn-google btn-user btn-block">
                                                        <i class="fa fa-search"></i> Let's go!
                                                        <!--FIX THIS PART WITH JAVA CODE to make the search-->
                                                    </a>
                                                </div>
                                        </form>
                                    </div>

                                </div>
                            </div>

                        </div>
                        <br />
                    </div>
                </div>

                <br />

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer t4t-gradient-primary">

                <div class="container my-auto">

                    <div class="copyright text-center my-auto text-white">
                        <span class="disclaimer">Disclaimer: This website is for informational purposes only and does
                            not sell or otherwise
                            provide any age-restricted products. Tap4Tap urges you to follow your local laws regarding
                            the consumption, sales, and possession of alcoholic beverages.</span>

                    </div>
                </div>
                <div class="text-white text-center">
                    <br />
                    <span class="authors1">Kirkland, WA © 2023 Tap4Tap created by Carmen Albiter, Carolina Solar-Morales
                        and Joy Hyunjung
                        Oh.</span>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="resources/js/select-country.js"></script>
</body>

</html>
