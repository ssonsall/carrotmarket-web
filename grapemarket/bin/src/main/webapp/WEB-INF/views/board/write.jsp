<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>write</title>
<%@include file="../include/favicons.jsp"%>
<%@include file="../include/stylesheets.jsp"%>
</head>
<body data-spy="scroll" data-target=".onpage-navigation"
	data-offset="60">
	<main>
		<div class="page-loader">
			<div class="loader">Loading...</div>
		</div>
		<!-- nav -->
		<%@include file="../include/nav.jsp"%>

		<div class="main">
			<section class="module" id="contact">
				<div class="container">
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<h2 class="module-title font-alt">상품 등록</h2>
							<div class="module-subtitle font-serif"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<form id="contactForm" role="form" method="post"
								action="/board/writeProc">
								<input type="hidden" name="userId" value="${user.id}">
								<input type="hidden" name="state" value="0">
								
								<div class="form-group">
									<label class="sr-only" for="title">제목</label> <input
										class="form-control" type="text" id="title" name="title"
										placeholder="제목*" required="required"
										data-validation-required-message="Please enter your name." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="category">카테고리</label>
									<!-- <input class="form-control" type="text" id="category" name="category" placeholder="카테고리*" required="required" data-validation-required-message="Please enter your email address."/> -->
									<div class="col-sm-4 mb-sm-20">
										<select name="category_id" class="form-control"
											style="position: relative; bottom: 7px; right: 15px; width: 555px;">
											<option value="1" selected="selected">인기매물</option>
											<option value="2">디지털/가전</option>
											<option value="3">가구/인테리어</option>
											<option value="4">유아동/유아도서</option>
											<option value="5">생활/가공식품</option>
											<option value="6">여성의류</option>
											<option value="7">여성잡화</option>
											<option value="8">뷰티/미용</option>
											<option value="9">남성패션/잡화</option>
											<option value="10">스포추/레저</option>
											<option value="11">게임/취미</option>
											<option value="12">도서/티켓/음반</option>
											<option value="13">반려동물용품</option>
											<option value="14">기타 중고물품</option>
											<option value="15">삽니다</option>
										</select>
									</div>
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="price">가격</label> <input
										class="form-control" type="text" id="price" name="price"
										placeholder="가격*" required="required"
										data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<textarea class="form-control" rows="7" id="content"
										name="content" placeholder="내용*" required="required"
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
			<%@include file="../include/footer.jsp"%>
		</div>
		<div class="scroll-up">
			<a href="#totop"><i class="fa fa-angle-double-up"></i></a>
		</div>
	</main>
	<%@include file="../include/script.jsp"%>
</body>
</html>