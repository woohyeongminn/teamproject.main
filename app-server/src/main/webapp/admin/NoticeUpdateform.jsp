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
  #f-photo-image {
  height: 300px;
  }
  label {
    margin-right: 5px;
    text-align: center;
/*     display: inline; */
    width: 60px;
  }
  label#content {
    position: relative;
    bottom: 400px;
  }
  legend {
    text-align: center;
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
  .c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  text-align: center;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
  }
  th {
  text-align: center;
  }
  button[type=submit] {
    margin-bottom: 300px;
    margin-right: 10px;
  }
  .all-content {
    width: 100%;
    max-width: 900px;
    margin: 0 auto;
    font-size: 14px;
  }
  thead, tbody, tfoot, tr, td, th {
    border-color: black;
    border-style: solid;
    border-width: 0;
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
<br>
<div class="all-content">
<table class="table table-responsive">
<thead>

<form id="notice-update" action='update' method='post' enctype="multipart/form-data">
<tr>
  <th scope="col"></th>
  <th scope="col">🔔 공지게시판 게시글 변경</th>
  <th scope="col">&emsp;&emsp;&emsp;</th>
</tr>

<tr>
<th scope="row"><label for='f-title'>제목</label></th>
<td><input id='f-title' type='text' name='title' placeholder='${notice.adminNotiTitle}' autocomplete='off'></td>
<td></td>
</tr>

<tr>
<th scope="row"><label for='f-content' id="content">내용</label></th>
<td><textarea id='f-content' type='text' name='content' rows="20" wrap="virtual" autocomplete='off' placeholder='${notice.adminNotiContent}'></textarea></td>
<td></td>
</tr>

<tr>
<th scope="row"><label for='f-filepath'>파일</label></th>
<td><img id="f-photo-image" src="../upload/notice/${notice.adminNotiFile}">
<input id='f-filepath' type='file' name='filepath'></td>
<td></td>
</tr>

</thead>
</table>

<input type='hidden' name='no' value='${notice.adminNotiNo}'>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="변경">변경</button>
</form>
</div>
</div>
</fieldset>
</section>

<script>
document.querySelector("#notice-update").onsubmit = () => {
  if (document.querySelector("#f-title").value == "" ||
        document.querySelector("#f-content").value == "") {
    /* window.alert("필수 입력 항목이 비어 있습니다.") */
    Swal.fire('제목이나 내용을 입력해 주세요.')
    return false; // 일단 서버에 보내지 마
  }
};
</script>

 <jsp:include page="../footer.jsp"/>
</body>
</html>