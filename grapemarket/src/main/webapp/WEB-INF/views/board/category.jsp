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
					<h1>카테고리</h1>
				</div>
			</section>
			<hr class="divider-w">
			<section class="module">
				<div class="container">
					<div class="row">
						<div class="row fa-icons">
							<c:forEach var="category" items="${categories}"
								varStatus="status">
								<c:choose>
									<c:when test="${status.count%2 == 0 }">
										<div class="col-md-4 col-sm-6 col-lg-4"
											style="padding-left: 5%">
											<a href="/board/category/${status.count}"><h3>
													${category}</h3></a>
										</div>
									</c:when>
									<c:otherwise>
										<div class="col-md-4 col-sm-6 col-lg-4"
											style="padding-left: 5%; background-color: #f9f2ec">
											<a href="/board/category/${status.count}"><h3>
													${category}</h3></a>
										</div>
									</c:otherwise>
								</c:choose>

							</c:forEach>
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