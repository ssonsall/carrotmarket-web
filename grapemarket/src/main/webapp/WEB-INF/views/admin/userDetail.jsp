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
			<%@include file="include/sidebar.jsp"%>
			<!--/span-->
			<div class="span9" id="content">

				<div class="row-fluid">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">USER INFO</div>
						</div>
						<div class="block-content collapse in">
							<div class="span12">


								<table class="table table-striped table-bordered"
									style="vertical-align: none;">
									<thead>
										<tr>
											<th class="span2" style="text-align: center; margin: auto;">TITLE</th>
											<th style="text-align: center; margin: auto;">CONTENT</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th>ID</th>
											<td>${user.id}</td>
										</tr>
										<tr>
											<th>ROLE</th>
											<td>${user.role}
												<button class="btn btn-inverse" onclick="openModal()">
													<i class="icon-refresh icon-white"></i> Update
												</button>
											</td>
										</tr>
										<tr>
											<th>USERNAME</th>
											<td>${user.username}</td>
										</tr>
										<tr>
											<th>NAME</th>
											<td>${user.name}</td>
										</tr>
										<tr>
											<th>EMAIL</th>
											<td>${user.email}</td>
										</tr>
										<tr>
											<th>PROFILE</th>
											<td>${user.userProfile}</td>
										</tr>
										<tr>
											<th>ADDRESS</th>
											<td>${user.address}</td>
										</tr>
										<tr>
											<th>ADD AUTH</th>
											<td>${user.addressAuth}</td>
										</tr>
										<tr>
											<th>PROVIDER</th>
											<td>${user.provider}</td>
										</tr>
										<tr>
											<th>PROVID ID</th>
											<td>${user.providerId}</td>
										</tr>
										<tr>
											<th>CREATE DATE</th>
											<td>${user.createDate}</td>
										</tr>
										<tr>
											<th>UPDATE DATE</th>
											<td>${user.updateDate}</td>
										</tr>
									</tbody>
								</table>
								<div class="table-toolbar">

									<div class="btn-group pull-right">
										<button id="user_delete" class="btn btn-danger"
											onclick="userDelete(${user.id})">
											<i class="icon-remove icon-white"></i> Delete
										</button>
									</div>
								</div>
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
					alert("해당 유저를 어드민으로 변경할 수 없습니다.");
					}
			}
		function changeRoleToUser(id, role) {
			 if(role == 'USER'){
				alert("현재 유저의 권한이 유저입니다.");
				} else {
					if (confirm("해당 유저의 권한을 유저로 변경하시겠습니까?") == true) { //확인
						location.href = '/admin/changeRole/'+id+'/USER';
					} else { //취소
					alert("권한변경을 취소하였습니다.");
					}
				} 
			
			
		}
	</script>

	<script src="/AdminBoot/vendors/jquery-1.9.1.js"></script>
	<script src="/AdminBoot/bootstrap/js/bootstrap.min.js"></script>
	<script src="/AdminBoot/vendors/datatables/js/jquery.dataTables.min.js"></script>

	<script src="/AdminBoot/assets/scripts.js"></script>
	<script src="/AdminBoot/assets/DT_bootstrap.js"></script>
</body>

</html>