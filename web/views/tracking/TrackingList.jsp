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
                            <li class="breadcrumb-item active"><span>Tracking List</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="row">
                        <div class="card mb-4">
                            <div class="card-header"><strong>Tracking Management</strong></div>

                            <div class="card-body">
                                <div class="table-responsive" id="__rxz">

                                    <div class="input-group">
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
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 1)">Planned</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 2)">Analysed</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 3)">Designed</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 4)">Coded</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 5)">Integrated</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 6)">Submitted</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 7)">Evaluated</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 8)">Rejected</a></li>
                                        </ul>

                                        <input class="form-control" type="text" id="inputSearch" value="${search_value}" placeholder="Search by name ..." aria-label="Text input with segmented dropdown button">
                                        <button class="btn btn-outline-secondary" type="button" onclick="search()">
                                            <svg class="icon">
                                            <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-search"></use>
                                            </svg>
                                        </button>

                                    </div>

                                    <hr class="dropdown-divider">

                                    <c:if test="${sessionScope.role_id == 4}">
                                        <a class="btn btn-outline-info" type="button" href="tracking?go=add&team_id=${team_id}">Add Tracking</a>
                                        <!--<button id="sync_button" style="float: right" class="btn btn-outline-success" onclick="sync_click()" type="button">Sync With GitLab</button>-->

                                    </c:if>

                                    <table class="table border mb-0 table-dark table-striped">
                                        <thead class="table-light fw-semibold">
                                            <tr>
                                                <th>
                                                    ID
                                                    <svg class="icon" id="tracking_id" onclick="filter(this, '${filter_types[0]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th>
                                                    Function
                                                    <svg class="icon" id="function_name" onclick="filter(this, '${filter_types[1]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th>
                                                    Milestone
                                                    <svg class="icon" id="milestone_name" onclick="filter(this, '${filter_types[2]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th>
                                                    Assignee
                                                    <svg class="icon" id="assignee_name" onclick="filter(this, '${filter_types[3]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>

                                                <th>
                                                    Role
                                                    <svg class="icon" id="role" onclick="filter(this, '${filter_types[5]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th>
                                                    Notes
                                                </th>
                                                <th>
                                                    Status
                                                    <svg class="icon" id="status" onclick="filter(this, '${filter_types[6]}')">
                                                    <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                    </svg>
                                                </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${trackingList}" var="item">
                                                <tr>
                                                    <td>${item.getTracking_id()}</td>
                                                    <td>
                                                        ${item.getFunction_name()}</div>
                                                    </td>


                                                    <td>${item.getMilestone_name()}</td>

                                                    <td>
                                                        ${item.getAssignee_name()}
                                                    </td>

                                                    <td>
                                                        ${item.getAccess_roles()}
                                                    </td>
                                                    <td>
                                                        ${item.getTracking_note()}
                                                    </td>
                                                    <td>
                                                        <c:if test="${item.getStatus() == 1}">
                                                            Planned
                                                        </c:if>
                                                        <c:if test="${item.getStatus() == 2}">
                                                            Analysed
                                                        </c:if>
                                                        <c:if test="${item.getStatus() == 3}">
                                                            Designed
                                                        </c:if>
                                                        <c:if test="${item.getStatus() == 4}">
                                                            Coded
                                                        </c:if>
                                                        <c:if test="${item.getStatus() == 5}">
                                                            Integrated
                                                        </c:if>
                                                        <c:if test="${item.getStatus() == 6}">
                                                            Submitted
                                                        </c:if>
                                                        <c:if test="${item.getStatus() == 7}">
                                                            Evaluated
                                                        </c:if>
                                                        <c:if test="${item.getStatus() == 8}">
                                                            Rejected
                                                        </c:if>
                                                    </td>

                                                    <td>
                                                        <a style=" text-decoration: none" href="tracking?go=edit&id=${item.getTracking_id()}" type="button" > 
                                                            <svg class="icon">
                                                            <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-pencil">
                                                            </use>
                                                            </svg>
                                                        </a>
                                                        <a style=" text-decoration: none" href="trackingupdate?go=view&tracking_id=${item.getTracking_id()}"> 
                                                            <svg class="icon">
                                                            <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-list">
                                                            </use>
                                                            </svg>
                                                        </a>
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
                                                    var list_key = "tracking_id|function_name|milestone_name|assignee_name|role|status".split("|");
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
                                                    url.searchParams.set("note_search", valSearch);
                                                    document.location.search = url.search;
                                                }


        </script>

    </body>

</html>
