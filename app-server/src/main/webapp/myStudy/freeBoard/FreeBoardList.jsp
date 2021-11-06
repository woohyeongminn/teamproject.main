<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>목록 | 스터디 자유게시판</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../../header.jsp"/>
  <h1>🪧 자유게시판 목록</h1>
    <table class="table table-hover">
    <thead>
      <tr>
        <th>No.</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
      </tr>
      </thread>
    <tbody>
      <c:forEach items="${freeBoardList}" var="freeBoard">
        <tr>
          <td>${freeBoard.freeBoardNo}</td>
          <td><a href="freeboarddetail?freeNo=${freeBoard.freeBoardNo}&studyNo=${freeBoard.studyNo}&perNo=${member.perNo}">${freeBoard.freeBoardTitle}</a></td>
          <td>${freeBoard.freeBoardWriter.perNickname}</td>
          <td>${freeBoard.freeBoardViewcount}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  <c:if test="${empty freeBoardList}">
    등록된 게시글이 없습니다.
  </c:if>
      <br>
      <button>
      <a href='freeboardaddform?studyNo=${studyNo}&perNo=${member.perNo}'>등록</a>
      </button>
</body>
</html>