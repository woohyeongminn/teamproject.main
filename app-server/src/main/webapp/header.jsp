<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
  <style>
    ol, ul, li {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: top;
}

body {
  line-height: 1.5;
}

ul {
  padding: 0;
}

ul li {
  list-style-type: none;
}

a {
  text-decoration: none;
  color: black;
}

/*header 시작*/
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 34px;
  border-bottom:  1px solid rgba(0,0,0,.125);
}

header a {
  text-decoration: none;
  color: black;
}

.header_logo {
  font-size: 20px;
  text-align: center;
}

.header_logo a {
  display: inline-block;
  width: 180px;
  height: 60px;
  margin: 0 auto;
  line-height: 60px;
}

.header_logo a img {
  width: 100%;
  vertical-align: middle;
}

.header_menu {
  display: flex;
}

.header_menu li {
  font-size: 14px;
  padding: 8px 34px;
}

.header_menu li a {
  padding: 4px;
  
}
.header_menu li a:hover {
  color: darkgrey;
  box-shadow: inset 0 -2px darkgrey;
  line-height: 20px;
}

.header_login {
 display: flex;
 box-sizing: border-box;
 border-left: 2px solid darkgrey;
}

.header_login li {
  font-size: 12px;
  padding: 2px 8px;
}

@media screen and (max-width: 768px) {
  header {
    flex-direction: column;
    align-self: start;
    padding: 5px 0;
  }

  .header_logo {
    margin: 0;
    width: 100%;
    text-align: center;
    padding-bottom: 5px;
    border-bottom: 1px solid black;
  }
  
  .header_menu {
    flex-direction: column;
    align-items: center;
    width: 100%;
  }

  .header_menu li {
    width: 100%;
    text-align: center;
  }

  .header_login {
    justify-content: center;
    width: 100%;
  }

  .header_login li {
    text-align: center;
    margin: 4px;
  }

  .header_login li a {
    padding: 10px;
  }
}
  </style>
</head>
<body>
<%@include file="memberSession.jsp" %>
<header>
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
<%--   <c:choose>
     <c:when test="${not empty loginPerUser || empty loginCeoUser}">
       <li><a href="/ogong/member/detail">마이페이지</a></li>
       <li><a href="/ogong//member/logout">로그아웃</a></li>
     </c:when>
   
     <c:when test="${not empty loginCeoUser || empty loginPerUser}">
       <li><a href="/ogong/ceomember/detail">기업페이지</a></li>
       <li><a href="/ogong/ceomember/logout">로그아웃</a></li>
     </c:when>
   
      <!-- <c:otherwise> -->
      <c:when test="${empty loginPerUser || empty loginCeoUser}">
        <li><a href="/ogong/signup">회원가입</a></li>
       <li><a href="/ogong/login">로그인</a></li>
     </c:when>  
  </c:choose> --%>
  
     <c:choose>
       <c:when test="${empty loginUser}">
         <li><a href="/ogong/signup">회원가입</a></li>
         <li><a href="/ogong/login">로그인</a></li>
       </c:when>
       <c:otherwise>
         <li><a href="/ogong/member/detail">마이페이지</a></li>
         <li><a href="/ogong//member/logout">로그아웃</a></li>
       </c:otherwise>
    </c:choose>
 
 <c:choose>
    <c:when test="${empty loginCeoUser}">
      <li><a href="/ogong/signup">회원가입</a></li>
      <li><a href="/ogong/login">로그인</a></li>
    </c:when>
    <c:otherwise>
      <li><a href="/ogong/ceomember/detail">기업페이지</a></li>
      <li><a href="/ogong/ceomember/logout">로그아웃</a></li>
    </c:otherwise>
 </c:choose>
 
  <c:choose> 
    <c:when test="${empty loginAdmin}">
      <li><a href="/ogong/signup">회원가입</a></li>
      <li><a href="/ogong/login">로그인</a></li>
    </c:when>
    <c:otherwise>
      <li><a href="/ogong/admin/detail">관리자페이지</a></li>
      <li><a href="/ogong/admin/logout">로그아웃</a></li>
    </c:otherwise>
   </c:choose> 

</ul>
</header>
</body>
</html>