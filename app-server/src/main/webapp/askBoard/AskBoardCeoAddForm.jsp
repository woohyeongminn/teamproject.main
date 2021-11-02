<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 게시글 등록(사장 회원)</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  legend {
  text-align: center;
  }
</style>
</head>

<body>
  <h5>💬 문의글 등록[사장 회원]</h5>
  <form action='ceoadd'>
    <label for='f-title' class='form-label' size='100px'>제목</label>
    <input id='f-title' type='text' name='title' placeholder="제목"><br>
    
    <label for='f-content' class='form-label' size='100px'>내용</label>
    <input id='f-content' type='text' name='content' placeholder="내용"><br>

    <input type ='hidden' name='writer' value='${ceoMember.ceoNo}'>       
      
     <span>
     1. 공개 / 2. 비공개<br>
     </span> 
    <label for='f-status' calss= form-label' size='100px'>문의글 상태</label>
    <input id='f-status' type='number' name='status' pattern="/d*" placeholder="문의글 상태"><br>

    <label for='f-tempPW' class='form-label' size='100px'>🔑문의글 비밀번호(4가지)</label>
    <input id='f-tel' type='number' name='tempPW' pattern="/d*" placeholder="문의글 비밀번호"><br>
    
    <button type="submit" class="btn btn-primary">
      등록하기
    </button>
   </form>
 </body>
</html>





