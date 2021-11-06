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
  padding: 20px 0 20px 50px;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
}

.contents .c1 {
  margin-right: 20px;
  height: 600px;
  padding-left:30px;
}

.contents .c1 #box {
  margin: 20px;
  height: 180px;
  margin-top: 100px;
}

.contents .c1 .card {
  border: none;
  border: 1px solid darkgray;
  border-radius: 10px;
  padding: 30px;
}
/* 
.contents .c1 .card span .btn {
  background-color: rgb(247, 231, 215);
  border  :1px solid rgb(247, 231, 215);
  color: #616161;
}

.contents .c1 .card .btn:hover {
  background-color:  rgb(219, 211, 209);
  border  :1px solid rgb(219, 211, 209);
  color:  white;
} */

.contents .c1 .card .card-text {
  margin-bottom: 16px;
  font-size: small;
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
              <span class="but"><a href="admin/form" class="btn btn-outline-dark">LOGIN</a></span>
            </div>
        </div>

        <div class="col" id="box">
          <div class="card">
              <h4 class="card-title">개인 회원</h4>
              <span class="card-text">개인 회원 로그인<br> 페이지로 이동합니다.</span>
              <span><a href="member/form" class="btn btn-outline-dark">LOGIN</a>&emsp;
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
              <span><a href="ceomember/form" class="btn btn-outline-dark">LOGIN</a></span>
            </div>
        </div>
      </div>


    </div>
    </section>
 <jsp:include page="footer.jsp"/>


  </div>
</body>
</html>
