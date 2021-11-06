<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 게시글 등록(사장 회원)</title>

  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>

<style>

  label { 
    font-family: '굴림체';
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
    font-size: 20px;
  }
  
  div {
    font-family: '굴림체';
    font-size: 20px;
  }

  h5 {
      background-color: blanchedalmond;
      text-align: center;
      color: black;
      margin-top: 10px;
      font-size: 50px;
  }

.btn {
   border-radius: 4px;
   background-color: blanchedalmond;
   color: black;
   font-size: 18px;
  }
  .btn:hover {
   background-color: beige;
   color: black;
  }

</style>
</head>

<body>
<jsp:include page="../header.jsp"/>
<br>
  <h5>💬문의글 등록</h5>
  <form >
  
    <label for='f-status'>공개</label>
    <select id="f-status" name='status' >
    <option value='1' name='status'>공개</option>
    <option value='2' name='status'>비공개</option>
    </select><br> 
    
    <div id="passwordRow">
      <label for='f-tempPW' size='100px'>🔑비밀번호</label>
      <input id='f-tempPW' type='password' name='tempPW' placeholder="4자리"><br>
    </div>
    
    <br>
    <div class="mb-3">
      <label for="f-title" class="form-label">제목</label>
      <input type="text" class="form-control" name="title" placeholder="제목을 입력하세요"></input>
    </div>
    
    <div class="mb-3">
      <label for="f-content" class="form-label">내용</label>
      <input class="form-control" id="f-content" name="content" placeholder="내용을 입력하세요"></input>
    </div>

    <input type ='hidden' name='writer' value="${loginCeoUser.ceoNo}"></input>      
  
    <br><br>
    
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-outline-primary me-md-2 btn-small" 
	     type="submit" value="등록" formaction="ceoadd">
	     등록하기
     </button>
    </div>
  </form>
 
 <script>
var fStatus = document.querySelector("#f-status");
var passwordRow = document.querySelector("#passwordRow");

passwordRow.style["display"] = "none";

fStatus.addEventListener("input", function() {
  if (fStatus.value == "2") {
    passwordRow.style["display"] = "";
  } else {
     passwordRow.style["display"] = "none";
  }
});
</script>
</body>
</html>





