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
  }
  
  .containerbox {
    padding: 0;
    margin: 20px 20px;
    height: 380px;
  }
  
  .ms-2 {
  font-size: 16px;
  }
  .ps__rail-y {
  height: 380px;
  }
  
  .list-group {
    display: flex;
    flex-direction: column;
    padding-left: 0;
    margin-bottom: 0;
    border-radius: 0.375rem;
    font-size: 14px;
  }
  
  #button1 {
  background-color: #d7d7b5;
  color: black;
  line-height: 14px;
  top: 5px;
  }
  
  #button_wrap {
  text-align: right;
  }
  
  #button2 {
  margin-top: 20px;
  font-size: 12px;
  line-height: 5px;
  }
  </style>
  
</head>

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
          <a class="nav-link text-white" href="${contextPath}/app/admin/permemberlist">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">person</i>
            </div>
            <span class="nav-link-text ms-1">회원</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white active bg-gradient-primary"" href="${contextPath}/app/admin/study/list" style="background-color: rgb(161 135 120);">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">mode</i>
            </div>
            <span class="nav-link-text ms-1">스터디</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " href="${contextPath}/app/admin/cafeList">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">business</i>
            </div>
            <span class="nav-link-text ms-1">장소</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " href="${contextPath}/app/admin/reviewList">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">article</i>
            </div>
            <span class="nav-link-text ms-1">리뷰</span>
          </a>
        </li>
        <li class="nav-item mt-3">
          <h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">CS pages - Admin</h6>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " href="${contextPath}/app/adminNotice/list">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">help</i>
            </div>
            <span class="nav-link-text ms-1">공지사항</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " href="${contextPath}/app/admin/askboard/list">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">help</i>
            </div>
            <span class="nav-link-text ms-1">문의게시판</span>
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
              <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3" style="background-color: #d7d7b5; box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(215 215 181 / 40%) !important;">
                <h6 class="text-white text-capitalize ps-3">${study.studyTitle} | ${study.owner.perNickname}</h6>
              </div>
            </div>
  
                    <div class="containerbox" name="studyInfo">
                    <input type="hidden" name="members" value="${study.members}">
                    <input type="hidden" name="waitingMember" value="${study.waitingMember}">
                    <input type="hidden" name="studyStatus" value="${study.studyStatus}">
                    <input type="hidden" name="countMember" value="${study.countMember}">
                    <input type="hidden" name="numberOfPeple" value="${study.numberOfPeple}">
                      <p class="font-weight-bold mb-0" style="margin-top: 10px; font-size: 12px; color: #7b809a; text-align: right;">${study.introduction}&emsp;
                      <c:choose>
                       <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus ne '2'}">
                         <button type="button" id="button1" class="btn btn-primary btn-sm" disabled>모집중</button>
                       </c:when>
                       <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus ne '2'}">
                         <button type="button" id="button1" class="btn btn-primary btn-sm" disabled>모집중</button>
                       </c:when>
                       <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus eq '2'}">
                         <button type="button" id="button2" class="btn btn-secondary btn-sm" disabled>모집완료</button>
                       </c:when>
                       <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus eq '2'}">
                         <button type="button" id="button2" class="btn btn-secondary btn-sm" disabled>모집완료</button>
                       </c:when>
                </c:choose>
                     </p>
                      <ul class="list-group">
                          <li class="list-group-item d-flex justify-content-between align-items-center">
                            🔎 분야
                            <p class="text-xs font-weight-bold mb-0" style="color:#7b809a">${study.subjectName}</p>
                          </li>
                          <li class="list-group-item d-flex justify-content-between align-items-center">
                            🌐 지역
                            <p class="text-xs font-weight-bold mb-0" style="color:#7b809a">${study.area}</p>
                          </li>
                          <li class="list-group-item d-flex justify-content-between align-items-center">
                          🎭 대면 상태
                          <p class="text-xs font-weight-bold mb-0" style="color:#7b809a">${study.faceName}</p>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                          🏆 활동 점수
                          <p class="text-xs font-weight-bold mb-0" style="color:#7b809a">${study.point}</p>
                        </li>
                        </ul>
                        
                      <div id='button_wrap'>
                        <a href='${contextPath}/app/admin/study/list' class="btn btn-outline-dark" id="button2">목록</a>
                        <a href='${contextPath}/app/admin/study/delete?studyno=${study.studyNo}' class="btn btn-outline-dark" id="button2">삭제</a>
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
  