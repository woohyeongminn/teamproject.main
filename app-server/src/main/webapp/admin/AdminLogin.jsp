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
  margin-left: 100px;
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: center;
  float: left;
  }
  button[type=button] {
    border-radius: 10px;
    background-color: beige;
    color: black;
  }
  button[type=button]:hover {
    background-color: yellow;
    color: black;
  }
  button[type=button1] {
    border-radius: 10px;
    border-color: lightgray;
    background-color: beige;
    color: black;
  }
  button[type=button1]:hover {
    background-color: yellow;
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
<div class="btn-group">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
    👑 관리자 페이지
  </button>
  <div class="dropdown-menu" role="menu" style="border-color: white;">
    <button class="dromdown-item" type="button1">
      <a href='logout' style="color: black;">🖐 로그아웃</a></button><br>
    <button class="dromdown-item" type="button1">
      <a href='detail?no=${admin.masterNo}' style="color: black;">🙂 마이페이지</a></button>
  </div>
</div>

</div>
<div class="btn-group">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
    📁 회원 관리
  </button>
  <div class="dropdown-menu" role="menu" style="border-color: white;">
    <button class="dromdown-item" type="button1">
      <a href="/ogong/admin/permemberlist" style="color: black;">🎓 개인 회원</a></button><br>
    <button class="dromdown-item" type="button1">
      <a href="/ogong/admin/ceomember/list" style="color: black;">👔 기업 회원</a></button>
  </div>
</div>

</div>
<div class="btn-group">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
    📖 스터디 관리
  </button>
  <div class="dropdown-menu" role="menu" style="border-color: white;">
    <button class="dromdown-item" type="button1">
      <a href="study/list" style="color: black;">📚 스터디 목록</a></button><br>
    <!-- <button class="dromdown-item" type="button1">
      <a href="/study/list" style="color: black;">📔 스터디 삭제</a></button> -->
  </div>
</div>

<div class="btn-group">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
    🏘 장소 관리(미완)
  </button>
  <div class="dropdown-menu" role="menu" style="border-color: white;">
    <button class="dromdown-item" type="button1">
      <a href="/ogong/admin/cafeList" style="color: black;">📝 장소 목록</a></button><br>
    <button class="dromdown-item" type="button1">
      <a href="/ogong/cafe/list" style="color: black;">🔖 장소 리뷰</a></button>
  </div>
</div>

<div class="btn-group">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
    💌 고객센터 관리
  </button>
  <div class="dropdown-menu" role="menu" style="border-color: white;">
    <button class="dromdown-item" type="button1">
      <a href="/ogong/adminNotice/list" style="color: black;">📢 공지사항</a></button><br>
    <button class="dromdown-item" type="button1">
      <a href="/ogong/admin/askboardlist" style="color: black;">💬 문의사항</a></button>
  </div>
</div>
</body>
</html>
<!--
<div class="btn-group dropend">
  <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
    👑 관리자 페이지
  </button>
  <div class="dropdown-menu" role="menu" style="border-color: white;">
    <button class="dromdown-item" type="button1">
      <a href='logout' style="color: black;">🖐 로그아웃</a></button><br>
    <button class="dromdown-item" type="button1">
      <a href='detail?no=${admin.masterNo}' style="color: black;">🙂 마이페이지</a></button>
  </div>
</div>
<br>
<br>

<div class="btn-group dropend">
  <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
    💌 고객센터 관리
  </button>
  <div class="dropdown-menu" role="menu" style="border-color: white;">
    <button class="dromdown-item" type="button1">
      <a href="/ogong/adminNotice/list" style="color: black;">📢 공지사항</a></button><br>
    <button class="dromdown-item" type="button1">
      <a href="/ogong/askboardlist/list" style="color: black;">💬 문의사항</a></button>
  </div>
</div>
-->