<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <title>Tables</title>
    <!-- Bootstrap -->
    <link href="/AdminBoot/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/AdminBoot/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="/AdminBoot/assets/styles.css" rel="stylesheet" media="screen">
    <link href="/AdminBoot/assets/DT_bootstrap.css" rel="stylesheet" media="screen">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
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
                                ${reportType.user.role} </span> ] 입니다.
                        </p>
                        <p>계정을 제재하시겠습니까?</p>
                    </div>
                </div>
            </div>
            <hr>

            <div style="width: auto;">
                <button class="btn btn-warning"onclick="changeRoleToCaution1(${reportType.user.id})">경고1</button>
                <button class="btn btn-danger"" onclick="changeRoleToCaution2(${reportType.user.id})">경고2</button>
                <button class="btn btn-inverse" onclick="changeRoleToBan(${reportType.user.id})">정지</button>
                <button class="btn" style="float: right" onclick="closeModal()">CANCLE</button>
            </div>
        </div>
    </div>


    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span
                        class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
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
                            <div class="muted pull-left">REPORT DETAIL</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">


                                <table class="table table-striped table-bordered" style="vertical-align: none;">
                                    <thead>
                                        <tr>
                                            <th class="span3" style="text-align: center; margin: auto;">TITLE</th>
                                            <th style="text-align: center; margin: auto;">CONTENT</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th>REPORT ID</th>
                                            <td>${report.id}</td>
                                        </tr>
                                        <tr>
											<th>USERNAME</th>
											<td>${reportType.user.username}</td>
										</tr> 
                                        <tr>
											<th>ROLE</th>
											<td>${reportType.user.role}</td>
										</tr> 
                                        <tr>
                                            <th>TYPE</th>
                                            <td>${report.reportType}</td>
                                        </tr>
                                        <tr>
                                            <th>CONTENT</th>
                                            <td>${report.content}</td>
                                        </tr>
                                        <tr>
                                            <th>REPORTER</th>
                                            <td>${report.user.username}</td>
                                        </tr>

                                    </tbody>
                                </table>
                                <div class="table-toolbar">

                                    <div class="btn-group pull-right">
                                        <button id="user_delete" class="btn btn-danger" onclick="openModal()">
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

        function changeRoleToCaution1(id) {
            if (confirm("해당 계정을 '경고 1' 조치하시겠습니까? ") == true) { //확인
                location.href = '/admin/restriction?id=' + id + '&sort=caution1';
            } else { //취소
                alert("취소하였습니다.");
            }
        }

        function changeRoleToCaution2(id) {
            if (confirm("해당 계정을 '경고 2' 조치하시겠습니까? ") == true) { //확인
                location.href = '/admin/restriction?id=' + id + '&sort=caution2';
            } else { //취소
                alert("취소하였습니다.");
            }
        }

        function changeRoleToBan(id) {
            if (confirm("해당 계정을 '정지' 조치하시겠습니까? ") == true) { //확인
                location.href = '/admin/restriction?id=' + id + '&sort=ban';
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
