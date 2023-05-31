<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content=" tap4tap website">
    <meta name="author" content="Created by Carmen Albiter, Carolina Solar-Morales and Joy Hyunjung Oh">

    <title>BreweryDetails</title>

    <!-- Custom fonts for this page-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
        type="text/css">

    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this page-->
    <link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="resources/css/breweryResults.css" rel="stylesheet">


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
                                    <#if loggedIn>Hello ${owner.displayName}!
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
                                <a class="dropdown-item  text-white" href="/tap4tap/servlet?cmd=logout">
                                    <i class="fa fa-sign-out mr-2 text-gray-100"></i>
                                    Logout
                                </a>
                                <a class="dropdown-item  text-white" href="/tap4tap/servlet?cmd=myAccount">
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
                            <div class="container">
                                <div class="row">

                                    <div class="col-12">
                                        <br />
                                        <h1 class="logo-sm mb-1 text-gray-900 text-center"> Find all the details about
                                            the brewery: ${brewery.name}</h1>
                                    </div>

                                </div>


                                <!--TO DO create the cards with the info here and fix it with the info of the database-->
                                <div class="container ">

                                    <div class="card shadow mb-4">
                                        <br />

                                        <div class="col-12">

                                            <div class="card-body bg-gradient-light text-black-50">

                                                <div class="col mr-2">
                                                    <div
                                                        class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Brewery type:
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <i class="fa fa-beer fa-lg" aria-hidden="true"></i>
                                                        ${brewery.breweryType}
                                                    </div>
                                                </div>


                                            </div>

                                            <br />

                                            <div class="card-body bg-gradient-light text-black-50">

                                                <div class="col mr-2">
                                                    <div
                                                        class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Brewery Address:
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                    <#if brewery.longitude !=0>
                                                        <a href="https://www.google.com/maps?q=#{brewery.latitude; M10},#{brewery.longitude; M10}">
                                                        <i class="fa fa-location-arrow fa-lg" aria-hidden="true"></i>
                                                        <#if brewery.address1?has_content>
                                                        ${brewery.address1}
                                                            <#if brewery.address2?has_content>
                                                            , ${brewery.address2}
                                                            </#if>
                                                        <#else>
                                                            Not available
                                                        </#if>
                                                        </a>
                                                    </#if>
                                                    </div>
                                                </div>


                                            </div>

                                            <br />

                                            <div class="card-body bg-gradient-light text-black-50">

                                                <div class="col mr-2">
                                                    <div
                                                        class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        City:
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <i class="fa fa-university fa-lg" aria-hidden="true"></i>
                                                        <#if brewery.city?has_content>
                                                            ${brewery.city}
                                                            <#else>
                                                            Not available
                                                            </#if>
                                                    </div>
                                                </div>


                                            </div>

                                            <br />

                                            <div class="card-body bg-gradient-light text-black-50">

                                                <div class="col mr-2">
                                                    <div
                                                        class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        State:
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <i class="fa fa-flag fa-lg" aria-hidden="true"></i>
                                                        <#if brewery.stateProvince?has_content>
                                                        ${brewery.stateProvince}
                                                        <#else>
                                                            Not available
                                                            </#if>
                                                    </div>
                                                </div>


                                            </div>

                                            <br />

                                            <div class="card-body bg-gradient-light text-black-50">

                                                <div class="col mr-2">
                                                    <div
                                                        class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Zip Code:
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <i class="fa fa-map-marker fa-lg" aria-hidden="true"></i>
                                                        ${brewery.postalCode}
                                                    </div>
                                                </div>


                                            </div>

                                            <br />

                                            <div class="card-body bg-gradient-light text-black-50">

                                                <div class="col mr-2">
                                                    <div
                                                        class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Country:
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <i class="fa fa-globe fa-lg" aria-hidden="true"></i>
                                                        ${brewery.country}
                                                    </div>
                                                </div>


                                            </div>

                                            <br />

                                            <div class="card-body bg-gradient-light text-black-50">

                                                <div class="col mr-2">
                                                    <div
                                                        class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Phone:
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <i class="fa fa-phone fa-lg" aria-hidden="true"></i>
                                                        <#if brewery.phone?has_content>
                                                        ${brewery.phone}
                                                        <#else>
                                                            Not available
                                                            </#if>
                                                    </div>
                                                </div>


                                            </div>

                                            <br />

                                            <div class="card-body bg-gradient-light text-black-50">

                                                <div class="col mr-2">
                                                    <div
                                                        class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Website:
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <i class="fa fa-internet-explorer fa-lg" aria-hidden="true"></i>
                                                        <#if brewery.websiteUrl?has_content>
                                                            <a href="${brewery.websiteUrl}">${brewery.websiteUrl}</a>
                                                        <#else>
                                                            Not available
                                                        </#if>
                                                    </div>
                                                </div>


                                            </div>

                                            <br />



                                        </div>

                                    </div>
                                </div>

                                <!--add the save it to my list buttom here and fix it with an on click-->

                                <hr> <!--TO DO fix this part-->
                                <#if loggedIn>
                                <a href="/tap4tap/servlet?cmd=myAccount&breweryId=${brewery.breweryId}&user=${owner.userId}" class="btn btn-google btn-user btn-block">
                                    <i class="fa fa-bookmark-o fa-lg" aria-hidden="true"></i> Save it to my list!
                                    <!--FIX THIS PART WITH JAVA CODE to save it into a list-->
                                </a>
                                <#else>
                                <a href="/tap4tap/servlet?cmd=showLogin" class="btn btn-google btn-user btn-block">
                                    <i class="fa fa-bookmark-o fa-lg" aria-hidden="true"></i> Save it to my list!
                                </a>
                                </#if>
                                <hr>

                                <br />

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
                    <span class="authors1">Kirkland, WA &copy 2023 Tap4Tap created by Carmen Albiter, Carolina
                        Solar-Morales
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

</body>

</html>