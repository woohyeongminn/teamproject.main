<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>상세 | 참여 대기중 스터디</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

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
  <form action='canclestudy'>
    <input type='hidden' name='subjectNo' value='${waitingStudy.subjectNo}'>
    <span>번호ㅣ</span> <span>${waitingStudy.studyNo}</span><br>
    <span>북마크ㅣ</span> <span>${waitingStudy.countBookMember}</span><br>
    <span>제목ㅣ</span> <span>${waitingStudy.studyTitle}</span><br>
    <span>조장ㅣ</span> <span>${waitingStudy.owner.perNickname}</span><br>
    <span>분야ㅣ</span> <span>${waitingStudy.subjectName}</span><br>
    <span>지역ㅣ</span> <span>${waitingStudy.area}</span><br>
    <span>인원수ㅣ</span> <span>${waitingStudy.countMember}</span><br>
    <span>최대 인원수ㅣ</span> <span>${waitingStudy.numberOfPeple}</span><br>
    <span>대면 상태ㅣ</span> <span>${waitingStudy.faceName}</span><br>
    <span>소개글ㅣ</span> <span>${waitingStudy.introduction}</span><br>
    <span>활동 점수ㅣ</span> <span>${waitingStudy.point}</span><br>
    <button>
      <a href='waitinglist?perNo=${member.perNo}'>참여취소</a>
    </button>
    <button>
      <a href='waitinglist?perNo=${member.perNo}'>목록</a>
    </button>

</body>
</html>










