<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  
  .all-content {
  margin: 20px;
  font-size: 14px;
  }
  
  ul {
  list-style:none;
  }
  
  .cafe-top {
  width: 100%;
  height: 300px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-bottom: 15px;
  }

  .slide{height:300px;overflow:hidden;}
  .slide ul{width:calc(100% * 4);display:flex;animation:slide 8s infinite;} /* slide를 8초동안 진행하며 무한반복 함 */
  .slide li{width:calc(100% / 4);height:300px;}
  .slide li:nth-child(1){background:#ffa;}
  .slide li:nth-child(2){background:#faa;}
  .slide li:nth-child(3){background:#afa;}
  .slide li:nth-child(4){background:#aaf;}
  @keyframes slide {
   0% {margin-left:0;} /* 0 ~ 10  : 정지 */
   10% {margin-left:0;} /* 10 ~ 25 : 변이 */
   25% {margin-left:-100%;} /* 25 ~ 35 : 정지 */
   35% {margin-left:-100%;} /* 35 ~ 50 : 변이 */
   50% {margin-left:-200%;}
   60% {margin-left:-200%;}
   75% {margin-left:-300%;}
   85% {margin-left:-300%;}
   100% {margin-left:0;}
  }

  #content {
   display: block;
   width: 100%;
   padding: 10px 10px 0 10px;
  }
  #content > label {
  width: 15%;
  font-weight: bold;
  padding: 5px 0;
  color: #4e4847;
  }
  #content > span {
  display: inline-block;
  width: 122px;
  padding: 5px 0;
  font-weight: 600 !important;
  color: #7b809a;
  }

  .cafe-bottom {
  width: 100%;
  text-align: left;
  padding: 5px 10px;
  font-weight: 600 !important;
  color: #7b809a;
  }
  .cafe-bottom > label {
   width: 15%;
   font-weight: bold;
   padding: 5px 0;
   color: #4e4847;
  }
  .cafe-bottom > span {
  width: 80%;
  padding: 5px 0;
  font-weight: 600 !important;
  color: #7b809a;
  }
  
  .label-wrap {
  width: 100%;
  height: fit-content;
  display: flex;
  flex-direction: row;
  }
  .label-wrap > label {
  width: 15%;
  font-weight: bold;
  padding: 5px 0;
  color: #4e4847;
  }
  .label-wrap > span {
   width:80%;
   padding: 5px 0;
   overflow: scroll;
   font-weight: 600 !important;
   color: #7b809a;
  }

  .cafe-bottom-review {
  width: 100%;
  text-align: left;
  font-weight: 600 !important;
  color: #7b809a;
  }
 
  .line {
  width: 100%;
  height: 4px;
  background: gray;
  }

  .review-wrap {
  height: 180px;
  /* overflow-y: scroll; */
  }
  .review-wrap::-webkit-scrollbar {
   width: 10px;
  }
  .review-wrap::-webkit-scrollbar-thumb {
   background-color: rgb(247, 231, 215);
   border-radius: 10px;
   background-clip: padding-box;
   border: 2px solid transparent;
  }
  .review-wrap::-webkit-scrollbar-track {
   background-color: rgb(250, 250, 234);
   border-radius: 10px;
   box-shadow: inset 0px 0px 5px white;
  }

  #c-review-content {
  margin: 0;
  font-size: 14px;
  color: #4e4847;
  font-weight: 530 !important;
  }
  #c-review {
  background-color: whitesmoke;
  height: fit-content;
  margin-bottom: 10px;
  padding: 10px;
  }
  
  button[type=submit] {
  float: right;
  margin-right: 10px;
  font-size: 12px;
  line-height: 5px;
  }
  
  .btn_wrap {
  max-width: 420px;
  margin: 20px auto 0;
  text-align: center;
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-bottom: 100px;
  }
  
  </style>
  
</head>

 <form action='cafeApproval' name='approval' method='post'>
  <input id='c-no' type='hidden' name="no" value='${cafe.no}'>

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
          <a class="nav-link text-white" href="${contextPath}/app/admin/detail">
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
          <a class="nav-link text-white active bg-gradient-primary" href="${contextPath}/app/admin/cafeList" style="background-color: rgb(161 135 120);">
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
              <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3" style="background-color: #edb68a; box-shadow: 0 4px 20px 0 rgb(0 0 0 / 14%), 0 7px 10px -5px rgb(237 182 138 / 40%) !important;">
                <h6 class="text-white text-capitalize ps-3">${cafe.name}</h6>
              </div>
            </div>
            <div class="card-body px-0 pb-2">
              <div class="table-responsive p-0">
	              <div class="all-content"> 
	                <div class = "cafe-top">
	                  <div class="slide">
	                    <ul>
	                      <li><img src="${contextPath}/upload/cafe/${cafe.cafeImgs[0].name}" style="width:100%"></li>
	                      <li><img src="${contextPath}/upload/cafe/${cafe.cafeImgs[1].name}" style="width:100%"></li>
	                      <li><img src="${contextPath}/upload/cafe/${cafe.cafeImgs[2].name}" style="width:100%"></li>
	                    </ul>
	                  </div>
	                 </div>

	                <!-- 카페 상세 글 부분 -->      
	                <div id='content'>
	                  <label for='f-bossName'>대표자</label><span>${cafe.ceoMember.ceoBossName}</span><br>
	                  <label for='f-licenseNo'>사업자 등록번호</label><span>${cafe.ceoMember.ceoLicenseNo}</span><br>
	                  <div class="label-wrap"><label for='f-location'>주소</label> <span style="height: fit-content;">${cafe.location}</span></div>
	                </div>
	               <div class="cafe-bottom">
	                 <div class="label-wrap"><label for='f-info'>소개글</label><span style="height: fit-content;">${cafe.info}</span></div>
	                 <label for='f-tel'>전화번호</label><span>${cafe.phone}</span><br>
	                 <label for='f-openTime'>운영시간</label><span>${cafe.openTime} AM ~ ${cafe.closeTime} PM</span><br>
	                 <label for='f-holiday'>이번주 휴무일</label><span>${cafe.holiday}</span><br>
	                 <label for='f-review'>리뷰평점</label><span>⭐${cafe.avgReview} / ${cafe.countReview}개</span><br>
	                 <label for='f-cafeStatus'>운영 상태</label>
	                 
	                 <c:if test="${cafe.cafeStatus != 1}">
	                   <script>
	                   if(${cafe.cafeStatus == 1}) {
	                     document.write("<label for='f-cafeStatus'>운영 상태</label>승인 대기");
	                   } else if(${cafe.cafeStatus == 2}) {
	                     document.write("<label for='f-cafeStatus'>운영 상태</label>운영 중");
	                   } else if(${cafe.cafeStatus == 3}) {
	                     document.write("<label for='f-cafeStatus'>운영 상태</label>운영 중단");
	                   } else if(${cafe.cafeStatus == 4}) {
	                     document.write("<label for='f-cafeStatus'>운영 상태</label>삭제");
	                   } 
	                   </script>
	                 </c:if>
	                 
	                 <c:if test="${cafe.cafeStatus == 1}">
								    <select name="cafeStatus">
								      <c:if test='${cafe.cafeStatus==1}'>
								          <option value="${cafe.cafeStatus}">승인 대기</option>
								          <option value="2" name="cafeStatus" >운영 중</option>
								          <option value="3" disabled>운영 중단</option>
								          <option value="4" disabled>삭제</option>
								       </c:if>
								     </select>
								    </c:if>
					       </div>

               <!-- 리뷰 시작부분에 선 -->
               <div class="cafe-bottom-review">
                 <hr style="border-top: 4px double #bbb; text-align: center; display: inline-block; width: 48%; margin: 6px 0">
                 <i class="far fa-comments" style="color:#bbb; font-size: large;"></i>
                 <hr style="border-top: 4px double #bbb; text-align: center; display: inline-block; width: 48%;  margin: 6px 0">
             
                 <!-- 리뷰 보여지는 부분 -->
                 <c:if test='${not empty reviewList}'>
                  <div class = "review-wrap">
                   <c:forEach items="${reviewList}" var="review">
                    <div id='c-review'>
                    <p id='c-review-content'>${review.content}</p>
                     <span id='c-grade'>
                       <c:set var="grade" value="${review.grade}" /> 
                          <% 
                          int grade = (int) pageContext.getAttribute("grade");
                          String star = CafeHandlerHelper.getReviewGradeStatusLabel(grade);
                          pageContext.setAttribute("star", star);
                          %>
                       <span style="font-size: 12px;">${star}(${review.grade}/5)</span>
                     </span>
                     <span style="font-size: 12px;"> | ${review.member.perNickname} | ${review.registeredDate}</span>
                    </div>
                   </c:forEach>
                 </div>
               </c:if>
             
             <c:if test='${empty reviewList}'>
             등록된 리뷰가 없습니다.<br><br>  
             </c:if>
           </div>
          </div>
          
            <div id='button_wrap'>
					    <button type="submit" class="btn btn-outline-dark" onclick="agree();">승인</button>
					  </div>
        
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
    
 </main>
 </form>

  <!--   Core JS Files   -->
<script src="${contextPath}/css/admin/assets/js/core/popper.min.js"></script>
<script src="${contextPath}/css/admin/assets/js/core/bootstrap.min.js"></script>

<script type="text/javascript">
function agree(){
  Swal.fire({
    title: '승인되었습니다.',
    icon: 'info',
    confirmButtonColor: '#3085d6',
    confirmButtonText: '확인',
    timer: 30000
    }).then((result) => {
      if (result.value) {
        location.href = "${contextPath}/app/admin/cafeList";
        }
      })
    }
</script>

<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
<script src="${contextPath}/css/admin/assets/js/material-dashboard.min.js?v=3.0.0"></script>
