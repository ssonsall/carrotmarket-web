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
							<form id="writeFormSubmit" action="/board/writeProc"
								method="post" enctype="multipart/form-data">
								<%-- <input type="hidden" name="userId" value="${user.id}"> --%>
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
										<select name="category" class="form-control"
											style="position: relative; bottom: 7px; right: 15px; width: 555px;">
											<option value="2">생활가전</option>
											<option value="3">디지털</option>
											<option value="4">가구/인테리어</option>
											<option value="5">유아동/유아도서</option>
											<option value="6">생활/가공식품</option>
											<option value="7">여성의류</option>
											<option value="8">여성잡화</option>
											<option value="9">뷰티/미용</option>
											<option value="10">남성패션/잡화</option>
											<option value="11">스포추/레저</option>
											<option value="12">게임/취미</option>
											<option value="13">도서/티켓/음반</option>
											<option value="14">반려동물용품</option>
											<option value="15">기타 중고물품</option>
											<option value="16">삽니다</option>
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
								<!-- 사진 시작 -->
								<div style="margin-bottom: 10px;" type="button"
									onclick="plusPhoto();savePhoto();">
									<i class="fa fa-plus-circle"> 사진추가</i>
								</div>

								<div id="photo">
									<div class="form-group" id="productImage1">
										<label class="sr-only" for="price">사진</label> <input
											style="display: inline;" class="inputfile" type="file"
											accept="image/*" id="productImage1" required="required"
											name="productImage1"
											data-validation-required-message="Please enter your email address." />
										<p class="help-block text-danger">${board.image1}</p>
									</div>

									<c:if test="${!empty board.image2}">
										<div class="form-group" id="productImage2">
											<label class="sr-only" for="price">사진</label> <input
												style="display: inline;" class="inputfile" type="file"
												accept="image/*" id="productImage2" name="productImage2"
												data-validation-required-message="Please enter your email address." />
											<div style="display: inline;" type='button'
												onclick='deletePhoto(2)'>
												<i class="fa fa-minus-circle">사진삭제</i>
											</div>
											<p class="help-block text-danger">${board.image2}</p>

										</div>
									</c:if>
									<c:if test="${!empty board.image3}">
										<div class="form-group" id="productImage3">
											<label class="sr-only" for="price">사진</label> <input
												style="display: inline;" class="inputfile" type="file"
												accept="image/*" id="productImage3" name="productImage3"
												data-validation-required-message="Please enter your email address." />
											<div style="display: inline;" type='button'
												onclick='deletePhoto(3)'>
												<i class="fa fa-minus-circle">사진삭제</i>
											</div>
											<p class="help-block text-danger">${board.image3}</p>

										</div>
									</c:if>
									<c:if test="${!empty board.image4}">
										<div class="form-group" id="productImage4">
											<label class="sr-only" for="price">사진</label> <input
												style="display: inline;" class="inputfile" type="file"
												accept="image/*" id="productImage4" name="productImage4"
												data-validation-required-message="Please enter your email address." />
											<div style="display: inline;" type='button'
												onclick='deletePhoto(4)'>
												<i class="fa fa-minus-circle">사진삭제</i>
											</div>
											<p class="help-block text-danger">${board.image4}</p>

										</div>
									</c:if>
									<c:if test="${!empty board.image5}">
										<div class="form-group" id="productImage5">
											<label class="sr-only" for="price">사진</label> <input
												style="display: inline;" class="inputfile" type="file"
												accept="image/*" id="productImage5" name="productImage5"
												data-validation-required-message="Please enter your email address." />
											<div style="display: inline;" type='button'
												onclick='deletePhoto(5)'>
												<i class="fa fa-minus-circle">사진삭제</i>
											</div>
											<p class="help-block text-danger">${board.image5}</p>

										</div>
									</c:if>
								</div>
								<div id="hiddenPhoto"></div>

								<!-- 사진 끝 -->
								<div class="form-group">
									<textarea class="form-control" rows="7" id="content"
										name="content" placeholder="내용*" required="required"
										data-validation-required-message="Please enter your message.">${board.content}</textarea>
									<p class="help-block text-danger"></p>
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
			<%@include file="../include/footer.jsp"%>
		</div>
		<div class="scroll-up">
			<a href="#totop"><i class="fa fa-angle-double-up"></i></a>
		</div>
	</main>
	<%@include file="../include/script.jsp"%>
	<script type="text/javascript">
		/*    function plusPhoto() {
		      var ele = document.getElementById('photo');
		      var eleCount = ele.childElementCount;
		      console.log('eleCount');
		      if (eleCount < 5) {
		         $("#photo")
		               .append(
		                     "<div class='form-group' id='checkImage"+ref+"'><label class='sr-only' for='price'>사진</label> <input id='checkImage"+ref+"' class='form-control' type='file' accept='image/*' name='checkImage' data-validation-required-message='Please enter your email address.' onchange='document.all.bbb.value = this.value' /><p class='help-block text-danger'></p><input type='button' onclick='deletePhoto("
		                           + ref + ")' value='-'></input></div>");
		      }
		      ref++;
		   }; */

		function plusPhoto() {
			if (!document.getElementById("productImage1")) {
				$("#photo")
						.append(
								"<div class='form-group' id='productImage1'><label class='sr-only' for='price'>사진</label> <input style='display: inline;' class='inputfile' type='file' accept='image/*' id='productImage1' name='productImage1' data-validation-required-message='Please enter your email address.'  /><div style='display: inline;' type='button' onclick='deletePhoto(1)'><i class='fa fa-minus-circle'>사진삭제</i></div><p class='help-block text-danger'></p></div>");
			} else if (!document.getElementById("productImage2")) {
				$("#photo")
						.append(
								"<div class='form-group' id='productImage2'><label class='sr-only' for='price'>사진</label> <input style='display: inline;' class='inputfile' type='file' accept='image/*' id='productImage2' name='productImage2' data-validation-required-message='Please enter your email address.'  /><div style='display: inline;' type='button' onclick='deletePhoto(2)'><i class='fa fa-minus-circle'>사진삭제</i></div><p class='help-block text-danger'></p></div>");
			} else if (!document.getElementById("productImage3")) {
				$("#photo")
						.append(
								"<div class='form-group' id='productImage3'><label class='sr-only' for='price'>사진</label> <input style='display: inline;' class='inputfile' type='file' accept='image/*' id='productImage3' name='productImage3' data-validation-required-message='Please enter your email address.'  /><div style='display: inline;' type='button' onclick='deletePhoto(3)'><i class='fa fa-minus-circle'>사진삭제</i></div><p class='help-block text-danger'></p></div>");
			} else if (!document.getElementById("productImage4")) {
				$("#photo")
						.append(
								"<div class='form-group' id='productImage4'><label class='sr-only' for='price'>사진</label> <input style='display: inline;' class='inputfile' type='file' accept='image/*' id='productImage4' name='productImage4' data-validation-required-message='Please enter your email address.'  /><div style='display: inline;' type='button' onclick='deletePhoto(4)'><i class='fa fa-minus-circle'>사진삭제</i></div><p class='help-block text-danger'></p></div>");
			} else if (!document.getElementById("productImage5")) {
				$("#photo")
						.append(
								"<div class='form-group' id='productImage5'><label class='sr-only' for='price'>사진</label> <input style='display: inline;' class='inputfile' type='file' accept='image/*' id='productImage5' name='productImage5' data-validation-required-message='Please enter your email address.'  /><div style='display: inline;' type='button' onclick='deletePhoto(5)'><i class='fa fa-minus-circle'>사진삭제</i></div><p class='help-block text-danger'></p></div>");
			}
		};

		function deletePhoto(num) {
			var field = "#productImage" + num;
			console.log(field);
			$(field).remove();
		};

		$('#writeFormSubmit')
				.submit(
						function fillPhoto() {
							if (!document.getElementById("productImage1")) {
								$("#photo")
										.append(
												"<div class='form-group' id='productImage1'><label class='sr-only' for='price'>사진</label> <input class='form-control' type='file' accept='image/*' id='productImage1' name='productImage1' data-validation-required-message='Please enter your email address.' /><p class='help-block text-danger'></p><input type='button' onclick='deletePhoto(1)' value='-'></input></div>");
							}
							if (!document.getElementById("productImage2")) {
								$("#photo")
										.append(
												"<div class='form-group' id='productImage2'><label class='sr-only' for='price'>사진</label> <input class='form-control' type='file' accept='image/*' id='productImage2' name='productImage2' data-validation-required-message='Please enter your email address.' /><p class='help-block text-danger'></p><input type='button' onclick='deletePhoto(2)' value='-'></input></div>");
							}
							if (!document.getElementById("productImage3")) {
								$("#photo")
										.append(
												"<div class='form-group' id='productImage3'><label class='sr-only' for='price'>사진</label> <input class='form-control' type='file' accept='image/*' id='productImage3' name='productImage3' data-validation-required-message='Please enter your email address.' /><p class='help-block text-danger'></p><input type='button' onclick='deletePhoto(3)' value='-'></input></div>");
							}
							if (!document.getElementById("productImage4")) {
								$("#photo")
										.append(
												"<div class='form-group' id='productImage4'><label class='sr-only' for='price'>사진</label> <input class='form-control' type='file' accept='image/*' id='productImage4' name='productImage4' data-validation-required-message='Please enter your email address.' /><p class='help-block text-danger'></p><input type='button' onclick='deletePhoto(4)' value='-'></input></div>");
							}
							if (!document.getElementById("productImage5")) {
								$("#photo")
										.append(
												"<div class='form-group' id='productImage5'><label class='sr-only' for='price'>사진</label> <input class='form-control' type='file' accept='image/*' id='productImage5' name='productImage5' data-validation-required-message='Please enter your email address.' /><p class='help-block text-danger'></p><input type='button' onclick='deletePhoto(5)' value='-'></input></div>");
							}
							return true;
						});
	</script>
</body>
</html>