<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <title>유저 게시글 목록</title>
    <!-- Bootstrap -->
    <link href="/AdminBoot/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/AdminBoot/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="/AdminBoot/assets/styles.css" rel="stylesheet" media="screen">
    <link href="/AdminBoot/assets/DT_bootstrap.css" rel="stylesheet" media="screen">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>

<body>
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

                        <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th class="span2" style="text-align: center; margin: auto;">ID</th>
                                    <th class="span8" style="text-align: center; margin: auto;">Title</th>
                                    <th class="span2" style="text-align: center; margin: auto;">State</th>
                                    <th class="span1" style="text-align: center; margin: auto;">Report</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="board" items="${boards}">
                                    <tr class="odd gradeX">
                                        <td style="text-align: center; margin: auto;">${board.id}</td>
                                        <td>${board.title}</td>
                                        <c:choose>
                                            <c:when test="${board.state eq 0}">
                                                <td style="text-align: center; margin: auto; font-weight: bold;"><button
                                                        class="btn btn-info" disabled>
                                                        <i class="icon-play icon-white"></i> 거래중
                                                    </button></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td style="text-align: center; margin: auto;"><button class="btn"
                                                        disabled>
                                                        <i class="icon-stop"></i> 거래완료
                                                    </button></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td style="text-align: center; margin: auto;"><button
                                                onclick="boardReportPopup(${board.id})" class="btn btn-danger">
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
        var popupX = (window.screen.width / 2) - (500 / 2);
        var popupY = (window.screen.height / 2) - (600 / 2);


        function boardReportPopup(id) {
            window.open("/report/boardReportForm?id=" + id + "&type=board", "a", "width=500, height=600, left=" +
                popupX + ", top=" + popupY);
        }
    </script>

    <script src="/AdminBoot/vendors/jquery-1.9.1.js"></script>
    <script src="/AdminBoot/bootstrap/js/bootstrap.min.js"></script>
    <script src="/AdminBoot/vendors/datatables/js/jquery.dataTables.min.js"></script>

    <script src="/AdminBoot/assets/scripts.js"></script>
    <script src="/AdminBoot/assets/DT_bootstrap.js"></script>
</body>

</html>