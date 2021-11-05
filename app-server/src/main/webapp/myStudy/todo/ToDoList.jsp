<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>📖 | To-Do List</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
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
	<h3>📖 | To-Do List 목록 </h3>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>상태</th>
				<th>번호</th>
				<th>내용</th>
				<th>비고</th>
				<th>날짜</th>
			</tr>
			</thead>
		<tbody>
		<%-- <input type='hidden' name='studyno' value='${study.studyNo}'> --%>
		<c:if test='${empty countProgressing}'>
    [등록된 To-Do List가 없습니다.]
    </c:if>
    <c:if test='${not empty countProgressing}'>
    [등록된 To-Do List]
    </c:if>
		
			<c:forEach items="${countProgressing}" var="todo">
				<tr>
					<td>${todo.todocomplete}</td>
					<td>${todo.todoNo}</td>
					<td><a href="detail?todono=${todo.todoNo}&studyno=${study.studyNo}">${todo.todoContent}</a></td>
					<td>${todo.todoRemark}</td>
					<td>${todo.todoDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button class="btn btn-outline-dark"><a href="todo/add?todono=${todo.todoNo}&perno=${member.perNo}&studyno=${study.studyNo}">등록</a></button>
<%--       <button class="btn btn-outline-dark"><a href="todo/detail?todono=${todo.todoNo}&perno=${member.perNo}&studyno=${study.studyNo}">상세</a></button> --%>  </div>
</body>
</html>
