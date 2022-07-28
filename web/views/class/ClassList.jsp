<%-- 
    Document   : ClassList
    Created on : Jun 16, 2022, 12:39:24 PM
    Author     : ptuan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <title>Class List</title>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="js/add.js"></script>
        <link href="vendors/@coreui/chartjs/css/coreui-chartjs.css" rel="stylesheet">
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
                            <li class="breadcrumb-item active"><span>Class List</span></li>
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
                        <div class="card-header"><strong>Class List</strong></div>
                        <div class="card-body">

                            <div class="d-flex">
                                <form action="class" method="post" class="input-group">
                                    <div class="input-group">

                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false" ${chekc == null ? "" : "hidden"}>Trainer</button>
                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="dropdown-item" onclick="urlSearch('trainer', -1)">All</a>
                                            </li>

                                            <c:forEach items="${user}" var="user1">
                                                <c:if test="${(user1.getRole_id() == 3 || user1.getRole_id() == 2) && user1.getStatus()== 1}">
                                                    <li>
                                                        <a class="dropdown-item" onclick="urlSearch('trainer', ${user1.getUser_id()})">${user1.getFull_name()}</a>
                                                    </li>
                                                </c:if>                                                            
                                            </c:forEach>

                                        </ul>

                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Status</button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" onclick="urlSearch('status', -1)">All</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 1)">Open</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 2)">Cancel</a></li>
                                            <li><a class="dropdown-item" onclick="urlSearch('status', 0)">Close</a></li>
                                        </ul>

                                        <input hidden="" name="action" value="view">
                                        <input class="form-control" type="text" name="inputSearch" aria-label="Text input with segmented dropdown button" value="${search}">
                                        <button class="btn btn-outline-secondary" type="submit">Search</button>  
                                    </div>
                                </form>
                            </div>
                            <hr class="dropdown-divider">
                            <a class="btn btn-outline-info" type="button" href="class?action=add">Add Class</a>
                            <hr class="dropdown-divider">
                            <div class="example" id="__rxz">
                                <div class="tab-content rounded-bottom">
                                    <div class="tab-pane p-3 active preview" role="tabpanel" id="preview-752">
                                        <table class="table border mb-0 table-dark table-striped">
                                            <thead class="table-light fw-semibold">
                                                <tr>
                                                    <th>
                                                        Subject
                                                    </th>
                                                    <th>
                                                        Class Code
                                                        <svg class="icon" id="class_code" onclick="filter(this, '${filter_types[0]}')">
                                                        <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                        </svg>
                                                    </th>
                                                    <th>
                                                        Trainer
                                                    </th>
                                                    <th>
                                                        Year
                                                        <svg class="icon" id="class_year" onclick="filter(this, '${filter_types[1]}')">
                                                        <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                        </svg>
                                                    </th>
                                                    <th>
                                                        Term
                                                    </th>
                                                    <th>
                                                        GitLabID
                                                    </th>
                                                    <th>
                                                        Block 5
                                                    </th>
                                                    <th>
                                                        Status
                                                    </th>
                                                    <th>
                                                        Action
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${class}" var="class2">
                                                    <tr>
                                                        <c:forEach items="${subject}" var="subject">
                                                            <c:if test="${class2.getSubject_id() == subject.getSubject_id()}">
                                                                <td>${subject.getSubject_name()}</td>
                                                            </c:if>                                                            
                                                        </c:forEach>
                                                        <th scope="row">${class2.getClass_code()}</th>

                                                        <c:forEach items="${user}" var="user1">
                                                            <c:if test="${user1.getUser_id() == class2.getTrainer_id()}">
                                                                <td>${user1.getFull_name()}</td>
                                                            </c:if>                                                            
                                                        </c:forEach>
                                                        <td>${class2.getClass_year()}</td>
                                                        <td>${class2.getClass_term()}</td>
                                                        <td>${class2.getGitlabID()}</td>
                                                        <td>${class2.getBlock5_class() == 1? "Yes":"No"}</td>

                                                        <td class="text-center">${class2.getStatus() == 1? "Open": class2.getStatus()==2 ? "Cancel" :"Close"}</td>
                                                        <td>
                                                            <a style=" text-decoration: none" href="class?action=edit&id=${class2.getClass_id()}" type="button" ${chekc == null ? "" : "hidden"}>
                                                                <svg class="icon">
                                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-pencil">
                                                                </use>
                                                                </svg>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>

                                    <nav aria-label="UserList pagination">
                                        <ul class="pagination justify-content-end">
                                            <li class="page-item ${pageIndex > 1 ? "" : "disabled"}"><a class="page-link" onclick="urlSearch('page', 1)" tabindex="-1" aria-disabled="true">First</a></li>
                                                <c:forEach var = "i" begin = "1" end = "${maxpage}">
                                                <li class="page-item ${pageIndex == i ? "active" : ""}"><a class="page-link" onclick="urlSearch('page', ${i})">${i}</a></li>
                                                </c:forEach>
                                            <li class="page-item ${pageIndex >= maxpage ? "disabled" : ""}"><a class="page-link" onclick="urlSearch('page', ${maxpage == 0 ? 1 : maxpage})">Last</a></li>
                                        </ul>
                                    </nav>

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
        <script>
                                                function urlSearch(key, val) {
                                                    var url = new URL(window.location.href);
                                                    url.searchParams.set("action", "view");
                                                    url.searchParams.set(key, val);
                                                    document.location.search = url.search;
                                                }
                                                function updateStatus(uid, status) {
                                                    var wrapper = $("#__rxz").closest("div");
                                                    $(wrapper).find('[role="alert"]').remove();
                                                    $("#rxzDgl").modal("hide");
                                                    $.ajax({
                                                        url: 'setting',
                                                        data: "go=delete&ID=" + uid + "&status=" + status,
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
                                                function filter(item, type) {
                                                    var url = new URL(window.location.href);
                                                    url.searchParams.set("action", "view");
                                                    var key = item.getAttribute('id');
                                                    var list_key = "class_code|class_year".split("|");
                                                    for (var i = 0; i < list_key.length; i++) {
                                                        if (list_key[i] !== key) {
                                                            url.searchParams.delete(list_key[i]);
                                                        }
                                                    }
                                                    url.searchParams.set(key, type);
                                                    document.location.search = url.search;
                                                }
        </script>
    </body>

</html>

