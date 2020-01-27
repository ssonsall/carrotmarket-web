<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Statistics</title>
<!-- Bootstrap -->
<link href="/AdminBoot/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/AdminBoot/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet" media="screen">
<link href="/AdminBoot/assets/styles.css" rel="stylesheet"
	media="screen">

<script src="/AdminBoot/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>
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

				<!-- morris graph chart -->
				<div class="row-fluid section">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">
								����� ��� <small>�湮�� / ������</small>
							</div>

						</div>
						<div class="block-content collapse in">
							<div class="span12">
								<div id="hero-graph" style="height: 230px;"></div>
							</div>
						</div>
					</div>
					<!-- /block -->
				</div>


				<!-- morris graph chart -->
				<div class="row-fluid section">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">
								�ŷ� ��� <small>��ü �ŷ� / �ŷ� �Ϸ�</small>
							</div>

						</div>
						<div class="block-content collapse in">
							<div class="span12">
								<div id="deal-graph" style="height: 230px;"></div>
							</div>
						</div>
					</div>
					<!-- /block -->
				</div>


				<!-- morris graph chart -->
				<div class="row-fluid section">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">ä�� ���</div>

						</div>
						<div class="block-content collapse in">
							<div class="span12">
								<div id="chat-graph" style="height: 230px;"></div>
							</div>
						</div>
					</div>
					<!-- /block -->
				</div>
				
				
				<!-- morris graph chart -->
				<div class="row-fluid section">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">�Ű� ���</div>

						</div>
						<div class="block-content collapse in">
							<div class="span12">
								<div id="report-graph" style="height: 230px;"></div>
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
	<link rel="stylesheet" href="/AdminBoot/vendors/morris/morris.css">

	<script src="/AdminBoot/vendors/jquery-1.9.1.min.js"></script>
	<script src="/AdminBoot/vendors/jquery.knob.js"></script>
	<script src="/AdminBoot/vendors/raphael-min.js"></script>
	<script src="/AdminBoot/vendors/morris/morris.min.js"></script>

	<script src="/AdminBoot/bootstrap/js/bootstrap.min.js"></script>
	<script src="/AdminBoot/vendors/flot/jquery.flot.js"></script>
	<script src="/AdminBoot/vendors/flot/jquery.flot.categories.js"></script>
	<script src="/AdminBoot/vendors/flot/jquery.flot.pie.js"></script>
	<script src="/AdminBoot/vendors/flot/jquery.flot.time.js"></script>
	<script src="/AdminBoot/vendors/flot/jquery.flot.stack.js"></script>
	<script src="/AdminBoot/vendors/flot/jquery.flot.resize.js"></script>

	<script src="/AdminBoot/assets/scripts.js"></script>
	
	<script>

		// Morris Line Chart
		var tax_data = [<c:forEach items="${Statistics.visitorVolume}" var="item"
			varStatus="status">{ "period" : "${item.createDate}", "visits" : ${item.count}, "signups" : ${Statistics.memberVolume[status.index].count}	}<c:if test="${!status.last}">, </c:if>
			</c:forEach>];
		Morris.Line({
			element : 'hero-graph',
			data : tax_data,
			xkey : 'period',
			xLabels : "month",
			ykeys : [ 'visits', 'signups' ],
			labels : [ '�湮��', '������' ],
			lineColors	:["#A854BA", "#D28BE1"]
		});

		var deal_data = [<c:forEach items="${Statistics.dealVolume}" var="item"
			varStatus="status">{ "period" : "${item.createDate}", "visits" : ${item.count}, "signups" : ${Statistics.completedDealVolume[status.index].count}	}<c:if test="${!status.last}">, </c:if>
			</c:forEach>];
		Morris.Line({
			element : 'deal-graph',
			data : deal_data,
			xkey : 'period',
			xLabels : "month",
			ykeys : [ 'visits', 'signups' ],
			labels : [ '�ŷ���', '�ŷ��Ϸᷮ' ]
		});



		var chat_data = [<c:forEach items="${Statistics.chatVolume}" var="item"
			varStatus="status">{ "period" : "${item.createDate}", "visits" : ${item.count}	}<c:if test="${!status.last}">, </c:if>
			</c:forEach>];
		Morris.Line({
			element : 'chat-graph',
			data : chat_data,
			xkey : 'period',
			xLabels : "month",
			ykeys : [ 'visits' ],
			labels : [ 'Chats'],
		lineColors	:["#FFAD1E"]
		});



		var report_data = [<c:forEach items="${Statistics.chatVolume}" var="item"
			varStatus="status">{ "period" : "${item.createDate}", "visits" : ${item.count}	}<c:if test="${!status.last}">, </c:if>
			</c:forEach>];
		Morris.Line({
			element : 'report-graph',
			data : chat_data,
			xkey : 'period',
			xLabels : "month",
			ykeys : [ 'visits' ],
			labels : [ 'Reports'],
		lineColors	:["#D72E2E"]
		});
	</script>
</body>

</html>