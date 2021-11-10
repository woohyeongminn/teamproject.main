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

</head>

<body>
    
     <span id='no' name='no'>(${askBoard.askNo})</span><br>
     <span>제목ㅣ</span> <span>${askBoard.askTitle}</span><br>
     <span>내용ㅣ</span> <span>${askBoard.askContent}</span><br>
     <span>작성자ㅣ</span> <span>${askBoard.askMemberWriter.perNickname}</span><br>
     <span>작성일ㅣ</span> <span>${askBoard.askRegisteredDate}</span><br>
     
     <c:choose>
        <c:when test="${empty askBoard.reply}">
          <span>
            등록된 답변이 없습니다.
          </span><br>  
        </c:when>
        <c:otherwise>
         <span>관리자ㅣ</span>
         <span>
         ${askBoard.reply.replyTitle} | ${askBoard.reply.replyContent} | 
         ${askBoards.reply.replyRegisteredDate}
         </span><br>
        </c:otherwise>
     </c:choose>
</body>
</html>  
     
     
     
     