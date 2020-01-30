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
			<section class="module" id="contact" style="padding-top: 30px; padding-bottom: 0px;">
				<div class="container">
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<h2 class="module-title font-alt">신고</h2>
							<div class="module-subtitle font-serif"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<form id="writeFormSubmit" action="/report/reportProc"
								method="post" enctype="multipart/form-data">
								<input type="hidden" name="state" value="0"> <input
									type="hidden" name="reportType" value="${type}">
								<c:choose>
									<c:when test="${type eq 'board'}">
										<input type="hidden" name="reportId" value="${board.id}">
										<div class="form-group">
											<label class="sr-only" for="title">제목</label> <input
												class="form-control" type="text" id="title" name="title"
												placeholder="제목*" required="required"
												data-validation-required-message="Please enter your name."
												value="${board.title}" readonly="readonly" />
											<p class="help-block text-danger"></p>
										</div>
									</c:when>
									<c:when test="${type eq 'comment'}">
										<input type="hidden" name="reportId" value="${comment.id}">
										<div class="form-group">
											<label class="sr-only" for="title">제목</label> <input
												class="form-control" type="text" id="title" name="title"
												placeholder="제목*" required="required"
												data-validation-required-message="Please enter your name."
												value="${comment.content}" readonly="readonly" />
											<p class="help-block text-danger"></p>
										</div>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="reportId" value="${message.id}">
										<div class="form-group">
											<label class="sr-only" for="title">제목</label> <input
												class="form-control" type="text" id="title" name="title"
												placeholder="제목*" required="required"
												data-validation-required-message="Please enter your name."
												value="${message.message}" readonly="readonly" />
											<p class="help-block text-danger"></p>
										</div>
									</c:otherwise>

								</c:choose>




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