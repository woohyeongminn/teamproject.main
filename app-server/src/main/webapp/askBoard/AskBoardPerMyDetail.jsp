<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 문의글 상세(개인회원)</title>
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
   <b> 💬 문의글 상세</b>
   <hr>
   <form action='updateform'>
     <span id='no' name='no'>(${myAskBoard.askNo})</span><br>
     <span>제목ㅣ</span> <span>${myAskBoard.askTitle}</span><br>
     <span>내용ㅣ</span> <span>${myAskBoard.askContent}</span><br>
     <span>작성자ㅣ</span> <span>${myAskBoard.askMemberWriter.perNickname}</span><br>
     <span>작성일ㅣ</span> <span>${myAskBoard.askRegisteredDate}</span><br>
</body>
</html>  
     
     
     
     