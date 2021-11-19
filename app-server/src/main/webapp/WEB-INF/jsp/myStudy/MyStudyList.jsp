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
    max-height: 680px;
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
      
      <!-- main-panel -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
              <div class="card">
                <div class="card-body">
                  <p class="card-title mb-0"></p>
                  <div class="table-responsive">
                  
                    <table class="table table-borderless">
                      <thead>
                        <tr style="width: 100%;text-align: center;">
                          <th class="border-bottom" width="190px">스터디명</th>
                          <th class="border-bottom" width="108px">분야</th>
                          <th class="border-bottom" width="163px">조장</th>
                          <th class="border-bottom" width="150px">대면/비대면</th>
                          <th class="border-bottom" width="98px">인원수</th>
                        </tr>
                      </thead>
                      
                      <tbody class="study-table">
                        <c:forEach items="${myStudyList}" var="studyList">
                          <c:if test="${studyList.studyStatus != 3}">
		                        <tr style="width: 100%;text-align: center;">
		                          <td class="text-muted" width="5%"><a href="${contextPath}/app/mystudy/detail?studyNo=${studyList.studyNo}">${studyList.studyTitle}</a></td>
		                          <td class="text-muted" width="5%">${studyList.subjectName}</td>
		                          <td class="text-muted" width="5%">${studyList.owner.perNickname}</td>
		                          <td class="text-muted" width="5%">${studyList.faceName}</td>
		                          <td class="text-muted" width="5%">${studyList.countMember}/${studyList.numberOfPeple}명</td>
		                        </tr>
                          </c:if>
                        </c:forEach>
                      </tbody>
                    </table>
                    
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        
   </div> <!-- end page-body-wrapper -->
  </div> <!-- end container-scroller -->


<script>
document.querySelectorAll("tbody a").forEach((aTag) => {
  aTag.target.onclick = () => false;
});                

var trList = document.querySelectorAll("tbody tr");
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
      window.location.href = e.currentTarget.querySelector("a").href; // 방법 2) 현재 페이지를 그 링크로 바꾸게 해라
    };
  });
</script>



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