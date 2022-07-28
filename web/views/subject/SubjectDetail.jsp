
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
        <script>

            function save_change() {
                "subject_code|subject_name|status".split("|").forEach(err => $("[name='" + err + "']").removeClass("is-invalid"));
                var wrapper = $("#__form").closest("div");
                $(wrapper).find('[role="alert"]').remove();
                $.ajax({
                    url: 'subject',
                    data: $("#__form").serialize() + "&submit=submit" + "&subject_id=" + ${subject.subject_id},
                    cache: false,
                    processData: false,
                    type: 'POST',
                    success: function (data) {
                        // update name
                        //$("#userName").text($("[name='full_name']").val());
                        if (data == "success") {
                            $(wrapper).append(
                                    '<div class="snackbar show" role="alert"><i class="fa fa-check-circle text-success"></i> Subject information updated successfully</div>'
                                    );

                            setTimeout(() => {
                                $(wrapper).find('[role="alert"]').remove();
                            }, 3000);
                        } else {
                            if (data.search("error") != -1)
//                                console.log(data);
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
                            <li class="breadcrumb-item active"><span>Subject Details</span></li>
                        </ol>
                    </nav>
                </div>
            </header>

            <!-- Body -->
            <div class="body flex-grow-1 px-3">
                <div class="container-lg">
                    <div class="row">

                        <div class="col-xl-8">
                            <!-- Account details card-->
                            <div class="card mb-4">

                                <div class="card-header"><strong>Subject Details: ${subject.subject_name}</strong></div>
                                <div class="card-body">
                                    <form id="__form">

                                        <input type="hidden" name="go" value="edit">
                                        <div class="row gx-3 mb-3">
                                            <input type="hidden" name="subject_id" value="${subject.subject_id}">
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="subject_code">Subject Code *</label>
                                                <input class="form-control" id="subject_code" type="text" name="subject_code" value ="${subject.subject_code}" placeholder="Enter your subject code" required>
                                                <div class="invalid-feedback">
                                                    Please give a Subject's Code
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row gx-3 mb-3">
                                            <div class="col-md-12">
                                                <label class="small mb-1" for="subject_name">Subject Name *</label>
                                                <input class="form-control" id="subject_name" type="text" name="subject_name" value ="${subject.subject_name}" placeholder="Enter your subject name" required>
                                                <div class="invalid-feedback">
                                                    Please give a Subject's Name
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row gx-3 mb-3">
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputstatus">Status *</label>
                                                <div class="form-control">

                                                    <input class="form-check-input" id="status1" type="radio" name="status" value="1">
                                                    <label class="form-check-label" for="inlineRadio1">Active</label>
                                                    <input class="form-check-input" id="status2" type="radio" name="status" value="0">
                                                    <label class="form-check-label" for="inlineRadio2">Inactive</label>

                                                    <c:if test = '${feature.getStatus() == 1}'>
                                                        <script>
                                                            document.getElementById("status1").checked = true;
                                                        </script>
                                                    </c:if>

                                                    <c:if test = '${feature.getStatus() == 0}'>
                                                        <script>
                                                            document.getElementById("status2").checked = true;
                                                        </script>
                                                    </c:if>
                                                </div>

                                            </div>
                                            <div class="col-md-6">
                                                
                                                <label class="small mb-1" for="author_id">Author *</label>
                                                <select class="form-select" aria-label="Default select example" name="author_id">
                                                    <c:forEach var="i" items="${listAuthor}">
                                                        <option name="author_id" value="${i.getId()}">${i.getName()}</option>
                                                    </c:forEach>
                                                    
                                                </select>
                                            </div>

                                        </div>

                                        <div class="row gx-5 mb-4 mt-4">
                                            <!-- Save changes button-->
                                            <div class="col-md-1">
                                                <input class="btn btn-outline-info" type="reset">
                                            </div>
                                            <div class="col-md-11">
                                                <button id="submit" name="submit" class="btn btn-primary" type="button" onclick="save_change()">Save changes</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4">
                            <table border="0" class="table table-dark table-striped">

                                <tbody>
                                    <tr>
                                        <th>INFORMATION</th>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>Subject</td>
                                        <td>${subject.subject_code} - ${subject.subject_name}</td>
                                    </tr>
                                    <tr>
                                        <td>Author</td>
                                        <td>${subject.full_name} (${subject.gender})</td>
                                    </tr>
                                    <tr>
                                        <td>Contact</td>
                                        <td>${subject.email}</td>
                                    </tr>
                                    <tr>
                                        <td>Phone</td>
                                        <td>${subject.mobile}</td>
                                    </tr>
                                    <tr>
                                        <td>Status</td>
                                        <td>
                                            <c:if test="${subject.status==1}">
                                                <div class="form-check form-switch">
                                                    <input class="form-check-input" id="flexSwitchCheckCheckedDisabled" type="checkbox" checked disabled>
                                                    <label class="form-check-label" for="flexSwitchCheckCheckedDisabled">Active</label>
                                                </div>
                                            </c:if>
                                            <c:if test="${subject.status==0}">
                                                <div class="form-check form-switch">
                                                    <input class="form-check-input" id="flexSwitchCheckDisabled" type="checkbox" disabled>
                                                    <label class="form-check-label" for="flexSwitchCheckDisabled">Inactive</label>
                                                </div>
                                            </c:if>
                                        </td>
                                    </tr>

                                </tbody>
                            </table>
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
        </script>

    </body>

</html>
