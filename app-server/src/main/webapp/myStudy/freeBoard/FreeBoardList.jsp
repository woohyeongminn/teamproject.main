<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>목록 | 자유 게시판</title>
<style>
	*{
	  font-size:14px;
	}
</style>
</head>
<body>
	<h1>🪧 자유 게시판 목록</h1>
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
					<td><a
						href="detail?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}">${freeBoard.freeBoardTitle}</a></td>
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
		<a href='addform?studyno=${studyno}'>등록</a>
	</button>
</body>
</html>
