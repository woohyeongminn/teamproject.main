<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
    font-size:16px;
}
.all-content {
	display: flex;
	justify-content: center;
}
.form-control {
  height:23px;
}
</style>
<script type="text/javascript">
  function checkValue() {

  var form = document.studyInfo;

  if (!form.title.value) {
    alert("제목을 입력하세요.");
    return false;
  }

  if (!form.content.value) {
    alert("내용을 입력하세요.");
    return false;
  }

  if (!form.filepath.value) {
    alert("파일을 선택하세요.");
    return false;
  }
}
</script>
</head>
<body>
<div class="all-content">
  <form action='add' method='post' name='freeBoardInfo' onsubmit="return checkValue()">
	<input type='hidden' name='studyno' value='${studyno}'>
	<div id='content'>
      <br>
    <div class="mb-3 row">
    <label for='f-title' class="col-sm-2 col-form-label">제목</label>
    <div class="col-sm-6">
    <input id='f-title' type='text' name='title' class="form-control">
    </div>
  </div>

  <div class="mb-3 row">
    <label for='f-content' class="col-sm-2 col-form-label">내용</label>
    <div class="col-sm-6">
    <input id='f-content' type='text' name='content' class="form-control">
    </div>
  </div>

  <div class="mb-3 row">
    <label for='f-filepath' class="col-sm-2 col-form-label">파일</label>
    <div class="col-sm-6">
    <input id='f-filepath' type='file' name='filepath' class="form-control">
    </div>
  </div>

  <%-- <label for='f-filepath' class='form-label'>파일</label>
	    <input id='f-filepath' type='file' name='filepath' /><br>
	    
	    <c:if test="${empty filepath}">
	      <input id='f-filepath' type='hidden' name='filepath' value="freeboard_80x80.jpg"/><br>
	    </c:if> --%>
    </div>
    <br>
	<button class="btn btn-primary btn-sm">등록</button><br>
</form>
  </div>
</body>
</html>
