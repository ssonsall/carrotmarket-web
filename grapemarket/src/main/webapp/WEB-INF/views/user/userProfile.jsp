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
<title>Titan | Multipurpose HTML5 Template</title>
<%@include file="../include/favicons.jsp"%>
<%@include file="../include/stylesheets.jsp"%>
<script src='/map/lib/proj4js-combined.js'></script>
</head>

<body data-spy="scroll" data-target=".onpage-navigation"
	data-offset="60">
	<main>
		<div class="page-loader">
			<div class="loader">Loading...</div>
		</div>
		<%@include file="../include/nav.jsp"%>
		<div class="main">
			<section class="module-small">
				<div class="container">
					<div class="row">


						<!-- 프로필 시작 -->

						<c:if test="${authNeeded eq 1}">
							<script>
								alert("주소인증필요");
							</script>
						</c:if>


						<!-- null 처리 -->
						<div class="col-sm-4 col-md-3 sidebar">
							<div class="widget">
								<div class="col-sm-12 col-md-9" style="margin-bottom: 10%;">
									<img src="assets/images/market/default.jpg" alt="">
								</div>
							</div>
							<div class="widget">
								<h1>${user.username}</h1>
							</div>
							<div class="widget">
								<h1>유저 address</h1>
							</div>
						</div>
						<!-- 프로필 끝 -->


						<div class="col-sm-8 col-sm-offset-1">
							<div role="tabpanel">
								<ul class="nav nav-tabs font-alt" role="tablist" id="tablist">
									<li class="active" id="tablist-profile"><a href="#profile"
										data-toggle="tab"><span class="icon-profile-male"></span>유저정보</a></li>
									<li id="tablist-profile"><a href="#myTown"
										data-toggle="tab"><span class="icon-globe"></span>동네설정</a></li>
									<li id="tablist-townAuth"><a href="#townAuth"
										data-toggle="tab"><span class="icon-compass"></span>동네인증</a></li>
									<li id="tablist-sales" onactivate="getState()"><a
										href="#sales" data-toggle="tab"><span class="icon-browser"></span>거래내역</a></li>
									<li id="tablist-serviceCenter"><a href="#serviceCenter"
										data-toggle="tab"><span class="icon-tools-2"></span>고객센터</a></li>
									<li id="tablist-share"><a href="#share" data-toggle="tab"><span
											class="icon-tools-2"></span>공유하기</a></li>
								</ul>



								<div class="tab-content">

									<div class="tab-pane active" id="profile"
										style="margin-top: 5%;">
										<div class="col-sm-6 col-sm-offset-3 col-md-8"
											style="margin-left: auto;">
											<form role="form" method="post" action="/user/update"
												enctype="multipart/form-data">
												<input type="hidden" name="id" value="${user.id}"> <input
													type="hidden" name="currentUserProfile"
													value="${user.userProfile}">
												<div class="form-group">
													<label class="sr-only" for="name">username</label> <input
														class="form-control" type="text" id="username"
														name="username" placeholder="${user.username}"
														required="required"
														data-validation-required-message="Please enter your name."
														readonly="readonly" value="${user.username}" />
													<p class="help-block text-danger"></p>
												</div>

												<div class="form-group">
													<label class="sr-only" for="name">name</label> <input
														class="form-control" type="text" id="name" name="name"
														placeholder="${user.name}" required="required"
														data-validation-required-message="Please enter your name."
														readonly="readonly" value="${user.name}" />
													<p class="help-block text-danger"></p>
												</div>
												<div class="form-group">
													<label class="sr-only" for="password">password</label> <input
														class="form-control" type="password" id="password"
														name="password" placeholder="password*"
														required="required"
														data-validation-required-message="Please enter your email address." />
													<p class="help-block text-danger"></p>
												</div>
												<div class="form-group">
													<label class="sr-only" for="checkpassword">checkpassword</label>
													<input class="form-control" type="password"
														id="checkpassword" name="checkpassword"
														placeholder="checkpassword*" required="required"
														data-validation-required-message="Please enter your name." />
													<p class="help-block text-danger"></p>
												</div>
												<div class="form-group">
													<label class="sr-only" for="email">email</label> <input
														class="form-control" type="text" id="email" name="email"
														placeholder="email*" required="required"
														data-validation-required-message="Please enter your email address."
														value="${user.email}" />
													<p class="help-block text-danger"></p>
												</div>
												<div class="form-group">
													<label class="sr-only" for="phone">phone</label> <input
														class="form-control" type="text" id="phone" name="phone"
														placeholder="phone*" required="required"
														data-validation-required-message="Please enter your email address."
														value="${user.phone}" />
													<p class="help-block text-danger"></p>
												</div>
												<div class="form-group">
													<label class="sr-only" for="userProfile">photo</label> <input
														class="form-control" type="file" id="userProfile"
														name="userProfile" placeholder="photo*" accept="image/*"
														data-validation-required-message="Please enter your email address." />
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

									<div class="tab-pane" id="myTown" style="margin-top: 5%;">
										<div class="col-sm-6 col-sm-offset-3 col-md-10"
											style="margin-left: auto;">

											<form name="form" id="form" method="post"
												action="/user/addupdate">
												<input type="hidden" name="id" value="${user.id}">

												<div id="callBackDiv form-group col-md-10">
													<div class="form-group">
														<label class="sr-only" for="name">도로명주소</label> <input
															class="form-control" type="text" id="roadFullAddr"
															name="address" value="${user.address}"
															required="required"
															data-validation-required-message="Please enter your name."
															readonly="readonly" />
														<p class="help-block text-danger"></p>
													</div>
													<div class="form-group">
														<label class="sr-only" for="name">Y좌표</label> <input
															class="form-control" type="hidden" id="entX"
															name="addressX" value="${user.addressX}"
															required="required"
															data-validation-required-message="Please enter your name."
															readonly="readonly" />
														<p class="help-block text-danger"></p>
													</div>
													<div class="form-group">
														<label class="sr-only" for="name">X좌표</label> <input
															class="form-control" type="hidden" id="entY"
															name="addressY" value="${user.addressY}"
															required="required"
															data-validation-required-message="Please enter your name."
															readonly="readonly" />
														<p class="help-block text-danger"></p>
													</div>

												</div>
												<div class="text-center col-md-4">
													<input class="btn btn-block btn-round btn-d" id="cfsubmit"
														type="button" onClick="goPopup();" placeholder="Search"
														value="동네설정" /> <input
														class="btn btn-block btn-round btn-d" id="cfsubmit"
														type="button" onclick="addUpdate()" value="저장" />
												</div>
											</form>

											<script>
												function addUpdate() {
													var form = new FormData(
															document
																	.getElementById('form'));
													var url = '/user/addupdate'

													fetch(
															url,
															{
																method : "post",
																body : form,
																contentType : "application/json; charset=utf-8"
															})
															.then(
																	function(
																			res) {
																		res
																				.json()
																	})
															.then(
																	function(
																			res) {

																		localStorage
																				.setItem(
																						"from",
																						"mytown");
																		localStorage
																				.setItem(
																						"state",
																						"auth");

																		location.href = '/user/userProfile';
																	});
												}
											</script>

											<div class="ajax-response font-alt" id="contactFormResponse"></div>
										</div>
									</div>

									<div class="tab-pane" id="townAuth" style="margin-top: 5%;">
										<div class="col-sm-6 col-sm-offset-3 col-md-10"
											style="margin-left: auto;">
											<form role="form" method="post" id="townAuthForm"
												action="/user/authupdate">
												<input type="hidden" name="id" value="${user.id}"> <input
													type="hidden" id="addressX" name="addressX"
													value="${user.addressX}"> <input type="hidden"
													id="addressY" name="addressY" value="${user.addressY}">
												<div class="form-group">
													<label class="sr-only" for="name">address Auth</label>
													<div style="display: none;">
														<p id="demo"></p>
														<p id="check"></p>
													</div>
													<c:choose>
														<c:when test="${user.addressAuth==1}">
															<input class="form-control" type="text" id="username"
																name="username" placeholder="주소인증이 완료되었습니다."
																data-validation-required-message="Please enter your name."
																readonly="readonly" />
														</c:when>
														<c:otherwise>
															<input class="form-control" type="text" id="check"
																name="username" placeholder="주소인증이 필요합니다"
																data-validation-required-message="Please enter your name."
																readonly="readonly" />
														</c:otherwise>
													</c:choose>

													<p class="help-block text-danger"></p>
												</div>
												<c:choose>
													<c:when test="${user.addressAuth==0}">
														<div class="text-center col-md-4">
															<input class="btn btn-block btn-round btn-d"
																id="cfsubmit" type="button" onClick="getLocation();"
																placeholder="Search" value="인증하기" />
														</div>
													</c:when>
												</c:choose>



											</form>
										</div>
									</div>

									<!-- 거래내역 시작 -->
									<div class="tab-pane" id="sales">
										<h3>거래내역</h3>
										<div class="container">
											<div class="row multi-columns-row">
												<div id="stateList" class="col-sm-8">

													<!-- 아이템 -->
													<!-- <div class="menu">
														<div class="row">
															<div class="col-sm-6">
																<h4 class="menu-title font-alt">Meatloaf with Black
																	Pepper-Honey BBQ</h4>
															</div>
															<div class="col-sm-2">
																<h4 class="menu-title font-alt">작성시간</h4>
															</div>
															<div class="col-sm-4 menu-price-detail">
																<h4 class="menu-price font-alt">$16.4</h4>
															</div>
														</div>
													</div> -->


												</div>

											</div>
										</div>
									</div>
									<!-- 거래내역 끝 -->

									<!-- 고객센터 시작 -->
									<div class="tab-pane" id="serviceCenter">
										<div class="panel-group" id="accordion">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title font-alt">
														<a class="collapsed" data-toggle="collapse"
															data-parent="#accordion" href="#support1">포도마켓 운영정책</a>
													</h4>
												</div>
												<div class="panel-collapse collapse" id="support1">
													<div class="panel-body">우리 동네의 따뜻한 거래를 위해 다음과 같은 약속을
														꼭꼭 지켜주세요. 포도마켓의 거래 매너를 잘 지켜주세요. 거래약속은 꼭 지켜주세요. 이왕이면 근처에서
														직거래를 해주세요. 판매금지 물품은 판매할 수 없어요. 광고/홍보는 포도마켓 광고주센터를 통한 지역
														광고만 허용됩니다. 최대 2개의 지역에 게시글을 올리고 거래할 수 있어요. 중복 게시글이나 도배는 안
														돼요. 카테고리를 준수해주세요. 거래 관련 문제가 생겼을 때는 거래 및 환불 정책을 따르고 있어요.
														거래분쟁이 생겼을 때 상대방의 닉네임을 공개하면서 명예를 훼손하는 글을 올리면 안 돼요.(사기나
														거래불이행 경우 제외) 서비스의 이용제한 위의 약속을 포함 포도마켓의 운영 정책을 벗어나는 경우, 경고
														및 이용 제한을 하고 있습니다. 포도마켓 서비스 내에서 관계 법령을 위반하는 경우, 서비스 시스템 및
														다른 사용자의 정상적인 서비스 이용을 방해하는 활동을 하는 경우 또는 범죄행위(사기, 성범죄, 폭력 전과
														등) 기록이 있거나 확인되는 경우 정상적인 사용자의 신속한 보호를 위해 사전 안내 없이 위반/방해 활동
														사용자의 서비스 이용이 한시적, 영구적으로 제한될 수 있습니다.</div>
												</div>
											</div>
											<div class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title font-alt">
														<a class="collapsed" data-toggle="collapse"
															data-parent="#accordion" href="#support2">동네인증이 안돼요!</a>
													</h4>
												</div>
												<div class="panel-collapse collapse" id="support2">
													<div class="panel-body">동네 인증을 하기 전, 현재 위치가 인증하려고 하는
														동네에 있는지 확인해주세요. 인증하려는 동네가 현재 위치와 다르다면, 해당 동네에 있을 때 인증을
														시도해주세요. 동네인증이 안 되는 경우는 크게 4가지예요. 첫 번째, 휴대폰에서 위치정보승인을 안 한
														경우 해결방법은 아래와 같습니다. 안드로이드 (안드로이드 6.0 버전 이상) 휴대폰의 '설정 > 개인정보
														보호 및 안전 > 앱 권한 > 위치' 메뉴에서 포도마켓 앱을 찾아 위치정보를 허용해주세요. iOS
														휴대폰의 '설정 > 개인정보 보호 > 위치서비스' 메뉴에서 포도마켓 앱을 찾아 위치정보를 허용해주세요.
														두 번째, GPS 설정이 잘못된 경우(안드로이드만 해당) 휴대폰 설정 - 연결 - 위치 - 위치 인식
														방식으로 들어간다. (갤럭시 s6 기준) 위치 인식 방식이 "높은 정확도"로 잘 선택되어 있는지
														확인한다. 만약 다르게 되어있다면 "높은 정확도"로 바꾸어 준다. 디바이스마다 위치 설정 위치가 다르니
														설정에서 위치 관련 설정 메뉴를 확인해보세요. (하단 이미지 확인) 세 번째, 위치정보를 켜도 휴대폰이
														현 위치를 못 가져오는 경우 해결방법은 아래와 같습니다. 창가나 야외에서 시도해본다. 여전히 안되면
														네이버 지도나 다음 지도 등 지도 어플을 실행시켜 현 위치를 가져오기를 시도해본다. 포도마켓 어플을
														실행시켜서 다시 한 번 동네인증을 시도한다. 네 번째, 현재 설정된 동네에 내가 위치하지 않을 때
														동네인증이 실패합니다. 내 동네가 제대로 설정되어 있는지 확인해보세요. 현위치로 동네를 변경하기 위해서는
														'나의 포도(더보기) - 내 동네 설정하기'에서 동네를 변경할 수 있어요!</div>
												</div>
											</div>
											<div class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title font-alt">
														<a class="collapsed" data-toggle="collapse"
															data-parent="#accordion" href="#support3">어떤 경우에 이용
															제재 당하나요?</a>
													</h4>
												</div>
												<div class="panel-collapse collapse" id="support3">
													<div class="panel-body">아래와 같은 행위를 한 경우 포도마켓 운영원칙에 따라
														이용 제재를 당할 수 있어요. 거래 불이행 (입금 지연, 거래 물품 미발송, 환불 지연 등) 비매너
														(거래 약속 파기, 거래 매너 위반, 비매너 평가 및 경고 누적 등) 성희롱 부적합한 서비스 (서비스
														목적에 위반하는 서비스 사용) 채팅 남용 욕설 및 명예훼손 스팸 및 어뷰징 활동 홍보/광고 기타
														(포도마켓 운영정책에 위배되는 행위를 한 경우) 거래 상대방이 이용 제한되었다면 그 이유를 찾아보세요.
														이용 제한이 해제되면 해당 문구는 나오지 않습니다. 간혹, 상대방이 오류로 표시된다거나 잘못 처리되었다는
														등의 이야기를 한다면 주의하세요!</div>
												</div>
											</div>
											<div class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title font-alt">
														<a class="collapsed" data-toggle="collapse"
															data-parent="#accordion" href="#support4">판매금지 품목이
															목록에서 보여요~ </a>
													</h4>
												</div>
												<div class="panel-collapse collapse" id="support4">
													<div class="panel-body">판매금지 품목을 발견하시는 경우 신고 부탁드립니다.
														신고해주시면 운영정책에 따라 경고 및 제재 처리됩니다. 참고로, 누가 신고 했는지는 알 수 없습니다.
														신고를 많이 해주셔야 더 빠른 운영 처리를 할 수 있어요.</div>
												</div>
											</div>
											<div class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title font-alt">
														<a class="collapsed" data-toggle="collapse"
															data-parent="#accordion" href="#support5">거래 및 환불 정책
														</a>
													</h4>
												</div>
												<div class="panel-collapse collapse" id="support5">
													<div class="panel-body">당근마켓의 모든 거래는 기본적으로 거래 당사자들의
														결정을 전제로 합니다. 간혹 거래하다가 이견이 있어도 거래 당사자들끼리 매너 있는 대화를 통해 해결하는
														것을 권장하고 있어요. 하지만 의견이 달라 합의가 어려운 경우, 당근마켓은 아래와 같은 정책을 따르고
														있습니다. 거래하다가 문제가 발생하더라도 당근마켓의 기본매너를 지키는 건 잊지 말아 주세요. 서로의
														입장을 이해해주는 당근주민이 되어주세요. 액정 너머 나와 똑같은 사람이 있다는 것을 기억해주세요.</div>
												</div>
											</div>
										</div>
									</div>
									<!-- 고객센터 끝 -->

									<div class="tab-pane" id="share">공유하기</div>
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


	<script src='/map/address.js'></script>
	<%@include file="../include/script.jsp"%>
	<script>
		$(document)
				.ready(
						function() {

							var from = localStorage.getItem('from');
							var state = localStorage.getItem('state');

							if (from === 'detail') {
								if (state === 'complete') {
									document.querySelector('.tab-pane.active').className = 'tab-pane';
									document.querySelector('li.active').className = '';
									document.querySelector('#sales').className = 'tab-pane active';
									document.querySelector('#tablist-sales').className = 'active';
									getState();
								}

							}

							if (from === 'mytown') {
								if (state === 'auth') {
									document.querySelector('.tab-pane.active').className = 'tab-pane';
									document.querySelector('li.active').className = '';
									document.querySelector('#townAuth').className = 'tab-pane active';
									document.querySelector('#tablist-townAuth').className = 'active';
								}
							}

							localStorage.setItem("from", "");
							localStorage.setItem("state", "");
						});
	</script>
</body>

</html>