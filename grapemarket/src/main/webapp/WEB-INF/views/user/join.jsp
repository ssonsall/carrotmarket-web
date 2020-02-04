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
<title>join</title>
<%@include file="../include/favicons.jsp"%>
<%@include file="../include/stylesheets.jsp"%>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>

<script>
	$(document).ready(function() {
		usernameCheck();
		passwordCheck();
	});
</script>

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
							<h2 class="module-title font-alt">Join</h2>
							<div class="module-subtitle font-serif"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<form role="form" method="post" action="/user/joinProc"
								enctype="multipart/form-data">
								<div class="form-group">
									<label class="sr-only" for="name">username</label> <input
										class="form-control" type="text" id="username" name="username"
										placeholder="username*" required="required"
										data-validation-required-message="Please enter your username." />
									<p class="help-block text-danger"></p>
								</div>
								<div id="usernameCheck" class="usernameCheck">중복확인</div>
								<div class="form-group">
									<label class="sr-only" for="name">name</label> <input
										class="form-control" type="text" id="name" name="name"
										placeholder="name*" required="required"
										data-validation-required-message="Please enter your name." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="email">password</label> <input
										class="form-control" type="password" id="password"
										name="password" placeholder="password*" required="required"
										data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="name">checkpassword</label> <input
										class="form-control" type="password" id="checkPassword"
										required="required" placeholder="check password*"
										data-validation-required-message="checkpassword." />
									<p class="help-block text-danger"></p>
								</div>
								<div id="passwordCheck">비밀번호 중복확인</div>
								<div class="form-group">
									<label class="sr-only" for="email">email</label> <input
										class="form-control" type="text" id="email" name="email"
										placeholder="email*" required="required"
										data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="email">phone</label> <input
										class="form-control" type="text" id="phone" name="phone"
										placeholder="phone*" required="required"
										data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<label class="sr-only" for="userProfile">photo</label> <input
										class="form-control" type="file" id="userProfile"
										name="profile" placeholder="photo*" accept="image/*"
										data-validation-required-message="Please enter your email address." />
									<p class="help-block text-danger"></p>
								</div>
								<input type="hidden" name="userProfile" value="">
								<div class="text-center">
									<button class="btn btn-block btn-round btn-d" type="submit">Submit</button>
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

	<script type="text/javascript">
		function usernameCheck() {
			$("#username").keyup(function() {
				var username = $("#username").val();
				if(username != ""){
				$.ajax({
					type : "POST",
					dataType : "text",
					contentType : "application/json; charset=utf-8",
					data : username,
					url : "/user/usernameCheck",
					success : function(data) {
						if (data == "no") {
							var x = document.getElementById("usernameCheck");
							x.style.color = "red";
							x.innerHTML ="사용할수 없는 아이디입니다."
						} else if (data == "ok") {
							var x = document.getElementById("usernameCheck");
							x.style.color = "blue";
							x.innerHTML ="사용 가능한 아이디입니다."
						}
					},
					error : function(error) {
						alert("Error!");
					}
				});
				}else{
					var x = document.getElementById("usernameCheck");
					x.style.color = "black";
					x.innerHTML ="중복확인";
					}
			});
		};
		
		let usernameCheckB = false;
		let passwordCheckB = false;

		function passwordCheck() {
			$("#password").keyup(pCheck);
			$("#checkPassword").keyup(pCheck);

			function pCheck() {
				var pw_val = document.getElementById("password").value;
				var pwc_val = document.getElementById("checkPassword").value;
				var x = document.getElementById("passwordCheck");
				if (pwc_val == "" || pw_val == "") {
					passwordCheckB = false;
					x.style.color = "black";
					x.innerHTML = "비밀번호를 입력하세요"
				} else {
					if (pw_val == pwc_val) {
						passwordCheckB = true;
					} else {
						passwordCheckB = false;
					}
					if (passwordCheckB) {
						x.style.color = "blue";
						x.innerHTML = "사용 가능한 비밀번호입니다."
					} else {
						x.style.color = "red";
						x.innerHTML = "비밀번호가 일치하지않습니다."
					}
				}

			}
		}
	</script>

	<%@include file="../include/script.jsp"%>
</body>
</html>