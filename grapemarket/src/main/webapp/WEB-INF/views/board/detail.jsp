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
		<!-- nav -->
		<%@include file="../include/nav.jsp"%>
		<div class="main">
			<section class="module">
				<div class="container">
					<div class="row">
						<!-- ìí í° ì¬ì§ -->
						<div class="col-sm-6 mb-sm-40">
							<a class="gallery" href="/assets/images/shop/product-7.jpg"><img
								src="/assets/images/shop/product-7.jpg"
								alt="Single Product Image" /></a>
							<!-- ìí ìì ì¬ì§ ìì-->
							<ul class="product-gallery">
								<li><a class="gallery"
									href="/assets/images/shop/product-8.jpg"></a><img
									src="/assets/images/shop/product-8.jpg" alt="Single Product" /></li>
								<li><a class="gallery"
									href="/assets/images/shop/product-9.jpg"></a><img
									src="/assets/images/shop/product-9.jpg" alt="Single Product" /></li>
								<li><a class="gallery"
									href="/assets/images/shop/product-10.jpg"></a><img
									src="/assets/images/shop/product-7.jpg" alt="Single Product" /></li>
								<li><a class="gallery"
									href="/assets/images/shop/product-10.jpg"></a><img
									src="/assets/images/shop/product-10.jpg" alt="Single Product" /></li>
								<li><a class="gallery"
									href="/assets/images/shop/product-10.jpg"></a><img
									src="/assets/images/shop/product-10.jpg" alt="Single Product" /></li>
							</ul>
							<!-- ìí ìì ì¬ì§ ë -->
						</div>
						<div class="col-sm-6">
							<div class="row">
								<div class="col-sm-12">
									<h1 class="product-title font-alt">Accessories Pack</h1>
								</div>
							</div>
							<div class="row mb-20">
								<div class="col-sm-12">
									<span><i class="fa fa-star star"></i></span><span><i
										class="fa fa-star star"></i></span><span><i
										class="fa fa-star star"></i></span><span><i
										class="fa fa-star star"></i></span><span><i
										class="fa fa-star star-off"></i></span><a
										class="open-tab section-scroll" href="#reviews">-2customer
										reviews</a>
								</div>
							</div>
							<div class="row mb-20">
								<div class="col-sm-12">
									<div class="price font-alt">
										<span class="amount">Â£20.00</span>
									</div>
								</div>
							</div>
							<div class="row mb-20">
								<div class="col-sm-12">
									<div class="description">
										<p>The European languages are members of the same family.
											Their separate existence is a myth. For science, music,
											sport, etc, Europe uses the same vocabulary. The languages
											only differ in their grammar, their pronunciation and their
											most common words.</p>
									</div>
								</div>
							</div>
							<div class="row mb-20">
								<div class="col-sm-4 mb-sm-20">
									<input class="form-control input-lg" type="number" name=""
										value="1" max="40" min="1" required="required" />
								</div>
								<div class="col-sm-8">
									<a class="btn btn-lg btn-block btn-round btn-b" href="#">Add
										To Cart</a>
								</div>
							</div>
							<div class="row mb-20">
								<div class="col-sm-12">
									<div class="product_meta">
										Categories:<a href="#"> Man, </a><a href="#">Clothing, </a><a
											href="#">T-shirts</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-70">
						<div class="col-sm-12">
							<ul class="nav nav-tabs font-alt" role="tablist">
								<li class="active"><a href="#reviews" data-toggle="tab"><span
										class="icon-tools-2"></span>ëê¸ (2)</a></li>
							</ul>
							<!-- ëê¸ êµ¬ì­ ìì-->
							<div class="comments reviews">
								<div class="comment clearfix">
									<div class="comment-avatar">
										<img src="" alt="avatar" />
									</div>
									<div class="comment-content clearfix">
										<div class="comment-author font-alt">
											<a href="#">John Doe</a>
										</div>
										<div class="comment-body">
											<p>The European languages are members of the same family.
												Their separate existence is a myth. For science, music,
												sport, etc, Europe uses the same vocabulary. The European
												languages are members of the same family. Their separate
												existence is a myth.</p>
										</div>
										<div class="comment-meta font-alt">
											Today, 14:55 -<span><i class="fa fa-star star"></i></span><span><i
												class="fa fa-star star"></i></span><span><i
												class="fa fa-star star"></i></span><span><i
												class="fa fa-star star"></i></span><span><i
												class="fa fa-star star-off"></i></span>
										</div>
									</div>
								</div>
								<div class="comment clearfix">
									<div class="comment-avatar">
										<img src="" alt="avatar" />
									</div>
									<div class="comment-content clearfix">
										<div class="comment-author font-alt">
											<a href="#">Mark Stone</a>
										</div>
										<div class="comment-body">
											<p>Europe uses the same vocabulary. The European
												languages are members of the same family. Their separate
												existence is a myth.</p>
										</div>
										<div class="comment-meta font-alt">
											Today, 14:59 -<span><i class="fa fa-star star"></i></span><span><i
												class="fa fa-star star"></i></span><span><i
												class="fa fa-star star"></i></span><span><i
												class="fa fa-star star-off"></i></span><span><i
												class="fa fa-star star-off"></i></span>
										</div>
									</div>
								</div>
							</div>
							<!-- ëê¸ êµ¬ì­ ë -->

							<!-- ëê¸ ì°ê¸° ìì -->
							<div class="comment-form mt-30">
								<h4 class="comment-form-title font-alt">Add review</h4>
								<form method="post">
									<div class="row">
										<div class="col-sm-4">
											<div class="form-group">
												<label class="sr-only" for="name">Name</label> <input
													class="form-control" id="name" type="text" name="name"
													placeholder="Name" />
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label class="sr-only" for="email">Name</label> <input
													class="form-control" id="email" type="text" name="email"
													placeholder="E-mail" />
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<select class="form-control">
													<option selected="true" disabled="">Rating</option>
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
												</select>
											</div>
										</div>
										<div class="col-sm-12">
											<div class="form-group">
												<textarea class="form-control" id="" name="" rows="4"
													placeholder="Review"></textarea>
											</div>
										</div>
										<div class="col-sm-12">
											<button class="btn btn-round btn-d" type="submit">Submit
												Review</button>
										</div>
									</div>
								</form>
							</div>
							<!-- ëê¸ ì°ê¸° ë -->
						</div>

					</div>
				</div>
			</section>
			<hr class="divider-w">
			<section class="module-small">
				<div class="container">
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<h2 class="module-title font-alt">Related Products</h2>
						</div>
					</div>
					<div class="row multi-columns-row">
						<div class="col-sm-6 col-md-3 col-lg-3">
							<div class="shop-item">
								<div class="shop-item-image">
									<img src="/assets/images/shop/product-11.jpg"
										alt="Accessories Pack" />
									<div class="shop-item-detail">
										<a class="btn btn-round btn-b"><span class="icon-basket">Add
												To Cart</span></a>
									</div>
								</div>
								<h4 class="shop-item-title font-alt">
									<a href="#">Accessories Pack</a>
								</h4>
								Â£9.00
							</div>
						</div>
						<div class="col-sm-6 col-md-3 col-lg-3">
							<div class="shop-item">
								<div class="shop-item-image">
									<img src="/assets/images/shop/product-12.jpg"
										alt="Menâs Casual Pack" />
									<div class="shop-item-detail">
										<a class="btn btn-round btn-b"><span class="icon-basket">Add
												To Cart</span></a>
									</div>
								</div>
								<h4 class="shop-item-title font-alt">
									<a href="#">Menâs Casual Pack</a>
								</h4>
								Â£12.00
							</div>
						</div>
						<div class="col-sm-6 col-md-3 col-lg-3">
							<div class="shop-item">
								<div class="shop-item-image">
									<img src="/assets/images/shop/product-13.jpg" alt="Menâs Garb" />
									<div class="shop-item-detail">
										<a class="btn btn-round btn-b"><span class="icon-basket">Add
												To Cart</span></a>
									</div>
								</div>
								<h4 class="shop-item-title font-alt">
									<a href="#">Menâs Garb</a>
								</h4>
								Â£6.00
							</div>
						</div>
						<div class="col-sm-6 col-md-3 col-lg-3">
							<div class="shop-item">
								<div class="shop-item-image">
									<img src="/assets/images/shop/product-14.jpg" alt="Cold Garb" />
									<div class="shop-item-detail">
										<a class="btn btn-round btn-b"><span class="icon-basket">Add
												To Cart</span></a>
									</div>
								</div>
								<h4 class="shop-item-title font-alt">
									<a href="#">Cold Garb</a>
								</h4>
								Â£14.00
							</div>
						</div>
					</div>
				</div>
			</section>
			<hr class="divider-w">
			<section class="module">
				<div class="container">
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<h2 class="module-title font-alt">Exclusive products</h2>
							<div class="module-subtitle font-serif">The languages only
								differ in their grammar, their pronunciation and their most
								common words.</div>
						</div>
					</div>
					<div class="row">
						<div class="owl-carousel text-center" data-items="5"
							data-pagination="false" data-navigation="false">
							<div class="owl-item">
								<div class="col-sm-12">
									<div class="ex-product">
										<a href="#"><img src="/assets/images/shop/product-1.jpg"
											alt="Leather belt" /></a>
										<h4 class="shop-item-title font-alt">
											<a href="#">Leather belt</a>
										</h4>
										Â£12.00
									</div>
								</div>
							</div>
							<div class="owl-item">
								<div class="col-sm-12">
									<div class="ex-product">
										<a href="#"><img src="/assets/images/shop/product-3.jpg"
											alt="Derby shoes" /></a>
										<h4 class="shop-item-title font-alt">
											<a href="#">Derby shoes</a>
										</h4>
										Â£54.00
									</div>
								</div>
							</div>
							<div class="owl-item">
								<div class="col-sm-12">
									<div class="ex-product">
										<a href="#"><img src="/assets/images/shop/product-2.jpg"
											alt="Leather belt" /></a>
										<h4 class="shop-item-title font-alt">
											<a href="#">Leather belt</a>
										</h4>
										Â£19.00
									</div>
								</div>
							</div>
							<div class="owl-item">
								<div class="col-sm-12">
									<div class="ex-product">
										<a href="#"><img src="/assets/images/shop/product-4.jpg"
											alt="Leather belt" /></a>
										<h4 class="shop-item-title font-alt">
											<a href="#">Leather belt</a>
										</h4>
										Â£14.00
									</div>
								</div>
							</div>
							<div class="owl-item">
								<div class="col-sm-12">
									<div class="ex-product">
										<a href="#"><img src="/assets/images/shop/product-5.jpg"
											alt="Chelsea boots" /></a>
										<h4 class="shop-item-title font-alt">
											<a href="#">Chelsea boots</a>
										</h4>
										Â£44.00
									</div>
								</div>
							</div>
							<div class="owl-item">
								<div class="col-sm-12">
									<div class="ex-product">
										<a href="#"><img src="/assets/images/shop/product-6.jpg"
											alt="Leather belt" /></a>
										<h4 class="shop-item-title font-alt">
											<a href="#">Leather belt</a>
										</h4>
										Â£19.00
									</div>
								</div>
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