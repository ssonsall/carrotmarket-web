<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>login</title>
<%@include file="../include/favicons.jsp"%>
<%@include file="../include/stylesheets.jsp"%>
</head>
<body data-spy="scroll" data-target=".onpage-navigation" data-offset="60">
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
							<h2 class="module-title font-alt">Login</h2>
							<div class="module-subtitle font-serif"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<form role="form" method="post" action="/user/loginProc">
								<div class="col-sm-8">
									<label class="sr-only" for="name">username</label> <input class="form-control" type="text" id="username" name="username"
										placeholder="username*" required="required" data-validation-required-message="Please enter your name." />
									<p class="help-block text-danger"></p>
									<label class="sr-only" for="email">password</label> <input class="form-control" type="password" id="password" name="password"
										placeholder="password*" required="required" data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>
								

								<div class="col-sm-4">
									<button class="btn btn-block btn-round btn-d" id="cfsubmit" type="submit" style="height: 85px">Submit</button>
								</div>
								<div class="text-center">
									
									<div class="col-sm-8">
										<a href="javascript:;" class="btn_social" data-social="google"> <img src="/oAuth/btn_google_signin_dark_focus_web@2x.png" alt="google">
										</a>
									</div>
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
	<!--  
    JavaScripts
    =============================================
    -->
	<script>
		var social = document.getElementsByClassName("btn_social");
		for (var i = 0; i < social.length; ++i) {
			social[i].addEventListener("click", function() {
				var socialType = this.getAttribute("data-social");
				location.href = "/oauth2/authorization/" + socialType;
				console.log("click!!!");
			}, false);
		}
	</script>
	<%@include file="../include/script.jsp"%>
</body>
</html>