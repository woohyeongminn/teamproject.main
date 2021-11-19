<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 아이콘 -->
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<%--   <link rel="apple-touch-icon" sizes="76x76" href="${contextPath}/css/admin/assets/img/apple-icon.png">
--%>
 <link rel="icon" href="${contextPath}/img/favicon.ico" type="image/x-icon" sizes="16x16" style="background-color: rgba(255, 255, 255, 0);">
  
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
  <!-- Nucleo Icons -->
  <link href="${contextPath}/css/admin/assets/css/nucleo-icons.css" rel="stylesheet" />
  <link href="${contextPath}/css/admin/assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- Font Awesome Icons -->
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <!-- Material Icons -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
  <!-- CSS Files -->
  <link id="pagestyle" href="${contextPath}/css/admin/assets/css/material-dashboard.css?v=3.0.0" rel="stylesheet" />
  
  <style>
  body {
  color: #4e4847;
  }
  #search {
    text-align: center;
    padding: 20px;
  }
  .navbar {
    -webkit-box-shadow: 0;
    -moz-box-shadow: 0;
    box-shadow: none;
  }
  .navbar-vertical.navbar-expand-xs.fixed-start {
    left: 0;
  }
  .navbar-vertical.navbar-expand-xs {
      display: block;
      position: absolute;
      top: 0;
      bottom: 0;
      width: 100%;
      height: 500px;
      max-width: 15.625rem !important;
      overflow-y: auto;
      padding: 0;
      box-shadow: none;
      font-size: 14px;
  }
  .container-fluid {
    padding: 0;
    width: 74%;
    margin-left: 24%;
    margin-bottom: 100px;
  }
  .card-body {
    margin: 0;
    padding: 0;
    width: 100%;
    max-height: 415px;
    left: 22%;
    overflow-y: scroll;
  }
  
  .card-body::-webkit-scrollbar {
    width: 10px;
  }
  .card-body::-webkit-scrollbar-thumb {
  background-color: rgb(247, 231, 215);
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
  }
  .card-body::-webkit-scrollbar-track {
  background-color: rgb(250, 250, 234);
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
  }
  
  .ms-2 {
  font-size: 16px;
  }
  a:hover {
  color: rgb(155 136 131);
  }
  .ps__rail-y {
  height: 380px;
  }
  
  #content {
    margin: 20px 50px;
    height: 650px;
    font-size: 14px;
  }
  .col2 {
    width: 280px;
  }
  .card2 {
    height: 450px;
  }
  .card-body2 {
    line-height: 25px;
  }
  .btn-sm, .btn-group-sm > .btn {
    padding: 0.25rem 0.5rem;
    font-size: 12px;
    line-height: 14px;
    margin-bottom: 2px;
    border-radius: 0.2rem;
    color: #4e4847;
    border-color: #4e4847;
  }
  .btn-sm, .btn-group-sm > .btn:hover {
    color: #4e4847;
    border-color: #4e4847;
  }
  </style>
  
</head>

  <div id="search">
    <form action="search">
    <select name="where" style="font-size: 12px;">
      <option value="1" style="font-size: 12px; line-height: 14px;">지역</option>
      <option value="2" style="font-size: 12px; line-height: 14px;">이름</option>
    </select>
    <input type="text" name="keyword" style="font-size: 12px; line-height: 12px;">
    <input type="hidden" name="perNo" value="${perNo}">
    <button class="btn btn-outline-dark btn-sm">검색</button>
    </form>
  </div>

  <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
  
  <aside class="sidenav navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark" id="sidenav-main">
    <div class="sidenav-header">
      <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
      <a class="navbar-brand m-0" href="${contextPath}/app/index2" target="_blank">
        <span class="ms-2 font-weight-bold text-white">👑　오늘의 공부</span>
      </a>
    </div>
    <hr class="horizontal light mt-0 mb-2">
    
    <div class="collapse navbar-collapse  w-auto  max-height-vh-100" id="sidenav-collapse-main">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link text-white" href="${contextPath}/app/admin/detail">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">face</i>
            </div>
            <span class="nav-link-text ms-1">관리자</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="${contextPath}/app/admin/detail">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">person</i>
            </div>
            <span class="nav-link-text ms-1">회원</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " href="${contextPath}/app/admin/study/list">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">mode</i>
            </div>
            <span class="nav-link-text ms-1">스터디</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white active bg-gradient-primary" href="${contextPath}/app/admin/cafeList" style="background-color: rgb(161 135 120);">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">business</i>
            </div>
            <span class="nav-link-text ms-1">장소</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " ${contextPath}/app/admin/reviewList">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">article</i>
            </div>
            <span class="nav-link-text ms-1">리뷰</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " href="${contextPath}/css/admin/pages/tables.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">help</i>
            </div>
            <span class="nav-link-text ms-1">고객센터</span>
          </a>
        </li>
        <li class="nav-item mt-3">
          <h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Account pages</h6>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " href="${contextPath}/app/admin/logout">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">login</i>
            </div>
            <span class="nav-link-text ms-1">Login</span>
          </a>
        </li>
      </ul>
    </div>
    
    <div class="sidenav-footer position-absolute w-100 bottom-0 ">
      <div class="mx-3">
        <a class="btn bg-gradient-primary mt-4 w-100" href="${contextPath}/app/admin/logout" type="button" style="background-color: rgb(161 135 120);">Logout</a>
      </div>
    </div>
    
  </aside>
  
    <div class="container-fluid py-4">
      <div class="row">
        <div class="col-12">
          <div class="card my-4">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
              <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3" style="background-color: #edb68a; box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(237 182 138 / 40%) !important;">
                <h6 class="text-white text-capitalize ps-3">Study Cafe</h6>
              </div>
            </div>
            <div class="card-body px-0 pb-2">
              <div class="table-responsive p-0">
              
						<div id="content">
						  <c:if test='${not empty cafeList}'>
						    <div class="row row-cols-1 row-cols-md-3 g-4" style="float: left">
						    <c:forEach items="${cafeList}" var="cafe">
						      <div class="col2">
						        <div class="card2">
						          <img src="${contextPath}/img/aaa.jpg" class="card-img-top" alt="...">
						          <div class="card-body2">
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
						  </c:if>
						  </div>
						  
						  <c:if test='${empty cafeList}'>
						   검색 결과가 존재하지 않습니다.<br><br>  
						  </c:if>
              
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
  </main>
  
  <!--   Core JS Files   -->
<script src="${contextPath}/css/admin/assets/js/core/popper.min.js"></script>
<script src="${contextPath}/css/admin/assets/js/core/bootstrap.min.js"></script>

<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
<script src="${contextPath}/css/admin/assets/js/material-dashboard.min.js?v=3.0.0"></script>
  
