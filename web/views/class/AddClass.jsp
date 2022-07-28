<%-- 
    Document   : AddClass
    Created on : Jun 19, 2022, 6:05:35 AM
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
        <title>Add class</title>
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
                            <li class="breadcrumb-item active"><span>Class List</span></li>
                            <li class="breadcrumb-item active"><span>Add class</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">

                    <!-- Table Content-->

                    <div class="card mb-4">
                        <div class="card-header"><strong>Add Class</strong></div>
                        <div class="card-body">
                            <form id="__form">
                                <div class="row gx-3 mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label" for="inputTitle">Subject(*)</label>
                                        <select class="form-control" name="subject">
                                            <c:forEach items="${subject}" var="k">
                                                <option value="${k.getSubject_id()}">${k. getSubject_name()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label" for="inputValue">Trainer(*)</label>
                                        <select class="form-control" name="trainer">
                                            <c:forEach items="${user}" var="i">
                                                <c:if test="${(i.getRole_id()== 3 || i.getRole_id() == 2 ) && i.getStatus() == 1}">
                                                    <option value="${i.getUser_id()}">${i.getFull_name()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row gx-3 mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label" for="inputTitle">Class Code(*)</label>
                                        <input type="text" class="form-control" id="inputclass_code" name="class_code" rows="3"></input>
                                        <div class="invalid-feedback">
                                            This information is not null, and not the same code.
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label" for="inputValue">Year(*)</label>
                                        <input type="text" class="form-control" id="input_year" name="year" rows="3" maxlength="20" required=""></input>
                                        <div class="invalid-feedback">
                                            This information is not null.
                                        </div>
                                    </div>
                                </div>
                                <div class="row gx-3 mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label" for="inputOrder">Term*:</label>
                                        <input class="form-control" type="text" id="input_term" min="1" max="10"  name="term">
                                        <div class="invalid-feedback">
                                            This information is not null.
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label" for="inputGit">ID GitLab(*)</label>
                                        <input type="text" class="form-control" id="input_year" name="git" rows="3" maxlength="20" required=""></input>
                                        <div class="invalid-feedback">
                                            This information is not null.
                                        </div>
                                    </div>
                                </div>
                                <div class="row gx-3 mb-3">
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputstatus">Status*</label>

                                            <input class="form-check-input" id="status1" type="radio" name="status" value="1" checked="">
                                            <label class="form-check-label" for="inlineRadio1">Open</label>
                                            <input class="form-check-input" id="status2" type="radio" name="status" value="2">
                                            <label class="form-check-label" for="inlineRadio2">Cancel</label>
                                            <input class="form-check-input" id="status3" type="radio" name="status" value="0">
                                            <label class="form-check-label" for="inlineRadio3">Close</label>                                                    
                                    </div>
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputPhone1">Block 5*</label>
                                        <input class="form-check-input" id="block51" type="radio" name="block5" value="1">
                                        <label class="form-check-label" for="inlineRadio12">Yes</label>
                                        <input class="form-check-input" id="block52" type="radio" name="block5" value="0" checked="">
                                        <label class="form-check-label" for="inlineRadio21">No</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <button class="btn btn-outline-info" type="reset">Reset</button>
                                    <button id="submit" class="btn btn-primary" type="button" onclick="save1_change()">Add Class</button>
                                </div>
                                <input hidden="" name="submit" value="submit">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
    <script>
        function save1_change() {
            "class_code|year|term|git|block5|status".split("|").forEach(err => $("[name='" + err + "']").removeClass("is-invalid"));
            var wrapper = $("#__form").closest("div");
            $(wrapper).find('[role="alert"]').remove();
            $.ajax({
                url: 'class',
                data: "action=add&" + $("#__form").serialize(),
                cache: false,
                processData: false,
                type: 'POST',
                success: function (data) {
                    // update name
                    console.log(data);
                    if (data == "success") {
                        $(wrapper).append(
                                '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> Add successfully</div>'
                                );
                        setTimeout(() => {
                            $(wrapper).find('[role="alert"]').remove();
                        }, 3000);
                    } else {
                        if (data.search("error") !== -1)
                            data.replace("error: ", "").split("|").forEach(err => $("[name='" + err + "']").addClass("is-invalid"));
                        $(wrapper).append(
                                '<div class="snackbar show" role="alert"><i class="fa fa-times-circle text-danger"></i> There is an error while Add! Please try again later.</div>'
                                );
                        setTimeout(() => {
                            $(wrapper).find('[role="alert"]').remove();
                        }, 3000);
                    }
                }
            });
        }
    </script>
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
</html>
