<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content=" tap4tap website">
    <meta name="author" content="Created by Carmen Albiter, Carolina Solar-Morales and Joy Hyunjung Oh">

    <title>MyAccount</title>

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
                                <span class="mr-2 d-none d-lg-inline text-gray-900">My Account</span>
                                <img class="img-profile rounded-circle" src="resources/img/undraw_profile.svg">

                            </a>

                            <!-- Dropdown - User Information -->

                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in bg-gradient-primary"
                                aria-labelledby="userDropdown">

                                <a class="dropdown-item  text-white" href="/tap4tap/servlet?cmd=logout"
                                    style="visibility: visible;">
                                    <i class="fa fa-sign-out mr-2 text-gray-100"></i>
                                    Logout
                                </a>
                                <a class="dropdown-item  text-white" href="/tap4tap/servlet?cmd=myAccount">
                                    <i class="fa fa-user mr-2 text-gray-100"></i>
                                    Manage Account
                                </a>
                                <div class="dropdown-divider"></div>

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
                                <div class="logo text-gray-400" data-text="Tap4Tap">My Account</div>
                                <br /><br />
                            </div>
                        </div>

                    </div>

                    <div class="row bg-gradient-warning">

                        <div class="col-lg">
                            <div class="container">
                                <br />


                                <div class="col-12">
                                    <!--TODO fix this part with java code-->
                                    <div class="card-body bg-gradient-light text-black-50">

                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                Display Name:
                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                <i class="fa fa-user fa-lg" aria-hidden="true"></i>
                                                ${owner.displayName}
                                            </div>
                                        </div>

                                        <br />

                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                Username:
                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                <i class="fa fa-address-card fa-lg" aria-hidden="true"></i>
                                                ${owner.username}
                                            </div>
                                        </div>

                                        <br />

                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                Password:
                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                <i class="fa fa-unlock-alt fa-lg" aria-hidden="true"></i>
                                                ******** <!--fix this part-->
                                            </div>
                                        </div>
                                        <br />

                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                <i class="fa fa-unlock fa-lg" aria-hidden="true"></i> Change Password:
                                            </div> <!--fix this part-->

                                        </div>
                                        <br />

                                    </div>
                                    <hr>

                                    <br />
                                </div>

                                <!--create table here-->
                                <div class="container ">

                                    <div class="card shadow mb-4">
                                        <div class="card-header py-3">
                                            <h3 class="m-0 font-weight-bold text-primary">My List</h3>
                                            <div class="text-white-50 medium text-left">${message}</div>
                                        </div>
                                        <div class="card-body bg-gradient-light text-black-50">
                                            <div class="table-responsive">
                                                <table class="table table-bordered bg-gradient-light text-black-50"
                                                    id="dataTable" width="100%" cellspacing="0">
                                                    <thead>
                                                        <tr>
                                                            <th>Brewery Name</th>

                                                        </tr>
                                                    </thead>
                                                    <tfoot>
                                                        <tr>
                                                            <th>Brewery Name</th>
                                                        </tr>
                                                    </tfoot>
                                                    <tbody>
                                                        <#if breweryList?has_content>
                                                            <#list breweryList as brewery>
                                                            <tr>
                                                                <td><i class="fa fa-beer fa-2x " aria-hidden="true"></i>
                                                                    ${brewery.name}</td>
                                                            </tr>
                                                            <tr>
                                                                <td><i class="fa fa-beer fa-2x" aria-hidden="true"></i>
                                                                    ${brewery.name}</td>
                                                            </tr>
                                                            </#list>
                                                        </#if>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <hr>

                                <!--add the log out button and fix this part here-->
                                <a href="/tap4tap/servlet?cmd=logout" class="btn btn-dark btn-user btn-block">
                                    <i class="fa fa-sign-out fa-lg"></i> Log Out
                                    <!--FIX THIS PART WITH JAVA CODE to make the search-->
                                </a>

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

    <!-- Core plugin JavaScript-->
    <script src="resources/js/jquery-easing.js"></script>

    <!-- Page level plugins -->
    <script src="resources/js/jquery.dataTables.js"></script>
    <script src="resources/js/dataTables.bootstrap.js"></script>

    <!-- Page level custom scripts -->
    <script src="resources/js/dataTables.demo.js"></script>


</body>

</html>