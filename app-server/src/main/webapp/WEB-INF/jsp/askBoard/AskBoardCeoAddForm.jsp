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
  a {
  color: black;
  }  
</style>
<br>
  <form id="all" action="ceoadd" method="post">
    <label for='f-status'>공개</label>
    <select id="f-status" name='askStatus' >
      <option value='1' name='askStatus'>공개</option>
      <option value='2' name='askStatus'>비공개</option>
    </select><br> 
    
    <div id="passwordRow">
      <label for='f-tempPW' size='100px'>🔏비밀번호</label>
      <input id='f-tempPW' type='password' name='password' placeholder="4자리"></input>
    </div><br>
  
      <br>
      <label for="f-title" class="form-label">제목</label>
      <input id="title"  type="text" class="form-control" name="askTitle" placeholder="제목을 입력하세요"></input>
    
      <label for="f-content" class="form-label">내용</label>
      <textarea id="content" class="form-control" id="f-content" name="askContent" placeholder="내용을 입력하세요" class="form-control" cols="50" rows="8"></textarea>
    
    <hr><br><div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-outline-dark" type="submit" value="등록" formaction="ceoadd">등록하기</button>
     <a href="ceomylist" type="button" class="btn btn-outline-dark" >취소하기</a>
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



