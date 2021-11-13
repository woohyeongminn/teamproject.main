<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
     <span id='no' name='no'>(${myAskBoard.askNo})</span><br>
     <span>제목ㅣ</span> <span>${myAskBoard.askTitle}</span><br>
     <span>내용ㅣ</span> <span>${myAskBoard.askContent}</span><br>
     <span>작성자ㅣ</span> <span>${myAskBoard.askCeoWriter.ceoNickname}</span><br>
     <span>작성일ㅣ</span> <span>${myAskBoard.askRegisteredDate}</span><br>

     <c:choose>
        <c:when test="${empty myAskBoard.reply}">
          <span>
            등록된 답변이 없습니다.
          </span><br>      
        </c:when>
        <c:otherwise>
         <span>관리자ㅣ</span>
         <span>
         ${myAskBoard.reply.replyTitle} | ${myAskBoard.reply.replyContent} | 
         ${myAskBoard.reply.replyRegisteredDate}
         </span><br>
        </c:otherwise>
     </c:choose>
      <br>
     <button class = "btn btn-outline-dark" type="submit" value="수정" formaction="add">
     <a href='ceoupdateform?askNo=${myAskBoard.askNo}'>문의글수정</a>
     </button>
     <button class = "btn btn-outline-dark" type="submit" value="등록" formaction="add">
     <a href='ceodelete?askNo=${myAskBoard.askNo}'>문의글삭제</a>
     </button>
     <button class = "btn btn-outline-dark" type="submit" value="목록" formaction="list">
     <a href='mylist'>내 목록보기</a>
     </button> 
