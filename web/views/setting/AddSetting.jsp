<%@page import="java.util.Vector"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : Dashboard
    Created on : May 19, 2022, 4:22:11 PM
    Author     : RxZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <style>
            .form-label{font-weight: bold }
        </style>
    </head>

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
                    <div class="row">
                        <div class="col-12">
                            <div class="card mb-4">
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="card mb-4">
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="card mb-4">

                                <form method="POST" action="setting">
                                    <input hidden="" name="action" value="add">
                                    <c:if test="${not empty message}">
                                        <h6 style="color: #00aced">${message}</h6>
                                    </c:if>
                                    <div class="card-header"><strong>ADD SETTING</strong></div>
                                    <div class="card-body">
                                        <div class="example">
                                            <div class="example">
                                                <div class="tab-content rounded-bottom">
                                                    <div class="tab-pane p-3 active preview" role="tabpanel" id="preview-1182">
                                                        <div class="row g-3">
                                                            <div class="row mt-3">
                                                                <div class="col">
                                                                    <label class="form-label" for="type">Group*</label>
                                                                    <select class="form-select" name="type">
                                                                        <%

                                                                            String name = "";
                                                                            Vector<String> vec = (Vector<String>) request.getAttribute("list");
                                                                            for (String s : vec) {
                                                                                name = (String) session.getAttribute(s);
                                                                        %>
                                                                        <option value="<%=s%>"><%=name%></option>
                                                                        <%}%>
                                                                    </select>
                                                                </div>
                                                                <div class="col">
                                                                    <label class="form-label" for="order" required>Order*</label>
                                                                    <input class="form-control" name="order" type="text" required>

                                                                </div>
                                                            </div>
                                                            <div class="row mt-3">
                                                                <div class="col">
                                                                    <label class="form-label" for="name" required>Name*</label>
                                                                    <input class="form-control" name="name" type="text" required>
                                                                </div>
                                                            </div>
                                                            <div class="row mt-3">
                                                                <div class="col">
                                                                    <label class="form-label" for="value" required>Value</label>
                                                                    <input class="form-control" name="value" type="text" required>
                                                                </div>

                                                                <div class="col">
                                                                    <label class="form-label" for="status" required>Status</label><br>
                                                                    <div class="mt-2">
                                                                        <input type="radio" name="status" value="1"  /><span class="span-active" style=" margin-right: 20px">Active</span>
                                                                        <input class="mr-3" type="radio" name="status" value="0" /><span>Inactive</span>
                                                                    </div>
                                                                </div>
                                                                <div class="row mt-4">
                                                                    <div class="col" style="margin-top: 20px">
                                                                        <button style="background-color: #303c54" class="btn btn-primary" name="submit" type="submit">Add</button>
                                                                    </div>
                                                                    <div class="col" style="text-align: right">
                                                                        <button onclick="myFunction()" style="border: none; background-color: #f9fafa" >

                                                                            <a style="text-decoration: none; color: #000000" href="setting">Back</a> 
                                                                        </button>
                                                                    </div>
                                                                </div>


                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>   
                                    </div>
                                </form>                                      
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
                <script>
                                                                            function myFunction() {
                                                                                alert("Do you really want to back?");
                                                                            }
                </script>

                </body>

                </html>
