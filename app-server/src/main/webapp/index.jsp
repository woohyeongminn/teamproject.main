<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>오늘의 공부</title>
<link rel="icon" href="./img/favicon.ico" type="image/x-icon" sizes="16x16">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
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

/*header 시작*/
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 34px;
}

.header_logo {
  font-size: 20px;
  text-align: center;
}

.header_logo a {
  display: inline-block;
  width: 180px;
  height: 60px;
  margin: 0 auto;
  line-height: 60px;
}

.header_logo a img {
  width: 100%;
  vertical-align: middle;
}


.header_menu {
  display: flex;
}

.header_menu li {
  font-size: 14px;
  padding: 8px 34px;
}

.header_menu li a {
  padding: 4px;
  
}
.header_menu li a:hover {
  color: darkgrey;
  box-shadow: inset 0 -2px darkgrey;
  line-height: 20px;
}

.header_login {
 display: flex;
 box-sizing: border-box;
 border-left: 2px solid darkgrey;
}

.header_login li {
  font-size: 12px;
  padding: 2px 8px;
}

@media screen and (max-width: 768px) {
  header {
    flex-direction: column;
    align-self: start;
    padding: 5px 0;
  }

  .header_logo {
    margin: 0;
    width: 100%;
    text-align: center;
    padding-bottom: 5px;
    border-bottom: 1px solid black;
  }
  
  .header_menu {
    flex-direction: column;
    align-items: center;
    width: 100%;
  }

  .header_menu li {
    width: 100%;
    text-align: center;
  }

  .header_login {
    justify-content: center;
    width: 100%;
  }

  .header_login li {
    text-align: center;
    margin: 4px;
  }

  .header_login li a {
    padding: 10px;
  }
}

/*body 시작*/
section {
  width:100%;
  z-index:500;
}

.contents {
  background-color: beige;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
}

.contents .c1 {
  width: 100%;
  height: 800px;
  background-color: rgb(219, 211, 209);
  text-align: center;
}

.contents .c1 #main_1 {
  opacity: 35%;
}

.contents .c2 {
  width: 100%;
  height: 800px;
  background-color: rgb(247, 231, 215);
  display: flex;
  flex-direction: row;
}

.contents .c2 .c2-leftbox {
  width: 50%;
  border: 3px solid red;
  text-align: center;
  padding-top: 100px;
}

.contents .c2 .c2-rightbox {
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.contents .c2 #main_2 {
  max-width: 90%;
  dispaly: inline-block;
}

.contents .c3 {
  width: 100%;
  height: 800px;
  background-color: rgb(247, 247, 247);
}

a.navbar-brand {
color: white;
background-color: gray;
border-radius: 50%;
opacity: 0.3;
padding: 12px 10px;
margin: 0 auto;
display: inline-block;
position: fixed;
right: 30px;
bottom: 20px;
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
      <div class="c1"><img src="img/main_1.JPG" id="main_1">
      </div>
      <div class="c2">
        <div class="c2-leftbox">오늘의 공부 텍스트 박스</div>
        <div class="c2-rightbox"><img src="img/main_2.jpg" id="main_2"></div>
      </div>
      <div class="c3">
        세번째 내용
      </div>
    </div>
    
    </section>
    
    <nav id="navbar-example2" class="navbar navbar-light bg-light px-3 navbarbtn">
      <a class="navbar-brand" href="#first">
      <i class="fas fa-graduation-cap fa-2x"></i></a>
    </nav>

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
</body>
</html>