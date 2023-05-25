<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content=" tap4tap website">
    <meta name="author" content="Created by Carmen Albiter, Carolina Solar-Morales and Joy Hyunjung Oh">

    <title>BrewerySearchResults</title>

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
    <link href="resources/css/dataTables.css" rel="stylesheet">

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
                                <span class="mr-2 d-none d-lg-inline text-gray-900">Login</span>
                                <img class="img-profile rounded-circle" src="resources/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in bg-gradient-primary"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item text-white" href="/tap4tap/servlet?cmd=showLogin">

                                    <i class="fa fa-sign-in mr-2 text-gray-100"></i>
                                    Login
                                </a>
                                <a class="dropdown-item  text-white" href="/tap4tap/servlet?cmd=showLogin"> <#--fix this
                                        to show page create account -->
                                        <i class="fa fa-user mr-2 text-gray-100"></i>
                                        Create Account
                                </a>
                                <div class="dropdown-divider"></div>

                                <!-- JOY:Check this please, this (log out) is going to be hidden until the user log in-->
                                <a class="dropdown-item  text-white" href="#" data-toggle="modal"
                                    data-target="#logoutModal" style="visibility: hidden;">
                                    <i class="fa fa-sign-out mr-2 text-gray-100"></i>
                                    Logout
                                </a>
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
                                        <h1 class="logo-sm mb-1 text-gray-900 text-center">Click the brewery name for
                                            more info...</h1>
                                    </div>

                                </div>


                                <!--create table here-->
                                <div class="container ">

                                    <div class="card shadow mb-4">
                                        <div class="card-header py-3">
                                            <h3 class="m-0 font-weight-bold text-primary">Brewery Search Results:</h3>
                                        </div>
                                        <div class="card-body bg-gradient-light text-black-50">
                                            <div class="table-responsive">
                                                <table class="table table-bordered bg-gradient-light text-black-50"
                                                    id="dataTable" width="100%" cellspacing="0">
                                                    <thead>
                                                        <tr>
                                                            <th>Brewery Name</th>
                                                            <th>Location</th>
                                                        </tr>
                                                    </thead>
                                                    <tfoot>
                                                        <tr>
                                                            <th>Brewery Name</th>
                                                            <th>Location</th>
                                                        </tr>
                                                    </tfoot>
                                                    <tbody>

                                                        <#list breweries as brewery>
                                                            <tr>
                                                                <td><i class="fa fa-beer fa-2x " aria-hidden="true"></i>
                                                                    ${brewery.name}</td>
                                                                <td>
                                                                    <a href="https://www.google.com/maps/@${brewery.longitude},${brewery.latitude}">
                                                                    <i class="fa fa-map fa-2x" aria-hidden="true"></i>
                                                                    </a>
                                                                    ${brewery.country}
                                                                </td>
                                                            </tr>
                                                        </#list>


                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>

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

    <!-- Core plugin JavaScript-->
    <script src="resources/js/jquery-easing.js"></script>

    <!-- Page level plugins -->
    <script src="resources/js/jquery.dataTables.js"></script>
    <script src="resources/js/dataTables.bootstrap.js"></script>

    <!-- Page level custom scripts -->
    <script src="resources/js/dataTables.demo.js"></script>


</body>

</html>