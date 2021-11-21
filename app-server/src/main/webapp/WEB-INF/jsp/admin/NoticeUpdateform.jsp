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
    height: 415px;
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
  
  th#date {
    font: small-caption;
    padding: 0;
  }
  tr#blockbox {
    vertical-align: top;
  }
  label {
    margin-right: 5px;
    text-align: center;
    width: 60px;
  }
  input {
  width: 650px;
  font-size: 14px;
  border : white;
  outline-color : lightgray;
  font-weight: 400;
  }
  input#chooseFile {
   font-size: 12px;
   color: white;
   line-height: 12px;
   /*mix-blend-mode: color;*/
  }
  input::file-selector-button {
  display: none;
  }
  #f-content {
  text-align: justify;
  margin: 0;
  word-wrap: break-word;
  width: 700px;
  height: 150px;
  font-size: 14px;
  min-height: 200px;
  letter-spacing: 0;
  border: 0px solid white;
  outline-color: lightgray;
  border-radius: 1px;
  }
  #f-photo-image {
  height: 300px;
  }
  #f-filepath {
  margin: 0;
  width: 300px;
  font-size: 14px;
  min-height: 370px;
  }
  
  button[type=submit] {
  float: right;
  margin-right: 10px;
  font-size: 12px;
  line-height: 5px;
  }
  </style>
  
</head>

<form id="notice-update" action='update' method='post' enctype="multipart/form-data">

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
          <a class="nav-link text-white" href="${contextPath}/app/admin/detail">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">face</i>
            </div>
            <span class="nav-link-text ms-1">관리자</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " href="${contextPath}/app/admin/ceomember/list">
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
          <a class="nav-link text-white" href="${contextPath}/app/admin/reviewList">
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
          <a class="nav-link text-white active bg-gradient-primary" href="${contextPath}/app/adminNotice/list" style="background-color: rgb(161 135 120);">
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
              <div class="bg-gradient-success shadow-success border-radius-lg pt-4 pb-3" style="background-color: #5b5b5b; box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(91 91 91 / 40%) !important;">
                <h6 class="text-white text-capitalize ps-3">Notice Board</h6>
              </div>
            </div>
            <div class="card-body px-0 pb-2">
              <div class="table-responsive p-0">
                <table class="table align-items-center mb-0">
                  <thead>
  
                  <tr id="first">
                    <th scope="col"><label for='f-title'>제목</label></th>
                    <th scope="col" id="title"><input id='f-title' type='text' name='adminNotiTitle' placeholder='${notice.adminNotiTitle}' autocomplete='off'></th>
                    <th scope="col"></th>
                  </tr>
  
                  <tr id="blockbox">
                    <th scope="row" id="second"><label for='f-content' id="content">내용</label></th>
                    <th scope="col"><textarea id='f-content' type='text' name='adminNotiContent' rows="20" wrap="virtual" autocomplete='off' placeholder='${notice.adminNotiContent}'></textarea></td>
                    <th></th>
                  </tr>
                  
                  <tr id="blockbox">
                    <th scope="row" id="third"><label for='f-filepath'>파일</label></th>
                    <th scope="col"><div class="img_button">
                      <img id="f-photo-image" src="${contextPath}/upload/notice/${notice.adminNotiFile}"></div>
                      <div class="click_button">
                      <label for="chooseFile" id="click"><b>👉 CLICK HERE! 👈</b></label>
                      </div>
                      <input type="file" id="chooseFile" name="filepath" accept="image/*" onchange="loadFile(this)"></td>
                    <th></th>
                  </tr>
  
                  </thead>
                </table>
  
              <input type='hidden' name='adminNotiNo' value='${notice.adminNotiNo}'>
              <div class="d-grid gap-2 d-md-flex justify-content-md-end">
						  <button type="submit" class="btn btn-outline-dark" value="변경" id="updatenotice">변경</button>
						  </div>
              
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>
   
  </main>
</form>

<script>
document.querySelector("#updatenotice").onclick = () => {
  var adminNotiTitle = document.querySelector('input[name=adminNotiTitle]').value;
  var adminNotiContent = document.querySelector('textarea[name=adminNotiContent]').value;
  var filepath = document.querySelector('input[name=filepath]').value;

  if (document.querySelector("#f-title").value == "" ||
      document.querySelector("#f-content").value == "") {
    Swal.fire('제목이나 내용을 입력해 주세요.')
      return false;
    } else {
     Swal.fire({
       title: '🔔 공지게시글 수정',
       text: "정말 수정하시겠습니까?",
       icon: 'question',
       showCancelButton: true,
       confirmButtonColor: '#3085d6',
       cancelButtonColor: '#d33',
       confirmButtonText: '네',
       cancelButtonText: '아니오'
     }).then((result) => {
       if (result.value) {
         document.querySelector("#notice-update").submit;
       }
     })
  }
}
</script>

  <!--   Core JS Files   -->
<script src="${contextPath}/css/admin/assets/js/core/popper.min.js"></script>
<script src="${contextPath}/css/admin/assets/js/core/bootstrap.min.js"></script>

<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
<script src="${contextPath}/css/admin/assets/js/material-dashboard.min.js?v=3.0.0"></script>
  
