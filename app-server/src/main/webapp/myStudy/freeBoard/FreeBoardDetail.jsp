<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>상세 | 자유 게시판</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<style>
label {
	margin-right: 5px;
	text-align: right;
	display: inline-block;
	width: 60px;
}
</style>
</head>
<body>
	<jsp:include page="../../header.jsp" />
	<h1>🪧 자유 게시판 상세</h1>
		<span>번호ㅣ</span> <span>${freeBoard.freeBoardNo}</span><br> <span>제목ㅣ</span>
		<span>${freeBoard.freeBoardTitle}</span><br> <span>작성자ㅣ</span> <span>${freeBoard.freeBoardWriter.perNickname}</span><br>
		<span>내용ㅣ</span> <span>${freeBoard.freeBoardContent}</span><br> <span>작성일ㅣ</span>
		<span>${freeBoard.freeBoardRegisteredDate}</span><br> <span>조회수ㅣ</span>
		<span>${freeBoard.freeBoardViewcount}</span><br>
		<div class="input-group mb-3">
			<span>댓글&nbsp;</span>
			<form action='/ogong/freeboard/comment/add'>
				<input type='hidden' name='freeboardno' value='${freeBoard.freeBoardNo}'/>
				<input type='hidden' name='studyno' value='${freeBoard.studyNo}'/>
				<input id='f-commentText' type="text" name='commenttext' class="form-control"
					     placeholder="내용을 입력하세요." aria-describedby="button-addon2"/>
				<button class="btn btn-outline-secondary" type="submit" id="button-addon2">등록</button>
		  </form>
		</div>
			<c:if test="${empty commentList}">등록된 댓글이 없습니다.</c:if><br>
			<c:forEach items="${commentList}" var="comment">
			<div class="card">
      <div class="card-body">
        <span>내용ㅣ${comment.commentText}</span>
        <br>
        <span>작성자ㅣ${comment.commentWriter.perNickname}</span>
        <br>
        <span>등록일ㅣ${comment.commentRegisteredDate}</span><br>
        <button>
		      <a href='list?studyno=${freeBoard.studyNo}'>수정</a>
		    </button>
        <button>
		      <a href='list?studyno=${freeBoard.studyNo}'>삭제</a>
		    </button>
        </div>
        </div>
      </c:forEach>
        <br>
		<button>
			<a href='list?studyno=${freeBoard.studyNo}'>목록</a>
		</button>
		<button>
			<a
				href='updateform?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}'>수정</a>
		</button>
		<button>
			<a
				href='delete?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}'>삭제</a>
		</button>
	</form>
</body>
</html>
