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
<br>
  <form method="post">
    <label for='f-status'>공개</label>
    <select id="f-status" name='status' >
	    <option value='1' name='status'>공개</option>
	    <option value='2' name='status'>비공개</option>
    </select><br> 
    
    <div id="passwordRow">
      <label for='f-tempPW' size='100px'>🔏비밀번호</label>
      <input id='f-tempPW' type='password' name='tempPW' placeholder="4자리"></input>
    </div><br>
  
    <br>
		  <label for="f-title" class="form-label">제목</label>
      <input type="text" class="form-control" name="title" placeholder="제목을 입력하세요"></input>
    
      <label for="f-content" class="form-label">내용</label>
      <textarea class="form-control" id="f-content" name="content" placeholder="내용을 입력하세요" class="form-control" cols="50" rows="8"></textarea>
    
    <input type ='hidden' name='writer' value='${loginUser.perNo}'></input>      
    <br><br>
    
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-outline-dark" type="submit" value="등록" formaction="peradd">등록하기</button>
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

<!-- 
<script type="text/javascript">
function popupOpen() {
	var popUrl = "${contextPath}/mylist";
	var popOption = "width=1200, heigth=600, resizable=no, scrollvars=no, status=no;";
	var p = window.open(popUrl, "popUrl", popOption);
	p.focus();
}
</script>
 -->




