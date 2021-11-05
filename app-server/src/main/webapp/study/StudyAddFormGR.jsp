<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>등록 | 스터디 찾기</title>
<link rel="stylesheet" type="text/css" href="../header.css">
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
	<h1>📖 스터디 등록</h1>
	<form action='add'>
    <input type='hidden' name='perno' value='${member.perNo}'>
    <div class="mb-3">
		<label for="f-studyTitle" class="form-label">제목</label>
		<input type="text" class="form-control" id="exampleFormControlInput1">
	</div>
	<div class="mb-3">
		<label for="f-introduction" class="form-label">소개</label>
		<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
	</div>
	<label for="f-subjectNo" class="form-label">분야</label>
	<div class="form-check">
		<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
		<label class="form-check-label" for="f-subjectName">
		어학
		</label>
	</div>
	<div class="form-check">
		<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
		<label class="form-check-label" for="f-subjectName">
		자격증
		</label>
	</div>
	<div class="form-check">
		<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
		<label class="form-check-label" for="f-subjectName">
		취업
		</label>
	</div>
	<div class="form-check">
		<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
		<label class="form-check-label" for="f-subjectName">
		IT
		</label>
	</div>
	<div class="form-check">
		<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
		<label class="form-check-label" for="f-subjectName">
		예체능
		</label>
	</div>
	<div class="form-check">
		<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
		<label class="form-check-label" for="f-subjectName">
		기타
		</label>
	</div>
	<div class="mb-3">
		<label for="f-subjectNo" class="form-label">분야</label>
		<input type="text" class="form-control" id="exampleFormControlInput1">
	</div>
	<div class="form-check">
		<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
		<label class="form-check-label" for="f-subjectNo">
		대면
		</label>
	</div>
	<div class="form-check">
		<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
		<label class="form-check-label" for="f-subjectNo">
		비대면
		</label>
	</div>
	<div class="form-check">
		<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
		<label class="form-check-label" for="f-subjectNo">
		대면/비대면
		</label>
	</div>
	<div class="mb-3">
		<label for="f-faceNo" class="form-label">대면 상태</label>
		<input type="text" class="form-control" id="exampleFormControlInput1">
	</div>
	<div class="mb-3">
		<label for="f-area" class="form-label">지역</label>
		<input type="text" class="form-control" id="exampleFormControlInput1">
	</div>
	<div class="mb-3">
		<label for="f-numberOfPeple" class="form-label">최대 인원수</label>
		<input type="text" class="form-control" id="exampleFormControlInput1">
	</div>
	<label for='f-studyTitle'>제목</label> <input id='f-studyTitle' type='text' name='studytitle'><br>
        <p>
				[ Category ]<br>
				1. 어학<br>
				2. 자격증<br>
				3. 취업<br>
				4. IT<br>
				5. 예체능<br>
				6. 기타<br>
				</p>
        <label for='f-subjectNo'>분야</label> <input id='f-subjectNo' type='text' name='subjectno'><br>
        <label for='f-area'>지역</label> <input id='f-area' type='text' name='area'><br>
        <label for='f-numberOfPeple'>최대 인원수</label> <input id='f-numberOfPeple' type='text' name='numberofpeple'><br>
        <p>
				[ 대면 상태 ]<br>
				1. 대면<br>
				2. 비대면<br>
				3. 대면/비대면<br>
				</p>
        <label for='f-faceNo'>대면 상태</label> <input id='f-faceNo' type='text' name='faceno'><br>
        <label for='f-introduction'>소개글</label> <input id='f-introduction' type='text' name='introduction'><br>
    <button type="submit" value="등록">등록</button><br>
	</form>
</body>
</html>
