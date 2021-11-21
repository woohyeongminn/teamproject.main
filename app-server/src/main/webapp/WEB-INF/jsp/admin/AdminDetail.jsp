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
    width: 74%;
    left: 22%;
  }
  .ms-2 {
  font-size: 16px;
  }
  #f-profile {
    text-align: center;
    border: white;
  }
  a:hover {
  color: rgb(155 136 131);
  }
  </style>
  
</head>

  <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
  
  <aside class="sidenav navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark" id="sidenav-main">
    <div class="sidenav-header">
      <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
      <a class="navbar-brand m-0" href="${contextPath}/app/index" target="_blank">
        <span class="ms-2 font-weight-bold text-white">👑　오늘의 공부</span>
      </a>
    </div>
    <hr class="horizontal light mt-0 mb-2">
    
    <div class="collapse navbar-collapse  w-auto  max-height-vh-100" id="sidenav-collapse-main">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link text-white active bg-gradient-primary" href="${contextPath}/app/admin/detail" style="background-color: rgb(161 135 120);">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">face</i>
            </div>
            <span class="nav-link-text ms-1">관리자</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " href="${contextPath}/app/admin/detail">
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
      
        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
          <div class="card">
            <div class="card-header p-3 pt-2">
              <div class="icon icon-lg icon-shape bg-gradient-dark shadow-dark text-center border-radius-xl mt-n4 position-absolute" style="background-color: #5b5b5b;">
                <i class="material-icons opacity-10">weekend</i>
              </div>
              <div class="text-end pt-1">
                <p class="text-sm mb-0 text-capitalize">공지사항</p>
                <h4 class="mb-0"><a href=${contextPath}/app/adminNotice/list>Notice</a></h4>
              </div>
            </div>
          </div>
        </div>
        
        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
          <div class="card">
            <div class="card-header p-3 pt-2">
              <div class="icon icon-lg icon-shape bg-gradient-primary shadow-primary text-center border-radius-xl mt-n4 position-absolute" style="background-color: #ed6e99;">
                <i class="material-icons opacity-10">person</i>
              </div>
              <div class="text-end pt-1">
                <p class="text-sm mb-0 text-capitalize">개인회원</p>
                <h4 class="mb-0"><a href=${contextPath}/app/admin/permemberlist>Member User</a></h4>
              </div>
            </div>
          </div>
        </div>
        
        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
          <div class="card">
            <div class="card-header p-3 pt-2">
              <div class="icon icon-lg icon-shape bg-gradient-success shadow-success text-center border-radius-xl mt-n4 position-absolute" style="background-color: #7bc9b2;">
                <i class="material-icons opacity-10">person</i>
              </div>
              <div class="text-end pt-1">
                <p class="text-sm mb-0 text-capitalize">기업회원</p>
                <h4 class="mb-0"><a href=${contextPath}/app/admin/ceomember/list>Business User</a></h4>
              </div>
            </div>
          </div>
        </div>
        
        <div class="col-xl-3 col-sm-6">
          <div class="card">
            <div class="card-header p-3 pt-2">
              <div class="icon icon-lg icon-shape bg-gradient-info shadow-info text-center border-radius-xl mt-n4 position-absolute" style="background-color: #49a3f1;">
                <i class="material-icons opacity-10">weekend</i>
              </div>
              <div class="text-end pt-1">
                <p class="text-sm mb-0 text-capitalize">문의게시판</p>
                <h4 class="mb-0"><a href=${contextPath}/app/admin/askboard/list>Ask</a></h4>
              </div>
            </div>
          </div>
        </div>
        
      </div>
    </div>


    <div class="card card-body mx-3 mx-md-4 mt-n6">
    
      <div class="row gx-4 mb-2">
        <div class="col-auto">
          <div class="avatar avatar-xl position-relative">
            <img src="${contextPath}/img/admin.png" alt="profile_image" class="w-100 border-radius-lg shadow-sm">
          </div>
        </div>
        <div class="col-auto my-auto">
          <div class="h-100">
            <h5 class="mb-1" style="color: #4e4847;">
              ${loginAdmin.masterNickname}
            </h5>
            <p class="mb-0 font-weight-normal text-sm">
              관리자
            </p>
          </div>
        </div>
        <div class="col-lg-4 col-md-6 my-sm-auto ms-sm-auto me-sm-0 mx-auto mt-3">
          <div class="nav-wrapper position-relative end-0">
            <ul class="nav nav-pills nav-fill p-1" role="tablist">
              <li class="nav-item">
                <a class="nav-link mb-0 px-0 py-1 active " data-bs-toggle="tab" href="javascript:;" role="tab" aria-selected="true">
                  <i class="material-icons text-lg position-relative">home</i>
                  <span class="ms-1">App</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link mb-0 px-0 py-1 " data-bs-toggle="tab" href="javascript:;" role="tab" aria-selected="false">
                  <i class="material-icons text-lg position-relative">email</i>
                  <span class="ms-1">Messages</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link mb-0 px-0 py-1 " data-bs-toggle="tab" href="javascript:;" role="tab" aria-selected="false">
                  <i class="material-icons text-lg position-relative">settings</i>
                  <span class="ms-1">Settings</span>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      
      <div class="row">
        <div class="row">
          <div class="col-12 col-xl-4" style="width: auto;">
            <div class="card card-plain h-100">
              <div class="card-header pb-0 p-3">
                <div class="row">
                  <div class="col-md-8 d-flex align-items-center">
                    <h5 class="mb-0" style="color: #4e4847;">Profile</h5>
                  </div>
                  <div class="col-md-4 text-end">
                    <a href="updateForm">
                      <i class="fas fa-user-edit text-secondary text-sm" data-bs-toggle="tooltip" data-bs-placement="top" title="Edit Profile"></i>
                    </a>
                  </div>
                </div>
              </div>
              <br>
              <div class="card-body p-3">
                <ul class="list-group">
                  <li class="list-group-item border-0 ps-0 pt-0 text-sm"><strong class="text-dark" style="font-size: 14px; color: #4e4847;">Full Name:</strong> &nbsp; <input id='f-profile' type='text' name='nickName' value='${loginAdmin.masterNickname} 👑' readonly></li>
                  <li class="list-group-item border-0 ps-0 text-sm"><strong class="text-dark" style="font-size: 14px; color: #4e4847;">Email:</strong> &nbsp; <input id='f-profile' type='email' name='email' value='${loginAdmin.masterEmail}' readonly> </li>
                  <li class="list-group-item border-0 ps-0 pb-0">
                  </li>
                </ul>
                <p class="text-sm" style="color: white;">
                  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                </p>
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
<script src="${contextPath}/css/admin/assets/js/plugins/perfect-scrollbar.min.js"></script>
<script src="${contextPath}/css/admin/assets/js/plugins/smooth-scrollbar.min.js"></script>

<script>
  var win = navigator.platform.indexOf('Win') > -1;
  if (win && document.querySelector('#sidenav-scrollbar')) {
    var options = {
      damping: '0.5'
    }
    Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
  }
</script>

<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
<script src="${contextPath}/css/admin/assets/js/material-dashboard.min.js?v=3.0.0"></script>
  
