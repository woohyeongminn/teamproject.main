<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 | 개인/title>
<style>
  * {
  font-size: 14px;
  }
  
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
  }
  
  .btn {
    line-height: 14px;
  }
  </style>
</head>
<body>
<p>회원가입이 완료되었습니다.</p>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class = "btn btn-outline-dark" type="submit" value="등록" formaction="peradd">
     <a href="form">로그인</a>
     </button>
     </div>
</body>
</html>