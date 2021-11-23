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

  <script src="http://code.jquery.com/jquery-latest.js"></script> 
  <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>

  <script src="${contextPath}/css/study/vendors/datatables.net/jquery.dataTables.js"></script>
  <script src="${contextPath}/css/study/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
  <script src="${contextPath}/css/study/js/dataTables.select.min.js"></script>
  
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
    width: 96%;
    margin: 0 auto;
    border-radius: 20px;
    margin-bottom: 30px;
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
  
</style>

      <!-- side nav-->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="${contextPath}/app/mystudy/list" style="background-color:rgb(239 230 225);">
              <span class="menu-title" style="color:black"> 📚 MY STUDY LIST</span>
            </a>
           </li>
          
        <c:if test="${status != 'waiting'}">
          
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#guilder" aria-expanded="false" aria-controls="guilder">
              <span class="menu-title" style="color:black">구성원&nbsp;＞</span>
            </a>
            <div class="collapse" id="guilder">
            
              <c:if test="${member.perNo eq study.owner.perNo}">
               <ul class="nav flex-column sub-menu">
                  <li class="nav-item"> <a class="nav-link" href='${contextPath}/app/mystudy/guilder/list?studyNo=${study.studyNo}'>참여 중인 구성원</a></li>
              <!-- </ul>
              <ul class="nav flex-column sub-menu"> -->
                  <li class="nav-item"> <a class="nav-link" href='${contextPath}/app/mystudy/guilder/waitinglist?studyNo=${study.studyNo}'>승인 대기 중인 구성원</a></li>
              </ul>
              </c:if>
              
              <c:if test="${member.perNo ne study.owner.perNo}">
              <ul class="nav flex-column sub-menu">
                  <li class="nav-item"> <a class="nav-link" href='${contextPath}/app/mystudy/guilder/list?studyNo=${study.studyNo}'>참여 중인 구성원</a></li>
              </ul>
              </c:if>
              
            </div>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="${contextPath}/app/mystudy/freeboard/list?studyno=${study.studyNo}">
              <span class="menu-title" style="color:black">자유게시판&nbsp;＞</span>
            </a>
            
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="${contextPath}/app/mystudy/calendar/list?studyNo=${study.studyNo}">
              <span class="menu-title" style="color:black">캘린더&nbsp;＞</span>
            </a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="${contextPath}/app/mystudy/todo/list?studyno=${study.studyNo}">
              <span class="menu-title" style="color:black">투두리스트&nbsp;＞</span>
            </a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="${contextPath}/app/mystudy/test?studyno=${study.studyNo}">
              <span class="menu-title" style="color:black">화상미팅&nbsp;＞</span>
            </a>
          </li>
              
          <li class="nav-item">
            <a class="nav-link" onclick="return exitBtn_click(${study.studyNo}, ${study.CountMember}, ${study.waitingCountMember});">
              <span class="menu-title" style="color:black">
              <c:if test="${status == 'waiting'}">
                가입 취소&nbsp;＞
              </c:if>
              <c:if test="${status != 'waiting'}">
                탈퇴&nbsp;＞
              </c:if>
              </span>
            </a>
          </li>
          
          <c:if test="${study.owner.perNo == member.perNo}">
	          <li class="nav-item">
	            <a class="nav-link" data-toggle="collapse" href="#controll" aria-expanded="false" aria-controls="waiting">
	              <span class="menu-title" style="color:black">관리&nbsp;＞</span>
	            </a>
	            <div class="collapse" id="controll">
		             <ul class="nav flex-column sub-menu">
                    <li class="nav-item"> <a class="nav-link" href='${contextPath}/app/mystudy/updateform?studyno=${study.studyNo}'>수정</a></li>
                 </ul>
                 
                 <c:if test="${!(study.countMember > '1')}">
	               <ul class="nav flex-column sub-menu">
	                  <li class="nav-item"><a href="#" onclick="return delBtn_click(${study.waitingCountMember});"> 삭제</a></li>
	               </ul>
                 </c:if>
	            </div>
	          </li>
          </c:if>
              
        </c:if>
       </ul>
         
      </nav><!-- side nav-->

<script type="text/javascript">
  <!-- 내 스터디 삭제 -->
  function delBtn_click(waitingGuilder) {
    if (waitingGuilder > 0) {
      if (confirm("스터디 삭제 시, 승인 대기 중인 구성원도 모두 거절됩니다.\n정말 삭제하시겠습니까?") == true) {
        location.href="${contextPath}/app/mystudy/delete?studyno=${study.studyNo}";
      } else {
        return false;
      }

    } else {
      if (confirm("정말 삭제하시겠습니까?") == true) {
        location.href="${contextPath}/app/mystudy/delete?studyno=${study.studyNo}";
      } else {
        return false;
      }
    }
  }
  
  <!-- 내 스터디 탈퇴 -->
  function exitBtn_click(studyno, countMember, waitingGuilder) {
    if (countMember > 1) {
      if (confirm("구성원에게 조장 권한을 위임하고 탈퇴를 진행해 주세요.") == true) {
        location.href="${contextPath}/app/mystudy/exit?studyno=${study.studyNo}";
      } else {
        return false;
      }

    } else if (waitingGuilder > 0) {
      if (confirm("스터디 탈퇴 시, 승인 대기 중인 구성원도 모두 거절됩니다.\n정말 탈퇴하시겠습니까?") == true) {
        location.href="${contextPath}/app/mystudy/exit?studyno=${study.studyNo}";
      } else {
        return false;
      }

    } else {
      if (confirm("정말 탈퇴하시겠습니까?") == true) {
        location.href="${contextPath}/app/mystudy/exit?studyno=${study.studyNo}";
      } else {
        return false;
      }
    }
  }
  </script>
  
  <!-- inject:js -->
  <script src="${contextPath}/css/study/js/off-canvas.js"></script>
  <script src="${contextPath}/css/study/js/hoverable-collapse.js"></script>
  <%--   <script src="${contextPath}/css/study/js/template.js"></script> --%>
  <script src="${contextPath}/css/study/js/settings.js"></script>
  <script src="${contextPath}/css/study/js/todolist.js"></script>
  <!-- endinject -->
  
  <!-- Custom js for this page-->
  <script src="${contextPath}/css/study/js/dashboard.js"></script>
  <script src="${contextPath}/css/study/vendors/js/vendor.bundle.base.js"></script>
  <!-- End custom js for this page-->
</body>
      