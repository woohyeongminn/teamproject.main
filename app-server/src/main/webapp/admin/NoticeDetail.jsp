<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <title>공지게시판</title>
<body>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
   <!-- 아이콘 -->
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
  <style>
  #f-photo-image {
  height: 300px;
  }
  label {
    margin-right: 5px;
    text-align: center;
    /* display: inline; */
    width: 60px;
  }
  legend {
    text-align: center;
  }
  a {
  color : black;
  text-decoration : blink;
  }
  a:hover {
  color : white;
  }
  button[type=submit] {
    margin-bottom: 300px;
    margin-right: 10px;
  }
  input {
  width: 700px;
  font-size: 14px;
  text-align: center;
  border : white;
  outline-color : lightgray;
  font-weight: 400;
  }
  #f-content {
  text-align: justify;
  margin: 0;
  word-wrap: break-word;
  width: 700px;
  font-size: 14px;
  min-height: 370px;
  letter-spacing: 0;
  border: 0px solid white;
  outline-color: lightgray;
  border-radius: 1px;
  }
  #f-filepath {
  margin: 0;
  width: 300px;
  font-size: 14px;
  min-height: 370px;
  }
  .c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  text-align: center;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
  }
  .all-content {
    width: 100%;
    max-width: 900px;
    margin: 0 auto;
    font-size: 14px;
  }
  th#date {
    font: small-caption;
  }
  thead, tbody, tfoot, tr, td, th {
    border-color: black;
    border-style: solid;
    border-width: 0;
  }
  tr#blockbox {
    vertical-align: top;
  }
/*   tr#first {
    border-bottom: black;
    border-style: double;
    border-top-color: white;
    border-left-color: white;
    border-right-color: white;
  }
  th#date {
    font: caption;
    font-size: small;
  }
  th#second {
    border: lightgray;
    border-style: solid;
    border-left: white;
    border-top-color: white;
    border-bottom-width: 0;
    font-size: 14px;
  }
  th#third {
    border: lightgray;
    border-style: solid;
    border-left: white;
    border-top-color: white;
    border-bottom-width: 1px;
    font-size: 14px;
  } */
  
  </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<fieldset>
<section>
<div class="c-top">
        🔔 공지게시글 상세
      </div>
<br>
<div class="all-content">
<table class="table table-responsive text-center">
<thead>

<tr id="first">
  <th scope="col"></th>
  <th scope="col">🔔 공지게시판 게시글 상세</th>
  <th scope="col" id="date">${adminNotice.adminNotiRegisteredDate}</th>
</tr>

<tr>
<th scope="row"><label for='f-title'>제목</label></th>
<td><input id='f-title' type='text' name='title' value="${adminNotice.adminNotiTitle}" readonly></td>
<td></td>
</tr>

<tr id="blockbox">
<th scope="row" id="second"><label for='f-content' id="content">내용</label></th>
<td><textarea id='f-content' type='text' name='content' rows="20" wrap="virtual" readonly>${adminNotice.adminNotiContent}</textarea></td>
<td></td>
</tr>

<tr id="blockbox">
<th scope="row" id="third"><label for='f-filepath'>파일</label></th>
<td><img id="f-photo-image" src="../upload/notice/${adminNotice.adminNotiFile}"></td>
<td></td>
</tr>

</thead>
</table>


<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="목록"><a href='list'>목록</a></button>
<c:if test="${not empty loginAdmin}">
<button type="submit" class="btn btn-outline-dark" value="변경"><a href='Updateform?no=${adminNotice.adminNotiNo}'>변경</a></button>
<button type="submit" class="btn btn-outline-dark" value="삭제"><a href='delete?no=${adminNotice.adminNotiNo}'>삭제</a></button></body>
</c:if>

</div>
</fieldset>
</section>

 <jsp:include page="../footer.jsp"/>
</body>
</html>