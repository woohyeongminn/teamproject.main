<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
	* {
	  font-size:14px;
	}
</style>
</head>
<body>
<c:if test='${not empty freeBoardList}'>
<c:forEach items="${freeBoardList}" var="freeBoard">
<div class="card mb-3" style="max-width: 540px;">
  <div class="row g-0">
    <div class="col-md-4">
      <img src="${contextPath}/upload/upload/freeboard/"${freeboard.freeboardImgs[0].name} class="img-fluid rounded-start" alt="...">
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title">
          <a href="detail?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}">${freeBoard.freeBoardTitle}</a>
        </h5>
        <p class="card-text">${freeBoard.freeBoardContent}</p>
        <p class="card-text"><small class="text-muted">${freeBoard.freeBoardWriter.perNickname}</small></p>
      </div>
    </div>
  </div>
</div>
</c:forEach>
</c:if>
<c:if test="${empty freeBoardList}">
  등록된 게시글이 없습니다.
</c:if>
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
