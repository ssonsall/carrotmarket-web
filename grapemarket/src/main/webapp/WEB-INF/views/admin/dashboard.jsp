<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js">

<head>
<title>Admin Home Page</title>
<!-- Bootstrap -->
<link href="/AdminBoot/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/AdminBoot/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet" media="screen">
<link href="/AdminBoot/vendors/easypiechart/jquery.easy-pie-chart.css"
	rel="stylesheet" media="screen">
<link href="/AdminBoot/assets/styles.css" rel="stylesheet"
	media="screen">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
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
			<%@include file="include/sidebar.jsp"%>
			<!--/span-->
			<div class="span9" id="content">

				<div class="row-fluid">
					<!-- block -->
					<div class="block">


						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">
								<i class="icon-user"></i> ${AdminDashBoard.currentVisitorCount}
							</div>

						</div>
					</div>

				</div>
				<div class="row-fluid">
					<div class="span6">
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">Users</div>
								<div class="pull-right">
									<span class="badge badge-info">${countStatVol.memberVolume}</span>
								</div>
							</div>
							<div class="block-content collapse in">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>ID</th>
											<th>Username</th>
											<th>Address</th>
											<th>CreateDate</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="user" items="${AdminDashBoard.users }">

											<tr>
												<td>${user.id }</td>
												<td>${user.username }</td>
												<td>${user.address }</td>
												<td>${user.createDate }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- /block -->
					</div>
					<div class="span6">
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">Deals</div>
								<div class="pull-right">
									<span class="badge badge-info">${countStatVol.dealVolume}</span>

								</div>
							</div>
							<div class="block-content collapse in">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>ID</th>
											<th>Title</th>
											<th>Price</th>
										</tr>
									</thead>
									<tbody><c:forEach var="board" items="${AdminDashBoard.boards }">
										<tr>
											<td>${board.id }</td>
											<td>${board.title }</td>
											<td>${board.price }원</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- /block -->
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">Reports</div>
								<div class="pull-right">
									<span class="badge badge-info">${countStatVol.reportVolume}</span>

								</div>
							</div>
							<div class="block-content collapse in">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>ID</th>
											<th>reportType</th>
											<th>Username</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="report" items="${AdminDashBoard.reports }">
										<tr>
											<td>${report.id }</td>
											<td>${report.reportType }</td>
											<td>${report.user.username }</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- /block -->
					</div>
					<div class="span6">
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">인기검색어</div>
								
							</div>
							<div class="block-content collapse in">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>순위</th>
											<th>검색어</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="search" items="${AdminDashBoard.searchs }" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td>${search.content }</td>
										</tr>
										
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- /block -->
					</div>
				</div>

			</div>
		</div>
		<hr>
		<footer>
			<p>&copy; Vincent Gabriel 2013</p>
		</footer>
	</div>
	<!--/.fluid-container-->
	<script src="/AdminBoot/vendors/jquery-1.9.1.min.js"></script>
	<script src="/AdminBoot/bootstrap/js/bootstrap.min.js"></script>
	<script src="/AdminBoot/vendors/easypiechart/jquery.easy-pie-chart.js"></script>
	<script src="/AdminBoot/assets/scripts.js"></script>
	<script>
		$(function() {
			// Easy pie charts
			$('.chart').easyPieChart({
				animate : 1000
			});
		});
	</script>
</body>

</html>