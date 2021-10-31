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
<legend><b> 🔔 공지게시글 등록 </b></legend><br>
<hr>
<table class="table table-responsive">
<thead>
<tr>
<th><label for='f-no'>번호</label></th>
<th><label for='f-title'>제목</label></th>
<th><label for='f-content'>내용</label></th>
<th><label for='f-filepath'>파일</label></th>
<th><label for='f-registeredDate'>등록일</label></th>
</tr>
</thead>
  <form action='add'></td>
  <td></td>
  <td></label><input id='f-title' type='text' name='title'></td>
  <td><input id='f-content' type='text' name='content'></td>
  <td><input id='f-filepath' type='file' name='filepath'></td>
  <td></td>
</table>
</fieldset>

<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="등록">등록</button>
</form>
</div>
</body>
</html>