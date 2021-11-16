<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>오늘의 공부</title>
<link rel="icon" href="${contextPath}/img/favicon.ico" type="image/x-icon" sizes="16x16" style="background-color: rgba(255, 255, 255, 0);">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<link rel="stylesheet" href="${contextPath}/css/common.css">

<style>

p#music {
width: 100%;
background-color: whitesmoke;
}

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

/*body 시작*/
section {
  width:100%;
  z-index:500;
  margin: 0 auto;
}

.contents {
  background-color: beige;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
}

.background {
  width: 100%;
  background-color: rgb(219, 211, 209);
}

.contents .c1 {
  width: 1200px;
  height: 800px;
  background-color: rgb(219, 211, 209);
  text-align: center;
  margin: 0 auto;
  position: relative;
}

.contents .c1 #photo_1 {
    position: absolute;
    z-index: 9999;
    top: 35%;
    left: 25%;
    margin: 0 auto;
}

.contents .p1_back {
width: 600px;
height: 166px;
position: absolute;
    z-index: 999;
    top: 35%;
    left: 25%;
    margin: 0 auto;
   background-color: gray;
   border-radius: 50px;
   padding: 15px;
   opacity: 30%;
}

.contents .c1 #main_1 {
  opacity: 35%;
}

.background_2 {
  width: 100%;
  background-color: rgb(247, 231, 215);
}

.contents .c2 {
  width: 1200px;
  height: 800px;
  background-color: rgb(247, 231, 215);
  display: flex;
  flex-direction: row;
  margin: 0 auto;
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

.background_3 {
  width: 100%;
  background-color: rgb(247, 247, 247);
}

.contents .c3 {
  width: 1200px;
  height: 800px;
  background-color: rgb(247, 247, 247);
  text-align: center;
  margin: 0 auto;
  position: relative;
  display: flex;
  justify-content: flex-end;
  flex-direction: column;
}

.contents .c3 #main_3 {
  opacity: 35%;
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

</style>
</head>

<body>
<div id="first"></div>
    <jsp:include page="header.jsp"/>

<jsp:include page="mainPopup.jsp"/>

  <section>
    <div class="contents">
    
    <div class="background">
      <div class="c1">
        <div class="box">
            <img src="${contextPath}/img/logo.png" id="photo_1">
            <div class="p1_back"></div>
        </div>
         <img src="${contextPath}/img/main_1.JPG" id="main_1">
      </div>
    </div>
    
     <div class="background_2">
         <div class="c2">
           <div class="c2-leftbox" id="second">오늘의 공부 텍스트 박스</div>
           <div class="c2-rightbox"><img src="${contextPath}/img/main_2.jpg" id="main_2"></div>
         </div>
     </div>
     
    <div class="background_3">
       <div class="c3">
         <img src="${contextPath}/img/main_3.JPG" id="main_3">
       </div>
    </div>
    
   </div>
   </section>
    
    <nav id="navbar-example2" class="navbar navbar-light bg-light px-3 navbarbtn">
      <a class="navbar-brand" href="#first">
      <i class="fas fa-graduation-cap fa-2x"></i></a>
    </nav>
<!-- 
<p id="music" align="center"><iframe width="20" height="20" src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/131362507&amp;color=%2397cbff&amp;auto_play=true&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;show_teaser=true" frameborder="no" scrolling="no" allow="autoplay"></iframe></p>

    <jsp:include page="footer.jsp"/>
     -->
    
</body>
</html>
