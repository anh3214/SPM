<%-- 
    Document   : Dashboard
    Created on : May 19, 2022, 4:22:11 PM
    Author     : RxZ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.User"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <base href="./">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta name="description" content="CoreUI - Open Source Bootstrap Admin Template">
        <meta name="author" content="Łukasz Holeczek">
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.2.5/js/fileinput.min.js"></script>
        <link href="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.2.5/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
        

    </head>

    <body>

        <style>
            .text-danger {
                color: #dc3545 !important;
            }
            .text-success {
                color: #28a745 !important;
            }

            .profile-pic-wrapper {

                width: 100%;
                position: relative;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }
            .pic-holder {
                text-align: center;
                position: relative;
                border-radius: 50%;
                width: 150px;
                height: 150px;
                overflow: hidden;
                display: flex;
                justify-content: center;
                align-items: center;
                margin-bottom: 20px;
            }

            .pic-holder .pic {
                height: 100%;
                width: 100%;
                -o-object-fit: cover;
                object-fit: cover;
                -o-object-position: center;
                object-position: center;
            }

            .pic-holder .upload-file-block,
            .pic-holder .upload-loader {
                position: absolute;
                top: 0;
                left: 0;
                height: 100%;
                width: 100%;
                background-color: rgba(90, 92, 105, 0.7);
                color: #f8f9fc;
                font-size: 12px;
                font-weight: 600;
                opacity: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                transition: all 0.2s;
            }

            .pic-holder .upload-file-block {
                cursor: pointer;
            }

            .pic-holder:hover .upload-file-block,
            .uploadProfileInput:focus ~ .upload-file-block {
                opacity: 1;
            }

            .pic-holder.uploadInProgress .upload-file-block {
                display: none;
            }

            .pic-holder.uploadInProgress .upload-loader {
                opacity: 1;
            }

            /* Snackbar css */
            .snackbar {
                visibility: hidden;
                min-width: 250px;
                background-color: #333;
                color: #fff;
                text-align: center;
                border-radius: 2px;
                padding: 16px;
                position: fixed;
                z-index: 1;
                left: 50%;
                bottom: 30px;
                font-size: 14px;
                transform: translateX(-50%);
            }

            .snackbar.show {
                visibility: visible;
                -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
                animation: fadein 0.5s, fadeout 0.5s 2.5s;
            }

            @-webkit-keyframes fadein {
                from {
                    bottom: 0;
                    opacity: 0;
                }
                to {
                    bottom: 30px;
                    opacity: 1;
                }
            }

            @keyframes fadein {
                from {
                    bottom: 0;
                    opacity: 0;
                }
                to {
                    bottom: 30px;
                    opacity: 1;
                }
            }

            @-webkit-keyframes fadeout {
                from {
                    bottom: 30px;
                    opacity: 1;
                }
                to {
                    bottom: 0;
                    opacity: 0;
                }
            }

            @keyframes fadeout {
                from {
                    bottom: 30px;
                    opacity: 1;
                }
                to {
                    bottom: 0;
                    opacity: 0;
                }
            }

        </style>

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
                            <li class="breadcrumb-item active"><span>Profile</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="row">
                        <div class="col-xl-4">
                            <!-- Profile picture card-->
                            <div class="card mb-4 mb-xl-0">
                                <div class="card-header">Profile Picture</div>
                                <div class="card-body text-center">
                                    <div class="profile-pic-wrapper">
                                        <div class="pic-holder">
                                            <!-- uploaded pic shown here -->
                                            <img id="profilePic" class="pic" src="services/image/avatar_${current_user.getUser_id()}.jpg">

                                            <Input class="uploadProfileInput" type="file" name="profile_pic" id="newProfilePhoto" accept="image/*" style="opacity: 0;" />
                                            <label for="newProfilePhoto" class="upload-file-block">
                                                <div class="text-center">
                                                    <div class="mb-2">
                                                        <i class="fa fa-camera fa-2x"></i>
                                                    </div>
                                                    <div class="text-uppercase">
                                                        Update <br /> Profile Photo
                                                    </div>
                                                </div>
                                            </label>
                                        </div>

                                        </hr>
                                        <p class="text-info text-center small">JPG or PNG no larger than 5 MB</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-8">
                            <!-- Account details card-->
                            <div class="card mb-4">
                                <div class="card-header">Account Details</div>
                                <div class="card-body">
                                    <form id="__form">

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (first name)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputFullName">Full Name</label>
                                                <input class="form-control" id="inputFullName" type="text" name="full_name" placeholder="Enter your full name" value="${current_user.getFull_name()}" required>
                                                <div class="invalid-feedback">
                                                    Please choose a unique and valid name.
                                                </div>
                                            </div>
                                            <!-- Form Group (last name)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputFbLink">Facebook</label>
                                                <input class="form-control" id="inputLastName" type="text" name="fb_link" placeholder="Enter your facebook link" value="${current_user.getFacebook_link()}">
                                                <div class="invalid-feedback">
                                                    Please give valid profile url.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputEmailAddress">Email address</label>
                                                <input class="form-control" id="inputEmailAddress" name="email" type="email" placeholder="Enter your email address" value="${current_user.getEmail()}">
                                                <div class="invalid-feedback">
                                                    Must be in form @fpt.edu.vn
                                                </div>
                                            </div>
                                            <!-- Form Group (birthday)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputPhone">Phone number</label>
                                                <input class="form-control" id="inputPhone" name="mobile" type="tel" placeholder="Enter your phone number" value="${current_user.getMobile()}">
                                                <div class="invalid-feedback">
                                                    Must be unique Viet Nam phone number
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Form Row-->
                                        <div class="row gx-3 mb-3">


                                            <!-- Form Group (birthday)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputBirthday">Birthday</label>
                                                <input class="form-control" id="inputBirthday" type="date" name="birthday" placeholder="Enter your birthday" value="${current_user.getDate_of_birth()}">
                                            </div>

                                            <!-- Form Group (gender)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputPhone">Gender</label>
                                                <div class="form-control">

                                                    <input class="form-check-input" id="gender1" type="radio" name="gender" value="1">
                                                    <label class="form-check-label" for="inlineRadio1">Male</label>
                                                    <input class="form-check-input" id="gender2" type="radio" name="gender" value="2">
                                                    <label class="form-check-label" for="inlineRadio2">Female</label>

                                                    <c:if test = '${current_user.getGender().equals("male")}'>
                                                        <script>
                                                            document.getElementById("gender1").checked = true;
                                                        </script>
                                                    </c:if>

                                                    <c:if test = '${current_user.getGender().equals("female")}'>
                                                        <script>
                                                            document.getElementById("gender2").checked = true;
                                                        </script>
                                                    </c:if>
                                                </div>

                                            </div>
                                            
                                            
                                        </div>
                                            <c:if test = "${sessionScope.role_id != 4}">
                                        <div class="col-md-12">
                                            <!-- Form Group (phone number)-->

                                                <label class="small mb-1" for="inputToken">User Token</label>
                                                <input class="form-control" id="inputToken" name="token" type="text" placeholder="Enter your user token" value="${current_user.getToken_user()}">
                                                <div class="invalid-feedback">
                                                    Must be valid
                                                </div>

                                           
                                        </div>
                                                <hr>
                                            </c:if>
                                        <!-- Save changes button-->
                                        <button id="submit" class="btn btn-primary" type="button" onclick="save_change()">Save changes</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <script>

                function save_change() {
                    "full_name|fb_link|email|mobile".split("|").forEach(err => $("[name='" + err + "']").removeClass("is-invalid"));
                    var wrapper = $("#__form").closest("div");
                    $(wrapper).find('[role="alert"]').remove();
                    $.ajax({
                        url: 'profile',
                        data: $("#__form").serialize(),
                        cache: false,
                        processData: false,
                        type: 'POST',
                        success: function (data) {
                            // update name
                            $("#userName").text($("[name='full_name']").val());
                            if (data == "success") {
                                $(wrapper).append(
                                        '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> Profile information updated successfully</div>'
                                        );

                                setTimeout(() => {
                                    $(wrapper).find('[role="alert"]').remove();
                                }, 3000);
                            } else {
                                if (data.search("error") != -1)
                                    data.replace("error: ", "").split("|").forEach(err => $("[name='" + err + "']").addClass("is-invalid"));
                                $(wrapper).append(
                                        '<div class="snackbar show" role="alert"><i class="fa fa-times-circle text-danger"></i> There is an error while updating! Please try again later.</div>'
                                        );

                                setTimeout(() => {
                                    $(wrapper).find('[role="alert"]').remove();
                                }, 3000);
                            }
                        }
                    });
                }

                $(document).on("change", ".uploadProfileInput", function () {
                    var triggerInput = this;
                    var currentImg = $(this).closest(".pic-holder").find(".pic").attr("src");
                    var holder = $(this).closest(".pic-holder");
                    var wrapper = $(this).closest(".profile-pic-wrapper");
                    $(wrapper).find('[role="alert"]').remove();
                    triggerInput.blur();
                    var files = !!this.files ? this.files : [];
                    if (!files.length || !window.FileReader) {
                        return;
                    }
                    if (/^image/.test(files[0].type)) {
                        // only image file
                        var reader = new FileReader(); // instance of the FileReader
                        reader.readAsDataURL(files[0]); // read the local file

                        reader.onloadend = function () {
                            $(holder).addClass("uploadInProgress");
                            $(holder).find(".pic").attr("src", this.result);
                            $(holder).append(
                                    '<div class="upload-loader"><div class="spinner-border text-primary" role="status"></div></div>'
                                    );

                            // Dummy timeout; call API or AJAX below
                            setTimeout(() => {
                                $(holder).removeClass("uploadInProgress");
                                $(holder).find(".upload-loader").remove();
                                dat = new FormData();
                                dat.append("file", $('#newProfilePhoto')[0].files[0]);
                                $.ajax({
                                    url: 'profile/avatar',
                                    data: dat,
                                    cache: false,
                                    contentType: false,
                                    processData: false,
                                    type: 'POST',
                                    success: function (data) {
                                        if (data != "error") {
                                            // Update Current profile
                                            $(".avatar-img").attr("src", data + "?t=" + new Date().getTime());

                                            $(wrapper).append(
                                                    '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> Profile image updated successfully</div>'
                                                    );

                                            // Clear input after upload
                                            $(triggerInput).val("");

                                            setTimeout(() => {
                                                $(wrapper).find('[role="alert"]').remove();
                                            }, 3000);
                                        } else {
                                            $(holder).find(".pic").attr("src", currentImg);
                                            $(wrapper).append(
                                                    '<div class="snackbar show" role="alert"><i class="fa fa-times-circle text-danger"></i> There is an error while uploading! Please try again later.</div>'
                                                    );

                                            // Clear input after upload
                                            $(triggerInput).val("");
                                            setTimeout(() => {
                                                $(wrapper).find('[role="alert"]').remove();
                                            }, 3000);
                                        }
                                    }
                                });
//                                
                            }, 1500);
                        };
                    } else {
                        $(wrapper).append(
                                '<div class="alert alert-danger d-inline-block p-2 small" role="alert">Please choose the valid image.</div>'
                                );
                        setTimeout(() => {
                            $(wrapper).find('role="alert"').remove();
                        }, 3000);
                    }
                });

            </script>
            <!-- Footer -->
            <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        </div>
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

    </body>

</html>
