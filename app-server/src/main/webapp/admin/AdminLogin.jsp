<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
  legend {
  text-align: center;
  }
  p {
  text-align-last: center;
  }
  div {
  margin-right: 10px;
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: center;
  }
  button[type=button] {
    background-color: beige;
    color: black;
  }
  button[type=button]:hover {
    background-color: gold;
    color: black;
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
<body>
<br>
<legend><b> 🖐 관리자 로그인 </b></legend><br>
<hr>
<p>👑 '${admin.masterNickname}'님 환영합니다! 🖐</p>
<br>
<br>
<div class="btn-group dropend">
  <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
    💌 고객센터 관리
  </button>
  <ul class="dropdown-menu">
    <li><a class="dropdown-item" href="/ogong/adminNotice/list">공지게시판</a></li>
    <li><a class="dropdown-item" href="/ogong/adminNotice/list">문의게시판</a></li>
  </ul>
</div>

<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="로그아웃" ><a href='logout'>로그아웃</a></button> 
<button type="submit" class="btn btn-outline-dark" value="마이페이지" ><a href='detail?no=${admin.masterNo}'>마이페이지</a></button> 
</div>
 </body>
</html>