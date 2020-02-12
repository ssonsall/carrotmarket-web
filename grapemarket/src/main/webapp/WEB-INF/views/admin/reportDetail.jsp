<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>신고 상세내용</title>
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
	display: none;
	/* Hidden by default */
	position: fixed;
	/* Stay in place */
	z-index: 10;
	/* Sit on top */
	left: 0;
	top: 0;
	width: 100%;
	/* Full width */
	height: 100%;
	/* Full height */
	overflow: auto;
	/* Enable scroll if needed */
	background-color: rgb(0, 0, 0);
	/* Fallback color */
	background-color: rgba(0, 0, 0, 0.4);
	/* Black w/ opacity */
}

/* Modal Content/Box */
.modal-box {
	background-color: #fefefe;
	margin: 15% auto;
	/* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #888;
	width: 30%;
	/* Could be more or less, depending on screen size */
}
</style>

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
								${AdminReportDetail.reportType.user.role} </span> ] 입니다.
						</p>
						<p>계정을 제재하시겠습니까?</p>
					</div>
				</div>
			</div>
			<hr>
			<div style="width: auto;">
				<c:if test="${AdminReportDetail.reportType.user.role eq 'USER' }">
					<button class="btn btn-warning"
						onclick="changeRoleToCaution1(${AdminReportDetail.reportType.user.id},${AdminReportDetail.report.id })">경고1</button>

				</c:if>
				<c:if
					test="${AdminReportDetail.reportType.user.role eq 'USER' or AdminReportDetail.reportType.user.role eq'CAUTION1' }">
					<button class="btn btn-danger"
						onclick="changeRoleToCaution2(${AdminReportDetail.reportType.user.id},${AdminReportDetail.report.id })">경고2</button>

				</c:if>
				<button class="btn btn-inverse"
						onclick="changeRoleToBan(${AdminReportDetail.reportType.user.id},${AdminReportDetail.report.id })">정지</button>
				<button class="btn btn-inverse"
						onclick="deleteReport(${AdminReportDetail.reportType.user.id},${AdminReportDetail.report.id })">취소</button>
				<button class="btn" style="float: right" onclick="closeModal()">X</button>
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
			<div class="span7" id="content">

				<div class="row-fluid">
					<!-- block -->
					<div class="block">
						<div class="navbar navbar-inner block-header">
							<div class="muted pull-left">신고 상세보기</div>
						</div>
						<div class="block-content collapse in">
							<div class="span12">


								<table class="table table-striped table-bordered"
									style="vertical-align: none;">
									<thead>
										<tr>
											<th class="span3" style="text-align: center; margin: auto;">-</th>
											<th style="text-align: center; margin: auto;">-</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th>신고 번호</th>
											<td>${AdminReportDetail.report.id}</td>
										</tr>
										<tr>
											<th></th>
											<td></td>
										</tr>
										<tr>
											<th>신고자 ID</th>
											<td>${AdminReportDetail.report.user.id}</td>
										</tr>
										<tr>
											<th>신고자</th>
											<td>${AdminReportDetail.report.user.username}</td>
										</tr>
										<tr>
											<th>신고내용</th>
											<td>${AdminReportDetail.report.content}</td>
										</tr>
										<tr>
											<th></th>
											<td></td>
										</tr>
										<tr>
											<th>피신고자 ID</th>
											<td>${AdminReportDetail.reportType.user.id}</td>
										</tr>
										<tr>
											<th>피신고자</th>
											<td>${AdminReportDetail.reportType.user.username}</td>
										</tr>
										<tr>
											<th>피신고자 등급</th>
											<td>${AdminReportDetail.reportType.user.role}</td>
										</tr>
										<tr>
											<th>게시글 유형</th>
											<td>${AdminReportDetail.report.reportType}</td>
										</tr>
										<tr>
											<th></th>
											<td></td>
										</tr>
										<!-- 보드의 경우 -->
										<c:choose>
											<c:when test="${AdminReportDetail.report.reportType eq 'board'}">
												<tr>
													<th>제목</th>
													<td>${AdminReportDetail.reportType.title}<a
														href="/board/detail/${AdminReportDetail.reportType.id}"> [해당 게시글로 이동하기]</a></td>
												</tr>
												<tr>
													<th>내용</th>
													<td>${AdminReportDetail.reportType.content}</td>
												</tr>
												<tr>
													<th>사진</th>
													<td><img style="width: 50px; height: 50px;"
														src="/home/ubuntu/grapemarket-web/grapemarket/src/main/resources/static/upload/${AdminReportDetail.reportType.image1}" alt=" No Image" />
														</li> <c:if test="${!empty AdminReportDetail.reportType.image2}">
															<li><img style="width: 50px; height: 50px;"
																src="/home/ubuntu/grapemarket-web/grapemarket/src/main/resources/static/upload/${AdminReportDetail.reportType.image2}" alt=" No Image" /></li>
														</c:if> <c:if test="${!empty AdminReportDetail.reportType.image3}">
															<li><img style="width: 50px; height: 50px;"
																src="/home/ubuntu/grapemarket-web/grapemarket/src/main/resources/static/upload/${AdminReportDetail.reportType.image3}" alt=" No Image" /></li>
														</c:if> <c:if test="${!empty AdminReportDetail.reportType.image4}">
															<li><img style="width: 50px; height: 50px;"
																src="/home/ubuntu/grapemarket-web/grapemarket/src/main/resources/static/upload/${AdminReportDetail.reportType.image4}" alt=" No Image" /></li>
														</c:if> <c:if test="${!empty AdminReportDetail.reportType.image5}">
															<li><img style="width: 50px; height: 50px;"
																src="/home/ubuntu/grapemarket-web/grapemarket/src/main/resources/static/upload/${AdminReportDetail.reportType.image5}" alt=" No Image" /></li>
														</c:if></td>
												</tr>
											</c:when>
											<c:when test="${AdminReportDetail.report.reportType eq 'comment'}">
												<tr>
													<th>내용</th>
													<td>${AdminReportDetail.reportType.content}<a
														href="/board/detail/${AdminReportDetail.reportType.board.id}"> [해당 게시글로
															이동하기]</a></td>
												</tr>
											</c:when>

											<c:when test="${AdminReportDetail.report.reportType eq 'message'}">
												<tr>
													<th>내용</th>
													<td>${AdminReportDetail.reportType.message}<a
														href="/admin/chatLog?id=${AdminReportDetail.reportType.chat.id}&reportId=${AdminReportDetail.report.id}">
															[해당 게시글로 이동하기]</a></td>
												</tr>
											</c:when>

										</c:choose>

										<tr>
											<th></th>
											<td></td>
										</tr>



									</tbody>
								</table>
								<div class="table-toolbar">

									<div class="btn-group pull-right">
										<button id="user_delete" class="btn btn-danger"
											onclick="openModal()">
											<i class="icon-remove icon-white"></i> 계정 제재
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
        function openModal() {
            $("#modal").show();
        };

        function closeModal() {
            $('.searchModal').hide();
        };

        function userDelete(id) {
            if (confirm("해당 유저를 삭제하시겠습니까?") == true) { //확인
                location.href = '/admin/userDelete/' + id;
            } else { //취소
                return;
            }
        }

        function changeRoleToCaution1(id, reportId) {
            if (confirm("해당 계정을 '경고 1' 조치하시겠습니까? ") == true) { //확인
                location.href = '/admin/restriction?id=' + id + '&sort=caution1&reportId='+reportId;
            } else { //취소
                alert("취소하였습니다.");
            }
        }

        function changeRoleToCaution2(id, reportId) {
            if (confirm("해당 계정을 '경고 2' 조치하시겠습니까? ") == true) { //확인
                location.href = '/admin/restriction?id=' + id + '&sort=caution2&reportId='+reportId;
            } else { //취소
                alert("취소하였습니다.");
            }
        }

        function changeRoleToBan(id, reportId) {
            if (confirm("해당 계정을 '정지' 조치하시겠습니까? ") == true) { //확인
                location.href = '/admin/restriction?id=' + id + '&sort=ban&reportId='+reportId;
            } else { //취소
                alert("취소하였습니다.");
            }

        }
        function deleteReport(id, reportId) {
            if (confirm("해당 신고를 취소하시겠습니까? ") == true) { //확인
                location.href = '/admin/restriction?id=' + id + '&sort=deleteReport&reportId='+reportId;
            } else { //취소
                alert("취소하였습니다.");
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
