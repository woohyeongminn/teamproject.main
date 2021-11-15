<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>
#main {
  max-width: 500px;
  background-color: white;
  border-radius: 15px;
  border: 2px solid rgb(110, 110, 110);
  text-align: center;
  padding-bottom: 14px;
  padding-left: 10px;
}
</style>
<br>
<div id="main">
<b> ${perMember.perName}님의 이메일은 [${findemail}] 입니다.</b>
</div>
    <hr><br><div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <a href="${contextPath}/app/login" >로그인 |</a>
     <a href="${contextPath}/app/login" >비밀번호 찾기</a>
   </div>