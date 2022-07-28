<%-- 
    Document   : change_password
    Created on : May 31, 2022, 12:33:56 AM
    Author     : Luu Dat
--%>
<!DOCTYPE html>
<head>

    <meta charset="UTF-8">

    <link rel="apple-touch-icon" type="image/png" href="https://cpwebassets.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png">
    <meta name="apple-mobile-web-app-title" content="CodePen">

    <link rel="shortcut icon" type="image/x-icon" href="https://cpwebassets.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico">

    <link rel="mask-icon" type="image/x-icon" href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111">


    <title> Change password form</title>
    <base href="./">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="description" content="CoreUI - Open Source Bootstrap Admin Template">
    <meta name="author" content="?ukasz Holeczek">
    <meta name="keyword" content="Bootstrap,Admin,Template,Open,Source,jQuery,CSS,HTML,RWD,Dashboard">
    <title>Project Manager</title>
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

    <link href="vendors/@coreui/chartjs/css/coreui-chartjs.css" rel="stylesheet">

    <style>
        .mainDiv {
            display: flex;
            min-height: 100%;
            align-items: center;
            justify-content: center;
            background-color: #f9f9f9;
            font-family: 'Open Sans', sans-serif;
        }
        .cardStyle {
            width: 500px;
            border-color: white;
            background: #fff;
            padding: 36px 0;
            border-radius: 4px;
            margin: 30px 0;
            box-shadow: 0px 0 2px 0 rgba(0,0,0,0.25);
        }
        #signupLogo {
            max-height: 200px;
            margin: auto;
            display: flex;
            flex-direction: column;
        }
        .formTitle{
            font-weight: 600;
            margin-top: 20px;
            color: #2F2D3B;
            text-align: center;
        }
        .inputLabel {
            font-size: 12px;
            color: #555;
            margin-bottom: 6px;
            margin-top: 24px;
        }
        .inputDiv {
            width: 70%;
            display: flex;
            flex-direction: column;
            margin: auto;
        }
        input {
            height: 40px;
            font-size: 16px;
            border-radius: 4px;
            border: none;
            border: solid 1px #ccc;
            padding: 0 11px;
        }
        input:disabled {
            cursor: not-allowed;
            border: solid 1px #eee;
        }
        .buttonWrapper {
            margin-top: 40px;
        }
        .submitButton {
            width: 70%;
            height: 40px;
            margin: auto;
            display: block;
            color: #fff;
            background-color: #065492;
            border-color: #065492;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.12);
            box-shadow: 0 2px 0 rgba(0, 0, 0, 0.035);
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
        }
        .submitButton:disabled,
        button[disabled] {
            border: 1px solid #cccccc;
            background-color: #cccccc;
            color: #666666;
        }

        #loader {
            position: absolute;
            z-index: 1;
            margin: -2px 0 0 10px;
            border: 4px solid #f3f3f3;
            border-radius: 50%;
            border-top: 4px solid #666666;
            width: 14px;
            height: 14px;
            -webkit-animation: spin 2s linear infinite;
            animation: spin 2s linear infinite;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>

    <script>
        window.console = window.console || function (t) {};
    </script>



    <script>
        if (document.location.search.match(/type=embed/gi)) {
            window.parent.postMessage("resize", "*");
        }
    </script>


</head>
<body translate="no">
    <div class="sidebar sidebar-dark sidebar-fixed" id="sidebar">
        <div class="sidebar-brand d-none d-md-flex">
            <svg class="sidebar-brand-full" width="118" height="46" alt="CoreUI Logo">
            <use xlink:href="assets/brand/coreui.svg#full"></use>
            </svg>
            <svg class="sidebar-brand-narrow" width="46" height="46" alt="CoreUI Logo">
            <use xlink:href="assets/brand/coreui.svg#signet"></use>
            </svg>
        </div>
        <!-- Sidebar -->
        <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>
        <button class="sidebar-toggler" type="button" data-coreui-toggle="unfoldable"></button>
    </div>

    <!-- Root -->
    <div class="wrapper d-flex flex-column min-vh-100 bg-light">
        <!-- Doan nay-->
        <header class="header header-sticky mb-4">
            <div class="container-fluid">
                <!-- NavBar -->
                <%@ include file="/WEB-INF/jspf/header.jspf" %>
                <!-- Profile -->
                <%@ include file="/WEB-INF/jspf/profile.jspf" %>
            </div>
            <div class="header-divider"></div>
            <div class="container-fluid">
                <!-- Breadcrum -->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb my-0 ms-2">
                        <li class="breadcrumb-item">
                            <!-- if breadcrumb is single--><span>Home</span>
                        </li>
                        <li class="breadcrumb-item active"><span>Dashboard</span></li>
                        <li class="breadcrumb-item active"><span>Settings</span></li>
                    </ol>
                </nav>
            </div>

        </header>


        <div class="body flex-grow-1 px-3">
            <div class="container-lg">
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <div class="card mb-4 mx-4">
                            <div class="card-body p-4">

                                <form action="change_password" method="post" name="signupForm" id="signupForm">
                                    <h1 style="text-align: center">

                                        <a href="dashboard" role="button"><img src="https://www.j2store.org/images/extensions/apps/apps_preview_image/change_password_preview.png" id="signupLogo"></a>
                                    </h1>

                                    <div class="inputDiv">
                                        <label class="inputLabel" for="password">Old Password</label>
                                        <input type="password" id="password" name="old_password" required="">
                                    </div>

                                    <div class="inputDiv">
                                        <label class="inputLabel" for="password">New Password</label>
                                        <input type="password" id="password" name="new_password_1" required="">
                                    </div>

                                    <div class="inputDiv">
                                        <label class="inputLabel" for="confirmPassword">Confirm Password</label>
                                        <input type="password" id="confirmPassword" name="new_password_2">
                                    </div>
                                    
                                    <div class="buttonWrapper">
                                        <button type="submit" id="submitButton" onclick="validateSignupForm()" class="submitButton pure-button pure-button-primary">
                                            <span>Done</span>
                                            <span id="loader" style="display: none;"></span>
                                        </button>
                                    </div>

                                </form>

                                <p class="thongbao1" style="color: red">${requestScope.notification1}</p>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </div>



    <script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>
    <!-- CoreUI and necessary plugins-->
    <script src="vendors/@coreui/coreui/js/coreui.bundle.min.js"></script>
    <script src="vendors/simplebar/js/simplebar.min.js"></script>
    <!-- Plugins and scripts required by this view-->
    <script src="vendors/chart.js/js/chart.min.js"></script>
    <script src="vendors/@coreui/chartjs/js/coreui-chartjs.js"></script>
    <script src="vendors/@coreui/utils/js/coreui-utils.js"></script>
    <script src="js/main.js"></script>
    <script>
    </script>


    <script id="rendered-js">
        var password = document.getElementById("new_password_1"),
                confirm_password = document.getElementById("new_password_2");

        document.getElementById('signupLogo').src = "https://www.j2store.org/images/extensions/apps/apps_preview_image/change_password_preview.png";
        enableSubmitButton();

        function validatePassword() {
            if (password.value != confirm_password.value) {
                confirm_password.setCustomValidity("Passwords Don't Match");
                return false;
            } else {
                confirm_password.setCustomValidity('');
                return true;
            }
        }

        password.onchange = validatePassword;
        confirm_password.onkeyup = validatePassword;

        function enableSubmitButton() {
            document.getElementById('submitButton').disabled = false;
            document.getElementById('loader').style.display = 'none';
        }

        function disableSubmitButton() {
            document.getElementById('submitButton').disabled = true;
            document.getElementById('loader').style.display = 'unset';
        }

        function validateSignupForm() {
            var form = document.getElementById('signupForm');

            for (var i = 0; i < form.elements.length; i++) {
                if (window.CP.shouldStopExecution(0))
                    break;
                if (form.elements[i].value === '' && form.elements[i].hasAttribute('required')) {
                    console.log('There are some required fields!');
                    return false;
                }
            }
            window.CP.exitedLoop(0);

            if (!validatePassword()) {
                return false;
            }

            onSignup();
        }

        function onSignup() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {

                disableSubmitButton();

                if (this.readyState == 4 && this.status == 200) {
                    enableSubmitButton();
                } else
                {
                    console.log('AJAX call failed!');
                    setTimeout(function () {
                        enableSubmitButton();
                    }, 1000);
                }

            };

            xhttp.open("GET", "ajax_info.txt", true);
            xhttp.send();
        }
//# sourceURL=pen.js
    </script>
</body>






