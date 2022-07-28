<%-- 
    Document   : Dashboard
    Created on : May 19, 2022, 4:22:11 PM
    Author     : RxZ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.ClassUser"%>
<%@page import="entity.Function"%>

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
        <title>Project Manager - Function List</title>
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

        <link href="https://unpkg.com/bootstrap-table@1.20.2/dist/bootstrap-table.min.css" rel="stylesheet">
        <script src="https://unpkg.com/bootstrap-table@1.20.2/dist/bootstrap-table.min.js"></script>
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
                            <li class="breadcrumb-item active"><span>Function List</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="row">
                        <div class="card mb-4">
                            <div class="card-header"><strong id="fText">Function List</strong></div>
                            <div class="card-body">

                                <div class="table-responsive" id="__rxz">
                                    <!--Filter-->
                                    <div class="input-group">

                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Class</button>
                                        <ul class="dropdown-menu">

                                                <c:forEach items="${classes}" var="cls">
                                                <li>
                                                    <a class="dropdown-item" id="cls_${cls.getClass_id()}" onclick="urlSearch('class', ${cls.getClass_id()})">${cls.getClass_code()}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>

                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Team</button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" onclick="urlSearch('team', -1)" id="te_all">All</a></li>
                                                <c:forEach items="${teams}" var="te">
                                                <li>
                                                    <a class="dropdown-item" id="te_${te.getTeam_id()}" onclick="urlSearch('team', ${te.getTeam_id()})">${te.getTeam_name()}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>

                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Feature</button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" onclick="urlSearch('feature', -1)" id="fe_all">All</a></li>
                                                <c:forEach items="${features}" var="fe">
                                                <li>
                                                    <a class="dropdown-item" id="fe_${fe.getFeature_id()}" onclick="urlSearch('feature', ${fe.getFeature_id()})">${fe.getFeature_name()}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>

                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Status</button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" onclick="urlSearch('status', -1)" id="fs_all">All</a></li>
                                                <c:forEach items="${func_status}" var="fs">
                                                <li>
                                                    <a class="dropdown-item" id="fs_${fs.getClass_setting_id()}" onclick="urlSearch('status', ${fs.getClass_setting_id()})">${fs.getType_title()}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                        <input class="form-control" type="text" id="inputSearch" aria-label="Text input with segmented dropdown button">
                                        <button class="btn btn-outline-secondary" type="button" onclick="search()"><svg class="icon">
                                            <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-search"></use>
                                            </svg>Search</button>

                                    </div>
                                    <hr class="dropdown-divider">
                                    <c:if test = "${sessionScope.role_id == 4 || sessionScope.role_id == 3}">
                                        <button class="btn btn-outline-primary" type="button" onclick="open_import()">Import</button>
                                        <button class="btn btn-outline-primary" type="button" onclick="open_export()">Export</button>

                                        <!--<button style="float: right" class="btn btn-outline-danger" type="button" onclick="del_sl()">Delete selected</button>-->

                                        <a class="btn btn-outline-info" type="button" onclick="urlCall('add')">Add new function</a>

                                        <button title="Sync Function"  class="btn btn-outline-info" type="button" id="sync_button" onclick="sync_click()">
                                            <svg class="icon">
                                            <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-sync"></use>
                                            </svg>
                                        </button>

                                        <div id="loading" class="spinner-border text-primary mr-2" role="status" style="display: none; float: right;">
                                            <span class="visually-hidden">Loading...</span>
                                        </div>
                                        <hr class="dropdown-divider">
                                    </c:if>

                                    <!--Table-->
                                    <table class="table border mb-0 table-dark table-striped" id="table" data-toggle="table" data-id-field="id">
                                        <thead class="table-light fw-semibold">
                                            <tr>
                                                <!--<th data-field="state" data-checkbox="true"></th>-->
                                                <!--<th data-sortable="true">ID</th>-->
                                                <th data-sortable="true">Name</th>
                                                
                                                <th data-sortable="true">Feature</th>
                                                <th data-sortable="true">Access Roles</th>
                                                <th data-sortable="true">Complexity</th>
                                                <th data-sortable="true">Priority</th>
                                                <th>Owner</th>
                                                <th data-sortable="true">Status</th>
                                                    <c:if test = "${sessionScope.role_id == 4 || sessionScope.role_id == 3}">
                                                    <th></th>
                                                    </c:if>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${dummies}" var="dummy">
                                                <tr id="${dummy.getId()}">
                                                    <!--<th data-field="checked" data-checkbox="true"></th>-->
                                                    
                                                    <td>
                                                        <strong><b>${dummy.getName()}</b></strong>
                                                    </td>
                                                    
                                                    <td>
                                                        ${dummy.getFeatureName()}
                                                    </td>
                                                    <td>
                                                        ${dummy.getAccessRoles()}
                                                    </td>
                                                    <td>
                                                        ${dummy.getComplexity()}
                                                    </td>
                                                    <td>
                                                        ${dummy.getPriority()}
                                                    </td>
                                                    <td>
                                                        ${dummy.getOwnerName()}
                                                    </td>
                                                    <td>
                                                        
                                                        <span style="background-color:${dummy.getStatusColor()}" class="badge me-1 rounded-pill">${dummy.getStatusName()}</span>
                                                    </td>
                                                    
                                                    <c:if test = "${sessionScope.role_id == 4 || sessionScope.role_id == 3}">
                                                        <td>
                                                            <a href="function?go=edit&id=${dummy.getId()}" class="btn btn-primary rounded-pill btn-sm" type="button">Edit</a>
                                                        </td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>

                                    <br></br>
                                    <nav aria-label="Func:List pagination">

                                        <ul class="pagination justify-content-end">
                                            <li class="page-item ${pageIndex > 1 ? "" : "disabled"}"><a class="page-link" onclick="urlSearch('page', 1)" tabindex="-1" aria-disabled="true">First</a></li>
                                                <c:forEach var = "i" begin = "0" end = "4" step = "1">
                                                    <c:set var = "pi" scope = "page" value = "${pageIndex-2+i}"/>
                                                    <c:if test="${pi >= 1 && pi <= totalSize}">
                                                    <li class="page-item ${pageIndex == pi ? "active" : ""}"><a class="page-link" onclick="urlSearch('page', ${pi})">${pi}</a></li>
                                                    </c:if>
                                                </c:forEach>
                                            <li class="page-item ${pageIndex >= totalSize ? "disabled" : ""}"><a class="page-link" onclick="urlSearch('page', ${totalSize == 0 ? 1 : totalSize})">Last</a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!-- Footer -->

            <%@ include file="/WEB-INF/jspf/footer.jspf" %>

            <!--Modal-->
            <div class="modal fade" id="rxzDgl" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="rxzDglTitle">Export to excel file</h5>
                            <button class="btn-close" type="button" data-coreui-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="__form_export" action="function" method="get">
                                <input type="text" class="form-control" id="id" name="go" rows="3" value="export" hidden></input>


                                <div class="row gx-3 mb-3">
                                    <div class="col-md-12">
                                        <label class="small mb-1" for="inputClass">Class</label>
                                        <select class="form-select form-select-sm" id="inputClass" name="class" onchange="OnChangeClassSelect()">

                                        </select>
                                        <div class="invalid-feedback">
                                            Please choose a class.
                                        </div>
                                    </div>
                                </div>

                                <div class="row gx-3 mb-3">
                                    <div class="col-md-12">
                                        <label class="small mb-1" for="inputTeam">Team</label>
                                        <select class="form-select form-select-sm" id="inputTeam" name="team" onchange="OnChangeTeamSelect()">

                                        </select>
                                        <div class="invalid-feedback">
                                            Please choose a team.
                                        </div>
                                    </div>
                                </div>

                                <div class="row gx-3 mb-3">
                                    <div class="col-md-12">
                                        <label class="small mb-1" for="inputFeature">Feature</label>
                                        <select class="form-select form-select-sm" id="inputFeature" name="feature">

                                        </select>
                                        <div class="invalid-feedback">
                                            Please choose a feature.
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-primary" type="submit" id="rxzDglConfirm" >Export</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
            <!--Import-->
            <div class="modal fade" id="rxzDglImport" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="rxzDglTitle">Import to excel file</h5>
                            <button class="btn-close" type="button" data-coreui-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="__form_import">
                                <div class="row gx-3 mb-3">
                                    <div class="col-md-12">
                                        <label class="small mb-1" for="inputFile">File (*)</label>
                                        <input id="input-id" type="file" class="file" data-show-preview="false" data-show-upload="false" data-allowed-file-extensions='["xlsx"]' accept=".xlsx">
                                        <div class="invalid-feedback">
                                            Please choose a feature.
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div id="result_status">
                                </div>


                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary" type="button" id="rxzDglConfirm" onclick="open_export()">> Template</button>
                            <button class="btn btn-primary" type="button" id="rxzDglConfirm" onclick="import_process()">Import</button>
                        </div>
                    </div>
                </div>
            </div>

            <!--Sync-->
            <div class="modal fade" id="SyncFuntion" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="SyncFuntionTitle"></h5>
                            <button class="btn-close" type="button" data-coreui-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p id="SyncFuntionBody"></p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" id="SyncFuntionClose">No</button>
                            <button class="btn btn-primary" type="button" id="SyncFuntionConfirm">Yes</button>
                        </div>
                    </div>
                </div>
            </div>
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
            <c:if test="${ sessionScope.role_id == 3 ||sessionScope.role_id == 4 }">
                                var jsoFeature = ${features_jso};
                                build();

                                function build() {
                                    //
                                    $("#inputClass").html("");
                                    $("#inputTeam").html("");
                                    $("#inputFeature").html("");
                                    $("#inputOwner").html("");

                                    var ids = [];
                                    var text = "";
//                                    jsoFeature.forEach(e => {
//                                        if (!ids.includes(e.subject_id)) {
//                                            text += "<option value='" + e.subject_id + "'>" + e.subject_code + "</option>\n"
//                                            ids.push(e.subject_id);
//                                            
//                                        }
//                                    })
//                                    $("#inputSubject").html(text);
//                                    OnChangeSubjectSelect();
                                    jsoFeature.forEach(e => {
                                        if (!ids.includes(e.class_id)) {
                                            text += "<option value='" + e.class_id + "'>" + e.class_code + "</option>\n"
                                            ids.push(e.class_id);

                                        }
                                    })
                                    $("#inputClass").html(text);
                                    OnChangeClassSelect();
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
                                    var subSelected = parseInt($("#inputTeam").val());
                                    var ids = [];
                                    var text = "<option value='-1'>All</option>\n";
                                    jsoFeature.forEach(e => {
                                        if (!ids.includes(e.feature_id) && subSelected == e.team_id) {
                                            text += "<option value='" + e.feature_id + "'>" + e.feature_name + "</option>\n"
                                            ids.push(e.feature_id);

                                        }
                                    })
                                    $("#inputFeature").html(text);
                                }
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

                                function do_sync() {
                                    var wrapper = $("#__rxz").closest("div");
                                    $(wrapper).find('[role="alert"]').remove();
                                    $("#SyncFuntion").modal("hide");
                                    document.getElementById("sync_button").style.display = "none";
                                    document.getElementById("loading").style.display = "inline";
                                    $.ajax({
                                        url: "function",
                                        data: "go=function_sync&team_id=${curTeam.getTeam_id()}",
                                        cache: false,
                                        processData: false,
                                        type: 'POST',
                                        success: function (data) {
                                            if (data == "success") {
                                                document.getElementById("sync_button").style.display = "inline";
                                                document.getElementById("loading").style.display = "none";
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> Sync Functions successfully</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                }, 3000);
                                            } else {
                                                document.getElementById("sync_button").style.display = "inline";
                                                document.getElementById("loading").style.display = "none";
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-times-circle text-danger"></i> There is an error while sync! Please try again later.</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                }, 3000);
                                            }
                                        }
                                    });
                                }

                                function sync_click() {
                                    $("#SyncFuntionTitle").html("Sync Function Of Team: ${curTeam.getTeam_name()}");
                                    $("#SyncFuntionBody").html("Do you want to sync?");
                                    $("#SyncFuntionClose").click(function () {
                                        $("#SyncFuntion").modal("hide");
                                    })
                                    $("#SyncFuntionConfirm").off();
                                    $("#SyncFuntionConfirm").click(function () {
                                        do_sync();
                                    })
                                    $("#SyncFuntion").modal("show")
                                }
            </c:if>

                                $(document).ready(function () {
                                    $("#input-id").fileinput();

                                    // bind current value from url to filter
                                    var url = new URL(window.location.href);

                                    if (url.searchParams.get("search") != null)
                                        $("#inputSearch").val(decodeURI(url.searchParams.get("search")));

                                    clsv = url.searchParams.get("class")
                                    if (clsv != null && clsv != -1 && clsv != ""){
                                        $("#cls_" + clsv).addClass("active");
                                        $("#fText").text($("#fText").text() + " - " + $("#cls_" + clsv).text() );
                                    }
                                        
                                    else
                                        $("#cls_all").addClass("active");

                                    clsv = url.searchParams.get("subject")
                                    if (clsv != null && clsv != -1 && clsv != "")
                                        $("#sjs_" + clsv).addClass("active");
                                    else
                                        $("#sjs_all").addClass("active");

                                    clsv = url.searchParams.get("team")
                                    if (clsv != null && clsv != -1 && clsv != ""){
                                        
                                        $("#te_" + clsv).addClass("active");
                                         $("#fText").text($("#fText").text() + " - " + $("#te_" + clsv).text());
                                    }
                                    else
                                        $("#te_all").addClass("active");

                                    clsv = url.searchParams.get("feature")
                                    if (clsv != null && clsv != -1 && clsv != "")
                                        $("#fe_" + clsv).addClass("active");
                                    else
                                        $("#fe_all").addClass("active");

                                    var fs_call = url.searchParams.get("status");
                                    if (fs_call != null && fs_call != "" && fs_call != -1)
                                        $("#fs_" + fs_call).addClass("active");
                                    else
                                        $("#fs_all").addClass("active");

                                });

                                function open_export() {
                                    setCurrent();
                                    $("#rxzDglImport").modal("hide");
                                    $("#rxzDgl").modal("show");
                                    
                                }

                                function open_import() {
                                    $("#result_status").html("");
                                    $("#rxzDgl").modal("hide");
                                    $("#rxzDglImport").modal("show");
                                }


                                function getSelections() {
                                    return $.map($('#table').bootstrapTable('getSelections'), function (row) {
                                        return row._id
                                    })
                                }
                                function urlSearch(key, val) {
                                    var url = new URL(window.location.href);
                                    url.searchParams.set(key, val);
                                    if (key != "page")
                                        url.searchParams.set("page", 1);
                                    document.location.search = url.search;
                                }

                                function search() {
                                    var url = new URL(window.location.href);
                                    var valSearch = encodeURI($("#inputSearch").val());

                                    url.searchParams.set("search", valSearch);

                                    document.location.search = url.search;
                                }
                                
                                function urlCall(val) {
                                    var url = new URL(window.location.href);

                                    url.searchParams.set("go", val);

                                    document.location.search = url.search;
                                }

                                function import_process() {
                                    $("#rxzDglConfirm").prop("disabled", true);
                                    var wrapper = $("#__rxz").closest("div");
                                    $(wrapper).find('[role="alert"]').remove();
                                    dat = new FormData();
                                    dat.append("file", $('#input-id')[0].files[0]);
                                    $("#result_status").html(`<div class="spinner-grow spinner-grow-sm" role="status"></div><strong>   Processing...</strong>`)
                                    $.ajax({
                                        url: 'function?go=import',
                                        data: dat,
                                        cache: false,
                                        contentType: false,
                                        processData: false,
                                        type: 'POST',
                                        success: function (data) {
                                            if (data.search("success") != -1) {
                                                setTimeout(() => {
                                                    $("#result_status").html(`<i class="fa fa-check-circle text-success"></i> <strong>Success: ` + data.replace("success: ", "").split("|")[0] + `!</strong>`)
                                                }, 1000);
                                            } else {

                                                setTimeout(() => {
                                                    $("#result_status").html(`<i class="fa fa-times-circle text-danger"></i> <strong>Error: ` + data.replace("error: ", "").split("|")[0] + `!</strong>`)
                                                }, 1000);
                                            }
                                            $("#rxzDglConfirm").prop("disabled", false);
//                                            setTimeout(() => {
//                                                location.reload();
//                                            }, 1000);
                                        }
                                    })
                                }
        </script>

    </body>

</html>
