<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
   <title>문의 게시판(관리자)</title>
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
  legend {
  font-family: '굴림체';
    text-align: center;
     background-color: blanchedalmond;
     text-align: center;
     color: black;
     margin-top: 10px;
     font-size: 50px;
  }

  div {
  font-family: '굴림체';
  margin-right: 10px;
  }
  a {
  
  color : black;
  text-decoration : auto;
  }
  a:hover {
  color : lightgray;
  }
  #head {
  font-family: '굴림체';
  background-color: beige;
  color: black;
  font-size: 25px;
  }
  div {
    font-family: '굴림체';
  }
  td {
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
<fieldset>
<br>
<legend ><b> 💬 문의게시글 목록(관리자 전용) </b></legend><br>
<hr>
<table class="table table-responsive">
<thead>
<tr id="head">
<th>No.</th>
<th>제목</th>
<th>작성자</th>
<th>조회수</th>
<th>등록일</th>
<th></th>
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
        <td> 📕 </td>
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









