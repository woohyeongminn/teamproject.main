<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
   <title>문의 게시판</title>
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
<jsp:include page="../header.jsp"/>
<fieldset>
<br>
<legend ><b> 💬 문의게시글 목록 </b></legend><br>
<hr>
<table class="table table-responsive">
<thead>
<tr id="head">
<th>No.</th>
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
       등록된 문의글이 없습니다.
</c:if>

    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-primary me-md-2" type="submit" value="등록" formaction="add">
     <a href='../index.jsp'>등록하기</a>
     </button>
   </div>

</body>
</html>









