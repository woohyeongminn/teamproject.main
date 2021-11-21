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
  
  /* 투두 */

button#buttonsave {
   background-color: white;
}

button#buttonsave:hover {
  font-weight: bold;
}


.btn-group {
    padding: 11px;
    margin-top: 129px;
    padding-left: 88px;
}

.addForm-control {
    width: 367px;
    padding: 0;
    margin: 0;
    height: 38px;
    border: 0;
    border-bottom: 1px solid gray;
}

.todoAddBtn {
    font-size: 16px;
    position: absolute;
    z-index: 99999;
    right: 0;
    bottom: 6px;
    height: 23px;
    width: 46px;
    border: 0;
    margin: 0;
}

.list-group {
    height: 267px;
    overflow: scroll;
}

.todoDeleteBtn {
    border: 0;
    font-size: 13px;
    position: absolute;
    right: 12px;
    bottom: 26%;
    background-color: white;
   } 
   
  .studyBtn {
   padding: 10px 30px;
   border-radius: 10px;
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
                    <li class="nav-item">조장으로 참여 중인 스터디가 없습니다.</li>
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
                 <li class="nav-item">구성원으로 참여 중인 스터디가 없습니다.</li>
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

      <%-- main-panel --%>
      <div class="main-panel">
        <div class="content-wrapper">
        
          <%-- row studyInfo --%>
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
            </div> <%-- end col-6 --%>
            
            <div class="col-6 btn-group">
	             <c:if test='${study.owner.perNo == loginUser.perNo}'>
	              <a href='${contextPath}/app/mystudy/updateform?studyno=${study.studyNo}' class="btn btn-outline-dark studyBtn">수정</a>
	                  
	              <c:if test="${!(study.countMember > '1')}">
	                <button type="button" class="btn btn-outline-dark studyBtn" onclick="return delBtn_click(${study.waitingCountMember});">삭제</button>
	              </c:if>
	             </c:if>
	            
	            <button type="button" class="btn btn-outline-dark studyBtn" onclick="return exitBtn_click(${study.studyNo}, ${study.CountMember}, ${study.waitingCountMember});">
	            <c:if test="${status == 'waiting'}">
	              가입 취소
	            </c:if>
	            <c:if test="${status != 'waiting'}">
	            탈퇴
	            </c:if>
	            </button>
           </div> <%-- end col-6 --%>
         </div> <%-- end row studyInfo --%>
          
          
          
          
          
          <c:if test="${status != 'waiting'}">
          
          <%-- row sub-items --%>
          <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
            
              <%-- TO DO --%>
              <div class="col-md-6 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body" style="padding: 5px;">
								    <div class="input-group mb-3">
									    <form action='todo/add' method='post' id="todobox">
									       <input type='hidden' name='studyNo' value='${study.studyNo}'>
									       <input type="text" class="addForm-control" id="input" name='todoContent'>
									       <input id='f-progress_no' type='hidden' class="form-control" name='todocomplete' value="진행 중" readonly>
									       <input id='f-progress_no' type='hidden' class="form-control" name='todoStatus' value="1" readonly>
									       <input id='f-writer' type='hidden' class="form-control" value='${member.perNickname}' readonly>
									       <button class="todoAddBtn" type="submit" id="buttonsave">입력</button>
									    </form>
								    </div>
								  
									  <ul class="list-group" id="list">
									    <li class="list-group-item">
                         <a href='${contextPath}/app/mystudy/todo/list?studyno=${study.studyNo}'>
                           📋 TO DO LIST
                         </a>
                      </li>
									      <c:if test="${empty todoList}">
									        <br>&nbsp;등록된 To-Do List가 없습니다.
									      </c:if>
									      <c:forEach items="${todoList}" var="todo">
										      <c:if test="${not empty todoList}">
										        <li class="list-group-item" style="text-align: justify;">${todo.todoContent}
										          <form action='todo/delete' id="tododelete">
											          <input type='hidden' name='studyno' value='${study.studyNo}'>
											          <input type='hidden' name='todono' value='${todo.todoNo}'>
											          <button class="todoDeleteBtn" type='submit' onclick='remove("+cnt+")'>삭제</button>
										          </form>
										        </li>
										      </c:if>
									      </c:forEach>
									  </ul>
                  </div>
                </div>
              </div>
              
   
            
            <div class="col-md-6 grid-margin transparent">
              <div class="row">
                <div class="col-md-6 mb-4 stretch-card transparent">
                  <div class="card" style="background-color: #fbb172">
                    <a href='${contextPath}/app/mystudy/guilder/list?studyNo=${study.studyNo}' style="text-decoration: none;">
                      <div class="card-body" style="color: white;">
                        <p class="mb-4">구성원</p>
                        <p class="fs-30 mb-2">Guilder</p>
                        <p>Member ${study.countMember}/${study.numberOfPeple}</p>
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
                    <a href='${contextPath}/app/mystudy/calendar/list?studyNo=${study.studyNo}' style="text-decoration: none;">
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
                    <a href='${contextPath}/app/mystudy/test?studyno=${study.studyNo}' style="text-decoration: none;">
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
        </div> <%-- end row sub-items --%>
        
        </c:if>
        
       </div> <%-- end content-wrapper --%>
     </div> <%-- main-panel --%>
     
     </div>
   </div>
  
  
  
  
  <!-- 투두 -->
  <script type="text/javascript">
  var button = document.getElementById('buttonsave');
  var input = document.getElementById('input');
  var list = document.getElementById('list');
  var cnt = 1;

  button.addEventListener('click', clickButton);

  function clickButton() {
    var temp = document.createElement('li');
    temp.setAttribute("class", "list-group-item");
    temp.setAttribute("id", "li"+cnt);
    temp.innerHTML = input.value;
   /*  temp.innerHTML += "<button style='float: right;' class='btn btn-outline-dark' type='button' onclick='remove("+cnt+")'>삭제</button>"; */
    temp.innerHTML += "<button class="todoDeleteBtn" type='submit' onclick='remove("+cnt+")'>삭제</button>";
    list.appendChild(temp);
    cnt++;
  }

  function remove(cnt) {
    //window.alert(cnt);
    var li = document.getElementById('li'+cnt);
    list.removeChild(li);
  }
  </script>

  
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
      