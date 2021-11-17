<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <link rel="stylesheet" href="${contextPath}/node_modules/bootstrap/dist/css/bootstrap.css">
<script src="${contextPath}/node_modules/@popperjs/core/dist/umd/popper.js"></script>
<script src="${contextPath}/node_modules/bootstrap/dist/js/bootstrap.js"></script>
   --%>
<style>
.dropdown-item:focus {
  background-color: white;
}
</style>


<header>
<div class="header_background">
  <div class='header_logo'>
    <a href="${contextPath}/app/index"><img src="${contextPath}/img/logo.png"></a>
  </div>

  <ul class="header_menu">
    <li><a href="${contextPath}/app/study/list">스터디 찾기</a></li>
    <li><a href="${contextPath}/app/cafe/list">스터디 장소 찾기</a></li>
    <c:choose>
    <c:when test="${empty loginUser && empty loginCeoUser && empty loginAdmin}">
        <li><a href="${contextPath}/app/login">내 스터디</a></li>
    </c:when>
    <c:when test="${not empty loginUser}">
        <li><a href="${contextPath}/app/mystudy/list">내 스터디</a></li>
    </c:when>
    <c:when test="${not empty loginCeoUser}">
        <li><a href="${contextPath}/app/ceomember/cafe/detail">내 카페</a></li>
    </c:when>
    </c:choose>
    <li>
      <a class="dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-offset="10,20">
        고객센터
      </a>
      <ul class="dropdown-menu">
        <li><a class="dropdown-item" href="${contextPath}/app/adminNotice/list">공지사항</a></li>
        <li><a class="dropdown-item" href="${contextPath}/app/askboard/alllist">문의게시판</a></li>
      </ul>
   </li>
  </ul>

  <ul class="header_login">
	  <c:choose>
	    <c:when test="${empty loginUser && empty loginCeoUser && empty loginAdmin}">
	      <li><a href="${contextPath}/app/signup">회원가입</a></li>
	      <li><a href="${contextPath}/app/login">로그인</a></li>
	    </c:when>
	    <c:when test="${not empty loginUser}">
	      <li><a href="${contextPath}/app/member/detail">마이페이지</a></li>
	      <li><a href="${contextPath}/app/member/logout">로그아웃</a></li>
	    </c:when>
	    <c:when test="${not empty loginCeoUser}">
        <li><a href="${contextPath}/app/ceomember/detail">기업페이지</a></li>
        <li><a href="${contextPath}/app/ceomember/logout">로그아웃</a></li>
      </c:when>
      <c:when test="${not empty loginAdmin}">
        <li><a href="${contextPath}/app/admin/detail">관리자페이지</a></li>
        <li><a href="${contextPath}/app/admin/logout">로그아웃</a></li>
      </c:when>
	 </c:choose>
  </ul>
 </div>
</header>