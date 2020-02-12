<!-- nav.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<nav class="navbar navbar-custom navbar-fixed-top navbar-transparent" style="background-color: black;" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#custom-collapse">
				<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">PODO</a>
		</div>
		<div class="collapse navbar-collapse" id="custom-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropup"><a class="dropup-toggle" href="/" data-toggle="dropup">홈화면</a></li>
				<li class="dropup"><a class="dropup-toggle" href="/board/page?page=0&category=1&userInput=&range=5"
					data-toggle="dropup">포도장터</a></li>
				<li class="dropup"><a class="dropup-toggle" href="/board/writeForm" data-toggle="dropup">상품등록</a></li>
				<li class="dropup"><a class="dropup-toggle" href="/chat/" data-toggle="dropup">채팅</a></li>

				<c:choose>
					<c:when test="${empty principal.user.id}">
						<li class="dropup"><a class="dropup-toggle" href="/user/join" data-toggle="dropup">회원가입</a></li>
						<li class="dropup"><a class="dropup-toggle" href="/user/login" data-toggle="dropup">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li class="dropup"><a class="dropup-toggle" href="/user/userProfile" data-toggle="dropup">내 정보</a></li>
						<li class="dropup"><a class="dropup-toggle" href="/user/logout" data-toggle="dropup">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
				<c:if test="${!empty principal.user.id}">
					<div>
						<span style="position: absolute; right: 10px;"> 
							<a class="dropup-toggle" href="/user/userProfile" data-toggle="dropup">
								<c:choose>
									<c:when test="${pricipal.user.provider eq 'local'}">
										<img src="/grapemarket-web/grapemarket/target/classes/static/home/ubuntu/grapemarket-web/grapemarket/src/main/resources/static/upload/${principal.user.userProfile}"
											style="width: 50px; height: 50px;">
									</c:when>
									<c:otherwise>
										<img src="/home/ubuntu/grapemarket-web/grapemarket/src/main/resources/static/upload/${principal.user.userProfile}" style="width: 50px; height: 50px;">
									</c:otherwise>
								</c:choose> ${principal.user.name}
							</a>
						</span>
					</div>
				</c:if>

			</ul>
		</div>
	</div>
</nav>