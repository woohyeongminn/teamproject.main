<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<div class="all-content">
<b style="font-size:20px"> 이메일 찾기 </b>
<br>
<hr>
  <form action=findemail method="post">
    <label for='f-status'>이메일 찾기</label>
    <select id="f-status" name='status' >
      <option value='1' name='status'>전화번호로 찾기</option>
      <option value='2' name='status'>이름으로 찾기</option>
    </select><br> 

    <div id="telRow">
       <br>
      <label for='f-tempPW' size='100px'>📞전화번호</label>
      <input id='f-tempPW' type='tel' name='tel' placeholder="입력하세요"></input>
    </div><br>    
    <div id="nameRow">
       <br>
      <label for='f-name' size='100px'>🖊이름</label>
      <input id='f-name' type='text' name='name' placeholder="입력하세요"></input>
    </div><br> 
    
    <hr><br><div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-outline-dark" type="submit" value="찾기" >이메일 찾기</button>
     <a href="${contextPath}/app/login" type="button" class="btn btn-outline-dark" >취소하기</a>
   </div> 
  </form>
        
       
    
    
<script>
var fStatus = document.querySelector("#f-status");
var nameRow = document.querySelector("#nameRow");
var telRow = document.querySelector("#telRow");

nameRow.style["display"] = "none";
telRow.style["display"] = "none";

fStatus.addEventListener("input", function() {
  if (fStatus.value == "2") {
	  telRow.style["display"] = "none";
    nameRow.style["display"] = "";
  } else  {
	    telRow.style["display"] = "";
	    nameRow.style["display"] = "none";
  }
});
</script> 
    