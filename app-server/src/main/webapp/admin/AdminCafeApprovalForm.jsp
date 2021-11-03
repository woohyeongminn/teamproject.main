<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>🏘 스터디 카페</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <style>
  legend {
  text-align: center;
  }
  label {
    display: inline-block;
    margin-right: 5px;
    width: 130px;
  }
  form {
  display: inline-block;
  }
  #all {
  margin: 50px;
  }
  #aside {
     width: 120px;
     height: 200px;
     float: left;
     background-color: lightyellow;
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
  a {
  color : black;
  text-decoration:none;
  }
  a:hover {
  color : white;
  }
  </style>
<c:if test='${cafe.cafeStatus == 4}'>
<style>
#deleted {
display: none;
}
</style>
</c:if>
</head>
<body>
  <input id='c-no' type='hidden' value='${cafe.no}'><br>
  <legend><b> 🏘 스터디 카페 승인 </b></legend><br>
  <hr>
  <div id='all'>
  <b><h4>${cafe.name}</h4></b>
  <hr>
  <div id='aside'>
    <span id='c-image'>대표이미지</span>
  </div>
  <div id='content'>
    <label for='f-bossName'>대표자</label><span id='deleted'>${cafe.ceoMember.ceoBossName}</span><br>
    <label for='f-licenseNo'>사업자 등록 번호</label><span id='deleted'>${cafe.ceoMember.ceoLicenseNo}</span><br>    
    <label for='f-info'>소개글</label>${cafe.info}<br>
      <label for='f-location'>주소</label>${cafe.location}<br>
      <label for='f-tel'>전화번호</label>${cafe.phone}<br>
      <label for='f-openTime'>오픈 시간</label>${cafe.openTime}<br>
      <label for='f-closeTime'>마감 시간</label>${cafe.closeTime}<br>
      <label for='f-holiday'>이번 주 휴무일</label>${cafe.holiday}<br>
      <label for='f-viewCount'>조회수</label>${cafe.viewCount}<br>
      <label for='f-review'>리뷰 평점</label>⭐${cafe.avgReview}(${cafe.countReview})<br>
    <label for='f-cafeStatus'>운영 상태</label>
   <form action='cafeApproval'>
   <input type="hidden" value="${cafe.no}" name="no"></input>
    <select name="cafeStatus">
    <c:if test='${cafe.cafeStatus==1}'>
          <option value="${cafe.cafeStatus}">승인 대기</option>
          <option value="2" name="cafeStatus" >운영 중</option>
          <option value="3" name="cafeStatus" >운영 중단</option>
          <option value="4" disabled>삭제</option>
        </c:if>
      </select><br>
      
    </div>
<br>
<c:if test='${not empty reviewList}'>
  <c:forEach items="${reviewList}" var="review">
    <div id='c-review'>
      <span>${review.member.perNickname}</span> 
      <span id='c-grade'>
        <c:set var="grade" value="${review.grade}" /> 
          <% 
          int grade = (int) pageContext.getAttribute("grade");
          String star = CafeHandlerHelper.getReviewGradeStatusLabel(grade);
          pageContext.setAttribute("star", star);
          %>
      ${star}(${review.grade}/5)
      </span>
      <span id='c-grade'>${review.registeredDate}</span>
      <br><p id='c-review-content'>${review.content}</p><br>
    </div>
  </c:forEach>
  <br>
  <br>
  <br>
  <br>
</c:if>
<c:if test='${empty reviewList}'>
   등록된 리뷰가 없습니다.<br><br>  
</c:if>
<br>
<script>
function disagree()  {
  confirm(" 승인을 거절하시겠습니까? ");
}
</script>
<button type="submit" class="btn btn-outline-dark">승인</button>
<button type="button" class="btn btn-outline-dark" onclick="disagree()"><a href="/ogong/admin/cafeList">거절</a></button>
<button type="submit" class="btn btn-outline-dark" value="로그아웃" ><a href='/ogong/admin/logout'>로그아웃</a></button> 
</form>
</div>
</body>
</html>