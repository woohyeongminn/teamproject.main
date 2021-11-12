<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>상세 | 스터디 찾기</title>
<style>
*{
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
		<span>번호 </span> <span>${study.studyNo}</span><br>
    <span>북마크 </span> <span>${study.countBookMember}</span><br>
    <span>제목 </span> <span>${study.studyTitle}</span><br>
    <span>조장 </span> <span>${study.owner.perNickname}</span><br>
    <span>분야 </span> <span>${study.subjectName}</span><br>
    <span>지역 </span> <span>${study.area}</span><br>
    <span>인원수 </span> <span>${study.countMember}</span><br>
    <span>최대 인원수 </span> <span>${study.numberOfPeple}</span><br>
    <span>대면 상태 </span> <span>${study.faceName}</span><br>
    <span>소개글 </span> <span>${study.introduction}</span><br>
    <span>활동 점수 </span> <span>${study.point}</span><br>
		<c:choose>
		<c:when test="${study.owner.perNo eq loginUser.perNo}">
    <button>
      <a href='updateform?studyno=${study.studyNo}'>수정</a>
    </button>
    <button>
       <a href='delete?studyno=${study.studyNo}'>삭제</a>
    </button>
    </c:when>
    <c:when test="${study.owner.perNo ne member.perNo}">
    <button>
      <a href='join?studyno=${study.studyNo}'>참여 신청</a>
    </button>
    </c:when>
    </c:choose>
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
			<a href='../bookmark/add?studyno=${study.studyNo}'>북마크 추가</a>
		</button>
		<button>
			<a href='list'>목록</a>
		</button>
		<br>
</body>
</html>
