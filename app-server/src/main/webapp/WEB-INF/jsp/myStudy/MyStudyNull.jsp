<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- plugins:css -->
  <link rel="stylesheet" href="${contextPath}/css/study/vendors/feather/feather.css">
  <link rel="stylesheet" href="${contextPath}/css/study/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="${contextPath}/css/study/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  
  <!-- Plugin css for this page -->
  <link rel="stylesheet" href="${contextPath}/css/study/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="${contextPath}/css/study/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/css/study/js/select.dataTables.min.css">
  <!-- End plugin css for this page -->
  
  <!-- inject:css -->
  <link rel="stylesheet" href="${contextPath}/css/study/css/vertical-layout-light/style.css">

  <style>
  .navbar {
    -webkit-box-shadow: 0;
    -moz-box-shadow: 0;
    box-shadow: none;
  }
  
  .content-wrapper {
    background: rgb(239 230 225);
    border-radius: 20px;
    max-height: 300px;
  }
  
  .page-body-wrapper {
    padding-top: 0;
  }
  
  .sidebar .nav .nav-item .nav-link {
    color: black;
  }
  
  .sidebar .nav:not(.sub-menu) > .nav-item > .nav-link:hover {
    background-color: rgb(239 230 225);
    color: black;
  }
  
  .sidebar .nav:not(.sub-menu) > .nav-item > .nav-link[aria-expanded="true"] {
    border-radius: 8px 8px 0 0;
    background: rgb(239 230 225);
    color: black;
  }

  .sidebar .nav.sub-menu {
    margin-bottom: 0;
    margin-top: 0;
    list-style: none;
    padding: 0px 0 0 24px;
    background: rgb(239 230 225);
    padding-bottom: 12px;
    color:black;
  }

	.sidebar .nav.sub-menu .nav-item .nav-link {
	  color:black;
  }
 
 .sidebar .nav.sub-menu .nav-item .nav-link:hover {
    color:black;
    font-weight: bold;
  }
 
   .sidebar .nav:not(.sub-menu) > .nav-item:hover > .nav-link, .sidebar .nav:not(.sub-menu) > .nav-item:hover[aria-expanded="true"] {
    background: rgb(239 230 225);
    color: #fff;
  }

 .sidebar .nav .nav-item.active > .nav-link {
    background: rgb(239 230 225);
    position: relative;
  }
  
  .sidebar .nav:not(.sub-menu) > .nav-item.active {
    background: rgb(239 230 225);
  }

  .sidebar .nav.sub-menu .nav-item .nav-link.active {
    color: black;
    background: transparent;
  }
  
 .menu-title {
  font-weight: bold;
  }
 
 .table thead th {
    font-size: 14px;
  }
 
 .table td, .jsgrid .jsgrid-table td {
    font-size: 14px;
  } 

.study-table {
    display: table-caption;
    height: 470px;
    overflow: scroll;
  }   

.study-table > th {
    text-align: center;
  }
  
      
 a {
  text-decoration: none;
  color: black;
  }

 a:hover{
  text-decoration: none;
  color: black;
  }
</style>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper">
      
      <!-- side nav-->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="${contextPath}/app/mystudy/list" style="background-color:rgb(239 230 225);">
              <span class="menu-title" style="color:black"> 📚 MY STUDY LIST</span>
            </a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#owner" aria-expanded="false" aria-controls="owner">
              <span class="menu-title" style="color:black">조장&nbsp;＞</span>
            </a>
            <div class="collapse" id="owner">
              <ul class="nav flex-column sub-menu">
			           <c:forEach items="${ownerStudyList}" var="study">
			               <li class="nav-item"><a class="nav-link" href="${contextPath}/app/mystudy/detail?studyNo=${study.studyNo}">${study.studyTitle}</a></li>
						     </c:forEach>
						     <c:if test="${empty ownerStudyList}">
						        <li class="nav-item">&nbsp;&nbsp;스터디가 없습니다.</li>
					       </c:if>
              </ul>
            </div>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#guilder" aria-expanded="false" aria-controls="guilder">
              <span class="menu-title" style="color:black">구성원&nbsp;＞</span>
            </a>
            <div class="collapse" id="guilder">
              <ul class="nav flex-column sub-menu">
                <c:forEach items="${guilderMembers}" var="study">
                  <li class="nav-item"> <a class="nav-link" href="${contextPath}/app/mystudy/detail?studyNo=${study.studyNo}">${study.studyTitle}</a></li>
                </c:forEach>
                <c:if test="${empty guilderMembers}">
					       <li class="nav-item">&nbsp;&nbsp;스터디가 없습니다.</li>
					      </c:if>
              </ul>
            </div>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#waiting" aria-expanded="false" aria-controls="waiting">
              <span class="menu-title" style="color:black">승인 대기 중&nbsp;＞</span>
            </a>
            <div class="collapse" id="waiting">
              <ul class="nav flex-column sub-menu">
                <c:forEach items="${waitingStudyList}" var="study">
                <li class="nav-item"> <a class="nav-link" href="${contextPath}/app/mystudy/detail?studyNo=${study.studyNo}">${study.studyTitle}</a></li>
                </c:forEach>
              </ul>
            </div>
          </li>
        </ul>
      </nav><!-- side nav-->
      
      <!-- main-panel -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
              <div class="card">
                <div class="card-body">
                  <p class="card-title mb-0"></p>
                  <div class="table-responsive" style="text-align: center;">
                  
                      <br>
                      가입된 스터디가 없습니다.
                      <br>
                      스터디 가입을 진행해주세요.
                      <br>
                      스터디 찾기 페이지로 이동합니다.
                      <br>
                      <br>
                      
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        
   </div> <!-- end page-body-wrapper -->
  </div> <!-- end container-scroller -->

  <!-- plugins:js -->
  <script src="${contextPath}/css/study/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  
  <!-- Plugin js for this page -->
  <script src="${contextPath}/css/study/vendors/datatables.net/jquery.dataTables.js"></script>
  <script src="${contextPath}/css/study/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
  <script src="${contextPath}/css/study/js/dataTables.select.min.js"></script>
  <!-- End plugin js for this page -->
  
  <!-- inject:js -->
  <script src="${contextPath}/css/study/js/off-canvas.js"></script>
  <script src="${contextPath}/css/study/js/hoverable-collapse.js"></script>
  <script src="${contextPath}/css/study/js/template.js"></script>
  <script src="${contextPath}/css/study/js/settings.js"></script>
  <script src="${contextPath}/css/study/js/todolist.js"></script>
  <!-- endinject -->
  
  <!-- Custom js for this page-->
  <script src="${contextPath}/css/study/js/dashboard.js"></script>
  <!-- End custom js for this page-->
</body>