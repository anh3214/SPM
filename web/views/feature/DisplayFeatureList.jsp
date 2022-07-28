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
                            <li class="breadcrumb-item active"><span>Feature List</span></li>
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
                                <div class="card-header"><strong>Feature List</strong><span class="small ms-1"></span></div>

                            </div>
                            <div class="card-body">
                                <form method="GET" action="feature">
                                    <input type="hidden" name="team_id" value="${team_id}">
                                    <div class="table-responsive" id="notifi">
                                        <div class="input-group">

                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Class</button>
                                            <ul class="dropdown-menu">
                                                <c:forEach var="i" items="${sessionScope.classes}">
                                                    <li>
                                                        <a class="dropdown-item" onclick="getTeam('${i.getClass_id()}')">
                                                            ${i.getClass_code()}
                                                        </a>
                                                    </li>
                                                </c:forEach>
                                            </ul>

                                            <button id="team_button" class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Team</button>
                                            <ul class="dropdown-menu" id="team_list">
                                                <c:forEach var="i" items="${teams}">
                                                    <li><a class="dropdown-item" onclick="urlSearch('team_id', ${i.getTeam_id()})">${i.getTeam_name()}</a></li>
                                                    </c:forEach>
                                            </ul>

                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-coreui-toggle="dropdown" aria-expanded="false">Status</button>
                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item" onclick="urlSearch('status', -1)">All</a></li>
                                                <li><a class="dropdown-item" onclick="urlSearch('status', 1)">Active</a></li>
                                                <li><a class="dropdown-item" onclick="urlSearch('status', 0)">Inactive</a></li>
                                            </ul>
                                            <input class="form-control" type="text" id="inputSearch" value="${search_value}" placeholder="Search by name ..." aria-label="Text input with segmented dropdown button">
                                            <button class="btn btn-outline-secondary" type="button" onclick="search()">
                                                <svg class="icon">
                                                <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-search"></use>
                                                </svg>
                                                Search
                                            </button>
                                        </div>
                                        <hr class="dropdown-divider">
                                        <div  style="" >
                                            <c:if test="${role_id == 4}">
                                                <div class="input-group" >


                                                    <a class="btn btn-outline-secondary" href="feature?go=add&team_id=${team_id}" tabindex="-1" type="button" role="button" aria-disabled="true">
                                                        <svg class="icon">
                                                        <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-plus"></use>
                                                        </svg>
                                                        Add 
                                                    </a>   

                                                </div>
                                            </c:if>
                                        </div>


                                        <hr class="dropdown-divider">
                                        <table class="table table-dark table-striped" >
                                            <thead class="table-light fw-semibold" >
                                                <tr>

                                                    <th>
                                                        Name 
                                                        <svg class="icon" id="feature_name" onclick="filter(this, '${filter_types[0]}')">
                                                        <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                        </svg>
                                                    </th>
                                                    <th>
                                                        Description
                                                        <svg class="icon" id="description" onclick="filter(this, '${filter_types[1]}')">
                                                        <use xlink:href="vendors/@coreui/icons/svg/free.svg#cil-swap-vertical"></use>
                                                        </svg>
                                                    </th>
                                                    <th>Subject</th>
                                                    <th>Topic</th>
                                                    <th>Status</th>
                                                    <c:if test="${role_id == 4}"><th>Action</th></c:if>


                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${features}" var="i">
                                                    <tr>
                                                <input type="hidden" name="feature_id" value="${i.feature_id}">
                                                <td><b>${i.getFeature_name()}</b></td>
                                                <td>${i.getDescription()}</td>
                                                <td>${i.getSubject_code()}</td>
                                                <td>${i.getTopic_code()}- ${i.getTopic_name()}</td>
                                                <td class="text-center">
                                                    <div class="form-check form-switch">
                                                        <input <c:if test="${role_id == 3 || role_id == 2 || role_id == 1}"> disabled </c:if> class="form-check-input" name="status" id="sw__${i.getFeature_id()}" fe="${i.getFeature_id()}" type="checkbox" ${i.getStatus() == 1 ? "checked" : ""}>
                                                        </div>
                                                    </td>

                                                <c:if test="${role_id == 4}">
                                                    <td>
                                                        <a href="feature?go=edit&team_id=${team_id}&feature_id=${i.getFeature_id()}" class="btn btn-primary rounded-pill btn-sm" type="button">Edit</a>
                                                    </td>
                                                </c:if> 
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <br></br>
                                        <nav aria-label="FeatureList pagination">
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
                                                    let class_id = ${class_id};

                                                    function getTeam(id) {
                                                        class_id = id;
                                                        $.ajax({
                                                            url: 'feature',
                                                            data: "go=getTeam&class_id=" + id,
                                                            cache: false,
                                                            processData: false,
                                                            type: 'POST',
                                                            success: function (data) {
                                                                document.getElementById('team_list').innerHTML = '';
                                                                document.getElementById('team_list').innerHTML = data;
                                                            }
                                                        });
                                                    }

                                                    function urlSearch(key, val) {
                                                        var url = new URL(window.location.href);
                                                        url.searchParams.set("go", "viewAll");
                                                        url.searchParams.set("class_id", class_id);
                                                        url.searchParams.set(key, val);
                                                        var keys = "status".split("|");

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
                                                        var list_key = "feature_name|description".split("|");
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
                                                        url.searchParams.set("feature_nameSearch", valSearch);
                                                        document.location.search = url.search;
                                                    }

                                                    function updateStatus(featureID, status) {
                                                        var wrapper = $("#notifi").closest("div");
                                                        $(wrapper).find('[role="alert"]').remove();
                                                        $("#rxzDgl").modal("hide");
                                                        $.ajax({
                                                            url: 'feature',
                                                            data: "go=updateStatus&feature_id=" + featureID + "&status=" + status,
                                                            cache: false,
                                                            processData: false,
                                                            type: 'POST',
                                                            success: function (data) {
                                                                if (data == "success") {
                                                                    if (status == 1)
                                                                        $('#sw__' + featureID)[0].checked = true;
                                                                    else
                                                                        $('#sw__' + featureID)[0].checked = false;
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
                                                        var featureID = this.id.replace("sw__", "");
                                                        $("#rxzDglClose").click(function () {
                                                            $("#rxzDgl").modal("hide");
                                                        })
                                                        $("#rxzDglConfirm").off();
                                                        if (this.checked) {
                                                            this.checked = false;
                                                            $("#rxzDglTitle").html("Activate this feature?");
                                                            $("#rxzDglBody").html("Do you want to active feature: " + $("#" + this.id).attr("fe"));
                                                            $("#rxzDglConfirm").click(function () {
                                                                updateStatus(featureID, 1);
                                                            })


                                                        } else {
                                                            this.checked = true;
                                                            $("#rxzDglTitle").html("Inactivatethis feature?");
                                                            $("#rxzDglBody").html("Do you want to active feature: " + $("#" + this.id).attr("fe"));
                                                            $("#rxzDglConfirm").click(function () {
                                                                updateStatus(featureID, 0);
                                                            })
                                                        }
                                                        $("#rxzDgl").modal("show")
                                                    });
        </script>

    </body>

</html>
