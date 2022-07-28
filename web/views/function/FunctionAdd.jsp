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
                            <li class="breadcrumb-item active"><span>Function Details</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <form id="__form">
                        <div class="row">
                            <div class="col-xl-8">
                                <!-- Profile picture card-->
                                <div class="card mb-4">
                                    <div id="_header" class="card-header">Add Function</div>
                                    <div class="card-body">
                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputFunctionName">Function Name (*)</label>
                                                <input type="text" class="form-control" id="name" name="name" rows="3"></input>
                                                <div class="invalid-feedback">
                                                    Name is too long.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputAccessRoles">Access Roles</label>
                                                <input type="text" class="form-control" id="access" name="access" rows="3"></input>
                                                <div class="invalid-feedback">
                                                    Wrong format!
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputPriority">Priority (*)</label>
                                                <input type="number" class="form-control" id="inputPriority" name="priority" value="1"></input>
                                                <div class="invalid-feedback">
                                                    Priority must be greater than 0
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputComplexity">Complexity</label>
                                                <select class="form-select" id="inputComplexity" name="complexity">
                                                    <option value="1" selected>Simple</option>
                                                    <option value="2">Medium</option>
                                                    <option value="3">Complex</option>
                                                </select>

                                                <div class="invalid-feedback">
                                                    Please choose a complexity.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputStatus">Status</label>
                                                <select class="form-select" id="inputComplexity" name="status">
                                                    <c:forEach var="fs" items="${func_status}">
                                                        <option value="${fs.getClass_setting_id()}">${fs.getType_title()}</option>
                                                    </c:forEach>
                                                </select>

                                                <div class="invalid-feedback">
                                                    Please choose a status.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputDesc">Description (optional)</label>
                                                <textarea class="form-control" id="inputDesc" name="desc" rows="3"></textarea>

                                                <div class="invalid-feedback">
                                                    Max 100 chars
                                                </div>
                                            </div>
                                        </div>

                                        <hr>


                                        <!-- Save changes button-->
                                        <button class="btn btn-outline-info" type="reset">Reset</button>
                                        <button id="submit" class="btn btn-primary" type="button" onclick="save_change()">Save changes</button>

                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-4">
                                <div class="card mb-4">
                                    <div id="_header" class="card-header">Base Info</div>
                                    <div class="card-body">
                                        <div class="row gx-3 mb-3">
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputSubject">Subject</label>
                                                <select class="form-select" id="inputSubject" name="subject" onchange="OnChangeSubjectSelect()">

                                                </select>
                                                <div class="invalid-feedback">
                                                    Please choose a subject.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputClass">Class</label>
                                                <select class="form-select" id="inputClass" name="class" onchange="OnChangeClassSelect()">

                                                </select>
                                                <div class="invalid-feedback">
                                                    Please choose a class.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputTeam">Team</label>
                                                <select class="form-select" id="inputTeam" name="team" onchange="OnChangeTeamSelect()">

                                                </select>
                                                <div class="invalid-feedback">
                                                    Please choose a team.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputFeature">Feature</label>
                                                <select class="form-select" id="inputFeature" name="feature">

                                                </select>
                                                <div class="invalid-feedback">
                                                    Please choose a feature.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputOwner">Owner</label>
                                                <select class="form-select" id="inputOwner" name="owner">

                                                </select>
                                                <div class="invalid-feedback">
                                                    Please choose a owner of function.
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <script>


                var jsoFeature = ${features};
                build();
                setCurrent();

                function build() {
                    //
                    $("#inputClass").html("");
                    $("#inputTeam").html("");
                    $("#inputFeature").html("");
                    $("#inputOwner").html("");

                    var ids = [];
                    var text = "";
                    jsoFeature.forEach(e => {
                        if (!ids.includes(e.subject_id)) {
                            text += "<option value='" + e.subject_id + "'>" + e.subject_code + "</option>\n"
                            ids.push(e.subject_id);

                        }
                    })
                    $("#inputSubject").html(text);
                    OnChangeSubjectSelect();
                }
                function OnChangeSubjectSelect() {
                    $("#inputClass").html("");
                    $("#inputTeam").html("");
                    $("#inputFeature").html("");
                    $("#inputOwner").html("");
                    var subSelected = parseInt($("#inputSubject").val());
                    var ids = [];
                    var text = "";
                    jsoFeature.forEach(e => {
                        if (!ids.includes(e.class_id) && subSelected == e.subject_id) {
                            text += "<option value='" + e.class_id + "'>" + e.class_code + "</option>\n"
                            ids.push(e.class_id);

                        }
                    })
                    $("#inputClass").html(text);
                    OnChangeClassSelect();
                }
                function OnChangeClassSelect() {
                    $("#inputTeam").html("");
                    $("#inputFeature").html("");
                    $("#inputOwner").html("");
                    var subSelected = parseInt($("#inputClass").val());
                    var ids = [];
                    var text = "";
                    jsoFeature.forEach(e => {
                        if (!ids.includes(e.team_id) && subSelected == e.class_id) {
                            text += "<option value='" + e.team_id + "'>" + e.team_name + "</option>\n"
                            ids.push(e.team_id);

                        }
                    })
                    $("#inputTeam").html(text);
                    OnChangeTeamSelect()
                }
                function OnChangeTeamSelect() {
                    $("#inputFeature").html("");
                    getOwnerList();
                    var subSelected = parseInt($("#inputTeam").val());
                    var ids = [];
                    var text = "";
                    jsoFeature.forEach(e => {
                        if (!ids.includes(e.feature_id) && subSelected == e.team_id) {
                            text += "<option value='" + e.feature_id + "'>" + e.feature_name + "</option>\n"
                            ids.push(e.feature_id);

                        }
                    })
                    $("#inputFeature").html(text);
                }

                // onSubjectChange()

                function setCurrent() {
                    var url = new URL(window.location.href);
                    var subject = url.searchParams.get("subject");
                    var _class = url.searchParams.get("class");
                    var team = url.searchParams.get("team");
                    var _feature = url.searchParams.get("feature");
                    if (subject != null && subject != -1)
                        $("#inputSubject").val(subject).change();
                    if (_class != null && _class != -1)
                        $("#inputClass").val(_class).change();
                    if (team != null && team != -1)
                        $("#inputTeam").val(team).change();
                    if (_feature != null && _feature != -1)
                        $("#inputFeature").val(_feature).change();
                }

                function save_change() {

                    "name|access|priority|complexity|status|desc|subject|class|team|feature|owner".split("|").forEach(err => $("[name='" + err + "']").removeClass("is-invalid"));
                    var wrapper = $("#__form").closest("div");
                    $(wrapper).find('[role="alert"]').remove();
                    $.ajax({
                        url: 'function?go=add',
                        data: $("#__form").serialize(),
                        cache: false,
                        processData: false,
                        type: 'POST',
                        success: function (data) {
                            // update name
                            if (data.search("success") != -1) {
                                $(wrapper).append(
                                        '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> function added successfully</div>'
                                        );

                                setTimeout(() => {
                                    $(wrapper).find('[role="alert"]').remove();
                                }, 3000);
                            } else {
                                if (data.search("error") != -1)
                                    data.replace("error: ", "").split("|").forEach(err => $("[name='" + err + "']").addClass("is-invalid"));
                                $(wrapper).append(
                                        '<div class="snackbar show" role="alert"><i class="fa fa-times-circle text-danger"></i> There is an error while adding! Please try again later.</div>'
                                        );

                                setTimeout(() => {
                                    $(wrapper).find('[role="alert"]').remove();
                                }, 3000);
                            }
                        }
                    });
                }

                function getOwnerList() {
                    $("#inputOwner").html("");
                    //$("#inputFeature").prop("disabled", true);
                    $.ajax({
                        url: 'function',
                        data: "go=owner_list&class=" + $("#inputClass").val() + "&team=" + $("#inputTeam").val(),
                        cache: false,
                        processData: false,
                        type: 'POST',
                        success: function (data) {
                            // update name
                            if (data.search("success") != -1) {
                                text = "";
                                data.replace("success: ", "").split("|").forEach(dd => text += "<option value='" + dd.split("#")[0] + "'>" + dd.split("#")[1] + " - " + dd.split("#")[2] + "</option>\n")
                                $("#inputOwner").html(text);
                                $("#inputOwner").prop("disabled", false);
                            } else {
                                $("#inputOwner").prop("disabled", false);
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
