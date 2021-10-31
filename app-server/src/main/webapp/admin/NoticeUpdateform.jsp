<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
   <title>공지게시판</title>
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
  input {
  border : white;
  outline-color : lightgray;
  }
  </style>
</head>
<body>
<fieldset>
<br>
<legend><b> 🔔 공지게시글 수정 </b></legend><br>
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
  <td><form action='update'><span>(${notice.adminNotiNo})</span></td>
  <td><input id='f-title' type='text' name='title' value='${notice.adminNotiTitle}'></td>
  <td><input id='f-content' type='text' name='content' value='${notice.adminNotiContent}'></td>
  <td><input id='f-filepath' type='text' name='filepath' value='${notice.adminNotiFile}'></td>
  <td><span id='f-registeredDate'>${notice.adminNotiRegisteredDate}</span></td>
</table>
</fieldset>
<input type='hidden' name='no' value='${notice.adminNotiNo}'>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="변경">변경</button>
</form>
</div>
</body>
</html>