<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 상세</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>

<style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
  }
   #head {
    font-family: '굴림체';
    text-align: center;
     background-color: blanchedalmond;
     text-align: center;
     color: black;
     margin-top: 10px;
     font-size: 50px;
  }  

</style>
</head>

<body>
   <form id="head">💬 문의글 상세</form>
   <hr>
   <form action='updateform'>
     <span id='no' name='no'>(${askBoard.askNo})</span><br>
     <span>제목ㅣ</span> <span>${askBoard.askTitle}</span><br>
     <span>내용ㅣ</span> <span>${askBoard.askContent}</span><br>
     <span>작성자ㅣ</span> <span>${askBoard.askMemberWriter.perNickname}</span><br>
     <span>작성일ㅣ</span> <span>${askBoard.askRegisteredDate}</span><br>
</form>
</body>
</html>  
     
     
     
     