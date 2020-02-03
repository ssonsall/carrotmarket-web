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

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<style>
/* The Modal (background) */
.searchModal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 10; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}
/* Modal Content/Box */
.modal-box {
	background-color: #fefefe;
	margin: 15% auto; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #888;
	width: 30%; /* Could be more or less, depending on screen size */
}
</style>
<script>
function openModal() {
$("#modal").show();
};
function closeModal() {
$('.searchModal').hide();
};
</script>
</head>

<body>
	<div id="modal" class="searchModal">
		<div class="modal-box">
			<div class="modal-header">
				<h1>권한 변경</h1>
			</div>
			<div class="col-sm-6">
				<div class="modal_content" style="margin: 10%">
					<div>
						<p>
							현재 유저의 권한은 [ <span style="font-style: italic; font-weight: bold;">
								${user.role} </span> ] 입니다.
						</p>
						<p>유저 권한을 변경하시겠습니까?</p>
					</div>
				</div>
			</div>
			<hr>

			<div style="width: auto;">
				<button class="btn btn-danger"
					onclick="changeRoleToAdmin(${user.id},'${user.role}')">ADMIN
					으로 변경</button>
				<button class="btn btn-info"
					onclick="changeRoleToUser(${user.id},'${user.role}')">USER
					로 변경</button>
				<button class="btn" style="float: right" onclick="closeModal()">CANCLE</button>
			</div>
		</div>
	</div>


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
			<!-- block -->
			<div class="block">
				<div class="navbar navbar-inner block-header">
					<div class="muted pull-left">
						<span style="font-weight: bold; color: black">${user.username}</span>
						's Board List
					</div>
				</div>
				<div class="block-content collapse in">
					<div class="span12">

						<table cellpadding="0" cellspacing="0" border="0"
							class="table table-striped table-bordered">
							<thead>
								<tr>
									<th class="span2" style="text-align: center; margin: auto;">ID</th>
									<th class="span8" style="text-align: center; margin: auto;">신고자</th>
									<th class="span8" style="text-align: center; margin: auto;">피신고자</th>
									<th class="span1" style="text-align: center; margin: auto;">Report</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="message" items="${messages}">
									<tr class="odd gradeX">
										<td style="text-align: center; margin: auto;">${message.id}</td>
										<td>
										<c:if test="${message.user.id eq report.user.id }">
										${message.message }
										</c:if>
										</td>
										<td><c:if test="${message.user.id ne report.user.id }">
										${message.message }
										</c:if></td>

										<td style="text-align: center; margin: auto;"><button
												onclick="messageReportPopup(${message.id})"
												class="btn btn-danger">
												<i class="icon-bullhorn icon-white"></i>
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

		<hr>
		<footer>
			<p>&copy; Vincent Gabriel 2013</p>
		</footer>
	</div>
	<script type="text/javascript">
		function userDelete(id) {
			if (confirm("해당 유저를 삭제하시겠습니까?") == true) { //확인
				location.href = '/admin/userDelete/'+id;
			} else { //취소
				return;
			}
		}

		function changeRoleToAdmin(id, role) {
				if(role == 'USER'){
					if (confirm("해당 유저의 권한을 어드민으로 변경하시겠습니까?") == true) { //확인
					location.href = '/admin/changeRole/'+id+'/ADMIN';
					} else { //취소
					alert("권한변경을 취소하였습니다.");
					}
				}else{
					alert("현재 유저의 권한이 어드민입니다. ");
					}
			}
		function changeRoleToUser(id, role) {
			if(role == 'ADMIN'){
				if (confirm("해당 유저의 권한을 유저로 변경하시겠습니까?") == true) { //확인
					location.href = '/admin/changeRole/'+id+'/USER';
				} else { //취소
				alert("권한변경을 취소하였습니다.");
				}
			}else{
				alert("현재 유저의 권한이 유저입니다. ");
				}
			
		}
	</script>
	<script type="text/javascript">
	    var popupX = (window.screen.width / 2) - (500 / 2);
	    var popupY = (window.screen.height / 2) - (600 / 2);
	    	function c(id) {
	            window.open("/report/boardReportForm?id=" + id + "&type=message", "a", "width=500, height=600, left="+popupX+", top="+popupY);
	        }
    </script>

	<script src="/AdminBoot/vendors/jquery-1.9.1.js"></script>
	<script src="/AdminBoot/bootstrap/js/bootstrap.min.js"></script>
	<script src="/AdminBoot/vendors/datatables/js/jquery.dataTables.min.js"></script>

	<script src="/AdminBoot/assets/scripts.js"></script>
	<script src="/AdminBoot/assets/DT_bootstrap.js"></script>
</body>

</html>