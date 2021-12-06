<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  * {
  font-size: 14px;
  }
  .all-content {
  width: 100%;
  max-width: 900px;
  margin: 0 auto;
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
  }  xt-align:right;
  
</style>
<div class="all-content">
<div id="mian">
  <br>
  <b>제목</b>
  <input type="text" class="form-control" name="title" value="${askBoard.askTitle}" readonly></input>
  <br>  
  <b>내용</b>
  <textarea class="form-control" id="f-content" name="content" placeholder="${askBoard.askContent}" class="form-control" cols="50" rows="3" readonly></textarea>
  <br>
  <div id="info">
    <b>작성자 :</b><span>${askBoard.askMemberWriter.perNickname}</span><br>
    <b>등록일 :</b><span>${askBoard.askRegisteredDate}</span>
  </div>
  <hr>
  <c:choose>
     <c:when test="${empty askBoard.reply}">
        <label>답변📔 </label>
        <textarea class="form-control" id="f-content" name="content" 
        placeholder="등록된 답변이 없습니다." class="form-control" cols="50" rows="2" readonly></textarea>
     </c:when>
     <c:otherwise>
        <label>답변📖  등록일ㅣ${askBoard.reply.replyRegisteredDate} </label>
        <textarea class="form-control" id="f-content" name="content" 
        placeholder="${askBoard.reply.replyContent}"
         class="form-control" cols="50" rows="2" readonly></textarea>
     </c:otherwise>
  </c:choose>
</div>
     
<c:choose>     
  <c:when test="${loginUser.perNo == askBoard.askMemberWriter.perNo}">
     <br>
     <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <c:choose>
        <c:when test="${empty askBoard.reply}">
          <a href='perupdateform?askNo=${askBoard.askNo}' type="button" class="btn btn-outline-dark">수정하기</a>
        </c:when>
        <c:otherwise>
          <a id="notEmptyReply" type="button" class = "btn btn-outline-dark" onclick="notEmptyReply(this);">수정하기</a>        
        </c:otherwise>
       </c:choose>
       <a href='perdelete?askNo=${askBoard.askNo}' type="button" class = "btn btn-outline-dark">삭제하기</a>
       <a href='alllist' type="button" class="btn btn-outline-dark" >뒤로 가기</a>
     </div>
  </c:when>
  <c:when test="${not empty loginUser && (loginUser.perNo != askBoard.askMemberWriter.perNo)}">
     <br>
     <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <a href='alllist' type="button" class="btn btn-outline-dark" >뒤로 가기</a>
     </div>
  </c:when>  
  <c:when test="${loginCeoUser.ceoNo == askBoard.askCeoWriter.ceoNo}">
     <br>
     <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <c:choose>
        <c:when test="${empty askBoard.reply}">
          <a href='ceoupdateform?askNo=${askBoard.askNo}' type="button" class="btn btn-outline-dark">수정하기</a>
        </c:when>
        <c:otherwise>
          <a id="notEmptyReply" type="button" class = "btn btn-outline-dark" onclick="notEmptyReply(this);">수정하기</a>        
        </c:otherwise>
       </c:choose>       
       <a href='ceodelete?askNo=${askBoard.askNo}' type="button" class = "btn btn-outline-dark">삭제하기</a>
       <a href='alllist' type="button" class="btn btn-outline-dark" >뒤로 가기</a>
     </div>
  </c:when>
  <c:when test="${not empty loginCeoUser && (loginCeoUser.ceoNo != askBoard.askCeoWriter.ceoNo)}">
     <br>
     <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <a href='alllist' type="button" class="btn btn-outline-dark" >뒤로 가기</a>
     </div>
  </c:when>
  <c:otherwise>
     <br>
     <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <a href='alllist' type="button" class="btn btn-outline-dark" >뒤로 가기</a>
     </div>
  </c:otherwise>
</c:choose>
</div>

<script>
function logout(obj) { 
    alert("로그인 해주세요.")
    }
function notEmptyReply(obj) { 
    alert("답변이 등록된 글은 수정할 수 없습니다.")
    }
</script>
     
     
     