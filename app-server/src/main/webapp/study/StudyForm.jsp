<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>등록 | 📖 스터디 찾기</title>
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
	<h1>스터디 등록</h1>
	<form action='add'>
        <label for='f-studyTitle'>제목</label> <input id='f-studyTitle' type='text' name='studyTitle'><br>
        <label for='f-subjectNo'>분야</label> <input id='f-subjectNo' type='text' name='subjectNo'><br>
        <label for='f-area'>지역</label> <input id='f-area' type='text' name='area'><br>
        <label for='f-numberOfPeple'>최대 인원수</label> <input id='f-numberOfPeple' type='text' name='numberOfPeple'><br>
        <label for='f-faceNo'>대면/비대면</label> <input id='f-faceNo' type='text' name='faceNo'><br>
        <label for='f-introduction'>소개글</label> <input id='f-introduction' type='text' name='introduction'><br>
    <input type='hidden' name='no' value='1'>
    <button>등록</button><br>
	</form>
</body>
</html>
