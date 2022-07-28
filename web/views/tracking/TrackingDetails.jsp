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
                            <li class="breadcrumb-item active"><span>Tracking Details</span></li>
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
                                <div class="card-header">Tracking Details - ${tracking.getTracking_id()}</div>                        

                                <div class="card-body">
                                    <form id="__form">
                                        <input class="form-control" type="hidden" name="go" value="edit" readonly="">
                                        
                                        <input class="form-control" id="inputMilestoneID" type="hidden" name="milestone_id" value="${tracking.getMilestone_id()}" readonly="">
                                        <input class="form-control" id="inputTrackingID" type="hidden" name="tracking_id" value="${tracking.getTracking_id()}" readonly="">

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (first name)-->
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="inputMilestoneName">Milestone Name (*)</label>
                                                <select class="form-select form-select-sm" id="inputMilestoneName" name="milestone_name" aria-label=".form-select-sm example">
                                                    <c:forEach var="item" items="${milestones}">
                                                        <c:if test="${tracking.getMilestone_id() == item.getMilestone_id()}">
                                                            <option selected value="${item.getMilestone_id()}">${item.getMilestone_name()} </option>
                                                        </c:if>
                                                        <c:if test="${tracking.getMilestone_id() != item.getMilestone_id()}">
                                                            <option value="${item.getMilestone_id()}">${item.getMilestone_name()}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <div class="invalid-feedback">
                                                    Please choose an Milestone.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (first name)-->
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="inputFuntionName">Function Name (*)</label>
                                                <select class="form-select form-select-sm" id="inputFuntionName" name="funtion_name" aria-label=".form-select-sm example">
                                                    <c:forEach var="item" items="${funtions}">
                                                        <c:if test="${tracking.getFunction_id() == item.getId()}">
                                                            <option selected value="${item.getId()}">${item.getName()} </option>
                                                        </c:if>
                                                        <c:if test="${tracking.getFunction_id() != item.getId()}">
                                                            <option value="${item.getId()}">${item.getName()}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <div class="invalid-feedback">
                                                    Please choose an Function.
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (first name)-->
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="inputAssigneeName">Assignee Name (*)</label>
                                                <select class="form-select form-select-sm" id="inputAssigneeName" name="assignee_name" aria-label=".form-select-sm example">
                                                    <c:forEach var="item" items="${assignees}">
                                                        <c:if test="${tracking.getAssignee_id() == item.getUser_id()}">
                                                            <option selected value="${item.getUser_id()}">${item.getFull_name()} </option>
                                                        </c:if>
                                                        <c:if test="${tracking.getAssignee_id() != item.getUser_id()}">
                                                            <option value="${item.getUser_id()}">${item.getFull_name()}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <div class="invalid-feedback">
                                                    Please choose an Assignee.
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (first name)-->
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="inputAssignerName">Assigner Name (*)</label>
                                                <select class="form-select form-select-sm" id="inputAssignerName" name="assigner_name" aria-label=".form-select-sm example">
                                                    <c:forEach var="item" items="${assigners}">
                                                        <c:if test="${tracking.getAssigner_id() == item.getUser_id()}">
                                                            <option selected value="${item.getUser_id()}">${item.getFull_name()} </option>
                                                        </c:if>
                                                        <c:if test="${tracking.getAssigner_id() != item.getUser_id()}">
                                                            <option value="${item.getUser_id()}">${item.getFull_name()}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <div class="invalid-feedback">
                                                    Please choose an Assigner.
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (first name)-->
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="inputStatus">Status (*)</label>
                                                <select class="form-select form-select-sm" id="inputStatus" name="status_name" aria-label=".form-select-sm example">
                                                    
                                                            <option selected value="1">Planned </option>
                                                            <option value="2">Analysed</option>
                                                            <option value="3">Designed</option>
                                                            <option value="4">Coded</option>
                                                            <option value="5">Integrated</option>
                                                            <option value="6">Submitted</option>
                                                            <option value="7">Evaluated</option>
                                                            <option value="8">Rejected</option>
                                                  
                                                </select>
                                                <div class="invalid-feedback">
                                                    Please choose an Status
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-12">
                                                <label class="form-label" for="inputTrackingNote">Note</label>
                                                <textarea class="form-control" id="inputTrackingNote" name="note_name" rows="3" >${tracking.getTracking_note()}</textarea>
                                                <div class="invalid-feedback">
                                                    Note is too long.
                                                </div>
                                            </div>
                                        </div>

                                        <c:if test="${sessionScope.role_id == 4}">
                                            <button class="btn btn-outline-info" type="reset">Reset</button>
                                            <button id="submit" class="btn btn-primary" type="button" onclick="save_change()">Save changes</button>
                                        </c:if>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <c:if test="${sessionScope.role_id != 4}">
                            <script>
                                document.getElementById("inputMilestoneName").setAttribute("disabled", "true");
                                document.getElementById("inputFuntionName").setAttribute("disabled", "true");
                                document.getElementById("inputAssigneeName").setAttribute("disabled", "true");
                                document.getElementById("inputAssignerName").setAttribute("disabled", "true");
                                document.getElementById("inputStatus").setAttribute("disabled", "true");
                                document.getElementById("inputTrackingNote").setAttribute("disabled", "true");
                               
                            </script>
                        </c:if>

                    </div>
                </div>
            </div>
            <script>
                function save_change() {
                    "milestone_id|funtion_name|assignee_name|assigner_name|status_name|note_name".split("|").forEach(err => $("[name='" + err + "']").removeClass("is-invalid"));
                    var wrapper = $("#__form").closest("div");
                    $(wrapper).find('[role="alert"]').remove();
                    $.ajax({
                        url: 'tracking',
                        data: $("#__form").serialize() + "&submit=submit",
                        cache: false,
                        processData: false,
                        type: 'POST',
                        success: function (data) {
//                            console.log(data);
                            if (data == "success") {
                                $(wrapper).append(
                                        '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> Tracking updated successfully</div>'
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
