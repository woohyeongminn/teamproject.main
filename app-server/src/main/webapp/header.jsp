<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="memberSession.jsp" %>
<header>
<div class="header_background">
  <div class='header_logo'>
    <a href="/ogong/index.jsp"><img src="/ogong/img/logo.png"></a>
  </div>

  <ul class="header_menu">
    <li><a href="/ogong/study/list">스터디 찾기</a></li>
    <li><a href="/ogong/cafe/list">스터디 장소 찾기</a></li>
    <li><a href="#">내 스터디</a></li>
    <li><a href="/ogong/adminNotice/list">공지사항</a></li>
  </ul>

 <ul class="header_login">
	  <c:choose>
	    <c:when test="${empty loginUser && empty loginCeoUser && empty loginAdmin}">
	      <li><a href="/ogong/signup">회원가입</a></li>
	      <li><a href="/ogong/login">로그인</a></li>
	    </c:when>
	    <c:when test="${not empty loginUser}">
	      <li><a href="/ogong/member/detail">마이페이지</a></li>
	      <li><a href="/ogong//member/logout">로그아웃</a></li>
	    </c:when>
	    <c:when test="${not empty loginCeoUser}">
        <li><a href="/ogong/ceomember/detail">기업페이지</a></li>
        <li><a href="/ogong/ceomember/logout">로그아웃</a></li>
      </c:when>
      <c:when test="${not empty loginAdmin}">
        <li><a href="/ogong/admin/detail">관리자페이지</a></li>
        <li><a href="/ogong/admin/logout">로그아웃</a></li>
      </c:when>
	 </c:choose>
  </ul>
  </div>
</header>