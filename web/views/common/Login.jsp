<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="./">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta name="description" content="CoreUI - Open Source Bootstrap Admin Template">
        <meta name="author" content="Łukasz Holeczek">
        <meta name="keyword" content="Bootstrap,Admin,Template,Open,Source,jQuery,CSS,HTML,RWD,Dashboard">
        <title>Login - Student Project Management System</title>
        <link rel="apple-touch-icon" sizes="57x57" href="assets/favicon/apple-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="assets/favicon/apple-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="assets/favicon/apple-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="assets/favicon/apple-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="assets/favicon/apple-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="assets/favicon/apple-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="assets/favicon/apple-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="assets/favicon/apple-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-icon-180x180.png">
        <link rel="icon" type="image/png" sizes="192x192" href="assets/favicon/android-icon-192x192.png">
        <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="assets/favicon/favicon-96x96.png">
        <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
        <link rel="manifest" href="assets/favicon/manifest.json">
        <meta name="msapplication-TileColor" content="#ffffff">
        <meta name="msapplication-TileImage" content="assets/favicon/ms-icon-144x144.png">
        <meta name="theme-color" content="#ffffff">
        <!-- Vendors styles-->
        <link rel="stylesheet" href="vendors/simplebar/css/simplebar.css">
        <link rel="stylesheet" href="css/vendors/simplebar.css">
        <!-- Main styles for this application-->
        <link href="css/style.css" rel="stylesheet">
        <!-- We use those styles to show code examples, you should remove them in your application.-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/prismjs@1.23.0/themes/prism.css">
        <link href="css/examples.css" rel="stylesheet">
        <!-- Global site tag (gtag.js) - Google Analytics-->
        <script async="" src="https://www.googletagmanager.com/gtag/js?id=UA-118965717-3"></script>
        <script>
            window.dataLayer = window.dataLayer || [];

            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());
            // Shared ID
            gtag('config', 'UA-118965717-3');
            // Bootstrap ID
            gtag('config', 'UA-118965717-5');
        </script>
    </head>
    <body>
        <form action="login" method="post">
            <div class="bg-light min-vh-100 d-flex flex-row align-items-center">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <div class="card-group d-block d-md-flex row">
                                <div class="card col-md-7 p-4 mb-0">
                                    <div class="card-body">                                                  
                                        <h1>Login</h1>
                                        <p class="text-medium-emphasis">Sign In to your account</p>
                                        <c:if test="${not empty warning}">
                                            <p class="alert alert-danger"">${warning}</p>
                                        </c:if>
                                        <div class="input-group mb-3"><span class="input-group-text">
                                                <svg class="icon">
                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-user"></use>
                                                </svg></span>
                                            <input class="form-control" name="email" type="text" value="${email}" placeholder="Email" required="">
                                        </div>
                                        <div class="input-group mb-2"><span class="input-group-text">
                                                <svg class="icon">
                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-lock-locked"></use>
                                                </svg></span>
                                            <input class="form-control" name="password" id="showpass" type="password" placeholder="Password" required="">
                                        </div>
                                        <div class="row mt-3">                                            
                                            <div>
                                                <input type="checkbox" name="remember" onclick="myFunction()" value="check"> Show Password
                                            </div>
                                        </div>
                                        <div class="row mt-2">
                                            <div>
                                                <input type="checkbox" name="autologin" value="check"> Remember Login
                                            </div>
                                        </div>                                       
                                        <div class="row">
                                            <div class="col-6">
                                                <button class="btn btn-primary mt-3 px-4" type="submit" name="submit" value="login">Login</button>
                                            </div>
                                            <div class="col-6 text-end">
                                                <button class="btn btn-link mt-3 px-0" type="button"><a href="reset">Forgot password?</a></button>
                                            </div>
                                        </div>                                                                                                             
                                    </div>
                                </div>
                                <div class="card col-md-5 text-white bg-primary py-5">
                                    <div class="card-body text-center">
                                        <div>
                                            <h2>Sign up</h2>
                                            <p class="mb-4 mt-3">Register with your Edu Mail to access the FPT Education System for students to manage project.</p>
                                            <button class="btn btn-lg btn-outline-light mt-5" type="button"><a style="text-decoration: none; color: white" href="register">Register Now!</a></button>
                                        </div>
                                    </div>
                                </div>             
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <!-- CoreUI and necessary plugins-->
        <script src="vendors/@coreui/coreui/js/coreui.bundle.min.js"></script>
        <script src="vendors/simplebar/js/simplebar.min.js"></script>
        <script>
                                                    function myFunction() {
                                                        var x = document.getElementById("showpass");
                                                        if (x.type === "password") {
                                                            x.type = "text";
                                                        } else {
                                                            x.type = "password";
                                                        }
                                                    }
        </script>
    </body>
</html>
