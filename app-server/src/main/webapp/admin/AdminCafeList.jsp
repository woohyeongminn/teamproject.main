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
  #content {
  float: center;
    margin-left: 20px;
    width: 58%;
    xborder: 1px solid black;
  }
  rect {
  width: 414px;
  fill: lightyellow;
  }
  text {
  fill: black;
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
  text-decoration : blink;
  }
  a:hover {
  color : lightgray;
  }
  .col {
    width: 450px;
  }
  .card {
    height: 350px;
  }
  </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<br>
<legend data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample"><b> 🏘 스터디 카페 목록 </b></legend><br>
<hr>
  <div id="content">
    <div class="row row-cols-1 row-cols-md-3 g-4" style="float: left">
    <c:forEach items="${cafeList}" var="cafe">
      <div class="col">
        <div class="card">
          <svg class="bd-placeholder-img rounded" width="425" height="200" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 200x200" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#868e96"></rect><text x="45%" y="50%" fill="#dee2e6" dy=".3em">이미지</text></svg>
          <div class="card-body">
            <a href='cafeDetail?no=${cafe.no}'><b>${cafe.name}</b></a><br>
            ${cafe.location}<br>
            영업시간 ${cafe.openTime} ~ ${cafe.closeTime}<br>
            ⭐${cafe.avgReview}(${cafe.countReview})<br>
            <script>
         if(${cafe.cafeStatus == 1}) {
           document.write("<label for='f-cafeStatus'>❗ </label>승인 대기 중인 카페입니다.");
         } else if(${cafe.cafeStatus == 2}) {
           document.write("<label for='f-cafeStatus'>🔆 </label>현재 운영 중입니다.");
         } else if(${cafe.cafeStatus == 3}) {
           document.write("<label for='f-cafeStatus'>⛔ </label>운영 중단된 카페입니다.");
         } else if(${cafe.cafeStatus == 4}) {
           document.write("<label for='f-cafeStatus'>❌ </label>삭제된 카페입니다.");
         } 
       </script>
          </div>
        </div>
      </div>
    </c:forEach>
    </div>
  </div>
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