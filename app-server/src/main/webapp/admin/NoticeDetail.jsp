<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
   <title>공지게시판</title>
</head>
<body>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
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
<button type="button" class="btn btn-outline-dark"><a href='list'>목록</a></button>
<button type="button" class="btn btn-outline-dark"><a href='Updateform?no=${adminNotice.adminNotiNo}'>변경</a></button>
<button type="button" class="btn btn-outline-dark"><a href='delete?no=${adminNotice.adminNotiNo}'>삭제</a></button></body>
</div>
</body>
</html>