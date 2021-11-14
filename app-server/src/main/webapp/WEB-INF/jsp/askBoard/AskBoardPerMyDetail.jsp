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
  <div id="mian">
      <span id='no' name='no'>${myAskBoard.askNo}</span><br>
      <label for="f-title" class="form-label">제목</label>
      <input type="text" class="form-control" name="title" value="${myAskBoard.askTitle}" readonly></input>
    
      <label for="f-content" class="form-label">내용</label>
      <textarea class="form-control" id="f-content" name="content" placeholder="${myAskBoard.askContent}" class="form-control" cols="50" rows="3" readonly></textarea>
 
      <span>작성자ㅣ${myAskBoard.askMemberWriter.perNickname}</span><br>
      <span>등록일ㅣ${myAskBoard.askRegisteredDate}</span><br>
      <c:choose>
	      <c:when test="${empty myAskBoard.reply}">
	      <br><label>답변📔 </label>
      	<textarea class="form-control" id="f-content" name="content" 
	      placeholder="등록된 답변이 없습니다." class="form-control" cols="50" rows="2" readonly></textarea>
	      </c:when>
	      <c:otherwise>
	      <br><label>답변📖  등록일ㅣ${myAskBoard.reply.replyRegisteredDate} </label>
        <textarea class="form-control" id="f-content" name="content" 
        placeholder="${myAskBoard.reply.replyContent}"
         class="form-control" cols="50" rows="2" readonly></textarea>
	      </c:otherwise>
     </c:choose>
  </div>   
      
     <br><div class="d-grid gap-2 d-md-flex justify-content-md-end">
	     <a href='perupdateform?askNo=${myAskBoard.askNo}' type="button" class="btn btn-outline-dark">수정하기</a>
	     <a href='perdelete?askNo=${myAskBoard.askNo}' type="button" class = "btn btn-outline-dark">삭제하기</a>
	    <a href='permylist' type="button" class="btn btn-outline-dark" >뒤로가기</a>
     </div>
     
     