<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html lang="en-US" dir="ltr">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--  
    Document Title
    =============================================
    -->
<title>detail</title>

<%@include file="../include/favicons.jsp"%>
<%@include file="../include/stylesheets.jsp"%>

</head>

<body data-spy="scroll" data-target=".onpage-navigation"
	data-offset="60">
	<main>
		<div class="page-loader">
			<div class="loader">Loading...</div>
		</div>
		<div class="main">
			<section class="module" id="contact"
				style="padding-top: 30px; padding-bottom: 0px;">
				<div class="container">
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<h2 class="module-title font-alt">${ReportFormData.type}신고</h2>
							<div class="module-subtitle font-serif"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<form id="writeFormSubmit" action="/report/androidReportProc"
								method="post" enctype="multipart/form-data">
								<input type="hidden" name="state" value="0"> <input
									type="hidden" name="reportType" value="${ReportFormData.type}">
								<input type="hidden" name="reportId"
									value="${ReportFormData.object.id}">
								<div class="form-group">
									<c:choose>
										<c:when test="${ReportFormData.type eq 'board'}">
											<input class="form-control" type="text" id="title"
												name="title" placeholder="제목*" required="required"
												data-validation-required-message="Please enter your name."
												value="${ReportFormData.object.title}" readonly="readonly" />
										</c:when>
										<c:when test="${ReportFormData.type eq 'comment'}">
											<input class="form-control" type="text" id="title"
												name="title" placeholder="제목*" required="required"
												data-validation-required-message="Please enter your name."
												value="${ReportFormData.object.content}" readonly="readonly" />
										</c:when>
										<c:otherwise>
											<input class="form-control" type="text" id="title"
												name="title" placeholder="제목*" required="required"
												data-validation-required-message="Please enter your name."
												value="${ReportFormData.object.message}" readonly="readonly" />
										</c:otherwise>
									</c:choose>
								</div>
								<div class="form-group">
									<textarea class="form-control" rows="7" id="content"
										name="content" placeholder="신고사유" required="required"
										data-validation-required-message="Please enter your message."></textarea>
									<p class="help-block text-danger"></p>
								</div>
								<!-- <div class="form-group">
                           <label class="sr-only" for="product_images">사진첨부</label> <input
                              class="form-control" type="file" id="product_images"
                              name="product_images" />
                           <p class="help-block text-danger"></p>
                        </div> -->
								<div class="text-center">
									<br>
									<h4>해당 게시물을 신고하시겠습니까?</h4>
								</div>

								<div class="text-center">
									<button class="btn btn-block btn-round btn-d" id="cfsubmit"
										type="submit">Submit</button>
								</div>
							</form>
							<div class="ajax-response font-alt" id="contactFormResponse"></div>
						</div>
					</div>
				</div>
			</section>
		</div>

	</main>
	<%@include file="../include/script.jsp"%>
	<script type="text/javascript">
		function reportPopup() {
			window.open("08_2_popup.html", "a",
					"width=500, height=600, left=100, top=50");
		}
	</script>
</body>

</html>