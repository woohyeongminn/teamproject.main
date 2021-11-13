<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
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

.template-content {
    height: 1100px;
}

 .all-content {
  width: 1000px;
  margin: 0 auto;
  height: 800px;
}

ul {
  list-style:none;
}

.cafe-wrap {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.cafe-top {
  width: 50%;
   margin: 8px 8px 0 0;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-bottom: 15px;
}

    .slide{height:300px;overflow:hidden;}
    .slide ul{width:calc(100% * 4);display:flex;animation:slide 8s infinite;} /* slide를 8초동안 진행하며 무한반복 함 */
    .slide li{width:calc(100% / 4);height:300px;}
    .slide li:nth-child(1){background:#ffa;}
    .slide li:nth-child(2){background:#faa;}
    .slide li:nth-child(3){background:#afa;}
    .slide li:nth-child(4){background:#aaf;}
    @keyframes slide {
      0% {margin-left:0;} /* 0 ~ 10  : 정지 */
      10% {margin-left:0;} /* 10 ~ 25 : 변이 */
      25% {margin-left:-100%;} /* 25 ~ 35 : 정지 */
      35% {margin-left:-100%;} /* 35 ~ 50 : 변이 */
      50% {margin-left:-200%;}
      60% {margin-left:-200%;}
      75% {margin-left:-300%;}
      85% {margin-left:-300%;}
      100% {margin-left:0;}
    }


.cafe-bottom {
      width: 50%;
    text-align: left;
    padding: 5px 0;
    margin-top: 30px;
    margin-left: 15px;
}

.cafe-bottom label {
       width: 135px;
   font-weight: bold;
   padding: 5px 0;
}

.cafe-bottom span {
  width: 80%;
  padding: 5px 0;
}

.label-wrap {
  width: 100%;
  height: fit-content;
}

.label-wrap > label {
   width: 135px;
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
  padding: 0 10px 30px 0px;
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

</style>
</head>

<body>
<br><br><br>
<div class="all-content">
	<c:choose>
	<c:when test='${empty cafe}'>
	  <span>등록된 카페가 없습니다.</span><br>
	  <a href='addform' class="btn btn-outline-dark">카페 등록하러 가기</a>
	</c:when>
	
	<c:otherwise>
	<div class="cafe-wrap">
		<div class="cafe-top">
		 <h4 style="text-align: center;">[${cafe.name}]</h4>
			<div class="slide">
			    <ul>
			      <li><img src="../../upload/cafe/${cafe.cafeImgs[0].name}" style="width:100%"></li>
				    <li><img src="../../upload/cafe/${cafe.cafeImgs[1].name}" style="width:100%"></li>
				    <li><img src="../../upload/cafe/${cafe.cafeImgs[2].name}" style="width:100%"></li>
			       <%-- <li><img src="../upload/cafe/${cafe.cafeImgs[2].name}.jpg" style="width:100%"></li> --%>
			  </ul>
			</div>
		</div>
		
		<!-- 카페 상세 글 부분 -->      
		 <div class="cafe-bottom">
		<form action='updateform' method='post' enctype="multipart/form-data">
			 <input id='c-no' type='hidden' value='${cafe.no}'>
			 <label for='f-bossName'>대표자</label><span>${cafe.ceoMember.ceoBossName}</span><br>
			 <label for='f-licenseNo'>사업자 등록번호</label><span>${cafe.ceoMember.ceoLicenseNo}</span><br>
			 <div class="label-wrap"><label for='f-location'>주소</label> <span style="height: fit-content;">${cafe.location}</span></div>
			 <div class="label-wrap"><label for='f-info'>소개글</label><span style="height: fit-content;">${cafe.info}</span></div>
			 <label for='f-tel'>전화번호</label><span>${cafe.phone}</span><br>
			 <label for='f-openTime'>운영시간</label><span>${cafe.openTime} AM ~ ${cafe.closeTime} PM</span><br>
			 <label for='f-holiday'>이번주 휴무일</label><span>${cafe.holiday}</span><br>
			 <label for='f-viewCount'>상태</label><span>${cafeStatus}</span><br>
			 <label for='f-review'>리뷰평점</label><span>⭐${cafe.avgReview} / ${cafe.countReview}개</span>
	  </form>
		 </div>
	</div>
	<!-- 리뷰 부분 감싸는 박스 -->
	<div class="cafe-bottom-review">
	
	<!-- 리뷰 시작부분에 선 -->
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
</body>
</html>