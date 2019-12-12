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
   <%@include file="include/favicons.jsp" %>
    <%@include file="include/stylesheets.jsp" %>
</head>

<body data-spy="scroll" data-target=".onpage-navigation" data-offset="60">
  <main>
    <div class="page-loader">
      <div class="loader">Loading...</div>
    </div>
    
    <!-- nav -->
      <%@include file="include/nav.jsp" %>
    
    <section class="home-section home-fade home-full-height" id="home">
      <div class="hero-slider">
        <ul class="slides">
          <li class="bg-dark-30 bg-dark shop-page-header"
            style="background-image:url(&quot;assets/images/market/light_purple.jpg&quot;);">
            <div class="titan-caption">

              <div class="row caption-content">
                <div style="margin-left: 15%;">
                  <div class="col-sm-12 col-md-6 col-lg-6" style="float:left;">
                    <div class="font-alt mb-30 titan-title-size-1">This is 502</div>
                    <div class="font-alt mb-30 titan-title-size-4"> PoDo Market</div>
                    <div class="font-alt mb-40 titan-title-size-1">Your online fashion destination</div>
                    <div class="font-alt">
                      <form id="subscription-form" role="form" method="post" action="php/subscribe.php">
                        <div class="input-group col-sm-8 col-md-5 col-lg-5" style=" float:none; margin:0 auto">
                          <input class="form-control" type="email" id="semail" name="semail" placeholder="검색어를 입력하세요"
                            data-validation-required-message="Please enter your email address."
                            required="required" /><span class="input-group-btn">
                            <button class="btn btn-g btn-round" id="subscription-form-submit"
                              type="submit">검색</button></span>
                        </div>
                      </form>
                    </div>
                  </div>
                  <div class="col-sm-6 col-md-3 col-lg-3" style="display:inline-block;">
                    fddsf
                  </div>
                </div>
              </div>

            </div>
          </li>
        </ul>
      </div>
    </section>

    <div class="main">
      <section class="module">
        <div class="container">
          <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
              <h2 class="module-title font-alt">포도마켓 인기 매물</h2>
              <div class="module-subtitle font-serif">The languages only differ in their grammar, their pronunciation
                and their most common words.</div>
            </div>
          </div>
          <div class="row">
            <div class="owl-carousel text-center" data-items="5" data-pagination="false" data-navigation="false">
              <div class="owl-item">
                <div class="col-sm-12">
                  <div class="ex-product"><a href="#"><img src="assets/images/shop/product-1.jpg"
                        alt="Leather belt" /></a>
                    <h4 class="shop-item-title font-alt"><a href="#">Leather belt</a></h4>£12.00
                  </div>
                </div>
              </div>
              <div class="owl-item">
                <div class="col-sm-12">
                  <div class="ex-product"><a href="#"><img src="assets/images/shop/product-2.jpg"
                        alt="Derby shoes" /></a>
                    <h4 class="shop-item-title font-alt"><a href="#">Derby shoes</a></h4>£54.00
                  </div>
                </div>
              </div>
              <div class="owl-item">
                <div class="col-sm-12">
                  <div class="ex-product"><a href="#"><img src="assets/images/shop/product-3.jpg"
                        alt="Leather belt" /></a>
                    <h4 class="shop-item-title font-alt"><a href="#">Leather belt</a></h4>£19.00
                  </div>
                </div>
              </div>
              <div class="owl-item">
                <div class="col-sm-12">
                  <div class="ex-product"><a href="#"><img src="assets/images/shop/product-4.jpg"
                        alt="Leather belt" /></a>
                    <h4 class="shop-item-title font-alt"><a href="#">Leather belt</a></h4>£14.00
                  </div>
                </div>
              </div>
              <div class="owl-item">
                <div class="col-sm-12">
                  <div class="ex-product"><a href="#"><img src="assets/images/shop/product-5.jpg"
                        alt="Chelsea boots" /></a>
                    <h4 class="shop-item-title font-alt"><a href="#">Chelsea boots</a></h4>£44.00
                  </div>
                </div>
              </div>
              <div class="owl-item">
                <div class="col-sm-12">
                  <div class="ex-product"><a href="#"><img src="assets/images/shop/product-6.jpg"
                        alt="Leather belt" /></a>
                    <h4 class="shop-item-title font-alt"><a href="#">Leather belt</a></h4>£19.00
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <section class="module module-video bg-dark-30"
        style="background-image:url(&quot;assets/images/market/light_purple.jpg&quot;);">
        <div class="container">
          <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
              <h2 class="module-title font-alt mb-0">Be inspired. Get ahead of trends.</h2>
            </div>
          </div>
        </div>
      </section>

      <section class="module" id="news">
        <div class="container">
          <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
              <h2 class="module-title font-alt">포도마켓 사용자 후기</h2>
            </div>
          </div>
          <div class="row multi-columns-row post-columns wo-border">
            <div class="col-sm-6 col-md-4 col-lg-4">
              <div class="post mb-40">
                <div class="post-header font-alt">
                  <h2 class="post-title"><a href="#">- 대전 둔산2동</a></h2>
                </div>
                <div class="post-entry">
                  <p>당근에서 거래하는 재미에 쏙 빠졌어요~ 안쓰는 물건 나눔하고 피드백 받는 경험도 너무 좋았어요~ 동네라서 정말 편해요. 넘나 애정하는 어플 응원할게요!</p>
                </div>
                <div class="post-more"><a class="more-link" href="#">Read more</a></div>
              </div>
            </div>
            <div class="col-sm-6 col-md-4 col-lg-4">
              <div class="post mb-40">
                <div class="post-header font-alt">
                  <h2 class="post-title"><a href="#">- 부산 부전제1동</a></h2>
                </div>
                <div class="post-entry">
                  <p>요즘 미니멀라이프 실천 중인데 당근마켓만한 앱이 없어요! 미니멀리즘을 위한 앱이랄까요?ㅎㅎ 동네 직거래라 쉽고 간편해서 너무 좋아요^^ 중고거래에 빠져있답니다❤︎</p>
                </div>
                <div class="post-more"><a class="more-link" href="#">Read more</a></div>
              </div>
            </div>
            <div class="col-sm-6 col-md-4 col-lg-4">
              <div class="post mb-40">
                <div class="post-header font-alt">
                  <h2 class="post-title"><a href="#">- 제주 노형동</a></h2>
                </div>
                <div class="post-entry">
                  <p>요즘 매일 쓰는 벼룩 앱이에요:) 근처 주민이라 믿을만하고 쉽게 거래할 수 있어요~ 동네에서 올라오는 매물 구경하는 맛에 완전 중독됐어요!! 진짜 안써본 사람은 몰라요 ㅋㅋ
                  </p>
                </div>
                <div class="post-more"><a class="more-link" href="#">Read more</a></div>
              </div>
            </div>
            <div class="col-sm-6 col-md-4 col-lg-4">
              <div class="post mb-40">
                <div class="post-header font-alt">
                  <h2 class="post-title"><a href="#">- 인천 주안1동</a></h2>
                </div>
                <div class="post-entry">
                  <p>집에 있는 안 쓰는 물건 팔기 좋아요 :) 동네 사람들이랑 직거래할 수 있어서 소소한 물건 팔기 좋아요!</p>
                </div>
                <div class="post-more"><a class="more-link" href="#">Read more</a></div>
              </div>
            </div>
            <div class="col-sm-6 col-md-4 col-lg-4">
              <div class="post mb-40">
                <div class="post-header font-alt">
                  <h2 class="post-title"><a href="#">- 서울 잠실3동</a></h2>
                </div>
                <div class="post-entry">
                  <p>근처에 있는 사람들끼리 거래하니까 뭔가 더 믿을 수 있는 것 같아요. 동네에서 바로 직거래할 수 있는 물건만 볼 수 있어 좋은 것 같아요.</p>
                </div>
                <div class="post-more"><a class="more-link" href="#">Read more</a></div>
              </div>
            </div>
            <div class="col-sm-6 col-md-4 col-lg-4">
              <div class="post mb-40">
                <div class="post-header font-alt">
                  <h2 class="post-title"><a href="#">- 광주 관천동</a></h2>
                </div>
                <div class="post-entry">
                  <p>아나바다 운동하는 것 같아서 좋아요ㅎ 저한텐 필요 없는 물건이 필요한 사람에게 전달될 수 있는 공간을 열어주셔서 감사합니다!</p>
                </div>
                <div class="post-more"><a class="more-link" href="#">Read more</a></div>
              </div>
            </div>
          </div>
        </div>
      </section>
     <%@include file="include/footer.jsp" %>
    </div>
    <div class="scroll-up"><a href="#totop"><i class="fa fa-angle-double-up"></i></a></div>
  </main>
<%@include file="include/script.jsp" %>
</body>

</html>