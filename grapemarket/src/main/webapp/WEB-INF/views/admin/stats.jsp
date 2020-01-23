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
<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/charts.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>
<!-- <style>
#chartdiv {
	width: 100%;
	height: 500px;
}
</style> -->
<script>
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end

		// Create chart instance
		var chart = am4core.create("chartdiv", am4charts.XYChart);

		// Increase contrast by taking evey second color
		chart.colors.step = 2;

		// Add data
		chart.data = generateChartData();

		// Create axes
		var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
		dateAxis.renderer.minGridDistance = 50;

		// Create series
		function createAxisAndSeries(field, name, opposite, bullet) {
			var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
			if (chart.yAxes.indexOf(valueAxis) != 0) {
				valueAxis.syncWithAxis = chart.yAxes.getIndex(0);
			}

			var series = chart.series.push(new am4charts.LineSeries());
			series.dataFields.valueY = field;
			series.dataFields.dateX = "date";
			series.strokeWidth = 2;
			series.yAxis = valueAxis;
			series.name = name;
			series.tooltipText = "{name}: [bold]{valueY}[/]";
			series.tensionX = 0.8;
			series.showOnInit = true;

			var interfaceColors = new am4core.InterfaceColorSet();

			switch (bullet) {
			case "triangle":
				var bullet = series.bullets.push(new am4charts.Bullet());
				bullet.width = 12;
				bullet.height = 12;
				bullet.horizontalCenter = "middle";
				bullet.verticalCenter = "middle";

				var triangle = bullet.createChild(am4core.Triangle);
				triangle.stroke = interfaceColors.getFor("background");
				triangle.strokeWidth = 2;
				triangle.direction = "top";
				triangle.width = 12;
				triangle.height = 12;
				break;
			case "rectangle":
				var bullet = series.bullets.push(new am4charts.Bullet());
				bullet.width = 10;
				bullet.height = 10;
				bullet.horizontalCenter = "middle";
				bullet.verticalCenter = "middle";

				var rectangle = bullet.createChild(am4core.Rectangle);
				rectangle.stroke = interfaceColors.getFor("background");
				rectangle.strokeWidth = 2;
				rectangle.width = 10;
				rectangle.height = 10;
				break;
			default:
				var bullet = series.bullets.push(new am4charts.CircleBullet());
				bullet.circle.stroke = interfaceColors.getFor("background");
				bullet.circle.strokeWidth = 2;
				break;
			}

			valueAxis.renderer.line.strokeOpacity = 1;
			valueAxis.renderer.line.strokeWidth = 2;
			valueAxis.renderer.line.stroke = series.stroke;
			valueAxis.renderer.labels.template.fill = series.stroke;
			valueAxis.renderer.opposite = opposite;
		}

		createAxisAndSeries("visits", "Visits", false, "circle");
		createAxisAndSeries("views", "Views", true, "triangle");
		createAxisAndSeries("hits", "Hits", true, "rectangle");

		// Add legend
		chart.legend = new am4charts.Legend();

		// Add cursor
		chart.cursor = new am4charts.XYCursor();

		// generate some random data, quite different range
		function generateChartData() {
			var chartData = "<c:out value='${stats}'/>";

			}
			return chartData;
		}

	}); // end am4core.ready()
</script>

</head>
<body>
	<h1>${stats.visitorVolume[0].createDate}</h1>
	<c:forEach var="stat" items="${stats.visitorVolume}">
	</c:forEach>

	<input id="statsDate" type="text" value="${stats.visitorVolume}">
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
					<div class="span12">
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">Pie Chart</div>
							</div>
							<div class="block-content collapse in">
								<div class="span12" id="chartdiv" style="height: 400px"></div>
							</div>
						</div>
						<!-- /block -->
					</div>
					<div class="span6">

						<div id="chartdiv"></div>
					</div>
				</div>

				<div class="row-fluid">
					<!-- block -->
					<div class="block">

						<div id="chartdiv"></div>

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

</body>

</html>