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
  <!-- endinject -->
  <link rel="shortcut icon" href="${contextPath}/css/study/images/favicon.png" />

<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
        <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
          <span class="icon-menu"></span>
        </button>
        <ul class="navbar-nav mr-lg-2">
          <li class="nav-item nav-search d-none d-lg-block">
            <div class="input-group">
              
              <input type="text" class="form-control" id="navbar-search-input" placeholder="Search now" aria-label="search" aria-describedby="search">
            </div>
          </li>
        </ul>
        <ul class="navbar-nav navbar-nav-right">
          <li class="nav-item dropdown">
            <a class="nav-link count-indicator dropdown-toggle" id="notificationDropdown" href="#" data-toggle="dropdown">
              <i class="icon-bell mx-0"></i>
              <span class="count"></span>
            </a>
            <div class="#" aria-labelledby="notificationDropdown"></div>
          </li>
          <li class="nav-item nav-profile dropdown">
            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
              <img src="${contextPath}/css/study/images/faces/face28.jpg" alt="profile"/>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
              <a class="dropdown-item">
                <i class="ti-settings text-primary"></i>
                마이페이지
              </a>
              <a class="dropdown-item">
                <i class="ti-power-off text-primary"></i>
                로그아웃
              </a>
            </div>
          </li>
          <li class="nav-item nav-settings d-none d-lg-flex">
            <a class="nav-link" href="#">
              <i class="icon-ellipsis"></i>
            </a>
          </li>
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          <span class="icon-menu"></span>
        </button>
      </div>
    </nav>
    
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
    
      <div id="right-sidebar" class="settings-panel">
        <i class="settings-close ti-close"></i>
        <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
          <li class="nav-item">
            <a class="nav-link active" id="todo-tab" data-toggle="tab" href="#todo-section" role="tab" aria-controls="todo-section" aria-expanded="true">TO DO LIST</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="chats-tab" data-toggle="tab" href="#chats-section" role="tab" aria-controls="chats-section">MEMBERS</a>
          </li>
        </ul>
        
        <div class="tab-content" id="setting-content">
          <p>스터디를 먼저 선택하세요</p>
        </div>
      </div>
      
      <!-- partial -->
      <!-- partial:partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="../index2">
              <i class="icon-grid menu-icon"></i>
              <span class="menu-title">MY STUDY LIST</span>
            </a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#owner" aria-expanded="false" aria-controls="owner">
              <i class="icon-layout menu-icon"></i>
              <span class="menu-title">조장</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="owner">
              <ul class="nav flex-column sub-menu">
			           <c:forEach items="${ownerStudyList}" var="study">
			               <li class="nav-item"><a class="nav-link" href="${contextPath}/app/mystudy/detail?studyNo=${study.studyNo}">${study.studyTitle}</a></li>
						     </c:forEach>
						     <c:if test="${empty ownerStudyList}">
						        <li class="nav-item">조장으로 참여 중인 스터디가 없습니다.</li>
					       </c:if>
              </ul>
            </div>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#guilder" aria-expanded="false" aria-controls="guilder">
              <i class="icon-bar-graph menu-icon"></i>
              <span class="menu-title">구성원</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="guilder">
              <ul class="nav flex-column sub-menu">
                <c:forEach items="${guilderMembers}" var="study">
                  <li class="nav-item"> <a class="nav-link" href="${contextPath}/app/mystudy/detail?studyNo=${study.studyNo}">${study.studyTitle}</a></li>
                </c:forEach>
                <c:if test="${empty guilderMembers}">
					       <li class="nav-item">구성원으로 참여 중인 스터디가 없습니다.</li>
					      </c:if>
              </ul>
            </div>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#waiting" aria-expanded="false" aria-controls="waiting">
              <i class="icon-grid-2 menu-icon"></i>
              <span class="menu-title">승인 대기 중</span>
              <i class="menu-arrow"></i>
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
      </nav>
      
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
              <div class="card">
                <div class="card-body">
                  <p class="card-title mb-0">스터디 목록</p>
                  <div class="table-responsive">
                    <table class="table table-borderless">
                      <thead>
                        <tr>
                          <th class="pl-0  pb-2 border-bottom">이름</th>
                          <th class="border-bottom pb-2">분야</th>
                          <th class="border-bottom pb-2">조장</th>
                          <th class="border-bottom pb-2">대면/비대면</th>
                          <th class="border-bottom pb-2">인원수</th>
                        </tr>
                      </thead>
                      <tbody>
                      
                        <c:forEach items="${myStudyList}" var="studyList">
	                        <tr>
	                          <td class="pl-0">${studyList.studyTitle}</td>
	                          <td><p class="mb-0">${studyList.subjectName}</p></td>
	                          <td class="text-muted">${studyList.owner.perNickname}</td>
	                          <td class="text-muted">${studyList.faceName}</td>
	                          <td class="text-muted">${studyList.numberOfPeple}명</td>
	                        </tr>
                        </c:forEach>
                        
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>   
        <!-- partial -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->

  <!-- plugins:js -->
  <script src="${contextPath}/css/study/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  
  <!-- Plugin js for this page -->
  <script src="${contextPath}/css/study/vendors/chart.js/Chart.min.js"></script>
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
  <script src="${contextPath}/css/study/js/Chart.roundedBarCharts.js"></script>
  <!-- End custom js for this page-->
</body>