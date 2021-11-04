<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>상세 | 스터디 찾기</title>
<link rel="stylesheet" type="text/css" href="../header.css">
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
<jsp:include page="../header.jsp"/>
	<h1>📖 스터디 상세</h1>
	<form action='updateform'>
	  <input type='hidden' name='subjectNo' value='${study.subjectNo}'>
		<span>번호ㅣ</span> <span>${study.studyNo}</span><br>
    <span>북마크ㅣ</span> <span>${study.countBookMember}</span><br>
    <span>제목ㅣ</span> <span>${study.studyTitle}</span><br>
    <span>조장ㅣ</span> <span>${study.owner.perNickname}</span><br>
    <span>분야ㅣ</span> <span>${study.subjectName}</span><br>
    <span>지역ㅣ</span> <span>${study.area}</span><br>
    <span>인원수ㅣ</span> <span>${study.countMember}</span><br>
    <span>최대 인원수ㅣ</span> <span>${study.numberOfPeple}</span><br>
    <span>대면 상태ㅣ</span> <span>${study.faceName}</span><br>
    <span>소개글ㅣ</span> <span>${study.introduction}</span><br>
    <span>활동 점수ㅣ</span> <span>${study.point}</span><br>
		
		<c:if test="${study.owner.perNo eq member.perNo}">
    <button  type="submit" value="수정" formaction="updateform">
      <a href='updateform?perno=${member.perNo}&studyno=${study.studyNo}'>수정</a>
    </button>
    <button>
       <a href='delete?perno=${member.perNo}&studyno=${study.studyNo}'>삭제</a>
    </button>
    </c:if>
		<!-- <c:if test="${study.owner.perNickname eq member.perNickname}">
		<button id="writer">
      <a href='update?studyno=${study.studyNo}'>수정</a>
    </button>
		<button id="writer">
			<a href='delete?studyno=${study.studyNo}'>삭제</a>
		</button>
		<style>
		#writer {
		visibility: visible;
		}
		</style>
		</c:if> -->
		
		<button>
			<a href='join?perno=${member.perNo}&studyno=${study.studyNo}'>참여 신청</a>
		</button>
		<button>
			<a href='../bookmark/add?perno=${member.perNo}&studyno=${study.studyNo}'>북마크 추가</a>
		</button>
		<button>
			<a href='list?perno=${member.perNo}'>목록</a>
		</button>
		<br>
	</form>
</body>
</html>
