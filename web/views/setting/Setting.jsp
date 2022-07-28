<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : Dashboard
    Created on : May 19, 2022, 4:22:11 PM
    Author     : RxZ
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

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
                            <li class="breadcrumb-item active"><span>Settings</span></li>
                        </ol>
                    </nav>
                </div>
            </header>
            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="car"></div>
                    <!-- Table Content-->
                    <%
                        Vector<Setting> vector = (Vector<Setting>) request.getAttribute("list");
                        Vector<String> vector2 = (Vector<String>) request.getAttribute("list2");
                    %>
                    <div class="card mb-4">
                        <div class="card-header"><strong>System Setting</strong></div>
                        <div class="card-body">

                            <div class="d-flex">
                                <form action="setting" method="post" class="input-group">
                                    <div class="input-group">
                                        <button type="button" class="btn btn-outline-secondary dropdown-toggle" id="dropdownMenuReferbtn-secondary dropdown-toggle dropdown-toggle-splitence" data-coreui-toggle="dropdown" aria-expanded="false" data-coreui-reference="parent">
                                            <span class="">All Setting</span>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuReference">
                                            <li>
                                                <a class="dropdown-item" onclick="urlSearch('trainer', -1)">All</a>
                                            </li>
                                            <%
                                                for (String elem : vector2) {
                                                    String namet = (String) session.getAttribute(elem);
                                            %>
                                            <li>
                                                <a class="dropdown-item" href="setting?go=search&tye=<%=elem%>&pages=${page}"><%=namet%></a>
                                            </li>
                                                <%}%>
                                        </ul>


                                        <button type="button" class="btn btn-outline-secondary dropdown-toggle" id="dropdownMenuReference" data-coreui-toggle="dropdown" aria-expanded="false" data-coreui-reference="parent">
                                            <span class="">All Status</span>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuReference">
                                            <li>
                                                <a class="dropdown-item" onclick="urlSearch('trainer', -1)">All</a>
                                            </li>
                                            <li>
                                                <a class="dropdown-item" href="setting?go=searchs&sta=<%=1%>&pages=${page}">Active</a>
                                            </li>
                                            <li>
                                                <a class="dropdown-item" href="setting?go=searchs&sta=<%=0%>&pages=${page}">Inactive</a>
                                            </li>
                                        </ul>

                                        <input hidden="" name="go" value="searchName">
                                        <input type="text" class="form-control" aria-label="Type Setting name to search" aria-describedby="button-addon2" name="name">
                                        <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Search</button>
                                    </div>
                                </form>
                            </div>
                            <hr class="dropdown-divider">
                            <a class="btn btn-outline-info" type="button" href="setting?action=add">Add Setting</a>
                            <hr class="dropdown-divider">
                            <div class="example" id="__rxz">
                                <div class="tab-pane p-3 active preview" role="tabpanel" id="preview-752">
                                    <table class="table border mb-0 table-dark table-striped">
                                        <thead class="table-light fw-semibold">
                                            <tr>
                                                <th>Setting Type</th>
                                                <th>Name</th>
                                                <th>Order</th>
                                                <th>Value</th>
                                                <th>Active</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (Setting elem : vector) {
                                                    try {
                                                        String name_type = (String) session.getAttribute(elem.getType_id() + "");
                                            %>
                                            <tr>
                                                <th scope="row"><%=name_type%></th>
                                                <td><%=elem.getSetting_title()%></td>
                                                <td><%=elem.getDisplay_order()%></td>
                                                <td><%=elem.getSetting_value()%></td>
                                                <td class="text-center">
                                                    <div class="form-check form-switch">
                                                        <%if (elem.getStatus() == 1) {%>
                                                        <input class="form-check-input" name="sw" id="sw__<%=elem.getSetting_id()%>" fe="<%=elem.getSetting_title()%>" type="checkbox" checked="">
                                                        <%} else {%>
                                                        <input class="form-check-input" name="sw" id="sw__<%=elem.getSetting_id()%>" fe="<%=elem.getSetting_title()%>" type="checkbox">
                                                        <%}%>
                                                    </div>
                                                </td>
                                                <td>
                                                    <a class="btn btn-primary rounded-pill btn-sm" style=" text-decoration: none" href="setting?action=edit&setting_id=<%=elem.getSetting_id()%>" type="button">Edit</a>
                                                </td>
                                            </tr>
                                            <%} catch (Exception e) {
                                                    }
                                                }%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <nav aria-label="UserList pagination">
                                <ul class="pagination justify-content-end" style=" list-style-type: none;">   

                                    <c:if test="${sta != null}">
                                        <li class="page-item"><a class="page-link" href="setting?go=searchs&sta=${sta}&pages=${pre}"style=" text-decoration: none;"><i>Previous</i></a></li>
                                            <c:forEach begin = "1" end = "${maxpage}" var="i">
                                                <c:choose>
                                                    <c:when test="${i != page}">
                                                    <li class="page-item"><span><a class="page-link" href="setting?go=searchs&sta=${sta}&pages=${i}"style=" text-decoration: none; ">${i}</a></span></li>                                                                                                                              
                                                            </c:when>
                                                            <c:otherwise>
                                                    <li class="page-item"><span><a class="page-link" href="setting?go=searchs&sta=${sta}&pages=${i}"style=" text-decoration: none; background-color: orange; ">${i}</a></span></li>
                                                            </c:otherwise> 
                                                        </c:choose>
                                                    </c:forEach>
                                        <li class="page-item"><a class="page-link" href="setting?go=searchs&sta=${sta}&pages=${next}"style=" text-decoration: none; "><i class="next">Next</i></a></li>
                                        </c:if>

                                    <c:if test="${tye != null}">
                                        <li class="page-item"><a class="page-link" href="setting?go=search&tye=${tye}&pages=${pre}"style=" text-decoration: none; "><i>Previous</i></a></li>
                                            <c:forEach begin = "1" end = "${maxpage}" var="i">
                                                <c:choose>
                                                    <c:when test="${i != page}">
                                                    <li class="page-item"><span><a class="page-link" href="setting?go=search&tye=${tye}&pages=${i}"style=" text-decoration: none; ">${i}</a></span></li>                                                                                                                              
                                                            </c:when>
                                                            <c:otherwise>
                                                    <li class="page-item"><span><a class="page-link" href="setting?go=search&tye=${tye}&pages=${i}"style=" text-decoration: none; background-color: orange;">${i}</a></span></li>
                                                            </c:otherwise> 
                                                        </c:choose>
                                                    </c:forEach>  
                                        <li class="page-item"><a class="page-link" href="setting?go=search&tye=${tye}&pages=${next}"style=" text-decoration: none; "><i class="next">Next</i></a></li>
                                        </c:if>

                                    <c:if test="${name != null}">
                                        <li class="page-item"><a class="page-link" href="setting?go=searchName&name=${name}&pages=${pre}"style=" text-decoration: none; "><i>Previous</i></a></li>
                                            <c:forEach begin = "1" end = "${maxpage}" var="i">
                                                <c:choose>
                                                    <c:when test="${i != page}">
                                                    <li class="page-item"><span><a class="page-link" href="setting?go=searchName&name=${name}&pages=${i}"style=" text-decoration: none;">${i}</a></span></li>                                                                                                                              
                                                            </c:when>
                                                            <c:otherwise>
                                                    <li class="page-item"><span><a class="page-link" href="setting?go=searchName&name=${name}&pages=${i}"style=" text-decoration: none; background-color: orange;">${i}</a></span></li>
                                                            </c:otherwise> 
                                                        </c:choose>
                                                    </c:forEach>
                                        <li class="page-item"><a class="page-link" href="setting?go=searchName&name=${name}&pages=${next}"style=" text-decoration: none;"><i class="next">Next</i></a></li>
                                        </c:if>

                                    <c:if test="${sta == null && tye == null && name == null}">
                                        <li class="page-item"><a class="page-link" href="setting?pages=${pre}"style=" text-decoration: none;"><i>Previous</i></a></li>
                                            <c:forEach begin = "1" end = "${maxpage}" var="i">
                                                <c:choose>
                                                    <c:when test="${i != page}">
                                                    <li class="page-item"><span><a class="page-link" href="setting?pages=${i}"style=" text-decoration: none;">${i}</a></span></li>                                                                                                                              
                                                            </c:when>
                                                            <c:otherwise>
                                                    <li class="page-item"><span><a class="page-link" href="setting?pages=${i}"style=" text-decoration: none; background-color: orange; ">${i}</a></span></li>
                                                            </c:otherwise> 
                                                        </c:choose>
                                                    </c:forEach>
                                        <li class="page-item"><a class="page-link" href="setting?pages=${next}"style=" text-decoration: none;"><i class="next">Next</i></a></li>
                                        </c:if>
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

        </script>

    </body>

</html>
