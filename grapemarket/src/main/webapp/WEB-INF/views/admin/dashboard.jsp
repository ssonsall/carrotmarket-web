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
								<i class="icon-user"></i>  ${currentVisitorCount}
							</div>

						</div>
					</div>

					<div class="block">

						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">Statistics</div>
							<div class="pull-right">
								<span class="badge badge-warning">View More</span>

							</div>
						</div>

						<div class="block-content collapse in">
							<div class="span3">
								<div class="chart" data-percent="73">73%</div>
								<div class="chart-bottom-heading">
									<span class="label label-info">Visitors</span>

								</div>
							</div>
							<div class="span3">
								<div class="chart" data-percent="53">53%</div>
								<div class="chart-bottom-heading">
									<span class="label label-info">Page Views</span>

								</div>
							</div>
							<div class="span3">
								<div class="chart" data-percent="83">83%</div>
								<div class="chart-bottom-heading">
									<span class="label label-info">Users</span>

								</div>
							</div>
							<div class="span3">
								<div class="chart" data-percent="13">13%</div>
								<div class="chart-bottom-heading">
									<span class="label label-info">Orders</span>

								</div>
							</div>
						</div>
					</div>
					<!-- /block -->
				</div>
				<div class="row-fluid">
					<div class="span6">
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">Users</div>
								<div class="pull-right">
									<span class="badge badge-info">1,234</span>

								</div>
							</div>
							<div class="block-content collapse in">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>#</th>
											<th>First Name</th>
											<th>Last Name</th>
											<th>Username</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Mark</td>
											<td>Otto</td>
											<td>@mdo</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Jacob</td>
											<td>Thornton</td>
											<td>@fat</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Vincent</td>
											<td>Gabriel</td>
											<td>@gabrielva</td>
										</tr>
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
								<div class="muted pull-left">Orders</div>
								<div class="pull-right">
									<span class="badge badge-info">752</span>

								</div>
							</div>
							<div class="block-content collapse in">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>#</th>
											<th>Product</th>
											<th>Date</th>
											<th>Amount</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Coat</td>
											<td>02/02/2013</td>
											<td>$25.12</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Jacket</td>
											<td>01/02/2013</td>
											<td>$335.00</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Shoes</td>
											<td>01/02/2013</td>
											<td>$29.99</td>
										</tr>
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
								<div class="muted pull-left">Clients</div>
								<div class="pull-right">
									<span class="badge badge-info">17</span>

								</div>
							</div>
							<div class="block-content collapse in">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>#</th>
											<th>First Name</th>
											<th>Last Name</th>
											<th>Username</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Mark</td>
											<td>Otto</td>
											<td>@mdo</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Jacob</td>
											<td>Thornton</td>
											<td>@fat</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Vincent</td>
											<td>Gabriel</td>
											<td>@gabrielva</td>
										</tr>
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
								<div class="muted pull-left">Invoices</div>
								<div class="pull-right">
									<span class="badge badge-info">812</span>

								</div>
							</div>
							<div class="block-content collapse in">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>#</th>
											<th>Date</th>
											<th>Amount</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>02/02/2013</td>
											<td>$25.12</td>
										</tr>
										<tr>
											<td>2</td>
											<td>01/02/2013</td>
											<td>$335.00</td>
										</tr>
										<tr>
											<td>3</td>
											<td>01/02/2013</td>
											<td>$29.99</td>
										</tr>
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