<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


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
							<img style="width: 555px; height: 625.94px;" id="mainImage"
								src="/home/ubuntu/upload/${board.image1}" alt="Single Product Image" />
							<!-- ìí ìì ì¬ì§ ìì-->
							<ul class="product-gallery">
								<li><img style="width: 83.25px; height: 93.89px;" id="img1"
									onClick="changeImg(1)" src="/home/ubuntu/upload/${board.image1}"
									alt=" No Image" /></li>
								<c:if test="${!empty board.image2}">
									<li><img style="width: 83.25px; height: 93.89px;"
										id="img2" onClick="changeImg(2)" src="/home/ubuntu/upload/${board.image2}"
										alt=" No Image" /></li>
								</c:if>
								<c:if test="${!empty board.image3}">
									<li><img style="width: 83.25px; height: 93.89px;"
										id="img2" onClick="changeImg(3)" src="/home/ubuntu/upload/${board.image3}"
										alt=" No Image" /></li>
								</c:if>
								<c:if test="${!empty board.image4}">
									<li><img style="width: 83.25px; height: 93.89px;"
										id="img2" onClick="changeImg(4)" src="/home/ubuntu/upload/${board.image4}"
										alt=" No Image" /></li>
								</c:if>
								<c:if test="${!empty board.image5}">
									<li><img style="width: 83.25px; height: 93.89px;"
										id="img2" onClick="changeImg(5)" src="/home/ubuntu/upload/${board.image5}"
										alt=" No Image" /></li>
								</c:if>

								<!-- 								<li><img style="width: 83.25px; height: 93.89px;" id="img3" onClick="changeImg(3)"
									src="/home/ubuntu/upload/${board.image3}" alt=" No Image" /></li>
								<li><img style="width: 83.25px; height: 93.89px;" id="img4" onClick="changeImg(4)"
									src="/home/ubuntu/upload/${board.image4}" alt=" No Image" /></li>
								<li><img style="width: 83.25px; height: 93.89px;" id="img5" onClick="changeImg(5)"
									src="/home/ubuntu/upload/${board.image5}" alt="" /></li> -->
							</ul>




							<script>
                                function changeImg(id) {
                                    var img = document
                                        .getElementById("mainImage");
                                    if (id == 1) {
                                        img.src = "/home/ubuntu/upload/${board.image1}";
                                    } else if (id == 2) {
                                        img.src = "/home/ubuntu/upload/${board.image2}";
                                    } else if (id == 3) {
                                        img.src = "/home/ubuntu/upload/${board.image3}";
                                    } else if (id == 4) {
                                        img.src = "/home/ubuntu/upload/${board.image4}";
                                    } else if (id == 5) {
                                        img.src = "/home/ubuntu/upload/${board.image5}";
                                    }

                                }
                            </script>
						</div>
						<div class="col-sm-6">
							<div class="row">
								<div class="col-sm-12">
									<h1 style="font-size: 40px;" class="product-title font-alt">${board.title}</h1>


									<form id="likeForm">
										<input type="hidden" name="board" value="${board.id}">

										<div id="liked">
											<c:choose>
												<c:when test="${!empty liked}">
													<span onclick="like()" style="font-size: 30px;">🧡</span>
												</c:when>
												<c:otherwise>
													<span onclick="like()" style="font-size: 30px;">🤍<span>
												</c:otherwise>
											</c:choose>
										</div>
										<div id="likeCount">
											<p>${likeCount}명의회원이이상품을좋아합니다.</p>
										</div>
									</form>
									<%-- <c:choose>
										<c:when test="${likeCount ne 0}">
										</c:when>
										<c:otherwise>
											<div id="likeCount">
												<p></p>
											</div>
										</c:otherwise>
									</c:choose> --%>




									<div class="row mb-20">
										<div class="col-sm-12">
											<div class="product_meta">
												<c:choose>
													<c:when test="${board.category eq 3}">
                                                        디지털/가전 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 4}">
                                                        가구/인테리어 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 5}">
                                                        유아동/유아도서 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 6}">
                                                        생활/가공식품 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 7}">
                                                        여성의류 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 8}">
                                                        여성잡화 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 9}">
                                                        뷰티/미용 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 10}">
                                                        남성패션/잡화 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 11}">
                                                        스포츠/레저 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 12}">
                                                        게임/취미 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 13}">
                                                        도서/티켓/음반 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 14}">
                                                        반려동물용품 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 15}">
                                                        기타 중고물품 · ${board.createDate}
                                                    </c:when>
													<c:when test="${board.category eq 16}">
                                                        삽니다 · ${board.createDate}
                                                    </c:when>
												</c:choose>
												<!-- <a href="#"> enum 생성후 카테고리 입력 </a> -->
											</div>
										</div>
									</div>
									<h4 style="color: gray;">${board.user.address}·
										${board.user.name}</h4>
									<%-- <h4 style="color: gray;">${board.user.address}</h4> --%>
								</div>
							</div>
							<div class="row mb-20">
								<div class="col-sm-12">

									<!-- 별모양 / 별점 -->
									<!-- <span><i class="fa fa-star star"></i></span><span><i
										class="fa fa-star star"></i></span><span><i
										class="fa fa-star star"></i></span><span><i
										class="fa fa-star star"></i></span><span><i
										class="fa fa-star star-off"></i></span><a
										class="open-tab section-scroll" href="#reviews">-2customer
										reviews</a> -->
								</div>
							</div>
							<div class="row mb-20">
								<div class="col-sm-12">
									<div class="price font-alt">
										<span class="amount">${board.price} 원</span>
									</div>
								</div>
							</div>



							<div class="row mb-20">
								<div class="col-sm-12">
									<div class="description">
										<p>${board.content}</p>
									</div>
								</div>
							</div>
							<div class="row mb-20">
								<!-- <div class="col-sm-4 mb-sm-20">
									<input class="form-control input-lg" type="number" name=""
										value="1" max="40" min="1" required="required" />
								</div> -->
								<c:choose>
									<c:when test="${principal.user.id ne board.user.id}">
										<div class="col-sm-8">
											<!-- 채팅생성 -->
											<form id="createChat">
												<input type="hidden" name="board" value="${board.id}" /> <input
													type="hidden" name="buyerId" value="${principal.user.id}">
												<input type="hidden" name="sellerId"
													value="${board.user.id}">
											</form>

											<c:choose>
												<c:when test="${board.state eq 0}">
													<button onclick="createChat()" class="btn btn-round btn-d"
														type="button">채팅으로 거래하기</button>
												</c:when>
												<c:otherwise>
													<button onclick="" class="btn btn-round btn-d"
														type="button">거래 종료 아이템</button>
												</c:otherwise>
											</c:choose>




											<button onclick="boardReportPopup(${board.id})"
												class="btn btn-round btn-d" type="button">게시글 신고하기</button>
										</div>
									</c:when>
									<c:otherwise>

										<c:choose>
											<c:when test="${board.state eq 0 }">
												<div class="col-sm-8">
													<form id="boardUpdate" style="display: inline;"
														action="/board/updateForm/${board.id}" method="post">
														<%-- DB Select 안할거면 여기서 다 날려야 하지만 그렇게 안할거임 귀찮 --%>
														<button class="btn btn-round btn-d" type="submit">글
															수정</button>
													</form>
													<form id="boardDelete" style="display: inline;"
														action="/board/delete/${board.id}" method="post">
														<button class="btn btn-round btn-d" type="submit">글
															삭제</button>
													</form>

													<form id="selectForm">
														<input type="hidden" name="id" value="${board.id}">
														<select style="position: relative; top: 10px;"
															name="Buyer" id='selectBox_Buyer' class="form-control">
															<c:forEach var="tradeState" items="${tradeStates}">

																<c:choose>
																	<c:when test="${tradeState.state eq '구매완료'}">
																		<option value="${tradeState.user.id}">${tradeState.user.name}
																			★</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${tradeState.user.id}">${tradeState.user.name}</option>
																	</c:otherwise>
																</c:choose>

															</c:forEach>
															<option value="-1">판매취소</option>
														</select>
													</form>
													<button style="position: relative; top: 18px;"
														onclick="boardComplete(${board.id})"
														class="btn btn-round btn-d">거래 완료</button>
												</div>
											</c:when>
											<c:otherwise>
												<div class="col-sm-8">
													<form id="boardUpdate" style="display: inline;"
														action="/board/updateForm/${board.id}" method="post">
														<%-- DB Select 안할거면 여기서 다 날려야 하지만 그렇게 안할거임 귀찮 --%>
													</form>
													<form id="boardDelete" style="display: inline;"
														action="/board/delete/${board.id}" method="post"></form>

													<form id="selectForm">
														<input type="hidden" name="id" value="${board.id}">
														<select style="position: relative; top: 10px;"
															name="Buyer" id='selectBox_Buyer' class="form-control">
															<c:forEach var="tradeState" items="${tradeStates}">

																<option selected="selected" disabled="disabled" value="">${board.buyer.name}</option>

															</c:forEach>
														</select>
													</form>
												</div>
											</c:otherwise>
										</c:choose>




									</c:otherwise>
								</c:choose>
							</div>





						</div>
					</div>
					<div class="row mt-70">
						<div class="col-sm-12">
							<ul class="nav nav-tabs font-alt" role="tablist">
								<li class="active"><a href="#reviews" data-toggle="tab"><span
										class="icon-tools-2"></span>comment</a></li>
							</ul>
							<!-- ëê¸ êµ¬ì­ ìì-->
							<div class="comments_reviews" id="comments_reviews">

								<c:forEach var="comment" items="${comments}">

									<div id="comment_id_${comment.id}" class="comment clearfix">
										<div class="comment-avatar">
											<img src="/home/ubuntu/upload/${comment.user.userProfile}" alt="avatar" />
										</div>
										<div class="comment-content clearfix">
											<div class="comment-author font-alt">
												${comment.user.username}
												<c:if
													test="${principal.user.username eq comment.user.username}">
													<span style="font-size: 20px"
														onclick="commentDelete(${comment.id})">🗑</span>
													<span style="font-size: 20px"
														onclick="commentReportPopup(${comment.id})">🚨</span>
												</c:if>

											</div>
											<div class="comment-body">
												<p>${comment.content}</p>
											</div>
											<div class="comment-meta font-alt">
												${comment.createDate}
											</div>
										</div>

									</div>

								</c:forEach>
							</div>
							<!-- ëê¸ êµ¬ì­ ë -->

							<!-- ëê¸ ì°ê¸° ìì -->



							<div class="comment-form mt-30">
								<h4 class="comment-form-title font-alt">Add review</h4>


								<form id="comment-submit">
									<div class="row">
										<div class="col-sm-4">
											<div class="form-group">
												<label class="sr-only" for="name">Name</label> <input
													type="hidden" name="board" value="${board.id}" /> <input
													type="hidden" name="user" value="${principal.user.id}" />
												<input class="form-control" id="username" type="text"
													name="username" value="${principal.user.username}"
													placeholder="${principal.user.username}"
													readonly="readonly" />
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label class="sr-only" for="email">Name</label> <input
													class="form-control" id="email" type="text" name="email"
													placeholder="${principal.user.email}"
													value="${principal.user.email}" readonly="readonly" />
											</div>
										</div>
										<div class="col-sm-12">
											<div class="form-group">
												<textarea class="form-control" id="comment_area"
													name="content" rows="4" placeholder="Review"></textarea>
											</div>
										</div>
										<div class="col-sm-12">
											<button onclick="commentWrite()" class="btn btn-round btn-d"
												type="button">Submit Review</button>
										</div>
									</div>
								</form>


								<script>

                                </script>
							</div>
							<!-- ëê¸ ì°ê¸° ë -->
						</div>

					</div>
				</div>
			</section>
			<hr class="divider-w">
			<%@include file="../include/footer.jsp"%>

		</div>
		<div class="scroll-up">
			<a href="#totop"><i class="fa fa-angle-double-up"></i></a>
		</div>
	</main>
	<%@include file="../include/script.jsp"%>
	<script type="text/javascript">
        var popupX = (window.screen.width / 2) - (500 / 2);
        var popupY = (window.screen.height / 2) - (600 / 2);

        function boardReportPopup(id) {
            window.open("/report/ReportForm?id=" + id + "&type=board", "a", "width=500, height=600, left=" +
                popupX + ", top=" + popupY);
        }

        function commentReportPopup(id) {
            window.open("/report/ReportForm?id=" + id + "&type=board", "a", "width=500, height=600, left=" +
                popupX + ", top=" + popupY);
        }
    </script>
</body>

</html>