<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ======= 템플릿에서 사용 ======= -->

<style>
a {
   text-decoration: none;
}

#footer .footer-top .footer-links ul li {
    padding: 3px 0;
    font-size: 10px;
}

#footer .footer-top .footer-links {
    margin-bottom: 15px;
}

#footer .footer-top .social-links a {
    width: 26px;
    height: 26px;
}

p {
    font-size: 10px;
}
</style>
<!-- ======= Footer ======= -->
  <footer id="footer" class="section-bg" style="font-size: 10px;">
    <div class="footer-top" style="padding: 20px 0;">
      <div class="container">
            <div class="row" style="justify-content: center;">
            
              <div class="col-sm-3">
                <div class="footer-info" style="margin-bottom: 0;">
                  <h4>🎓 오늘의 공부</h4>
                </div>
                <div class="footer-newsletter">
                  <p style="margin:0;">TODAY STUDY</p>
                  <p style="margin:0;">오늘의 공부는 웹 사이트 내에서 스터디 모임,</p>
                  <p style="margin:0;">장소 탐색 예약 기능을 제공하고 있으며</p>
                  <p style="margin:0;">별도의 복잡한 프로그램 설치 없이 편리하게</p>
                  <p style="margin:0;">이용할 수 있도록 제공하는 사이트입니다.</p>
                </div>
              </div>

              <div class="col-sm-2">
                <div class="footer-links">
                  <h4>Useful Links</h4>
                  <ul>
                    <li><a href="${contextPath}/app/index">오늘의 공부</a></li>
                    <li><a href="${contextPath}/app/askboard/alllist">이용약관</a></li>
                    <li><a href="#">개인정보처리방침</a></li>
                    <li><a href="${contextPath}/app/adminNotice/list">1:1문의</a></li>
                    <li><a href="#">법적고지</a></li>
                  </ul>
                </div>
              </div>
              
              <div class="col-sm-3">
                <div class="footer-links">
                  <h4>Contact Us</h4>
                  <p>
                    상호명 : 오늘의 공부 <br>
                    주소 : (우)1111 서울특별시 강남구<br>
                    역삼동 819-3 삼오빌딩<br>
                    <strong>FAX :</strong> 0505-111-1111<br>
                    <strong>Email :</strong> bit.study2@gmail.com<br>
                  </p>
                </div>

                <div class="social-links">
                  <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                  <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
                  <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
                  <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
                </div>

              </div>
            </div>
          </div>
        </div>

    <div class="container">
      <div class="copyright" style="padding-top: 15px; font-size: 12px;">
        &copy; Copyright ⓒ2021<strong>TODAYSTUDY</strong>. All Rights Reserved
      </div>
      <div class="credits">
        <!--
        All the links in the footer should remain intact.
        You can delete the links only if you purchased the pro version.
        Licensing information: https://bootstrapmade.com/license/
        Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Rapid
      -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>
  </footer><!-- End  Footer -->
