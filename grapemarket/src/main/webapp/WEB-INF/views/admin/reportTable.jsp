<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>신고 내역</title>
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
			<%@include file="include/sidebar.jsp"%>
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
											<th class="span2" style="text-align: center; margin: auto;">ID</th>
											<th class="span2" style="text-align: center; margin: auto;">TYPE</th>
											<th style="text-align: center; margin: auto;">CONTENT</th>
											<th class="span2" style="text-align: center; margin: auto;">USER</th>
											<th class="span2" style="text-align: center; margin: auto;">상세보기</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="report" items="${reports}">
											<tr>
												<td style="text-align: center; margin: auto;">${report.id}</td>
												<td style="text-align: center; margin: auto;">${report.reportType}</td>
												<td style="text-align: center; margin: auto;">${report.content}</td>
												<td style="text-align: center; margin: auto;">${report.user.username}</td>
												<td style="text-align: center; margin: auto;"><c:choose>
														<c:when test="${report.state eq 0}">
															<button class="btn" onclick="location.href = '/admin/reportDetail?id=${report.id}'">상세보기</button>
														</c:when>
														<c:otherwise>
															<button class="btn">Button</button>
														</c:otherwise>
													</c:choose></td>

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