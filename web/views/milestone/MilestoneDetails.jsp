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
                            <li class="breadcrumb-item active"><span>User Details</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="row">
                        <div class="col-xl-8">
                            <!-- Profile picture card-->
                            <div class="card mb-4">
                                <div class="card-header">Milestone Details - ${milestone.getMilestone_name()}</div>

                                <div class="card-body">
                                    <form id="__form">
                                        <input class="form-control" type="hidden" name="go" value="edit" readonly="">
                                        <input class="form-control" id="inputClassID" type="hidden" name="class_id" value="${milestone.getClass_id()}" readonly="">
                                        <input class="form-control" id="inputMilestoneID" type="hidden" name="milestone_id" value="${milestone.getMilestone_id()}" readonly="">
                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (first name)-->
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="inputIterationID">Iteration ID (*)</label>
                                                <select class="form-select form-select-sm" id="inputIterationID" name="iteration_id" aria-label=".form-select-sm example">
                                                    <c:forEach var="item" items="${iterations}">
                                                        <c:if test="${milestone.getIteration_id() == item.getIteration_id()}">
                                                            <option selected value="${item.getIteration_id()}">${item.getIteration_name()} - ${item.getDuration()} Weeks</option>
                                                        </c:if>
                                                        <c:if test="${milestone.getIteration_id() != item.getIteration_id()}">
                                                            <option value="${item.getIteration_id()}">${item.getIteration_name()} - ${item.getDuration()} Weeks</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <div class="invalid-feedback">
                                                    Please choose an Iteration.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="inputClassCode">Class (*)</label>
                                                <input class="form-control" id="inputClassCode" type="text" name="class_code" value="${milestone.getClass_code()}" required readonly="">
                                                <div class="invalid-feedback">
                                                    Please choose a class.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputMilestoneName">Milestone Name</label>
                                                <input type="text" class="form-control" id="inputMilestoneName" name="name" rows="3" value="${milestone.getMilestone_name()}"></input>
                                                <div class="invalid-feedback">
                                                    Name is too long.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="inputFromDate">From Date (*)</label>
                                                <input class="form-control" id="inputFromDate" type="date" name="from_date" value="${milestone.getFrom_date()}" required="">
                                                <div class="invalid-feedback">
                                                    Please give valid date with iteration duration.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (birthday)-->
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="inputToDate">To Date (*)</label>
                                                <input class="form-control" id="inputToDate" type="date" name="to_date" value="${milestone.getTo_date()}" required="">
                                                <div class="invalid-feedback">
                                                    Please give valid date with iteration duration.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputMilestoneDescription">Milestone Description</label>
                                                <textarea class="form-control" id="inputMilestoneDescription" name="description" rows="3">${milestone.getDescription()}</textarea>
                                                <div class="invalid-feedback">
                                                    Description is too long.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="inputstatus">Status (*)</label>
                                                <div class="">
                                                    <input class="form-check-input" id="open" type="radio" name="status" value="1">
                                                    <label class="form-check-label" for="inlineRadio1">Open</label>
                                                    <input class="form-check-input" id="closed" type="radio" name="status" value="0">
                                                    <label class="form-check-label" for="inlineRadio2">Closed</label>
                                                    <input class="form-check-input" id="canceled" type="radio" name="status" value="2">
                                                    <label class="form-check-label" for="inlineRadio2">Canceled</label>

                                                    <c:if test = '${milestone.getStatus() == 0}'>
                                                        <script>
                                                            document.getElementById("closed").checked = true;
                                                        </script>
                                                    </c:if>

                                                    <c:if test = '${milestone.getStatus() == 1}'>
                                                        <script>
                                                            document.getElementById("open").checked = true;
                                                        </script>
                                                    </c:if>

                                                    <c:if test = '${milestone.getStatus() == 2}'>
                                                        <script>
                                                            document.getElementById("canceled").checked = true;
                                                        </script>
                                                    </c:if>
                                                </div>
                                                <div class="invalid-feedback">
                                                    Choose a status.
                                                </div>
                                            </div>
                                        </div>

                                        <c:if test="${sessionScope.role_id != 4}">
                                            <button class="btn btn-outline-info" type="reset">Reset</button>
                                            <button id="submit" class="btn btn-primary" type="button" onclick="save_change()">Save changes</button>
                                        </c:if>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <c:if test="${sessionScope.role_id == 4}">
                            <script>
                                document.getElementById("inputIterationID").setAttribute("disabled", "true");
                                document.getElementById("inputMilestoneName").setAttribute("readonly", "true");
                                document.getElementById("inputFromDate").setAttribute("readonly", "true");
                                document.getElementById("inputToDate").setAttribute("readonly", "true");
                                document.getElementById("inputMilestoneDescription").setAttribute("readonly", "true");
                                document.getElementById("open").setAttribute("disabled", "true");
                                document.getElementById("closed").setAttribute("disabled", "true");
                                document.getElementById("canceled").setAttribute("disabled", "true");
                            </script>
                        </c:if>
                        <div class="col-xl-4">
                            <table border="0" class="table border mb-0 table-dark table-striped">

                                <tbody>
                                    <tr>
                                        <th>INFORMATION</th>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>Class</td>
                                        <td>${milestone.getClass_code()}</td>
                                    </tr>
                                    <tr>
                                        <td>Subject Name</td>
                                        <td>${milestone.getSubject_name()}</td>
                                    </tr>
                                    <tr>
                                        <td>Subject Code</td>
                                        <td>${milestone.getSubject_code()}</td>
                                    </tr>
                                    <tr>
                                        <td>Trainer</td>
                                        <td>${milestone.getTrainer_name()}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                function save_change() {
                    "milestone_id|class_id|iteration_id|from_date|to_date|description|status".split("|").forEach(err => $("[name='" + err + "']").removeClass("is-invalid"));
                    var wrapper = $("#__form").closest("div");
                    $(wrapper).find('[role="alert"]').remove();
                    $.ajax({
                        url: 'milestone',
                        data: $("#__form").serialize() + "&submit=submit",
                        cache: false,
                        processData: false,
                        type: 'POST',
                        success: function (data) {
//                            console.log(data);
                            if (data == "success") {
                                $(wrapper).append(
                                        '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> Milestone updated successfully</div>'
                                        );

                                setTimeout(() => {
                                    $(wrapper).find('[role="alert"]').remove();
                                }, 3000);
                            } else {
                                if (data.search("error") != -1)
                                    data.replace("error: ", "").split("|").forEach(err => $("[name='" + err + "']").addClass("is-invalid"));
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
        </script>

    </body>

</html>
