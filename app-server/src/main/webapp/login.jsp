<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>오늘의 공부-로그인</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<style>
  html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed, 
figure, figcaption, footer, header, hgroup, 
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: top;
}

body {
  line-height: 1.5;
}

ul li {
  list-style-type: none;
}

a {
  text-decoration: none;
  color: black;
}

/*body 시작-------------------------------------*/
section {
  width:100%;
  z-index:500;
}

.contents {
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  position: relative;
}

.contents .c-top {
  width: 100%;
  padding: 60px 0 15px 50px;
  font-weight: bold;
  background-color: #f0e3d4;
}

.contents .c1 {
  margin-right: 20px;
  height: 800px;
  padding-left:30px;
}

.contents .c1 #box {
  margin: 20px;
  height: 180px;
  margin-top: 100px;
}

.contents .c1 .card {
  border: none;
  border: 1px solid rgba(0,0,0,.125);
  border-radius: 10px;
  padding: 30px;
}

.contents .c1 .card span .btn {
  background-color: #ccc0ae;
  border  :1px solid #ccc0ae;
  color: white;
}

.contents .c1 .card .btn:hover {
  background-color: #f0e3d4;
  border  :1px solid #f0e3d4;
  color:  #616161;
}

.contents .c1 .card .card-text {
  margin-bottom: 16px;
  font-size: small;
}

/*footer 시작*/
footer {
  font-size: 14px;
  padding: 8px 0;
  background-color: whitesmoke;
}

.footer_company {
  display: flex;
  margin-left: 20px;
}

.footer_company li a{
  padding: 2px 10px 2px 0;
}

.footer_address {
  margin-left: 20px;
}

.footer_copyright {
  margin-left: 20px;
}

</style>
</head>

<body>
<jsp:include page="header.jsp"/>
    <section>
    <div class="contents">
      <div class="c-top">
        | 로그인 할 계정을 선택해주세요.
      </div>
      <div class="c1 row">
        <div class="col" id="box">
          <div class="card">
              <h4 class="card-title">관리자&ensp;<i class="fas fa-child"></i></h4>
              <span class="card-text">관리자 로그인<br> 페이지로 이동합니다.</span>
              <span class="but"><a href="admin/form" class="btn btn-primary">LOGIN</a></span>
            </div>
        </div>

        <div class="col" id="box">
          <div class="card">
              <h4 class="card-title">개인 회원</h4>
              <span class="card-text">개인 회원 로그인<br> 페이지로 이동합니다.</span>
              <span><a href="member/form" class="btn btn-primary">LOGIN</a>&emsp;
              <!-- 네이버 로그인 버튼 노출 영역 -->
			  <div id="naver_id_login"></div>
			  <!-- //네이버 로그인 버튼 노출 영역 -->
			  <script type="text/javascript">
			    var naver_id_login = new naver_id_login("wh8zH3W5STAuoCbw48Fz", "http://localhost:8080/ogong/adminNotice/list");
			    var state = naver_id_login.getUniqState();
			    naver_id_login.setButton("green", 1, 40);
			    naver_id_login.setDomain("http://localhost:8080/ogong/login");
			    naver_id_login.setState(state);
			    naver_id_login.setPopup();
			    naver_id_login.init_naver_id_login();
			  </script>
			  </span>
            </div>
        </div>

        <div class="col" id="box">
          <div class="card">
              <h4 class="card-title">기업 회원</h4>
              <span class="card-text">기업 회원 로그인<br> 페이지로 이동합니다.</span>
              <span><a href="ceomember/form" class="btn btn-primary">LOGIN</a></span>
            </div>
        </div>
      </div>


    </div>
    </section>

    <footer>
      <ul class="footer_company">
        <li><a href="#">오늘의 공부 소개</a></li>
        <li><a href="#">이용약관</a></li>
        <li><a href="#" class="orange">개인정보처리방침</a></li>
        <li><a href="#">1:1문의</a></li>
        <li><a href="#">법적고지</a></li>
        <li><a href="#">사이트맵</a></li>
      </ul>
      <hr>
      <div class="footer_address">
        <p>상호명 : 오늘의 공부&emsp;ㅣ&emsp;주소 : (우)1111 서울특별시 강남구 역삼동 819-3 삼오빌딩</p>
        <p>FAX : 0505-111-1111&emsp;ㅣ&emsp;Email: bit.study2@gmail.com</p>
        <p>고객센터 : 1577-1111 (평일 09:00 ~ 18:00 / 점심 12:00 ~ 13:00)</p>
        <p>사업자등록번호 : 391-11-1111&emsp;ㅣ&emsp;통신판매업신고번호: 제 2021-서울강남-1111 호 ㅣ 대표: 엄땡땡</p>
      </div>
      <hr>
      <p class="footer_copyright">COPYRIGHTⓒ2021 TODAYSTUDY. ALL RIGHTS RESERVED.</p>
    </footer>

  </div>
</body>
</html>
