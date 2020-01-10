<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Tables</title>
<!-- Bootstrap -->
<link href="/AdminBoot/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/AdminBoot/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet" media="screen">
<link href="/AdminBoot/assets/styles.css" rel="stylesheet"
	media="screen">
<link href="/AdminBoot/assets/DT_bootstrap.css" rel="stylesheet"
	media="screen">
<script src="/AdminBoot/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</head>

<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">Admin Panel</a>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3" id="sidebar">
				<ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
					<li><a href="index.html"><i class="icon-chevron-right"></i>
							Dashboard</a></li>
					<li><a href="stats.html"><i class="icon-chevron-right"></i>
							Statistics</a></li>
					<li class="active"><a href="tables.html"><i
							class="icon-chevron-right"></i> User</a></li>
					<li><a href="buttons.html"><i class="icon-chevron-right"></i>
							Buttons & Icons</a></li>
					<li><a href="interface.html"><i class="icon-chevron-right"></i>
							UI & Interface</a></li>
					<li><a href="#"><span class="badge badge-info pull-right">1,234</span>
							Users</a></li>
					<li><a href="#"><span
							class="badge badge-success pull-right">731</span> Product</a></li>
					<li><a href="#"><span
							class="badge badge-success pull-right">812</span> Deal</a></li>
					<li><a href="#"><span
							class="badge badge-warning pull-right">4,231</span> Chats</a></li>
					<li><a href="#"><span
							class="badge badge-important pull-right">83</span> Report</a></li>
				</ul>
			</div>
			<!--/span-->
			<div class="span9" id="content">

				<div class="row-fluid">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">Bootstrap dataTables with
								Toolbar</div>
						</div>
						<div class="block-content collapse in">
							<div class="span12">
								<div class="table-toolbar">
									<div class="btn-group">
										<a href="#"><button class="btn btn-success">
												Add New <i class="icon-plus icon-white"></i>
											</button></a>
									</div>
									<div class="btn-group pull-right">
										<button data-toggle="dropdown" class="btn dropdown-toggle">
											Tools <span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li><a href="#">Print</a></li>
											<li><a href="#">Save as PDF</a></li>
											<li><a href="#">Export to Excel</a></li>
										</ul>
									</div>
								</div>
								<table class="table table-striped table-bordered"
									style="vertical-align: none;" id="example2">
									<thead>
										<tr>
											<th style="text-align: center; margin: auto;">ID</th>
											<th style="text-align: center; margin: auto;">USERNAME</th>
											<th style="text-align: center; margin: auto;">PROVIDER</th>
											<th style="text-align: center; margin: auto;">ROLE</th>
											<th class="span2" style="text-align: center; margin: auto;">Detail</th>
											<th class="span2" style="text-align: center; margin: auto;">Edit</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="user" items="${users}">
											<tr>
												<td style="text-align: center; margin: auto;">${user.id}</td>

												<c:choose>
													<c:when test="${user.provider eq 'local'}">
														<td style="">${user.username}</td>
														<td
															style="text-align: center; margin: auto; background-color: #d9edf7">${user.provider}</td>

													</c:when>
													<c:otherwise>
														<td>${user.email}</td>
														<td
															style="text-align: center; margin: auto; background-color: #dff0d8;">${user.provider}</td>

													</c:otherwise>
												</c:choose>

												<td style="text-align: center; margin: auto;">${user.role}</td>
												<td style="text-align: center; margin: auto;"><button
														class="btn btn-inverse">
														<i class="icon-shopping-cart icon-white"></i> Detail
													</button></td>
												<td style="text-align: center; margin: auto;"><button
														class="btn btn-primary" onclick="location.href = '/admin/detail/${user.id}'">
														<i class="icon-pencil icon-white"></i> Edit
													</button></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- /block -->
				</div>
			</div>
		</div>
		<hr>
		<footer>
			<p>&copy; Vincent Gabriel 2013</p>
		</footer>
	</div>
	<!--/.fluid-container-->

	<script src="/AdminBoot/vendors/jquery-1.9.1.js"></script>
	<script src="/AdminBoot/bootstrap/js/bootstrap.min.js"></script>
	<script src="/AdminBoot/vendors/datatables/js/jquery.dataTables.min.js"></script>

	<script src="/AdminBoot/assets/scripts.js"></script>
	<script src="/AdminBoot/assets/DT_bootstrap.js"></script>
</body>

</html>