<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
               <form class="row" action="/board/" method="post">
                  <div class="col-sm-4 mb-sm-20">
                     <select name="id" class="form-control">
                        <option value="1" selected="selected">인기매물</option>
                        <option value="2">디지털/가전</option>
                        <option value="3">가구/인테리어</option>
                        <option value="4">유아동/유아도서</option>
                        <option value="5">생활/가공식품</option>
                        <option value="6">여성의류</option>
                        <option value="7">여성잡화</option>
                        <option value="8">뷰티/미용</option>
                        <option value="9">남성패션/잡화</option>
                        <option value="10">스포츠/레저</option>
                        <option value="11">게임/취미</option>
                        <option value="12">도서/티켓/음반</option>
                        <option value="13">반려동물용품</option>
                        <option value="14">기타 중고물품</option>
                        <option value="15">삽니다</option>
                     </select>
                  </div>
                  <div class="col-sm-3">
                     <button class="btn btn-block btn-round btn-g" type="submit">Apply</button>
                  </div>
               </form>
            </div>
         </section>


         <hr class="divider-w">
         <section class="module-small">
            <div class="container">
               <div class="row multi-columns-row">


                  <c:forEach var="board" items="${boards}">
                     <div class="col-sm-6 col-md-3 col-lg-3">
                        <div class="shop-item">
                           <div class="shop-item-image">
                              <img src="/assets/images/shop/product-7.jpg"
                                 alt="Accessories Pack" />
                              <div class="shop-item-detail">
                                 <a href="/board/detail" class="btn btn-round btn-b"><span class="icon-basket">See
                                       Detail</span></a>
                              </div>
                           </div>
                           <h4 class="shop-item-title font-alt">
                              <a href="#">${board.title}</a>
                           </h4>
                           가격
                        </div>
                     </div>
                  </c:forEach>
               </div>
               
               <!-- 페이징 -->
               <div class="row">
               <div class="col-sm-12">
               <div class="pagination font-alt">
               <c:choose>
					<c:when test="${(param.page+1)%4 ne 0}">
						<fmt:parseNumber var="navPage" value="${param.page/4}" integerOnly="true"></fmt:parseNumber>
					</c:when>
					<c:otherwise>
						<fmt:parseNumber var="navPage" value="${(param.page-1)/4}" integerOnly="true"></fmt:parseNumber>
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
					<a href="/test/page?page=${navPage*4}"><i class="fa fa-angle-left"></i></a>
				</c:if>
					<c:forEach var="i" begin="${navPage*4+1}" end="${navPage*4+4}">
						<c:if test="${i le count}">
							<c:choose>
								<c:when test="${i-1 eq param.page}">
									<a class="active" style="background-color: black;" href="/test/page?page=${i-1}">${i}</a>
								</c:when>
								<c:otherwise>
									<a href="/test/page?page=${i-1}">${i}</a>
								</c:otherwise>
							</c:choose>
							</c:if>
					</c:forEach>
					<c:if test="${navPage lt totalPage}">
						<a href="/test/page?page=${navPage*4+4}"><i class="fa fa-angle-right"></i></a>
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