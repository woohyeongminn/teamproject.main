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
.c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  text-align: center;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
}
.table > :not(caption) > * > * {
    text-align: center;
}
  input {
  border : white;
  outline-color : lightgray;
  margin-left: 80px;
  }
  button[type=submit] {
    margin-right: 10px;
    margin-bottom: 300px;
  }
  </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<fieldset>
<section>
      <div class="c-top">
        🔔 공지게시글 등록
      </div>
<table class="table table-responsive">
<thead>
<tr>
<th><label for='f-title'>제목</label></th>
<th><label for='f-content'>내용</label></th>
<th><label for='f-filepath'>파일</label></th>
</tr>
</thead>
  <form action='add'></td>
  <td></label><input id='f-title' type='text' name='title'></td>
  <td><input id='f-content' type='text' name='content'></td>
  <td><input id='f-filepath' type='file' name='filepath'></td>
</table>
</fieldset>

<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="등록">등록</button>
</form>
</div>
</section>
 <jsp:include page="../footer.jsp"/>
</body>
</html>