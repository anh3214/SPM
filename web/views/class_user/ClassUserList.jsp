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
                            <li class="breadcrumb-item active"><span>Class Users List</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="row">
                        <div class="card mb-4">
                            <div class="card-header"><strong>${cname}</strong><span class="small ms-1">Class Users List</span></div>
                            <div class="card-body">
                                <p class="text-medium-emphasis small">Class Users Management</p>
                                <div class="table-responsive" id="__rxz">
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
                                            <li><a id="tdm_all" class="dropdown-item" onclick="urlSearch('team_id', -1)">All</a></li>
                                                <c:forEach items="${team_dummies}" var="tdm">
                                                <li><a class="dropdown-item" id="tdm_${tdm.getTeam_id()}" onclick="urlSearch('team_id', ${tdm.getTeam_id()})">${tdm.getTeam_name()}</a></li>
                                                </c:forEach>

                                        </ul>
                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Status</button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" onclick="urlSearch('status', -1)" id="status_all">All</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 1)" id="status_act">Active</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 0)" id="status_inact">Inactive</a></li>
                                        </ul>
                                        <input class="form-control" type="text" id="inputSearch" aria-label="Text input with segmented dropdown button">
                                        <button class="btn btn-outline-secondary" type="button" onclick="search()">Search</button>
                                        <c:if test = "${sessionScope.role_id == 3}">
                                        <button class="btn btn-outline-primary" type="button" onclick="adv()">Simple</button>
                                        </c:if>

                                    </div>
                                    <c:if test = "${sessionScope.role_id == 3}">
                                    <hr class="dropdown-divider">
                                    <button class="btn btn-outline-primary" type="button" onclick="import_xlsx()">Import</button>
                                    <a class="btn btn-outline-primary" type="button" href="class_user?go=export&class_id=${cid}">Export</a>
                                    
                                    <button style="float: right" class="btn btn-outline-danger" type="button" onclick="del_sl()">Delete selected</button>
                                    
                                    <a class="btn btn-outline-info" type="button" href="class_user?go=add&class_id=${cid}">Add new user</a>
                                    </c:if>
                                    <hr class="dropdown-divider">
                                    <table class="table border mb-0 table-dark table-striped" id="table" data-toggle="table" data-id-field="id">
                                        <thead class="table-light fw-semibold">
                                            <tr>
                                                <c:if test = "${sessionScope.role_id == 3}">
                                                <th data-field="state" data-checkbox="true"></th>
                                                </c:if>
                                                <th>Class</th>
                                                <th data-sortable="true">Team</th>
                                                <th>Roll number</th>
                                                <th>Name</th>
                                                <th>Gender</th>
                                                <th data-sortable="true">DOB</th>
                                                <th>Email</th>
                                                <c:if test = "${sessionScope.role_id == 3}">
                                                <th>Eval</th>
                                                <th data-sortable="true">Status</th>
                                                <th></th></c:if>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${dummies}" var="dummy">
                                                <tr id="${dummy.getUserId()}-${dummy.getClassId()}-${dummy.getTeamId()}">
                                                    <c:if test = "${sessionScope.role_id == 3}">
                                                    <th data-field="checked" data-checkbox="true"></th>
                                                    </c:if>
                                                    <td>
                                                        <b>${dummy.getClassName()}</b>
                                                    </td>
                                                    <td>
                                                        <b>${dummy.getTeamName()}</b>
                                                    </td>
                                                    <td> <b>${dummy.getRollNumber()} </b></td>
                                                    <c:if test="${dummy.getTeamLeader() == 1}">
                                                        <td>${dummy.getFullName()} <span class="badge bg-secondary">v</span></td>
                                                    </c:if>
                                                    <c:if test="${dummy.getTeamLeader() != 1}">
                                                        <td>${dummy.getFullName()}</td>
                                                    </c:if>
                                                    <td>${dummy.getGender()}</td>
                                                    <td>${dummy.getDateOfBirth()}</td>
                                                    <td>${dummy.getEmail()}</td>
                                                    <c:if test = "${sessionScope.role_id == 3}">
                                                    <td><span class="badge me-1 rounded-pill bg-info">${dummy.getOnGoingEval()}/${dummy.getFinalPresEval()}/${dummy.getFinalTopicEval()}</span></td>
                                                    <td class="text-center">
                                                        <div class="form-check form-switch">
                                                            <input disabled class="form-check-input" name="sw" id="sw__${dummy.getUserId()}__${dummy.getTeamId()}__${dummy.getClassId()}" fe="${dummy.getEmail()}" type="checkbox" ${dummy.getStatus() == 1 ? "checked" : ""}>

                                                        </div>
                                                    </td>
                                                    <td>
                                                        <a href="class_user?go=edit&class_id=${dummy.getClassId()}&user_id=${dummy.getUserId()}&team_id=${dummy.getTeamId()}" class="btn btn-primary rounded-pill btn-sm" type="button">Edit</a>
                                                    </td>
                                                    </c:if>




                                                </tr>
                                            </c:forEach>


                                        </tbody>
                                    </table>
                                    <br></br>
                                    <nav aria-label="UserList pagination">

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
            <div class="modal fade" id="rxzDgl" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="rxzDglTitle">Import from excel file</h5>
                            <button class="btn-close" type="button" data-coreui-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p id="rxzDglBody">

                            </p>
                            <input id="input-id" type="file" class="file" data-show-preview="false" data-show-upload="false" data-allowed-file-extensions='["xlsx"]' accept=".xlsx">

                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary" type="button" id="rxzDglConfirm" onclick="import_process()">Import</button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="modal fade" id="rxzDglDel" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="rxzDglTitle">Delete all selected</h5>
                            <button class="btn-close" type="button" data-coreui-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p id="rxzDglBody">
                                Do you want to delete all users selected?
                            </p>
                            
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary" type="button" id="rxzDglConfirm1" onclick="delete_process()">Delete</button>
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

                                $(document).ready(function () {
                                    $("#input-id").fileinput();
                                    var url = new URL(window.location.href);
                                    if (url.searchParams.get("search") != null)
                                        $("#inputSearch").val(decodeURI(url.searchParams.get("search")));

                                    var status_call = url.searchParams.get("status");
                                    if (status_call == '1')
                                        $("#status_act").addClass("active");
                                    if (status_call == '0')
                                        $("#status_inact").addClass("active");
                                    if (status_call == '-1')
                                        $("#status_all").addClass("active");

                                    var team_call = url.searchParams.get("team_id");
                                    if (team_call != null && team_call != "" && team_call != -1)
                                        $("#tdm_" + team_call).addClass("active");
                                    if (team_call == -1)
                                        $("#tdm_all").addClass("active");
                                });

                                function import_xlsx() {
                                    $("#rxzDgl").modal("show");
                                }
                                
                                function del_sl() {
                                    $("#rxzDglDel").modal("show");
                                }

                                function import_process() {
                                    var wrapper = $("#__rxz").closest("div");
                                    $(wrapper).find('[role="alert"]').remove();
                                    dat = new FormData();
                                    dat.append("file", $('#input-id')[0].files[0]);
                                    $.ajax({
                                        url: 'class_user?go=import&cid=${cid}',
                                        data: dat,
                                        cache: false,
                                        contentType: false,
                                        processData: false,
                                        type: 'POST',
                                        success: function (data) {
                                            $("#rxzDgl").modal("hide");
                                            if (data.search("success") != -1) {
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> Import successfully ' + data + '</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                }, 3000);
                                            } else {
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-times-circle text-danger"></i> There is an error while updating! Please try again later.</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                }, 3000);
                                            }
                                            setTimeout(() => {
                                                location.reload();
                                            }, 1000);
                                        }
                                    })
                                }

                                function delete_process() {
                                    var wrapper = $("#__rxz").closest("div");
                                    $(wrapper).find('[role="alert"]').remove();
                                    dat = new FormData();
                                    dat.append("params", getSelections().join("|"));
                                    $.ajax({
                                        url: 'class_user?go=delete',
                                        data: dat,
                                        cache: false,
                                        contentType: false,
                                        processData: false,
                                        type: 'POST',
                                        success: function (data) {
                                            $("#rxzDglDel").modal("hide");
                                            if (data.search("success") != -1) {
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> Delete successfully ' + data + '</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                }, 3000);
                                            } else {
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-times-circle text-danger"></i> There is an error while deleting! Please try again later.</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                }, 3000);
                                            }
                                            setTimeout(() => {
                                                location.reload();
                                            }, 1000);
                                        }
                                    })
                                }

                                function getSelections() {
                                    return $.map($('#table').bootstrapTable('getSelections'), function (row) {
                                        return row._id
                                    })
                                }
                                function urlSearch(key, val) {
                                    var url = new URL(window.location.href);
                                    url.searchParams.set(key, val);
                                    document.location.search = url.search;
                                }

                                function search() {
                                    var url = new URL(window.location.href);
                                    var valSearch = encodeURI($("#inputSearch").val());

                                    url.searchParams.set("search", valSearch);

                                    document.location.search = url.search;
                                }

                                function adv() {
                                    var url = new URL(window.location.href);


                                    url.searchParams.set("go", "view");

                                    document.location.search = url.search;
                                }


        </script>

    </body>

</html>
