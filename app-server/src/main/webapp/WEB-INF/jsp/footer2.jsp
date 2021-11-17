<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ======= Footer ======= -->
  <footer id="footer" class="section-bg">
    <div class="footer-top">
      <div class="container">

        <div class="row">

          <div class="col-lg-6">

            <div class="row">

              <div class="col-sm-6">

                <div class="footer-info">
                  <h3>🎓 오늘의 공부</h3>
                </div>

                <div class="footer-newsletter">
                  <h4>TODAY STUDY</h4>
                  <p style="margin:0;">오늘의 공부는 웹 사이트 내에서 스터디 모임,</p>
                  <p style="margin:0;">장소 탐색 예약 기능을 제공하고 있으며</p>
                  <p style="margin:0;">별도의 복잡한 프로그램 설치 없이 편리하게</p>
                  <p style="margin:0;">이용할 수 있도록 제공하는 사이트입니다.</p>
                  <br>
                  <form action="" method="post">
                    <input type="email" name="email"><input type="submit" value="사이트 소개">
                  </form>
                </div>

              </div>

              <div class="col-sm-6">
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

                <div class="footer-links">
                  <h4>Contact Us</h4>
                  <p>
                    상호명 : 오늘의 공부 <br>
                    주소 : (우)1111 서울특별시 강남구<br>
                    역삼동 819-3 삼오빌딩<br>
                    <strong>FAX:</strong> 0505-111-1111<br>
                    <strong>Email:</strong> bit.study2@gmail.com<br>
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

          <div class="col-lg-6">

            <div class="form">

              <h4>Send us a message</h4>

              <form action="forms/contact.php" method="post" role="form" class="php-email-form">
                <div class="form-group">
                  <input type="text" name="name" class="form-control" id="name" placeholder="Your Name" required>
                </div>
                <div class="form-group mt-3">
                  <input type="email" class="form-control" name="email" id="email" placeholder="Your Email" required>
                </div>
                <div class="form-group mt-3">
                  <input type="text" class="form-control" name="subject" id="subject" placeholder="Subject" required>
                </div>
                <div class="form-group mt-3">
                  <textarea class="form-control" name="message" rows="5" placeholder="Message" required></textarea>
                </div>

                <div class="my-3">
                  <div class="loading">Loading</div>
                  <div class="error-message"></div>
                  <div class="sent-message">Your message has been sent. Thank you!</div>
                </div>

                <div class="text-center"><button type="submit" title="Send Message">✉ 이메일 보내기</button></div>
              </form>

            </div>

          </div>

        </div>

      </div>
    </div>

    <div class="container">
      <div class="copyright">
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
