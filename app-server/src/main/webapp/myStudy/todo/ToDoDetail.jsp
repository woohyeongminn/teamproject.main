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
	<h3>📖 | To-Do List 상세 </h3>
	<hr>
	  <%-- <input type='hidden' name='studyno' value='${study.studyNo}'> --%>
	  <c:if test='${empty todo}'>
    [등록된 To-Do List가 없습니다.]
    </c:if>
    <thead>
      <tr>
      <br>
    <span>상태ㅣ</span> 
    <c:choose>
    <c:when test="${todo.todoStatus == 1}">
    진행 중
    </c:when>
    <c:otherwise>
    완료
    </c:otherwise>
    </c:choose>
    <br>
    <%-- <span>${todo.todocomplete}</span><br> --%>
    <span>번호ㅣ</span> <span>${todo.todoNo}</span><br>
    <span>내용ㅣ</span> <span>${todo.todoContent}</span><br>
    <span>비고ㅣ</span> <span>${todo.todoRemark}</span><br>
    <span>날짜ㅣ</span> <span>${todo.todoDate}</span><br>
      </tr>
      </thead>
    <tbody>
    <%-- <input type='hidden' name='studyno' value='${study.studyNo}'> --%>
    </tbody>
  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button class="btn btn-outline-dark"><a href="list?todono=${todo.todoNo}&perno=${member.perNo}&studyno=${study.studyNo}">목록</a></button>
      <button class="btn btn-outline-dark"><a href="updateform?todono=${todo.todoNo}&perno=${member.perNo}&studyno=${study.studyNo}">수정</a></button>
      <button class="btn btn-outline-dark"><a href="delete?todono=${todo.todoNo}&perno=${member.perNo}&studyno=${study.studyNo}">삭제</a></button>
  </div>
</body>
</html>

