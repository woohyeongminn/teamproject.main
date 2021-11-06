<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
   <title>공지게시판</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
   <!-- 아이콘 -->
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
  <style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  legend {
    text-align: center;
  }
  input {
  border : white;
  outline-color : lightgray;
  }
  .c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  text-align: center;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
}
  </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<fieldset>
<section>
      <div class="c-top">
        🔔 공지게시글 변경
      </div>
<table class="table table-responsive">
<thead>
<tr>
<th>번호</th>
<th>제목</th>
<th>내용</th>
<th>파일</th>
<th>등록일</th>
</tr>
</thead>
  <td><form action='update'><span>(${notice.adminNotiNo})</span></td>
  <td><input id='f-title' type='text' name='title' value='${notice.adminNotiTitle}'></td>
  <td><input id='f-content' type='text' name='content' value='${notice.adminNotiContent}'></td>
  <td><input id='f-filepath' type='file' name='filepath' value='${notice.adminNotiFile}'></td>
  <td><span id='f-registeredDate'>${notice.adminNotiRegisteredDate}</span></td>
</table>
</fieldset>
<input type='hidden' name='no' value='${notice.adminNotiNo}'>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="변경">변경</button>
</form>
</div>
</section>
</body>
</html>