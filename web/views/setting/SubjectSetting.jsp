<%@page import="entity.Subject"%>
<%@page import="entity.SubjectSetting"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : SubjectSetting
    Created on : May 31, 2022, 4:14:46 PM
    Author     : ptuan
--%>
<%@page import="entity.Setting"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    </head>
    <style>
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
    </style>
    <body>
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
                            <li class="breadcrumb-item active"><span>Setting Subject</span></li>
                        </ol>
                    </nav>
                </div>
            </header>
            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="car"></div>
                    <!-- Table Content-->
                    <div class="card mb-4">
                        <div class="card-header"><strong>Setting Subject</strong></div>
                        <div class="card-body">
                            <div class="d-flex" data>
                                <form action="subjectsetting" method="post" class="input-group">

                                    <button type="button" class="btn btn-outline-secondary dropdown-toggle" id="dropdownMenuReference" data-coreui-toggle="dropdown" aria-expanded="false" data-coreui-reference="parent">
                                        <span class="">Type</span>
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuReference">
                                        <li>
                                            <a class="dropdown-item" onclick="urlSearch('trainer', -1)">All</a>
                                        </li>
                                        <c:forEach items="${list2}" var="setting">
                                            <c:forEach items="${listType}" var="type">
                                                <c:if test="${setting.getSetting_id() == type}">
                                                    <li>
                                                        <a class="dropdown-item" href="subjectsetting?action=searchType&tye=${setting.getSetting_id()}">${setting.getSetting_title()}</a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                    </ul>

                                    <button type="button" class="btn btn-outline-secondary dropdown-toggle" id="dropdownStatus" data-coreui-toggle="dropdown" aria-expanded="false" data-coreui-reference="parent">
                                        <span class="">Status</span>
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="dropdownStatus" data-show-active-item="">
                                        <li>
                                            <a class="dropdown-item" onclick="urlSearch('trainer', -1)">All</a>
                                        </li>
                                        <li>
                                            <a class="dropdown-item" href="subjectsetting?action=searStatus&sta=1">Active</a>
                                        </li>
                                        <li>
                                            <a class="dropdown-item" href="subjectsetting?action=searStatus&sta=0">Inactive</a>
                                        </li>
                                    </ul>

                                    <input hidden="" name="action" value="searchsubject">
                                    <input type="text" class="form-control" placeholder="Type title to search" aria-label="Type Subject name to search" aria-describedby="button-addon2" name="name">
                                    <button class="btn btn-outline-dark" type="submit" id="button-addon2">Search</button>
                                </form>
                            </div>
                            <hr class="dropdown-divider">
                            <a class="btn btn-outline-info" type="button" href="subjectsetting?action=add">Add Subject Setting</a>
                            <hr class="dropdown-divider">
                            <div class="example" id="__rxz">
                                <div class="tab-content rounded-bottom">
                                    <div class="tab-pane p-3 active preview" role="tabpanel" id="preview-752">
                                        <table class="table border mb-0 table-dark table-striped">
                                            <thead class="table-light fw-semibold">
                                                <tr>
                                                    <th scope="col">Subject</th>
                                                    <th scope="col">Type</th>
                                                    <th scope="col">Tile</th>
                                                    <th scope="col">Value</th>
                                                    <th scope="col">Order</th>
                                                    <th scope="col">Active</th>
                                                    <th scope="col">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody id="sort_data">
                                                <c:forEach items="${list}" var="list1">
                                                    <tr>
                                                        <c:forEach items="${listS}" var="listS">
                                                            <c:if test="${list1.getSubject_id() == listS.getSubject_id()}">
                                                                <th scope="row" >
                                                                    ${listS.getSubject_name()}
                                                                </th>
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:forEach items="${list2}" var="settings">
                                                            <c:if test="${settings.getSetting_id() == list1.getType_id()}">
                                                                <td>
                                                                    ${settings.getSetting_title()}
                                                                </td>
                                                            </c:if>
                                                        </c:forEach>
                                                        <td>${list1.getSetting_tile()}</td>
                                                        <td>${list1.getSetting_value()}</td>
                                                        <td>${list1.getDisplay_order()}</td>
                                                        <td>
                                                            <div class="form-check form-switch">
                                                                <input class="form-check-input" name="sw" id="sw__${list1.getSetting_id()}" fe="${list1.getSetting_tile()}" type="checkbox" ${list1.getStatus()==1 ? "checked":""}>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <a class="btn btn-primary rounded-pill btn-sm"  style=" text-decoration: none" href="subjectsetting?action=edit&id=${list1.getSetting_id()}">Edit</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>

                                    <nav aria-label="UserList pagination">
                                        <ul class="pagination justify-content-end" style=" list-style-type: none;">   

                                            <c:if test="${sta != null}">
                                                <nav aria-label="UserList pagination">
                                                    <ul class="pagination justify-content-end">
                                                        <li class="page-item ${page > 1 ? "" : "disabled"}"><a class="page-link" href="subjectsetting?action=searStatus&sta=${sta}&pages=1" tabindex="-1" aria-disabled="true">First</a></li>
                                                            <c:forEach var = "i" begin = "1" end = "${maxpage}">
                                                            <li class="page-item ${page == i ? "active" : ""}"><a class="page-link" href="subjectsetting?action=searStatus&sta=${sta}&pages=${i}">${i}</a></li>
                                                            </c:forEach>
                                                        <li class="page-item ${page >= maxpage ? "disabled" : ""}"><a class="page-link" href="subjectsetting?action=searStatus&sta=${sta}&pages=${maxpage == 0 ? 1 : maxpage}">Last</a></li>
                                                    </ul>
                                                </nav>
                                            </c:if>

                                            <c:if test="${tye != null}">
                                                <nav aria-label="UserList pagination">
                                                    <ul class="pagination justify-content-end">
                                                        <li class="page-item ${page > 1 ? "" : "disabled"}"><a class="page-link" href="subjectsetting?action=searchType&tye=${tye}&pages=1" tabindex="-1" aria-disabled="true">First</a></li>
                                                            <c:forEach var = "i" begin = "1" end = "${maxpage}">
                                                            <li class="page-item ${page == i ? "active" : ""}"><a class="page-link" href="subjectsetting?action=searchType&tye=${tye}&pages=${i}">${i}</a></li>
                                                            </c:forEach>
                                                        <li class="page-item ${page >= maxpage ? "disabled" : ""}"><a class="page-link" href="subjectsetting?action=searchType&tye=${tye}&pages=${maxpage == 0 ? 1 : maxpage}">Last</a></li>
                                                    </ul>
                                                </nav>                                                                                               
                                            </c:if>

                                            <c:if test="${name != null}">
                                                <nav aria-label="UserList pagination">
                                                    <ul class="pagination justify-content-end">
                                                        <li class="page-item ${page > 1 ? "" : "disabled"}"><a class="page-link" href="subjectsetting?action=searchsubject&name=${name}&pages=1" tabindex="-1" aria-disabled="true">First</a></li>
                                                            <c:forEach var = "i" begin = "1" end = "${maxpage}">
                                                            <li class="page-item ${page == i ? "active" : ""}"><a class="page-link" href="subjectsetting?action=searchsubject&name=${name}&pages=${i}">${i}</a></li>
                                                            </c:forEach>
                                                        <li class="page-item ${page >= maxpage ? "disabled" : ""}"><a class="page-link" href="subjectsetting?action=searchsubject&name=${name}&pages=${maxpage == 0 ? 1 : maxpage}">Last</a></li>
                                                    </ul>
                                                </nav>  
                                            </c:if>

                                            <c:if test="${sta == null && tye == null && name == null}">
                                                <nav aria-label="UserList pagination">
                                                    <ul class="pagination justify-content-end">
                                                        <li class="page-item ${page > 1 ? "" : "disabled"}"><a class="page-link" onclick="urlSearch('pages', 1)" tabindex="-1" aria-disabled="true">First</a></li>
                                                            <c:forEach var = "i" begin = "1" end = "${maxpage}">
                                                            <li class="page-item ${page == i ? "active" : ""}"><a class="page-link" onclick="urlSearch('pages', ${i})">${i}</a></li>
                                                            </c:forEach>
                                                        <li class="page-item ${page >= maxpage ? "disabled" : ""}"><a class="page-link" onclick="urlSearch('pages', ${maxpage == 0 ? 1 : maxpage})">Last</a></li>
                                                    </ul>
                                                </nav>
                                            </c:if>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
</body>
<script>
                                                            function updateStatus(uid, status) {
                                                                var wrapper = $("#__rxz").closest("div");
                                                                $(wrapper).find('[role="alert"]').remove();
                                                                $("#rxzDgl").modal("hide");
                                                                $.ajax({
                                                                    url: 'subjectsetting',
                                                                    data: "action=delete&ID=" + uid + "&status=" + status,
                                                                    cache: false,
                                                                    processData: false,
                                                                    type: 'POST',
                                                                    success: function (data) {
                                                                        if (data === "success") {
                                                                            if (status === 1)
                                                                                $('#sw__' + uid)[0].checked = false;
                                                                            else
                                                                                $('#sw__' + uid)[0].checked = true;
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

                                                            $('input[name="sw"]').change(function () {
                                                                var uid = this.id.replace("sw__", "");
                                                                $("#rxzDglClose").click(function () {
                                                                    $("#rxzDgl").modal("hide");
                                                                });
                                                                $("#rxzDglConfirm").off();
                                                                if (this.checked) {
                                                                    this.checked = false;
                                                                    $("#rxzDglTitle").html("Activate user - " + uid);
                                                                    $("#rxzDglBody").html("Do you want to active: " + $("#" + this.id).attr("fe"));
                                                                    $("#rxzDglConfirm").click(function () {
                                                                        updateStatus(uid, 0);
                                                                    });


                                                                } else {
                                                                    this.checked = true;
                                                                    $("#rxzDglTitle").html("Inactivate user - " + uid);
                                                                    $("#rxzDglBody").html("Do you want to deactive user: " + $("#" + this.id).attr("fe"));
                                                                    $("#rxzDglConfirm").click(function () {
                                                                        updateStatus(uid, 1);
                                                                    });
                                                                }
                                                                $("#rxzDgl").modal("show");
                                                            });
                                                            function urlSearch(key, val) {
                                                                var url = new URL(window.location.href);
                                                                url.searchParams.set("action", "listAll");
                                                                url.searchParams.set(key, val);
                                                                document.location.search = url.search;
                                                            }
</script>
</html>

