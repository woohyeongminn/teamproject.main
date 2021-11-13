<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
* {
font-size: 14px;
}
  p#welcome {
    text-align: center;
    margin: 100px;
  }
  .groupwarp {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  }
  .btn-group {
  margin: 0 10px;
  align-items: center;
  }
  button[type=button] {
    border-radius: 10px;
    background-color: white;
    color: black;
    margin: 0 40px;
    margin-bottom: 200px;
  }
  button[type=button]:hover {
    background-color: beige;
    color: black;
  }
  .btn-secondary:focus {
  background-color: white;
  color: black;
  }
  button[type=button1] {
    border-radius: 10px;
    border-color: lightgray;
    background-color: white;
    color: black;
  }
  button[type=button1]:hover {
    background-color: beige;
    color: black;
  }
  .dropdown-menu {
  background-color: rgba(255, 255, 255, 0);
  border: rgba(255, 255, 255, 0);
  }
  a {
  color : black;
  text-decoration : blink;
  }
  a:hover {
  color : white;
  }

</style>

</head>

<section>

<p id="welcome"><b>👑 '${loginAdmin.masterNickname}'님 환영합니다! 👑</b></p>
<br>
<br>

<div class="groupwarp">

	<div class="btn-group">
	  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
	    👑 관리자 페이지
	  </button>
	  <div class="dropdown-menu" role="menu" style="border-color: white;">
	    <button class="dromdown-item" type="button1">
	      <a href='${contextPath}/app/admin/logout' style="color: black;">🖐 로그아웃</a></button><br>
	    <button class="dromdown-item" type="button1">
	      <a href='${contextPath}/app/admin/detail' style="color: black;">🙂 마이페이지</a></button>
	  </div>
	</div>
	
	<div class="btn-group">
	  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
	    📁 회원 관리
	  </button>
	  <div class="dropdown-menu" role="menu" style="border-color: white;">
	    <button class="dromdown-item" type="button1">
	      <a href="${contextPath}/app/admin/permemberlist" style="color: black;">🎓 개인 회원</a></button><br>
	    <button class="dromdown-item" type="button1">
	      <a href="${contextPath}/app/admin/ceomember/list" style="color: black;">👔 기업 회원</a></button>
	  </div>
	</div>
	
	<div class="btn-group">
	  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
	    📖 스터디 관리
	  </button>
	  <div class="dropdown-menu" role="menu" style="border-color: white;">
	    <button class="dromdown-item" type="button1">
	      <a href="${contextPath}/app/admin/study/list" style="color: black;">📚 스터디 목록</a></button><br>
	  </div>
	</div>
	
	<div class="btn-group">
	  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
	    🏘 장소 관리
	  </button>
	  <div class="dropdown-menu" role="menu" style="border-color: white;">
	    <button class="dromdown-item" type="button1">
	      <a href="${contextPath}/app/admin/cafeList" style="color: black;">📝 장소 목록</a></button><br>
	    <button class="dromdown-item" type="button1">
	      <a href="${contextPath}/app/admin/reviewList" style="color: black;">🔖 장소 리뷰</a></button>
	  </div>
	</div>
	
	<div class="btn-group">
	  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
	    💌 고객센터 관리
	  </button>
	  <div class="dropdown-menu" role="menu" style="border-color: white;">
	    <button class="dromdown-item" type="button1">
	      <a href="${contextPath}/app/adminNotice/list" style="color: black;">📢 공지사항</a></button><br>
	    <button class="dromdown-item" type="button1">
	      <a href="${contextPath}/app/admin/askboard/list" style="color: black;">💬 문의사항</a></button>
	  </div>
	</div>
	
</div>

</section>