<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- =======  기존 헤더  ======= -->


<%-- <link rel="stylesheet" href="${contextPath}/node_modules/bootstrap/dist/css/bootstrap.css">
<script src="${contextPath}/node_modules/@popperjs/core/dist/umd/popper.js"></script>
<script src="${contextPath}/node_modules/bootstrap/dist/js/bootstrap.js"></script>
   --%>
<style>
.dropdown-item:focus {
  background-color: white;
}
</style>


<!-- ======= Header ======= -->
  <header id="header" class="fixed-top d-flex align-items-center header-transparent" style="position:relative;">
    <div class="container d-flex align-items-center">

      <h1 class="logo me-auto">
      <a href="${contextPath}/app/index2"><img src="${contextPath}/css/assets/img/logo.png"></a>
      </h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo me-auto"><img src="${contextPath}/css/assets/img/logo.png" alt="" class="img-fluid"></a>-->

      <nav id="navbar" class="navbar order-last order-lg-0">
        <ul>
          <li><a class="nav-link scrollto active" href="${contextPath}/app/index">Home</a></li>
          <li><a class="nav-link scrollto" href="${contextPath}/app/study/list">스터디 찾기</a></li>
          <li><a class="nav-link scrollto" href="${contextPath}/app/cafe/list">장소 찾기</a></li>
          
          <c:choose>
            <c:when test="${empty loginUser && empty loginCeoUser && empty loginAdmin}">
              <li><a class="nav-link scrollto" href="${contextPath}/app/login">내 스터디</a></li>
            </c:when>
            
            <c:when test="${not empty loginUser}">
              <li><a class="nav-link scrollto"  href="${contextPath}/app/mystudy/list2">내 스터디</a></li>
            </c:when>
            
            <c:when test="${not empty loginCeoUser}">
              <li><a class="nav-link scrollto"  href="${contextPath}/app/ceomember/cafe/detail">내 카페</a></li>
            </c:when>
          </c:choose> 
          
          <li class="dropdown"><a href="#"><span>고객센터</span> <i class="bi bi-chevron-down"></i></a>
            <ul>
              <li><a href="${contextPath}/app/adminNotice/list">공지사항</a></li>
              <li><a href="${contextPath}/app/askboard/alllist">문의게시판</a></li>
            </ul>
          </li>
          
          <c:choose>
              
              <c:when test="${empty loginUser && empty loginCeoUser && empty loginAdmin}">
              <!-- 비회원 -->
                <li><a class="nav-link scrollto" href="${contextPath}/app/signup">회원가입</a></li>
                <li><a class="nav-link scrollto" href="${contextPath}/app/login">로그인</a></li>
              </c:when>
              
              <c:when test="${not empty loginUser}">
              <!-- 개인 회원 -->
                <li><a class="nav-link scrollto" href="${contextPath}/app/member/detail">마이페이지</a></li>
                <li><a class="nav-link scrollto" href="${contextPath}/app/member/logout">로그아웃</a></li>
              </c:when>
              
              <c:when test="${not empty loginCeoUser}">
              <!-- 기업 회원 -->
                <li><a class="nav-link scrollto" href="${contextPath}/app/ceomember/detail">기업페이지</a></li>
                <li><a class="nav-link scrollto" href="${contextPath}/app/ceomember/logout">로그아웃</a></li>
              </c:when>
              
              <c:when test="${not empty loginAdmin}">
              <!-- 관리자 -->
                <li><a class="nav-link scrollto" href="${contextPath}/app/admin/detail">관리자페이지</a></li>
                <li><a class="nav-link scrollto" href="${contextPath}/app/admin/logout">로그아웃</a></li>
              </c:when>
           </c:choose>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->
     
      <div class="social-links">
        <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
        <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
        <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
        <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
      </div>

    </div>
  </header><!-- End Header -->