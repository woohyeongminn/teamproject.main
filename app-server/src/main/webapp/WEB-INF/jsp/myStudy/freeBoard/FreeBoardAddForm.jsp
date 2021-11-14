<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
  font-size:14px;
}
.all-content {
  width: 100%;
  margin: 0 auto;
	display: flex;
	justify-content: center;
}
#button {
  text-align: center;
}
</style>
<script type="text/javascript">
  function checkValue() {

  var form = document.freeBoardInfo;

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
    <label for='f-title'>제목</label>
    <input id='f-title' type='text' name='title' class="form-control">
  </div>

  <div class="mb-3 row">
    <label for='f-content'>내용</label>
    <textarea id='f-content' type='text' name='content' class="form-control" rows="3"></textarea>
  </div>

  <div class="mb-3 row">
    <label for='f-filepath'>파일</label>
    <input id='f-filepath' type='file' name='filepath' class="form-control">
  </div>

   <%-- <label for='f-filepath' class='form-label'>파일</label>
   <input id='f-filepath' type='file' name='filepath' /><br>
   
   <c:if test="${empty filepath}">
     <input id='f-filepath' type='hidden' name='filepath' value="freeboard_80x80.jpg"/><br>
   </c:if> --%>
  </div>
	<div id='button'>
    <button class="btn btn-dark">등록</button>
  </div>
	</form>
</div>
</body>
</html>
