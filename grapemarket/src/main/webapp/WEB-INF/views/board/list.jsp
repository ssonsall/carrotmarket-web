<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>list</title>
<%@include file="../include/favicons.jsp"%>
<%@include file="../include/stylesheets.jsp"%>
</head>
<body data-spy="scroll" data-target=".onpage-navigation"
	data-offset="60">
	<main>
		<div class="page-loader">
			<div class="loader">Loading...</div>
		</div>
		<%@include file="../include/nav.jsp"%>

		<div class="main">
			<section class="module bg-dark-60 shop-page-header"
				data-background="/assets/images/shop/product-page-bg.jpg">
				<div class="container">
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<h2 class="module-title font-alt">Shop Products</h2>
							<div class="module-subtitle font-serif">A wonderful
								serenity has taken possession of my entire soul, like these
								sweet mornings of spring which I enjoy with my whole heart.</div>
						</div>
					</div>
				</div>
			</section>
			<section class="module-small">
				<div class="container">
					<form class="row">
						<div class="col-sm-4 mb-sm-20">
							<select class="form-control">
								<option selected="selected">인기매물</option>
								<option>디지털/가전</option>
								<option>가구/인테리어</option>
								<option>유아동/유아도서</option>
								<option>생활/가공식품</option>
								<option>여성의류</option>
								<option>여성잡화</option>
								<option>뷰티/미용</option>
								<option>남성패션/잡화</option>
								<option>스포추/레저</option>
								<option>게임/취미</option>
								<option>도서/티켓/음반</option>
								<option>반려동물용품</option>
								<option>기타 중고물품</option>
								<option>삽니다</option>
							</select>
						</div>

						<!-- 서브카테고리 -->
						<!-- <div class="col-sm-2 mb-sm-20">
                <select class="form-control">
                  <option selected="selected">Woman</option>
                  <option>Man</option>
                </select>
              </div>
              <div class="col-sm-3 mb-sm-20">
                <select class="form-control">
                  <option selected="selected">All</option>
                  <option>Coats</option>
                  <option>Jackets</option>
                  <option>Dresses</option>
                  <option>Jumpsuits</option>
                  <option>Tops</option>
                  <option>Trousers</option>
                </select>
              </div> -->
						<!-- 서브카테고리 끝 -->


						<div class="col-sm-3">
							<button class="btn btn-block btn-round btn-g" type="submit">Apply</button>
						</div>
					</form>
				</div>
			</section>
			<hr class="divider-w">
			<section class="module-small">
				<div class="container">
					<div class="row multi-columns-row">
						<c:forEach var="board" items="${boards}">
							<div class="col-sm-6 col-md-3 col-lg-3">
								<div class="shop-item">
									<div class="shop-item-image">
										<img src="/assets/images/shop/product-7.jpg"
											alt="Accessories Pack" />
										<div class="shop-item-detail">
											<a class="btn btn-round btn-b"><span class="icon-basket">See
													Detail</span></a>
										</div>
									</div>
									<h4 class="shop-item-title font-alt">
										<a href="#">${board.title}</a>
									</h4>
									${board.price}
								</div>
							</div>
						</c:forEach>


					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="pagination font-alt">
								<a href="#"><i class="fa fa-angle-left"></i></a><a
									class="active" href="#">1</a><a href="#">2</a><a href="#">3</a><a
									href="#">4</a><a href="#"><i class="fa fa-angle-right"></i></a>
							</div>
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