<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  
  a {
  color: black;
  }
  
  .btn {
   border-radius: 4px;
   background-color: blanchedalmond;
   font-size: 18px;
  }
  
  .btn:hover {
   background-color: beige;
   color: black;
  }
  
</style>

</head>

<body>
<jsp:include page="../header.jsp"/>
   <form> 💬 문의글 상세보기[개인 회원]</form>
    
     <span id='no' name='no'>(${myAskBoard.askNo})</span><br>
     <span>제목ㅣ</span> <span>${myAskBoard.askTitle}</span><br>
     <span>내용ㅣ</span> <span>${myAskBoard.askContent}</span><br>
     <span>작성자ㅣ</span> <span>${myAskBoard.askMemberWriter.perNickname}</span><br>
     <span>작성일ㅣ</span> <span>${myAskBoard.askRegisteredDate}</span><br>
     
     <c:choose>
	      <c:when test="${empty myAskBoard.reply}">
		      <span>
		        등록된 답변이 없습니다.
		      </span><br>  
           <div class="d-grid gap-2 d-md-flex ">
             <button class="btn btn-primary me-md-2" type="submit" value="등록" formaction="add">
             <a href='perupdateform?askNo=${myAskBoard.askNo}'>문의글수정</a>
             </button>
           </div>		          
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
    
   <div class="d-grid gap-2 d-md-flex ">
     <button class="btn btn-primary me-md-2" type="submit" value="등록" formaction="add">
     <a href='perdelete?askNo=${myAskBoard.askNo}&perNo=${myAskBoard.askMemberWriter.perNo}'>문의글삭제</a>
     </button>
   </div> 
   

</html>  
     
     
     