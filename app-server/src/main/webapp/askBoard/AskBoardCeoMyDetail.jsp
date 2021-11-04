<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 상세</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
  }
  
   form {
    font-family: '굴림체';
    text-align: center;
     background-color: blanchedalmond;
     text-align: center;
     color: black;
     margin-top: 10px;
     font-size: 50px;
  }
  
  span {
    text-align: center;
    font-size: 15px;
  }
  
   .btn {
   border-radius: 4px;
   background-color: blanchedalmond;
   color: black;
   font-size: 18px;
  }
  
  .btn:hover {
   background-color: beige;
   color: black;
  }
  
</style>
</head>

<body>
   <form> 💬 문의글 상세보기[사장 회원]</form>
   <hr>
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
</body>

   <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-primary me-md-2" type="submit" value="수정" >
     <a href='ceoupdateform?askNo=${myAskBoard.askNo}'>문의글수정</a>
     </button>
   </div> 
   
   <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-primary me-md-2" type="submit" value="삭제" >
     <a href='ceodelete?askNo=${myAskBoard.askNo}&ceoNo=${myAskBoard.askCeoWriter.ceoNo}'>문의글삭제</a>
     </button>
   </div>

</html>  