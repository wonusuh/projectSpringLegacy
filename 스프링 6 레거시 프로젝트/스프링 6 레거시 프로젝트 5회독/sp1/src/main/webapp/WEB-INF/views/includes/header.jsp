<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Panel</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

  <!-- Top Navbar -->
  <nav class="navbar navbar-expand-lg navbar-custom px-4">
    <a class="navbar-brand" href="#">AdminPanel</a>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" href="#">Dashboard</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Users</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Settings</a></li>
      </ul>
      <span class="navbar-text">
        Logged in as <strong>admin</strong> | <a href="#" class="text-white text-decoration-underline">Logout</a>
      </span>
    </div>
  </nav>

  <!-- Sidebar + Content -->
  <div class="main-wrapper">
    <!-- Sidebar -->
    <div class="sidebar pt-3">
      <div class="list-group list-group-flush">
        <a href="#" class="list-group-item active">Dashboard</a>
        <a href="#" class="list-group-item">Analytics</a>
        <a href="#" class="list-group-item">User List</a>
        <a href="#" class="list-group-item">Roles</a>
        <a href="#" class="list-group-item">System Logs</a>
      </div>
    </div>

    <!-- Main Content -->
    <div class="content">