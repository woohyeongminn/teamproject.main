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
  p, #info, #createDt{
  text-align:right;
  }
  textarea {
  color: white;
  }
  a {
  color: black;
  }  
</style>
  <div id="mian">
      <b>제목</b>
      <input type="text" class="form-control" name="title" value="${myAskBoard.askTitle}" readonly></input>
    
      <br><b>내용</b>
      <textarea class="form-control" id="f-content" name="content" placeholder="${myAskBoard.askContent}" class="form-control" cols="50" rows="3" readonly></textarea>
 
       <div id="info">
        <b>작성자 :</b><span>${myAskBoard.askCeoWriter.ceoNickname}</span><br>
        <b>등록일 :</b><span>${myAskBoard.askRegisteredDate}</span>
       </div>
      <c:choose>
	      <c:when test="${empty myAskBoard.reply}">
	      <label>답변📔 </label>
      	<textarea class="form-control" id="f-content" name="content" 
	      placeholder="등록된 답변이 없습니다." class="form-control" cols="50" rows="2" readonly></textarea>
	      </c:when>
	      <c:otherwise>
	      <label>답변📖  등록일ㅣ${myAskBoard.reply.replyRegisteredDate} </label>
        <textarea class="form-control" id="f-content" name="content" 
        placeholder="${myAskBoard.reply.replyContent}"
         class="form-control" cols="50" rows="2" readonly></textarea>
	      </c:otherwise>
     </c:choose>
  </div>   
 
<br><div class="d-grid gap-2 d-md-flex justify-content-md-end">
  <c:choose>
   <c:when test="${empty myAskBoard.reply}">
     <a href='perupdateform?askNo=${myAskBoard.askNo}' type="button" class="btn btn-outline-dark">수정하기</a>
   </c:when>
   <c:otherwise>
     <a id="notEmptyReply" type="button" class = "btn btn-outline-dark" onclick="notEmptyReply(this);">수정하기</a>        
   </c:otherwise>
  </c:choose>
  <a href='perdelete?askNo=${myAskBoard.askNo}' type="button" class = "btn btn-outline-dark">문의글삭제</a>
  <a href='alllist' type="button" class="btn btn-outline-dark" >뒤로 가기</a>
</div>

<script>
function notEmptyReply(obj) { 
    alert("답변이 등록된 글은 수정할 수 없습니다.")
    }
</script>
     
     