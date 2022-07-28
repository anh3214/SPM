<%-- 
    Document   : DisplaySubjectSettingDetail
    Created on : Jun 4, 2022, 9:28:43 PM
    Author     : ptuan
--%>
<%@page import="entity.SubjectSetting"%>
<%@page import="entity.Subject"%>
<%@page import="java.util.Vector"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <base href="./">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta name="description" content="CoreUI - Open Source Bootstrap Admin Template">
        <meta name="author" content="Łukasz Holeczek">
        <meta name="keyword" content="Bootstrap,Admin,Template,Open,Source,jQuery,CSS,HTML,RWD,Dashboard">
        <title>Subject Setting Details</title>
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
    </head>
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
                            <li class="breadcrumb-item active"><span>Setting Subject Detail</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">

                    <div class="car">
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

                        <!-- The Modal -->
                        <div class="modal" id="myModal">
                            <div class="modal-dialog">
                                <div class="modal-content">

                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">Confirm</h4>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                    </div>

                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        Are you sure you want to update the Setting Subject?
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-outline-info" data-bs-dismiss="modal" onclick="update()">Oke</button>
                                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancel</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Table Content-->

                    <div class="card mb-4">
                        <div class="card-header"><strong>Subject Setting Management</strong></div>
                        <div class="card-body">
                            <form id="__form">
                                <input hidden="" name="action" value="edit">
                                <c:forEach var="subSetting" items="${subjectsetting}">
                                    <div class="row gx-3 mb-3">
                                        <input hidden="" name="sub_idx" value="${subSetting.getSetting_id()}">
                                        <!-- Form Group (first name)-->
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputSubjectName">Subject Name:(*)</label>
                                            <select id="sub_name" class="form-control" name="id_subject">
                                                <c:forEach var="subject1" items="${subject}">
                                                    <option value="${subject1.getSubject_id()}" ${subject1.getSubject_id() == subSetting.getSubject_id()? "selected":""}>${subject1.getSubject_name()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputTypeName">Type:(*)</label>
                                            <select id="type" class="form-control" name="id_type">
                                                <c:forEach var="setting1" items="${setting}">
                                                    <option value="${setting1.getSetting_id()}" ${setting1.getSetting_id() == subSetting.getType_id()? "selected":""}>${setting1.getSetting_title()}</option>                                           
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>                       
                                    <div class="row gx-3 mb-3">
                                        <div class="col-md-6">
                                            <label class="form-label" for="inputTitle">Title:(*)</label>
                                            <input type="text" class="form-control" id="inputTitle" name="title" rows="3" value="${subSetting.getSetting_tile()}"></input>
                                            <div class="invalid-feedback">
                                                Title is not null.
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label" for="inputValue">Value:(*)</label>
                                            <input type="text" class="form-control" id="inputTitle" name="value" rows="3" value="${subSetting.getSetting_value()}"></input>
                                            <div class="invalid-feedback">
                                                Value is not null.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row gx-3 mb-3">
                                        <div class="col-md-12">
                                            <label class="form-label" for="inputOrder">Display Order:</label>
                                            <input class="form-control" type="number" id="order" min="1" max="10" required="" name="Display" value="${subSetting.getDisplay_order()}">
                                            <div class="invalid-feedback">
                                                Value is not null.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row gx-3 mb-3">
                                        <label class="small mb-1" for="inputstatus">Status:</label>
                                        <div class="">
                                            <input class="form-check-input" id="active" type="radio" name="status" value="1" ${subSetting.getStatus() == 1 ? "checked":""}>
                                            <label class="form-check-label" for="inlineRadio1">Active</label>
                                            <input class="form-check-input" id="inactive" type="radio" name="status" value="0" ${subSetting.getStatus() == 0 ? "checked":""}>
                                            <label class="form-check-label" for="inlineRadio2">Inactive</label>
                                        </div>
                                    </div>
                                </c:forEach>
                                <div class="col-md-6">
                                    <button class="btn btn-outline-info" type="reset">Reset</button>
                                    <button id="submit" class="btn btn-primary" type="button" onclick="save_change()">Save Changes</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer -->
            <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://unpkg.com/toastmaker/dist/toastmaker.min.css">
        <script type="text/javascript" src="https://unpkg.com/toastmaker/dist/toastmaker.min.js"></script>
        <!-- CoreUI and necessary plugins-->
        <script src="vendors/@coreui/coreui/js/coreui.bundle.min.js"></script>
        <script src="vendors/simplebar/js/simplebar.min.js"></script>
        <!-- Plugins and scripts required by this view-->
        <script src="vendors/chart.js/js/chart.min.js"></script>
        <script src="vendors/@coreui/chartjs/js/coreui-chartjs.js"></script>
        <script src="vendors/@coreui/utils/js/coreui-utils.js"></script>
        <script src="js/main.js"></script>
        <script src="js/add.js"></script>
    </body>
    <script>
                                        function save_change() {
                                            "title|value".split("|").forEach(err => $("[name='" + err + "']").removeClass("is-invalid"));
                                            var wrapper = $("#__form").closest("div");
                                            $(wrapper).find('[role="alert"]').remove();
                                            $.ajax({
                                                url: 'subjectsetting',
                                                data: $("#__form").serialize() + "&submit=submit",
                                                cache: false,
                                                processData: false,
                                                type: 'POST',
                                                success: function (data) {
                                                    if (data == "success") {
                                                        $(wrapper).append(
                                                                '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i>Update Subject Setting successfully</div>'
                                                                );

                                                        setTimeout(() => {
                                                            $(wrapper).find('[role="alert"]').remove();
                                                        }, 3000);
                                                    } else {
                                                        if (data.search("error: |") != -1) {
                                                            data.replace("error: ", "").split("|").forEach(err => $("[name='" + err + "']").addClass("is-invalid"));
                                                        }
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
    </script>
</html>
