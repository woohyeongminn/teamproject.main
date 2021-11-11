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
 
.c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
  text-align: center;
  font-size: 16px;
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
  height: 200px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 15px;
}
  
#aside {
  width: 45%;
  height: 100%;
  background-color: lightsteelblue;
  margin: 10px;
  display: inline-block;
}
  
#content {
  display: block;
  width: 53%;
  margin: 10px;
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
	width: 300px;
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
  
.cafe-bottom-review {
  width: 100%;
  padding: 10px 20px;
  text-align: left;
}
 
.line {
	 width: 100%;
	 height: 4px;
	 background: gray;
}
 
#c-grade {
	 margin-left: 41px;
	 vertical-align: 4px;
}
  
#c-review {
  width: 427px;
  background-color: whitesmoke;
  height: 80px;
  margin-bottom: 10px;
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
</style>
</head>

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
    <div class="tabCon" >
		  <c:choose>
		  <c:when test='${empty cafe}'>
		     <span>등록된 카페가 없습니다.</span><br>
		     <a href='addform' class="btn btn-outline-dark">카페 등록하러 가기</a>
		  </c:when>
		  <c:otherwise>
		  <div class = "cafe-top">
		  <div id='aside'>
		    <span id='c-image'><img src="../../img/aaa.jpg" style="width: 370px; height: 200px;"></span>
		  </div>
		  <div id='content'>
		    <form action='updateform' class="cafe-form" method='post' enctype="multipart/form-data">
		    <input id='c-no' type='hidden' value='${cafe.no}'>
		    <h5>[${cafe.name}]</h5>
		    <label for='f-bossName'>대표자</label><span>${cafe.ceoMember.ceoBossName}</span><br>
		    <label for='f-licenseNo'>사업자 등록번호</label><span>${cafe.ceoMember.ceoLicenseNo}</span><br>
		    <label for='f-location'>주소</label><span>${cafe.location}</span><br>
		    </form>
		  </div>
		  </div>
		  <div class="cafe-bottom">
		    <label for='f-info'>소개글</label><span>${cafe.info}</span><br>
		    <label for='f-tel'>전화번호</label><span>${cafe.phone}</span><br>
		    <label for='f-openTime'>오픈시간</label><span>${cafe.openTime}</span><br>
		    <label for='f-closeTime'>마감시간</label><span>${cafe.closeTime}</span><br>
		    <label for='f-holiday'>이번주 휴무일</label><span>${cafe.holiday}</span><br>
		    <label for='f-viewCount'>상태</label><span>${cafeStatus}</span><br>
		    <label for='f-review'>리뷰평점</label><span>⭐${cafe.avgReview} / ${cafe.countReview}개</span>
		  </div>
		  <div class="cafe-bottom-review">
			  <hr style="border-top: 4px double #bbb; text-align: center; display: inline-block; width: 48%; margin: 4px 0">
			  <i class="far fa-comments" style="color:#bbb; font-size: large;"></i>
			  <hr style="border-top: 4px double #bbb; text-align: center; display: inline-block; width: 47%;  margin: 4px 0">
		  
		   <c:if test='${not empty reviewList}'>
		     <c:forEach items="${reviewList}" var="review">
		      <div id='c-review'>
		         <span>닉네임ㅣ</span>
		         <span>${review.member.perNickname}</span>
		         
		         <span>별점 </span>
		         <span id='c-grade'>
		           <c:set var="grade" value="${review.grade}" /> 
		              <% 
		              int grade = (int) pageContext.getAttribute("grade");
		              String star = CafeHandlerHelper.getReviewGradeStatusLabel(grade);
		              pageContext.setAttribute("star", star);
		              %>
		           <span>${star}(${review.grade}/5)</span>
		         </span>
		         
		         <span>등록일ㅣ</span>
		         <span id='c-grade'>${review.registeredDate}</span>
		         
		         <span>내용ㅣ</span>
		          <br><p id='c-review-content'>${review.content}</p><br>
		      </div>
		     </c:forEach>
		   </c:if>
		   <c:if test='${empty reviewList}'>
		     <p>등록된 리뷰가 없습니다.</p><br>  
		   </c:if>
		   </div>
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