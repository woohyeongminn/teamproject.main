<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>
 .main{
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    font-size:16px;
    text-align: center;
  } 
  
   a {
  text-decoration: none;
  color: black;
  }
  
</style>
<br>
<div class="main">
<b>'${ceoMember.ceoName}'님</b>
<b>'${ceoMember.ceoEmail}'로 임시 비밀번호가 발급되었습니다.</b>
<b>로그인 페이지로 이동합니다.</b>

    <!-- 
    <hr><br><div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <a href="${contextPath}/app/login" style="font-size: 14px" class="btn btn-outline-dark">로그인</a>
      -->
      
   </div>
</div>