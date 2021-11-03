<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
   <title>공지게시판</title>
<body>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
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
  a {
  color : black;
  text-decoration : blink;
  }
  a:hover {
  color : white;
  }
  </style>
</head>
<body>
<!-- <h1>  ▶ 공지 상세 </h1> -->
<fieldset>
<br>
<legend><b> 🔔 공지게시글 상세 </b></legend><br>
<hr>
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
<tbody>
  <td>( ${adminNotice.adminNotiNo} )</td>
  <td>${adminNotice.adminNotiTitle}</td>
  <td>${adminNotice.adminNotiContent}</td>
  <td>${adminNotice.adminNotiFile}</td>
  <td>${adminNotice.adminNotiRegisteredDate}</td>
</tbody>
</table>
</fieldset>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="목록"><a href='list'>목록</a></button>
<button type="submit" class="btn btn-outline-dark" value="변경"><a href='Updateform?no=${adminNotice.adminNotiNo}'>변경</a></button>
<button type="submit" class="btn btn-outline-dark" value="삭제"><a href='delete?no=${adminNotice.adminNotiNo}'>삭제</a></button></body>
</div>
</body>
</html>