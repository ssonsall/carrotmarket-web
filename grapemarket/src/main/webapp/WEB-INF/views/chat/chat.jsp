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
<title>Titan | Multipurpose HTML5 Template</title>
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
			<!-- <section class="module bg-dark-60 blog-page-header" data-background="assets/images/blog_bg.jpg">
          <div class="container">
            <div class="row">
              <div class="col-sm-6 col-sm-offset-3">
                <h2 class="module-title font-alt">Blog Standard</h2>
                <div class="module-subtitle font-serif">A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart.</div>
              </div>
            </div>
          </div>
        </section> -->
			<section class="module">
				<div class="container">
					<div class="row">
						<!-- 현재 거래 상품 정보 시작 -->
						<div class="col-sm-4 col-md-3 sidebar">
							<h4 class="font-alt mb-0">현재 거래상품</h4>
							<hr class="divider-w mt-10 mb-20">
							<!-- 이미지 세로 크기 고정 필요 -->
							<div class="col-sm-12 mb-sm-40">
								<img
									src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvH5pM8pc9EOEuK1DbpkvBG7ruBU_0rJMBX5wqDOjoFRcq2QIbJw&s"
									alt="Single Product Image"
									style="max-height: 300px; min-width: 232px;" />
							</div>
							<h5>
								<div class="col-sm-12 mb-sm-40 font-alt">판매자</div>
							</h5>
							<div class="col-sm-12 mb-sm-40 font-alt">&nbsp도라에몽</div>
							<h5>
								<div class="col-sm-12 mb-sm-40 font-alt">상품명</div>
							</h5>
							<div class="col-sm-12 mb-sm-40 font-alt">&nbsp맥북 프로 2015년형</div>
							<h5>
								<div class="col-sm-12 mb-sm-40 font-alt">가격</div>
							</h5>
							<div class="col-sm-12 mb-sm-40 font-alt">&nbsp800,000원</div>
						</div>
						<!-- 현재 거래 상품 정보 끝 -->
						<!-- 채팅방 목록 시작 -->
						<div class="col-sm-4 col-sm-offset-1">
							<h4 class="font-alt mb-0">채팅방 목록</h4>
							<hr class="divider-w mt-10 mb-20">
							<div class="alert alert-success" role="alert">
								<button class="close" type="button" data-dismiss="alert"
									aria-hidden="true">&times;</button>
								<i class="fa fa-comment"></i><strong>Alert!</strong> 김선발님과의
								채팅방입니다.
							</div>
							<div class="alert alert-success" role="alert">
								<button class="close" type="button" data-dismiss="alert"
									aria-hidden="true">&times;</button>
								<i class="fa fa-comment"></i><strong>Alert!</strong> 반장님과의
								채팅방입니다.
							</div>
							<div class="alert alert-success" role="alert">
								<button class="close" type="button" data-dismiss="alert"
									aria-hidden="true">&times;</button>
								<i class="fa fa-comment"></i><strong>Alert!</strong> 태군형님과의
								채팅방입니다.
							</div>
							<div class="alert alert-success" role="alert">
								<button class="close" type="button" data-dismiss="alert"
									aria-hidden="true">&times;</button>
								<i class="fa fa-comment"></i><strong>Alert!</strong> 정민님과의
								채팅방입니다.
							</div>
							<div class="alert alert-success" role="alert">
								<button class="close" type="button" data-dismiss="alert"
									aria-hidden="true">&times;</button>
								<i class="fa fa-comment"></i><strong>Alert!</strong> 주호님과의
								채팅방입니다.
							</div>
						</div>
						<!-- 채팅방 목록 끝 -->
						<!-- 채팅방 시작 -->
						<div class="col-sm-4 col-md-3 col-md-offset-1 sidebar">
							<h4 class="font-alt mb-0">채팅방 목록</h4>
							<hr class="divider-w mt-10 mb-20">
							<form id="contactForm" role="form" method="post"
								action="php/contact.php">
								<div class="form-group">
									<textarea class="form-control" rows="20" id="content"
										name="content" readonly
										data-validation-required-message="Please enter your message."></textarea>
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="message">메세지</label> <input
										class="form-control" type="text" id="message" name="message"
										placeholder="메세지*"
										data-validation-required-message="Please enter your name." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="text-center">
									<button class="btn btn-block btn-round btn-d" id="cfsubmit"
										type="submit">Submit</button>
								</div>
							</form>
						</div>
						<!-- 채팅방 끝 -->
					</div>
				</div>
			</section>
			<div class="module-small bg-dark">
				<div class="container">
					<div class="row">
						<div class="col-sm-3">
							<div class="widget">
								<h5 class="widget-title font-alt">About Titan</h5>
								<p>The languages only differ in their grammar, their
									pronunciation and their most common words.</p>
								<p>Phone: +1 234 567 89 10</p>
								Fax: +1 234 567 89 10
								<p>
									Email:<a href="#">somecompany@example.com</a>
								</p>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="widget">
								<h5 class="widget-title font-alt">Recent Comments</h5>
								<ul class="icon-list">
									<li>Maria on <a href="#">Designer Desk Essentials</a></li>
									<li>John on <a href="#">Realistic Business Card Mockup</a></li>
									<li>Andy on <a href="#">Eco bag Mockup</a></li>
									<li>Jack on <a href="#">Bottle Mockup</a></li>
									<li>Mark on <a href="#">Our trip to the Alps</a></li>
								</ul>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="widget">
								<h5 class="widget-title font-alt">Blog Categories</h5>
								<ul class="icon-list">
									<li><a href="#">Photography - 7</a></li>
									<li><a href="#">Web Design - 3</a></li>
									<li><a href="#">Illustration - 12</a></li>
									<li><a href="#">Marketing - 1</a></li>
									<li><a href="#">Wordpress - 16</a></li>
								</ul>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="widget">
								<h5 class="widget-title font-alt">Popular Posts</h5>
								<ul class="widget-posts">
									<li class="clearfix">
										<div class="widget-posts-image">
											<a href="#"><img src="assets/images/rp-1.jpg"
												alt="Post Thumbnail" /></a>
										</div>
										<div class="widget-posts-body">
											<div class="widget-posts-title">
												<a href="#">Designer Desk Essentials</a>
											</div>
											<div class="widget-posts-meta">23 january</div>
										</div>
									</li>
									<li class="clearfix">
										<div class="widget-posts-image">
											<a href="#"><img src="assets/images/rp-2.jpg"
												alt="Post Thumbnail" /></a>
										</div>
										<div class="widget-posts-body">
											<div class="widget-posts-title">
												<a href="#">Realistic Business Card Mockup</a>
											</div>
											<div class="widget-posts-meta">15 February</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<hr class="divider-d">
			<footer class="footer bg-dark">
				<div class="container">
					<div class="row">
						<div class="col-sm-6">
							<p class="copyright font-alt">
								&copy; 2017&nbsp;<a href="index.html">TitaN</a>, All Rights
								Reserved
							</p>
						</div>
						<div class="col-sm-6">
							<div class="footer-social-links">
								<a href="#"><i class="fa fa-facebook"></i></a><a href="#"><i
									class="fa fa-twitter"></i></a><a href="#"><i
									class="fa fa-dribbble"></i></a><a href="#"><i
									class="fa fa-skype"></i></a>
							</div>
						</div>
					</div>
				</div>
			</footer>
		</div>
		<div class="scroll-up">
			<a href="#totop"><i class="fa fa-angle-double-up"></i></a>
		</div>
	</main>
	<!--  
    JavaScripts
    =============================================
    -->
	<%@include file="../include/script.jsp"%>
</body>
</html>