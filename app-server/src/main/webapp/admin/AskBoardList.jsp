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
<c:forEach items="${adminAskBoardList}" var="askBoard">
<tr>
  <div>
      <td>${askBoard.askNo}.</td>
    </div>
  <td><a href='askboarddetail?askNo=${askBoard.askNo}'>${askBoard.askTitle}</a></td>
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
</c:forEach>
</tbody>
</table>
</fieldset>
<c:if test="${empty adminAskBoardList}">
       등록된 문의글이 없습니다.
</c:if>
</body>
</html>









