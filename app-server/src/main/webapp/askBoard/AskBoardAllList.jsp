<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
   <title>목록 | 문의 게시글</title>
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
<fieldset>
<br>
<hr>
<table class="table table-responsive text-center">
	<thead>
	  <tr id="head">
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>조회수</th>
		<th>등록일</th>
		<th>답변📔/📖</th>
	</tr>   
</thead>
<tbody>
<c:forEach items="${askBoardList}" var="askBoard">
<c:choose>
<c:when test="${askBoard.askStatus == 1}">
 <tr>
	  <div>
      <td>${askBoard.askNo}.</td>
    </div>
	  <td><a href='detail?askNo=${askBoard.askNo}'>${askBoard.askTitle}</a></td>
	  <td>${askBoard.askMemberWriter.perNickname}</td>
	  <td>${askBoard.askVeiwCount}</td>
	  <td>${askBoard.askRegisteredDate}</td>
	     <c:choose>
        <c:when test="${empty askBoard.reply}">
        <td> 📔 </td>
        </c:when>
        <c:otherwise>
        <td> 📖 </td>
        </c:otherwise>
      </c:choose>
 </tr>
</c:when>
<c:otherwise>
	<tr>
		<div>
      <td>${askBoard.askNo}.</td>
    </div>
		<td></td><td>🔒 비밀글입니다.</td><td></td><td></td><td></td>
	</tr>
</c:otherwise>
</c:choose>
</c:forEach>

</tbody>
</table>
</fieldset>
<c:if test="${empty askBoardList}">
   <form id="empty">등록된 문의글이 없습니다.</form><br>
</c:if>
<c:if test="${not empty loginUser}">
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
   <button class ="btn btn-outline-dark" type="submit" value="등록" formaction="add">
   <a href='peraddform'>등록하기</a>
   </button>
</div>
</c:if>
<c:if test="${not empty loginCeoUser}">
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
   <button class = "btn btn-outline-dark" type="submit" value="등록" formaction="add">
   <a href='ceoaddform'>등록하기</a>
   </button>
</div>
</c:if>
</body>
</html>









