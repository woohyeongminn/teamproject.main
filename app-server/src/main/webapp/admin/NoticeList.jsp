<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <title>공지게시판</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  legend {
    text-align: center;
  }
  legend:hover {
    color: lightgrey;
  }
  button[type=button] {
    border-radius: 10px;
    background-color: beige;
    color: black;
  }
  button[type=button]:hover {
    background-color: blanchedalmond;
    color: black;
  }
  .btn-secondary:focus {
  background-color: beige;
  color: black;
  }
  button[type=button1] {
    border-radius: 10px;
    border-color: lightgray;
    background-color: beige;
    color: black;
  }
  button[type=button1]:hover {
    background-color: blanchedalmond;
    color: black;
  }
  .btn-group {
  display: block;
  
  }
  div {
  margin-right: 10px;
  }
  a {
  color : black;
  text-decoration : auto;
  }
  a:hover {
  color : lightgray;
  }
  </style>
</head>
<body>
<fieldset>
<br>
<legend data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample"><b> 🔔 공지게시글 목록 </b></legend><br>
<hr>
<table class="table table-responsive">
<thead>
<tr>
<th>번호</th>
<th>제목</th>
<th>내용</th>
<th>파일</th>
<th>등록일</th>
</tr>
</thead>
<tbody>
<c:forEach items="${adminNoticeList}" var="noticeList">
<tr>
	<td>( ${noticeList.adminNotiNo} )</td>
	<td><a href='detail?no=${noticeList.adminNotiNo}'>${noticeList.adminNotiTitle}</a></td>
	<td>${noticeList.adminNotiContent}</td>
	<td>${noticeList.adminNotiFile}</td>
	<td>${noticeList.adminNotiRegisteredDate}</td>
</tr>
</c:forEach>
</tbody>
</table>
</fieldset>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="등록"><a href='form'>등록</a></button>
<button type="submit" class="btn btn-outline-dark" value="로그아웃" ><a href='/ogong/admin/logout'>로그아웃</a></button> 
</div>
</body>
<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title" id="offcanvasExampleLabel">관리자</h5>
    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>

  <div class="offcanvas-body">
    <div>
      이동하실 탭을 선택하세요.
    </div>

    <div class="btn-group dropend">
      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
        👑 관리자 페이지
      </button>
      <div class="dropdown-menu" role="menu" style="border-color: white;">
        <button class="dromdown-item" type="button1">
          <a href='logout' style="color: black;">🖐 로그아웃</a></button><br>
        <button class="dromdown-item" type="button1">
          <a href='detail?no=${admin.masterNo}' style="color: black;">🙂 마이페이지</a></button>
      </div>
      
    <div class="btn-group dropend">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
		    📁 회원 관리
		  </button>
		  <div class="dropdown-menu" role="menu" style="border-color: white;">
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/admin/permemberlist" style="color: black;">🎓 개인 회원</a></button><br>
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/admin/ceomember/list" style="color: black;">👔 기업 회원</a></button>
		  </div>
		</div>
      
    <div class="btn-group dropend">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
		    📖 스터디 관리
		  </button>
		  <div class="dropdown-menu" role="menu" style="border-color: white;">
		    <button class="dromdown-item" type="button1">
		      <a href="study/list" style="color: black;">📚 스터디 목록</a></button><br>
		    <!-- <button class="dromdown-item" type="button1">
		      <a href="/study/list" style="color: black;">📔 스터디 삭제</a></button> -->
		  </div>
		</div>
		
		<div class="btn-group dropend">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
		    🏘 장소 관리(미완)
		  </button>
		  <div class="dropdown-menu" role="menu" style="border-color: white;">
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/admin/cafeList" style="color: black;">📝 장소 목록</a></button><br>
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/cafe/list" style="color: black;">🔖 장소 리뷰</a></button>
		  </div>
		</div>
		
		<div class="btn-group dropend">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
		    💌 고객센터 관리
		  </button>
		  <div class="dropdown-menu" role="menu" style="border-color: white;">
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/adminNotice/list" style="color: black;">📢 공지사항</a></button><br>
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/admin/askboardlist" style="color: black;">💬 문의사항</a></button>
		  </div>
		</div>
      
    </div>
</div>
</html>