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
</style>
<br>
<div class="main">
<b>'${perMember.perName}' 님의 이메일은 [${findemail}] 입니다.</b>
    <hr><br><div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <a href="${contextPath}/app/login" style="font-size: 14px">로그인 |</a>
     <a href="${contextPath}/app/login" style="font-size: 14px">비밀번호 찾기</a>
   </div>
</div>