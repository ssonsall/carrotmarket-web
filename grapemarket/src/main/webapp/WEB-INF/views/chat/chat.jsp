<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal" var="principal" />
		</sec:authorize>
		<div class="main">
			<section class="module" style="padding: 70px 0 !important;">
				<div class="container">
					<div class="row">
						<!-- 현재 거래 상품 정보 시작 -->
						<div class="col-sm-4 col-md-3 sidebar">
							<h4 class="font-alt mb-0">현재 거래상품</h4>
							<hr class="divider-w mt-10 mb-20">

							<div id="product_image">
								<!-- 이미지 세로 크기 고정 필요 -->

							</div>
							<!-- 상품 정보 노출되는곳 -->
							<div id="product_info"></div>

						</div>
						<!-- 현재 거래 상품 정보 끝 -->
						<!-- 채팅방 목록 시작 -->
						<div class="col-sm-4 col-sm-offset-1">
							<h4 class="font-alt mb-0">채팅방 목록</h4>
							<hr class="divider-w mt-10 mb-20">
							<h3>구매</h3>
							<c:forEach var="chat" items="${chatForBuy}">
								<c:if test="${chat.buyerState eq 1}">
									<div id="chatId_${chat.id}">
										<div class="alert alert-success" role="alert"
											style="position: relative; padding: 5px;"
											onclick="enterRoom(${chat.id},${principal.user.id})">
											<button class="close" type="button" data-dismiss="alert"
												style="position: absolute; left: 340px; top: -4px;" aria-hidden="true"
												onclick="outChat(${chat.id},'buyer')">&times;</button>
											<img src="/home/ubuntu/upload/${chat.board.image1}"
												style="width: 50px; height: 50px;" />
											<div
												style="position: absolute; top: 12px; left: 70px; font-size: 12px;">
												<span><i class="fa fa-location-arrow"></i>${chat.sellerId.address} :
													 <strong> ${chat.board.title}</strong></span>
												<p style="position: relative;">${chat.sellerId.username}님과의
													채팅방입니다.</p>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
							<h3>판매</h3>
							<c:forEach var="chat" items="${chatForSell}">
								<c:if test="${chat.sellerState eq 1}">
									<div id="chatId_${chat.id}">
										<div class="alert alert-success" role="alert"
											style="position: relative; padding: 5px;"
											onclick="enterRoom(${chat.id},${principal.user.id})">
											<button class="close" type="button" data-dismiss="alert"
												style="position: absolute; left: 340px; top: -4px;" aria-hidden="true"
												onclick="outChat(${chat.id},'buyer')">&times;</button>
											<img src="/home/ubuntu/upload/${chat.board.image1}"
												style="width: 50px; height: 50px;" />
											<div
												style="position: absolute; top: 12px; left: 70px; font-size: 12px;">
												<span><i class="fa fa-location-arrow"></i>${chat.buyerId.address} :
													 <strong> ${chat.board.title}</strong></span>
												<p style="position: relative;">${chat.buyerId.username}님과의
													채팅방입니다.</p>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>


						<!-- 채팅방 목록 끝 -->
						<!-- 채팅방 시작 -->
						<div class="col-sm-4 col-md-3 col-md-offset-1 sidebar">
							<!-- div 생성 - 버튼 입력될 공간/ 구매상태 / 판매상태 결정 -->
							<div id="tradeState"></div>
							<!-- div 생성 - 버튼 입력될 공간/ 구매상태 / 판매상태 결정 -->
							<hr class="divider-w mt-10 mb-20">
							<div id="chat-page">
								<c:forEach var="chat" items="${chatForBuy}" begin="0" end="">
									<iframe id="chatframe" style="width: 400px; height: 500px"
										src=""></iframe>
								</c:forEach>
								<c:forEach var="chat" items="${chatForSell}" begin="0" end="">
									<iframe id="chatframe" style="width: 400px; height: 500px"
										src=""></iframe>
								</c:forEach>

							</div>
						</div>
						<!-- 채팅방 끝 -->
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
	<script>
	$(document).ready(function(){
		if(localStorage.getItem("from") === 'detail'){
			enterRoom(localStorage.getItem('chatId'),localStorage.getItem('buyerId'))
			};
		
		localStorage.setItem("from","");
		
		});
	</script>
</body>
</html>