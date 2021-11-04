<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 상세(관리자)</title>
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
   <b> 💬 문의글 상세[관리자 전용]</b>
   <hr>
   <form action='updateform'>
     <span id='no' name='no'>(${adminAskBoard.askNo})</span><br>
     <span>제목ㅣ</span> <span>${adminAskBoard.askTitle}</span><br>
     <span>내용ㅣ</span> <span>${adminAskBoard.askContent}</span><br>
     <span>작성자ㅣ</span> <span>${adminAskBoard.askMemberWriter.perNickname}</span><br>
     <span>작성일ㅣ</span> <span>${adminAskBoard.askRegisteredDate}</span><br>
</body>

<c:choose>
<c:when test="${empty adminAskBoard.reply}">
  <button type="submit" value="답변" formaction="reply">
    <a href='../askboard/replyaddform?askNo=${adminAskBoard.askNo}'>답변등록</a>
  </button>
</c:when>
<c:otherwise>
  <span>답변 내용ㅣ</span>
   <span>${adminAskBoard.reply.replyTitle}
    ㅣ ${adminAskBoard.reply.replyContent}
    ㅣ${adminAskBoard.reply.replyRegisteredDate}</span><br>
</c:otherwise>
</c:choose>
  
  <button type="submit" value="삭제" formaction="delete">
   <a href='askboarddelete?askNo=${adminAskBoard.askNo}'>문의글삭제</a>
  </button>  
</html>  
     
     
     
     