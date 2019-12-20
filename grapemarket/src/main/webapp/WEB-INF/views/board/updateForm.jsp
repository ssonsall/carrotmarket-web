<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
							<h2 class="module-title font-alt">상품 수정</h2>
							<div class="module-subtitle font-serif"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<form action="/board/update" method="post"
								enctype="multipart/form-data">
								<input type="hidden" name="state" value="0"> <input
									type="hidden" name="id" value="${board.id}"> <input
									type="hidden" name="currentImage1" value="${board.image1}">
								<input type="hidden" name="currentImage2"
									value="${board.image2}"> <input type="hidden"
									name="currentImage3" value="${board.image3}"> <input
									type="hidden" name="currentImage4" value="${board.image4}">
								<input type="hidden" name="currentImage5"
									value="${board.image5}">
								<div class="form-group">
									<label class="sr-only" for="title">제목</label> <input
										class="form-control" type="text" id="title" name="title"
										placeholder="제목*" required="required" value="${board.title}"
										data-validation-required-message="Please enter your name." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="category">카테고리</label>
									<!-- <input class="form-control" type="text" id="category" name="category" placeholder="카테고리*" required="required" data-validation-required-message="Please enter your email address."/> -->
									<div class="col-sm-4 mb-sm-20">
										<select name="category" class="form-control"
											style="position: relative; bottom: 7px; right: 15px; width: 555px;">
											<c:choose>
												<c:when test="${board.category eq '3'}">
													<option value="3" selected="selected">디지털/가전</option>
												</c:when>
												<c:otherwise>
													<option value="3">디지털/가전</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '4'}">
													<option value="4" selected="selected">가구/인테리어</option>
												</c:when>
												<c:otherwise>
													<option value="4">가구/인테리어</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '5'}">
													<option value="5" selected="selected">유아동/유아도서</option>
												</c:when>
												<c:otherwise>
													<option value="5">유아동/유아도서</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '6'}">
													<option value="6" selected="selected">생활/가공식품</option>
												</c:when>
												<c:otherwise>
													<option value="6">생활/가공식품</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '7'}">
													<option value="7" selected="selected">여성의류</option>
												</c:when>
												<c:otherwise>
													<option value="7">여성의류</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '8'}">
													<option value="8" selected="selected">여성잡화</option>
												</c:when>
												<c:otherwise>
													<option value="8">여성잡화</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '9'}">
													<option value="9" selected="selected">뷰티/미용</option>
												</c:when>
												<c:otherwise>
													<option value="9">뷰티/미용</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '10'}">
													<option value="10" selected="selected">남성패션/잡화</option>
												</c:when>
												<c:otherwise>
													<option value="10">남성패션/잡화</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '11'}">
													<option value="11" selected="selected">스포츠/레저</option>
												</c:when>
												<c:otherwise>
													<option value="11">스포츠/레저</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '12'}">
													<option value="12" selected="selected">게임/취미</option>
												</c:when>
												<c:otherwise>
													<option value="12">게임/취미</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '13'}">
													<option value="13" selected="selected">도서/티켓/음반</option>
												</c:when>
												<c:otherwise>
													<option value="13">도서/티켓/음반</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '14'}">
													<option value="14" selected="selected">반려동물용품</option>
												</c:when>
												<c:otherwise>
													<option value="14">반려동물용품</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '15'}">
													<option value="15" selected="selected">기타 중고물품</option>
												</c:when>
												<c:otherwise>
													<option value="15">기타 중고물품</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${board.category eq '16'}">
													<option value="16" selected="selected">삽니다</option>
												</c:when>
												<c:otherwise>
													<option value="16">삽니다</option>
												</c:otherwise>
											</c:choose>
										</select>
									</div>
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="price">가격</label> <input
										class="form-control" type="text" id="price" name="price"
										placeholder="가격*" required="required" value="${board.price}"
										data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="price">사진</label> <input
										class="form-control" type="file" accept="image/*"
										id="productImage1" name="productImage1"
										data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="price">사진</label> <input
										class="form-control" type="file" accept="image/*"
										id="productImage2" name="productImage2"
										data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="price">사진</label> <input
										class="form-control" type="file" accept="image/*"
										id="productImage3" name="productImage3"
										data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="price">사진</label> <input
										class="form-control" type="file" accept="image/*"
										id="productImage4" name="productImage4"
										data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="price">사진</label> <input
										class="form-control" type="file" accept="image/*"
										id="productImage5" name="productImage5"
										data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>

								<div class="form-group">
									<textarea class="form-control" rows="7" id="content"
										name="content" placeholder="내용*" required="required"
										data-validation-required-message="Please enter your message.">${board.content}</textarea>
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