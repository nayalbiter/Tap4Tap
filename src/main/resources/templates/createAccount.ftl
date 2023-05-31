<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content=" tap4tap website">
    <meta name="author" content="Created by Carmen Albiter, Carolina Solar-Morales and Joy Hyunjung Oh">

    <title>Create an account</title>

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
    <link href="resources/css/tap4tap.css" rel="stylesheet">
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
                                <div class="logo text-gray-400" data-text="Tap4Tap">Welcome!</div>
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
                                    <img src="resources/img/13.jpg" alt="cheers" class="center">

                                </div>

                            </section>
                            <br />
                        </div>
                        <div class="col-lg">
                            <div class="container">
                                <div class="row">

                                    <div class="col-12">
                                        <br />
                                        <h1 class="logo-sm mb-1 text-gray-900 text-center"> Create an Account</h1>

                                    </div>

                                </div>
                                <div class="row bg-gradient-warning">

                                    <div class="col-12">
                                        <div class="text-center">
                                            <br />
                                        </div>

                                        <!--TO DO: FIX THIS PART WITH JAVA CODE-->
                                        <form method="post" class="user action=" /tap4tap/servlet?cmd=createAccount">
                                            <div class="form-group row">
                                                <div class="col-sm-6 mb-3 mb-sm-0">
                                                    <input name="firstName" type="text"
                                                        class="form-control form-control-user" id="exampleFirstName"
                                                        placeholder="First Name:">
                                                </div>
                                                <div class="col-sm-6">
                                                    <input name="lastName" type="text"
                                                        class="form-control form-control-user" id="exampleLastName"
                                                        placeholder="Last Name:">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input name="email" type="email" class="form-control form-control-user"
                                                    id="exampleInputEmail" placeholder="Email Address:">
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-sm-6 mb-3 mb-sm-0">
                                                    <input name="password" type="password"
                                                        class="form-control form-control-user" id="exampleInputPassword"
                                                        placeholder="Password:">
                                                </div>
                                                <div class="col-sm-6">
                                                    <input name="confirmPassword" type="password"
                                                        class="form-control form-control-user"
                                                        id="exampleRepeatPassword" placeholder="Confirm Password:">
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-primary btn-user btn-block">
                                                Create Account
                                            </button>

                                            <hr>

                                        </form>

                                        <hr>
                                        <div class="text-center">
                                            <a class="large" href="/tap4tap/servlet?cmd=showLogin">Already have an
                                                account?
                                                Login!</a>
                                        </div>

                                        <br />


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