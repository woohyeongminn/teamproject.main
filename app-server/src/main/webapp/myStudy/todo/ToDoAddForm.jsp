<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
   <title>📖 | To-Do List</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <style>
  input {
  border : white;
  outline-color : lightgray;
  }
  button {
  margin-right: 10px;
  }
  a {
  color : black;
  text-decoration : auto;
  }
  a:hover {
  color : white;
  }
  </style>
</head>
<body>
<jsp:include page="../../header.jsp"/>
<fieldset>
<br>
<h3>📖 | To-Do List 등록 </h3>
<hr>
<form action='add'>
<table class="table table-responsive">
<thead>
<tr>
<th><label for='f-progress_no'>상태</label></th>
<th><label for='f-content'>내용</label></th>
<th><label for='f-note'>비고</label></th>
<th><label for='f-writer'>작성자</label></th>
</tr>
</thead>
  <input type='hidden' name='studyno' value='${studyno}'>
  <input type='hidden' name='perno' value='${member.perNo}'>
  <td>진행 중</td>
  <td><input id='f-content' type='text' name='content'></td>
  <td><input id='f-note' type='text' name='note'></td>
  <td><input id='f-writer' type='text' name='writer' value='${member.perNickname}' readonly></td>
</table>
</fieldset>

<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="등록">등록</button>
</form>
</div>
</body>
</html>