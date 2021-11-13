<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<br>
	<h3>📖 | To-Do List 변경 </h3>
	<hr>
    <table class="table table-responsive">
    <thead>
      <tr>
      <br>
		<th>상태</th>
    <th>번호</th>
		<th>내용</th>
		<th>비고</th>
		<th>작성자</th>
		<th>날짜</th>
      </tr>
      </thead>
      <td><form action='update'><select name="progress_no">
    <c:choose>
    <c:when test="${todo.todoStatus == 1}">
          <option value="${todo.todoStatus}">진행 중</option>
          <option value="2" name="progress_no" >완료</option>
          </c:when>
          <c:otherwise>
          <option value="${todo.todoStatus}">완료</option>
          <option value="1" name="progress_no" >진행 중</option>
          </c:otherwise>
    </c:choose>
      </select></td>
  <input type='hidden' name='studyno' value='${study.studyNo}'>
  <input type='hidden' name='todono' value='${todo.todoNo}'>
  <input type='hidden' name='perno' value='${member.perNo}'>
  <td><span>(${todo.todoNo})</span></td>
  <td><input id='f-content' type='text' name='content' value='${todo.todoContent}'></td>
  <td><input id='f-note' type='text' name='note' value='${todo.todoRemark}'></td>
  <td><input id='f-writer' type='text' name='writer' value='${todo.todoWriter.perNickname}' readonly></td>
  <td><span id='f-create_dt'>${todo.todoDate}</span></td>
  </table>
  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button class="btn btn-outline-dark">변경</button>
      <button class="btn btn-outline-dark"><a href="list?perno=${member.perNo}&studyno=${study.studyNo}">목록</a></button>
  </div>
</form>
</body>
</html>

