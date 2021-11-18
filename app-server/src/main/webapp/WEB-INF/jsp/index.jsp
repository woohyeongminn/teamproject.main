<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ======= 메인페이지 ======= -->

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>오늘의 공부</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
<%--   <link href="${contextPath}/css/assets/img/favicon.png" rel="icon"> --%>
<link rel="icon" href="${contextPath}/img/favicon.ico" type="image/x-icon" sizes="16x16" style="background-color: rgba(255, 255, 255, 0);">
  <link href="${contextPath}/css/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Montserrat:300,400,500,600,700" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${contextPath}/css/assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="${contextPath}/css/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/css/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${contextPath}/css/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="${contextPath}/css/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link rel="stylesheet" href="${contextPath}/css/assets/css/style.css">
  <!-- =======================================================
  * Template Name: Rapid - v4.6.0
  * Template URL: https://bootstrapmade.com/rapid-multipurpose-bootstrap-business-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <jsp:include page="header.jsp"/>

  <!-- 메인 팝업(얘 닫히는 게 이상함) -->
  <jsp:include page="mainPopup2.jsp"/>

  <!-- ======= Hero Section ======= -->
  <section id="hero" class="clearfix">
    <div class="container d-flex h-100">
      <div class="row justify-content-center align-self-center" data-aos="fade-up">
        <div class="col-lg-6 intro-info order-lg-first order-last" data-aos="zoom-in" data-aos-delay="100">
          <h2>Today Study<br>for Your <span>Life!</span></h2>
          <div>
            <a href="${contextPath}/app/signup" class="btn-get-started scrollto">🎓 시작하기</a>
          </div>
        </div>

        <div class="col-lg-6 intro-img order-lg-last order-first" data-aos="zoom-out" data-aos-delay="200">
          <img src="${contextPath}/css/assets/img/meet.png" alt="" class="img-fluid">
        </div>
      </div>

    </div>
  </section><!-- End Hero -->

  <main id="main">

    <!-- ======= About Section ======= -->
    <section id="about" class="about">

      <div class="container" data-aos="fade-up">
        <div class="row">

          <div class="col-lg-5 col-md-6">
            <div class="about-img" data-aos="fade-right" data-aos-delay="100">
              <img src="${contextPath}/img/main_2.jpg" alt="" id="main_2">
            </div>
          </div>

          <div class="col-lg-7 col-md-6">
            <div class="about-content" data-aos="fade-left" data-aos-delay="100">
              <h2>Welcome</h2>
              <h3>'오늘의 공부'에 오신 걸 환영합니다!</h3>
              <p style="margin:0px">오늘의 공부에서 원하는 모임과 스터디를 개설하고 새로운 사람들을 만나고,</p>
              <p>모임 장소까지 한번에 예약할 수 있습니다.</p>
              <ul>
                <li><i class="bi bi-check-circle"></i> 새로운 스터디 개설하기!</li>
                <li><i class="bi bi-check-circle"></i> 스터디 카페 예약까지 한번에!</li>
                <li><i class="bi bi-check-circle"></i> 나만의 스터디를 효율적으로 관리!</li>
              </ul>
            </div>
          </div>
        </div>
      </div>

    </section><!-- End About Section -->

    <!-- ======= Services Section ======= -->
    <section id="services" class="services section-bg">
      <div class="container" data-aos="fade-up">

        <header class="section-header">
          <h3>Today Study</h3>
          <p>현재 이용 중인 스터디를 둘러보시고 원하는 스터디에 가입 신청을 넣어 보세요!</p>
        </header>

        <div class="row">
        <c:forEach items="${studyList}" var="study">
          <div class="col-md-6 col-lg-4" data-aos="zoom-in" data-aos-delay="100">
            <div class="box">
              <div class="icon" style="background: #fff0da;"><i class="bi bi-card-checklist" style="color: #e98e06;"></i></div>
              <h4 class="title"><a href="${contextPath}/app/study/detail?studyno=${study.studyNo}">${study.studyTitle}</a></h4>
              
              <c:choose>
                <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus ne '2'}">
                  <p id="wanted" style="text-align: right;font-size:12px;font-family: fantasy;margin: 0;">모집중</p>
                </c:when>
                <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus ne '2'}">
                  <p id="wanted" style="text-align: right;font-size:12px;font-family: fantasy;margin: 0;">모집중</p>
                </c:when>
                <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus eq '2'}">
                  <p id="wanted" style="text-align: right;font-size:12px;font-family: fantasy;margin: 0;">모집완료</p>
                </c:when>
                <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus eq '2'}">
                  <p id="wanted" style="text-align: right;font-size:12px;font-family: fantasy;margin: 0;">모집완료</p>
                </c:when>
              </c:choose>
              
              <p style="text-align: justify; -webkit-text-stroke-width: thin;">${study.introduction}</p>
              <p class="description">인원 ${study.countMember}/${study.numberOfPeple}</p>
              <p class="description">${study.subjectName} | ${study.faceName}</p>
              <p class="description">${study.owner.perNickname} ⭐${study.countBookMember}</p>
            </div>
          </div>
        </c:forEach>

      </div>

      </div>
    </section><!-- End Services Section -->

<!--
<i class="bi bi-bookmarks" style="color: #f058dc;"></i>
<i class="bi bi-box-seam" style="color: #ffb774;"></i>
<i class="bi bi-card-checklist" style="color: #589af1;"></i>
-->

    <!-- ======= Call To Action Section ======= -->
    <section id="call-to-action" class="call-to-action">
      <div class="container" data-aos="zoom-out">
        <div class="row">
          <div class="col-lg-9 text-center text-lg-start">
            <h3 class="cta-title">👋 Hello</h3>
            <p class="cta-text" style="margin: 0;"> 질문이 있으신가요?</p>
            <p class="cta-text"> 오늘의 공부 챗봇을 통해 실시간으로 문의해 보세요!</p>
          </div>
          <div class="col-lg-3 cta-btn-container text-center">
            <a class="cta-btn align-middle" href="javascript:openchatbot()">🙄 문의하기</a>
          </div>
        </div>

      </div>
    </section><!--  End Call To Action Section -->

    <!-- ======= Features Section ======= -->
    <section id="features" class="features">
      <div class="container" data-aos="fade-up">

        <div class="row feature-item">
          <div class="col-lg-6" data-aos="fade-right" data-aos-delay="100">
            <img src="${contextPath}/css/assets/img/part2.png" class="img-fluid" alt="">
          </div>
          <div class="col-lg-6 wow fadeInUp pt-5 pt-lg-0" data-aos="fade-left" data-aos-delay="150">
          <br>
            <h4>나만의 스터디 매니저</h4>
            <hr><br>
            <p><i class="bi bi-check-circle" id="iconcircle"></i>
              장소 탐색 및 예약 가능
            </p>
            <p><i class="bi bi-check-circle" id="iconcircle"></i>
              나만의 공부 스타일 등록
            </p>
            <p><i class="bi bi-check-circle" id="iconcircle"></i>
              화상 채팅 기능을 통한 비대면 모임 활성화
            </p>
            <p><i class="bi bi-check-circle" id="iconcircle"></i>
              가격 및 다양한 옵션을 체크하여 맞춤형 장소 제공
            </p>
            <p><i class="bi bi-check-circle" id="iconcircle"></i>
              누적 공부 시간 기록, 투두 리스트 등 편리한 기능
            </p>
          </div>
        </div>

      </div>
    </section><!-- End Features Section -->

    <!-- ======= Cafe Section ======= -->
    <section id="pricing" class="pricing section-bg wow fadeInUp">

      <div class="container" data-aos="fade-up">

        <header class="section-header">
          <h3>Weekly Study Cafe</h3>
          <p>이번주 별점이 높은 순위의<br>스터디 카페를 추천해드립니다!</p>
        </header>

        <div class="row flex-items-xs-middle flex-items-xs-center">

          <!-- Weekly Study Cafe  -->
          <div class="col-xs-12 col-lg-4" data-aos="fade-up" data-aos-delay="100">
            <div class="card">
              <div class="card-header">
                
              </div>
              <div class="card-block">
                <h4 class="card-title">
                  에이 스터디 카페
                </h4>
                <ul class="list-group">
                  <li class="list-group-item">별점 ⭐5.0</li>
                  <li class="list-group-item">⩗ 친절해요</li>
                  <li class="list-group-item">⩗ 깨끗해요</li>
                </ul>
                <a href="${contextPath}/app/cafe/detail?no=1" class="btn">✔ 예약하기</a>
              </div>
            </div>
          </div>

          <!-- Weekly Study Cafe  -->
          <div class="col-xs-12 col-lg-4" data-aos="fade-up" data-aos-delay="200">
            <div class="card">
              <div class="card-header">
                
              </div>
              <div class="card-block">
                <h4 class="card-title">
                  오호라 스터디 카페
                </h4>
                <ul class="list-group">
                  <li class="list-group-item">별점 ⭐4.2</li>
                  <li class="list-group-item">⩗ 넓고 쾌적해요</li>
                  <li class="list-group-item">⩗ 음료를 제공해줘요</li>
                </ul>
                <a href="${contextPath}/app/cafe/detail?no=5" class="btn">✔ 예약하기</a>
              </div>
            </div>
          </div>

          <!-- Premium Plan  -->
          <div class="col-xs-12 col-lg-4" data-aos="fade-up" data-aos-delay="300">
            <div class="card">
              <div class="card-header">
                
              </div>
              <div class="card-block">
                <h4 class="card-title">
                  비트 스터디 카페 판교센터
                </h4>
                <ul class="list-group">
                  <li class="list-group-item">별점 ⭐4.0</li>
                  <li class="list-group-item">⩗ 노트북 대여가 가능해요</li>
                  <li class="list-group-item">⩗ 이용금액이 저렴해요</li>
                </ul>
                <a href="${contextPath}/app/cafe/detail?no=7" class="btn">✔ 예약하기</a>
              </div>
            </div>
          </div>

        </div>
      </div>

    </section><!-- End Pricing Section -->

    <!-- ======= F.A.Q Section ======= -->
    <section id="faq" class="faq">
      <div class="container" data-aos="fade-up">
        <header class="section-header">
          <h3>Q & A = ?</h3>
          <p>자주 묻는 질문</p>
        </header>

        <ul class="faq-list" data-aso="fade-up" data-aos-delay="100">

          <li>
            <div data-bs-toggle="collapse" class="collapsed question" href="#faq1">스터디 카페 이용 방법?<i class="bi bi-chevron-down icon-show"></i><i class="bi bi-chevron-up icon-close"></i></div>
            <div id="faq1" class="collapse" data-bs-parent=".faq-list">
              <p>
                해당 홈페이지 로그인 후 상단의 "스터디 찾기" 메뉴를 이용해 주세요.
              </p>
            </div>
          </li>

          <li>
            <div data-bs-toggle="collapse" href="#faq2" class="collapsed question">아이디/비밀번호를 까먹었어요<i class="bi bi-chevron-down icon-show"></i><i class="bi bi-chevron-up icon-close"></i></div>
            <div id="faq2" class="collapse" data-bs-parent=".faq-list">
              <p>
                해당 홈페이지 로그인 버튼 클릭 후 하단의 ID/PW를 이용해 주세요. 자세한 사항은 "공지사항"을 참고해 주세요.
              </p>
            </div>
          </li>

          <li>
            <div data-bs-toggle="collapse" href="#faq3" class="collapsed question">스터디 카페 등록 방법이 궁금해요!<i class="bi bi-chevron-down icon-show"></i><i class="bi bi-chevron-up icon-close"></i></div>
            <div id="faq3" class="collapse" data-bs-parent=".faq-list">
              <p>
                스터디 카페(장소) 등록은 실제 운영 중이어야 하며 사업자등록증을 요구하고 있습니다. 자세한 문의는 "오늘의 공부 챗봇" 또는 "문의게시판"을 이용해 주세요.
              </p>
            </div>
          </li>

          <li>
            <div data-bs-toggle="collapse" href="#faq4" class="collapsed question">이메일로 문의 드리고 싶어요!<i class="bi bi-chevron-down icon-show"></i><i class="bi bi-chevron-up icon-close"></i></div>
            <div id="faq4" class="collapse" data-bs-parent=".faq-list">
              <p>
                메인에서 메일 보내기 서비스를 이용하실 수 있습니다. 해당 서비스를 통해 관리자에게 메일을 보내 주세요.
              </p>
            </div>
          </li>
        </ul>

      </div>
    </section><!-- End F.A.Q Section -->

  </main><!-- End #main -->

  <jsp:include page="footer1.jsp"/>

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="${contextPath}/css/assets/vendor/aos/aos.js"></script>
  <script src="${contextPath}/css/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${contextPath}/css/assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="${contextPath}/css/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="${contextPath}/css/assets/vendor/php-email-form/validate.js"></script>
  <script src="${contextPath}/css/assets/vendor/purecounter/purecounter.js"></script>
  <script src="${contextPath}/css/assets/vendor/swiper/swiper-bundle.min.js"></script>

  <!-- Template Main JS File -->
  <script src="${contextPath}/css/assets/js/main.js"></script>

</body>

<script type="text/JavaScript">
function openchatbot() {  
  window.open("todaystudy", "오늘의 공부 챗봇", "width=400px; height=550px;")
}
</script>

</html>