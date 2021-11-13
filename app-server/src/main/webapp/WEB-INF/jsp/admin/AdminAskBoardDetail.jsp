<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 | 문의 게시글</title>
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
     <span id='no' name='no'>(${adminAskBoard.askNo})</span><br>
     <span>제목ㅣ</span> <span>${adminAskBoard.askTitle}</span><br>
     <span>내용ㅣ</span> <span>${adminAskBoard.askContent}</span><br>
     <span>작성자ㅣ</span> <span>${adminAskBoard.askMemberWriter.perNickname}</span><br>
     <span>작성일ㅣ</span> <span>${adminAskBoard.askRegisteredDate}</span><br>
</body>

<c:choose>
<c:when test="${empty adminAskBoard.reply}">
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
	   <button class = "btn btn-outline-dark" type="submit" value="답변" formaction="reply">
	    <a href='../askboard/replyaddform?askNo=${adminAskBoard.askNo}'>답변등록</a>
	   </button>
	   <button class = "btn btn-outline-dark" type="submit" value="삭제" formaction="delete">
	   <a href='askboarddelete?askNo=${adminAskBoard.askNo}'>문의글삭제</a>
	   </button>  
  </div>
</c:when>
<c:otherwise>
  <span>답변 내용ㅣ</span>
   <span>${adminAskBoard.reply.replyTitle}
    ㅣ ${adminAskBoard.reply.replyContent}
    ㅣ${adminAskBoard.reply.replyRegisteredDate}</span><br>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
	   <button class = "btn btn-outline-dark" type="submit" value="삭제" formaction="delete">
	   <a href='askboarddelete?askNo=${adminAskBoard.askNo}'>문의글삭제</a>
	   </button>  
  </div>
</c:otherwise>
</c:choose>
  
</html>  
     
     
     
     