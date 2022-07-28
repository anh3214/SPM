<%-- 
    Document   : Dashboard
    Created on : May 19, 2022, 4:22:11 PM
    Author     : RxZ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.ClassUser"%>
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
                            <li class="breadcrumb-item active"><span>Add class user</span></li>
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
                                <div class="card-header">Avatar</div>
                                <div class="card-body text-center">
                                    <div class="profile-pic-wrapper">
                                        <div class="pic-holder">
                                            <!-- uploaded pic shown here -->
                                            <img id="profilePic" class="pic" src="services/image/default.jpg">

                                            <!--                                            <Input class="uploadProfileInput" type="file" name="profile_pic" id="newProfilePhoto" accept="image/*" style="opacity: 0;" />
                                                                                        <label for="newProfilePhoto" class="upload-file-block">
                                                                                            <div class="text-center">
                                                                                                <div class="mb-2">
                                                                                                    <i class="fa fa-camera fa-2x"></i>
                                                                                                </div>
                                                                                                <div class="text-uppercase">
                                                                                                    Update <br /> Profile Photo
                                                                                                </div>
                                                                                            </div>
                                                                                        </label>-->
                                        </div>

                                        </hr>
                                        <!--<p class="text-info text-center small">JPG or PNG no larger than 5 MB</p>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-8">
                            <!-- Account details card-->
                            <div class="card mb-4">
                                <div class="card-header">Class User Details - Class: ${dummy.getClass_code()}</div>

                                <div class="card-body">
                                    <form id="__form">

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (first name)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputUser">Email</label>
                                                <div class="input-group">
                                                    <input class="form-control" id="inputEmailFind" name="email_found" type="email" placeholder="Enter the student email" value="" required>
                                                    
                                                    <button class="btn btn-outline-secondary" id="button-addon2" type="button" onclick="findByEmail()">Find</button>
                                                </div>
                                            </div>
                                            <!-- Form Group (last name)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputEmailAddress">Info</label>
                                                <input class="form-control" id="inputInfo" type="text" placeholder="" value="" disabled>
                                                <input class="form-control" name="user_id" id="user_id" type="text" placeholder="" value="" hidden>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputClass">Class</label>
                                                <input class="form-control" id="inputClass" type="text" placeholder="Enter your email address" value="${dummy.getClass_id()} - ${dummy.getClass_code()}" disabled>
                                                <input class="form-control" id="inputClass" type="number" value="${dummy.getClass_id()}" hidden name="class_id">
                                            </div>
                                            <!-- Form Group (birthday)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputTeam">Team</label>
                                                <select class="form-select" aria-label="Default select example" name="team_id">
                                                    <c:forEach items="${team_dummies}" var="tdm">
                                                        <option value="${tdm.getTeam_id()}">${tdm.getTeam_id()} - ${tdm.getTopic_code()} - ${tdm.getTopic_name()}</option>
                                                    </c:forEach>

                                                </select>

                                            </div>
                                        </div>
                                        <div class="row gx-3 mb-3">
                                            <label class="small mb-1" for="inputBirthday">Actions</label>

                                            <div class="col-md-6">
                                                <div class="form-check form-switch">
                                                    <input class="form-check-input" id="flexSwitchCheckChecked" type="checkbox" checked name="status">
                                                    <label class="form-check-label" for="flexSwitchCheckChecked">Status</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">

                                                <!--<label class="small mb-1" for="inputBirthday">Team Leader</label>-->
                                                <div class="form-check form-switch">
                                                    <input class="form-check-input" id="flexSwitchCheckChecked" type="checkbox" name="is_leader">
                                                    <label class="form-check-label" for="flexSwitchCheckChecked">Is team leader</label>
                                                </div>

                                            </div>
                                        </div>

                                        <hr>

                                        <div class="row gx-3 mb-3">
                                            <label class="small mb-1" for="inputClass">Evaluations (>=0 and <=10)</label>
                                            <div class="col-md-4">
                                                <div class="input-group flex-nowrap"><span class="input-group-text" id="addon-wrapping">OnGoing</span>
                                                    <input class="form-control" type="number" placeholder="" aria-label="Username" aria-describedby="addon-wrapping" name="onging_eval" value="0">

                                                </div>
                                                <div class="invalid-feedback">
                                                    Number must be (0 <= number <= 10).
                                                </div>

                                            </div>
                                            <div class="col-md-4">
                                                <div class="input-group flex-nowrap"><span class="input-group-text" id="addon-wrapping">Final Present</span>
                                                    <input class="form-control" type="number" placeholder="" aria-label="Username" aria-describedby="addon-wrapping" name="final_pres_eval" value="0">
                                                    <div class="invalid-feedback">
                                                        Number must be (0 <= number <= 10).
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="col-md-4">
                                                <div class="input-group flex-nowrap"><span class="input-group-text" id="addon-wrapping">Final Topic</span>
                                                    <input class="form-control" type="number" placeholder="" aria-label="Username" aria-describedby="addon-wrapping" name="final_topic_eval" value="0">
                                                    <div class="invalid-feedback">
                                                        Number must be (0 <= number <= 10).
                                                    </div>
                                                </div>

                                            </div>
                                        </div>





                                        <!-- Form Row-->
                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (birthday)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputBirthday">Dropout Date</label>
                                                <input class="form-control" id="inputDropout" type="date" name="dropout" placeholder="Enter your birthday" value="0">
                                            </div>
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputUserNotes">User Notes</label>
                                                <input class="form-control" id="inputUserNotes" name="user_notes" type="text" placeholder="Enter your full name" value="no_notes" required>
                                                <div class="invalid-feedback">
                                                    Length of notes must be less than 100 chars.
                                                </div>
                                            </div>

                                        </div>


                                        <hr>


                                        <!-- Save changes button-->
                                        <button class="btn btn-outline-info" type="reset">Reset</button>
                                        <button id="submit" class="btn btn-primary" type="button" onclick="save_change()">Save changes</button>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>

            </div>
            <script>
                isOk = false
                function save_change() {
                    if ($("#user_id").val() == "") {  $("#inputEmailFind").addClass("is-invalid"); return}
                    "dropout|user_notes|onging_eval|final_pres_eval|final_topic_eval".split("|").forEach(err => $("[name='" + err + "']").removeClass("is-invalid"));
                    var wrapper = $("#__form").closest("div");
                    $(wrapper).find('[role="alert"]').remove();
                    $.ajax({
                        url: 'class_user?go=add',
                        data: $("#__form").serialize(),
                        cache: false,
                        processData: false,
                        type: 'POST',
                        success: function (data) {
                            // update name
                            if (data.search("success") != -1) {
                                $(wrapper).append(
                                        '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> information added successfully</div>'
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

                function back_to_list() {
                    var url = new URL(window.location.href);


                    url.searchParams.set("go", "view");
                    url.searchParams.set("team_id", "");
                    url.searchParams.set("user_id", "");

                    document.location.search = url.search;
                }
                
                function findByEmail() {
                    $("#inputInfo").val("");
                    $("#inputEmailFind").removeClass("is-valid");
                    $("#inputEmailFind").removeClass("is-invalid");
                    $.ajax({
                        url: 'class_user?go=find',
                        data: $("#__form").serialize(),
                        cache: false,
                        processData: false,
                        type: 'POST',
                        success: function (data) {
                            // update name
                            if (data.search("error") == -1) {
                                tt = data.split("#");
                                $("#inputInfo").val(tt[1]);
                                $("#user_id").val(tt[0]);
                                $("#inputEmailFind").addClass("is-valid");
                                isOk = true
                            } else {
                                $("#inputEmailFind").addClass("is-invalid");
                                isOk = false
                            }
                        }
                    });
                }



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
