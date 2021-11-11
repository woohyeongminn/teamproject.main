<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<style>
* {
	font-size: 14px;
}
	
 a {
   text-decoration:none;
}
 
 .all-content {
  width: 100%;
  margin: 0 auto;
  height: 800px;
}
 
.template-content {
    height: 1300px;
}

ul {
list-style:none;
}

.tabmenu{ 
  max-width:900px; 
  margin: 0 auto; 
  position:relative; 
}

.tabmenu > ul {
  padding: 0;
}

.tabmenu > ul > li{
  display:  inline-block;
  width:33.33%; 
  float:left;  
  text-align:center; 
  background :#f9f9f9;
}

.tabmenu > ul > li > a{
  display:block;
  line-height:40px;
  height:40px;
  text-decoration:none; 
  color: #000;
}

.tabCon{
  display:none; 
  padding: 20px;
  position:absolute;
  left:0;
  box-sizing: border-box; 
  border : 5px solid #f9f9f9;
  width: 900px;
  height: 620px;
}

.btnCon:target  {
  background : #ccc;
}

.btnCon:target .tabCon {
  display: block;
}

.btnCon:target .tabbtn {
  font-weight: bold;
}

.cafe-top {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-bottom: 15px;
}

#content {
  display: block;
  width: 100%;
  padding: 10px 10px 0 10px;
}
  
.cafe-form {  
  text-align: left;
}
  
.cafe-form > label {
	width: 120px;
	font-weight: bold;
	padding: 5px 0;
}

.cafe-form > span {
	    display: inline-block;
    width: 122px;
	padding: 5px 0;
}

#c-image {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
}

.cafe-bottom {
  width: 100%;
  text-align: left;
  padding: 5px 10px;
}

.cafe-bottom > label {
	 width: 15%;
	 font-weight: bold;
	 padding: 5px 0;
}

.cafe-bottom > span {
	width: 80%;
	padding: 5px 0;
}

.label-wrap {
  width: 100%;
  height: fit-content;
  display: flex;
  flex-direction: row;
  
}

.label-wrap > label {
   width: 15%;
   font-weight: bold;
   padding: 5px 0;
}

.label-wrap > span {
  width:80%;
  height:80px;
  padding: 5px 0;
  overflow: scroll;
}

.cafe-bottom-review {
  width: 100%;
  padding: 0 10px 30px 10px;
  text-align: left;
}
 
.line {
	 width: 100%;
	 height: 4px;
	 background: gray;
}

.review-wrap {
  width: 830px;
  height: 180px;
  overflow: scroll;
}

#c-review-content {
  margin: 0;
}

#c-grade {

}
  
#c-review {
  background-color: whitesmoke;
  height: fit-content;
  margin-bottom: 10px;
  padding: 10px;
}
  
button {
  border: 0;
  background: transparent;
}

.btn_wrap {
  max-width: 420px;
  margin: 20px auto 0;
  text-align: center;
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-bottom: 100px;
}
 
.btn_wrap .btn {
  margin: 0 7px;
  padding: 5px 10px;
  height: auto;
  line-height: inherit;
}

/* 슬라이드 */
* {box-sizing: border-box;}
body {font-family: Verdana, sans-serif;}
.mySlides {display: none;}
img {vertical-align: middle;}

/* Slideshow container */
.slideshow-container {
  max-width: 1000px;
  position: relative;
  margin: auto;
  height:300px;
  overflow: hidden;
  background-color: lightsteelblue;
  margin: 10px;
  display: inline-block;
}

/* Caption text */
.text {
  color: #000000;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */
.dot {
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.active {
  background-color: #717171;
}

/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .text {font-size: 11px}
}
</style>
</head>
<script type="text/javascript">
var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
}
</script>



<body>
<br><br><br>
<div class="all-content"> 
  <div class="tabmenu">
  <ul>
   <li id="tab1" class="btnCon"><a class="tabbtn first" href="../detail">내 프로필</a>
	   <div class="tabCon" >
	   </div>
   </li>
   <li id="tab2" class="btnCon"><a class="tabbtn" href="cafe/detail">내 카페</a>
    <div class="tabCon" style="height: fit-content;">
		  <c:choose>
		  <c:when test='${empty cafe}'>
		     <span>등록된 카페가 없습니다.</span><br>
		     <a href='addform' class="btn btn-outline-dark">카페 등록하러 가기</a>
		  </c:when>
		  
		  <c:otherwise>
		  <div class = "cafe-top">
	    <h4>[${cafe.name}]</h4>
	    
	    <!-- 카페 이미지 목록 (슬라이드) -->
       <!-- <span id='c-image'><img src="../../img/aaa.jpg" style="width: 370px; height: 200px;"></span> -->
        <div class="slideshow-container">
					<div class="mySlides fade">
					  <div class="numbertext">1 / 3</div>
					  <img src="../../img/aaa.jpg" style="width:100%">
					  <div class="text">Caption One</div>
					</div>
					
					<div class="mySlides fade">
					  <div class="numbertext">2 / 3</div>
					  <img src="../../img/bbb.jpg" style="width:100%">
					  <div class="text">Caption Two</div>
					</div>
					
					<div class="mySlides fade">
					  <div class="numbertext">3 / 3</div>
					  <img src="../../img/ccc.jpg" style="width:100%">
					  <div class="text">Caption Three</div>
					</div>
					
					</div>
					
					<div style="text-align:center">
					  <span class="dot"></span> 
					  <span class="dot"></span> 
					  <span class="dot"></span> 
					</div>
       </div>



      <!-- 카페 상세 글 부분 -->      
		  <div id='content'>
		    <form action='updateform' class="cafe-form" method='post' enctype="multipart/form-data">
		    <input id='c-no' type='hidden' value='${cafe.no}'>
		    <label for='f-bossName'>대표자</label><span>${cafe.ceoMember.ceoBossName}</span><br>
		    <label for='f-licenseNo'>사업자 등록번호</label><span>${cafe.ceoMember.ceoLicenseNo}</span><br>
		    <div class="label-wrap"><label for='f-location'>주소</label> <span style="height: fit-content;">${cafe.location}</span></div>
		    </form>
		  </div>
		  <div class="cafe-bottom">
		    <div class="label-wrap"><label for='f-info'>소개글</label><span style="height: fit-content;">${cafe.info}</span></div>
		    <label for='f-tel'>전화번호</label><span>${cafe.phone}</span><br>
		    <label for='f-openTime'>운영시간</label><span>${cafe.openTime} AM ~ ${cafe.closeTime} PM</span><br>
		    <label for='f-holiday'>이번주 휴무일</label><span>${cafe.holiday}</span><br>
		    <label for='f-viewCount'>상태</label><span>${cafeStatus}</span><br>
		    <label for='f-review'>리뷰평점</label><span>⭐${cafe.avgReview} / ${cafe.countReview}개</span>
		  </div>
		  
		  
		  
		  <!-- 리뷰 시작부분에 선 -->
		  <div class="cafe-bottom-review">
			  <hr style="border-top: 4px double #bbb; text-align: center; display: inline-block; width: 48%; margin: 6px 0">
			  <i class="far fa-comments" style="color:#bbb; font-size: large;"></i>
			  <hr style="border-top: 4px double #bbb; text-align: center; display: inline-block; width: 47.5%;  margin: 6px 0">
		  
		  
		  
		  
		  <!-- 리뷰 보여지는 부분 -->
		   <c:if test='${not empty reviewList}'>
		    <div class = "review-wrap">
		     <c:forEach items="${reviewList}" var="review">
		      <div id='c-review'>
		      <p id='c-review-content'>${review.content}</p>
	         <span id='c-grade'>
	           <c:set var="grade" value="${review.grade}" /> 
	              <% 
	              int grade = (int) pageContext.getAttribute("grade");
	              String star = CafeHandlerHelper.getReviewGradeStatusLabel(grade);
	              pageContext.setAttribute("star", star);
	              %>
	           <span style="font-size: 12px;">${star}(${review.grade}/5)</span>
	         </span>
	         <span style="font-size: 12px;"> | ${review.member.perNickname} | ${review.registeredDate}</span>
		      </div>
		     </c:forEach>
		     </div>
		   </c:if>
		   <c:if test='${empty reviewList}'>
		     <p>등록된 리뷰가 없습니다.</p><br>  
		   </c:if>
		   </div>
		   
		   
		   <!-- 버튼 -->
		   <div id='button_wrap'>
		     <button id='b-but' type="submit" value="수정" formaction="updateform">
		        <a href='updateform?cafeno=${cafe.no}' class="btn btn-outline-dark">스터디카페 수정</a>
		     </button>
		     
		     <button id='b-but' type="submit" value="삭제" formaction="deleteform">
		        <a href='deleteform?cafeno=${cafe.no}' class="btn btn-outline-dark">스터디카페 삭제</a>
		     </button>
		     
		       <!--
		        <button onclick="window.open('http://localhost:8080/ogong/ceomember/cafe//deleteform?no=${cafe.no}&ceono=${ceoMember.ceoNo}','deleteform','width=430,height=200,location=no,status=no,scrollbars=yes');">스터디카페 삭제</button>
		       -->
		     
		      <button id='b-but' type="submit" value="스터디룸관리">
		        <a href='room/list?cafeno=${cafe.no}' class="btn btn-outline-dark">스터디룸 관리</a>
		      </button>
		      <button id='b-but' type="submit" value="예약관리">
		        <a href='reser/list' class="btn btn-outline-dark">예약 관리</a>
		      </button>
		    </div>
		   </c:otherwise>
		  </c:choose>
		  </div>
     </li>
     
     
     
     <li id="tab3" class="btnCon"><a class="tabbtn" href="#tab3">내 문의내역</a>
      <div class="tabCon" >
        <!-- <a href='../askboard/mylist' class = "btn btn-outline-dark">내 문의게시판</a> -->
      </div>
     </li>
  </ul>
  </div>
</div>
<script>
  location.href = "#tab2";
</script>
</body>
</html>