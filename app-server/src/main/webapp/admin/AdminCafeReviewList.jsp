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
  legend:hover {
    color: lightgrey;
  }
  button[type=button] {
    margin-block: 10px;
    border-radius: 10px;
    background-color: beige;
    color: black;
  }
  button[type=button]:hover {
    background-color: blanchedalmond;
    color: black;
  }
  .btn-secondary:focus {
  background-color: beige;
  color: black;
  }
  button[type=button1] {
    margin-left: 15px;
    border-radius: 10px;
    border-color: lightgray;
    background-color: beige;
    color: black;
  }
  button[type=button1]:hover {
    background-color: blanchedalmond;
    color: black;
  }
  .dropdown-menu {
  background-color: rgba(211, 211, 211, 0);
  border: rgba(211, 211, 211, 0);
  }
  .btn-group {
  margin-top: 10px;
  display: block;
  }
  .offcanvas-start {
  width: 350px;
  }
  button[type=button2] {
  margin-left: 70px;
    color: black;
  }
  button[type=button2]:hover {
    color: black;
  }
  div {
  margin-right: 10px;
  }
  a {
  color : black;
  text-decoration : auto;
  }
  a:hover {
  color : lightgray;
  }
  </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<br>
<legend data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample"><b> 🔖 스터디 카페 리뷰 목록 </b></legend><br>
<hr>

<c:if test='${not empty reviewList}'>
<table class="table table-striped text-center">
	<thead>
	  <tr>
	    <th>번호</th>
	    <th>스터디카페</th>
	    <th>별점</th>
	    <th>내용</th>
	    <th>등록일</th>
	    <th>삭제</th>
	  </tr>
	</thead>
  <tbody>

	<c:forEach items="${reviewList}" var="review">
	 <tr>
	    <td>${review.reviewNo}</td>
	    <td>${review.cafe.name}</td>
	    <td>
	    <c:set var="grade" value="${review.grade}" /> 
	          <% 
	          int grade = (int) pageContext.getAttribute("grade");
	          String star = CafeHandlerHelper.getReviewGradeStatusLabel(grade);
	          pageContext.setAttribute("star", star);
	          %>
	      ${star}(${review.grade}/5)
	    </td>
	    <td>${review.content}</td>
	    <td>${review.registeredDate}</td>
	    <td>
	      <button class="btn btn-outline-dark"><a href="reviewDelete?reviewNo=${review.reviewNo}">삭제</a></button>
	    </td>
	 </tr>
	</c:forEach>
  </tbody>
</table>
</c:if>

<c:if test='${empty reviewList}'>
   등록된 리뷰가 없습니다.<br><br>  
</c:if>
<br>

</body>

<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
  <div class="offcanvas-header">
    <h4 class="offcanvas-title" id="offcanvasExampleLabel">👑 관리자 👑</h4>
    <button type="button2" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    <hr>
  </div>

  <div class="offcanvas-body">
    <div>
      <b>이동하고 싶은 탭을 선택해 주세요!</b>
    </div>

    <div class="btn-group dropend">
      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
        👑 관리자 페이지
      </button>
      <div class="dropdown-menu" role="menu" style="border-color: white;">
        <button class="dromdown-item" type="button1">
          <a href='logout' style="color: black;">🖐 로그아웃</a></button><br>
        <%-- <button class="dromdown-item" type="button1">
          <a href='detail?no=${admin.masterNo}' style="color: black;">🙂 마이페이지</a></button> --%>
      </div>
    </div>
    
    <div class="btn-group dropend">
      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
        📁 회원 관리
      </button>
      <div class="dropdown-menu" role="menu" style="border-color: white;">
        <button class="dromdown-item" type="button1">
          <a href="/ogong/admin/permemberlist" style="color: black;">🎓 개인 회원</a></button><br>
        <button class="dromdown-item" type="button1">
          <a href="/ogong/admin/ceomember/list" style="color: black;">👔 기업 회원</a></button>
      </div>
    </div>
      
    <div class="btn-group dropend">
      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
        📖 스터디 관리
      </button>
      <div class="dropdown-menu" role="menu" style="border-color: white;">
        <button class="dromdown-item" type="button1">
          <a href="study/list" style="color: black;">📚 스터디 목록</a></button><br>
        <!-- <button class="dromdown-item" type="button1">
          <a href="/study/list" style="color: black;">📔 스터디 삭제</a></button> -->
      </div>
    </div>
    
    <div class="btn-group dropend">
      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
        🏘 장소 관리
      </button>
      <div class="dropdown-menu" role="menu" style="border-color: white;">
        <button class="dromdown-item" type="button1">
          <a href="/ogong/admin/cafeList" style="color: black;">📝 장소 목록</a></button><br>
        <button class="dromdown-item" type="button1">
          <a href="/ogong/admin/reviewList" style="color: black;">🔖 장소 리뷰</a></button>
      </div>
    </div>
    
    <div class="btn-group dropend">
      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
        💌 고객센터 관리
      </button>
      <div class="dropdown-menu" role="menu" style="border-color: white;">
        <button class="dromdown-item" type="button1">
          <a href="/ogong/adminNotice/list" style="color: black;">📢 공지사항</a></button><br>
        <button class="dromdown-item" type="button1">
          <a href="/ogong/admin/askboardlist" style="color: black;">💬 문의사항</a></button>
      </div>
    </div>
      
    </div>
</div>

</html>