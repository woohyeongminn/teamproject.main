<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>상세 | 🌟 내 북마크</title>
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
	<h1>내 북마크 상세</h1>
	<label for='f-studyNo'>번호</label>
	<input id='f-studyNo' type='text' name='studyNo'
		value='${study.studyNo}' readonly>
	<br>
	<label for='f-countBookMember'>북마크</label>
	<input id='f-countBookMember' type='text' name='countBookMember'
		value='${study.countBookMember}' readonly>
	<br>
	<label for='f-studyTitle'>제목</label>
	<input id='f-studyTitle' type='text' name='studyTitle'
		value='${study.studyTitle}'>
	<br>
	<label for='f-owner'>조장</label>
	<input id='f-owner' type='text' name='owner'
		value='${owner.perNickname}'>
	<br>
	<label for='f-subjectName'>분야</label>
	<input id='f-subjectName' type='text' name='subjectName'
		value='${study.subjectName}'>
	<br>
	<label for='f-area'>지역</label>
	<input id='f-area' type='text' name='area' value='${study.area}'>
	<br>
	<label for='f-countMember'>인원수</label>
	<input id='f-countMember' type='text' name='countMember'
		value='${study.countBookMember}' readonly>
	<br>
	<label for='f-numberOfPeple'>최대 인원수</label>
	<input id='f-numberOfPeple' type='text' name='numberOfPeple'
		value='${study.numberOfPeple}' readonly>
	<br>
	<label for='f-faceName'>대면/비대면</label>
	<input id='f-faceName' type='text' name='faceName'
		value='${study.faceName}'>
	<br>
	<label for='f-introduction'>소개글</label>
	<input id='f-introduction' type='text' name='introduction'
		value='${study.introduction}'>
	<br>
	<a href='delete?no=${study.studyNo}'>[삭제]</a>
	<a href='list'>[목록]</a>
	<br>

</body>
</html>
