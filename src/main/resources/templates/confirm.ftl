<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content=" tap4tap website">
    <meta name="author" content="Created by Carmen Albiter, Carolina Solar-Morales and Joy Hyunjung Oh">

    <title>Log in</title>

    <!-- Custom fonts for this page-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

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

                </nav>
                <!-- End of Topbar -->


                <!-- Begin Page Content -->
                <div class="container-xl">
                    <div class="row">

                        <div class="col-12">
                            <!-- Logo Text -->
                            <div class="text-center">
                                <div class="logo text-gray-400" data-text="tap4tap"> Tap4Tap</div>
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
                                    <img src="resources/img/12.jpg" alt="people" class="center">

                                </div>

                            </section>
                            <br />
                        </div>


                        <div class="col-lg">
                            <div class="container">
                                <div class="row">

                                    <div class=" text-center col-12">
                                        <br />
                                        <h1 class="logo-sm mb-1 text-gray-900">
                                        <#if loggedIn?has_content>
                                            <#if loggedIn>
                                                Welcome Back, ${owner}!
                                            </#if>
                                        <#else> Welcome Back!
                                        </#if>
                                        </h1>

                                    </div>

                                </div>
                                <div class="row bg-gradient-warning">

                                    <div class="col-12">
                                        <div class="text-center">
                                            <br />
                                        </div>

                                        <#if !loggedIn>
                                            <form id="loginForm" class="user" action="?cmd=login" method="post">

                                                <div class="container">
                                                    <div class="row w-100">
                                                        <br /><br /><br />

                                                        <div class="row w-100">
                                                            <div class="col-4 mb-3 mb-sm-0">

                                                                <h4 class="name1 text-gray-800">Email:</h4>
                                                            </div>
                                                            <div class="col-8">
                                                                <input name="username"
                                                                    enctype="multipart/form-data"
                                                                    class="form-control form-control-user "
                                                                    type="email" id="inputEmail"
                                                                    placeholder="Enter Email Address..." size=60>
                                                                <br />
                                                            </div>
                                                        </div>
                                                        <div class="row w-100">
                                                            <div class="col-4 mb-3 mb-sm-0">
                                                                <h4 class="location1 text-left text-gray-800">
                                                                    Password:</h4>
                                                            </div>
                                                            <div class="col-8">
                                                                <input class="form-control form-control-user"
                                                                    enctype="multipart/form-data"
                                                                    name="password" type="password"
                                                                    id="inputPassword" size=60
                                                                    placeholder="Password">
                                                                <br />
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="custom-control custom-checkbox small">
                                                                <input type="checkbox" name="rememberme"
                                                                    class="custom-control-input" id="customCheck">
                                                                <label class="custom-control-label"
                                                                    for="customCheck">Remember
                                                                    Me</label>
                                                            </div>
                                                        </div>

                                                        <a id="loginSubmitButton" href="#"
                                                            class="btn btn-primary btn-user btn-block">
                                                            Login
                                                        </a>

                                                        <hr>
                                                    </div>

                                            </form>
                                        </#if>
                                        <br />
                                        <div class="card bg-danger text-white shadow text-center">
                                            <div class="card-body">
                                                <div class="text-white-50 medium text-center">${message}</div>
                                                <#--  <div class="text-white-50 medium text-left">If you've forgotten your password, click on the link below.</div>  -->
                                            </div>
                                        </div>
                                        <hr>
                                        <#if !loggedIn>
                                        <div class="text-center">
                                            <a class="large" href="/tap4tap/servlet?cmd=forgotPassword">Forgot Password?</a>
                                        </div>

                                        <br />
                                        <div class="text-center">
                                            <a class="large" href="/tap4tap/servlet?cmd=createAccount">Create an Account!</a>
                                        </div>
                                        </#if>

                                    </div>
                                </div>

                            </div>
                            <br />
                        </div>


                    </div>

                    <br />

                </div>
                <!-- End of Main Content -->
                <br />
                <!-- Footer -->
                <footer class="sticky-footer t4t-gradient-primary">

                    <div class="container my-auto">

                        <div class="copyright text-center my-auto text-white">
                            <span class="disclaimer">Disclaimer: This website is for informational purposes only and
                                does
                                not sell or otherwise
                                provide any age-restricted products. Tap4Tap urges you to follow your local laws
                                regarding
                                the consumption, sales, and possession of alcoholic beverages.</span>

                        </div>
                    </div>
                    <div class="text-white text-center">
                        <br />
                        <span class="authors1">Kirkland, WA © 2023 Tap4Tap created by Carmen Albiter, Carolina
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
        <script src="resources/js/login.js"></script>
</body>

</html>