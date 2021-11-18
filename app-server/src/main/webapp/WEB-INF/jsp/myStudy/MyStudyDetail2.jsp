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
    /* background: rgb(239 230 225); */
    background-color: white;
    border-radius: 20px;
    max-height: 680px;
    padding: 30px;
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
  
  .studyInfo {
    background-color: rgb(239 230 225);
    height: 200px;
    width: 97%;
    margin: 0 auto;
    border-radius: 20px;
    margin-bottom: 30px;
  }
</style>

<body>
<div class="container-scroller">
    
    <!-- temp -->
    <!-- 안쓰는데 있어야함 / 빈 부분 공간차지하라고(템플릿) -->
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
      </div><!-- temp -->
      
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
              <span class="menu-title" style="color:black">조장</span>
              <span>&nbsp;＞</span>
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
              <span class="menu-title" style="color:black">구성원</span>
              <span>&nbsp;＞</span>
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
              <span class="menu-title" style="color:black">승인 대기 중</span>
              <span>&nbsp;＞</span>
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
   
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row studyInfo">
                <div class="col-6" style="padding: 25px; font-size: 14px;">
                  
                  <span class="font-weight-bold" style="font-size: 16px;">
                  ${study.studyTitle} &nbsp; ⭐${study.countBookMember}
                  </span>
                  <br>
                  <br>
                  <c:choose>
                      <c:when test="${study.countMember < study.numberOfPeple}">
                      [모집 중]
                      </c:when>
                      <c:otherwise>
                      [모집 완료]
                      </c:otherwise>
                    </c:choose>
                    <br>
                    <span class="location font-weight-normal">소개 | </span>
                    <span class="font-weight-normal mb-0">${study.introduction}</span>
                    <br>
                    
                    <span class="location font-weight-normal">조장 | </span>
                    <span>${study.owner.perNickname}</span>
                    <br>
                    
                    <span class="location font-weight-normal">분야 | </span>
                    <span>${study.subjectName}</span>
                    <br>
                    
                    <span class="location font-weight-normal">지역 | </span>
                    <span>${study.area}</span>
                    <br>
                    
                    <%-- <span class="location font-weight-normal">북마크</span>
                    <span>${study.countBookMember}</span> --%>
                    
                    <span class="location font-weight-normal">대면상태 | </span>
                    <span>${study.faceName}</span>
                    <br>
                </div>
                
                
                
                <div class="col-6" style="padding: 25px; font-size: 14px;">
                <c:if test='${study.owner.perNo == loginUser.perNo}'>
					        <a href='${contextPath}/app/mystudy/updateform?studyno=${study.studyNo}' class="btn btn-outline-dark">수정</a>
					        <c:if test="${!(study.countMember > '1')}">
					          <a href='${contextPath}/app/mystudy/delete?studyno=${study.studyNo}' class="btn btn-outline-dark">삭제</a>
					        </c:if>
					      </c:if>
                </div>
                
                
          </div>
          <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
                    
                    
                    
                    <%-- <jsp:include page="todo/ToDoPopup.jsp"/> --%>
                    
                    
                    <!-- 여기 -->
                    <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">To Do Lists</h4>
                  <div class="list-wrapper pt-2">
                    <ul class="d-flex flex-column-reverse todo-list todo-list-custom">
                      <li>
                        <div class="form-check form-check-flat">
                          <label class="form-check-label">
                            <input class="checkbox" type="checkbox">
                            Meeting with Urban Team
                          </label>
                        </div>
                        <i class="remove ti-close"></i>
                      </li>
                      <li class="completed">
                        <div class="form-check form-check-flat">
                          <label class="form-check-label">
                            <input class="checkbox" type="checkbox" checked>
                            Duplicate a project for new customer
                          </label>
                        </div>
                        <i class="remove ti-close"></i>
                      </li>
                      <li>
                        <div class="form-check form-check-flat">
                          <label class="form-check-label">
                            <input class="checkbox" type="checkbox">
                            Project meeting with CEO
                          </label>
                        </div>
                        <i class="remove ti-close"></i>
                      </li>
                      <li class="completed">
                        <div class="form-check form-check-flat">
                          <label class="form-check-label">
                            <input class="checkbox" type="checkbox" checked>
                            Follow up of team zilla
                          </label>
                        </div>
                        <i class="remove ti-close"></i>
                      </li>
                      <li>
                        <div class="form-check form-check-flat">
                          <label class="form-check-label">
                            <input class="checkbox" type="checkbox">
                            Level up for Antony
                          </label>
                        </div>
                        <i class="remove ti-close"></i>
                      </li>
                    </ul>
                  </div>
                  <div class="add-items d-flex mb-0 mt-2">
                    <input type="text" class="form-control todo-list-input"  placeholder="Add new task">
                    <button class="add btn btn-icon text-primary todo-list-add-btn bg-transparent"><i class="icon-circle-plus"></i></button>
                  </div>
                </div>
              </div>
                    
                    
                    
            </div>
            <div class="col-md-6 grid-margin transparent">
              <div class="row">
                <div class="col-md-6 mb-4 stretch-card transparent">
                  <div class="card" style="background-color: #fbb172">
                    <a href="'${contextPath}/app/mystudy/guilder/list?studyNo=${study.studyNo}' style="text-decoration: none;">
                      <div class="card-body" style="color: white;">
                        <p class="mb-4">구성원</p>
                        <p class="fs-30 mb-2">Guilder</p>
                        <p>Member 3/10</p>
                      </div>
                    </a>
                  </div>
                </div>
                <div class="col-md-6 mb-4 stretch-card transparent">
                  <div class="card" style="background-color: #ed6e99">
                    <a href="${contextPath}/app/mystudy/freeboard/list?studyno=${study.studyNo}" style="text-decoration: none;">
                      <div class="card-body" style="color: white;">
                        <p class="mb-4">자유게시판</p>
                        <p class="fs-30 mb-2">Board</p>
                        <p>new!</p>
                      </div>
                    </a>
                  </div>
                </div>
              </div>
              
              <div class="row">
                <div class="col-md-6 mb-4 mb-lg-0 stretch-card transparent">
                  <div class="card" style="background-color: #7bc9b2">
                    <a href='${contextPath}/app/mystudy/calendar/list' style="text-decoration: none;">
                      <div class="card-body" style="color: white;">
                        <p class="mb-4">캘린더</p>
                        <p class="fs-30 mb-2">Calender</p>
                        <p>2021-11-17</p>
                      </div>
                    </a>
                  </div>
                </div>
                <div class="col-md-6 stretch-card transparent">
                  <div class="card" style="background-color: #49a3f1">
                    <a href="#화상미팅" style="text-decoration: none;">
                      <div class="card-body" style="color: white;">
                        <p class="mb-4">화상미팅</p>
                        <p class="fs-30 mb-2">Meet</p>
                        <p>Today Study</p>
                      </div>
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          </div>
          </div>
          
          
        </div>
        
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
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
