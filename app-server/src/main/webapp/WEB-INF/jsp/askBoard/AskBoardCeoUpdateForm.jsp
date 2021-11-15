<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <style>
  * {
  font-size: 14px;
  }
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
  }
  
  .btn {
    line-height: 14px;
  }
</style>
</head>
<body>
  <form id="all" action="ceoupdate" method="post">
    <span>(${ceoAskBoard.askNo})</span><br>
    
      <label for="f-title" class="form-label">*제목</label>
      <input id="title" type="text" class="form-control" name="askTitle" placeholder="${ceoAskBoard.askTitle}"></input>
    
      <label for="f-content" class="form-label">*내용</label>
      <textarea id="content" class="form-control" id="f-content" name="askContent" placeholder="${ceoAskBoard.askContent}" class="form-control" cols="10" rows="3"></textarea>
      <br>
    
    <span id='f-registeredDate'>${ceoAskBoard.askRegisteredDate}</span><br>
    <input type ='hidden' name='askNo' value='${ceoAskBoard.askNo}'>
  
     <div class="d-grid gap-2 d-md-flex justify-content-md-end">
    <button class ="btn btn-outline-dark"  type="submit" value="수정">수정하기</button>
    <a type="button"  class ="btn btn-outline-dark" href="ceomylist">취소하기</a>
    </div>
  </form>
  
  <script>  
  document.querySelector("#all").onsubmit = () => {
  if (document.querySelector("#title").value == "") {
    alert("**제목을 입력해주세요.")
    return false;
  } else if (document.querySelector("#content").value == "") {
    alert("**내용을 입력해주세요.")
    return false;
  }
};
</script>
