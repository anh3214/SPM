<%-- 
    Document   : Dashboard
    Created on : May 19, 2022, 4:22:11 PM
    Author     : RxZ
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

            .bgwarningtest {
                background-color: none;
            }


            .bgLabelsGreen{
                background-color: green;
            }
            .bgLabelsRed{
                background-color: red;
            }
            .bgLabelsBlue{
                background-color: blue;
            }
            .bgLabelsGray{
                background-color: gray;
            }
            .bgLabelsOrange{
                background-color: orange;
            }
            .bgLabelsPink{
                background-color: pink;
            }
            .bgLabelsPurple{
                background-color: purple;
            }
            .bgLabelsYellow{
                background-color: yellow;
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
                            <li class="breadcrumb-item active"><span>Issue List</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="row">
                        <div class="card mb-4">
                            <div class="row">
                                <div class="card-header"><strong>Issue List</strong><span class="small ms-1"></span></div>

                            </div>
                            <div class="card-body" id="__rxz">
                                <form method="GET" action="issue">

                                    <div class="table-responsive" id="notifi">
                                        <div class="input-group">
                                            <!--List Subject-->
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Assignee</button>
                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item" onclick="urlSearch('assignee', -1)">All</a></li>
                                                    <c:forEach items="${listAssignee}" var="item">
                                                    <li><a class="dropdown-item" onclick="urlSearch('assignee', '${item}')">${item}</a></li>
                                                    </c:forEach>
                                            </ul>


                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Function</button>
                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item" onclick="urlSearch('function', -1)">All</a></li>
                                                    <c:forEach items="${listFunction}" var="item">
                                                    <li><a class="dropdown-item" onclick="urlSearch('function', '${item}')">${item}</a></li>
                                                    </c:forEach>
                                            </ul>

                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Milestone</button>
                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item" onclick="urlSearch('milestone', -1)">All</a></li>
                                                    <c:forEach items="${listMilestone}" var="item">
                                                    <li><a class="dropdown-item" onclick="urlSearch('milestone', '${item}')">${item}</a></li>
                                                    </c:forEach>
                                            </ul>

                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Status</button>
                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item" onclick="urlSearch('status', -1)">All</a></li>
                                                <li><a class="dropdown-item" onclick="urlSearch('status', 1)">Active</a></li>
                                                <li><a class="dropdown-item" onclick="urlSearch('status', 0)">Inactive</a></li>
                                            </ul>
                                            <input class="form-control" type="text" id="inputSearch" value="${search_value}" placeholder="Search by title ..." aria-label="Text input with segmented dropdown button">
                                            <button class="btn btn-outline-secondary" type="button" onclick="search()">
                                                
                                                <svg class="icon">
                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-search"></use>
                                                </svg>
                                                Search
                                            </button>

                                        </div>
                                        <hr class="dropdown-divider">
                                        <div>
                                            <button  title="Assignee"  class="btn btn-outline-secondary" type="button"  id="full_name" onclick="filter(this, '${filter_types[0]}')">
                                                <svg class="icon">
                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                </svg>
                                                <svg class="icon">
                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-address-book"></use>
                                                </svg>
                                            </button>
                                            <button title="Created Date"  class="btn btn-outline-secondary" type="button" id="created_at"  onclick="filter(this, '${filter_types[1]}')">
                                                <svg class="icon">
                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                </svg>
                                                <svg class="icon">
                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-calendar"></use>
                                                </svg>
                                            </button>
                                            <button title="Milestone"  class="btn btn-outline-secondary" type="button" id="milestone_name" onclick="filter(this, '${filter_types[2]}')">
                                                <svg class="icon">
                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                </svg>
                                                <svg class="icon">
                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-3d"></use>
                                                </svg>
                                            </button>
                                            <button title="Sync Issue"  class="btn btn-outline-secondary" type="button" id="sync_button" onclick="sync_click()">
                                                <svg class="icon">
                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-sync"></use>
                                                </svg>
                                            </button>
                                            <div id="loading" class="spinner-border text-primary mr-2" role="status" style="display: none; float: right;">
                                                <span class="visually-hidden">Loading...</span>
                                            </div>
                                        </div>
                                        <hr class="dropdown-divider">


                                        <hr class="dropdown-divider">
                                        <div class="list-group">
                                            <c:forEach var="i" items="${issueList}">
                                                <div class="mb-3">
                                                    <a class="list-group-item list-group-item-action list-group-item-light">
                                                        <div class="d-flex w-100 justify-content-between">
                                                            <h6 class="mb-1">${i.getIssue_title()}</h6>
                                                            &emsp; 
                                                            <small>
                                                                <td class="text-center">
                                                                    <div class="form-check form-switch">
                                                                        <input <c:if test="${role_id == 3 || role_id == 2 || role_id == 1}"> disabled </c:if> class="form-check-input" name="status" id="sw__${i.getIssue_id()}" fe="${i.getIssue_id()}" type="checkbox" ${i.getStatus() == 1 ? "checked" : ""}>
                                                                        </div>
                                                                    </td>
                                                                </small>
                                                            </div>
                                                            <small class="mb-1" style="font-size: 12px; color: #00aced">Description</small>
                                                            <small>${i.getDesciption()}</small>
                                                        <br>
                                                        <small class="mb-1" style="font-size: 12px; color: #00aced">Function:</small>
                                                        <small>${i.getFunction_name()}</small>
                                                        <br>
                                                        <fmt:parseDate  pattern="yyyy-MM-dd" value="${i.getCreated_at()}" var="created_at"/>
                                                        <fmt:formatDate pattern="dd-MM-yyyy" value="${created_at}" var="format_created_at"/>
                                                        <fmt:parseDate  pattern="yyyy-MM-dd" value="${i.getDue_date()}" var="due_date"/>
                                                        <fmt:formatDate pattern="dd-MM-yyyy" value="${due_date}" var="format_due_date"/>
                                                        <small><i>#${i.getIssue_id()} . </i>Created at ${format_created_at} for assignee: ${i.getAssignee_name()}</small>
                                                        &emsp; 
                                                        <small title="Due Date" style="font-size: 15px; color: #666666">
                                                            <svg class="icon">
                                                            <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-calendar"></use>
                                                            </svg>
                                                            ${format_due_date}
                                                        </small>
                                                        &emsp;

                                                        <small title="${format_due_date}: Past Due" style="font-size: 15px; color: #065492">
                                                            <svg class="icon">
                                                            <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-3d"></use>
                                                            </svg>
                                                            ${i.getMilestone_name()}
                                                        </small>


                                                        &emsp;
                                                        <small>
                                                            <span style="background-color:${i.getColorOfLabel()} " class="badge me-1 rounded-pill">${i.getLabel_title()}</span>
                                                            <span style="background-color:${i.getColorOfStatus()} " class="badge me-1 rounded-pill">${i.getIssue_status_title()}</span>
                                                        </small>
                                                    </a>
                                                </div>

                                            </c:forEach>


                                        </div>
                                        <br></br>
                                        <nav aria-label="IssuePage pagination">
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
                                </form>

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
                            <button class="btn btn-primary" type="button" id="rxzDglConfirm">Yes</button>
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
                                                        url.searchParams.set("go", "viewAll");
                                                        url.searchParams.set(key, val);
                                                        var keys = "assignee|function|milestone|status".split("|");

                                                        for (var i = 0; i < keys.length; i++) {
                                                            if (!(keys[i] == key)) {
                                                                url.searchParams.delete(keys[i]);
                                                            }
                                                        }
                                                        document.location.search = url.search;
                                                    }
                                                    function filter(item, type) {
                                                        var url = new URL(window.location.href);
                                                        url.searchParams.set("go", "viewAll");
                                                        var key = item.getAttribute('id');
                                                        url.searchParams.set(key, type);
                                                        var list_key = "full_name|created_at|milestone_name".split("|");
                                                        for (var i = 0; i < list_key.length; i++) {
                                                            if (list_key[i] != key) {
                                                                url.searchParams.delete(list_key[i]);
                                                            }
                                                        }
                                                        document.location.search = url.search;
                                                    }

                                                    function search() {
                                                        var url = new URL(window.location.href);
                                                        var valSearch = encodeURI($("#inputSearch").val());
                                                        url.searchParams.set("go", "viewAll");
                                                        url.searchParams.set("description_search", valSearch);
                                                        document.location.search = url.search;
                                                    }

                                                    function updateStatus(issue_id, status) {
                                                        var wrapper = $("#notifi").closest("div");
                                                        $(wrapper).find('[role="alert"]').remove();
                                                        $("#rxzDgl").modal("hide");
                                                        $.ajax({
                                                            url: 'issue',
                                                            data: "go=updateStatus&issue_id=" + issue_id + "&status=" + status,
                                                            cache: false,
                                                            processData: false,
                                                            type: 'POST',
                                                            success: function (data) {
                                                                if (data == "success") {
                                                                    if (status == 1)
                                                                        $('#sw__' + issue_id)[0].checked = true;
                                                                    else
                                                                        $('#sw__' + issue_id)[0].checked = false;
                                                                    $(wrapper).append(
                                                                            '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> Updated successfully</div>'
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
                                                            }
                                                        });
                                                    }

                                                    $('input[name="status"]').change(function () {
                                                        var issue_id = this.id.replace("sw__", "");
                                                        $("#rxzDglClose").click(function () {
                                                            $("#rxzDgl").modal("hide");
                                                        })
                                                        $("#rxzDglConfirm").off();
                                                        if (this.checked) {
                                                            this.checked = false;
                                                            $("#rxzDglTitle").html("Open this issue?");
                                                            $("#rxzDglBody").html("Do you want to open this issue? ");
                                                            $("#rxzDglConfirm").click(function () {
                                                                updateStatus(issue_id, 1);
                                                            })


                                                        } else {
                                                            this.checked = true;
                                                            $("#rxzDglTitle").html("Close this feature?");
                                                            $("#rxzDglBody").html("Do you want to close feature?");
                                                            $("#rxzDglConfirm").click(function () {
                                                                updateStatus(issue_id, 0);
                                                            })
                                                        }
                                                        $("#rxzDgl").modal("show")
                                                    });

                                                    function do_sync() {
                                                        var wrapper = $("#__rxz").closest("div");
                                                        $(wrapper).find('[role="alert"]').remove();
                                                        $("#rxzDgl").modal("hide");
                                                        document.getElementById("sync_button").style.display = "none";
                                                        document.getElementById("loading").style.display = "inline";
                                                        $.ajax({
                                                            url: 'issue',
                                                            data: "go=sync_issue&team_id=${team}",
                                                            cache: false,
                                                            processData: false,
                                                            type: 'POST',
                                                            success: function (data) {
                                                                if (data === "success") {
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
                                                        $("#rxzDglTitle").html("Sync Issue of project - ${class.getClass_code()}");
                                                        $("#rxzDglBody").html("Do you want to sync?");

                                                        $("#rxzDglClose").click(function () {
                                                            $("#rxzDgl").modal("hide");
                                                        })

                                                        $("#rxzDglConfirm").off();
                                                        $("#rxzDglConfirm").click(function () {
                                                            do_sync();
                                                        })

                                                        $("#rxzDgl").modal("show");
                                                    }
        </script>
    </body>
</html>
