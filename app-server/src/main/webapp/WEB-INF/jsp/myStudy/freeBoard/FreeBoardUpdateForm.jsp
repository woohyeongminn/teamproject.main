<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper">

      <jsp:include page="../MyStudyDetailNav.jsp"/>

      <%-- main-panel --%>
      <div class="main-panel">
        <div class="content-wrapper">
        
          <%-- row sub-items --%>
          <div class="row">
            <div class="all-content">
						  <form action='update' method='post' name='freeBoardInfo' onsubmit="return checkValue()">
						  <input type='hidden' name='studyNo' value='${freeBoard.studyNo}'>
						  <input type='hidden' name='freeBoardNo' value='${freeBoard.freeBoardNo}'>
						  <div id='content'>
						      <br>
						  <div class="mb-3 row">
						    <label for='f-freeBoardTitle'>제목</label>
						    <input id='f-freeBoardTitle' type='text' name='freeBoardTitle' class="form-control" required
			           oninvalid="this.setCustomValidity('제목을 입력하세요.')"
			           oninput="this.setCustomValidity('')">
						  </div>
						
						  <div class="mb-3 row">
						    <label for='f-freeBoardContent'>내용</label>
						    <textarea id='f-freeBoardContent' type='text' name='freeBoardContent' class="form-control" rows="3" required
			           oninvalid="this.setCustomValidity('제목을 입력하세요.')"
			           oninput="this.setCustomValidity('')"></textarea>
						  </div>
						
						  <!-- <div class="mb-3 row">
						    <label for='f-freeBoardFile'>파일</label>
						    <input id='f-freeBoardFile' type='file' name='freeBoardFile' class="form-control">
						  </div> -->
						
						   <!-- <label for='f-freeBoardFile' class='form-label'>파일</label>
						   <input id='f-freeBoardFile' type='file' name='freeBoardFile' /><br>
						   
						   <c:if test="${empty freeBoardFile}">
						     <input id='f-freeBoardFile' type='hidden' name='freeBoardFile' value="freeboard_80x80.jpg"/><br>
						   </c:if> -->
						  </div>
						  <div id='button'>
						    <button class="btn btn-dark">수정</button>
						  </div>
						  </form>
						</div>
          </div> <%-- end row sub-items --%>
        
        
       </div> <%-- end content-wrapper --%>
     </div> <%-- main-panel --%>
     
     </div>
   </div>

<!-- <script type="text/javascript">
  function checkValue() {

  var form = document.freeBoardInfo;

  if (!form.freeBoardTitle.value) {
    alert("제목을 입력하세요.");
    return false;
  }

  if (!form.freeBoardContent.value) {
    alert("내용을 입력하세요.");
    return false;
  }

  /* if (!form.filepath.value) {
    alert("파일을 선택하세요.");
    return false;
  } */
}
</script> -->
</body>
      