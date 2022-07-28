<%-- 
    Document   : Dashboard
    Created on : May 19, 2022, 4:22:11 PM
    Author     : RxZ
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.User"%>
<%@page import="java.text.SimpleDateFormat"%>  
<%@page import="java.util.Date"%>
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
                            <li class="breadcrumb-item active"><span>Milestone List</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="row">
                        <div class="card mb-4">
                            <div class="card-header"><strong>Milestone Management</strong></div>
                            <div class="card-body">
                                <div class="table-responsive" id="__rxz">
                                    <div class="input-group">

                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Class</button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" onclick="urlSearch('class_id', -1)">All</a></li>
                                            <c:forEach var="i" items="${sessionScope.classes}">
                                                <li><a class="dropdown-item" onclick="urlSearch('class_id', ${i.getClass_id()})">${i.getClass_code()}</a></li>
                                            </c:forEach>
                                        </ul>

                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Status</button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" onclick="urlSearch('status', -1)">All</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 1)">Open</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 0)">Closed</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 2)">Canceled</a></li>
                                        </ul>

                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Iteration</button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" onclick="urlSearch('iteration_id', -1)">All</a></li>
                                                <c:forEach var="item" items="${iterations}">
                                                <li><a class="dropdown-item" onclick="urlSearch('iteration_id', ${item.getIteration_id()})">${item.getIteration_name()}</a></li>
                                                </c:forEach>
                                        </ul>

                                        <input class="form-control" type="text" id="inputSearch" value="${search_value}" aria-label="Text input with segmented dropdown button">
                                        <button class="btn btn-outline-secondary" type="button" onclick="search()">Search</button>
                                    </div>
                                    <hr class="dropdown-divider">

                                    <c:if test="${sessionScope.role_id != 4}">
                                        <a class="btn btn-outline-info" type="button" href="milestone?go=add">Add Milestone</a>
                                        <!--<button id="sync_button" style="float: right" class="btn btn-outline-success" onclick="sync_click()" type="button">Sync With GitLab</button>-->

                                        <button title="Sync Milestone"  class="btn btn-outline-info" type="button" id="sync_button" onclick="sync_click()">
                                            <svg class="icon">
                                            <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-sync"></use>
                                            </svg>
                                        </button>

                                        <div id="loading" class="spinner-border text-primary mr-2" role="status" style="display: none; float: right;">
                                            <span class="visually-hidden">Loading...</span>
                                        </div>
                                        <hr class="dropdown-divider">
                                    </c:if>

                                    <table class="table border mb-0 table-dark table-striped">
                                        <thead class="table-light fw-semibold">
                                            <tr>
                                                <th>
                                                    Name
                                                    <svg class="icon" id="milestone_name" onclick="filter(this, '${filter_types[0]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th>
                                                    Iteration
                                                    <svg class="icon" id="iteration_name" onclick="filter(this, '${filter_types[1]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th style="display: none">
                                                    Class
                                                    <svg class="icon" id="class_code" onclick="filter(this, '${filter_types[2]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th>
                                                    From Date
                                                    <svg class="icon" id="from_date" onclick="filter(this, '${filter_types[3]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th>
                                                    To Date
                                                    <svg class="icon" id="to_date" onclick="filter(this, '${filter_types[4]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th style="display: none">
                                                    Subject
                                                    <svg class="icon" id="subject_name" onclick="filter(this, '${filter_types[5]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th style="display: none">
                                                    Trainer
                                                </th>
                                                <th>Status</th>
                                                <th class="text-center">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${milestones}" var="item">
                                                <tr>
                                                    <td>${item.getMilestone_name()}</td>
                                                    <td>
                                                        ${item.getIteration_name()}</div>
                                                    </td>
                                                    <td style="display: none">${item.getClass_code()}</td>

                                                    <fmt:parseDate pattern="yyyy-MM-dd" value="${item.getFrom_date()}" var="from_date"/>
                                                    <fmt:formatDate pattern="dd-MM-yyyy" value="${from_date}" var="formated_from_date"/>
                                                    <td>${formated_from_date}</td>

                                                    <fmt:parseDate pattern="yyyy-MM-dd" value="${item.getTo_date()}" var="to_date"/>
                                                    <fmt:formatDate pattern="dd-MM-yyyy" value="${to_date}" var="formated_to_date"/>
                                                    <td>
                                                        ${formated_to_date}
                                                    </td>
                                                    <td style="display: none">
                                                        ${item.getSubject_name()} - ${item.getSubject_code()}
                                                    </td>
                                                    <td style="display: none">
                                                        ${item.getTrainer_name()}
                                                    </td>
                                                    <td>
                                                        <c:if test="${item.getStatus() == 0}">
                                                            Closed
                                                        </c:if>
                                                        <c:if test="${item.getStatus() == 1}">
                                                            Open
                                                        </c:if>
                                                        <c:if test="${item.getStatus() == 2}">
                                                            Canceled
                                                        </c:if>
                                                    </td>
                                                    <td class="text-center">
                                                        <a href="milestone?go=edit&id=${item.getMilestone_id()}" class="btn btn-primary rounded-pill btn-sm" type="button">${sessionScope.role_id == 4 ? "View" : "Edit"}</a>
                                                    </td>
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
                            <h5 class="modal-title" id="rxzDglTitle"></h5>
                            <button class="btn-close" type="button" data-coreui-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p id="rxzDglBody"></p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" id="rxzDglClose">No</button>
                            <button class="btn btn-primary" onclick="do_sync()" type="button" id="rxzDglConfirm">Yes</button>
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
                                function urlSearch(key, val) {
                                    var url = new URL(window.location.href);
                                    url.searchParams.set("go", "view");
                                    url.searchParams.set(key, val);
                                    document.location.search = url.search;
                                }

                                function search() {
                                    var url = new URL(window.location.href);
                                    url.searchParams.set("go", "view");
                                    var valSearch = encodeURI($("#inputSearch").val());
                                    url.searchParams.set("search", valSearch);
                                    document.location.search = url.search;
                                }

                                function filter(item, type) {
                                    var url = new URL(window.location.href);
                                    url.searchParams.set("go", "view");
                                    var key = item.getAttribute('id');
                                    url.searchParams.set(key, type);
                                    var list_key = "milestone_name|iteration_name|class_code|from_date|to_date|subject_name".split("|");
                                    for (var i = 0; i < list_key.length; i++) {
                                        if (list_key[i] != key) {
                                            url.searchParams.delete(list_key[i]);
                                        }
                                    }
                                    document.location.search = url.search;
                                }

                                function do_sync() {
                                    var wrapper = $("#__rxz").closest("div");
                                    $(wrapper).find('[role="alert"]').remove();
                                    $("#rxzDgl").modal("hide");
                                    document.getElementById("sync_button").style.display = "none";
                                    document.getElementById("loading").style.display = "inline";
                                    $.ajax({
                                        url: 'milestone',
                                        data: "go=sync_milestone&class_id=${class.getClass_id()}",
                                        cache: false,
                                        processData: false,
                                        type: 'POST',
                                        success: function (data) {
                                            if (data == "success") {
                                                document.getElementById("sync_button").style.display = "inline";
                                                document.getElementById("loading").style.display = "none";
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> Sync Milestones successfully</div>'
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
                                    $("#rxzDglTitle").html("Sync Milestone of class - ${class.getClass_code()}");
                                    $("#rxzDglBody").html("Do you want to sync?");
                                    $("#rxzDgl").modal("show")
                                }
        </script>
    </body>
</html>
