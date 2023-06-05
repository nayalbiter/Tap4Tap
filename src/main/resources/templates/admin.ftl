<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content=" tap4tap website">
    <meta name="author" content="Created by Carmen Albiter, Carolina Solar-Morales and Joy Hyunjung Oh">

    <title>AdminPage</title>

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

    <!-- Bootstrap core JavaScript-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

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

                            <!-- Dropdown - Admin Information -->
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


                                <#else>

                                <a class="dropdown-item  text-white" href="/tap4tap/servlet?cmd=logout">
                                    <i class="fa fa-sign-out mr-2 text-gray-100"></i>
                                    Logout
                                </a>

                                <a class="dropdown-item  text-white" href="/tap4tap/servlet?cmd=myAccount">
                                    <i class="fa fa-user mr-2 text-gray-100"></i>
                                    Manage Account
                                </a>
                                <#if owner.admin>
                                    <a class="dropdown-item  text-white" href="/tap4tap/servlet?cmd=admin">
                                        <i class="fa fa-user mr-2 text-gray-100"></i>
                                        Admin Page
                                    </a>
                                </#if>
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
                                <br />

                                <!--button add a new brewery here-->

                                <hr>

                                <button type="button" class="btn btn-google btn-user btn-block" data-toggle="modal"
                                    data-target="#exampleModalAdd">
                                    <i class="fa fa-plus-square fa-lg" aria-hidden="true"></i> Add new brewery

                                </button>

                                <hr>
                                <#if message?has_content>
                                        <div class="text-red-50 medium text-left">${message}</div>
                                </#if>
                                <br />

                                <!--create table here-->
                                <div class="container ">

                                    <div class="card shadow mb-4">
                                        <div class="card-header py-3">
                                            <h3 class="m-0 font-weight-bold text-primary">Breweries:</h3>
                                        </div>
                                        <div class="card-body bg-gradient-light text-black-50">
                                            <div class="table-responsive">
                                                <table class="table table-bordered bg-gradient-light text-black-50"
                                                    id="dataTable" width="100%" cellspacing="0">
                                                    <thead>
                                                        <tr>
                                                            <th>Brewery Name</th>
                                                            <th>Location</th>
                                                            <th>Edit</th>
                                                            <th>Delete</th>
                                                        </tr>
                                                    </thead>
                                                    <tfoot>
                                                        <tr>
                                                            <th>Brewery Name</th>
                                                            <th>Location</th>
                                                            <th>Edit</th>
                                                            <th>Delete</th>
                                                        </tr>
                                                    </tfoot>
                                                    <tbody>
                                                        <#if breweryList?has_content>
                                                        <#list breweryList as UUID, brewery>
                                                            <tr>
                                                                <td>
                                                                    <i class="fa fa-beer fa-2x " aria-hidden="true"></i>
                                                                    ${brewery.name}
                                                                </td>

                                                                <td>
                                                                    <#if brewery.longitude !=0>
                                                                        <a
                                                                            href="https://www.google.com/maps?q=${brewery.latitude},${brewery.longitude}">
                                                                            <i class="fa fa-map fa-2x"
                                                                                aria-hidden="true"></i>
                                                                        </a>
                                                                    </#if>
                                                                    <#if brewery.address1?has_content>
                                                                        ${brewery.address1}
                                                                        <#else>
                                                                        Not available
                                                                        </#if>
                                                                </td>

                                                                <td>
                                                                    <form action="/tap4tap/servlet" method="get">
                                                                        <input type="hidden" name="cmd" value="admin" />
                                                                        <input type="hidden" name="breweryId" value="${brewery.breweryId}" />
                                                                        <input type="hidden" name="modal" value="edit" />
                                                                        <button class="btn btn-primary" data-toggle="modal" data-targer="#edit">
                                                                            <i class="fa fa-pencil fa-2x" aria-hidden="true"></i>
                                                                        </button>
                                                                    </form>

                                                                </td>

                                                                <td>
                                                                    <form action="/tap4tap/servlet" method="get">
                                                                        <input type="hidden" name="cmd" value="admin" />
                                                                        <input type="hidden" name="breweryId" value="${brewery.breweryId}" />
                                                                        <input type="hidden" name="modal" value="delete" />
                                                                        <button class="btn btn-primary" data-toggle="modal" data-target="#delete">                                                     <i class="fa fa-trash fa-2x"
                                                                                aria-hidden="true"></i>
                                                                        </button>
                                                                    </form>
                                                                </td>
                                                            </tr>
                                                        </#list>
                                                        </#if>

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

            <!-- Modal used to add a new a brewery -->
            <div class="modal fade" id="exampleModalAdd" tabindex="-1" role="dialog"
                aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Add a new brewery</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form id="addBreweryForm" name="insert" method="post" class="user" action="?cmd=admin">
                            <div class="modal-body">
                                <div class="text-left">* required field</div>

                                <div class="form-group">
                                    <i class="fa fa-beer fa-lg " aria-hidden="true"></i>
                                    <input name="breweryName" type="text" class="form-control form-control-user"
                                        id="breweryName" placeholder="*Brewery Name:">
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-glass fa-lg" aria-hidden="true"></i>

                                    <input name="breweryType" type="text" class="form-control form-control-user"
                                        id="breweryType" placeholder="*Brewery Type:">
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-location-arrow fa-lg" aria-hidden="true"></i>

                                    <input name="breweryAddress" type="text" class="form-control form-control-user"
                                        id="breweryAddress" placeholder="*Brewery Address:">
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-university fa-lg" aria-hidden="true"></i>

                                    <input name="breweryCity" type="text" class="form-control form-control-user"
                                        id="breweryCity" placeholder="*City:">
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-flag fa-lg" aria-hidden="true"></i>

                                    <input name="breweryState" type="text" class="form-control form-control-user"
                                        id="breweryState" placeholder="*State:">
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-map-marker fa-lg" aria-hidden="true"></i>

                                    <input name="breweryZipCode" type="text" class="form-control form-control-user"
                                        id="breweryZipCode" placeholder="*Zip Code:">
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-globe fa-lg" aria-hidden="true"></i>

                                    <input name="breweryCountry" type="text" class="form-control form-control-user"
                                        id="breweryCountry" placeholder="*Country:">
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-phone fa-lg" aria-hidden="true"></i>

                                    <input name="breweryPhone" type="text" class="form-control form-control-user"
                                        id="breweryPhone" placeholder="*Phone:">
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-internet-explorer fa-lg" aria-hidden="true"></i>

                                    <input name="breweryWebsite" type="text" class="form-control form-control-user"
                                        id="breweryWebsite" placeholder="*Website:">
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-thumb-tack" aria-hidden="true"></i>

                                    <input name="breweryLongitude" type="text" class="form-control form-control-user"
                                        id="breweryLongitude" placeholder="*Longitude:">
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-thumb-tack" aria-hidden="true"></i>

                                    <input name="breweryLatitude" type="text" class="form-control form-control-user"
                                        id="breweryLatitude" placeholder="*Latitude:">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Create new brewery</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- End of Modal to add a new brewery -->


            <!-- Modal used to edit a brewery -->
            <#if edit>
            <div class="modal fade" id="edit" tabindex="-1" role="dialog"
                aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Editing Brewery</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <form method="post" action="?cmd=admin&breweryToEditId=${chosenBrewery.breweryId}&action=edit">
                            <div class="modal-body">
                                <div class="form-group">
                                    <i class="fa fa-beer fa-lg " aria-hidden="true"></i>
                                    <#if chosenBrewery??>
                                    <#if chosenBrewery.name?has_content>
                                    <input name="breweryName" type="text" class="form-control form-control-user"
                                        id="breweryName" placeholder="Brewery Name: ${chosenBrewery.name}">
                                    <#else>
                                    <input name="breweryName" type="text" class="form-control form-control-user"
                                        id="breweryName" placeholder="Brewery Name: not available">
                                    </#if>
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-glass fa-lg" aria-hidden="true"></i>
                                    <#if chosenBrewery.breweryType?has_content>
                                    <input name="breweryType" type="text" class="form-control form-control-user"
                                        id="breweryType" placeholder="Brewery Type: ${chosenBrewery.breweryType}">
                                        <#else>
                                        <input name="breweryType" type="text" class="form-control form-control-user"
                                        id="breweryType" placeholder="Brewery Type: not available">
                                        </#if>
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-location-arrow fa-lg" aria-hidden="true"></i>
                                    <#if chosenBrewery.address1?has_content>
                                    <input name="breweryAddress" type="text" class="form-control form-control-user"
                                        id="breweryAddress" placeholder="Brewery Address: ${chosenBrewery.address1}">
                                        <#if chosenBrewery.address2?has_content>
                                        <input name="breweryAddress" type="text" class="form-control form-control-user"
                                        id="breweryAddress" placeholder="Brewery Address: ${chosenBrewery.address1} ${chosenBrewery.address2}">
                                            <#if chosenBrewery.address3?has_content>
                                            <input name="breweryAddress" type="text" class="form-control form-control-user"
                                        id="breweryAddress" placeholder="Brewery Address: ${chosenBrewery.address1} ${chosenBrewery.address2} ${chosenBrewery.address3}">
                                            </#if>
                                        </#if>
                                    <#else>
                                    <input name="breweryAddress" type="text" class="form-control form-control-user"
                                        id="breweryAddress" placeholder="Brewery Address: not available">
                                    </#if>
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-university fa-lg" aria-hidden="true"></i>
                                    <#if chosenBrewery.city?has_content>
                                    <input name="breweryCity" type="text" class="form-control form-control-user"
                                        id="breweryCity" placeholder="City: ${chosenBrewery.city}">
                                    <#else>
                                    <input name="breweryCity" type="text" class="form-control form-control-user"
                                        id="breweryCity" placeholder="City: not available">
                                    </#if>
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-flag fa-lg" aria-hidden="true"></i>
                                    <#if chosenBrewery.stateProvince?has_content>
                                    <input name="breweryState" type="text" class="form-control form-control-user"
                                        id="breweryState" placeholder="State: ${chosenBrewery.stateProvince}">
                                    <#else>
                                        <input name="breweryState" type="text" class="form-control form-control-user"
                                        id="breweryState" placeholder="State: not available">
                                    </#if>
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-map-marker fa-lg" aria-hidden="true"></i>
                                    <#if chosenBrewery.postalCode?has_content>
                                    <input name="breweryZipCode" type="text" class="form-control form-control-user"
                                        id="breweryZipCode" placeholder="Zip Code: ${chosenBrewery.postalCode}">
                                    <#else>
                                    <input name="breweryZipCode" type="text" class="form-control form-control-user"
                                        id="breweryZipCode" placeholder="Zip Code: not available">
                                    </#if>
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-globe fa-lg" aria-hidden="true"></i>

                                    <input name="breweryCountry" type="text" class="form-control form-control-user"
                                        id="breweryCountry" placeholder="Country: ${chosenBrewery.country}">
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-phone fa-lg" aria-hidden="true"></i>
                                    <#if chosenBrewery.phone?has_content>
                                    <input name="breweryPhone" type="text" class="form-control form-control-user"
                                        id="breweryPhone" placeholder="Phone: ${chosenBrewery.phone}">
                                    <#else>
                                    <input name="breweryPhone" type="text" class="form-control form-control-user"
                                        id="breweryPhone" placeholder="Phone: not available">
                                        </#if>
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-internet-explorer fa-lg" aria-hidden="true"></i>
                                    <#if chosenBrewery.wesiteUrl?has_content>
                                    <input name="breweryWebsite" type="text" class="form-control form-control-user"
                                        id="breweryWebsite" placeholder="Website: ${chosenBrewery.websiteUrl}">
                                    <#else>
                                        <input name="breweryWebsite" type="text" class="form-control form-control-user"
                                        id="breweryWebsite" placeholder="Website: not available">
                                    </#if>
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-internet-explorer fa-lg" aria-hidden="true"></i>
                                    <#if chosenBrewery.longitude?has_content>
                                    <input name="breweryLongitude" type="text" class="form-control form-control-user"
                                        id="breweryLongitude" placeholder="Longitude: ${chosenBrewery.longitude}">
                                    <#else>
                                        <input name="breweryLongitude" type="text" class="form-control form-control-user"
                                        id="breweryLongitude" placeholder="Longitude: not available">
                                    </#if>
                                </div>

                                <div class="form-group">
                                    <i class="fa fa-internet-explorer fa-lg" aria-hidden="true"></i>
                                    <#if chosenBrewery.latitude?has_content>
                                    <input name="breweryLatitude" type="text" class="form-control form-control-user"
                                        id="breweryWebsite" placeholder="Latitude: ${chosenBrewery.latitude}">
                                    <#else>
                                        <input name="breweryLatitude" type="text" class="form-control form-control-user"
                                        id="breweryLatitude" placeholder="Latitude: not available">
                                    </#if>
                                    </#if>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Save changes</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>

            <script>
                $("#edit").modal('show');
            </script>
            </#if>
            <!-- End of Modal to edit a brewery -->



            <!-- Modal used to delete a brewery -->
            <#if delete>
                <div class="modal fade" id="delete" tabindex="-1" role="dialog"
                    aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Warning!</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form method="post" action="?cmd=admin&breweryToDeleteId=${chosenBrewery.breweryId}&action=delete">
                                <div class="modal-body">
                                <p>Are you sure you want to delete this entry?</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                    <button type="sumbit" class="btn btn-primary">Delete Brewery</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <script>
                    $("#delete").modal('show');
                </script>
            </#if>
            <!-- End of Modal -->

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

    <!-- Core plugin JavaScript-->
    <script src="resources/js/jquery-easing.js"></script>

    <!-- Page level plugins -->
    <script src="resources/js/jquery.dataTables.js"></script>
    <script src="resources/js/dataTables.bootstrap.js"></script>

    <!-- Page level custom scripts -->
    <script src="resources/js/dataTables.demo.js"></script>


</body>

</html>