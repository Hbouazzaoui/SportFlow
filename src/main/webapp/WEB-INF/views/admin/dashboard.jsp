<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/7/2025
  Time: 10:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin | Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">

<!-- Sidebar (You can include your sidebar here) -->
<%@ include file="../components/sidebar.jsp" %>

<!-- Main Content -->
<div class="container-fluid ms-5 me-3 my-3">
    <h1 class="h2 mb-4 text-dark fw-bold">Dashboard</h1>

    <!-- Stat Cards -->
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4 mb-4">
        <!-- Members Card -->
        <div class="col">
            <div class="card shadow-sm">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <div class="bg-primary text-white p-3 rounded-circle">
                            <i class="bi bi-people fs-4"></i>
                        </div>
                    </div>
                    <h5 class="card-title text-muted">Members</h5>
                    <p class="card-text fs-3 fw-bold text-dark"><c:out value="${requestScope.memberCount}" /></p>
                </div>
            </div>
        </div>

        <!-- Coaches Card -->
        <div class="col">
            <div class="card shadow-sm">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <div class="bg-success text-white p-3 rounded-circle">
                            <i class="bi bi-person-badge fs-4"></i>
                        </div>
                    </div>
                    <h5 class="card-title text-muted">Coaches</h5>
                    <p class="card-text fs-3 fw-bold text-dark"><c:out value="${requestScope.coachCount}" /></p>
                </div>
            </div>
        </div>

        <!-- Sessions Card -->
        <div class="col">
            <div class="card shadow-sm">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <div class="bg-warning text-white p-3 rounded-circle">
                            <i class="bi bi-calendar-event fs-4"></i>
                        </div>
                    </div>
                    <h5 class="card-title text-muted">Sessions</h5>
                    <p class="card-text fs-3 fw-bold text-dark"><c:out value="${requestScope.sessionCount}" /></p>
                </div>
            </div>
        </div>

        <!-- Enrollments Card -->
        <div class="col">
            <div class="card shadow-sm">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <div class="bg-info text-white p-3 rounded-circle">
                            <i class="bi bi-clipboard-check fs-4"></i>
                        </div>
                    </div>
                    <h5 class="card-title text-muted">Enrollments</h5>
                    <p class="card-text fs-3 fw-bold text-dark">10</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Users Table -->
    <%@ include file="../components/users_table.jsp" %>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>