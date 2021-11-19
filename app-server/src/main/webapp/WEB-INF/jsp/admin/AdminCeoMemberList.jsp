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
          <a class="nav-link text-white active bg-gradient-primary" href="${contextPath}/app/admin/detail" style="background-color: rgb(161 135 120);">
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
          <a class="nav-link text-white " href="${contextPath}/app/admin/cafeList">
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
              <div class="bg-gradient-success shadow-success border-radius-lg pt-4 pb-3" style="background-color: #7bc9b2;">
                <h6 class="text-white text-capitalize ps-3">Business User</h6>
              </div>
            </div>
            <div class="card-body px-0 pb-2">
              <div class="table-responsive p-0">
                <table class="table align-items-center mb-0">
                  <thead>
                    <tr>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">번호</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">닉네임</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">이름</th>
                      <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">가입일</th>
                      <th class="text-secondary opacity-7"></th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${ceoMemberList}" var="ceoMember">
                      <tr>
                        <td>
                          <p class="text-xs font-weight-bold mb-0" style="padding: 0.75rem 0 0.75rem 1.5rem;">${ceoMember.ceoNo}</p>
                        </td>
                        <td>
                          <div class="d-flex px-2 py-1">
                            <div>
                              <img src="${contextPath}/img/ceo.png" class="avatar avatar-sm me-3 border-radius-lg" alt="user1">
                            </div>
                            <div class="d-flex flex-column justify-content-center">
                              <h6 class="mb-0 text-sm" style="color:#4e4847;"><a href='detail?ceono=${ceoMember.ceoNo}'>${ceoMember.ceoNickname}</a></h6>
                              <p class="text-xs text-secondary mb-0">${ceoMember.ceoEmail}</p>
                            </div>
                          </div>
                        </td>
                        <td>
                          <p class="text-xs font-weight-bold mb-0">${ceoMember.ceoName}</p>
                        </td>
                        <td class="align-middle text-center">
                          <span class="text-secondary text-xs font-weight-bold">${ceoMember.ceoRegisteredDate}</span>
                        </td>
                        <td class="align-middle">
                          <a href="delete?ceono=${ceoMember.ceoNo}" class="text-secondary font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Edit user">
                            삭제
                          </a>
                        </td>                    
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
                <c:if test="${empty ceoMemberList}">
				        <p>등록된 기업 회원이 없습니다.</p>
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
  
