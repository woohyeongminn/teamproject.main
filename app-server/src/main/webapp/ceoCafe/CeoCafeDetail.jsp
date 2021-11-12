<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>








<!-- 이거 안쓰고 CeoCafeMyDetail 사용 -->

















<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 스터디카페 상세</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <style>
  a {
   text-decoration:none;
  }
  label {
    display: inline-block;
    margin-right: 5px;
    width: 130px;
  }
  #aside {
     width: 120px;
     height: 171px;
     float: left;
     background-color: lightsteelblue;
     display: table;
  }
  #content {
     margin-left: 130px;
  }
  #c-image {
    display: table-cell;
    vertical-align: middle;
    text-align: center;
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
  
  .all-content {
  width: 100%;
  margin: 0 auto;
}

.c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
  text-align: center;
}
  
  </style>
</head>

<body>
  <jsp:include page="../header.jsp"/>
  <div class="c-top">
     👩‍🏫 내 스터디카페 상세
  </div>
  <div class="all-content">
  
  <c:choose>
  <c:when test='${empty cafe}'>
     <p>등록된 카페가 없습니다.</p><br>
      <button id='b-but' type="submit" value="수정" formaction="updateform">
        <a href='addform'>카페 등록하러 가기</a>
     </button>
  </c:when>
  <c:otherwise>
  <input id='c-no' type='hidden' value='${cafe.no}'><br>
  <h5>[${cafe.name}]</h5>
  <div id='aside'>
    <span id='c-image'>대표이미지</span>
  </div>
  <form action='updateform' method='post' enctype="multipart/form-data">
  <div id='content'>
    <label for='f-bossName'>대표자</label><span>${cafe.ceoMember.ceoBossName}</span><br>
    <label for='f-licenseNo'>사업자 등록번호</label><span>${cafe.ceoMember.ceoLicenseNo}</span><br>
    <label for='f-info'>소개글</label><span>${cafe.info}</span><br>
    <label for='f-location'>주소</label><span>${cafe.location}</span><br>
    <label for='f-tel'>전화번호</label><span>${cafe.phone}</span><br>
    <label for='f-openTime'>오픈시간</label><span>${cafe.openTime}</span><br>
    <label for='f-closeTime'>마감시간</label><span>${cafe.closeTime}</span><br>
    <label for='f-holiday'>이번주 휴무일</label><span>${cafe.holiday}</span><br>
    <label for='f-viewCount'>상태</label><span>${cafeStatus}</span><br>
    <label for='f-review'>리뷰평점</label><span>⭐${cafe.avgReview}(${cafe.countReview})</span>
  </div>
  
  <span>============= 리뷰 =============</span>
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
   <div id='button'>
	   <button id='b-but' type="submit" value="수정" formaction="updateform">
	      <a href='updateform?cafeno=${cafe.no}'> 스터디카페 수정</a>
	   </button>
	   
	   <button id='b-but' type="submit" value="삭제" formaction="deleteform">
	      <a href='deleteform?cafeno=${cafe.no}'>스터디카페 삭제</a>
	   </button>
	   
	     <!--
	      <button onclick="window.open('http://localhost:8080/ogong/ceomember/cafe//deleteform?no=${cafe.no}&ceono=${ceoMember.ceoNo}','deleteform','width=430,height=200,location=no,status=no,scrollbars=yes');">스터디카페 삭제</button>
	     -->
	   
	    <button id='b-but' type="submit" value="스터디룸관리">
        <a href='room/list?cafeno=${cafe.no}'>스터디룸 관리</a>
      </button>
      <button id='b-but' type="submit" value="예약관리">
        <a href='reser/list'>예약 관리</a>
      </button>
    </div>
    </form>
   </c:otherwise>
  </c:choose>
  </div>
</body>
</html>