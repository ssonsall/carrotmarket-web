<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>list</title>
<%@include file="../include/favicons.jsp"%>
<%@include file="../include/stylesheets.jsp"%>
</head>
<body data-spy="scroll" data-target=".onpage-navigation" data-offset="60">
	<main>
		<div class="page-loader">
			<div class="loader">Loading...</div>
		</div>
		<%@include file="../include/nav.jsp"%>

		<div class="main">
			<section class="module bg-dark-60 shop-page-header" data-background="/assets/images/shop/product-page-bg.jpg">
				<div class="container">
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<h2 class="module-title font-alt">Shop Products</h2>
							<div class="module-subtitle font-serif">A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring
								which I enjoy with my whole heart.</div>
						</div>
					</div>
				</div>
			</section>
			<section class="module-small">
				<div class="container">
					<form class="row" action="/board/page?page=0" method="get">

						<!-- 슬라이더  -->
						<div class="slidecontainer">
							<c:choose>
								<c:when test="${empty currentRange}">
									<input type="range" min="5" max="15" step="5" value="5" class="slider" id="myRange" style="position: relative; left: 20px;" name="range">
								</c:when>
								<c:otherwise>
									<input type="range" min="5" max="15" step="5" value="${currentRange}" class="slider" id="myRange" style="position: relative; left: 20px;" name="range">
								</c:otherwise>
							</c:choose>
							<p style="position: relative; left: 20px; top: 10px;">
								거리: <span id="demo"></span> km
							</p>

						</div>

						<script>
							var slider = document.getElementById("myRange");
							var output = document.getElementById("demo");
							output.innerHTML = slider.value;

							slider.oninput = function() {
								output.innerHTML = this.value;
							}
						</script>
						<!-- 슬라이더  -->

						<div class="col-sm-4 mb-sm-20">

							<select name="category" class="form-control">
								<c:choose>
									<c:when test="${currentCategory eq '1'}">
										<option value="1" selected="selected">전체</option>
									</c:when>
									<c:otherwise>
										<option value="1">전체</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '2'}">
										<option value="2" selected="selected">생활가전</option>
									</c:when>
									<c:otherwise>
										<option value="2">생활가전</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '3'}">
										<option value="3" selected="selected">디지털</option>
									</c:when>
									<c:otherwise>
										<option value="3">디지털</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '4'}">
										<option value="4" selected="selected">가구/인테리어</option>
									</c:when>
									<c:otherwise>
										<option value="4">가구/인테리어</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '5'}">
										<option value="5" selected="selected">유아동/유아도서</option>
									</c:when>
									<c:otherwise>
										<option value="5">유아동/유아도서</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '6'}">
										<option value="6" selected="selected">생활/가공식품</option>
									</c:when>
									<c:otherwise>
										<option value="6">생활/가공식품</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '7'}">
										<option value="7" selected="selected">여성의류</option>
									</c:when>
									<c:otherwise>
										<option value="7">여성의류</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '8'}">
										<option value="8" selected="selected">여성잡화</option>
									</c:when>
									<c:otherwise>
										<option value="8">여성잡화</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '9'}">
										<option value="9" selected="selected">뷰티/미용</option>
									</c:when>
									<c:otherwise>
										<option value="9">뷰티/미용</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '10'}">
										<option value="10" selected="selected">남성패션/잡화</option>
									</c:when>
									<c:otherwise>
										<option value="10">남성패션/잡화</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '11'}">
										<option value="11" selected="selected">스포츠/레저</option>
									</c:when>
									<c:otherwise>
										<option value="11">스포츠/레저</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '12'}">
										<option value="12" selected="selected">게임/취미</option>
									</c:when>
									<c:otherwise>
										<option value="12">게임/취미</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '13'}">
										<option value="13" selected="selected">도서/티켓/음반</option>
									</c:when>
									<c:otherwise>
										<option value="13">도서/티켓/음반</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '14'}">
										<option value="14" selected="selected">반려동물용품</option>
									</c:when>
									<c:otherwise>
										<option value="14">반려동물용품</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '15'}">
										<option value="15" selected="selected">기타 중고물품</option>
									</c:when>
									<c:otherwise>
										<option value="15">기타 중고물품</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentCategory eq '16'}">
										<option value="16" selected="selected">삽니다</option>
									</c:when>
									<c:otherwise>
										<option value="16">삽니다</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
						<div class="col-sm-4 mb-sm-20">
							<input class="form-control" type="text" name="userInput" value="${originUserInput}" placeholder="*검색어를 입력해주세요." />
						</div>

						<div class="col-sm-3">
							<button class="btn btn-block btn-round btn-g" type="submit">검색</button>
						</div>


					</form>
				</div>
			</section>


			<hr class="divider-w">
			<section class="module-small">
				<div class="container">
					<div class="row multi-columns-row">
						<c:catch var="e">
							<c:forEach var="board" items="${boards}">
								<%-- <c:if test="${board.state ne 1}"> --%>
								<div class="col-sm-6 col-md-3 col-lg-3">
									<div class="shop-item">
										<div class="shop-item-image">
										<c:choose>
										<c:when test="${board.state eq 1 }">
										
										<div style="width: 262.5px; height: 296.05px">
										<img src="/home/ubuntu/upload/sold.png" style="position: absolute; top: 100px; transform: rotate(-20deg); z-index: 1" >
										<img src="/home/ubuntu/upload/${board.image1}" alt="Accessories Pack" style="width: 262.5px; height: 296.05px; filter: brightness(50%); z-index: 2" />
										
										</div>
										
										</c:when>
										<c:otherwise>
										<img src="/home/ubuntu/upload/${board.image1}" alt="Accessories Pack" style="width: 262.5px; height: 296.05px" />
										</c:otherwise>
										</c:choose>
											
											<div class="shop-item-detail">
												<a href="/board/detail/${board.id}" class="btn btn-round btn-b"><span class="icon-basket">See Detail</span></a>
											</div>
										</div>
										<h2 class="shop-item-title font-alt">
											<a href="/board/detail/${board.id}">${board.title}</a>
										</h2>
										<h5>${board.user.address}/${board.user.name}</h5>
										<h5>${board.price}원</h5>
									</div>
								</div>
								<%-- </c:if> --%>
							</c:forEach>
						</c:catch>
						<c:if test="${e ne null}">
							<h1>Error ${e.message}</h1>
						</c:if>

					</div>

					<!-- 페이징 -->
					<div class="row">
						<div class="col-sm-12">
							<div class="pagination font-alt">
								<c:choose>
									<c:when test="${(currentPage+1)%4 ne 0}">
										<fmt:parseNumber var="navPage" value="${currentPage/4}" integerOnly="true"></fmt:parseNumber>
									</c:when>
									<c:otherwise>
										<fmt:parseNumber var="navPage" value="${(currentPage-1)/4}" integerOnly="true"></fmt:parseNumber>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${count%4 ne 0}">
										<fmt:parseNumber var="totalPage" value="${count/4}" integerOnly="true"></fmt:parseNumber>
									</c:when>
									<c:otherwise>
										<fmt:parseNumber var="totalPage" value="${(count-1)/4}" integerOnly="true"></fmt:parseNumber>
									</c:otherwise>
								</c:choose>

								<c:if test="${navPage ne 0}">
									<a href="/board/page?page=${navPage*4-1}&category=${currentCategory}&userInput=${currentUserInput}&range=${currentRange}"><i
										class="fa fa-angle-left"></i></a>
								</c:if>
								<c:forEach var="i" begin="${navPage*4+1}" end="${navPage*4+4}">
									<c:if test="${i le count}">
										<c:choose>
											<c:when test="${i-1 eq currentPage}">
												<a class="active" style="background-color: black;"
													href="/board/page?page=${i-1}&category=${currentCategory}&userInput=${currentUserInput}&range=${currentRange}">${i}</a>
											</c:when>
											<c:otherwise>
												<a href="/board/page?page=${i-1}&category=${currentCategory}&userInput=${currentUserInput}&range=${currentRange}">${i}</a>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
								<c:if test="${navPage lt totalPage}">
									<a href="/board/page?page=${navPage*4+4}&category=${currentCategory}&userInput=${currentUserInput}&range=${currentRange}"><i
										class="fa fa-angle-right"></i></a>
								</c:if>
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