<!-- nav.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
 <nav class="navbar navbar-custom navbar-fixed-top navbar-transparent" style ="background-color: black;" role="navigation">
        <div class="container" >
          <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#custom-collapse"><span
                class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span
                class="icon-bar"></span></button><a class="navbar-brand" href="index.html">PODO</a>
          </div>
          <div class="collapse navbar-collapse" id="custom-collapse">
            <ul class="nav navbar-nav navbar-right">
              <li class="dropup"><a class="dropup-toggle" href="/" data-toggle="dropup">홈화면</a>
              </li>
              <li class="dropdown"><a class="dropdown-toggle" href="/board/" data-toggle="dropdown">카테고리</a>
                <ul class="dropdown-menu">
                  <li class="dropdown"><a href="#" data-toggle="dropdown">인기매물</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">디지털/가전</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">가구/인테리어</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">유아동/유아도서</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">생활/가공식품</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">여성의류</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">여성잡화</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">뷰티/미용</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">남성패션/잡화</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">스포츠/레저</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">게임/취미</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">도서/티켓/음반</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">반려동물용품</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">기타 중고물품</a></li>
                  <li class="dropdown"><a href="#" data-toggle="dropdown">삽니다</a></li>
                </ul><!--  -->
              </li>
              <li class="dropup"><a class="dropup-toggle" href="/board/writeForm" data-toggle="dropup">상품등록</a>
              </li>
              <li class="dropup"><a class="dropup-toggle" href="/chat/" data-toggle="dropup">채팅</a>
              </li>
              <li class="dropup"><a class="dropup-toggle" href="/user/userProfile" data-toggle="dropup">유저정보</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>