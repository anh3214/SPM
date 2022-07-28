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
                            <li class="breadcrumb-item active"><span>Tracking Update List</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="row">
                        <div class="card mb-4">
                            <div class="card-header"><strong>Tracking Update Management</strong></div>
                            <div class="card-body">
                                <div class="table-responsive" id="__rxz">
                                    <h6>Assignee Name: ${assignee_name}</h6>
                                    <hr class="dropdown-divider">
                                    <div class="input-group">
                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Milestone</button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" onclick="urlSearch('milestone', -1)">All</a></li>
                                                <c:forEach items="${milestones}" var="item">
                                                <li>
                                                    <a class="dropdown-item" onclick="urlSearch('milestone', '${item.getMilestone_id()}')">
                                                        ${item.getMilestone_name()}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </ul>

                                        <input class="form-control" type="text" id="inputSearch" value="${search_value}" aria-label="Text input with segmented dropdown button">
                                        <button class="btn btn-outline-secondary" type="button" onclick="search()">Search</button>
                                    </div>
                                    <hr class="dropdown-divider">

                                    <c:if test="${sessionScope.role_id == 4}">
                                        <a class="btn btn-outline-info" type="button" onclick="add_click()">Add Tracking Update</a>
                                        <hr class="dropdown-divider">
                                    </c:if>

                                    <table class="table border mb-0 table-dark table-striped">
                                        <thead class="table-light fw-semibold">
                                            <tr>
                                                <th style="width: 15%">
                                                    Milestone Name
                                                    <svg class="icon" id="milestone_name" onclick="filter(this, '${filter_types[0]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th style="width: 15%">
                                                    Date
                                                    <svg class="icon" id="date" onclick="filter(this, '${filter_types[1]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th>
                                                    Note
                                                </th>
                                                
                                                <th style="width: 20%">Action</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${trackingUpdates}" var="item">
                                                <tr>
                                                    <td id="milestone_${item.getUpdate_id()}">${item.getMilestone_name()}</td>
                                                    <fmt:parseDate pattern="yyyy-MM-dd" value="${item.getDate()}" var="parse_date"/>
                                                    <fmt:formatDate pattern="dd-MM-yyyy" value="${parse_date}" var="formated_date"/>
                                                    <td>${formated_date}</td>

                                                    <td id="note_${item.getUpdate_id()}">
                                                        <c:if test="${item.getNote().length() > 20}">
                                                            ${item.getNote().substring(0, 20)}...
                                                        </c:if>
                                                        <c:if test="${item.getNote().length() <= 20}">
                                                            ${item.getNote()}
                                                        </c:if>
                                                    </td>
                                                    <td style="display: none">
                                                        <p id="${item.getUpdate_id()}">${item.getNote()}</p>
                                                    </td>
                                                    <td>
                                                        <a id="edit_button" class="btn btn-primary rounded-pill btn-sm" onclick="edit_click(this, '${item.getUpdate_id()}')" type="button">View</a>
                                                        <c:if test="${sessionScope.role_id == 4}">
                                                            <a id="edit_button" class="btn btn-danger rounded-pill btn-sm" onclick="delete_click('${item.getUpdate_id()}')" type="button">Delete</a>
                                                        </c:if>
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
                            <form id="update_tracking">
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" id="rxzDglClose">Close</button>
                            <c:if test="${sessionScope.role_id == 4}">
                                <button class="btn btn-primary" type="button" id="rxzDglConfirm">Update</button>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="addTrackingUpdate" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Add New Tracking Update</h5>
                            <button class="btn-close" type="button" data-coreui-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="add_tracking">
                                <input type="hidden" name="tracking_id" value="${tracking_id}">
                                <div class="row gx-3 mb-3">
                                    <div class="col-md-12">
                                        <label class="small mb-1">Milestone (*)</label>
                                        <select class="form-select form-select-sm" name="milestone_id" aria-label=".form-select-sm example">
                                            <c:forEach items="${milestones}" var="item">
                                                <option value="${item.getMilestone_id()}">${item.getMilestone_name()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="row gx-3 mb-3">
                                    <div class="col-md-12">
                                        <label class="small mb-1">Date (*)</label>
                                        <input id="datePicker" class="form-control" type="date" name="date" required="">
                                        <div class="invalid-feedback">
                                            Please give valid date.
                                        </div>
                                        <script>
                                            $(document).ready(function () {
                                                var date = new Date();
                                                var day = date.getDate();
                                                var month = date.getMonth() + 1;
                                                var year = date.getFullYear();
                                                if (month < 10)
                                                    month = "0" + month;
                                                if (day < 10)
                                                    day = "0" + day;
                                                var today = year + "-" + month + "-" + day;
                                                document.getElementById("datePicker").value = today;
                                            });
                                        </script>
                                    </div>
                                </div>

                                <div class="row gx-3 mb-3">
                                    <label class="small mb-1">Note (*)</label>
                                    <div class="col-md-12">
                                        <textarea class="form-control" name="note" rows="10" cols="100" required=""></textarea>
                                    </div>
                                    <div class="invalid-feedback">
                                        Please enter note.
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button">Cancel</button>
                            <button class="btn btn-primary" type="submit" onclick="do_add()">Add</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalTitle"></h5>
                            <button class="btn-close" type="button" data-coreui-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p id="deleteModalBody"></p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" id="deleteModalClose">No</button>
                            <button class="btn btn-primary" onclick="do_sync()" type="button" id="deleteModalConfirm">Yes</button>
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
                                function filter(item, type) {
                                    var url = new URL(window.location.href);
                                    url.searchParams.set("go", "view");
                                    var key = item.getAttribute('id');
                                    url.searchParams.set(key, type);
                                    var list_key = "milestone_name|date".split("|");
                                    for (var i = 0; i < list_key.length; i++) {
                                        if (list_key[i] != key) {
                                            url.searchParams.delete(list_key[i]);
                                        }
                                    }
                                    document.location.search = url.search;
                                }

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

                                function delete_click(id) {
                                    var milestone = document.getElementById("milestone_" + id).innerHTML;
                                    $("#deleteModalTitle").html("Delete Tracking Update On " + milestone);
                                    $("#deleteModalBody").html("Do you want to delete?");
                                    $("#deleteModalClose").click(function () {
                                        $("#deleteModal").modal("hide");
                                    })
                                    $("#deleteModalConfirm").off();
                                    $("#deleteModalConfirm").click(function () {
                                        do_delete(id);
                                    })
                                    $("#deleteModal").modal("show");
                                }

                                function do_delete(id) {
                                    var wrapper = $("#__rxz").closest("div");
                                    $(wrapper).find('[role="alert"]').remove();
                                    $("#deleteModal").modal("hide");
                                    $.ajax({
                                        url: 'trackingupdate',
                                        data: "go=delete&update_id=" + id,
                                        cache: false,
                                        processData: false,
                                        type: 'POST',
                                        success: function (data) {
                                            console.log(data);
                                            if (data == "success") {
                                                var url = new URL(window.location.href);
                                                document.location.search = url.search;
                                            } else {
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-times-circle text-danger"></i>There is an error while update! Please try again later.</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                }, 3000);
                                            }
                                        }
                                    });
                                }

                                function do_add() {
                                    var wrapper = $("#__rxz").closest("div");
                                    $(wrapper).find('[role="alert"]').remove();
                                    $("#addTrackingUpdate").modal("hide");
                                    $.ajax({
                                        url: 'trackingupdate',
                                        data: "go=add&" + $("#add_tracking").serialize(),
                                        cache: false,
                                        processData: false,
                                        type: 'POST',
                                        success: function (data) {
                                            console.log(data);
                                            if (data == "success") {
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i>Update successfully</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                    var url = new URL(window.location.href);
                                                    document.location.search = url.search;
                                                }, 3000);
                                            } else {
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-times-circle text-danger"></i>There is an error while update! Please try again later.</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                }, 3000);
                                            }
                                        }
                                    });
                                }

                                function do_update(id) {
                                    var wrapper = $("#__rxz").closest("div");
                                    $(wrapper).find('[role="alert"]').remove();
                                    $("#rxzDgl").modal("hide");
                                    $.ajax({
                                        url: 'trackingupdate',
                                        data: "go=update&" + $("#update_tracking").serialize() + "&update_id=" + id,
                                        cache: false,
                                        processData: false,
                                        type: 'POST',
                                        success: function (data) {
                                            if (data == "long") {
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-times-circle text-danger"></i>Note is too long!</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                }, 3000);
                                            } else if (data == "database") {
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-times-circle text-danger"></i>There is an error while update! Please try again later.</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                }, 3000);
                                            } else {
                                                if (data.length > 20) {
                                                    document.getElementById("note_" + id).innerHTML = data.substr(0, 20) + "...";
                                                } else {
                                                    document.getElementById("note_" + id).innerHTML = data;
                                                }
                                                document.getElementById(id).innerHTML = data;
                                                $(wrapper).append(
                                                        '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i>Update successfully</div>'
                                                        );
                                                setTimeout(() => {
                                                    $(wrapper).find('[role="alert"]').remove();
                                                }, 3000);
                                            }
                                        }
                                    });
                                }

                                function edit_click(item, id) {
                                    var note = document.getElementById(id).innerHTML;
                                    var milestone = document.getElementById("milestone_" + id).innerHTML;

                                    $("#rxzDglTitle").html("Edit Tracking Update On " + milestone);
                                    $("#update_tracking").find('[role="input_note"]').remove();
                                    $("#update_tracking").append('<textarea ${sessionScope.role_id != 4 ? "readonly" : ""} id="rxzDglBody" class="form-control" rows="10" cols="100" role="input_note" name="note">' + note.trim() + '</textarea>');

                                    $("#rxzDglClose").click(function () {
                                        $("#rxzDgl").modal("hide");
                                    })
                                    $("#rxzDglConfirm").off();
                                    $("#rxzDglConfirm").click(function () {
                                        do_update(id);
                                    })
                                    $("#rxzDgl").modal("show");
                                }

                                function add_click() {
                                    $("#addTrackingUpdate").modal("show")
                                }
        </script>
    </body>
</html>
