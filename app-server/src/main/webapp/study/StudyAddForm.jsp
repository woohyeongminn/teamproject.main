<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>등록 | 스터디 찾기</title>
<style>
* {
    font-size:16px;
}
.container {
	display: flex;
	justify-content: center;
}
.form-control {
  height:25px;
}
</style>
<script type="text/javascript">
  function checkValue() {

  var form = document.studyInfo;

  if (!form.studytitle.value == "") {
    alert("제목을 입력하세요.");
    return false;
  }

  if (!form.subjectno.value) {
    alert("분야를 입력하세요.");
    return false;
  }

  if (!form.area.value) {
    alert("지역을 입력하세요.");
    return false;
  }

  if (!form.numberofpeple.value) {
    alert("최대 인원수를 입력하세요.");
    return false;
  }

  if (!form.faceno.value) {
    alert("대면 상태를 입력하세요.");
    return false;
  }

  if (!form.introduction.value == "") {
    alert("소개글을 입력하세요.");
    return false;
  }
</script>
</head>
<body>
<div class="container">
  <form action='add' method='post' name='studyInfo' onsubmit="return checkValue()">
    <input type='hidden' name='perno' value='${member.perNo}'>
  <br>
  
  <div class="mb-3 row">
    <label for='f-studytitle' class="col-sm-2 col-form-label">제목</label>
    <div class="col-sm-6">
    <input id='f-studytitle' type='text' name='studytitle' class="form-control">
    </div>
  </div>

  <div class="mb-3 row">
    <label for='f-subjectno' class="col-sm-2 col-form-label">분야</label>
    <div class="col-sm-6">
    </div>
  </div>
  <div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadio" id="flexRadioDefault2" checked>
  <label class="form-check-label" for="flexRadioDefault2">
    어학
  </label><br>
  <input class="form-check-input" type="radio" name="flexRadio" id="flexRadioDefault1">
  <label class="form-check-label" for="flexRadioDefault1">
    자격증
  </label><br>
  <input class="form-check-input" type="radio" name="flexRadio" id="flexRadioDefault1">
  <label class="form-check-label" for="flexRadioDefault1">
    취업
  </label><br>
  <input class="form-check-input" type="radio" name="flexRadio" id="flexRadioDefault1">
  <label class="form-check-label" for="flexRadioDefault1">
    IT
  </label><br>
  <input class="form-check-input" type="radio" name="flexRadio" id="flexRadioDefault1">
  <label class="form-check-label" for="flexRadioDefault1">
    예체능
  </label><br>
  <input class="form-check-input" type="radio" name="flexRadio" id="flexRadioDefault1">
  <label class="form-check-label" for="flexRadioDefault1">
    기타
  </label><br>
  </div>

  <div class="mb-3 row">
  <label for='f-area' class="col-sm-2 col-form-label">지역</label>
  <div class="col-sm-6">
    <input id='f-area' type='text' name='area' class="form-control">
  </div>
  </div>

  <div class="mb-3 row">
  <label for='f-numberofpeple' class="col-sm-2 col-form-label">최대 인원수</label>
  <div class="col-sm-6">
    <input id='f-numberofpeple' type='text' name='numberofpeple' class="form-control">
  </div>
  </div>

  <div class="mb-3 row">
  <label for='f-faceno' class="col-sm-2 col-form-label">대면 상태</label>
  <div class="col-sm-6">
  </div>
  </div>
  <div class="form-check">
  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
  <label class="form-check-label" for="flexRadioDefault2">
    대면
  </label><br>
  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
  <label class="form-check-label" for="flexRadioDefault1">
    비대면
  </label><br>
  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
  <label class="form-check-label" for="flexRadioDefault1">
    대면/비대면
  </label><br>
  </div>

    <div class="mb-3 row">
    <label for='f-introduction' class="col-sm-2 col-form-label">소개글</label>
    <div class="col-sm-10">
      <input id='f-introduction' type='introduction' name='introduction' class="form-control">
    </div>
    </div>
    
    <br>
	<button class="btn btn-primary btn-sm">등록</button><br>
  </form>
  </div>
</body>
</html>
