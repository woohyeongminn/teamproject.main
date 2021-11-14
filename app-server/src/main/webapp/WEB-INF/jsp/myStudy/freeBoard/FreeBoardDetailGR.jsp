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
label {
	margin-right: 5px;
	text-align: right;
	display: inline-block;
	width: 60px;
}
</style>
</head>
<body>
		<span>번호ㅣ</span> <span>${freeBoard.freeBoardNo}</span><br> <span>제목ㅣ</span>
		<span>${freeBoard.freeBoardTitle}</span><br> <span>작성자ㅣ</span> <span>${freeBoard.freeBoardWriter.perNickname}</span><br>
		<span>내용ㅣ</span> <span>${freeBoard.freeBoardContent}</span><br> <span>작성일ㅣ</span>
		<span>${freeBoard.freeBoardRegisteredDate}</span><br> <span>조회수ㅣ</span>
		<span>${freeBoard.freeBoardViewcount}</span><br>
		<div class="input-group mb-3">
			<span>댓글&nbsp;</span>
			<form action='/ogong/freeboard/comment/add' method='post'>
				<input type='hidden' name='studyno' value='${freeBoard.studyNo}'/>
				<input type='hidden' name='freeboardno' value='${freeBoard.freeBoardNo}'/>
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
		      <a href='comment/updateform?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}&commentno=${comment.commentNo}'>수정</a>
		    </button>
        <button>
		      <a href='comment/delete?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}&commentno=${comment.commentNo}'>삭제</a>
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
