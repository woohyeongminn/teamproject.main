<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
   <title>로그인</title>
</head>
<body>
<h1>[로그인 결과]</h1>

<p>${loginMember.perNickname}님 환영합니다! 🖐</p><br><button><a href='detail?no=${loginMember.perNo}'><p>마이페이지</p></a></button></body>
</html>
